<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
							<h2>Past Non-Administrations</h2>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<table id="datatable" class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>Non-Administration Number</th>
										<th>Date</th>
										<th>Regents Period</th>
										<th>District</th>
										<th>Submitted By</th>
										<sec:authorize access="hasRole('ADMIN')">
											<th>Management</th>
										</sec:authorize>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${optouts}" var="optout">
										<fmt:formatDate var="year" value="${optout.orderForm.startDate}" pattern="yyyy" />
										<tr>
											<td>${optout.uuid}</td>
											<td>
												<fmt:formatDate value="${optout.optOutDate}" type="date" pattern="MM/dd/yyyy"/>
											</td>
											<c:choose>
												<c:when test="${optout.orderForm.period eq 'January' }">
													<td>${optout.orderForm.period} ${year + 1}</td>
												</c:when>
												<c:otherwise>
													<td>${optout.orderForm.period} ${year}</td>
												</c:otherwise>
											</c:choose>
											<td>${optout.district.name}</td>
											<td>${optout.optOutUser.firstName} ${optout.optOutUser.lastName}</td>
											<sec:authorize access="hasRole('ADMIN')">
												<td width="150px">
													<a type="button" class="btn btn-danger custom-width" data-toggle="modal" data-target=".modal-sm-${optout.uuid}"><i class="fa fa-trash"></i></a>
												</td>
											</sec:authorize>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<!-- Delete Popup Confirmation -->
							<sec:authorize access="hasRole('ADMIN')">
								<c:forEach items="${optouts}" var="optout">
									<div class="modal fade modal-sm-${optout.uuid}" tabindex="-1" role="dialog" aria-hidden="true">
										<div class="modal-dialog modal-sm">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">?</span>
													</button>
													<h4 class="modal-title" id="myModalLabel2">Confirmation</h4>
												</div>
												<div class="modal-body">
													<h5>Delete Non-Administration Number: </h5>
													<p>${optout.uuid}</p>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
													<a href="<c:url value='/notadministration/${optout.uuid}/delete' />" class="btn btn-danger">Delete</a>
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