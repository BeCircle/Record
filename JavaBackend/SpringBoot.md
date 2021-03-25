# SpringBoot 内幕

SpringBoot本质上是一个基于Spring框架的应用，是Spring对“约定优于配置”理念的产物。包括4大核心特性：自动配置、起步依赖、Actuator、命令行界面（可选），主要关注前三点。

大致过程：利用springFactoriesLoader获取配置的监听器，启动监听器。准备environment，启动容器，创建applicationContext(将所有bean加入容器、处理beandefinition)、refresh（调用beanFactoryPostProcessor，处理注解），以上过程中利用事件监听机制发布各阶段事件，注册监听器如果感兴趣就处理对应事件。

处理到@import注解时调用importselector（利用springbootFactoriesLoader加载配置文件，得到stater的自动配置的类，自动配置类根据条件注解进行自动配置。）

+ [IOC](Spring_IoC.md)容器是Spring框架的基础，其他特性都是基于IOC进行实现，如：
+ [AOP](Spring_AOP.md)，
+ [MVC](Spring_MVC.md)。
+ [Annotation](Spring_annotation.md)
+ [事件监听机制](Spring_EventListen.md)

## SpringFactoriesLoader

#### [类加载机制](JVM_classLoader.md)

#### SpringFactoriesLoader

在了解《类加载机制》以及`SPI`的基础上，查看源码：

```Java
public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";

public static List<String> loadFactoryNames(Class<?> factoryType, @Nullable ClassLoader classLoader) {
    // 从解析配置中找出指定名称的配置项
    String factoryTypeName = factoryType.getName();
    return loadSpringFactories(classLoader).getOrDefault(factoryTypeName, Collections.emptyList());
}

private static Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader) {
    MultiValueMap<String, String> result = cache.get(classLoader);
    if (result != null) {return result;}
    // 使用类加载器加载资源文件，取得资源文件url
    Enumeration<URL> urls = (classLoader != null ?
            classLoader.getResources(FACTORIES_RESOURCE_LOCATION) :
            ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
    result = new LinkedMultiValueMap<>();
    while (urls.hasMoreElements()) { // 遍历url，解析配置文件
        URL url = urls.nextElement();
        UrlResource resource = new UrlResource(url);
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        for (Map.Entry<?, ?> entry : properties.entrySet()) {
            String factoryTypeName = ((String) entry.getKey()).trim();
            for (String factoryImplementationName : StringUtils.commaDelimitedListToStringArray((String) entry.getValue())) {
                result.add(factoryTypeName, factoryImplementationName.trim());
            }
        }
    }
    cache.put(classLoader, result);
    return result;
}
```

从`CLASSPATH`下的每个Jar包中搜寻所有`META-INF/spring.factories`配置文件，然后将解析properties文件，找到指定名称的配置后返回。注意，这里不仅会去ClassPath路径下查找，而且会扫描所有路径下的Jar包，只不过这个文件只会在Classpath下的jar包中。

```properties
// 来自 org.springframework.boot.autoconfigure下的META-INF/spring.factories
// EnableAutoConfiguration后文会讲到，它用于开启Spring Boot自动配置功能
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
```

执行`loadFactoryNames(EnableAutoConfiguration.class, classLoader)`后，得到对应的一组`@Configuration` 类，通过反射实例化这些类然后注入到IOC容器中，最后容器里就有了一系列标注了`@Configuration` 的JavaConfig形式的配置类。这就是`SpringFactoriesLoader`，它本质上属于Spring框架私有的一种扩展方案，类似于`SPI`，`Spring Boot`在Spring基础上的很多核心功能都是基于此.

## 自动装配

```java
@SpringBootApplication
public class MoonApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoonApplication.class, args);
    }
}
```

典型的SpringBoot应用的启动类位于`src/main/java`路径下，其中`SpringApplication.run`负责启动引导应用程序；而`@SpringBootApplication`是一个复合`Annotation`，用于开启组件扫描和自动配置，组合了三个有用的注解。

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
        @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
