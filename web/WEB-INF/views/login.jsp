<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Вход</title>
        <style>
            .login-page {
                width: 360px;
                padding: 8% 0 0;
                margin: auto;
            }
            .form {
                position: relative;
                z-index: 1;
                background: #FFFFFF;
                max-width: 360px;
                margin: 0 auto 100px;
                padding: 45px;
                text-align: center;
                box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
            }
            .form input {
                font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
                outline: 0;
                background: #f2f2f2;
                /*width: 100%;*/
                border: 0;
                margin: 0 0 15px;
                padding: 15px;
                box-sizing: border-box;
                font-size: 14px;
            }

            .form button {
                font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;

                text-transform: uppercase;
                outline: 0;
                background: #4CAF50;
                width: 100%;
                border: 0;
                padding: 15px;
                color: #FFFFFF;
                font-size: 14px;
                cursor: pointer;
            }
            .form button:hover,.form button:active,.form button:focus {
                background: #43A047;
            }
            .form .message {
                margin: 15px 0 0;
                color: #b3b3b3;
                font-size: 12px;
            }
            .form .title {
                 margin: 15px 0 0;
                 color: #4CAF50;
                 font-size: 14px;
             }
            .form .message a {
                color: #4CAF50;
                text-decoration: none;
            }
            body {
                background: #76b852; /* fallback for old browsers */
                background: -webkit-linear-gradient(right, #b2e8ea, #cee8ff);
                font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            }
        </style>
    </head>
</head>
<body>
<div class="login-page">
    <div class="form">
        <form class="login-form" action="/j_spring_security_check" method="post">

            <c:choose>
                <c:when test="${not empty error}">
                    <p class="text-danger">${error}</p>
                </c:when>
                <c:otherwise>
                    <p class="title">Введите логин и пароль</p>
                </c:otherwise>
            </c:choose>
            <input name="username" type="text" placeholder="Логин" style="width: 100%"/>
            <input name="password" type="password" placeholder="Пароль" style="width: 100%"/>
            <p class="text-info text-left"><input name="_spring_security_remember_me" type="checkbox"/> Запомнить</p>
            <button>Войти</button>
            <p class="message"><a href="/registration">Зарегистрироваться</a></p>
        </form>
    </div>
</div>
</body>
</html>
