<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meal list</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>

<div class="container">
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>

    <div class="col-sm-2" >
        <a class = "btn btn-success" href="<c:url value="?action=add"/>">Add meal</a>
    </div>
    <br>
    <br>
<table class="table table-hover">
    <tr>
        <th>DESCRIPTION</th>
        <th>DATE</th>
        <th>CALORIES</th>
        <th>EDIT</th>
        <th>DELETE</th>

    </tr>
    <c:forEach var="meal" items="${meals}">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
        <tr class="${meal.exceed?'exceeded':'normal'}"/>
            <td>${meal.description}</td>
          <%--  <td>${fn:replace(meal.dateTime, 'T', ' ')}</td> --%>
        <%--=TimeUtil.toString(meal.getDateTime())--%>
        <td>
        <fmt:parseDate value="${meal.dateTime}" pattern="y-M-dd'T'H:m" var = "parseDate"/>
        <fmt:formatDate value="${parseDate}" pattern="yyyy.MM.dd HH:mm" />
        </td>
            <td>${meal.calories}</td>
            <td><a class = "btn btn-primary" href="<c:url value="?action=edit&id=${meal.id}"/>">Edit</a></td>

            <td><a class = "btn btn-danger" href="<c:url value="?action=delete&id=${meal.id}"/>">Delete</a></td>
        </tr>

    </c:forEach>
</table>
</div>
</body>
</html>
