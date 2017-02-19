<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:url value="/admin/schools/create" var="addSchool" />
<html>
	<jsp:include page="fragments/header.jsp" />
	<!-- page content -->
	<div class="right_col" role="main">
		<div class="">
			<div class="clearfix"></div>
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>Schools</h2>
							<div class="nav navbar-right panel_toolbox">
								<a href="${addSchool}" class="btn btn-dark btn-xs custom-width">Create School</a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<table id="datatable" class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>Name</th>
										<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
											<th>Management</th>
										</sec:authorize>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${schools}" var="school">
										<tr>
											<td>${school.name}</td>
										    <sec:authorize access="hasRole('ADMIN')">
												<td>
													<a href="<c:url value='/admin/schools/${school.id}/edit' />" class="btn btn-success custom-width">edit</a>
													<a href="<c:url value='/admin/schools/${school.id}/delete' />" class="btn btn-danger custom-width">delete</a>
												</td>
					        				</sec:authorize>
										</tr>
									</c:forEach>									
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="fragments/footer.jsp" />
</html>