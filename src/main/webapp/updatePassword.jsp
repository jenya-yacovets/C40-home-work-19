<%--
  Created by IntelliJ IDEA.
  User: yacovets
  Date: 19.04.21
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменение пароля</title>
</head>
<body>
    <form action="" method="post">
        <input type="password" name="password" placeholder="Введите старый пароль"> <br>
        <input type="password" name="newPassword" placeholder="Введите новый пароль"> <br>
        <button>Изменить пароль</button>
        <br>
    </form>
    ${requestScope.message}
</body>
</html>
