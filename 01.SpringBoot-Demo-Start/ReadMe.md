# 创建Spring Boot 工程

## 1.使用idea创建项目

- 创建Maven项目

- pom.xml 文件添加父级依赖

  ```java
  <!-- 父级依赖 -->
  <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>2.2.2.RELEASE</version>
  </parent>
  ```

- 添加web依赖

  ```
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  ```

- 添加maven插件

  ```
  <build>
      <plugins>
          <plugin>
          	<groupId>org.springframework.boot</groupId>
          	<artifactId>spring-boot-maven-plugin</artifactId>
          </plugin>
      </plugins>
  </build>
  ```

## 2.启动类

```
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
```

## 3. 测试类

```
@Controller
@RequestMapping("/testController")
public class TestController {
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "hello test";
    }
}

@RestController
@RequestMapping("/testController")
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "hello test";
    }
}
```

