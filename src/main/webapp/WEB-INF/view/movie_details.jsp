<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="common/header.jsp"%>

<link href="${request.getContextPath()}/WEB-INF/view/common/style.css"
	rel="stylesheet" type="text/css">

<br>

<center>

	<h3>${movie.title}</h3>
	<br />
	
	<img
		src="${request.getContextPath()}/images/<c:out value='${movie.poster_path}'/>"
		alt="not available" width="50%"> 
	<br /> 

	<div>
		<embed
			src="${request.getContextPath()}/images/<c:out value='${movie.trailer}'/>"
			showcontrols="true" width="70%"></embed>
	</div>

	<c:out value="${movie.type}" />
	<br />

	<c:out value="${movie.overview}" />
	<br /> 
	
	<span>$<c:out value="${movie.budget} " /></span> 
	<br /> 
	
	<span><c:if test="${movie.adult} =true"> | 18+</c:if></span>
	<br /> 

	<a href="<c:out value='${movie.homepage}'/>" class="btn btn-primary">reference link </a>
	<br /> 
	
	<c:if test="${sessionScope.movie != null}">
		<a href="save_movie_details" class="btn btn-primary">Save </a>
	</c:if>

</center>

<%@include file="common/footer.jsp"%>

