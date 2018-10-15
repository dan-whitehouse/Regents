<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<jsp:include page="../fragments/header.jsp" />
<jsp:include page="../fragments/nav.jsp" />
<!-- page variables -->
<c:url value="/createUser" var="createUser" />
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
								<h2>Edit Order Form</h2>
							</c:when>
							<c:otherwise>
								<h2>Create Order Form</h2>
							</c:otherwise>
						</c:choose>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<!-- Date Picker -->
						<form:form method="POST" modelAttribute="orderForm" class="form-horizontal form-label-left" data-toggle="validator" role="form">
							<form:input type="hidden" path="id" id="id"/>
							<div class="item form-group has-feedback">
								<label for="name" class="control-label col-md-3 col-sm-3 col-xs-3 left">Title</label>
								<div class="input-group col-md-6 col-sm-6 col-xs-6">
									<span class="input-group-addon"><i class="fa fa-font"></i></span>
									<form:input type="text" path="name" id="name" class="form-control col-md-7 col-xs-12 active" name="name" placeholder="Order Form Name" required="required"/>
								</div>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								<!-- <div class="help-block with-errors">Hey look, this one has feedback icons!</div> -->
							</div>
							<div class="item form-group has-feedback">
								<label for="period" class="control-label col-md-3 col-sm-3 col-xs-3 left">Period</label>
								<div class="input-group col-md-6 col-sm-6 col-xs-6">
									<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
									<form:select path="period" id="period" name="period" class="date-picker form-control col-md-7 col-xs-12 active" required="required">
										<form:option value=""></form:option>
										<form:option value="August">August</form:option>
										<form:option value="January">January</form:option>
										<form:option value="June">June</form:option>
									</form:select>
								</div>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								<!-- <div class="help-block with-errors">Hey look, this one has feedback icons!</div> -->
							</div>
							<div class="item form-group has-feedback">
								<label for="startDate" class="control-label col-md-3 col-sm-3 col-xs-3 left">Start Date</label>
								<div class="input-group col-md-6 col-sm-6 col-xs-6">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<fmt:formatDate value="${orderForm.startDate}" type="date" pattern="MM/dd/yyyy" var="fStartDate"/>
									<form:input type="text" pattern="(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d" path="startDate" id="orderFormStartDate" value="${fStartDate}" class="date-picker form-control col-md-7 col-xs-12 active" name="startDate" placeholder="Order Form Start Date" required="required"/>
								</div>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								<!-- <div class="help-block with-errors">Hey look, this one has feedback icons!</div> -->
							</div>
							<div class="item form-group has-feedback">
								<label for="endDate" class="control-label col-md-3 col-sm-3 col-xs-3 left">End Date</label>
								<div class="input-group col-md-6 col-sm-6 col-xs-6">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<fmt:formatDate value="${orderForm.endDate}" type="date" pattern="MM/dd/yyyy" var="fEndDate"/>
									<form:input type="text" pattern="(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d" path="endDate" id="orderFormEndDate" value="${fEndDate}" class="date-picker form-control col-md-7 col-xs-12 active" name="endDate" placeholder="Order Form End Date" required="required"/>
								</div>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								<!-- <div class="help-block with-errors">Hey look, this one has feedback icons!</div> -->
							</div>
							<form:input type="hidden" value="0.00" path="rescanFee" id="rescanFee" name="rescanFee"/>
							<form:input type="hidden" value="0.00" path="inDistrictScanFee" id="inDistrictScanFee" name="inDistrictScanFee"/>
							<form:input type="hidden" value="0.00" path="processingFee" id="processingFee" name="processingFee"/>
							<form:input type="hidden" value="0.00" path="nonSecureDocumentFee" id="nonSecureDocumentFee" name="nonSecureDocumentFee"/>
							<div class="item form-group has-feedback">
								<label for="orderFormExams" class="control-label col-md-3 col-sm-3 col-xs-3 left">Exams</label>
								<div class="input-group col-md-6 col-sm-6 col-xs-6">
									<span class="input-group-addon"><i class="fa fa-files-o"></i></span>
									<c:choose>
										<c:when test="${edit}">
											<form:select path="orderFormExams" multiple="true" class="select2_multiple form-control" required="required">
												<c:forEach items="${selectedExams}" var="selectedExam">
													<c:choose>
														<c:when test="${selectedExam.selected eq 'true'}">
															<form:option selected="true" value="${selectedExam.exam.id}" label="${selectedExam.exam.name}"  />
														</c:when>
														<c:otherwise>
															<c:if test="${selectedExam.exam.visible eq true}">
																<form:option value="${selectedExam.exam.id}" label="${selectedExam.exam.name}" />
															</c:if>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</form:select>
										</c:when>
										<c:otherwise>
											<form:select path="orderFormExams" multiple="true" class="select2_multiple form-control" required="required">
												<c:forEach items="${exams}" var="exam" varStatus="status">
													<c:if test="${exam.visible eq true}">
														<form:option value="${exam.id}">${exam.name}</form:option>
													</c:if>
												</c:forEach>
											</form:select>
										</c:otherwise>
									</c:choose>
								</div>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								<!-- <div class="help-block with-errors">Hey look, this one has feedback icons!</div> -->
							</div>
							<div class="item form-group has-feedback">
								<label for="orderFormDocuments" class="control-label col-md-3 col-sm-3 col-xs-3 left">Documents</label>
								<div class="input-group col-md-6 col-sm-6 col-xs-6">
									<span class="input-group-addon"><i class="fa fa-files-o"></i></span>
									<c:choose>
										<c:when test="${edit}">
											<form:select path="orderFormDocuments" multiple="true" class="select2_multiple form-control" required="required">
												<c:forEach items="${selectedDocuments}" var="selectedDocument">
													<c:choose>
														<c:when test="${selectedDocument.selected eq 'true'}">
															<form:option selected="true" value="${selectedDocument.document.id}" label="${selectedDocument.document.name}"  />
														</c:when>
														<c:otherwise>
															<c:if test="${selectedDocument.document.visible eq true}">
																<form:option value="${selectedDocument.document.id}" label="${selectedDocument.document.name}" />
															</c:if>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</form:select>
										</c:when>
										<c:otherwise>
											<form:select path="orderFormDocuments" multiple="true" class="select2_multiple form-control" required="required">
												<c:forEach items="${docs}" var="doc">
													<c:if test="${doc.visible eq true}">
														<form:option value="${doc.id}">${doc.name}</form:option>
													</c:if>
												</c:forEach>
											</form:select>
										</c:otherwise>
									</c:choose>
								</div>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								<!-- <div class="help-block with-errors">Hey look, this one has feedback icons!</div> -->
							</div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<c:choose>
										<c:when test="${edit}">
											<input type="submit" value="Update" class="btn btn-success"/> <a href="<c:url value='/admin/orderForms' />" class="btn btn-primary">Cancel</a>
										</c:when>
										<c:otherwise>
											<input type="submit" value="Add" class="btn btn-success"/> <a href="<c:url value='/admin/orderForms' />" class="btn btn-primary">Cancel</a>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="clearfix"></div>
						</form:form>
						<br /><br /><br />
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
<script src="<c:url value='/resources/js/moment/moment.min.js' />"></script>
<script src="<c:url value='/resources/js/datepicker/daterangepicker.js' />"></script>
<script src="<c:url value='/resources/vendors/select2/dist/js/select2.full.min.js' />"></script>
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
<jsp:include page="../fragments/close.jsp" />