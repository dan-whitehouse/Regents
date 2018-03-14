<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:url value="/admin/users/create" var="addUserLink" />
<html>
	<jsp:include page="../fragments/header.jsp" />
	<!-- page content -->
	<div class="right_col" role="main">
		<div class="">
			<div class="clearfix"></div>
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>Users</h2>
							<ul class="nav navbar-right panel_toolbox">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
									<ul class="dropdown-menu" role="menu">
										<li><a href="${addUserLink}">Create User</a>
										</li>
									</ul>
								</li>
							</ul>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<table id="datatable" class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>Username</th>
										<th>First Name</th>
										<th>Last Name</th>
										<th>District</th>
										<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
											<th>Management</th>
										</sec:authorize>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${users}" var="user">
										<tr>
											<td><span style="text-decoration: underline;"><a href="<c:url value='/admin/users/${user.uuid}' />">${user.username}</a></span></td>
											<td>${user.firstName}</td>
											<td>${user.lastName}</td>
											<td>
												<c:forEach items="${user.userDistricts}" var="userDistrict">
													<span class = "label label-lightGrey">${userDistrict.district.name}</span>
												</c:forEach>
											</td>
											<sec:authorize access="hasRole('ADMIN')">
												<td width="195px">
													<a href="<c:url value='/admin/users/${user.uuid}/edit' />" class="btn btn-success custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Edit"><i class="fa fa-pencil"></i></a>
													<a type="button" class="btn btn-danger custom-width" data-toggle="modal" data-target=".modal-sm-${user.uuid}"><i class="fa fa-trash"></i></a>
													<!-- Visible -->
													<c:choose>
														<c:when test="${user.visible == true}">
															<a href="<c:url value='/admin/users/${user.uuid}/hide/false' />" class="btn btn-default custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Visible"><i class="fa fa-eye"></i></a>
														</c:when>
														<c:otherwise>
															<a href="<c:url value='/admin/users/${user.uuid}/hide/true' />" class="btn btn-default custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Hidden"><i class="fa fa-eye-slash"></i></a>
														</c:otherwise>
													</c:choose>
													<!-- Lock -->
													<c:choose>
														<c:when test="${user.locked == true}">
															<a href="<c:url value='/admin/users/${user.uuid}/lock/false' />" class="btn btn-dark custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Locked"><i class="fa fa-lock"></i></a>
														</c:when>
														<c:otherwise>
															<a href="<c:url value='/admin/users/${user.uuid}/lock/true' />" class="btn btn-dark custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Unlocked"><i class="fa fa-unlock"></i></a>
														</c:otherwise>
													</c:choose>
												</td>
											</sec:authorize>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<!-- Start Delete Popup Confirmation -->
							<sec:authorize access="hasRole('ADMIN')">
								<c:forEach items="${users}" var="user">
									<div class="modal fade modal-sm-${user.uuid}" tabindex="-1" role="dialog" aria-hidden="true">
										<div class="modal-dialog modal-sm">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
													</button>
													<h4 class="modal-title" id="myModalLabel2">Confirmation</h4>
												</div>
												<div class="modal-body">
													<h5>Delete User: </h5>
													<p>${user.username}</p>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
													<a href="<c:url value='/admin/users/${user.uuid}/delete' />" class="btn btn-danger">Delete</a>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</sec:authorize>
							<!-- End Delete Popup Confirmation -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp" />
</html>