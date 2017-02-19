<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:url value="/admin/districts/create" var="addDistrict" />
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
							<h2>Districts</h2>
							<div class="nav navbar-right panel_toolbox">
								<a href="/Regents/admin/districts/create" class="btn btn-dark btn-xs custom-width">Create District</a>
							</div>
							
<!-- 							<ul class="nav navbar-right panel_toolbox"> -->
<!-- 								<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li> -->
<!-- 								<li class="dropdown"> -->
<!-- 									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a> -->
<!-- 									<ul class="dropdown-menu" role="menu"> -->
<!-- 										<li> -->
<%-- 											<a href="${addDistrict}">Add District</a> --%>
<!-- 										</li> -->
<!-- 									</ul> -->
<!-- 								</li> -->
<!-- <!-- 								<li><a class="close-link"><i class="fa fa-close"></i></a> -->
<!-- 							</ul> -->
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
<!-- 							<p class="text-muted font-13 m-b-30"> -->
<%-- 								DataTables has most features enabled by default, so all you need to do to use it with your own tables is to call the construction function: <code>$().DataTable();</code> --%>
<!-- 							</p> -->
							<table id="datatable" class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>Name</th>
										<th>BEDS Code</th>
										<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
											<th>Management</th>
										</sec:authorize>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${districts}" var="district">
										<tr>
											<td>${district.name}</td>
											<td>${district.bedsCode}</td>
										    <sec:authorize access="hasRole('ADMIN')">
												<td>
													<a href="<c:url value='/admin/districts/${district.id}/edit' />" class="btn btn-success custom-width">edit</a>
													<a href="<c:url value='/admin/districts/${district.bedsCode}/delete' />" class="btn btn-danger custom-width">delete</a>
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