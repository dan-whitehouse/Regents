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
							<h2>Opt Out</h2>			
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<form:form method="POST" modelAttribute="optout" class="form-horizontal form-label-left">								
								<div class="item form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">District <span class="required">*</span>
									</label>
									<div class="col-md-6 col-sm-6 col-xs-12">									
<%-- 										<form:select path="districtByUser" items="${district}" multiple="false" itemValue="id" itemLabel="type" class="form-control col-md-7 col-xs-12" required="required"/> --%>
									</div>
								</div>
								
									<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<div class="form-group">
											<p>By "Opting Out", you signify that you are not testing for this regents period and have permission to "Opt Out" from an appropriate person who can authorize this.</p>
										</div>
										<input type="submit" value="Opt Out" class="btn btn-success"/> <a href="<c:url value='/orderForms' />" class="btn btn-primary">Cancel</a>
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
          placeholder: "Select a state",
          allowClear: true
        });
        $(".select2_group").select2({});
        $(".select2_multiple").select2({
          maximumSelectionLength: 10,
          placeholder: "With Max Selection limit 10",
          allowClear: true
        });
      });
    </script>
    <!-- /Select2 -->
</html>