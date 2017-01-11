<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:url value="/createUser" var="createUser" />

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
							<h2>Regents Order Form <small>Beta</small></h2>
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
						
						<!-- Date Picker -->
						<form class="form-horizontal form-label-left">
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">Start Date <span class="required">*</span></label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                          		<input id="birthday" class="date-picker form-control col-md-7 col-xs-12 active" required="required" type="text">
	                        	</div>
							</div>
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">End Date <span class="required">*</span></label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                          		<input id="birthday2" class="date-picker form-control col-md-7 col-xs-12 active" required="required" type="text">
	                        	</div>
							</div>
						</form>		
						<!-- Date Picker -->


							<!-- Exam Selection -->
							<form class="form-horizontal form-label-left">
		                      <div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Exams</label>
		                        <div class="col-md-9 col-sm-9 col-xs-12">
		                          <select class="select2_multiple form-control" multiple="multiple">
		                          	<c:forEach items="${exams}" var="exam">
										<option value="${exam.id}">${exam.name}</option>
									</c:forEach>
		                          </select>
		                        </div>
		                      </div>
		                    </form>				
							<!-- Exam Selection -->
							
							<!-- Document Selection -->
							<form class="form-horizontal form-label-left">
		                      <div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Documents</label>
		                        <div class="col-md-9 col-sm-9 col-xs-12">
		                          <select class="select2_multiple form-control" multiple="multiple">
		                          	<c:forEach items="${documents}" var="document">
										<option value="${document.id}">${document.name}</option>
									</c:forEach>
		                          </select>
		                        </div>
		                      </div>
		                    </form>				
							<!-- Document Selection -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /page content -->
	
	<!-- bootstrap-daterangepicker -->
    <script src="<c:url value='/resources/js/moment/moment.min.js' />"></script>
    <script src="<c:url value='/resources/js/datepicker/daterangepicker.js' />"></script>

    <!-- Select2 -->
	<script src="<c:url value='/resources/vendors/select2/dist/js/select2.full.min.js' />"></script>
	
	
	<!-- bootstrap-daterangepicker -->
    <script>
      $(document).ready(function() {
        $('#birthday').daterangepicker({
          singleDatePicker: true,
          calender_style: "picker_4"
        }, function(start, end, label) {
          console.log(start.toISOString(), end.toISOString(), label);
        });
      });
    </script>
    <!-- /bootstrap-daterangepicker -->
	
	<!-- Select2 -->
    <script>
      $(document).ready(function() {
        $(".select2_single").select2({
          placeholder: "Select a state",
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
	
	<jsp:include page="fragments/footer.jsp" />
</html>