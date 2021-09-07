<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>

<html>
<head>

    <title>Title</title>
    <script type="text/javascript" src="js/jquery.min.js"></script>

    <%
        pageContext.setAttribute("ctp",request.getContextPath());
    %>

    <style type="text/css">
        div{
            border: 1px solid #BBB;
            float: left;
            margin-left: 20px;
            width: 200px;
            height: 180px;
        }
    </style>

</head>
<body>
<div>
div1:<%=new Date()%>
</div>
<div>
div2:<%=new Date()%>
</div>
<div >
div3:<%=new Date()%>
</div>
<br/>

<hr/>

<a href="ajaxServlet" id="aBtn">获取学生数据</a>

<a href="ajaxServlet" id="aBtnAjax">获取学生数据-使用底层的Ajax方式</a>

</body>

<script type="text/javascript">
    //通过ajax进行数据的请求


    //Ajax
    $("#aBtnAjax").click(function(){
    //发送ajax请求
    //所有请求的属性参数都是可以通过这个js对象定义的
    //创建一个js对象
        var options={
            url: "${ctp}/ajaxServlet",  //规定的请求地址
            type: "GET",   //请求的方式
            data: {"lastName":"haha",age:22},  //发送的数据
            async: true,  //默认值是true表示请求异步
            success: function(data){
                //将收到的数据放在第三个div中
                var lastName=data.lastName;
                var age=data.age;

                $("div:eq(2)").append("姓名："+lastName+"<br/>年龄："+age);
                $("div:eq(2)").css("background-color","#bbb");
                alert("成功！"+data);
            },
            error: function(a,b){
                alert("请求失败了"+b+"状态码："+a.status);
            },
            dataType: "json"
        };
        $.ajax(options);

        //异步，直接就执行下面的语句
        alert("haha");

        return false;//禁用默认行为
    });

</script>

</html>
