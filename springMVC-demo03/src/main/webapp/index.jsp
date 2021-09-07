<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>

<html>
<head>

    <title>Title</title>
    <script type="text/javascript" src="js/jquery.min.js"></script>

    <%
        pageContext.setAttribute("ctp",request.getContextPath());
    %>
</head>
<body>
<script type="text/javascript">
    //json的要求是和js对象一样的，只不过key必须是字符串
    //js对象在生命的时候key是否加双引号是可以选择的

    //var student={lastName:"张三",age:18,car:{pp:"宝马",price:"3000$"},infos:[{bookName:"西游记",price:98.98},18,true]};
    //alert(student.car.pp);
    //alert(student.infos[2]);
    //alert(student);
    //var strJson=JSON.stringify(student);
    //JSON(js内置的对象)，将js对象转为json字符串
    //alert(strJson);

    //将json字符串转成js对象
    //var stu02=JSON.parse(strJson);

    //获取对应的js对象的值
   // alert(stu02.infos[0].bookName);


</script>
<h2>Hello World!</h2>
asasas
<a href="hello">给我一些数据</a>


<%=new Date()%>

<a href="getinfo" id="aBtn">获取信息</a>
<div id="haha">
    显示信息：${msg}
</div>
</body>

<script type="text/javascript">
    //通过ajax进行数据的请求
    //1、$.get
    $("#aBtn").click(function(){
        //$.get(url,[data],[callback],[type]),中括号代表可选字段参数
        //data:传递的数据："k=v&k=v"  传递一个js对象,会自动转为k=v&k=v的形式
        //callback：定义了一个回调函数，随便定义一个参数，这个参数就封装了服务器返回的数据
        //[type]:返回的数据，可以不写，自动判断  ，xml,html,script,json,text,_default
        $.get("${ctp}/getinfo",{lastName:"张三",age:23},function(abc){
            //alert(abc);
            $("#haha").append(abc);
        },"text");
        return false;
    });

</script>

</html>
