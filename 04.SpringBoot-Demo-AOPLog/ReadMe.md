# 使用AOP管理日志

## 1.AOP：

	- Aspect-Oriented Programming，面向切面编程，它利用一种"横切"的技术，将那些多个类的共同行为封装到一个可重用的模块。便于减少系统的重复代码，降低模块之间的耦合度，并有利于未来的可操作性和可维护性。

## 2.相关概念：

 - Aspect（切面）：声明类似于Java中的类声明，在Aspect中会包含一些Pointcut及相应的Advice。
 - Joint point（连接点）：表示在程序中明确定义的点。包括方法的调用、对类成员的访问等。
 - Pointcut（切入点）：表示一个组Joint point，如方法名、参数类型、返回类型等等。
 - Advice（通知）：Advice定义了在Pointcut里面定义的程序点具体要做的操作，它通过(before、around、after(return、throw)、finally来区别实在每个Joint point之前、之后还是执行 前后要调用的代码。
 - Before：在执行方法前调用Advice，比如请求接口之前的登录验证。
 - Around：在执行方法前后调用Advice，这是最常用的方法。
 - After：在执行方法后调用Advice，after、return是方法正常返回后调用，after\throw是方法抛出异常后调用。
 - Finally：方法调用后执行Advice，无论是否抛出异常还是正常返回。
 - AOP proxy：AOP proxy也是Java对象，是由AOP框架创建，用来完成上述动作，AOP对象通常可以通过JDK dynamic proxy完成，或者使用CGLIb完成。
 - Weaving：实现上述切面编程的代码织入，可以在编译时刻，也可以在运行时刻，Spring和其它大多数Java框架都是在运行时刻生成代理。

## 3.项目实例

 - 1 添加依赖

   ```
   <!-- aop依赖 -->
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-aop</artifactId>
   </dependency>
   ```

 - 2 切面类

   ```
   @Aspect
   @Component
   public class LogAspect {
       private Logger log = LoggerFactory.getLogger(LogAspect.class);
       ThreadLocal<Long> startTime = new ThreadLocal<>();
       /**
        * 定义切点，controller包下面的所以类的公有方法
        */
       @Pointcut(value = "execution(public * com.springboot.controller..*.*(..)))")
       public void requestLog(){}
       @Before("requestLog()")
       public void doBefore(JoinPoint joinPoint){
           startTime.set(System.currentTimeMillis());
           ServletRequestAttributes servletRequest = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
           HttpServletRequest request = servletRequest.getRequest();
           log.info("RequestMapping:[{}]", request.getRequestURI());
           log.info("Param:[{}]", Arrays.toString(joinPoint.getArgs()));
       }
       @AfterReturning(returning = "response",pointcut = "requestLog()")
       public void doAfterReturning(Object response){
           log.info("response:[{}]",response);
           log.info("times:[{}]",System.currentTimeMillis() - startTime.get());
       }
   }
   ```

 - 3 测试类：LogController

   ```
   @RestController
   @RequestMapping("/logController")
   public class LogController {
       private Logger log = LoggerFactory.getLogger(this.getClass());
       @RequestMapping("/logTest")
       public String logTest(){
           return "logtest";
       }
   }
   ```

   

## 4.注解

	- @Aspect：将类定义为切面类
	- @Pointcut：定义切点
	- @Before：切点前执行
	- @After：切点后，return之前执行
	- @AfterReturning：在切入点，return后执行
	- @Around：环绕切点，在进入切点前、切点后执行
	- @AfterThrowing：在切点后抛出异常进行处理
	- @order(i)： 标记切点的优先级,i越小,优先级越高