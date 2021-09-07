<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
        <%
            System.out.println("liulei");
            Object user=session.getAttribute("user");
            if(user==null){
                request.getRequestDispatcher("/admin/login.jsp").forward(request,response);
                return;
            }
        %>
        我是a.jsp文件
</body>
</html>