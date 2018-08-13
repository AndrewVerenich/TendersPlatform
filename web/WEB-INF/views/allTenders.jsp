<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Тендерная площадка ProjectTrade</title>
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
            /*text-shadow: 3px 2px red;*/
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
            <c:choose>
                <c:when test="${principal==null}">
                    <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Вход</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/j_spring_security_logout"><span class="glyphicon glyphicon-log-out"></span> Выход</a></li>
                </c:otherwise>
            </c:choose>
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
            <c:forEach items="${tenders}" var="tender">
                <tr>
                    <div class="panel panel-default">

                        <div class="panel-heading">
                            <div class="container-fluid">
                               <div class="col-sm-11">
                                   <a href="/tendDetails?tenderId=${tender.id}"><strong>${tender.project.name}</strong></a>
                               </div>
                               <div class="col-sm-1">
                                   <c:if test="${tender.active==true}"><strong style="color: green">Активный</strong></c:if>
                                   <c:if test="${tender.active==false}"><strong style="color: red">Завершен</strong></c:if>
                               </div>
                            </div>
                        </div>

                        <div class="panel-body">
                            <ul>
                                <li>Заказчик: <strong>${tender.project.customer.name}</strong></li>
                                <li>Класс сложности здания: <strong>К-${tender.project.complexityClass}</strong></li>
                                <li>Нормативная стоимость проектно-изыскательский работ: <strong>${tender.project.firstPrice} BYN</strong></li>
                                <li>Дата окончания проектирования: <strong>${tender.project.endDate}</strong>, дата проведения тендера: <strong>${tender.dateEndOfTender}</strong></li>
                            </ul>
                        </div>

                    </div>
                </tr>
            </c:forEach>

            <div class="container-fluid text-center">
            <ul class="pagination">
                <li><a href="#">1</a></li>
                <li class="active"><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
            </ul>
            </div>

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
