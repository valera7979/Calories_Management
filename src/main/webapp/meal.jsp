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
<div class="col-sm-6">
    <h2>Add new meal</h2>
    <%--<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>--%>
<form method="POST" action='meals' name="frmAddMeal">
    <div class="form-group">
        <label class="control-label col-sm-2">Product Id</label>
        <div class="col-sm-10">
    <input type="text"  name="id" readonly="readonly" class="form-control"
                value="<c:out value="${meal.id}" />" /> <br/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">Calories :</label>
        <div class="col-sm-10">
     <input type="number" min="0"  name="calories" class="form-control"
                     value="<c:out value="${meal.calories}" />" /> <br />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">Date :</label>
        <div class="col-sm-10">

        <input type="datetime-local" name="dateTime" class="form-control"
           value="${meal.dateTime}"/>
        </div>
    </div>
<br>
<br>
    <div class="form-group">
        <label class="control-label col-sm-2">Description:</label>
        <div class="col-sm-10">

    <input type="text" name="description" class="form-control"
           value="<c:out value="${meal.description}" />" /> <br />
        </div>
    </div>
    <div class="form-group">
        <div class="col-lg-offset-2 col-lg-10">
     <input type="submit" value="Submit" class="btn btn-primary"/>
        </div>
    </div>
</form>
</div>
</body>
</html>