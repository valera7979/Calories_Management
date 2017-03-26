<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Meal list</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>


<table class="table table-hover">
    <tr>
        <th>DESCRIPTION</th>
        <th>DATE</th>
        <th>CALORIES</th>

    </tr>
    <c:forEach var="meal" items="${meals}">
          <c:if test="${meal.exceed}">
        <tr class="exceeded">
          </c:if>
        <c:if test="${!meal.exceed}">
            <tr class="normal">
        </c:if>
            <td>${meal.description}</td>
            <td>${fn:replace(meal.dateTime, 'T', ' ')}</td>
            <td>${meal.calories}</td>

        </tr>

    </c:forEach>
</table>
</body>
</html>
