<%@ page import="static ru.javawebinar.topjava.util.TimeUtil.DATE_TIME_FORMATTER" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<section>
    <p><a href="meals?id=${mealTo.id}&action=add">Add meal</a></p>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        <c:forEach items="${meals}" var="mealTo">
            <jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr style="color: ${mealTo.excess ? 'red' : 'green'}">
                <td>
                    <%=DATE_TIME_FORMATTER.format(mealTo.getDateTime())%>
                </td>
                <td>
                        ${mealTo.description}
                </td>
                <td>
                        ${mealTo.calories}
                </td>
                <td>
                    <a href="meals?id=${mealTo.id}&action=update">Update</a>
                </td>
                <td>
                    <a href="meals?id=${mealTo.id}&action=delete">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
