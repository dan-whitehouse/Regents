<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
						<form:form method="POST" modelAttribute="orderForm" class="form-horizontal form-label-left">
							<form:input type="hidden" path="id" id="id"/>	
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">Title <span class="required">*</span></label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        		<form:input type="text" path="name" id="name" class="form-control col-md-7 col-xs-12 active" name="name" placeholder="Order Form Name" required="required"/>
	                        	</div>
							</div>
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">Period <span class="required">*</span></label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                          		<form:select path="period" id="period" name="period" class="date-picker form-control col-md-7 col-xs-12 active"  required="required">
	                          			 <form:option value="August">August</form:option>
	                          			 <form:option value="January">January</form:option>
	                          			 <form:option value="June">June</form:option>
	                          		</form:select>
	                        	</div>
							</div>
							
							<!-- DATES -->
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">Start Date <span class="required">*</span></label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        		<fmt:formatDate value="${orderForm.startDate}" type="date" pattern="MM/dd/yyyy" var="fStartDate"/>
	                          		<form:input type="text" path="startDate" id="orderFormStartDate" value="${fStartDate}" class="date-picker form-control col-md-7 col-xs-12 active" name="startDate" placeholder="Order Form Start Date" required="required"/>
	                        	</div>
							</div>
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">End Date <span class="required">*</span></label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        		<fmt:formatDate value="${orderForm.endDate}" type="date" pattern="MM/dd/yyyy" var="fEndDate"/>
	                          		<form:input type="text" path="endDate" id="orderFormEndDate" value="${fEndDate}" class="date-picker form-control col-md-7 col-xs-12 active" name="endDate" placeholder="Order Form End Date" required="required"/>
	                        	</div>
							</div>
							
							<!-- MONEY AMOUNTS -->
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">Rescan Fee <span class="required">*</span></label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        		<fmt:formatNumber var="fRescanFee" value="${orderForm.rescanFee}" type="number" maxFractionDigits="2" minFractionDigits="2" minIntegerDigits="1"/>
	                          		<form:input type="text" path="rescanFee" id="rescanFee" value="${fRescanFee}" class="form-control col-md-7 col-xs-12 active" name="rescanFee" placeholder="0.00" required="required"/>
	                        	</div>
							</div>
							
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">In-District Scan Fee <span class="required">*</span></label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        		<fmt:formatNumber var="fInDistrictScanFee" value="${orderForm.inDistrictScanFee}" type="number" maxFractionDigits="2" minFractionDigits="2" minIntegerDigits="1"/>
	                          		<form:input type="text" path="inDistrictScanFee" id="inDistrictScanFee" value="${fInDistrictScanFee}" class="form-control col-md-7 col-xs-12 active" name="inDistrictScanFee" placeholder="0.00" required="required"/>
	                        	</div>
							</div>
							
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">Student Processing Fee <span class="required">*</span></label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        		<fmt:formatNumber var="fProcessingFee" value="${orderForm.processingFee}" type="number" maxFractionDigits="2" minFractionDigits="2" minIntegerDigits="1"/>
	                          		<form:input type="text" path="processingFee" id="processingFee" value="${fProcessingFee}" class="form-control col-md-7 col-xs-12 active" name="processingFee" placeholder="0.00" required="required"/>
	                        	</div>
							</div>
							
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">Non-Secure Document Fee <span class="required">*</span></label>
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        		<fmt:formatNumber var="fNonSecureDocumentFee" value="${orderForm.nonSecureDocumentFee}" type="number" maxFractionDigits="2" minFractionDigits="2" minIntegerDigits="1"/>
	                          		<form:input type="text" path="nonSecureDocumentFee" id="nonSecureDocumentFee" value="${fNonSecureDocumentFee}" class="form-control col-md-7 col-xs-12 active" name="nonSecureDocumentFee" placeholder="0.00" required="required"/>
	                        	</div>
							</div>

							<!-- Exam Selection -->
		                      <div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Exams</label>
		                        <div class="col-md-6 col-sm-6 col-xs-12">
			                       <c:choose>
			                       		<c:when test="${edit}">
			                       			<form:select path="orderFormExams" multiple="true" class="select2_multiple form-control">
			                       				<c:forEach items="${selectedExams}" var="selectedExam">
			                       					<c:choose>
														<c:when test="${selectedExam.selected eq 'true'}">
															<form:option selected="true" value="${selectedExam.exam.id}" label="${selectedExam.exam.name}"  />
				                       					</c:when>
			                       						<c:otherwise>
				                      						<form:option value="${selectedExam.exam.id}" label="${selectedExam.exam.name}" />
 				                      					</c:otherwise>
 				                      				</c:choose>
			                       				</c:forEach>
			                       			</form:select>
		                       			</c:when>
		                       			<c:otherwise>
		                       					<form:select path="orderFormExams" multiple="true" class="select2_multiple form-control">
						                          	<c:forEach items="${exams}" var="exam" varStatus="status">
														<form:option value="${exam.id}">${exam.name}</form:option>
													</c:forEach>
					                          	</form:select>
										</c:otherwise>
		                       		</c:choose>
		                        </div>
		                      </div>   	
							<!-- Exam Selection -->
							
							<!-- Document Selection -->
		                      <div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Documents</label>
		                        <div class="col-md-6 col-sm-6 col-xs-12">
		                        
		                        	 <c:choose>
			                       		<c:when test="${edit}">
				                        	<form:select path="orderFormDocuments" multiple="true" class="select2_multiple form-control">
					                          	<c:forEach items="${selectedDocuments}" var="selectedDocument">
													<c:choose>
														<c:when test="${selectedDocument.selected eq 'true'}">
															<form:option selected="true" value="${selectedDocument.document.id}" label="${selectedDocument.document.name}"  />
				                       					</c:when>
			                       						<c:otherwise>
				                      						<form:option value="${selectedDocument.document.id}" label="${selectedDocument.document.name}" />
 				                      					</c:otherwise>
 				                      				</c:choose>
												</c:forEach>
						                    </form:select>
						                    </c:when>
		                       			<c:otherwise>
		                       				<form:select path="orderFormDocuments" multiple="true" class="select2_multiple form-control">
					                          	<c:forEach items="${docs}" var="doc">
													<form:option value="${doc.id}">${doc.name}</form:option>
												</c:forEach>
						                    </form:select>
			                    		</c:otherwise>
		                       		</c:choose>
		                        </div>
		                      </div>   	
							<!-- Document Selection -->
							
							<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<c:choose>
											<c:when test="${edit}">
												<input type="submit" value="Update" class="btn btn-success"/> <a href="<c:url value='/admin/orderForms/${orderForm.uuid}' />" class="btn btn-primary">Cancel</a>
											</c:when>
											<c:otherwise>
												<input type="submit" value="Add" class="btn btn-success"/> <a href="<c:url value='/admin/orderForms' />" class="btn btn-primary">Cancel</a>
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