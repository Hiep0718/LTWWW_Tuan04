<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management</title>
    <link rel="stylesheet" type="text/css" href="styles/main.css" />
</head>
<body>
<div>
    <h1>User Management</h1>
    <h2>
        <a href="${pageContext.request.contextPath}/users?action=new">Add New User</a>
        <a href="${pageContext.request.contextPath}/users">List All Users</a>
    </h2>
    <h2>List Users</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.gender}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/users?action=edit&id=<c:out value='${user.id}' />">Edit</a>
                    <a href="${pageContext.request.contextPath}/users?action=delete&id=<c:out value='${user.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>