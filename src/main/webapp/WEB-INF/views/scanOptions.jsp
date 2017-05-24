<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:url value="/admin/scanOptions/create" var="createScanOption" />
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
							<h2>Scan Options</h2>
							<ul class="nav navbar-right panel_toolbox">
								<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
									<ul class="dropdown-menu" role="menu">
										<li>
											<a href="${createScanOption}">Add Scan Option</a>
										</li>
									</ul>
								</li>
<!-- 								<li><a class="close-link"><i class="fa fa-close"></i></a> -->
							</ul>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
<!-- 							<p class="text-muted font-13 m-b-30"> -->
<%-- 								DataTables has most features enabled by default, so all you need to do to use it with your own tables is to call the construction function: <code>$().DataTable();</code> --%>
<!-- 							</p> -->
							<table id="datatable" class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>Option Name</th>
										<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
											<th>Management</th>
										</sec:authorize>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${scanOptions}" var="scanOption">
										<tr>
											<td>${scanOption.name}</td>
										    <sec:authorize access="hasRole('ADMIN')">
												<td>
													<a href="<c:url value='/admin/scanOptions/${scanOption.id}/edit' />" class="btn btn-success custom-width">edit</a>
													<a href="<c:url value='/admin/scanOptions/${scanOption.id}/delete' />" class="btn btn-danger custom-width">delete</a>
													<!-- Visible -->
													<c:choose>
														<c:when test="${scanOption.visible == true}">
															<a href="<c:url value='/admin/scanOptions/${scanOption.id}/hide/false' />" class="btn btn-default custom-width">Hide</a>
														</c:when>
														<c:otherwise>
															<a href="<c:url value='/admin/scanOptions/${scanOption.id}/hide/true' />" class="btn btn-default custom-width">Unhide</a>
														</c:otherwise>
													</c:choose>
													<!-- Lock -->
													<c:choose>
														<c:when test="${scanOption.locked == true}">
															<a href="<c:url value='/admin/scanOptions/${scanOption.id}/lock/false' />" class="btn btn-dark custom-width">Unlock</a>
														</c:when>
														<c:otherwise>
															<a href="<c:url value='/admin/scanOptions/${scanOption.id}/lock/true' />" class="btn btn-dark custom-width">Lock</a>
														</c:otherwise>
													</c:choose>
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