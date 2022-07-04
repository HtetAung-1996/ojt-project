<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include  file="common/Header.jsp"%>
<h3>Welcome to movie World</h3>


<form:form method="post" modelAttribute="new_movie" action="/demo/movie/save_data" >
		<div class="form-group">
		    <label for="original_title">Title</label>
		    <form:input path="original_title"  type="text" class="form-control" id="original_title" placeholder="Enter Movie Title"/>
		</div>
		<div class="form-group">
		    <label for="homepage">HomePage</label>
		    <form:input path="homepage"  type="text" class="form-control" id="homepage" placeholder="Enter homepage"/>
		</div>
		<div class="form-group">
		    <label for="overview">Overview</label>
		    <form:input path="overview"  type="textarea" class="form-control" id="overview" placeholder="Enter overview"/>
		</div>
		<div class="form-group">
		    <label for="budget">Budget</label>
		    <form:input path="budget"  type="textarea" class="form-control" id="budget" placeholder="Enter budget"/>
		</div>
		<div class="form-group">
		    <label for="type">Kinds of Movie</label>
		    <form:input path="type"  type="textarea" class="form-control" id="budget" placeholder="Enter kind of Movie"/>
		</div>
		<div class="form-check">
  		<form:radiobutton path="adult" class="form-check-input"  value="true"  />  Adult	<br>	  		 
  		 <form:radiobutton path="adult" class="form-check-input"  value="false" />  Children		 		
  		
	</div>
		<button type="submit" class="btn btn-primary">Next</button>
</form:form>

<%@include  file="common/Footer.html"%>

