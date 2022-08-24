<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page pageEncoding="UTF-8"%>

<%@include file="common/header.jsp"%>

<h3>${movie.title}</h3>

<br />

<div class="row">

	<div class="col-4">
		<img src="${request.getContextPath()}/images/<c:out value='${movie.poster_path}'/>"
			alt="not available" width="100%">
	</div>
	
	<div class="col-6">
		<c:out value="${movie.type}" />
		<br />
	
		<c:out value="${movie.overview}" />
		<br /> 
		
		<span>$<c:out value="${movie.budget} " /></span> 
		<br /> 
		
		<span><c:if test="${movie.adult} =true"> | 18+</c:if></span>
		<br />
		
		<c:if test="${sessionScope.movie != null}">
			<a href="save_movie_details" class="btn btn-primary">Save </a>
		</c:if>
	</div>

</div>

<br />

<div class="video-container">
	<iframe src="${request.getContextPath()}/images/<c:out value='${movie.trailer}'/>">
	</iframe>
</div>


<%@include file="common/footer.jsp"%>

