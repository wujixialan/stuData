<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/16
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:set var="rootPath" value="<%=request.getContextPath()%>"></c:set>
    <link rel="stylesheet" href="<c:out value="${rootPath}"></c:out>/webjars/layui/2.3.0/css/layui.css">
    <script language="JavaScript"
            src="<c:out value="${rootPath}"></c:out>/webjars/jquery/3.3.1/dist/jquery.js"></script>
    <script language="JavaScript"
            src="<c:out value="${rootPath}"></c:out>/webjars/layui/2.3.0/layui.all.js"></script>
</head>
<body>
<br>
<div class="layui-container layui-fluid">
    <div>
        <a href="<c:out value='${rootPath}'></c:out>/add.jsp" class="layui-btn layui-btn-primary layui-icon layui-icon-add-circle">添加</a>
        <a href="<c:out value='${rootPath}'></c:out>/stu?method=find&page=1&limit=10"
           class="layui-btn layui-btn-primary layui-icon layui-icon-list">数据展示</a>
    </div>
</div>




</body>
</html>
