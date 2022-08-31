<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page pageEncoding="UTF-8"%>

<%@include file="common/admin_header.jsp"%>

<h3>${sessionScope.toCreateMovie.title}</h3>

<br/>

<div class="row">

	<div class="col-4">
		<img 
			src="${request.getContextPath()}/images/${sessionScope.toCreateMovie.posterPath}"
			alt="not available"
			width="100%"
		>
	</div>
	
	<div class="col-6">
		<c:out value="${sessionScope.toCreateMovie.type}" />
		<br/>
	
		<c:out value="${sessionScope.toCreateMovie.overview}" />
		<br/> 
		
		<span>$<c:out value="${sessionScope.toCreateMovie.budget} " /></span> 
		<br/> 
		
		<span><c:if test="${sessionScope.toCreateMovie.adult} =true"> | 18+</c:if></span>
		<br/>
		
		<c:if test="${sessionScope.toCreateMovie != null}">
			<a 
				href="/admin/movie/create/save_movie_detail" 
				class="btn btn-primary"
			>
				Save
			</a>
		</c:if>
	</div>

</div>

<br/>

<div class="video-container">
	<iframe src="${request.getContextPath()}/images/<c:out value='${toCreateMovie.trailer}'/>">
	</iframe>
</div>


<%@include file="common/footer.jsp"%>

