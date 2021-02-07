<%--
  Created by IntelliJ IDEA.
  User: 10185
  Date: 2021/2/4
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp"%>

  <body>

  <%@include file="/WEB-INF/include-nav.jsp"%>
  <%@include file="/WEB-INF/include-sidebar.jsp"%>
  <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <ol class="breadcrumb">
      <li><a href="#">首页</a></li>
      <li><a href="#">数据列表</a></li>
      <li class="active">修改</li>
    </ol>
    <div class="panel panel-default">
      <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
      <div class="panel-body">
        <form role="form" method="post">
          <div class="form-group">z
            <label for="exampleInputPassword1">用户名称</label>
            <input name="userName" type="text" class="form-control" id="exampleInputPassword1" value="${requestScope.admin.userName}">
          </div>
          <div class="form-group">
            <label for="exampleInputPassword1">用户密码</label>
            <input name="userPswd" type="text" class="form-control" id="exampleInputPassword1" value="${requestScope.admin.userPswd}">

          </div>
          <div class="form-group">
            <label for="exampleInputEmail1">邮箱地址</label>
            <input name="email" type="email" class="form-control" id="exampleInputEmail1" value="${requestScope.admin.email}">
            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
          </div>
          <button type="submit" formaction="admin/update/${requestScope.admin.id}/${requestScope.pageNum1}/${requestScope.pageCard1}.html" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改</button>
          <button type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
        </form>
      </div>
    </div>
  </div>





  </body>





</html>
