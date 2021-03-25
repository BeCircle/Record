## JavaConfig与常见Annotation

`JavaConfig`基于Java代码和`Annotation`注解来描述`bean`之间的依赖绑定关系.

如果两个bean之间有依赖关系的话，在XML配置中应该是这样：

```xml
<bean id="bookService" class="cn.moondev.service.BookServiceImpl">
    <property name="dependencyService" ref="dependencyService"/>
</bean>
<bean id="otherService" class="cn.moondev.service.OtherServiceImpl">
    <property name="dependencyService" ref="dependencyService"/>
</bean>
<bean id="dependencyService" class="DependencyServiceImpl"/>
```

而在JavaConfig中则是这样的：

```java
@Configuration
public class MoonBookConfiguration {
    // 如果一个bean依赖另一个bean，则直接调用对应 JavaConfig 类中依赖bean的创建方法即可
    // 这里直接调用dependencyService()
    @Bean
    public BookService bookService() {
        return new BookServiceImpl(dependencyService());
    }
    @Bean
    public OtherService otherService() {
        return new OtherServiceImpl(dependencyService());
    }
    @Bean
    public DependencyService dependencyService() {
        return new DependencyServiceImpl();
    }
}
```

+ **@ComponentScan**

  `@ComponentScan`注解对应XML配置形式中的`<context:component-scan>`元素，表示启用组件扫描，Spring会自动扫描所有通过注解配置的bean，然后将其注册到IOC容器中。我们可以通过`basePackages`等属性来指定`@ComponentScan`自动扫描的范围，如果不指定，默认从声明`@ComponentScan`所在类的`package`进行扫描。正因为如此，SpringBoot的启动类都默认在`src/main/java`下。

+ **@Import**

  `@Import`注解用于导入配置类，举个简单的例子：

  ```java
  @Configuration
  public class MoonBookConfiguration {
      @Bean
      public BookService bookService() {
          return new BookServiceImpl();
      }
  }
  ```

  现在有另外一个配置类，比如：`MoonUserConfiguration`，这个配置类中有一个bean依赖于`MoonBookConfiguration`中的bookService，如何将这两个bean组合在一起？借助`@Import`即可：

  ```java
  @Configuration
  // 可以同时导入多个配置类，比如：@Import({A.class,B.class})
  @Import(MoonBookConfiguration.class)
  public class MoonUserConfiguration {
      @Bean
      public UserService userService(BookService bookService) {
          return new BookServiceImpl(bookService);
      }
  }
  ```

  在4.2之前，`@Import`注解只支持导入配置类，但是在4.2之后，它支持导入普通类，并将这个类作为一个bean的定义注册到IOC容器中。

+ **@Conditional**

  `@Conditional`表示在满足某种条件后才初始化一个bean或者启用某些配置。它一般用在由`@Component`、`@Service`、`@Configuration`等注解标识的类上面，或者由`@Bean`标记的方法上。如果一个`@Configuration`类标记了`@Conditional`，则该类中所有标识了`@Bean`的方法和`@Import`注解导入的相关类将遵从这些条件。

  在Spring里可以很方便的编写你自己的条件类，所要做的就是实现`Condition`接口，并覆盖它的`matches()`方法。举个例子，下面的简单条件类表示只有在`Classpath`里存在`JdbcTemplate`类时才生效：

  ```java
  public class JdbcTemplateCondition implements Condition {
      @Override
      public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
          try {
          conditionContext.getClassLoader().loadClass("org.springframework.jdbc.core.JdbcTemplate");
              return true;
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          }
          return false;
      }
  }
  ```

  在声明bean的时候，可以使用自定义的条件类：

  ```java
  @Conditional(JdbcTemplateCondition.class)
  @Service
  public MyService service() {
      ......
  }
  ```

  `Spring Boot`定义了很多有趣的条件，并把他们运用到了配置类上，这些配置类构成了`Spring Boot`的自动配置的基础。`Spring Boot`运用条件化配置的方法是：定义多个特殊的条件化注解，并将它们用到配置类上。

  | 条件化注解                      | 配置生效条件                                         |
  | ------------------------------- | ---------------------------------------------------- |
  | @ConditionalOnBean              | 配置了某个特定bean                                   |
  | @ConditionalOnMissingBean       | 没有配置特定的bean                                   |
  | @ConditionalOnClass             | Classpath里有指定的类                                |
  | @ConditionalOnMissingClass      | Classpath里没有指定的类                              |
  | @ConditionalOnExpression        | 给定的Spring Expression Language表达式计算结果为true |
  | @ConditionalOnJava              | Java的版本匹配特定指或者一个范围值                   |
  | @ConditionalOnProperty          | 指定的配置属性要有一个明确的值                       |
  | @ConditionalOnResource          | Classpath里有指定的资源                              |
  | @ConditionalOnWebApplication    | 这是一个Web应用程序                                  |
  | @ConditionalOnNotWebApplication | 这不是一个Web应用程序                                |

