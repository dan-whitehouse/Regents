<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/admin/orderForms/create" var="createOrderFormLink" />

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
							<h2>Order Forms</h2>
							<ul class="nav navbar-right panel_toolbox">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
									<ul class="dropdown-menu" role="menu">
										<li><a href="${createOrderFormLink}">Create OrderForm</a>
										</li>
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
										<th>Name</th>
										<th>Start Date</th>
										<th>End Date</th>
										<th>Period</th>
										<th>Status</th>
										<sec:authorize access="hasRole('ADMIN')">
											<th>Management</th>
										</sec:authorize>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${orderForms}" var="orderForm">
									
										<c:choose>
											<c:when test="${orderForm.locked == true}"> 
												<!-- <tr class="alert-danger"> -->
												<tr>
											</c:when>
											<c:otherwise>
												<tr>
											</c:otherwise>
										</c:choose>
											<td class="col-xs-4"><a href="<c:url value='/admin/orderForms/${orderForm.uuid}' />">${orderForm.name}</a></td>

											<fmt:formatDate value="${orderForm.startDate}" type="date" pattern="MM/dd/yyyy" var="fStartDate"/>
											<fmt:formatDate value="${orderForm.endDate}" type="date" pattern="MM/dd/yyyy" var="fEndDate"/>
											<fmt:formatDate var="schoolYear" value="${orderForm.startDate}" pattern="yyyy" />
											
											<td>${fStartDate}</td>
											<td>${fEndDate}</td>
											<td>${orderForm.period} ${schoolYear}</td>
											<td>
											<!-- Start Status Test -->
    											<c:choose>
													<c:when test="${orderForm.activePeriod}"> 
														<span class="label label-info">Active</span>
													</c:when>
													<c:when test="${orderForm.expiredPeriod}"> 
														<span class="label label-danger">Expired</span>
													</c:when>
													<c:otherwise>
														<span class="label label-default">Inactive</span>
													</c:otherwise>
												</c:choose>
    										<!-- End Status Test -->
											</td>
											<sec:authorize access="hasRole('ADMIN')">
												<td width="245px">
													<a href="<c:url value='/admin/orderForms/${orderForm.uuid}/edit' />" class="btn btn-success custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Edit"><i class="fa fa-pencil"></i></a>
													<a type="button" class="btn btn-danger custom-width" data-toggle="modal" data-target=".modal-sm-${orderForm.uuid}"><i class="fa fa-trash"></i></a>
													<!-- Visible -->
													<c:choose>
														<c:when test="${orderForm.visible == true}">
															<a href="<c:url value='/admin/orderForms/${orderForm.uuid}/hide/false' />" class="btn btn-default custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Visible"><i class="fa fa-eye"></i></a>
														</c:when>
														<c:otherwise>
															<a href="<c:url value='/admin/orderForms/${orderForm.uuid}/hide/true' />" class="btn btn-default custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Hidden"><i class="fa fa-eye-slash"></i></a>
														</c:otherwise>
													</c:choose>
													<!-- Lock -->
													<c:choose>
														<c:when test="${orderForm.locked == true}">
															<a href="<c:url value='/admin/orderForms/${orderForm.uuid}/lock/false' />" class="btn btn-dark custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Locked"><i class="fa fa-lock"></i></a>
														</c:when>
														<c:otherwise>
															<a href="<c:url value='/admin/orderForms/${orderForm.uuid}/lock/true' />" class="btn btn-dark custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Unlocked"><i class="fa fa-unlock"></i></a>
														</c:otherwise>
													</c:choose>
													<!-- Active -->
													<c:choose>
														<c:when test="${orderForm.active == true}">
															<a href="<c:url value='/admin/orderForms/${orderForm.uuid}/active/false' />" class="btn btn-info custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Active"><i class="fa fa-check-square"></i></a>
														</c:when>
														<c:otherwise>
															<a href="<c:url value='/admin/orderForms/${orderForm.uuid}/active/true' />" class="btn btn-default custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Inactive"><i class="fa fa-square"></i></a>
														</c:otherwise>
													</c:choose>
												</td>
											</sec:authorize>
										
									</c:forEach>	
								</tbody>
							</table>
							<!-- Start Delete Popup Confirmation -->
							<sec:authorize access="hasRole('ADMIN')">
								<c:forEach items="${orderForms}" var="orderForm">
									<div class="modal fade modal-sm-${orderForm.uuid}" tabindex="-1" role="dialog" aria-hidden="true">
										<div class="modal-dialog modal-sm">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">?</span>
													</button>
													<h4 class="modal-title" id="myModalLabel2">Confirmation</h4>
												</div>
												<div class="modal-body">
													<h5>Delete Order Form: </h5>
													<p>${orderForm.name}</p>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
													<a href="<c:url value='/admin/orderForms/${orderForm.uuid}/delete' />" class="btn btn-danger">Delete</a>
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
	<jsp:include page="fragments/footer.jsp" />
</html>