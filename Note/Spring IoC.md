# Spring IoC
![image.png](https://img.hacpai.com/file/2019/09/image-b69a6590.png)
本文章包含的所有图片是在 **springboot-5.1.8** 之上进行的简单分析。容器的初始化过程由具体的实现，用图中红色部分简略表示；整个过程由refresh的调用触发，一般是由具体的applicationContext调用，比如FileSystemXmlApplicationContext。

#### 1. KeyWords
+ **Resource:** Sping用来封装IO操作的类；
+ **BeanDefinition:** BeabDefinition 就是对依赖反转模式中管理对象依赖关系的数据抽象，也是容器实现依赖反转功能的核心数据结构，依赖反转功能都是围绕对这个 BeabDefubution 的处理来完成的。
+ **BeanFactory:** 也就是IoC容器或者对象工厂，Spring 中所有的 Bean 都是 BeanFactory 来进行管理的。
+ **FactoryBean:** 这个Bean 不是一个简单的 Bean ，而是一个能产生或者修饰对象生成的工厂 Bean，它的实现与设计模式中的工厂模式和修饰器模式类似。用户可以使用转义符“&”来得到FactoryBean 本身。
+ **DefaultListableBeanFactory:** 在Spring中是一个默认的功能完整的IoC容器。
+ **XmlBeanFactory:** 建立在 DefaultListableBeanFactory 这个基本容器之上，增加了 XML 读取的功能。

#### 2. BeanFactory
定义了容器的基本功能；可以使用不同的 Bean 检索方法从IoC容器中得到需要的Bean；

+ 编程式使用IoC容器的过程
	```
	ClassPathResource res = new ClassPathResource("base.xml");
	DefaultListableBeanFactory factory  = new DefaultListableBeanFactory ();
	XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
	reader.loadBeanDefinitions(res);
	```
	1. 创建 IoC 配置文件的抽象资源，这个资源包含了 BeanDefinition 的定义信息；
	1. 创建一个 BeanFactory；
	1. 创建一个载入 BeanDefinition的读取器，通过一个回调配置给BeanFactory；
	1. 读取配置信息。

#### 3. ApplicationContext
在简单容器的基础上增加了许多面向框架的特性，同时对应用环境做了许多适配。

+ 新特性
	1. 支持不同的信息源（extends MessageSource），这些信息源的拓展功能可以支持国际化的实现，支持了多语言版本的开发；
	1. 从不同的地方得到Bean定义资源（extends ResourcePatternResolver( extends ResourceLoader)）使用户程序可以灵活的定义Bean 定义信息；
	1. 支持应用事件(ApplicationEventPublisher)，从而在上下文中引入了事件机制。这些事件和Bean的生命周期的结合为Bean的管理提供便利；
	1. 其他附加服务，面向框架的使用风格。

#### 4. IoC 容器的初始化过程
`注意`：这里的容器初始化过程不包括Bean的依赖注入，Spring的IoC中Bean定义的载入和依赖注入时两个独立的过程，依赖注入一般繁盛在应用第一次通过getBean向容器索取Bean的时候。（如果设置了Bean中的lazyinit属性，这个Bean的依赖注入在IoC容器初始化时就预先完成）
1. Resource 定位
	由ResourceLoader通过统一的Resource接口来完成，这个Resource对各种Bean Definition都提供了统一的接口（文件系统中的使用FileSystemResource，类路径中的使用ClassPathResource）。
1. BeanDefinition的载入
	把用户定义好的Bean表示成IoC容器内部的数据结构BeanDefinition。
1. 向IoC容器注册BeanDefinition
	调用BeanDefinitionRegistry将BeanDefinition向IoC容器进行注册，注册到一个HashMap。

#### 5. IoC 容器的依赖注入
依赖注入的过程是用户第一次向IoC容器索要Bean时触发的，或者是在BeanDefinition信息中通过控制lazy-init属性来让容器完成对Bean的预实例化。过程的触发入口在 DefalutListableBeanFactory 中 getBean ，具体实现在 AbstractBeanFactory 的 doGetBean。
![image.png](https://img.hacpai.com/file/2019/09/image-7012a1f3.png)

doGetBean 调用实现在 AbstractAutowireCapableBeanFactory 中的 createBean ，createBean 不仅依据 BeanDefinition 定义的要求生成了需要的 Bean， 还对 Bean 的初始化进行了处理，比如实现了在 BeanDefinition 中的 init-method属性定义，Bean 后置处理器等。与依赖注入关系密切的方法有 createBeanInstance 和 populateBean， 在 createBeanInstance 中生成了 Bean 所包含的 Java 对象（可以通过工厂方法生成，也可以通过 autowire 特性来生成，由相关的 BeanDefinition 来指定）。 这里使用 CGLIB 对 Bean 进行实例化，SimpleInstantiationStrategy 这个 Strategy 是 Spring 用来生成 Bean 对象的默认类，它提供了两种实例化Java对象的方法，一种是通过 BeanUtils，它使用了JVM的反射功能，一种是通过 CGLIB 来生成。
![image.png](https://img.hacpai.com/file/2019/09/image-6e87b244.png)


在实例化 Bean 对象生成的基础上，通过populateBean 将这些Bean对象的依赖关系处理好，这些依赖关系处理的依据就是已经解析得到的 BeanDefinition。populateBean 首先处理autowire的注入（可以根据Bean的名字或者类型）；然后通过 applyPropertyValues 对属性进行解析和注入：这里通过 BeanDefinitionValueResolver 中的 resolveValueIfNecessary 对 BeanDefinition 进行解析，然后注入到 property 中。在完成这个解析过程后，已经为依赖注入准备好了条件，真正把Bean对象设置到它所依赖的另一个Bean的属性中去的是 AbstractNestablePropertyAccessor 中的 setPropertyValue。
![populateBean 的详细流程](https://img.hacpai.com/file/2019/09/image-688aec4c.png)

#### 6. 容器的其他相关特性
1. FactoryBean 的实现
	如第二张图片所示，AbstractBeanFactory 的 doGetBean 调用 getObjectForBeanInstance这个流程开始，最终返回的是作为工厂的FactoryBean生产的产品，而不是 FactoryBean 本身，这里采用工厂模式 getObject 由具体的 FactoryBean 实现。

1. BeanPostProcessor 的实现
	这个 Bean 的后置处理器是一个监听器，可以监听容器触发的事件。将它向IoC容器注册后，容器中管理的Bean具备了接受IoC容器事件回调的能力。图二展示了调用的位置。	

1. autowire 的实现
	触发的位置是 AbstractAutowireCapableBeanFactory 的 populateBean 中，如图三所示。

1. Bean 对 IoC 容器的感知
	Spring 通过ApplicationContextAwareProcessor中的postProcessBeforeInitialization来传递容器的状态给Bean（前三个通过AbstractAutowireCapableBeanFactory 中的 invokeAwareMethods方法传递），前提是Bean是**Aware接口的实例，并且在 bean 中实现相应的函数，比如 setApplicationContext 函数。
	+ BeanNameAware： 向 Bean 传递 IoC 容器的名称；
	+ BeanClassLoaderAware 5.1.8 有；
	+ BeanFactoryAware： 向 Bean 传递 IoC 容器，从而直接在Bean中使用容器的服务；
	+ ApplicationContextAware： 向 Bean 传递 Bean 所在的上下文，从而可以在Bean中发布应用上下文的事件；
	+ MessageSourceAware： 向 Bean 传递消息源；
	+ ApplicationEventPublisherAware： 向 Bean 传递应用上下文的事件发布器，从而可以在 Bean 中发布应用上下文事件；
	+ ResourceLoaderAware： 向 Bean 传递 ResourceLoader， 从而可以在 Bean 中使用 ResourceLoader 加载外部对应的 Resource 资源。
	+ EmbeddedValueResolverAware 5.1.8 有；
	+ EnvironmentAware 5.1.8 有；