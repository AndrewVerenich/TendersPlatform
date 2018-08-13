<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Правила</title>
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

            <h3 class="text-center">Сведения о тендере</h3>
            <p>Название проекта: <strong>${tender.project.name}</strong></p>
            <p>Класс сложности объекта: <strong>${tender.project.complexityClass}</strong></p>
            <p>Заказчик проекта: <strong>${tender.project.customer.name}</strong></p>
            <p>Нормативная стоимость проектно-изыскательский работ: <strong>${tender.project.firstPrice} BYN</strong></p>
            <p>Дата окончания проектирования: <strong>${tender.project.endDate}</strong></p>
            <p>Дата проведения тендера: <strong>${tender.dateEndOfTender}</strong></p>
            <br/>

            <h3 class="text-center">Сведения о заказчике</h3>
            <p>Название организации заказчика: <strong>${tender.project.customer.name}</strong></p>
            <p>Адрес: <strong>${tender.project.customer.address}</strong></p>
            <p>Контактный телефон: <strong>${tender.project.customer.telNumber}</strong></p>
            <p>Электронная почта: <strong>${tender.project.customer.email}</strong></p>
            <p class="text-center "><a class="btn btn-primary" href="/doBet?tenderId=${tender.id}">Оставить заявку на участие</a></p>
        </div>

        <div class="col-sm-2 sidenav">
            <p><a href="/tenders?active=all">Все тендеры</a></p>
            <p><a href="/tenders?active=true">Активные тендеры</a></p>
            <p><a href="/tenders?active=false">Завершенные тендеры</a></p>
        </div>
    </div>
</div>

<footer class="container-fluid text-center">
    <p>©ProjectTrade, 2018</p>
</footer>

</body>
</html>

