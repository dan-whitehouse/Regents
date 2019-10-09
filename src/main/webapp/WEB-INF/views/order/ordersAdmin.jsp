<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url value="/order" var="orderLink" />
<html>
	<jsp:include page="../fragments/header.jsp" />
	<sec:authorize access="hasRole('ADMIN')">
		<!-- page content -->
		<form:form method="POST" modelAttribute="AdminOrdersForm" role="form">
		<div class="right_col" role="main">
			<div class="">
				<div class="clearfix"></div>
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>Orders</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li class="dropdown">
										<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
										<ul class="dropdown-menu" role="menu">
											<form:hidden path="action" id="menuAction" value=""/>
											<li><a href="${orderLink}">Create Order</a></li>
											<li>
												<input type="submit" value="Set Selected As Complete" onclick="setAction('Complete');"/>
											</li>
											<li>
												<input type="submit" value="Set Selected As Processing" onclick="setAction('Processing');"/>
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
											<th>Order Number</th>
											<th>Date</th>
											<th>Regents Period</th>
											<th>District</th>
											<th>Ordered By</th>
											<th>Order Status</th>
											<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
												<th>Management</th>
											</sec:authorize>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${orders}" var="order" varStatus="status">
											<fmt:formatDate var="year" value="${order.orderForm.startDate}" pattern="yyyy" />
											<tr>
												<td><span style="text-decoration: underline;"><a href="<c:url value='/order/${order.uuid}' />"> ${order.uuid} </a></span></td>
												<td>
													<fmt:formatDate value="${order.orderDate}" type="date" pattern="MM/dd/yyyy"/>
												</td>
												<c:choose>
													<c:when test="${order.orderForm.period eq 'January' }">
														<td>${order.orderForm.period} ${year + 1}</td>
													</c:when>
													<c:otherwise>
														<td>${order.orderForm.period} ${year}</td>
													</c:otherwise>
												</c:choose>
												<td>${order.district.name}</td>
												<td>${order.user.firstName} ${order.user.lastName}</td>
												<td>${order.orderStatus}</td>
												<sec:authorize access="hasRole('ADMIN')">
													<td width="150px">
														<form:hidden path="selectedOrders[${status.index}].uuid" value="${order.uuid}"/>
														<a href="<c:url value='/order/${order.uuid}/edit' />" class="btn btn-success custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Edit"><i class="fa fa-pencil"></i></a>
														<a type="button" class="btn btn-danger custom-width" data-toggle="modal" data-target=".modal-sm-${order.uuid}"><i class="fa fa-trash"></i></a>
														<span class="btn btn-default custom-width checkbox_option">
															<form:checkbox path="selectedOrders[${status.index}].selected" class="orderForm "/>
														</span>
													</td>
												</sec:authorize>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!-- Delete Popup Confirmation -->
								<sec:authorize access="hasRole('ADMIN')">
									<c:forEach items="${orders}" var="order">
										<div class="modal fade modal-sm-${order.uuid}" tabindex="-1" role="dialog" aria-hidden="true">
											<div class="modal-dialog modal-sm">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-label="Close">
															<span aria-hidden="true">×</span>
														</button>
														<h4 class="modal-title" id="myModalLabel2">Confirmation</h4>
													</div>
													<div class="modal-body">
														<h5>Delete Order: </h5>
														<p>${order.uuid}</p>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
														<a href="<c:url value='/admin/order/${order.uuid}/delete' />" class="btn btn-danger">Delete</a>
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
	</form:form>
	</sec:authorize>
	<div class="clearfix"><br/></div>
	<jsp:include page="../fragments/footer.jsp" />
	<script>
			function setAction(action) {
				$('#menuAction').val(action);
			}
		</script>
		<script>
			$('#datatable').DataTable( {
				 "lengthMenu": [ [10, 25, 50, 100, 250, -1], [10, 25, 50, 100, 250, "All"] ],
				 "pageLength": 50
			} );
		
			$('.checkbox_option').click(function() {
			        var cb = $(this).find(":checkbox")[0];

			        if (!$(cb).attr("checked")) {
			            $(cb).attr("checked", "checked");
			            $(cb).prop("checked", true);
			        } 
			        else {
			            $(cb).removeAttr("checked");
			            $(cb).prop("checked", false);
			        }
			            
			    }
			);
		</script>
</html>