<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management Application</title>
    <link rel="stylesheet" type="text/css" href="styles/main.css"/>
</head>
<body>
<div>
    <h1>User Management</h1>
    <h2>
        <a href="${pageContext.request.contextPath}/users?action=new">Add
            New User</a> &nbsp;&nbsp;&nbsp; <a
            href="${pageContext.request.contextPath}/users">List All Users</a>
    </h2>

    <h2>Edit User</h2>
    <form action="${pageContext.request.contextPath}/users?action=update" method="post">
        <c:if test="${errors != null}">
            <c:if test="${errors != null}">
                <p style="text-align: left; color:red;">
                    <c:out value="${errors}" escapeXml="false"></c:out>
                </p>
            </c:if>
        </c:if>
        <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
        <table>
            <tr>
                <th>User Name:</th>
                <td>
                    <input type="text" name="name" maxlength="50" size="50" value="<c:out value='${user.name}' />"/>
                </td>
            </tr>
            <tr>
                <th>Email:</th>
                <td>
                    <input type="text" name="email" maxlength="50" size="50" value="<c:out value='${user.email}' />"/>
                </td>
            </tr>
            <tr>
                <th>Gender:</th>
                <td>
                    <input type="text" name="gender" maxlength="20" size="15"
                           value="<c:out value='${user.gender}' />"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>