```

+ `@SpringBootConfiguration` 就是`@Configuration`，标明该类是一个`JavaConfig`配置类；

+ `@ComponentScan` 启用组件扫描；

+ `@EnableAutoConfiguration` 表示开启自动配置功能，SpringBoot会根据应用的依赖、自定义的bean、classpath下有没有某个类等因素猜测需要的bean，然后注册到IOC容器中。`@EnableAutoConfiguration`的源码如下，重点是`@Import(AutoConfigurationImportSelector.class)`，`@Import`注解用于导入类，并将这个类作为一个bean的定义注册到容器中，这里把`AutoConfigurationImportSelector`作为bean注入到容器中，

  ```java
  ......
  @AutoConfigurationPackage
  @Import(AutoConfigurationImportSelector.class)
  public @interface EnableAutoConfiguration {
  ```

  而这个类会将所有符合条件的`@Configuation`配置都加载到容器中，`selectImports()`函数最终调用`getCandidateConfigurations()`扫描所有的jar包，并将所有**符合条件**的`@Configuation`配置类注入（可能是监听器来执行的注入）到容器中。

  ```java
  protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
      List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(),
              getBeanClassLoader());
      return configurations;
  }
  ```

  **符合条件**表示在`META-INF/spring.factories`中配置为`EnableAutoConfiguration`的项

## 启动过程

`SpringBoot` 整个启动流程分为两个步骤：初始化一个`SpringApplication`对象、执行该对象的`run`方法。

#### SpringApplication 初始化

`SpringApplication`的构造方法中构造方法：

```java
this.webApplicationType = WebApplicationType.deduceFromClasspath();
this.setInitializers(this.getSpringFactoriesInstances(ApplicationContextInitializer.class));
this.setListeners(this.getSpringFactoriesInstances(ApplicationListener.class));
this.mainApplicationClass = this.deduceMainApplicationClass();
```

初始化流程中最重要的就是通过`SpringFactoriesLoader`找到`spring.factories`文件中配置的`ApplicationContextInitializer`和`ApplicationListener`两个接口的实现类名称，以便后期构造相应的实例。

+ `ApplicationContextInitializer`的主要目的是在`ConfigurableApplicationContext`做`refresh`之前，对`ConfigurableApplicationContext`实例做进一步的设置或处理。`ConfigurableApplicationContext`继承自`ApplicationContext`，其主要提供了对`ApplicationContext`进行设置的能力。
+ `ApplicationListener`是`Spring`框架对Java事件监听机制的一种框架实现，具体分析在：[事件监听机制](Spring_EventListen.md)

1. 实现`ApplicationContextInitializer`

   此接口只定义了一个方法`initialize(C applicationContext)`，因此，实现起来较为简单。大多数情况下不必要自定义一个`ApplicationContextInitializer`，即便是`SpringBoot`框架，默认也只是注册了两个实现，毕竟`Spring`的容器已经非常成熟和稳定

2. 添加自定义监视器

   `SpringBoot`提供了两种方式来添加自定义监视器：

   + 通过方法添加

     1. `SpringApplication.addListeners(ApplicationListener<?>... listeners)`可添加一个监视器；
     2. `SpringApplication.setListeners(Collection<? extends ApplicationListener<?>> listeners)`可用于添加多个监视器。

   + 通过配置文件添加

     由于`SpringApplication`的初始化流程是从`spring.factories`中取得`ApplicationnListener`的实现类，那么可以直接在自己的jar包的`META-INF/spring.factories`文件中新增配置：

     ```java
     org.springframework.context.ApplicationListener=\
     cn.codeyourlife.listeners.xxxxListener\
     ```

#### SpringBoot启动流程

`SpringBoot`应用的启动流程封装在`SpringApplication.run`方法中，整个流程的本质就是在`Spring`容器启动的基础上做大量的拓展：

```java
public ConfigurableApplicationContext run(String... args) {
    ConfigurableApplicationContext context = null;
    Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList();
    this.configureHeadlessProperty();
    // 1
    SpringApplicationRunListeners listeners = this.getRunListeners(args);
    listeners.starting();
    Collection exceptionReporters;
    // 2
    ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
    ConfigurableEnvironment environment = this.prepareEnvironment(listeners, applicationArguments);
    this.configureIgnoreBeanInfo(environment);
    // 3
    Banner printedBanner = this.printBanner(environment);
    // 4
    context = this.createApplicationContext();
    // 5
    exceptionReporters = this.getSpringFactoriesInstances(SpringBootExceptionReporter.class, new Class[]{ConfigurableApplicationContext.class}, context);
    // 6
    this.prepareContext(context, environment, listeners, applicationArguments, printedBanner);
    // 7
    this.refreshContext(context);
    // 8
    this.afterRefresh(context, applicationArguments);
    // 9
    listeners.started(context);
    this.callRunners(context, applicationArguments);
    listeners.running(context);
    return context;
}
```

1. 通过`SpringFactoriesLoader`查找并加载所有的`SpringApplicationRunListeners`，并调用`starting()`方法通知这些应用开始启动。`SpringApplicationRunListeners`本质上是一个事件发布者，它在`SpringBoot`应用启动的不同时间点发布不同应用事件类型(`ApplicationEvent`)，初始化过程中，`SpringApplication`加载了一系列的`ApplicationListener`，这些监视器如果对这些事件感兴趣，就可以接受并处理。

   SpringApplicationRunListener的源码：

   ```java
   public interface SpringApplicationRunListener {
       // 运行run方法时立即调用此方法，可以用户非常早期的初始化工作
       void starting();
       // Environment准备好后，并且ApplicationContext创建之前调用
       void environmentPrepared(ConfigurableEnvironment environment);
       // ApplicationContext创建好后立即调用
       void contextPrepared(ConfigurableApplicationContext context);
       // ApplicationContext加载完成，在refresh之前调用
       void contextLoaded(ConfigurableApplicationContext context);
       void started(ConfigurableApplicationContext context);
   
       void running(ConfigurableApplicationContext context);
   
       void failed(ConfigurableApplicationContext context, Throwable exception);
   }
   ```

   `SpringApplicationRunListener`只有一个实现类：`EventPublishingRunListener`。1处的代码只会获取到一个`EventPublishingRunListener`的实例，`starting()`方法的内容：

   ```java
   public void starting() {
       // 发布一个 ApplicationStartingEvent
       this.initialMulticaster.multicastEvent(new ApplicationStartingEvent(this.application, this.args));
   }
   ```

2. 在`prepareEnvironment()`方法的源码中找到`listeners.environmentPrepared(environment)`，即`SpringApplicationRunListener`接口的第二个方法。`environmentPrepared()`又发布了另外一个事件`ApplicationEnvironmentPreparedEvent`。

   2创建并配置当前应用将要使用的`Environment`，`Environment`用于描述应用程序当前的运行环境，其抽象了两方面内容：配置文件(profile)和属性(properties)。不同的环境(eg：生产环境、预发布环境)可以使用不同的配置文件，而属性则可以从配置文件、环境变量、命令行参数等来源获取。因此，当`Environment`准备好后，在整个应用的任何时候，都可以从`Environment`中获取资源。

   总结起来，2处的两句代码，主要完成以下几件事：

   - 判断`Environment`是否存在，不存在就创建（如果是web项目就创建`StandardServletEnvironment`，否则创建`StandardEnvironment`）
   - 配置`Environment`：配置`profile`以及`properties`;
   - 调用`SpringApplicationRunListener`的`environmentPrepared()`方法，通知事件监听者：应用的`Environment`已经准备好。

3. 启动时的涂鸦，如果需要修改，可以研究一下Banner的实现。

4. 根据是否是web项目，创建不同的ApplicationContext容器。

5. 创建一系列`exceptionReporters`，创建流程依然是通过`SpringFactoriesLoader`获取到所有实现`exceptionReporters`接口的class，然后再创建对应的实例。`exceptionReporters`用于分析故障并提供相关诊断信息。

6. 初始化`ApplicationContext`，主要完成以下工作：

   - 将准备好的`Environment`设置给`ApplicationContext`;
   - 遍历调用所有的`ApplicationContextInitializer`的`initialize()`方法来对已经创建好的`ApplicationContext`进行进一步的处理;
   - 调用`SpringApplicationRunListener`的`contextPrepared()`方法，通知所有的监听者：`ApplicationContext`已经准备完毕;
   - 将所有的`bean`加载到容器中;
   - 调用`SpringApplicationRunListener`的`contextLoaded()`方法，通知所有的监听者：`ApplicationContext`已经装载完毕.

7. 调用`ApplicationContext`的`refresh()`方法，完成IoC容器可用的最后一道工序。从名字上理解为刷新容器，就是插手容器的启动：

   `run`方法调用了`invokeBeanFactoryPostProcessors()`，

   ```java
   protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
       PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, getBeanFactoryPostProcessors());
       ......
   }
   ```

   获取到所有的[`BeanFactoryPostProcessor`](./Spring_IoC.md#BeanFactoryPostProcessor)来对容器做一些额外的操作，`BeanFactoryPostProcessor`允许我们在容器实例化相应对象之前，对注册到容器的`BeanDefinition`所保存的信息做一些额外的操作。

   这里`getBeanFactoryPostProcessors()`方法可以获取到少量的Processor：

   ```java
   ConfigurationWarningsApplicationContextInitializer$ConfigurationWarningsPostProcessor
   SharedMetadataReaderFactoryContextInitializer$CachingMetadataReaderFactoryPostProcessor
   ConfigFileApplicationListener$PropertySourceOrderingPostProcessor
   ```

   因为只有这少量的做了类似于如下操作：

   ```java
   public void initialize(ConfigurableApplicationContext context) {
       context.addBeanFactoryPostProcessor(new ConfigurationWarningsPostProcessor(getChecks()));
   }
   ```

   然后进入到`PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors()`这个方法，这个方法除了会遍历上面获取到的`BeanFactoryPostProcessor`处理外，还会获取**接口**类型为**`BeanDefinitionRegistryPostProcessor`**的bean：`org.springframework.context.annotation.internalConfigurationAnnotationProcessor`，对应的Class为`ConfigurationClassPostProcessor`。`ConfigurationClassPostProcessor`用于解析处理各种注解，包括：`@Configuration`、`@ComponentScan`、`@Import`、`@PropertySource`、`@ImportResource`、`@Bean`。当处理`@import`注解的时候，就会调用<自动配置>这一小节中的`EnableAutoConfigurationImportSelector.selectImports()`来完成自动配置功能。

8. 查找当前context中是否注册有CommandLineRunner和ApplicationRunner，如果有则遍历执行它们。

9. 执行所有SpringApplicationRunListener的finished()方法。