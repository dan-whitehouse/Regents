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
							<h2>Manage Setting</h2>
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
							<form:form method="POST" modelAttribute="district" class="form-horizontal form-label-left" data-toggle="validator" role="form">								
								<span class="section">District Info</span>
								<form:input type="hidden" path="id" id="id"/>
								
								<!-- Name -->
								<div class="item form-group has-feedback">
									<label for="name" class="control-label">Name</label>
								    <div class="input-group col-md-6 col-sm-6 col-xs-6">
								    	<span class="input-group-addon"><i class="fa fa-university"></i></span>
								    	<form:input type="text" path="name" id="name" class="form-control col-md-7 col-xs-12 active" name="name" placeholder="Name" required="required"/>
								    </div>
								    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								    <!-- <div class="help-block with-errors">Hey look, this one has feedback icons!</div> -->
								</div>
								
								<!-- BEDS Code -->
								<div class="item form-group has-feedback">
									<label for="bedsCode" class="control-label">BEDS Code</label>
								    <div class="input-group col-md-6 col-sm-6 col-xs-6">
								    	<span class="input-group-addon"><i class="fa fa-university"></i></span>
								    	<form:input type="text" path="bedsCode" id="bedsCode" class="form-control col-md-7 col-xs-12 active" name="bedsCode" placeholder="BEDS Code" required="required"/>
								    </div>
								    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								    <!-- <div class="help-block with-errors">Hey look, this one has feedback icons!</div> -->
								</div>
								
								
								
							
								<%-- <div class="item form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-6"></label>
									<div class="col-md-3 col-sm-3 col-xs-6" has-feedback>
										<span class="fa fa-university form-control-feedback left" aria-hidden="true"></span>
										<form:input type="text" path="name" id="name" class="form-control col-md-7 col-xs-12 has-feedback-left" name="name" placeholder="Name" required="required"/>
										<div class="has-error">
											<form:errors path="name" class="help-inline"/>
										</div>
									</div>
								</div>
								
								<div class="item form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-6"></label>
									<div class="col-md-3 col-sm-3 col-xs-6" has-feedback>
										<form:input type="text" path="bedsCode" id="bedsCode" class="form-control col-md-7 col-xs-12 has-feedback-left" name="bedsCode" placeholder="BEDS Code" required="required"/>
										<span class="fa fa-university form-control-feedback left" aria-hidden="true"></span>
										<div class="has-error">
											<form:errors path="bedsCode" class="help-inline"/>
										</div>
									</div>
								</div> --%>
								
								<!-- Management -->
								<div class="ln_solid"></div>
								<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<c:choose>
											<c:when test="${edit}">
												<input type="submit" value="Update" class="btn btn-success"/> <a href="<c:url value='/districts' />" class="btn btn-primary">Cancel</a>
											</c:when>
											<c:otherwise>
												<input type="submit" value="Create" class="btn btn-success"/> <a href="<c:url value='/districts' />" class="btn btn-primary">Cancel</a>
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
	<jsp:include page="fragments/footer.jsp" />

    <!-- Select2 -->
	<script src="<c:url value='/resources/vendors/select2/dist/js/select2.full.min.js' />"></script>

	<!-- Select2 -->
    <script>
      $(document).ready(function() {
        $(".select2_single").select2({
          placeholder: "Select a School",
          allowClear: true
        });
        $(".select2_group").select2({});
        $(".select2_multiple").select2({
          maximumSelectionLength: 50,
          placeholder: "With Max Selection limit 50",
          allowClear: true
        });
      });
    </script>
    <!-- /Select2 -->
</html>