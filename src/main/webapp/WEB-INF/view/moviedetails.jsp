<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="common/Header.html"%>
<link href="${request.getContextPath()}/WEB-INF/view/common/style.css"
	rel="stylesheet" type="text/css">

<br>
<center>
	<h3>${movie.original_title}</h3>
	<br>
	<br>
	<div>
		<embed
			src="${request.getContextPath()}/images/<c:out value='${movie.trailer}'/>"
			showcontrols="true" width="55%" height="70%"></embed>
	</div>
	<c:out value="${movie.type}" />
	<br>
	<c:out value="${movie.overview}" />
	<br> <br> <img
		src="${request.getContextPath()}/images/<c:out value='${movie.poster_path}'/>"
		alt="not available" width="100px" height="100px"> <br> <span>$
		<c:out value="${movie.budget} " />
	</span><span> <c:if test="${movie.adult} =true"> | 18+</c:if></span><br> <a
		href="<c:out value='${movie.homepage}'/>" class="btn btn-primary">reference link </a> |
	<c:if test="${sessionScope.movie != null}">
		<a href="save_moviedetails" class="btn btn-primary">Save </a>
	</c:if>
</center>
<%@include file="common/Footer.html"%>

