## Request-Scoped Data 

### 背景

​	总结request作用域的数据的处理方式

### 1. 通过建立Controller，@RequestBody 请求入参即为Request Scope的data。

### 2.通过Spring MVC 的 RequestContextHolder，这个类是通过ThreadLocal来实现的

### 3. 通过自定义的ThreadLocal

### 4.通过注解@RequestScope

### 5. 应用

+ 可以存放贯穿整个请求的数据。比如，业务方标识，员工信息。



*参考：*

*http://www.javabyexamples.com/request-scoped-data-with-spring-mvc/*



### 关联知识点

+ ControllerAdvice中作用范围
  + basePackages - value是包路径，作用到包路径
  + basePackageClasses - value是class，作用到这些class作用的包路径
  + assignableTypes - value可以是接口或者父类，作用到这些类
  + annotations - value是注解，作用有这些注解的Controller