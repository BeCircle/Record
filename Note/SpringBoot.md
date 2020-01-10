# SpringBoot 内幕

SpringBoot本质上是一个基于Spring框架的应用，是Spring对“约定优于配置”理念的产物。包括4大核心特性：自动配置、起步依赖、Actuator、命令行界面（可选），主要关注前三点。

+ [IOC](Spring_IoC.md)容器是Spring框架的基础，其他特性都是基于IOC进行实现，如：
+ [AOP](Spring_AOP.md)，
+ [MVC](Spring_MVC.md)。



## JavaConfig与常见Annotation



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

## 事件监听机制



## 自动装配



## 启动过程

