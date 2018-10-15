<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../fragments/header.jsp" />
<jsp:include page="../fragments/nav.jsp" />
<!-- page variables -->
<c:url value="/admin/districts/${district.uuid}/edit" var="editLink" />
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						District
						<ul class="nav navbar-right panel_toolbox">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="${editLink}">Edit District</a>
									</li>
								</ul>
							</li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form:form method="POST" modelAttribute="district" class="form-horizontal form-label-left" role="form">
							<form:input type="hidden" path="id" id="id"/>
							<!-- Name -->
							<div class="item form-group">
								<label for="name" class="control-label col-md-3 col-sm-3 col-xs-3 left">Name</label>
								<div class="input-group col-md-6 col-sm-6 col-xs-6">
									<span class="input-group-addon"><i class="fa fa-university"></i></span>
									<form:input type="text" path="name" id="name" class="form-control col-md-7 col-xs-12 active" name="name" placeholder="Name" readonly="true"/>
								</div>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
							</div>
							<!-- BEDS Code -->
							<div class="item form-group">
								<label for="bedsCode" class="control-label col-md-3 col-sm-3 col-xs-3 left">BEDS Code</label>
								<div class="input-group col-md-6 col-sm-6 col-xs-6">
									<span class="input-group-addon"><i class="fa fa-university"></i></span>
									<form:input type="text" path="bedsCode" id="bedsCode" class="form-control col-md-7 col-xs-12 active" name="bedsCode" placeholder="BEDS Code" readonly="true"/>
								</div>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
							</div>
							<!-- Schools -->
							<div class="item form-group">
								<label for="schools" class="control-label col-md-3 col-sm-3 col-xs-3 left">Schools</label>
								<div class="input-group col-md-6 col-sm-6 col-xs-6">
									<span class="input-group-addon"><i class="fa fa-graduation-cap"></i></span>
									<select multiple="true" class="select2_multiple form-control" readonly="true">
										<c:forEach items="${district.schools}" var="school">
											<option selected="true" value="${school.id}" disabled>${school.name}</option>
										</c:forEach>
									</select>
								</div>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-3 left">Users</label>
								<div class="col-md-6 col-sm-6 col-xs-6">
									<table class="col-md-6 col-sm-6 col-xs-6 table table-striped table-bordered">
										<thead>
											<tr>
												<th>Username</th>
												<th>First Name</th>
												<th>Last Name</th>
												<th>Status</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${users}" var="user">
												<tr>
													<td><span style="text-decoration: underline;"><a href="<c:url value='/admin/users/${user.uuid}' />">${user.username}</a></span></td>
													<td>${user.firstName}</td>
													<td>${user.lastName}</td>
													<c:choose>
														<c:when test="${user.locked == true}">
															<td>
																Locked
															</td>
														</c:when>
														<c:otherwise>
															<td>
																Unlocked
															</td>
														</c:otherwise>
													</c:choose>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /page content -->
<jsp:include page="../fragments/footer.jsp" />
<jsp:include page="../fragments/scripts.jsp" />
<!-- Custom Scripts -->
<script src="<c:url value='/resources/vendors/select2/dist/js/select2.full.min.js' />"></script>
<script>
	$(document).ready(function() {
	  $(".select2_single").select2({
	    placeholder: "Select a School",
	    allowClear: false
	  });
	  $(".select2_group").select2({});
	  $(".select2_multiple").select2({
	    maximumSelectionLength: 50,
	    placeholder: "No Schools Found",
	    allowClear: false
	  });
	});
</script>
<script>
	$(document).ready(function()
	{
	    $('.select2-selection__choice__remove').remove();
	});
</script>
<jsp:include page="../fragments/close.jsp" />