<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/5/8
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Welcome Back ${user}</h1><br>
<h1>Welcome Back ${user1}</h1><br>
<h1>请求体内容为:${body}</h1><br>
<h1>JESSIONID:${jid}</h1>
<h1>Session中 ${sessionScope.user1}</h1><br>
<h1>Session中 ${sessionScope.user}</h1><br>

</body>
</html>
