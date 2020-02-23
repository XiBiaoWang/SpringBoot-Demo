# 异常全局统一处理

## 1. 基于@ControllerAdvice注解处理Controller异常

 - @ControllerAdvice 控制器增强类，当控制器发生异常且符合类中定义的异常时，将被拦截

   ```
   @ControllerAdvice
   public class ControllerExceptionHandler {
       @ExceptionHandler(Exception.class)
       @ResponseBody
       public Map<String,Object> exceptionHandler(Exception exception){
           Map<String, Object> map = new HashMap<>();
           map.put("errorCode", 500);
           map.put("errorMsg", exception.toString());
           return map;
       }
   }
   //测试类
   @GetMapping("/test")
   public String test(){
   	int i = 1/0;
       return "test";
   }
   //请求接口输出
   {"errorCode":500,"errorMsg":"java.lang.ArithmeticException: / by zero"}
   ```

 - 如果在Controller中对异常做了 try...catch...处理，则不会被拦截

   ```
   //测试类1
   @RequestMapping("/test1")
   public String test1(){
       try{
           int i = 1/0;
       }catch(Exception e){
           return "error:" + e.toString();
       }
       return "test1";
   }
   //请求接口输出
   error:java.lang.ArithmeticException: / by zero
   ```