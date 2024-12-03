<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Главная страница</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 100px;
        }
        h1 {
            font-size: 36px;
            margin-bottom: 20px;
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
<h1>Привет!</h1>
<button onclick="location.href='admin'">Панель администратора</button>
<button onclick="location.href='user'">Панель пользователя</button>
<button onclick="location.href='login'">Авторизация</button>
</body>
</html>