<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 4/23/2024
  Time: 9:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chỉnh sửa thông tin</title>
</head>
<body>
<h1>Chỉnh sửa công viêc</h1>
<form action="/todo" method="post">
    <input type="text" name="id" value="${todo.id}" readonly/>
    <br>
    <input type="text" name="title" value="${todo.title}">
    <br>
    <textarea name="content"  cols="30" rows="10">${todo.content}</textarea>
    <br>
    <select name="status">
        <option ${todo.status?"selected":""}  value="true">Đã hoàn thanh</option>
        <option ${!todo.status?"selected":""}  value="false">Đang tiến hanh</option>
    </select>
    <br>
    <input type="submit" name="action" value="UPDATE">
</form>
</body>
</html>