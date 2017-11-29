<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:url value="/admin/documents/create" var="createDocumentLink" />

<html>
	<jsp:include page="../fragments/header.jsp" />
	<sec:authorize access="hasRole('ADMIN')">
		<!-- page content -->
		<div class="right_col" role="main">
			<div class="">
				<div class="clearfix"></div>
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>Documents</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li class="dropdown">
										<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
										<ul class="dropdown-menu" role="menu">
											<li><a href="${createDocumentLink}">Create Document</a>
										</ul>
									</li>
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
											<th>Document Name</th>
											<th>Management</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${documents}" var="document">
											<tr>
												<td>${document.name}</td>
											    <sec:authorize access="hasRole('ADMIN')">
													<td width="195px">
														<a href="<c:url value='/admin/documents/${document.uuid}/edit' />" class="btn btn-success custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Edit"><i class="fa fa-pencil"></i></a>
														<a type="button" class="btn btn-danger custom-width" data-toggle="modal" data-target=".modal-sm-${document.uuid}"><i class="fa fa-trash"></i></a>
														<!-- Visible -->
														<c:choose>
															<c:when test="${document.visible == true}">
																<a href="<c:url value='/admin/documents/${document.uuid}/hide/false' />" class="btn btn-default custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Visible"><i class="fa fa-eye"></i></a>
															</c:when>
															<c:otherwise>
																<a href="<c:url value='/admin/documents/${document.uuid}/hide/true' />" class="btn btn-default custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Hidden"><i class="fa fa-eye-slash"></i></a>
															</c:otherwise>
														</c:choose>
														<!-- Lock -->
														<c:choose>
															<c:when test="${document.locked == true}">
																<a href="<c:url value='/admin/documents/${document.uuid}/lock/false' />" class="btn btn-dark custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Locked"><i class="fa fa-lock"></i></a>
															</c:when>
															<c:otherwise>
																<a href="<c:url value='/admin/documents/${document.uuid}/lock/true' />" class="btn btn-dark custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Unlocked"><i class="fa fa-unlock"></i></a>
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
									<c:forEach items="${documents}" var="document">
										<div class="modal fade modal-sm-${document.uuid}" tabindex="-1" role="dialog" aria-hidden="true">
											<div class="modal-dialog modal-sm">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
														</button>
														<h4 class="modal-title" id="myModalLabel2">Confirmation</h4>
													</div>
													<div class="modal-body">
														<h5>Delete Document: </h5>
														<p>${document.name}</p>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
														<a href="<c:url value='/admin/documents/${document.uuid}/delete' />" class="btn btn-danger">Delete</a>
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
	</sec:authorize>
	<jsp:include page="../fragments/footer.jsp" />
</html>