<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>

    <%--
        base 标签必须写在 head 标签内部
        base 标签必须在所有“带具体路径”的标签的前面
        serverName 部分 EL 表达式和 serverPort 部分 EL 表达式之间必须写“:”
        serverPort 部分 EL 表达式和 contextPath 部分 EL 表达式之间绝对不能写“/”
        原因：contextPath 部分 EL 表达式本身就是“/”开头
        如果多写一个“/”会干扰 Cookie 的工作机制
        serverPort 部分 EL 表达式后面必须写“/”
    --%>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">

    <script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="layer/layer.js"></script>

    <script>
        $(function () {
            $("#btn1").click(function () {
                var response = [1,2,3];
                var response1 = JSON.stringify(response);
                $.ajax({

                    url:"test2/ssm.json",
                    type: "post",
                    data: response1,
                    contentType:"application/json;charset=UTF-8",
                    dataType:"json",
                    success:function (data) {
                        console.log(data)
                    },
                    error:function (data) {
                        console.log(data)
                    }
                })

            });

        });


    </script>
</head>
<body>
<h2>Hello World!</h2>
<a href="login/ssm.html">登入</a>



<button id="btn1">点击进入ajax测试1</button>
<button onclick="location.href='admin/to/login/page.html'">点击进入ajax测试2</button>
<button onclick="layer.msg('我是一个msg')">点击实验layer</button>
<form role="form" id="myform">
    <input type="text" name="textresult">

</form>
<div id="myform1"></div>
<button id="btn11">点击进行提交</button>
<script>
    $(function () {
        $("#btn11").click(function () {
            $("#myform1").empty();


        let val = $("#myform [name = textresult]").val();


    $.ajax({

        url:"admin/get24/ssm.html",
        type: "post",
        data: {"text":val},

        success:function (data) {
            $("#myform1").append("<h1>"+data+"</h1>")
        }})

    })
    })

</script>





</body>
</html>
