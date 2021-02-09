<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      <li class="active">分配角色</li>
    </ol>
    <div class="panel panel-default">
      <div class="panel-body">
        <form method="post" role="form" class="form-inline" id="assignForm" action="assign/assignSaveAdmin/${param.adminId}/${param.pageNum}/${param.keyCard}.html">
        <div class="form-group">
            <label for="exampleInputPassword1">未分配角色列表</label><br>
            <select class="form-control" multiple="multiple" size="10" style="width:100px;overflow-y:auto;" id="leftSelect">

              <c:forEach items="${requestScope.notAssignRole}" var="role">
                <option value="${role.id}">${role.name}</option>


              </c:forEach>


            </select>
          </div>
          <div class="form-group">
            <ul>
              <li class="btn btn-default glyphicon glyphicon-chevron-right"></li>
              <br>
              <li class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
            </ul>
          </div>
          <script>
            $(function () {

              //为两个按钮绑定单机事件
              $(".glyphicon-chevron-right").click(function () {
                $("#leftSelect option:selected").appendTo("#rightSelect");

              });
              $(".glyphicon-chevron-left").click(function () {
                $("#rightSelect option:selected").appendTo("#leftSelect");

              });
              //给保存按钮添加单机事件
              $("#submitBtn").click(function () {
                //获取右边的选中框中所有的元素
                $("#rightSelect option").prop("selected","selected");




              });

            });

          </script>
          <div class="form-group" style="margin-left:40px;">
            <label for="exampleInputPassword1">已分配角色列表</label><br>
            <select class="form-control" multiple="multiple" size="10" style="width:100px;overflow-y:auto;" id="rightSelect" name="ids">
              <c:forEach items="${requestScope.alreadyAssignRole}" var="role">
                <option value="${role.id}">${role.name}</option>
              </c:forEach>
            </select>
            <div style="margin-left: 30px">
              <button id="submitBtn" type="submit" class="btn btn-sm btn-success btn-block">提交</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  </body>
  </html>
