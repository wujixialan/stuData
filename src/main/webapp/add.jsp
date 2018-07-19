<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/16
  Time: 16:10
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
    <script language="JavaScript"
            src="${rootPath}/js/date.js"></script>
</head>
<body>
<br><br>
<div class="layui-container layui-fluid">
    <form action="${rootPath}/stu?method=add" method="post" class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">name</label>
            <div class="layui-input-block">
                <input type="text" name="name" placeholder="请输入 name" autocomplete="off" class="layui-input"
                       lay-verify="name">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">birthday</label>
            <div class="layui-input-block">
                <input type="text" name="birthday" id="birthday" placeholder="请输入 birthday" autocomplete="off"
                       class="layui-input" readonly  lay-verify="required">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">description</label>
            <div class="layui-input-block">
                <input type="text" name="description" placeholder="请输入 description" autocomplete="off"
                       class="layui-input" lay-verify="description">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">avgScore</label>
            <div class="layui-input-block">
                <input type="text" name="avgScore" placeholder="请输入 avgScore" autocomplete="off"
                       class="layui-input" lay-verify="avgScore">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="add">添加</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

</div>

<script language="JavaScript">
    $(function () {
        layui.use(["form", "laydate", "layer"], function () {
            var $form = layui.form;
            var $laydate = layui.laydate;
            var $layer = layui.layer;
            //执行一个laydate实例
            $laydate.render({
                elem: '#birthday', //指定元素
                format: "yyyy-MM-dd",
                max: new Date().format("yyyy-MM-dd"),
            });

            $form.verify({
                name: function (value, item) {
                    if (value.trim() === "") {
                        return "name 不能为空"
                    }
                    if (value.trim().length < 0 || value.trim().length > 40) {
                        return "name 必须在 40 位以内";
                    }
                },

                description: function (value, item) {
                    if (value.trim() === "") {
                        return "name 不能为空"
                    }
                    if (value.trim().length < 0 || value.trim().length > 255) {
                        return "name 必须在 255 位以内";
                    }
                },

                avgScore: function (value, item) {
                    if (value.trim() === "") {
                        return "name 不能为空"
                    }
                    console.log(new RegExp(/\d+/).test(value));
                    if (!new RegExp(/\d+/).test(value)) {
                        return "avgScore 必须是数字";
                    }
                },
            });

            $form.on("submit(add)", function (data) {
                $.ajax({
                    url: data.form.action,
                    type: data.form.method,
                    data: data.field,
                    dataType: "json",
                    success: function (res) {
                        console.log(res);
                        if (res.code === 200) {
                            $layer.msg(res.msg, {
                                icon: 6,
                            });
                            window.location.href = "${rootPath}/stu?method=find&page=1&limit=10";
                        } else if (res.code === 400) {
                            $layer.msg(res.msg, {
                                icon: 5,
                            });
                        }
                    },
                    error: function (res) {
                        console.log(res);
                    }
                });
                return false;
            });
        });
    });
</script>
<script language="JavaScript"
        src="${rootPath}/webjars/layui/2.3.0/layui.all.js"></script>
</body>
</html>