+ **@ConfigurationProperties与@EnableConfigurationProperties**

  当某些属性的值需要配置的时候，我们一般会在`application.properties`文件中新建配置项，然后在bean中使用`@Value`注解来获取配置的值，比如下面配置数据源的代码。

    ```java
    // jdbc config
    jdbc.mysql.url=jdbc:mysql://localhost:3306/sampledb
    jdbc.mysql.username=root
    jdbc.mysql.password=123456
    ......
  
    // 配置数据源
    @Configuration
    public class HikariDataSourceConfiguration {
        @Value("jdbc.mysql.url")
        public String url;
        @Value("jdbc.mysql.username")
        public String user;
        @Value("jdbc.mysql.password")
        public String password;
        @Bean
        public HikariDataSource dataSource() {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(url);
            hikariConfig.setUsername(user);
            hikariConfig.setPassword(password);
            // 省略部分代码
            return new HikariDataSource(hikariConfig);
        }
    }
    ```

    使用`@Value`注解注入的属性通常都比较简单，如果同一个配置在多个地方使用，也存在不方便维护的问题（考虑下，如果有几十个地方在使用某个配置，而现在你想改下名字，你改怎么做？）。对于更为复杂的配置，Spring Boot提供了更优雅的实现方式，那就是`@ConfigurationProperties`注解。我们可以通过下面的方式来改写上面的代码：

    ```java
    @Component
    //  还可以通过@PropertySource("classpath:jdbc.properties")来指定配置文件
    @ConfigurationProperties("jdbc.mysql")
    // 前缀=jdbc.mysql，会在配置文件中寻找jdbc.mysql.*的配置项
    pulic class JdbcConfig {
        public String url;
        public String username;
        public String password;
    }
    @Configuration
    public class HikariDataSourceConfiguration {
        @AutoWired
        public JdbcConfig config;
        @Bean
        public HikariDataSource dataSource() {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(config.url);
            hikariConfig.setUsername(config.username);
            hikariConfig.setPassword(config.password);
            // 省略部分代码
            return new HikariDataSource(hikariConfig);
        }
    }
    ```

    `@ConfigurationProperties`对于更为复杂的配置，处理起来也是得心应手，比如有如下配置文件：

    ```bash
    #App
    app.menus[0].title=Home
    app.menus[0].name=Home
    app.menus[0].path=/
    app.menus[1].title=Login
    app.menus[1].name=Login
    app.menus[1].path=/login
  
    app.compiler.timeout=5
    app.compiler.output-folder=/temp/
  
    app.error=/error/
    ```

  可以定义如下配置类来接收这些属性

    ```java
    @Component
    @ConfigurationProperties("app")
    public class AppProperties {
        public String error;
        public List<Menu> menus = new ArrayList<>();
        public Compiler compiler = new Compiler();
        public static class Menu {
            public String name;
            public String path;
            public String title;
        }
        public static class Compiler {
            public String timeout;
            public String outputFolder;
        }
    }
    ```

`@EnableConfigurationProperties`注解表示对`@ConfigurationProperties`的内嵌支持，默认会将对应Properties Class作为bean注入的IOC容器中，即在相应的Properties类上不用加`@Component`注解。