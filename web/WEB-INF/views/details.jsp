<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${tender.project.name}<br/>
${tender.project.customer.name}

<a href="/doBet?tenderId=${tender.id}">Сделать ставку</a>



</body>
</html>
