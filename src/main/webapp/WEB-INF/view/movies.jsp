<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page pageEncoding="UTF-8"%>

<%@include file="common/Header.jsp"%>

<section class="my-5">

	<h3>Welcome to movie World</h3>

	Search by Types
	<form:form method="get" action="search_category">
		<select name="type">
			<option value="All">All</option>
			<c:forEach var="type" items="${types}">
				<option value="${type }">${type }</option>
			</c:forEach>
		</select>
		<button type="submit">Search</button>
	</form:form>
	<br />

	<div class="row">
		<c:forEach var="movie" items="${movies}">

			<div class="col">
				<div class="card" style="width: 18rem;">
					<img src="${request.getContextPath()}/images/${movie.poster_path}"
						class="card-img-top" alt="not available" width="200px"
						height="300px">
					<div class="card-body">
						<h5 class="card-title">${movie.original_title}</h5>
						<p class="card-text">${movie.overview}</p>
						<p class="card-text">
							$$ <span> ${movie.budget} </span> <span> <c:if
									test="${movie.adult} =true">18+</c:if></span>
						</p>
						<span>${movie.type}</span> <a href="${movie.homepage} "
							class="btn btn-primary">reference link</a> <a
							href="movie/${movie.movie_id } " class="btn btn-primary">Details</a>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

</section>

<%@include file="common/Footer.jsp"%>

