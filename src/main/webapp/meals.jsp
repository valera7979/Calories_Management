<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h2>Meal list</h2>
    <a href="meals?action=create">Add Meal</a>
    <form method="post" action="meals">
        <select name="authUser">
            <option
                    <c:if test="${authUser=='1'}">selected</c:if> value="1">User 1
            </option>
            <option
                    <c:if test="${authUser=='2'}">selected</c:if> value="2">User 2
            </option>
        </select>
        <input type="hidden" name="action" value="setUser">
        <button type="submit">Set User</button>
    </form>
    <hr>
    <form method="post" action="meals">

        <dl>
            <dt>From Date:</dt>
            <dd><input type="date" name="fromDate" value="${fromDate}"></dd>
            <dt>From Time:</dt>
            <dd><input type="time" name="fromTime" value="${fromTime}"></dd>
        </dl>
        <dl>
            <dt>To Date:</dt>
            <dd><input type="date" name="toDate" value="${toDate}"></dd>
            <dt>To Time:</dt>
            <dd><input type="time" name="toTime" value="${toTime}"></dd>
        </dl>
        <input type="hidden" name="action" value="filter">
        <button type="submit">Filter</button>
    </form>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>
                        <%-- <a href="meals?action=update&id=${meal.id}">Update</a> --%>
                    <form>
                        <input type="hidden" name="action" value="update">
                        <button type="submit" name="id" value="${meal.id}">Update</button>
                    </form>
                </td>
                <td>

                        <%-- <a href="meals?action=delete&id=${meal.id}">Delete</a> --%>
                    <form>
                        <input type="hidden" name="action" value="delete">
                        <button type="submit" name="id" value="${meal.id}">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>