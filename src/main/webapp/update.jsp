<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
    <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
    <style>
        dl {
            display: grid;
            grid-template-columns: repeat(auto-fit, 75px);
        }
        button {
            padding: 5px 10px;
            margin-right: 5px;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h3>Edit meal</h3>
<section>
    <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value="${meal.id}">

        <dl>
            <dt>DateTime:</dt>
            <dd><input type="datetime-local" name="dateTime" value="${meal.dateTime}" required></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" name="description" value="${meal.description}" required></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><input type="text" name="calories" value="${meal.calories}" required></dd>
        </dl>

        <button type="submit">Save</button>
        <button type="button" onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
