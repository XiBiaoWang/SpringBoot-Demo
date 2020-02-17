# Spring Boot 日志管理

## 1.Spring Boot 日志框架

- Spring Boot 支持 Java Util Logging、Log4j2、Logback，创建项目时已经添加了对jar包的依赖，默认使用Logback。

- 日志级别：从低到高 TRACE < DEBUG < INFO < WARN < ERROR < FATAL < OFF，如果设置为WARN，则低于WARN的信息就不会输出。

  

## 2.使用Logback

- 使用方法：

  - Spring Boot 默认使用Logback，pom文件无需添加引用。

  - 自定义日志输出格式：

    ```
    %d{HH:mm:ss.SSS}——日志输出时间
    
    %thread——输出日志的进程名字，这在Web应用以及异步任务处理中很有用
    
    %-5level——日志级别，并且使用5个字符靠左对齐
    
    %logger- ——日志输出者的名字
    
    %msg——日志消息
    
    %n——平台的换行符
    ```

    

- 配置文件：Logback相关配置可以配置在全局配置文件中，也可以配置在单独的配置文件中。

  - 全局配置文件 application.properties

    ```
    #日志输出级别
    logging.level.root=info
    #针对LogController类的日志输出级别
    logging.level.com.springboot.controller.LogController=error
    #日志输出到文件
    logging.file=e:/Works/SpringBoot-Demo/Logs/app.log
    #日志在控制台和日志文件中的输出格式
    logging.pattern.console=%d{yyyy/MM/dd-HH:mm:ss}[%thread] %-5level %logger- %msg%n
    logging.pattern.file=%d{yyyy/MM/dd-HH:mm:ss}[%thread] %-5level %logger- %msg%n
    ```

  - 单独的配置文件：配置文件命名为：logback.xml,logback-spring.xml,logback.groovy,logback-spring.groovy 四种格式都可以。官方推荐以-spring命名的配置文件。另外还可以配置自定义名称的配置文件，例如：logconfig.xml,但是需要在全局配置文件中配置节点：logging.config=classpath:logconfig.xml

    logback-spring.xml

    ```
    <?xml version="1.0" encoding="UTF-8" ?>
    <configuration scan="true" scanPeriod="60 seconds" debug="false">
        <contextName>logback</contextName>
        <property name="log.path" value="E:\\Works\\SpringBoot-Demo\\Logs\\app.log"></property>
        <!--输出到控制台配置 -->
        <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
            <encoder>
                <pattern>%d{HH:mm:ss.SSS} %contextName [%thread] %-5level %logger{36} - %msg%n</pattern>
            </encoder>
        </appender>
        <!--输出到日志文件配置 -->
        <appender name="file" class="ch.qos.logback.core.rolling.RollingFileAppender">
            <file>${log.path}}</file>
            <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                <fileNamePattern>logback.%d{yyyy-MM-dd}.log</fileNamePattern>
            </rollingPolicy>
            <encoder>
                <pattern>%d{HH:mm:ss.SSS} %contextName [%thread] %-5level %logger{36} - %msg%n</pattern>
            </encoder>
        </appender>
        <root level="info">
            <appender-ref ref="console" />
            <appender-ref ref="file" />
        </root>
        <!-- 针对某个包的的日志配置 -->
        <logger name="com.springboot.services" />
        <!-- 针对某个类的的日志配置 -->
        <logger name="com.springboot.controller.LogController" level="DEBUG" additivity="false">
            <appender-ref ref="console" />
        </logger>
        <!-- 多环境日志配置 -->
        <!-- 开发环境和测试环境，使用,分割 -->
        <springProfile name="dev,test">
            <logger name="com.springboot.controller" level="INFO" />
        </springProfile>
    
        <!-- 生产环境 -->
        <springProfile name="prod">
            <logger name="com.springboot.controller" level="ERROR" />
        </springProfile>
    </configuration>
    ```

## 3.使用log4j

- 使用方法：Spring Boot 默认使用Logback，pom文件需要先排除掉Logback依赖，添加对log4j的依赖

  ```
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <exclusions>
          <exclusion>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-logging</artifactId>
          </exclusion>
      </exclusions>
  </dependency>
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-log4j</artifactId>
  </dependency>
  ```

  

