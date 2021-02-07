<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 10185
  Date: 2021/2/4
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp"%>

<body>

<%@include file="/WEB-INF/include-nav.jsp"%>
<%@include file="/WEB-INF/include-sidebar.jsp"%><div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <script>
        $(function () {
            <c:if test="${! empty requestScope.exception}">
                var a = "${requestScope.exception.message}";
                layer.msg(a);
            </c:if>


        })
    </script>
    <ol class="breadcrumb">
        <li><a href="#">首页</a></li>
        <li><a href="#">数据列表</a></li>
        <li class="active">新增</li>
    </ol>
    <div class="panel panel-default">
        <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
        <div class="panel-body">
            <form role="form" >
                <div class="form-group">
                    <label for="exampleInputPassword1">登陆账号</label>
                    <input name="loginAcct" type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入登陆账号" value="${param.loginAcct}">

                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">用户名称</label>
                    <input name="userName" type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入用户名称" value="${param.userName}">

                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">用户密码</label>
                    <input name="userPswd" type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入用户密码" value="${param.userPswd}">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">邮箱地址</label>
                    <input name="email" type="email" class="form-control" id="exampleInputEmail1" placeholder="请输入邮箱地址" value="${param.email}">
                    <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                </div>
                <button type="submit" formaction="admin/save/ssm.html" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                <button type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
            </form>
        </div>
    </div>
</div>
</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题1</h4>
                    <p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题2</h4>
                    <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
                </div>
            </div>


</body>
</html>
