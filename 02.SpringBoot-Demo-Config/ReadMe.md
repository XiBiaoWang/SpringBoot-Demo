# Spring Boot 全局配置文件

## 1.配置文件格式有两种：

- application.properties

```
#服务端口号
server.port=8088
#路径
server.servlet.context-path=/wang
```

- application.yml

```
server:
  port: 8088
  servlet:
    context-path: /wang
```

## 2.Profile：针对多套环境提供不同配置

- 开发环境配置文件 application-dev.yml

  ```
  server:
    port: 8088
    servlet:
      context-path: /wang
  ```

- 生产环境配置文件 application-prod.yml

  ```
  server:
    port: 8081
    servlet:
      context-path: /wang
  ```

-  在全局配置文件application.yml中使用profiles指定使用的环境

  ```
  spring:
    profiles:
      active: prod
  ```

## 3.获取配置文件值

- application.properties/application.yml

  ```
  user.name=zhangsan
  user.age=20
  user.address=beijing
  //1.获取配置文件自定义配置，使用@Value注解
  @Value("${user.name}")
  private String name;
  @Value("${user.age}")
  private Integer age;
  @Value("${user.address}")
  private String address;
  
  @RequestMapping("/test")
  @ResponseBody
  public String test(){
  	return "name="+name+";age="+age+";address="+address;
  }
  //2.或者使用ConfigurationProperties
  
  Person.java:
  //注意，成员变量需要添加get、set方法
  @ConfigurationProperties(prefix="person")
  public class Person {
      private String name;
      private Integer age;
      private String address;
  }
  
  application.properties:
  person.name=zhangsan
  person.age=20
  person.address=beijing
  
  PersonController.java:
  @Autowired
  private Person p;
  @RequestMapping("/getperson")
  public String getPerson(){
  	return p.toString();
  }
  ```

