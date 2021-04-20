<%--
  Created by IntelliJ IDEA.
  User: yacovets
  Date: 19.04.21
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
  <form action="" method="post">
    <input type="text" name="login" placeholder="Введите логин"> <br>
    <input type="password" name="password" placeholder="Введите пароль"> <br>
    <button>Авторизоваться</button>
    <br>
  </form>
  ${requestScope.message}
</body>
</html>
