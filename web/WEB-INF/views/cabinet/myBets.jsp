<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Личный кабинет</title>
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
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>



<div class="imageAndText">
    <img src="${contextPath}/resources/img/wall.jpg" class="align-center img-responsive">
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
            <a class="navbar-brand" href="${contextPath}/">ProjectTrade</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="${contextPath}/cabinet"><span class="glyphicon glyphicon-home"></span> Личный кабинет</a></li>
            <li><a href="${contextPath}/registration"><span class="glyphicon glyphicon-user"></span> Регистрация</a></li>
            <li><a href="/j_spring_security_logout"><span class="glyphicon glyphicon-log-out"></span> Выход</a></li>
        </ul>
    </div>
</nav>


<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
            <p><a href="${contextPath}/rules">Правила проведения тендеров</a></p>
            <p><a href="${contextPath}/resources/stb_2331.pdf">СТБ 2331-2014 "Здания и сооружения. Классификация"</a></p>
        </div>
        <div class="col-sm-8 text-left">
            <h3>Ваши заявки на участие в тендерах:</h3>

            <c:forEach items="${bets}" var="bet">
                <tr>
                    <div class="panel panel-default">

                        <div class="panel-heading">
                            <div class="container-fluid">
                                <div class="col-sm-11">
                                    <a href="${contextPath}/tendDetails?tenderId=${bet.tender.id}"><strong>${bet.tender.project.name}</strong></a>
                                </div>
                                <div class="col-sm-1">
                                    <c:if test="${bet.tender.active==true}"><strong style="color: green">Активный</strong></c:if>
                                    <c:if test="${bet.tender.active==false}"><strong style="color: red">Завершен</strong></c:if>
                                </div>
                            </div>
                        </div>

                        <div class="panel-body">
                            <ul>
                                <li>Заказчик: <strong>${bet.tender.project.customer.name}</strong></li>
                                <li>Класс сложности здания: <strong>К-${bet.tender.project.complexityClass}</strong></li>
                                <li>Нормативная стоимость проектно-изыскательский работ: <strong>${bet.tender.project.firstPrice} BYN</strong></li>
                                <li>Дата окончания проектирования: <strong>${bet.tender.project.endDate}</strong>, дата проведения тендера: <strong>${bet.tender.dateEndOfTender}</strong></li>
                                <li>Предложенная цена: <strong>${bet.bet} BYN</strong></li>
                            </ul>
                        </div>

                    </div>
                </tr>
            </c:forEach>


        </div>

        <div class="col-sm-2 sidenav">
            <p><a href="${contextPath}/cabinet">Мой профиль</a></p>
            <p><a href="${contextPath}/cabinet/editProfile">Редактировать профиль</a></p>
            <p><a href="${contextPath}/cabinet/myBets">Мои ставки</a></p>
            <p><a href="${contextPath}/cabinet/myTenders">Мои тендеры</a></p>
            <p><a href="${contextPath}/cabinet/newTender">Создать тендер</a></p>
        </div>
    </div>
</div>

<footer class="container-fluid text-center">
    <p>©ProjectTrade, 2018</p>
</footer>

</body>
</html>



