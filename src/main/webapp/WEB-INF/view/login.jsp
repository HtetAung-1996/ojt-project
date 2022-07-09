<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page pageEncoding="UTF-8"%>

<%@include file="common/Header.jsp"%>

<form:form action="/user/loginCheck" method="post">

	<c:if test="${sessionScope.login_error != null}">
		<h5>${sessionScope.login_error}</h5>
	</c:if>
	
	<div class="form-group">
		<input name="gmail" type="text" class="form-control"
			placeholder="Gmail" />
	</div>
	<br>

	<div class="form-group">
		<input name="password" type="password" class="form-control"
			placeholder=" password" />
	</div>
	<br>

	<button type="submit" class="btn btn-primary">Login</button>

</form:form>

<%@include file="common/Footer.jsp"%>