<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Demo of Spring Boot+ MVC</title>
    <link src="style.css" type="text/css"></link>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>

<body >
<div class='container'>
    <nav class="navbar navbar-expand-sm navbar-dark bg-primary">
        <div class="container-fluid">
            <a href="#" class="navbar-brand">Spring Boot</a>
            <ul class="navbar-nav">
             <c:if test="${sessionScope.login_user != null}">
				<h5>${sessionScope.login_user.name}</h5>
			</c:if>
                <li class="nav-item">
                    <a href="demo/movie" class="nav-link active">MOVES</a>
                </li>                
                <li class="nav-item">
                    <a href="demo/about" class="nav-link">ABOUT</a>
                </li>
                <li class="nav-item">
                    <a href="/user/v1/login" class="nav-link">LOGIN</a>
                </li>
            </ul>
        </div>
    </nav>

