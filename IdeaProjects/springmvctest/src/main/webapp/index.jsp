<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn").click(function () {
                alert("hello");
                $.ajax({
                    url:"jsonTest",
                    data:'{"name":"张卓悦","id":"12"}',
                    type:"post",
                    contentType:"application/json",
                    success:function (data) {
                        alert("success"+data);
                    }
                });
            });
        })
    </script>
</head>
<body>
<h2>Hello World!</h2>
<a href="hello/10/小姐姐/2020-5-20">hello</a>
<form action="jsonTest" method="post">
    <input type="text" name="id">
    <input type="text" name="name">
    <input type="date" name="date"><br>
    <input id="btn" type="button" value="提交">
</form>

</body>
</html>
