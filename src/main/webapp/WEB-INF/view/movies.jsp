<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page pageEncoding="UTF-8"%>

<%@include file="common/header.jsp"%>

<h3>Welcome To Movie Theater</h3>

<div class="row mt-4">

	<div class="col-2">
	
		<div class="card">
			<c:forEach var="type" items="${types}">
				<a href="/category/${type}">${type}</a>
			</c:forEach>
		</div>
	
	</div>

	<div class="col-10">

		<div class="row">

			<c:forEach var="movie" items="${movies}">

				<div class="col">

					<div class="card" style="width: 18rem;">

						<img src="${request.getContextPath()}/images/${movie.posterPath}"
							class="card-img-top" style="object-fit: cover;"
							alt="not available" height="300px">

						<div class="card-body">
							<h5 class="card-title">${movie.title}</h5>
							<h6 class="card-subtitle text-muted">${movie.type}</h6>
							<p class="card-text">
								${movie.overview} <br /> $ <span> ${movie.budget} </span> <br />
								<c:if test="${movie.adult=true}">18+</c:if>
								<br />
							</p>
							<a href="" class="btn btn-primary">Reference Link </a> 
							<a href="/movie/details/${movie.id}" class="btn btn-primary">Details</a>
						</div>

					</div>

				</div>

			</c:forEach>

		</div>

	</div>

</div>

<%@include file="common/footer.jsp"%>

