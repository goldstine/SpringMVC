<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
        这是登陆页面
        <form action="http://localhost:8080/springMVC-demo02/loginServlet" method="get">
            用户名：<input type="text" name="username"><br/>
            密码：<input type="password" name="password"><br/>
            <input type="submit"/>
        </form>
</body>
</html>