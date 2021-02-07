function getPage() {
    //获取pageInfo对象
    var pageInfo = getPageInfo();
    initPage(pageInfo);

}

function getPageInfo() {
    var resultAjax = $.ajax({
        url: "role/search/ssm.json",
        data: {
            "pageNum": window.pageNum,
            "pageSize": window.pageSize,
            "keyCard": window.keyCard
        },
        dataType: "json",
        async: false,
        type: "post"


    });


//如果遇到其他错误没有回传数据
    if (resultAjax.status !== 200) {
        layer.msg("失败!响应状态码" + resultAjax.statusCode() + "错误信息" + resultAjax.statusText);
        return null;

    }
    //状态码为200
    let resultEntity = resultAjax.responseJSON;
    if (resultEntity.result === "SUCCESS") {
        return resultEntity.data;
    } else {
        console.log(resultEntity.message);
        return null;
    }

}

//进行页面初始化
function initPage(pageInfo) {
    $("#rolePage").empty();
    if (pageInfo && pageInfo.list && pageInfo.list.length !== 0) {


        pageInfo.list.forEach(function (value, index, array) {
            $("#rolePage").append("<tr>\n" +
                "              <td>" + (index + 1) + "</td>\n" +
                "              <td><input type=\"checkbox\"></td>\n" +
                "              <td>" + value.name + "</td>\n" +
                "              <td>\n" +
                "                <button id=" + value.id + " type=\"button\" class=\"btn btn-success btn-xs\"><i class=\" glyphicon glyphicon-check\"></i></button>\n" +
                "                <button id=" + value.id + " type=\"button\" class=\"btn btn-primary btn-xs update\"><i class=\" glyphicon glyphicon-pencil\"></i></button>\n" +
                "                <button id=" + value.id + " type=\"button\" class=\"btn btn-danger btn-xs delete\"><i class=\" glyphicon glyphicon-remove\"></i></button>\n" +
                "              </td>\n" +
                "            </tr>")
        });
    } else {
        $("#rolePage").append("没有数据")
    }
    //初始话分页
    initPagination(pageInfo);


}


function initPagination(pageInfo) {
    //获取分页数据中的总记录数
    var totalRecord = pageInfo.total;

    //声明Pagination设置属性的JSON对象
    var properties = {
        num_edge_entries: 3,                                //边缘页数
        num_display_entries: 5,                             //主体页数
        callback: pageSelectCallback,                       //点击各种翻页反扭时触发的回调函数（执行翻页操作）
        current_page: pageInfo.pageNum - 1, //当前页码
        prev_text: "上一页",                                 //在对应上一页操作的按钮上的文本
        next_text: "下一页",                                 //在对应下一页操作的按钮上的文本
        items_per_page: pageInfo.pageSize  //每页显示的数量
    };

    $("#Pagination").pagination(totalRecord, properties);
}

function pageSelectCallback(pageIndex, jQuery) {
    // pageIndex是当前页码的索引，因此比pageNum小1
    window.pageNum = pageIndex + 1;

    // 执行页面跳转
    getPage();

    // 取消当前超链接的默认行为
    return false;
}

function deleteRole() {
    //设置点击最上面勾全选/全不选
    setCheckbox();
    //设置多例删除
    setDeleteMany();

}

function setCheckbox() {
    $("#checkboxAll").click(function () {
        $("#rolePage :checkbox").prop("checked", this.checked)


    });
    //设置如果每一个checkbox都打勾,最上面的那个也需要打勾
    $("#rolePage").on("click", ":checkbox", function () {
        //所有checkbox标签里面的都选择了checked,那么就把上面最大的click都打上勾

        if ($("#rolePage :checkbox:checked ").length === $("#rolePage :checkbox").length) {
            $("#checkboxAll").prop("checked", true);
        } else {
            $("#checkboxAll").prop("checked", false);
        }
    });
    //可以改成
    /*$("#checkboxAll").prop("checked",$("#rolePage :checkbox:checked").length===$("#rolePage :checkbox").length)*/


}

function setDeleteMany() {

    //绑定单机事件
    $("#deleteMany").click(function () {
        var roles = [];
        $("#deleteModal").modal("show");
        //清空原先的所有数据
        $("#location").empty();
        //获取所有被选中的id
        $("#rolePage :checkbox:checked").each(function () {
            var id =$(this).parent().next().next().children(".delete").attr("id");
            var name = $(this).parent().next().text();
            roles.push({"name": name, "id": id})
        });
        roles.forEach(function (value, index, array) {
            $("#location").append("<tr>\n" +
                "                                <td>\n" +
                "                                    "+value.name+"\n" +
                "                                </td>\n" +
                "                            </tr>")
        });
        $("#deleteRoleBtn").one("click",function () {
            var attr = [];
            roles.forEach(function (value, index,array) {
                attr.push(value.id);
            });
            let s = JSON.stringify(attr);
            $.ajax({
                url: "role/deleteMany/ssm.json",
                data: s,
                type: "post",
                dataType: "json",
                contentType:"application/json;charset=UTF-8",
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
            $("#deleteModal").modal("hide");


        });



    });
}
