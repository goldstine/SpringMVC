<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<html>
<head>

    <title>Title</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/success">success.jsp</a>

    <%
        System.out.println("target页面执行了....");
        System.out.println("目标资源的的执行线程为："+Thread.currentThread().getName());
        System.out.println("target.jsp:"+request.getParameter("username"));
    %>
<h2>Hello World!</h2>
asasas
</body>
</html>
