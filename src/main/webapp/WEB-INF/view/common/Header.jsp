<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Movie Theater</title>
<link src="style.css" type="text/css"></link>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
</head>

<body class="d-flex flex-column min-vh-100">

	<nav class="navbar navbar-expand-sm navbar-dark bg-primary">
		<div class="container-fluid">
			<a href="#" class="navbar-brand">Spring Boot</a>
			<ul class="navbar-nav">
				<c:if test="${sessionScope.login_user != null}">
					<li class="nav-item"><a class="nav-link active">${sessionScope.login_user.name}</a></li>
				</c:if>
				<li class="nav-item"><a href="/" class="nav-link active">MOVIES</a></li>
				<!--<li class="nav-item"><a href="/about" class="nav-link">ABOUT</a></li>  -->
				<li class="nav-item"><a href="/user/login" class="nav-link">LOGIN</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container mt-3">