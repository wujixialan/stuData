<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/18
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:set var="rootPath" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${rootPath}/webjars/layui/2.3.0/css/layui.css">
    <script language="JavaScript"
            src="${rootPath}/webjars/jquery/3.3.1/dist/jquery.js"></script>
</head>
<body>
<br>
<a class="layui-btn layui-btn-sm layui-btn-radius layui-icon layui-icon-add-circle layui-col-lg-offset1"
   href="${rootPath}/add.jsp">添加</a>
<br><br>
<table id="student" lay-filter="student" border="1" width="100%">
    <thead>
    <tr>
        <th style="width: 150px;">ID</th>
        <th>姓名</th>
        <th>出生年月</th>
        <th>备注</th>
        <th>平均分</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="ele" items="${stu}">
        <tr style="height: 60px; width: 150px; text-align: center;">
            <input type="hidden" name="id" id="id" value="${ele.id}">
            <td>${ele.id}</td>
            <td>${ele.name}</td>
            <td>${ele.birthday}</td>
            <td>${ele.description}</td>
            <td>${ele.avgScore}</td>
            <td>
                <a class="layui-btn layui-btn-xs layui-btn-radius layui-icon layui-icon-edit"
                   lay-event="edit" editId="${ele.id}">
                    编辑</a>

                <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius layui-icon layui-icon-delete"
                   lay-event="del" delId="${ele.id}">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
<input type="hidden" id="total" name="total" value="${total}">
<div id="page"></div>

<script language="JavaScript">
    $(function () {
        layui.use(["form", "laydate", "layer", "laypage"], function () {
            var $form = layui.form;
            var $laydate = layui.laydate;
            var $layer = layui.layer;
            var $laypage = layui.laypage;
            console.log($("#total").prop("total"));
            $laypage.render({
                elem: 'page', //注意，这里的 test1 是 ID，不用加 # 号
                count: $("#total").val(), //数据总数，从服务端得到
                limit: 1,
                prev: "上一页",
                next: "下一页",
                first: "1",
                jump: function(obj, first){
                    //obj包含了当前分页的所有参数，比如：
                    console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                    console.log(obj.limit); //得到每页显示的条数

                    //首次不执行
                    if(!first){
                        //do something
                    }

                    $()

                }
            });
            $("a[lay-event='edit']").click(function () {
                window.location.href = "${rootPath}/stu?method=edit&id=" + $(this).attr("editId");
            });

            $("a[lay-event='del']").click(function () {
                var thisA = this;
                var delId = $(thisA).attr("delId");
                $layer.confirm("确认删除 " + delId + " 的记录吗？",
                    {
                        icon: 3,
                    },
                    function (res) {
                        $.ajax({
                            url: "${rootPath}/stu?method=delete",
                            type: "post",
                            data: {
                                id: delId,
                            },
                            dataType: "json",
                            success: function (res) {
                                if (res.code === 200) {
                                    $layer.msg(res.msg, {
                                        icon: 6,
                                    });
                                    window.location.href = "${rootPath}/stu?method=find&page=1&limit=10";
                                }else if (res.code === 400) {
                                    $layer.msg(res.msg, {
                                        icon: 5,
                                    });
                                }
                            }
                        });
                        $layer.close(res);
                    }
                );

            });
        });

    });
</script>


<script language="JavaScript"
        src="${rootPath}/webjars/layui/2.3.0/layui.all.js"></script>
</body>
</html>
