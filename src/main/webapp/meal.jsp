<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <title>Add new user</title>
</head>
<body>

<form method="POST" action='meals' name="frmAddMeal">

    id : <input type="text"  name="id" readonly="readonly"
                value="<c:out value="${meal.id}" />" /> <br />

    Calories : <input type="text"  name="calories"
                     value="<c:out value="${meal.calories}" />" /> <br />
    Date :
    <input type="datetime-local" name="dateTime" value="<c:out value="${meal.dateTime}"/>" />

    Description :
    <input type="text" name="description" value="<c:out value="${meal.description}" />" /> <br />
     <input
        type="submit" value="Submit" />
</form>
</body>
</html>