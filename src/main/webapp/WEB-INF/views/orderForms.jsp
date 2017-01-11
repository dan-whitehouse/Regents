<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:url value="admin/addUser" var="addUser" />
<html>
	<jsp:include page="fragments/header.jsp" />
	<!-- page content -->
	<div class="right_col" role="main">
		<div class="">
			<!--             <div class="page-title"> -->
			<!--               <div class="title_left"> -->
			<!--                 <h3>Users <small>Some examples to get you started</small></h3> -->
			<!--               </div> -->
			<!--               <div class="title_right"> -->
			<!--                 <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search"> -->
			<!--                   <div class="input-group"> -->
			<!--                     <input type="text" class="form-control" placeholder="Search for..."> -->
			<!--                     <span class="input-group-btn"> -->
			<!--                       <button class="btn btn-default" type="button">Go!</button> -->
			<!--                     </span> -->
			<!--                   </div> -->
			<!--                 </div> -->
			<!--               </div> -->
			<!--             </div> -->
			<div class="clearfix"></div>
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>Order Forms</h2>
							<ul class="nav navbar-right panel_toolbox">
								<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
									<ul class="dropdown-menu" role="menu">
										<li><a href="${addUser}">Add User</a>
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
										<th>Name</th>
										<th>Start Date</th>
										<th>End Date</th>
										<th>Status</th>
										<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
											<th>Management</th>
										</sec:authorize>
									</tr>
								</thead>
								<tbody>
<%-- 									<c:forEach items="${users}" var="user"> --%>
<!-- 										<tr> -->
<%-- 											<td>${user.firstName}</td> --%>
<%-- 											<td>${user.lastName}</td> --%>
<%-- 											<td>${user.email}</td> --%>
<%-- 											<td>${user.username}</td> --%>
<%-- 										    <sec:authorize access="hasRole('ADMIN')"> --%>
<!-- 												<td> -->
<%-- 													<a href="<c:url value='/edit-user-${user.username}' />" class="btn btn-success custom-width">edit</a> --%>
<%-- 													<a href="<c:url value='/delete-user-${user.username}' />" class="btn btn-danger custom-width">delete</a> --%>
<!-- 												</td> -->
<%-- 					        				</sec:authorize> --%>
<!-- 										</tr> -->
<%-- 									</c:forEach>									 --%>

									<!-- Demo Item 1 -->
									<tr>
										<td>January 2016</td>
										<td>12/01/2015</td>
										<td>01/01/2016</td>
										<td><span class="label label-default">Complete</span></td>
										<td>
											<a href="<c:url value='/edit-user-${user.username}' />" class="btn btn-success custom-width">edit</a>
											<a href="<c:url value='/delete-user-${user.username}' />" class="btn btn-danger custom-width">delete</a>
										</td>
									</tr>
									
									<!-- Demo Item 2 -->
									<tr>
										<td>June 2016</td>
										<td>5/01/2016</td>
										<td>6/01/2016</td>
										<td><span class="label label-primary">Active</span></td>
										<td>
											<a href="<c:url value='/edit-user-${user.username}' />" class="btn btn-success custom-width">edit</a>
											<a href="<c:url value='/delete-user-${user.username}' />" class="btn btn-danger custom-width">delete</a>
										</td>
									</tr>
									
									<!-- Demo Item 3 -->
									<tr>
										<td>June 2017</td>
										<td>5/01/2017</td>
										<td>6/01/2017</td>
										<td><span class="label label-danger">Inactive</span></td>
										<td>
											<a href="<c:url value='/edit-user-${user.username}' />" class="btn btn-success custom-width">edit</a>
											<a href="<c:url value='/delete-user-${user.username}' />" class="btn btn-danger custom-width">delete</a>
										</td>
									</tr>
 												
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