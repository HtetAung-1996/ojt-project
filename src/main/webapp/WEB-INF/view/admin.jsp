<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page pageEncoding="UTF-8"%>

<%@include file="common/admin_header.jsp"%>

<h3>Movie Theater Admin Screen</h3>

<div class="row mt-4">

	<%@include file="common/admin_side.jsp"%>

	<div class="col-10">

		<table class="table table-bordered align-middle">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Title</th>
					<th scope="col">Poster</th>
					<th scope="col">Budget</th>
					<th scope="col">Overview</th>
					<th scope="col">Type</th>
					<th scope="col">Adult</th>
					<th scope="col">CreatedAt</th>
					<th scope="col">UpdatedAt</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="movie" items="${movies}">
					<tr>
						<th scope="row">${movie.id}</th>
						<td>${movie.title}</td>
						<td>
							<img
								src="${request.getContextPath()}/images/${movie.posterPath}"
								alt="not available" height="150px"
							>
						</td>
						<td>${movie.budget}</td>
						<td>${movie.overview}</td>
						<td>${movie.type}</td>
						<td>${movie.adult}</td>
						<td>${movie.createdAt}</td>
						<td>${movie.updatedAt}</td>
						<td>
							<form:form method="GET" action="/admin/movie/update/update_movie/${movie.id}">
								<button type="submit" class="btn btn-secondary">
									<i class="bi bi-pencil"></i>
								</button>
							</form:form>
							<br/>
							
							<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">
								<i class="bi bi-trash"></i>
							</button>
							<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="deleteModalLabel">Delete This Movie?</h5>
											<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											You are going to delete this movie.<br/>
											Movie ID : ${movie.id}<br/>
											Movie Title : ${movie.title}
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
											<form:form method="POST" action="/admin/movie/delete/${movie.id}">
												<button type="submit" class="btn btn-primary">Delete</button>
											</form:form>
										</div>
									</div>
								</div>
							</div>
							
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

</div>

<%@include file="common/footer.jsp"%>

