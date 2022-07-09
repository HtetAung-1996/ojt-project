<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page pageEncoding="UTF-8"%>

<%@include file="common/Header.jsp"%>

<form:form action="/user/save" method="post"
	modelAttribute="user">

	<div class="form-group">
		<form:input path="name" type="text" class="form-control"
			placeholder="User Name" />
	</div>
	<br>
	
	<div class="form-group">
		<form:input path="password" type="text" class="form-control"
			placeholder=" password" />
	</div>
	<br>
	
	<div class="form-group">
		<form:input path="gmail" type="textarea" class="form-control"
			placeholder="Gmail" />
	</div>
	<br>
	
	<button type="submit" class="btn btn-primary">Register</button>

</form:form>

<%@include file="common/Footer.jsp"%>