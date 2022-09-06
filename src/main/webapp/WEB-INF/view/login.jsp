<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page pageEncoding="UTF-8"%>

<%@include file="common/header.jsp"%>

<form:form action="/user/loginCheck" method="post">

	<c:if test="${sessionScope.loginError != null}">
		<h5 class="text-danger">${sessionScope.loginError}</h5>
		<br>
	</c:if>
	
	<div class="form-group">
		<label class="mb-2">Gmail</label>
		<input name="gmail" type="text" class="form-control"
			placeholder="test@gmail.com" />
	</div>
	<br>

	<div class="form-group">
		<label class="mb-2">Password</label>
		<input name="password" type="password" class="form-control"
			placeholder="****" />
	</div>
	<br>

	<button type="submit" class="btn btn-primary">Login</button>

</form:form>

<%@include file="common/footer.jsp"%>
