<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        这是文件上传页面
        <form action="http://localhost:8080/springMVC-demo02/fileUpload" method="post" encType="multipart/form-data">
            用户名：<input type="text" name="username" /><br/>
            上传文件：<input type="file" name="photo"><br/>
            <input type="submit" value="上传"/>
        </form>
</body>
</html>