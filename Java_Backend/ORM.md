## Mybatis Note
#### 问题记录
+ 查询结果一个content字段返回为null
    1. 通过修改springboot的日志等级使得mybatis输出sql语句。
        ```YAML
        logging:
            level:
                mapper所在的包: debug
        ```
        果然语句中丢失了coontent这个字段。
    1. 查看 PageMapper.xml 
        ```XML
          <resultMap extends="BaseResultMap" id="ResultMapWithBLOBs"type="cn.codeyourlife.cyl.models.domain.Page">
            <result column="content" jdbcType="LONGVARCHAR" property="content" />
          </resultMap>
        ```
        mybatis generator 在表只有一个text类型时，pageMapper.xml 里生成了 withBLOBs 但是domain没有生成两个domain，两者不对应导致查询结果没有返回 content 这个text类型的字段为null。
    1. 尚未找到可以让domain也生成两个的方式（当然你可以手动去添加），只能在generatorConfig.xml里添加 
        ```XML
        <columnOverride column="content" javaType="java.lang.String" jdbcType="VARCHAR" />
        ```
        使得这个字段在pageMapper.xml中不要生成WithBLOBS(有通过配置generatorConfig.xml使得generator能在只有一个text字段时就生成domain的withBLOBs的欢迎交流)
## Hibernate Note
#### 问题记录
+ 联合主键的注解
    1. 将联合主键的字段单独放在一个类中，该类需要实现java.io.Serializable接口并重写equals和hascode。
        ```Java
        public class articleTagPK  implements Serializable{
            private Integer article_id;
            private Integer tag_id;
            ...... 
        }
        ```
    1. 方法一
        `将上面类注解为 @Embeddable`；然后在下面类中(该类不包含联合主键类中的字段)保存该联合主键类的一个引用，并生成set和get方法，并将该引用注解为@Id
        ```Java
        import lombok.Data;
        import lombok.EqualsAndHashCode;
        import lombok.NoArgsConstructor;
        import lombok.ToString;
        @Entity
        @Table(name = "article_tag")
        @Data
        @ToString(callSuper = true)
        @NoArgsConstructor
        @EqualsAndHashCode(callSuper = true)
        public class Tag extends BaseModel {
            @Id
            private articleTagPK articleTagPk;
        }
        ......
        ```
    1. 方法二
        上面类保持不变；在主类中(该类不包含联合主键类中的字段)保存该联合主键类的一个引用，并生成set和get方法，并将该引用注解为@EmbeddedId.
        ```Java
        import lombok.Data;
        import lombok.EqualsAndHashCode;
        import lombok.NoArgsConstructor;
        import lombok.ToString;
        @Entity
        @Table(name = "article_tag")
        @Data
        @ToString(callSuper = true)
        @NoArgsConstructor
        @EqualsAndHashCode(callSuper = true)
        public class Tag extends BaseModel {
            @EmbeddedId
            private articleTagPK articleTagPk;
        }
        ......
        ```
    1. 方法三
        在主类中(该类包含联合主键类中的字段)将联合主键字段都注解为@Id,并在该类上方将上这样的注解：@IdClass(联合主键类.class)。
        ```Java
        import lombok.Data;
        import lombok.EqualsAndHashCode;
        import lombok.NoArgsConstructor;
        import lombok.ToString;
        @IdClass(articleTagPK.class) 
        @Entity
        @Table(name = "article_tag")
        @Data
        @ToString(callSuper = true)
        @NoArgsConstructor
        @EqualsAndHashCode(callSuper = true)
        public class Tag extends BaseModel {
            @Id
            private Integer article_id;
            @Id
            private Integer tag_id;
        }
        ......
        ```
+ 两个对象循环依赖
    ```Java
    @JsonIgnoreProperties({"tags"})
    ```
+ 修复完循环依赖之后：No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer and no properties discovered to create BeanSerializer
    未解决
    