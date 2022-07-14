<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page pageEncoding="UTF-8"%>

<%@include file="common/header.jsp"%>

<h3>Create New Movie</h3>
<br/>

<form:form method="post" modelAttribute="movie"
	action="/movie/save">

	<div class="form-group">
		<label for="title">Title</label>
		<form:input path="title" type="text" class="form-control"
			id="title" placeholder="Enter Movie Title" />
	</div>
	<br/>

	<div class="form-group">
		<label for="homepage">HomePage</label>
		<form:input path="homepage" type="text" class="form-control"
			id="homepage" placeholder="Enter homepage" />
	</div>
	<br/>

	<div class="form-group">
		<label for="overview">Overview</label>
		<form:input path="overview" type="textarea" class="form-control"
			id="overview" placeholder="Enter overview" />
	</div>
	<br/>

	<div class="form-group">
		<label for="budget">Budget</label>
		<form:input path="budget" type="textarea" class="form-control"
			id="budget" placeholder="Enter budget" />
	</div>
	<br/>

	<div class="form-group">
		<label for="type">Kind of Movie</label>
		<form:input path="type" type="textarea" class="form-control"
			id="budget" placeholder="Enter kind of Movie" />
	</div>
	<br/>

	<div class="form-check">
		<form:radiobutton path="adult" class="form-check-input" value="true" />
		Adult <br>
		<form:radiobutton path="adult" class="form-check-input" value="false" />
		Children
	</div>
	<br/>

	<button type="submit" class="btn btn-primary">Next</button>

</form:form>

<%@include file="common/footer.jsp"%>

