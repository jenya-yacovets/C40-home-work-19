<%--
  Created by IntelliJ IDEA.
  User: yacovets
  Date: 19.04.21
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Калькулятор</title>
</head>
<body>
    <form action="" method="post">
        <input type="text" name="num1" required placeholder="Число 1"> <br>
        <input type="text" name="num2" required placeholder="Число 2"> <br>
        <select name="method">
            <option value="plus">Сложение</option>
            <option value="minus">Вычитание</option>
            <option value="multiply">Умножение</option>
            <option value="divide">деление</option>
        </select> <br>
        <button>Посчитать</button>
        <br>
        ${requestScope.message}
    </form>
</body>
</html>
