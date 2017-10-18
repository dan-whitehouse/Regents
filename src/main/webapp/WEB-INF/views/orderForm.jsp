<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/admin/orderForms/create" var="createOrderFormLink" />
<c:url value="/admin/orderForms/${orderForm.uuid}/edit" var="editOrderFormLink" />

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
							<h2>Order Form </h2>
							<ul class="nav navbar-right panel_toolbox">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
									<ul class="dropdown-menu" role="menu">
										<li><a href="${createOrderFormLink}">Create OrderForm</a>
										<c:if test="${orderForm.locked != true}">
											<li><a href="${editOrderFormLink}">Edit OrderForm</a></li>
										</c:if>
									</ul>
								</li>
							</ul>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
						
						<!-- Date Picker -->
						<div class="form-horizontal form-label-left">
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">Title:</label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        		<div class="form-control col-md-7 col-xs-12 active">
	                        			${orderForm.name}
                        			</div>
	                        	</div>
							</div>
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">Start Date:</label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        		<div class="form-control col-md-7 col-xs-12 active">
	                        			${orderForm.startDate}
                        			</div>
	                        	</div>
							</div>
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">End Date:</label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        		<div class="form-control col-md-7 col-xs-12 active">
	                        			${orderForm.endDate}
                        			</div>
	                        	</div>
							</div>
							
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">Rescan Fee:</label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        		<div class="form-control col-md-7 col-xs-12 active">
	                        			<fmt:formatNumber value = "${orderForm.rescanFee}" type="number" maxFractionDigits="2" minFractionDigits="2" minIntegerDigits="1"/>
                        			</div>
	                        	</div>
							</div>
							
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">In-District Scan Fee:</label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        		<div class="form-control col-md-7 col-xs-12 active">
	                        			<fmt:formatNumber value="${orderForm.inDistrictScanFee}" type="number" maxFractionDigits="2" minFractionDigits="2" minIntegerDigits="1"/>
                        			</div>
	                        	</div>
							</div>
							
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">Student Processing Fee:</label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        		<div class="form-control col-md-7 col-xs-12 active">
	                        			<fmt:formatNumber value="${orderForm.processingFee}" type="number" maxFractionDigits ="2" minFractionDigits = "2" minIntegerDigits="1"/> 
                        			</div>
	                        	</div>
							</div>
							
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">Non-Secure Document Fee:</label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        		<div class="form-control col-md-7 col-xs-12 active">
	                        			<fmt:formatNumber value="${orderForm.nonSecureDocumentFee}" type="number" maxFractionDigits="2" minFractionDigits="2" minIntegerDigits="1"/>
                        			</div>
	                        	</div>
							</div>

							<!-- Exam Selection -->
		                      <div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Exams:</label>
		                        <div class="col-md-6 col-sm-6 col-xs-12">
		                        	<span class="select2 select2-container select2-container--default select2-container--below select2-container--focus" dir="ltr">
										<span class="selection">
											<span class="select2-selection select2-selection--multiple" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="-1">
					                      		<ul class="select2-selection__rendered">	  
		                        					<c:forEach items="${orderForm.orderFormExams}" var="orderFormExam">    			
							                      			<li class="select2-selection__choice" title="${orderFormExam.exam.name}">
							                      				${orderFormExam.exam.name}
							                      			</li>
							                      	</c:forEach>
					                      		</ul>
											</span>
										</span>
										<span class="dropdown-wrapper" aria-hidden="true"></span>
									</span>
		                        </div>
		                      </div>   	
							<!-- Exam Selection -->
							
							<!-- Document Selection -->
		                    <div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Documents:</label>
		                        <div class="col-md-6 col-sm-6 col-xs-12">
		                        	<span class="select2 select2-container select2-container--default select2-container--below select2-container--focus" dir="ltr">
										<span class="selection">
											<span class="select2-selection select2-selection--multiple" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="-1">
					                      		<ul class="select2-selection__rendered">	  
		                        					<c:forEach items="${orderForm.orderFormDocuments}" var="orderFormDocument">    			
							                      			<li class="select2-selection__choice" title="${orderFormDocument.document.name}">
							                      				${orderFormDocument.document.name}
							                      			</li>
							                      	</c:forEach>
					                      		</ul>
											</span>
										</span>
										<span class="dropdown-wrapper" aria-hidden="true"></span>
									</span>
		                        </div>
		                      </div>   	
							<!-- Document Selection -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /page content -->
	
	<jsp:include page="fragments/footer.jsp" />
	
	  <!-- bootstrap-daterangepicker -->
    <script src="<c:url value='/resources/js/moment/moment.min.js' />"></script>
    <script src="<c:url value='/resources/js/datepicker/daterangepicker.js' />"></script>

    <!-- Select2 -->
	<script src="<c:url value='/resources/vendors/select2/dist/js/select2.full.min.js' />"></script>
	
	 <!-- bootstrap-daterangepicker -->
    <script>
		$(document).ready(function() 
		{
		    $('#orderFormStartDate').daterangepicker(
		    {
		      singleDatePicker: true,
		      calender_style: "picker_4"
	    	}, 
		    function(start, end, label) 
		    {
		      console.log(start.toISOString(), end.toISOString(), label);
		    });
		    
		    $('#orderFormEndDate').daterangepicker(
	    	{
		      singleDatePicker: true,
		      calender_style: "picker_4"
	    	}, 
		    function(start, end, label) 
		    {
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
	
</html>