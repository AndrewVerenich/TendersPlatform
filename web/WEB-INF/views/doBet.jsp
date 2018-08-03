<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${user.name} </br>
${tender.project.name}

<div class="form">
    <form  method="post" action="/bidAccepted?tenderId=${tender.id}">
        <input name="bid" type="text" placeholder="Ставка"/>
    <input type="submit" value="Bet">
    </form>
</body>
</html>
