<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="UTF-8">
<%@include file="include/include-head.jsp" %>
<body>
<%@include file="include/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="include/include-sidebar.jsp" %>

    </div>
</div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <ol class="breadcrumb">
        <li><a href="admin/to/mainPage">首页</a></li>
        <li><a href="admin/get/page">数据列表</a></li>
        <li class="active">新增</li>
    </ol>
    <div class="panel panel-default">
        <div class="panel-heading">
            表单数据
            <div style="float: right; cursor: pointer;" data-toggle="modal"
                 data-target="#myModal">
                <i class="glyphicon glyphicon-question-sign"></i>
            </div>
        </div>
        <div class="panel-body">
            <form action="admin/do/save" method="post" role="form">
                <p style="color: red;font-family: 楷体">${requestScope.exception.message}</p>
                <div class="form-group">
                    <label for="InputAcct">登录账号</label>
                    <input
                            name="loginAcct"
                            type="text" class="form-control" id="InputAcct"
                            placeholder="请输入登录账号">
                </div>
                <div class="form-group">
                    <label for="exampleInputAcct">登录密码</label>
                    <input
                            name="userPswd"
                            type="text" class="form-control" id="exampleInputAcct"
                            placeholder="请输入登录密码">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword">用户昵称</label>
                    <input
                            name="userName"
                            type="text" class="form-control" id="exampleInputPassword"
                            placeholder="请输入用户名称">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">邮箱地址</label>
                    <input type="email"
                           name="email"
                           class="form-control" id="exampleInputEmail1"
                           placeholder="请输入邮箱地址">
                    <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为：
                        xxxx@xxxx.com</p>
                </div>
                <button type="submit" class="btn btn-success">
                    <i class="glyphicon glyphicon-plus"></i> 新增
                </button>
                <button type="reset" class="btn btn-danger">
                    <i class="glyphicon glyphicon-refresh"></i> 重置
                </button>
            </form>
        </div>
    </div>
</div>
<%--<script>
    $(function () {
        $("#InputAcct").blur(function () {
            let val=$(this).val();
            alert(val);
        })
    })
</script>--%>
</body>
</html>