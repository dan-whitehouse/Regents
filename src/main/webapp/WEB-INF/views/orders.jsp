<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
							<h2>Orders</h2>
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
											<th>Manage</th>
										</sec:authorize>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${orders}" var="order">
										<tr>
											<td><span style="text-decoration: underline;"><a href="<c:url value='/order/${order.uuid}' />"> ${order.uuid} </a></span></td>
											<td>${order.orderDate}</td>
											<td>June 2017</td>
											<td>Albany County Super District</td>
											<td>${order.user.firstName} ${order.user.lastName}</td>
											<td>${order.orderStatus}</td>
										    <sec:authorize access="hasRole('ADMIN')">
												<td>
														<a href="<c:url value='/order/${order.uuid}/edit' />" class="btn btn-success custom-width">edit</a>
														<a href="<c:url value='/order/${order.uuid}/delete' />" class="btn btn-danger custom-width">delete</a>
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