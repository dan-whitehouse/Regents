<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
							<c:choose>
								<c:when test="${edit}">
									<h2>Edit School</h2>
								</c:when>
								<c:otherwise>
									<h2>Create School</h2>
								</c:otherwise>
							</c:choose>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<form:form method="POST" modelAttribute="school" class="form-horizontal form-label-left" data-toggle="validator" role="form">
								<form:input type="hidden" path="id" id="id"/>
								<!-- Name -->
								<div class="item form-group has-feedback">
									<label for="name" class="control-label col-md-3 col-sm-3 col-xs-3 left">Name</label>
									<div class="input-group col-md-6 col-sm-6 col-xs-6">
										<span class="input-group-addon"><i class="fa fa-graduation-cap"></i></span>
										<form:input type="text" path="name" id="name" class="form-control col-md-7 col-xs-12 active" name="name" placeholder="Name" required="required"/>
									</div>
									<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								</div>
								<!-- Districts -->
								<div class="item form-group has-feedback">
									<label for="district" class="control-label col-md-3 col-sm-3 col-xs-3 left">District</label>
									<div class="input-group col-md-6 col-sm-6 col-xs-6">
										<span class="input-group-addon"><i class="fa fa-university"></i></span>
										<form:select path="district" items="${districts}" itemValue="id" itemLabel="name" cssClass="form-control col-md-12 col-xs-12 active" required="required"/>
									</div>
									<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								</div>
								<!-- Management -->
								<div class="ln_solid"></div>
								<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<c:choose>
											<c:when test="${edit}">
												<input type="submit" value="Update" class="btn btn-success"/> <a href="<c:url value='/admin/schools' />" class="btn btn-primary">Cancel</a>
											</c:when>
											<c:otherwise>
												<input type="submit" value="Create" class="btn btn-success"/> <a href="<c:url value='/admin/schools' />" class="btn btn-primary">Cancel</a>
											</c:otherwise>
										</c:choose>
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
</html>