<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page pageEncoding="UTF-8"%>

<%@include file="common/header.jsp"%>

<form:form action="/user/save" method="post"
	modelAttribute="user">
	
	<c:if test="${sessionScope.loginError != null}">
		<h5 class="text-danger">${sessionScope.loginError}</h5>
		<br>
	</c:if>

	<div class="form-group">
		<label class="mb-2">Name</label>
		<form:input path="name" type="text" class="form-control"
			placeholder="Mg Mg" />
	</div>
	<br>
	
	<div class="form-group">
		<label class="mb-2">Password</label>
		<form:input path="password" type="text" class="form-control"
			placeholder="****" />
	</div>
	<br>
	
	<div class="form-group">
		<label class="mb-2">Gmail</label>
		<form:input path="gmail" type="textarea" class="form-control"
			placeholder="test@gmail.com" />
	</div>
	<br>
	
	<button type="submit" class="btn btn-primary">Register</button>

</form:form>

<%@include file="common/footer.jsp"%>
