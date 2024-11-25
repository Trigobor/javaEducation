<%@ page import="org.website.entity.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Панель Пользователя</title>
</head>
<body>
<%  User user = (User) request.getAttribute("user");
    String userName = (user != null) ? user.getName() : "";
%>
<h1>Привет пользователь <%= userName %>!</h1>
<form action="saveUserName" method="post">
    <label for="name">Введите имя пользователя:</label>
    <input type="text" id="name" name="name" required><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
