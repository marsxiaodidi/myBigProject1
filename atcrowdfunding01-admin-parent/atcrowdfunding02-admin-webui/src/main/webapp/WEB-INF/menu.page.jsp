<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp"%>
<link rel="stylesheet" href="ztree/zTreeStyle.css"/>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script>
  $(function () {
    $.ajax({
      "url": "menu/getMenuList/ssm.json",
      "type":"post",
      "dataType":"json",
      "success":function(response){
        var result = response.result;

        if(result === "SUCCESS") {
// 2.创建JSON 对象用于存储对zTree 所做的设置
          var setting = {};
// 3.从响应体中获取用来生成树形结构的JSON 数据
          var zNodes = response.data;
// 4.初始化树形结构
          $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        }
        if(result === "FAILED") {
          layer.msg(response.message);
        }
      }
    });

  });
</script>

  <body>

  <%@include file="/WEB-INF/include-nav.jsp"%>
  <%@include file="/WEB-INF/include-sidebar.jsp"%>
  <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <div class="panel panel-default">
      <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表 <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
      <div class="panel-body">
        <ul id="treeDemo" class="ztree">
          <%-- 显示树形结构依附于上面的ul --%>
        </ul>
      </div>
    </div>
  </div>
  </div>
  </div>



  </body>
  </html>

