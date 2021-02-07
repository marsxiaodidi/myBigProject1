<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp"%>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript" src="script/role.js"></script>
<link href="css/pagination.css" rel="stylesheet" />

  <body>

  <%@include file="/WEB-INF/include-nav.jsp"%>
  <%@include file="/WEB-INF/include-sidebar.jsp"%>
  <script>
    $(function () {
      $("#btn1").click(function () {
        window.keyCard = $("#search").val();
        getPage();


      });
      $("#btn2").click(function () {
        //设置手动打开隐藏页
        $("#addModal").modal("show");
      });
      //给保存按钮设施点击事件
      $("#saveRoleBtn").click(function () {
        //获取name = roleName的值

        let val = $("#addModal [name=roleName]").val();
        $.ajax({
          url: "role/save/ssm.json",
          data: {"name": val},
          type: "post",
          dataType: "json",
          success: function (data) {
            if (data.result === "SUCCESS") {
              layer.msg("保存成功");

              window.pageNum = 99999;
              getPage();
            } else {
              layer.msg("保存失败,失败原因为" + data.message)
            }
          },
          error: function (data) {
            layer.msg(data.statusMessage)
          }

        });
        //手动关闭隐藏页
        $("#addModal").modal("hide");
        //把值设置为空
        $("#addModal [name=roleName]").val("");
      });
      //开始进行分页操作
      window.pageNum = 1;
      window.pageSize = 5;
      window.keyCard = "";
      //主函数
      getPage();

      //进行修改操作的配置
      $(function () {
        //第一个参数是
        $("#rolePage").on("click", ".update", function () {
          $("#updateModal").modal("show");
          //this表示正在调用这个对象的标签
          let name = $(this).parent().prev().text();
          $("#updateModal [name = roleName]").val(name);
          window.roleId = $(this).attr("id");
        });
          $("#updateRoleBtn").click(function () {
            //获取name = roleName的值

            let val = $("#updateModal [name=roleName]").val();
            $.ajax({
              url: "role/update/ssm.json",
              data: {"name": val, "id": window.roleId},
              type: "post",
              dataType: "json",
              success: function (data) {
                if (data.result === "SUCCESS") {
                  layer.msg("修改成功");

                  getPage();
                } else {
                  layer.msg("修改失败,失败原因为：" + data.message)
                }
              },
              error: function (data) {
                layer.msg(data.statusMessage)
              }


            });
            //手动关闭隐藏页

            $("#updateModal").modal("hide");



        });

        });
      });
    $(function () {
      $("#rolePage").on("click",".delete", function () {
        var data = this;
        let name = $(this).parent().prev().text();
        layer.confirm('你确定要删除'+name+'吗?',  function(){
          let id = data.id;

          $.ajax({
            url: "role/delete/ssm.json",
            data: {"id": id},
            type: "post",
            dataType: "json",
            success: function (data) {
              if (data.result === "SUCCESS") {
                layer.msg("删除成功");

                getPage();
              } else {
                layer.msg("删除失败,失败原因为：" + data.message)
              }
            },
            error: function (data) {
              layer.msg(data.statusMessage)
            }

          });
        });
          layer.close(index);
        });

      });


    $(function () {
      deleteRole();

    });


  </script>
  <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
      </div>
      <div class="panel-body">
        <form class="form-inline" role="form" style="float:left;">
          <div class="form-group has-feedback">
            <div class="input-group">
              <div class="input-group-addon">查询条件</div>
              <input id="search" class="form-control has-success" type="text" placeholder="请输入查询条件">
            </div>
          </div>
          <button id="btn1" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
        </form>
        <button type="button" class="btn btn-danger" id="deleteMany" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
        <button id="btn2" type="button" class="btn btn-primary" style="float:right;" ><i class="glyphicon glyphicon-plus"></i> 新增</button>
        <br>
        <hr style="clear:both;">
        <div class="table-responsive">
          <table class="table  table-bordered">
            <thead>
            <tr>
              <th width="30">#</th>
              <th width="30"><input id="checkboxAll" type="checkbox"></th>
              <th>名称</th>
              <th width="100">操作</th>
            </tr>
            </thead>
            <tbody id="rolePage">

            </tbody>
            <tfoot>
            <tr>
              <td colspan="6" align="center">
                <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
              </td>
            </tr>

            </tfoot>
          </table>
        </div>
      </div>
    </div>
  </div>
  <%@include file="/WEB-INF/model-role-add.jsp" %>
  <%@include file="/WEB-INF/model-role-update.jsp" %>
  <%@include file="/WEB-INF/model-role-delete.jsp" %>
  </body>
</html>
