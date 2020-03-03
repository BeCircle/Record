## 1. Java事件监听机制

在服务器端，事件的监听机制多用于异步通知以及监控和异常处理。Java提供了实现事件监听机制的两个基础类：自定义事件类型扩展自`java.util.EventObject`、事件的监听器扩展自`java.util.EventListener`。来看一个简单的实例：简单的监控一个方法的耗时。

1. 首先定义事件类型

   通常的做法是扩展`EventObject`，随着事件的发生，相应的**状态通常都封装在此类中**：

```java
public class MethodMonitorEvent extends EventObject {
    // 时间戳，用于记录方法开始执行的时间
    public long timestamp;
    public MethodMonitorEvent(Object source) {
        super(source);
    }
}
```

2. 提供方法对接收到的事件进行处理

   事件发布之后，相应的监听器即可对该类型的事件进行处理，我们可以在方法开始执行之前发布一个begin事件，在方法执行结束之后发布一个end事件，相应地，事件监听器需要提供方法对这两种情况下接收到的事件进行处理：

```java
// 1、定义事件监听接口
public interface MethodMonitorEventListener extends EventListener {
    // 处理方法执行之前发布的事件
    public void onMethodBegin(MethodMonitorEvent event);
    // 处理方法结束时发布的事件
    public void onMethodEnd(MethodMonitorEvent event);
}
// 2、事件监听接口的实现：如何处理
public class AbstractMethodMonitorEventListener implements MethodMonitorEventListener {
    @Override
    public void onMethodBegin(MethodMonitorEvent event) {
        // 记录方法开始执行时的时间
        event.timestamp = System.currentTimeMillis();
    }
    @Override
    public void onMethodEnd(MethodMonitorEvent event) {
        // 计算方法耗时
        long duration = System.currentTimeMillis() - event.timestamp;
        System.out.println("耗时：" + duration);
    }
}
```

1. 发布事件

   事件监听器接口针对不同的事件发布实际提供相应的处理方法定义，最重要的是，其方法只接收`MethodMonitorEvent`参数，说明这个监听器类只负责监听器对应的事件并进行处理。有了事件和监听器，剩下的就是发布事件，然后让相应的监听器监听并处理。通常情况，我们会有一个事件发布者，它本身作为事件源，在合适的时机，将相应的事件发布给对应的事件监听器：

```csharp
public class MethodMonitorEventPublisher {

    private List<MethodMonitorEventListener> listeners = new ArrayList<MethodMonitorEventListener>();

    public void methodMonitor() {
        MethodMonitorEvent eventObject = new MethodMonitorEvent(this);
        publishEvent("begin",eventObject);
        // 模拟方法执行：休眠5秒钟
        TimeUnit.SECONDS.sleep(5);
        publishEvent("end",eventObject);

    }

    private void publishEvent(String status,MethodMonitorEvent event) {
        // 避免在事件处理期间，监听器被移除，这里为了安全做一个复制操作
        List<MethodMonitorEventListener> copyListeners = ➥ new ArrayList<MethodMonitorEventListener>(listeners);
        for (MethodMonitorEventListener listener : copyListeners) {
            if ("begin".equals(status)) {
                listener.onMethodBegin(event);
            } else {
                listener.onMethodEnd(event);
            }
        }
    }
    
    public static void main(String[] args) {
        MethodMonitorEventPublisher publisher = new MethodMonitorEventPublisher();
        publisher.addEventListener(new AbstractMethodMonitorEventListener());
        publisher.methodMonitor();
    }
    // 省略实现
    public void addEventListener(MethodMonitorEventListener listener) {}
    public void removeEventListener(MethodMonitorEventListener listener) {}
    public void removeAllListeners() {}
```

对于事件发布者（事件源）通常需要关注两点：

1. 在合适的时机发布事件。此例中的`methodMonitor()`方法是事件发布的源头，其在方法执行之前和结束之后两个时间点发布`MethodMonitorEvent`事件，每个时间点发布的事件都会传给相应的监听器进行处理。在具体实现时需要注意的是，事件发布是顺序执行，为了不影响处理性能，事件监听器的处理逻辑应尽量简单。
2. 事件监听器的管理。`publisher`类中提供了事件监听器的注册与移除方法，这样客户端可以根据实际情况决定是否需要注册新的监听器或者移除某个监听器。如果这里没有提供`remove`方法，那么注册的监听器示例将一直被`MethodMonitorEventPublisher`引用，即使已经废弃不用了，也依然在发布者的监听器列表中，这会导致隐性的内存泄漏。

## 2. Spring容器内的事件监听机制

`Spring`的`ApplicationContext`容器内部中的所有事件类型均继承自`org.springframework.context.AppliationEvent`，容器中的所有监听器都实现`org.springframework.context.ApplicationListener`接口，并且以`bean`的形式注册在容器中。一旦在容器内发布`ApplicationEvent`及其子类型的事件，注册到容器的`ApplicationListener`就会对这些事件进行处理。

`ApplicationEvent`继承自`EventObject`，`Spring`提供了一些默认的实现，比如：`ContextClosedEvent`表示容器在即将关闭时发布的事件类型，`ContextRefreshedEvent`表示容器在初始化或者刷新的时候发布的事件类型......

容器内部使用`ApplicationListener`作为事件监听器接口定义，它继承自`EventListener`。`ApplicationContext`容器在启动时，会自动识别并加载`EventListener`类型的bean，一旦容器内有事件发布，将通知这些注册到容器的`EventListener`。

`ApplicationContext`接口继承了`ApplicationEventPublisher`接口，该接口提供了`void publishEvent(ApplicationEvent event)`方法定义，不难看出，`ApplicationContext`容器担当的就是事件发布者的角色。如果有兴趣可以查看`AbstractApplicationContext.publishEvent(ApplicationEvent event)`方法的源码：`ApplicationContext`将事件的发布以及监听器的管理工作委托给`ApplicationEventMulticaster`接口的实现类。在容器启动时，会检查容器内是否存在名为`applicationEventMulticaster`的`ApplicationEventMulticaster`对象实例。如果有就使用其提供的实现，没有就默认初始化一个`SimpleApplicationEventMulticaster`作为实现。

最后，如果我们业务需要在容器内部发布事件，只需要为其注入`ApplicationEventPublisher`依赖即可：实现`ApplicationEventPublisherAware`接口或者`ApplicationContextAware`接口(Aware接口相关内容请回顾上文)