- 配置文件：可以使用log4j.properties,log4j-spring.properties,官方推荐使用-spring命名的配置文件

  log4j-spring.properties

  ```
  #log4j 配置
  #log4j定义了8个级别的log优先级从高到低依次为：OFF、FATAL、ERROR、WARN、INFO、DEBUG、TRACE、 ALL。
  #CONSOLE前面的DEBUG是控制台需要打印什么DEBUG级别以上的日志的信息
  log4j.rootLogger=DEBUG, CONSOLE, ERROR, WARN, INFO, DEBUG, ALL
  #-----------------------------------------------------------------------------------------------------
  #输出信息到控制台CONSOLE
  log4j.appender.CONSOLE = org.apache.log4j.ConsoleAppender 
  log4j.appender.CONSOLE.Target = System.out
  log4j.appender.CONSOLE.layout = org.apache.log4j.PatternLayout
  log4j.appender.CONSOLE.layout.ConversionPattern = [%-5p] %d{yyyy-MM-dd HH:mm:ss,SSS} method:%l%n%m%n
  #-----------------------------------------------------------------------------------------------------
  #输出ERROR 级别以上的日志到error.log
  log4j.logger.ERROR=ERROR
  log4j.appender.ERROR = org.apache.log4j.DailyRollingFileAppender
  log4j.appender.ERROR.File =logs/error/error.log
  log4j.appender.file.DatePattern ='.'yyyy-MM-dd
  log4j.appender.ERROR.Threshold = ERROR 
  log4j.appender.ERROR.Append = true
  log4j.appender.ERROR.layout = org.apache.log4j.PatternLayout
  log4j.appender.ERROR.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n
  #-----------------------------------------------------------------------------------------------------
  #输出WARN 级别以上的日志到warn.log
  log4j.logger.WARN=WARN
  log4j.appender.WARN = org.apache.log4j.DailyRollingFileAppender
  log4j.appender.WARN.File =logs/warn/warn.log
  log4j.appender.file.DatePattern ='.'yyyy-MM-dd
  log4j.appender.WARN.Threshold = WARN
  log4j.appender.WARN.Append = true
  log4j.appender.WARN.layout = org.apache.log4j.PatternLayout
  log4j.appender.WARN.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n
  #-----------------------------------------------------------------------------------------------------
  #输出INFO级别以上的内容到info.log中
  log4j.logger.INFO=INFO
  log4j.appender.INFO = org.apache.log4j.DailyRollingFileAppender
  log4j.appender.INFO.File = logs/info/info.log
  log4j.appender.file.DatePattern ='.'yyyy-MM-dd
  log4j.appender.INFO.Threshold = INFO 
  log4j.appender.INFO.Append = true
  log4j.appender.INFO.layout = org.apache.log4j.PatternLayout
  log4j.appender.INFO.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n
  #-----------------------------------------------------------------------------------------------------
  #输出DEBUG 级别以上的日志到debugger.log
  log4j.logger.DEBUG=DEBUG
  log4j.appender.DEBUG = org.apache.log4j.DailyRollingFileAppender
  log4j.appender.DEBUG.File = logs/debugger/debugger.log
  log4j.appender.file.DatePattern ='.'yyyy-MM-dd
  log4j.appender.DEBUG.Threshold = DEBUG
  log4j.appender.DEBUG.Append = true
  log4j.appender.DEBUG.layout = org.apache.log4j.PatternLayout
  log4j.appender.DEBUG.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n
  #-----------------------------------------------------------------------------------------------------
  #输出ALL级别的日志到all.log
  log4j.logger.ALL=ALL
  log4j.appender.ALL = org.apache.log4j.DailyRollingFileAppender
  log4j.appender.ALL.File = logs/all/all.log
  log4j.appender.file.DatePattern ='.'yyyy-MM-dd
  log4j.appender.ALL.Threshold = ALL 
  log4j.appender.ALL.Append = true
  log4j.appender.ALL.layout = org.apache.log4j.PatternLayout
  log4j.appender.ALL.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n
  ```

  





