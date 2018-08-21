<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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

            <h2 class="text-center"> Правила проведения тендерных торгов онлайн</h2>
            <ul>
                <li>Тендерные торги на сайте начинаются в день, указанный в детальной информации конкретного тендера в 01:00 по минскому времени. Заявки на участие в тендере принимаются до указанного времени.</li>
                <li>В деталях тендера указана нормативная стоимость проектно-изыскательский работ, предлагаемая участником тендера стоимость может быть ниже или выше нормативной стоимости.</li>
                <li>Для получения дополнительных сведений о проекте, Пользователь обращается к Заказчику по контактам, указанным в деталях тендера.</li>
                <li>Победителем тендера считается участник, предложивший наименьшую стоимость выполнения проектно-изыскательский работ.</li>
                <li>Результаты тендера (победитель и стоимость) будут доступны в деталях тендера и в Личном кабинете пользователя (при условии участия в тендере).</li>
                <li>После успешного проведения тендера Победитель связывается с Заказчиком (или наоборот) для заключения договора на выполнение проектно-изыскательский работ.</li>
                <li>После отправки заявки на проведение тендера, она проходит проверку Администратором.</li>
                <li>В случае если минимальную цену предложат двое или более Участников, победителем призенается участник, предложивший цену раньше.</li>
                <li>Участник вправе предложить новую цену, если предыдущая ставка сделана им же.</li>
            </ul>
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
