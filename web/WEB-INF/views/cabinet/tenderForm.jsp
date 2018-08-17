<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Создать тендер</title>
    <style>
        .sidenav {
            padding-top: 20px;
            background-color: #f1f1f1;
            height: 100%;
        }

        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

        .title{
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-weight: bold;
        }
        .imageAndText {position: relative;}
        .imageAndText .col {position: absolute; z-index: 1; top: 0; left: 0;}

    </style>
</head>
<body>


<div class="imageAndText">
    <img src="../../resources/img/wall.jpg" class="align-center img-responsive">
    <div class="col">
        <div class="col-sm-12 title">
            <p>ProjectTrade</p>
            <p>Система проведения тендерных</p>
            <p>торгов на проектные работы</p>
        </div>
    </div>
</div>


<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">ProjectTrade</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/cabinet"><span class="glyphicon glyphicon-home"></span> Личный кабинет</a></li>
            <li><a href="/registration"><span class="glyphicon glyphicon-user"></span> Регистрация</a></li>
                    <li><a href="/j_spring_security_logout"><span class="glyphicon glyphicon-log-out"></span> Выход</a></li>
        </ul>
    </div>
</nav>


<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
            <p><a href="/rules">Правила проведения тендеров</a></p>
            <p><a href="/resources/stb_2331.pdf">СТБ 2331-2014 "Здания и сооружения. Классификация"</a></p>
        </div>
        <div class="col-sm-8 text-left">

            <%--<div class="form-group">--%>
            <form method="post" action="/cabinet/createTender">
                <p>Название проекта: <input name="name" type="text"/></p>
                <p>Класс здания или сооружения: <input name="complClass" type="text" pattern="^[ 0-9]+$"/></p>
                <p>Нормативная стоимость (BYN): <input name="price" type="text" pattern="^[ 0-9]+$"/></p>
                <p>Дата окончания проектирования: <input name="dateP" type="date"/></p>
                <p>Дата проведения тендера: <input name="dateT" type="date"/></p>
                <input id="button" class="btn btn-primary" type="submit" value="Сохранить">
            </form>
            <%--</div>--%>


        </div>

        <div class="col-sm-2 sidenav">
            <p><a href="/cabinet">Мой профиль</a></p>
            <p><a href="/cabinet/editProfile">Редактировать профиль</a></p>
            <p><a href="/cabinet/myBets">Мои ставки</a></p>
            <p><a href="/cabinet/myTenders">Мои тендеры</a></p>
            <p><a href="/cabinet/newTender">Создать тендер</a></p>
        </div>
    </div>
</div>

<footer class="container-fluid text-center">
    <p>©ProjectTrade, 2018</p>
</footer>

</body>
</html>
