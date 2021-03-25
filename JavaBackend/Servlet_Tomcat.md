## 1. Servlet

### 1.1. Forward 和Redirect

+ `Redirect`需要浏览器再次请求

  间接转发，也叫重定向，用于避免用户的非正常访问，比如没有登陆的情况下访问后台资源，`Servlet`将请求重定向到登陆页面。

  ```java
  //Servlet中处理get请求的方法
  public void doGet(HttpServletRequest request,HttpServletResponse response){
  //请求重定向到另外的资源
      response.sendRedirect("资源的URL");
  }
  ```

+ `Forward`

  直接转发，MVC模式，由控制器控制请求转发给哪个资源，然后由具体资源处理请求。

  ```java
  //Servlet里处理get请求的方法
  public void doGet(HttpServletRequest request , HttpServletResponse response){
       //获取请求转发器对象，该转发器的指向通过getRequestDisPatcher()的参数设置
     RequestDispatcher requestDispatcher =request.getRequestDispatcher("资源的URL");
      //调用forward()方法，转发请求      
     requestDispatcher.forward(request,response);    
  }
  ```

  

## 2. Tomcat

