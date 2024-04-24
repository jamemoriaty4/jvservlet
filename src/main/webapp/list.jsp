<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 4/23/2024
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sach viec can lam</title>
</head>
<body>
<h1>Dah sach viec lam</h1>
<a href="/todo?action=ADD"> Thêm mới</a>
<table border="10" cellpadding="10" cellspacing="10">
    <thead>
    <tr>
        <th>STT</th>
        <th>Title</th>
        <th>Content</th>
        <th>Status</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="todo" varStatus="loop">
        <tr>
            <td>${loop.count}</td>
            <td>${todo.title}</td>
            <td>${todo.content}</td>
            <td>${todo.status?"Đã hoàn thành":"Đang tiến hành"}</td>
            <td><a href="/todo?action=EDIT&id=${todo.id}">Edit</a></td>
            <td><a href="/todo?action=DELETE&id=${todo.id}" onclick="return confirm('Bạn có chắc chăn muốn xóa ko')">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>