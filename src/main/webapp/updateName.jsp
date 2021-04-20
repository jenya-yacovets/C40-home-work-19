<%--
  Created by IntelliJ IDEA.
  User: yacovets
  Date: 19.04.21
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование имени пользователя</title>
</head>
<body>
  <form action="" method="post">
    <input type="text" name="name" placeholder="Введите новое имя"> <br>
    <button>Изменить имя пользователя</button>
    <br>
  </form>
  ${requestScope.message}
</body>
</html>
