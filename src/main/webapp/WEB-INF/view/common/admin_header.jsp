<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport"
		content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Movie Theater</title>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
		crossorigin="anonymous"
	>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<style><%@include file="/WEB-INF/view/common/style.css"%></style>
</head>

<body class="d-flex flex-column min-vh-100">

	<nav class="navbar navbar-expand-sm navbar-dark bg-primary">
		<div class="container-fluid px-5">
			<a href="/admin/" class="navbar-brand">Movie Theater</a>
			<ul class="navbar-nav">
				<c:choose>
					<c:when test="${sessionScope.loginUser != null}">
						<li class="nav-item"><a href="/user/logout" class="nav-link">LOGOUT</a></li>
					</c:when>
					<c:when test="${sessionScope.loginUser == null}">
						<li class="nav-item"><a href="/user/login" class="nav-link active">LOGIN</a></li>
					</c:when>
				</c:choose>
			</ul>
		</div>
	</nav>

	<div class="container my-3">