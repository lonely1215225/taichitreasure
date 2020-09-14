<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
        </div>
        <div class="panel-body">
            <form class="form-inline" role="form" style="float:left;" action="admin/get/page" method="post">
                <div class="form-group has-feedback">
                    <div class="input-group">
                        <div class="input-group-addon">查询条件</div>
                        <input name="keyWord" class="form-control has-success" value="${param.keyWord}" type="text" placeholder="请输入查询条件">
                    </div>
                </div>
                <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
            </form>
            <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                    class=" glyphicon glyphicon-remove"></i> 删除
            </button>
            <button type="button" class="btn btn-primary" style="float:right;"
                    onclick="window.location.href='admin/to/addPage'"><i class="glyphicon glyphicon-plus"></i> 新增
            </button>
            <br>
            <hr style="clear:both;">
            <div class="table-responsive">
                <table class="table  table-bordered">
                    <thead>
                    <tr>
                        <th width="30">#</th>
                        <th width="30"><input type="checkbox"></th>
                        <th>账号</th>
                        <th>名称</th>
                        <th>邮箱地址</th>
                        <th width="100">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${empty requestScope.pageInfo.list}">
                        <tr>
                            <td colspan="6" align="center">抱歉！没有查询到您要的数据！</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty requestScope.pageInfo.list}">
                        <c:forEach items="${requestScope.pageInfo.list}" var="admin" varStatus="myStatus">

                            <tr>
                                <td>${myStatus.count}</td>
                                <td><input type="checkbox"></td>
                                <td>${admin.loginAcct}</td>
                                <td>${admin.userName}</td>
                                <td>${admin.email}</td>
                                <td>
                                    <a href="assign/to/assign/role/page?adminId=${admin.id}&pageNum=${requestScope.pageInfo.pageNum}&keyWord=${param.keyWord}" class="btn btn-success btn-xs"><i
                                            class=" glyphicon glyphicon-check"></i></a>
                                    <a href="admin/to/editPage?adminId=${admin.id}&pageNum=${requestScope.pageInfo.pageNum}&keyWord=${param.keyWord}" class="btn btn-primary btn-xs"><i
                                            class=" glyphicon glyphicon-pencil"></i></a>
                                <c:if test="${sessionScope.loginAdmin.id ne admin.id}">
                                    <button type="button" onclick="remove(${admin.id})" class="btn btn-danger btn-xs"><i
                                            class=" glyphicon glyphicon-remove"></i></button>
                                </c:if>
                                    <c:if test="${sessionScope.loginAdmin.id eq admin.id}">
                                        <button type="button" class="btn btn-warning btn-xs">你</button>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="6" align="center">
                            <ul class="pagination">
                                <c:if test="${pageInfo.hasPreviousPage}">
                                    <li><a href="admin/get/page?pageNum=1&keyWord=${param.keyWord}">首</a></li>
<%--                                    <a href="admin/get/page?pageNum=${pageInfo.prePage}&keyWord=${param.keyWord}">上一页</a>--%>
                                    <li><a href="admin/get/page?pageNum=${pageInfo.prePage}&keyWord=${param.keyWord}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a></li>
                                </c:if>

                                <c:forEach items="${pageInfo.navigatepageNums}" var="page">
                                    <c:if test="${pageInfo.pageNum eq page}">
                                        <li class="active"><a href="admin/get/page?pageNum=${page}&keyWord=${param.keyWord}">${page}<span class="sr-only">(current)</span></a></li>
                                    </c:if>
                                    <c:if test="${pageInfo.pageNum ne page}">
                                        <li ><a href="admin/get/page?pageNum=${page}&keyWord=${param.keyWord}">${page}</a></li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${pageInfo.hasNextPage}">
<%--                                        href="admin/get/page?pageNum=${pageInfo.nextPage}&keyWord=${param.keyWord}">下一页</a>--%>
                                    <li> <a href="admin/get/page?pageNum=${pageInfo.nextPage}&keyWord=${param.keyWord}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a></li>
                                    <li><a href="admin/get/page?pageNum=${pageInfo.pages}&keyWord=${param.keyWord}">尾</a></li>
                                </c:if>
                            </ul>
                        </td>
                    </tr>

                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function remove(adminId){
        $(function () {
            layer.confirm('确定要删除？', {
                btn: ['YES','NO'] //按钮
            }, function(){
                $.ajax({
                    url: "admin/remove",
                    data: "adminId="+adminId,
                    type: "post",
                    success: function () {
                        window.location.reload();
                    },
                    error: function (response) {
                        alert(response)
                    }
                })
            }, function(){
                layer.msg('删除已撤回', {
                    time: 1000, //1s后自动关闭
                    btn: ['明白了', '知道了']
                });
            });
        })
    }
</script>
</html>