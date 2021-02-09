function addDiyDom(treeId,treeNode) {

    // treeId 是整个树形结构附着的ul 标签的id


    //treeNode
    /*check_Child_State: -1
check_Focus: false
checked: false
checkedOld: false
children: []
chkDisabled: false
editNameFlag: false
getCheckStatus: ƒ ()
getNextNode: ƒ ()
getParentNode: ƒ ()
getPreNode: ƒ ()
halfCheck: false
icon: "glyphicon
↵glyphicon-dashboard"
id: 2
isAjaxing: false
isFirstNode: true
isHover: false
isLastNode: false
isParent: false
level: 1
name: "控制面板"
nocheck: false
open: false
parentTId: "treeDemo_1"
pid: 1
tId: "treeDemo_2"
url: "main.htm"
zAsync: true
__proto__: Object*/
// 当前树形节点的全部的数据，包括从后端查询得到的Menu 对象的全部属性
    // zTree 生成id 的规则
// 例子：treeDemo_7_ico
// 解析：ul 标签的id_当前节点的序号_功能
// 提示：“ul 标签的id_当前节点的序号”部分可以通过访问treeNode 的tId 属性得到
// 根据id 的生成规则拼接出来span 标签的id
    var spanId = treeNode.tId + "_ico";
    //通过移除当前class,然后从数据库中拿出class
    $("#"+spanId).removeClass().addClass(treeNode.icon)



}

function myAddHoverDom(treeId,treeNode){
    /* level 0：根节点
      添加子节点
      level 1：分支节点
      修改
      添加子节点
      没有子节点：可以删除
      有子节点：不能删除
      level 2：叶子节点
      修改
      删除*/

    //获取当前level
    let level = treeNode.level;
    //添加按钮的样式
    addBtn = "<a id='"+treeNode.id+"' class='addBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    //修改的样式
    updateBtn = "<a id='"+treeNode.id+"' class='updateBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;'  title='修改权限信息'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";
    //删除的样式
    deleteBtn= "<a id='"+treeNode.id+"' class='deleteBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
    var html = "";
    if (level === 0) {
        html = addBtn;
    }
    if (level === 1){
        html = updateBtn+" "+addBtn+" ";
        if (treeNode.children.length === 0) {
            html += deleteBtn;
        }
    }
    if (level === 2) {
        html = updateBtn + " "+ deleteBtn;
    }

    //设置鼠标移进id为treeDemo_7_a的时候后面加一个span属性,如果长度为0的话就添加
    if ($("#" + treeNode.tId + "_span").nextAll("span").length === 0) {

        $("#"+treeNode.tId+"_span").after("<span>"+html+"</span>");
    }
}

function myRemoveHoverDom(treeId,treeNode) {
    //如果后面第一个是span标签,那么就删除
    $("#"+treeNode.tId+"_span").next("span").remove();
}
function getMenuPage() {
    $.ajax({
        "url": "menu/getMenuList/ssm.json",
        "type":"post",
        "dataType":"json",
        "success":function(response){
            var result = response.result;

            if(result === "SUCCESS") {
// 2.创建JSON 对象用于存储对zTree 所做的设置
                var setting = {
                    view:{
                        //用于替换class标签
                        addDiyDom: addDiyDom,
                        //用于鼠标移入name触发鼠标移入事件
                        addHoverDom:myAddHoverDom,
                        //用于鼠标移除触发鼠标移出事件
                        removeHoverDom: myRemoveHoverDom
                    },
                    data:{
                        key:{
                            url:"xiaodidi"//就从当前ztree里面寻找xiaodidi这个属性,如果找不到就不实现跳转
                        }
                    }




                };
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


}

function menuAddBtn() {
    $("#treeDemo").on("click", ".addBtn", function () {
        //打开模态框
        $("#menuAddModal").modal("show");
        //保存当前单机事件的id,以便新添加的菜单pid=id
        var id = this.id;

        //设置模态框里面的单机事件
        $("#menuSaveBtn").one("click",function () {
            //把表单里的数据变成字符串
           let data = $("#addMenuForm").serialize()+"&pid="+id;

           $.ajax({
               url:"menu/add/ssm.json",
               dataType: "json",
               type: "post",
               data:data,
               success: function (data) {
                   if (data.result === "SUCCESS") {
                       layer.msg("添加成功");

                       getMenuPage();
                   } else {
                       layer.msg("添加失败,失败原因为：" + data.message)
                   }
               },
               error: function (data) {
                   layer.msg(data.statusMessage)
               }

                //关闭模态框

           });
            $("#menuAddModal").modal("hide");
            //自己执行一次函数的单机事件,清空数据
            $("#menuResetBtn").click();


        });




        //去掉默认的超链接的行为
        return false;

    });


}
function menuUpdateBtn() {

    //给修改图表设置单机事件
    $("#treeDemo").on("click", ".updateBtn", function () {
        //取出id值
        var id =  this.id;
        //取出要进行修改的menu对象
        let zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
        //通过tid寻找
        /*let menu = zTreeObj.getNodeByTId("treeDemo_"+id);*/
        //第一个参数是key,第二个参数是value
        let menu = zTreeObj.getNodeByParam("id",id);
        //打开模态框
        $("#menuEditModal").modal("show");
        //进行回显
        $("#menuEditModal [name = name]").val(menu.name);
        $("#menuEditModal [name = url]").val(menu.url);
        //下面都可以选择到这个元素
        /*$("#menuEditModal [class = '"+menu.icon +"']").next().prop("checked","checked");*/
        /*$("#menuEditModal [value = '"+menu.icon+"']").prop("checked","checked");*/
        $("#menuEditModal :radio").val([menu.icon]);
        //设置模态框里面的单机事件
        $("#menuEditBtn").click(function () {
            //把表单里的数据变成字符串
            let data = $("#menuUpdateForm").serialize()+"&id="+menu.id;

            $.ajax({
                url:"menu/update/ssm.json",
                dataType: "json",
                type: "post",
                data:data,
                success: function (data) {
                    if (data.result === "SUCCESS") {
                        layer.msg("修改成功");

                        getMenuPage();
                    } else {
                        layer.msg("修改失败,失败原因为：" + data.message)
                    }
                },
                error: function (data) {
                    layer.msg(data.statusMessage)
                }

                //关闭模态框

            });
            $("#menuEditModal").modal("hide");
            //取消掉该单机事件,以免下次点击有两个
            $("#menuEditBtn").unbind("click");


        });

        return false;

    });

}

function menuDeleteBtn() {
    //绑定单机事件
    $("#treeDemo").on("click", ".deleteBtn", function () {
        //得到name的值
        //<span id="treeDemo_18_span">项目标签</span>
        let id = this.id;
        let name = $("#treeDemo_"+id+"_span").text();

        //打开模态框
        $("#menuConfirmModal").modal("show");
        //在模态框里添加该name
        $("#removeNodeSpan").text(name);
        //给删除按钮绑定单机事件
        $("#confirmBtn").one("click",function () {
            $.ajax({
                url:"menu/delete/ssm.json",
                dataType: "json",
                type: "post",
                data:{"id":id},
                success: function (data) {
                    if (data.result === "SUCCESS") {
                        layer.msg("修改成功");

                        getMenuPage();
                    } else {
                        layer.msg("修改失败,失败原因为：" + data.message)
                    }
                },
                error: function (data) {
                    layer.msg(data.statusMessage)
                }

                //关闭模态框

            });
            $("#menuConfirmModal").modal("hide");


        });



        return false;

    });

}
