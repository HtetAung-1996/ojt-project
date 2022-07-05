<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="common/Header.jsp"%>

<h3>Welcome to movie World</h3>

Upload Poster:
<br />
<form:form method="post" action="/demo/movie/save_poster"
	enctype="multipart/form-data">
	<input type="file" name="poster_path" />
	<button type="submit" class="btn btn-primary">Next</button>
</form:form>

<%@include file="common/Footer.jsp"%>

