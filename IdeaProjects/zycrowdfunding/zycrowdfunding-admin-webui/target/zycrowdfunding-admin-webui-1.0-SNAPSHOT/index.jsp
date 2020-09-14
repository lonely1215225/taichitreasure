<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/7/20
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.servletContext.contextPath}/">
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript">
        $(function () {

            $("#sendAjax").click(function () {
                let array = [1, 2, 3, 4, 5, 6];
                let arrayJson=JSON.stringify(array);
                $.ajax({
                    url: "ajax/array",
                    data: arrayJson,
                    //traditional: true,
                    contentType: "application/json;charset=UTF-8",
                    dataType: "text",
                    type: "post",
                    success: function (response) {
                        alert(response)
                    },
                    error: function (response) {
                        alert(response)
                    }
                });
            });
        })
    </script>
</head>
<body>
<a href="admin/to/loginPage">去登录</a>
</body>
</html>
