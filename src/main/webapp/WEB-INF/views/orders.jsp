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
<!-- 							<ul class="nav navbar-right panel_toolbox"> -->
<!-- 								<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a> -->
<!-- 								</li> -->
<!-- 								<li class="dropdown"> -->
<!-- 									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a> -->
<!-- 									<ul class="dropdown-menu" role="menu"> -->
<!-- 										<li><a href="#">Settings 1</a> -->
<!-- 										</li> -->
<!-- 										<li><a href="#">Settings 2</a> -->
<!-- 										</li> -->
<!-- 									</ul> -->
<!-- 								</li> -->
<!-- 								<li><a class="close-link"><i class="fa fa-close"></i></a> -->
<!-- 								</li> -->
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
														<a href="<c:url value='/edit-user-${user.username}' />" class="btn btn-success custom-width">edit</a>
														<a href="<c:url value='/delete-user-${user.username}' />" class="btn btn-danger custom-width">delete</a>
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