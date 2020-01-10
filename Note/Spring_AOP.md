AOP基于Java的动态代理特性实现，详见 [designPattern_Proxy](/designPattern_Proxy)

#### 1. Basic Concept
+ **Advice:** 通知（切面），定义在连接点做什么，为切面增强提供织入接口。具体的类型有 BeforeAdvice、AfterAdvice、ThrowsAdvice；
+ **Pointcut:** 决定Advice通知应该作用于哪个连接点，也就是说通过pointcut定义需要增强的方法集合，例如可以通过正则表达式进行标识或根据某个方法名进行匹配等。
+ **Advisor:** 把Advice和Pointcut结合起来，通过Advisor，可以定义使用哪个通知并在哪个关注点使用它。

#### 2. 建立 AopProxy 代理对象
在Spring的AOP模块中，一个主要的部分是代理对象的生成，而对于Spring应用是通过配置和调用 Spring 的 ProxyFactoryBean 来完成这个任务，ProxyFactoryBean  中封装了主要代理对象的生成过程。
![aop类继承关系图](https://img.hacpai.com/file/2019/10/aopclasses-2a2f851e.png)

如上图所示：
+ ProxyConfig 为其子类提供了配置属性，可以看作为一个数据基类；
+ AdvisedSupport 封装了AOP对通知和通知器的相关操作，但是对于具体的AOP代理对象的创建，交给它的子类去完成；
+ ProxyCreatorSupport 可以看作是其子类创建 AOP 代理对象的一个辅助类；
+ 通过继承 ProxyCreatorSupport 的功能实现，具体的 AOP 代理对象的生成根据不同的需要，分别由 ProxyFactoryBean、AspectJProxyFactory 和 ProxyFactory 来完成。
+ 对于需要使用 AspectJ 的 AOP 应用，AspectJProxyFactory 起到了集成Spring和AspectJ的作用；
+ 对于使用 Spring AOP 的应用，ProxyFactoryBean 和 ProxyFactory 都提供了AOP 功能的封装，只是使用 ProxyFactoryBean，可以在IOC 容器中完成声明式的配置，而使用 ProxyFactory 需要编程式的使用 Spring AOP 的功能。

![aopcreateproxy1.png](https://img.hacpai.com/file/2019/10/aopcreateproxy1-731bd40a.png)

IOC中的学习我们知道，从FactoryBean中获取对象，是以 getObject方法作为入口完成的，ProxyFactoryBean 实现了 getObject 方法，在这个方法中实现了对target对象增加增强处理（为Proxy代理对象配置Advisor链），然后由于Spring中有singleton和prototype两种类型，需要加以区分。

为Proxy代理对象配置Advisor链是在 initializeAdvisorChain 方法中实现的，这个过程中有一个标志位 advisorChainInitialized 使得初始化的工作只会发生在第一次通过 ProxyFactoryBean 去获取代理对象的时候；然后循环读取所有的 
interceptorNames (advisor)，通过将名字交给容器的getbean方法，并通过回调来获得；然后把 advisor 加入拦截器链中。

getSingletonInstance() 是ProxyFactoryBean 生成 AopProxy 代理对象的入口，它首先通过freshTargetSource() 读取ProxyFactoryBean 中的配置，并为生成代理对象做好必要的准备，比如设置代理的方法的调用接口等；具体生成代理对象由DefaultAopProxyFactory中的createAopProxy() 实现，在这个函数中决定通过CGLIB还是JDK生成需要的代理对象，如果目标对象是接口类，那么适合使用JDK来生成。

在 JdkDynamicAopProxy 中，使用了 JDK 的 Proxy 类来生成代理对象:首先从advised对象中取得代理对象的代理接口配置；然后通过三个参数（classloader，代理接口，Proxy回调方法所在对象）调用newProxyInstance得到对应的Prox代理对象。Proxy回调方法所在对象需要实现 InvocationHandler 接口定义的 invoke 方法，JdkDynamicAopProxy 本身就实现了，也就是说，在Proxy代理的接口被调用时，会触发 JdkDynamicAopProxy 的invoke 方法，这个 invoke 方法就是AOP编织实现的地方。ObjenesisCglibAopProxy 通过 enhancer 对象 callback的设置实现 Spring 的AOP。

#### 3. Spring AOP拦截器调用的实现
在 JdkDynamicAopProxy 中生成Proxy对象时，JdkDynamicAopProxy 实现了 InvocationHandler接口，在Proxy对象的代理方法m1被调用时，JdkDynamicAopProxy 作为Proxy对象的一个属性，它的invoke方法被调用。invoke 方法的用途包括获取目标对象、拦截器链、同时把这些对象作为输入，创建了ReflectiveMethodInvocation 对象，通过这个对象完成了对AOP功能实现的封装。如果没有设置拦截器，那么会对目标对象的方法直接调用，对于jdk生成的方式，是通过AopUtils使用反射机制在invokeJoinPointUsingReflection方法中实现的。基于CGLIB生成的代理对象的调用也非常类似。
基于JDK和CGLIB两种方式最终都是对ReflectionMethodInvocation中的proceed方法的调用，在这个方法中会：
+ 对代理方法进行一个匹配判断，决定拦截器是否满足切面增强的要求（pointcut中的matchs）；
+ 逐个运行拦截器中的拦截方法；

![aopcall.png](https://img.hacpai.com/file/2019/10/aopcall-8d7be76b.png)

在AdvisedSupport中，getInterceptorsAndDynamicInterceptionAdvice 方法在JdkDynamicAopProxy或者DynamicAdvisedInterceptor中被调用来获取拦截器链。获取拦截器链的工作是由配置好的AdvisorChainFactory来完成的，默认是DefaultAdvisorChainFactory；它首先设置一个List，长度有配置属性决定；然后，通过GlobalAdvisorAdapterRegistry来实现拦截器的注册，GlobalAdvisorAdapterRegistry是一个单例实现，具体实现是DefaultAdvisorAdapterRegestry。DefaultAdvisorAdapterRegestry 的getInterceptors方法通过循环调用 supportAdvice() 方法来判断advisor的类型，然后根据不同的类型来调用对应的adapter 的 getInterceptro() 注册不同的 AdviceInterceptor。这里用到了adapter模式。
具体拦截器的实现包括了invoke方法，invoke方法中调用增强的顺序不同，比如MethodBeforeInterceptor 调用增强之后再调用源方法，其他的以此类推。
![configAdvice.png](https://img.hacpai.com/file/2019/10/configAdvice-c6830bf2.png)


#### 4. Spring AOP的高级特性
+ **HotSwappableTargetSource**： 使得用户可以以线程安全的方式切换目标对象；
	+ 配置：将 HotSwappableTargetSource 配置到ProxyFactoryBean 的target 属性；
	+ 使用：调用swap方法（使用新的target对象调换目标target对象）；
	+ 注意：在JdkDynamicAopProxy 中的 invoke中，通过targetSource. getTraget() 来获取目标对象，这个对象是否被调换过，invoke不关心。