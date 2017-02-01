<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
							<h2>Manage Exam</h2>
							<ul class="nav navbar-right panel_toolbox">
								<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
								</li>
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
									<ul class="dropdown-menu" role="menu">
										<li><a href="#">Settings 1</a>
										</li>
										<li><a href="#">Settings 2</a>
										</li>
									</ul>
								</li>
								<li><a class="close-link"><i class="fa fa-close"></i></a>
								</li>
							</ul>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<%--                     <form:form method="POST" modelAttribute="user" class="form-horizontal form-label-left" novalidate> --%>
							<form:form method="POST" modelAttribute="exam" class="form-horizontal form-label-left">								
								<span class="section">Exam Info</span>
								<form:input type="hidden" path="id" id="id"/>
								
								<div class="item form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-6"></label>
									<div class="col-md-3 col-sm-3 col-xs-6" has-feedback>
										<form:input type="text" path="name" id="name" class="form-control col-md-7 col-xs-12 has-feedback-left" name="name" placeholder="Exam Name" required="required"/>
										<span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
										<div class="has-error">
											<form:errors path="name" class="help-inline"/>
										</div>
									</div>
									<div class="col-md-3 col-sm-3 col-xs-6" has-feedback>
										<form:input type="text" path="code" id="code" class="form-control col-md-7 col-xs-12 has-feedback-right" name="code" placeholder="Exam Code" required="required"/>
										<span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
										<div class="has-error">
											<form:errors path="code" class="help-inline"/>
										</div>
									</div>
									
								</div>

								<div class="ln_solid"></div>
								<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<c:choose>
											<c:when test="${edit}">
												<input type="submit" value="Update" class="btn btn-success"/> <a href="<c:url value='/list' />" class="btn btn-primary">Cancel</a>
											</c:when>
											<c:otherwise>
												<input type="submit" value="Create" class="btn btn-success"/> <a href="<c:url value='/list' />" class="btn btn-primary">Cancel</a>
											</c:otherwise>
										</c:choose>
										<!--                           	<button type="submit" class="btn btn-primary">Cancel</button> -->
										<!--                           	<button id="send" type="submit" class="btn btn-success">Submit</button> -->
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
	<jsp:include page="fragments/footer.jsp" />
</html>