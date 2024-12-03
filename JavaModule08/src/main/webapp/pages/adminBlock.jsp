<%@ page import="org.website.entity.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Вы пытаетесь пройти на страницу администратора без нужной авторизации</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
            margin: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h1>Вы пытаетесь пройти на страницу администратора без нужной авторизации</h1>
<button onclick="location.href='index.jsp'">На главную</button>
<form action="adminBlock" method="post" style="display:inline;">
    <button type="submit">Разлогиниться</button>
</form>
</body>
</html>
