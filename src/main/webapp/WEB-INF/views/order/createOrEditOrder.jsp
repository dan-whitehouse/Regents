<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/order" var="order" />
<fmt:formatDate var="schoolYear" value="${orderForm.startDate}" pattern="yyyy" />
<fmt:formatNumber var="rescanFee" value="${orderForm.rescanFee}" type="currency"/>
<fmt:formatNumber var="inDistrictScanFee" value="${orderForm.inDistrictScanFee}" type="currency"/>
<fmt:formatNumber var="nonSecureDocumentFee" value="${orderForm.nonSecureDocumentFee}" type="currency"/>
<fmt:formatNumber var="processingFee" value="${orderForm.processingFee}" type="currency"/>
<fmt:formatDate value="${orderForm.endDate}" type="date" pattern="MM/dd/yyyy" var="endDate"/>
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
							<h2>Regents Order Form </h2>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<!-- Wizard 2 -->
							<div class="container">
								<div class="row">
									<div class="col-xs-12">
										<ul class="nav nav-pills nav-justified thumbnail2 setup-panel">
											<li class="disabled">
												<a href="#step-1">
													<h4 class="list-group-item-heading">Step 1</h4>
													<p class="list-group-item-text">Info</p>
												</a>
											</li>
											<li class="active">
												<a href="#step-2">
													<h4 class="list-group-item-heading">Step 2</h4>
													<p class="list-group-item-text">Regents Exams</p>
												</a>
											</li>
											<li class="enabled">
												<a href="#step-3">
													<h4 class="list-group-item-heading">Step 3</h4>
													<p class="list-group-item-text">Location for Blanks</p>
												</a>
											</li>
											<li class="enabled">
												<a href="#step-4">
													<h4 class="list-group-item-heading">Step 4</h4>
													<p class="list-group-item-text">Non-Secure Documents</p>
												</a>
											</li>
											<li class="enabled">
												<a href="#step-5">
													<h4 class="list-group-item-heading">Step 5</h4>
													<p class="list-group-item-text">Options</p>
												</a>
											</li>
											<li class="enabled">
												<a href="#step-6">
													<h4 class="list-group-item-heading">Step 6</h4>
													<p class="list-group-item-text">Contact</p>
												</a>
											</li>
											<li class="enabled">
												<a href="#step-7" onclick="review();">
													<h4 class="list-group-item-heading">Step 7</h4>
													<p class="list-group-item-text">Review</p>
												</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<form:form method="POST" modelAttribute="xForm2" cssClass="container" data-toggle="validator" role="form">
								<!-- STEP 1 - INFO -->
								<div class="row setup-content" id="step-1">
									<div class="col-xs-12">
										<div class="col-md-12 well text-center">
											<div class="col-md-12 col-sm-12 col-xs-12">
												<h2 class="StepTitle text-center">${orderForm.period} ${schoolYear} - REGENTS ANSWER SHEET SERVICES & NON SECURE DOCUMENTS ORDER FORM</h2>
												<p>
													<strong><u>Directions:</u> The order form and data file MUST be received by NERIC no later than ${endDate}.</strong>
												</p>
												<p>
													<strong><u>Student Demographic Data File:</u></strong>
													If you are ordering preprinted answer sheets your district needs to provide NERIC with a student data file 
													securely through Serv-U. You can find the student data file template 
													<a href="http://neric.org/Testing/Regents.cfm" class="link" target="_blank">here</a>, 
													and directions on how to upload files to Serv-U <a href="http://neric.org/documents/Testing/How%20to%20Upload%20Files%20to%20ServU.pdf" class="link" target="_blank">here</a>. If you do not have access to Serv-U, a request for a secure link can be e-mailed 
													to <u><a href="mailto:testing@neric.org">testing@neric.org</a></u>. 
												<p class="text-danger">No student data files should be e-mailed to NERIC</p>
												</p>
												<p>
													<strong>Billing: </strong>
													Beginning with the 2018-2019 school year, NERIC is moving to a pay one price model based on RWADA (student counts for non-public schools) for testing services.
													This will simplify the billing process for both BOCES and districts and you will now have exact testing charges ahead of time for budget planning rather than having to wait much later in the year to be billed for number of tests scanned. 
													<strong>This model will include all scanning and non-secure documents charges for the Regents Exams.</strong> 
													If you are a public school district, this will be implemented immediately on the commitment forms that were sent to districts in February 2018.  
													Non-public schools will be billed in the fall of 2018 for the 2018-2019 testing charge.  
													Questions about this billing model can be directed to <u><a href="mailto:testing@neric.org">testing@neric.org</a></u>.<br> 
												</p>
												<p>This form must be submitted with the approval of your districts superintendent, business official or whoever else is authorized to approve this expenditure.</p>
											</div>
										</div>
									</div>
								</div>
								<!-- STEP 2 - EXAMS -->
								<div class="row setup-content" id="step-2">
									<div class="col-xs-12">
										<div class="col-md-12 well text-center">
											<!-- <form> -->               
											<div class="container col-xs-12">
												<div class="row clearfix">
													<div class="col-md-12 column">
														<table class="table" id="tab_logic">
															<thead>
																<tr>
																	<th width="75px">Order <input type="checkbox" id="isCheckedExams" onclick="selectAllExams()"/></th>
																	<th class="col-md-5 col-xs-12">Exam Name</th>
																	<c:if test="${period eq 'June'}">
																		<th># Students Pull from L0
																			<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="Pull from Level 0: Number of students enrolled in a course resulting in an exam." >
																			<span class=" fa fa-info"></span>
																			</span>
																		</th>
																	</c:if>
																	<th># Blank Answer Sheets 
																		<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="Number of blank in-district answer sheets." >
																		<span class=" fa fa-info"></span>
																		</span>
																	</th>
																	<th># Students in CSV
																		<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="Number of students included on the CSV file." >
																		<span class=" fa fa-info"></span>
																		</span>
																	</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${allExamOptionsByOrderForm}" var="e" varStatus="status">
																	${orderForm.period}
																	<form:hidden path="selectedExams[${status.index}].orderExam.exam.id" value="${e.orderExam.exam.id}"/>
																	<tr>
																		<td>
																			<form:checkbox path="selectedExams[${status.index}].selected" class="flat"/>
																		</td>
																		<td>
																			<form:input path="selectedExams[${status.index}].orderExam.exam.name" value="${e.orderExam.exam.name } - ${e.orderExam.exam.code}"  class="form-control col-md-3 col-xs-12" readonly="true"/>
																		</td>
																		<c:if test="${period eq 'June'}">
																			<td>
																				<div class="item form-group has-feedback">
																					<form:input path="selectedExams[${status.index}].orderExam.examAmount" type="number" min="0" step="1" class="form-control col-md-3 col-xs-12" />
																					<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
																				</div>
																			</td>
																		</c:if>
																		<td>
																			<div class="item form-group has-feedback">
																				<form:input path="selectedExams[${status.index}].orderExam.answerSheetAmount" type="number" min="0" step="1" class="form-control col-md-3 col-xs-12" />
																				<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
																			</div>
																		</td>
																		<td>
																			<div class="item form-group has-feedback">
																				<form:input path="selectedExams[${status.index}].orderExam.studentsPerCSV" type="number" min="0" step="1" class="form-control col-md-3 col-xs-12" />
																				<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
																			</div>
																		</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</div>
											</div>
											<a id="activate-step-3" class="btn btn-primary btn-md">Next</a>
										</div>
									</div>
								</div>
								<!--  STEP 3 - BLANKS -->
								<div class="row setup-content" id="step-3">
									<div class="col-xs-12">
										<div class="col-md-12 well text-center">
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback input-group">
												<!-- id is used in javascript -->
												<span class="input-group-addon"><i class="fa fa-university"></i></span>
												<form:select path="district" id="districtList" items="${userDistricts}" itemValue="id" itemLabel="name" onchange="updateSchoolList()" cssClass="form-control col-md-12 col-xs-12"/>
											</div>
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback input-group">
												<!-- id is used in javascript -->
												<span class="input-group-addon"><i class="fa fa-graduation-cap"></i></span>
												<form:select path="school" id="schoolList" cssClass="form-control col-md-12 col-xs-12"/>
											</div>
										</div>
									</div>
									<div class="col-xs-12">
										<div class="col-md-12 well text-center">
											<h2 class="text-center"> Special Requests for Blanks for Additional Locations</h2>
											<p class="text-center"> Requests <b><u>MUST</u></b> include DW Location code, which exam(s), and amount needed.</p>
											<div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback">
												<!-- id is used in javascript -->
												<form:textarea path="specialRequests" id="specialRequests" rows="10" maxlength="1000" cssClass="form-control col-md-12 col-xs-12 has-feedback-left"/>
												<span class="fa fa-plus form-control-feedback left" aria-hidden="true"></span>
											</div>
											<a id="deactivate-step-3" class="btn btn-primary btn-md">Back</a>
											<a id="activate-step-4" class="btn btn-primary btn-md">Next</a>
										</div>
									</div>
								</div>
								<!-- STEP 4 - DOCUMENTS -->
								<div class="row setup-content" id="step-4">
									<div class="col-xs-12">
										<div class="alert alert-softYellow alert-dismissible fade in" role="alert">
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">X</span>
											</button>
											Non-secure documents include Essay Booklets for ELA, USH and GH, and Reference Tables for the Sciences. 
											Charges are included in the new billing model.
										</div>
										<div class="col-md-12 well text-center">
											<table class="table">
												<thead>
													<tr>
														<th width="75px">Order <input type="checkbox" id="isCheckedDocuments" onclick="selectAllDocuments()"/></th>
														<th>Non-Secure Document</th>
														<th>Quantity</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${allDocumentOptionsByOrderForm}" var="d" varStatus="status">
														<form:hidden path="selectedDocuments[${status.index}].orderDocument.document.id" value="${d.orderDocument.document.id}"/>
														<tr>
															<td>
																<form:checkbox path="selectedDocuments[${status.index}].selected" class="flat"/>
															</td>
															<td>
																<form:input path="selectedDocuments[${status.index}].orderDocument.document.name" value="${d.orderDocument.document.name }"  class="form-control col-md-3 col-xs-12" readonly="true" />
															</td>
															<td>
																<div class="item form-group has-feedback">
																	<form:input path="selectedDocuments[${status.index}].orderDocument.documentAmount" type="number" min="0" step="1" class="form-control col-md-3 col-xs-12" />
																	<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
																</div>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<a id="deactivate-step-4" class="btn btn-primary btn-md">Back</a>
											<a id="activate-step-5" class="btn btn-primary btn-md">Next</a>
										</div>
									</div>
								</div>
								<!-- STEP 5 - OPTIONS -->
								<div class="row setup-content" id="step-5">
									<div class="col-xs-12">
										<div class="col-md-12 well">
											<div class="form-group col-xs-12">
												<label class="control-label col-md-6 col-sm-6 col-xs-12">Reporting Option: 
												<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="For January and June Regents NERIC will load scores in L1. NERIC will NOT load score for August." >
												<span class=" fa fa-info"></span>
												</span>
												</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<div class="checkbox">
														<c:choose>
															<c:when test="${orderForm.period eq 'August'}">
																<form:checkbox path="reportingOption" name="reportingOption" cssClass="form-control has-feedback-left flat " label="NERIC will load scores into Level 1" disabled="true"/>
															</c:when>
															<c:otherwise>
																<form:checkbox path="reportingOption" name="reportingOption" cssClass="form-control has-feedback-left flat" label="NERIC will load scores into Level 1" disabled="true" checked="checked"/>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</div>
											<br />
											<div class="clearfix"></div>
											<div class="form-group col-xs-12 has-feedback">
												<label class="control-label col-md-6 col-sm-6 col-xs-12">Printing Option: 
												<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="If nothing is selected, Alpha will be chosen by default." >
												<span class=" fa fa-info"></span>
												</span>
												</label>
												<div class="input-group col-md-6 col-sm-6 col-xs-12">
													<span class="input-group-addon"><i class="fa fa-print"></i></span>
													<form:select path="selectedOptionPrint" items="${allPrintOptions}" itemValue="id" itemLabel="name" cssClass="form-control"/>
												</div>
											</div>
											<div class="clearfix"></div>
											<div class="form-group col-xs-12 has-feedback">
												<label class="control-label col-md-6 col-sm-6 col-xs-12">Answer Sheets Scanning Option:</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<div class="radio">
														<ul class="list-unstyled">
															<form:radiobuttons path="selectedOptionScan" name="selectedOptionScan" items="${allScanOptions}" itemValue="id" itemLabel="name" cssClass="radio flat form-control" element="li" required="required"/>
														</ul>
													</div>
												</div>
											</div>
											<div class="col-md-12 text-center">
												<br />
												<a id="deactivate-step-5" class="btn btn-primary btn-md">Back</a>
												<a id="activate-step-6" class="btn btn-primary btn-md">Next</a>
											</div>
										</div>
									</div>
								</div>
								<!--  STEP 6 - CONTACT -->
								<div class="row setup-content" id="step-6">
									<div class="col-xs-12">
										<div class="col-md-12 well form-horizontal form-label-left text-center">
											<div class="item form-group has-feedback">
												<label for="firstName" class="control-label col-md-3 col-sm-3 col-xs-3 left">First Name</label>
												<div class="input-group col-md-6 col-sm-6 col-xs-6">
													<span class="input-group-addon"><i class="fa fa-user"></i></span>
													<form:input type="text" pattern="^[\w\-\s]+$" maxlength="50" path="orderContact.firstName" id="orderContact.firstName" class="form-control col-md-7 col-xs-12" name="firstName" placeholder="First Name"  required="required" />
												</div>
												<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
											</div>
											<div class="item form-group has-feedback">
												<label for="middleName" class="control-label col-md-3 col-sm-3 col-xs-3 left">Middle Name</label>
												<div class="input-group col-md-6 col-sm-6 col-xs-6">
													<span class="input-group-addon"><i class="fa fa-user"></i></span>
													<form:input type="text" pattern="^[\w\-\s]+$" maxlength="50" path="orderContact.middleName" id="orderContact.middleName" class="form-control col-md-7 col-xs-12" name="middleName" placeholder="Middle Name"/>
												</div>
												<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
											</div>
											<div class="item form-group has-feedback">
												<label for="lastName" class="control-label col-md-3 col-sm-3 col-xs-3 left">Last Name</label>
												<div class="input-group col-md-6 col-sm-6 col-xs-6">
													<span class="input-group-addon"><i class="fa fa-user"></i></span>
													<form:input type="text" pattern="^[\w\-\s]+$" maxlength="50" path="orderContact.lastName" id="orderContact.lastName" class="form-control col-md-7 col-xs-12" name="lastName" placeholder="Last Name"  required="required" />
												</div>
												<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
											</div>
											<div class="item form-group has-feedback">
												<label for="title" class="control-label col-md-3 col-sm-3 col-xs-3 left">Title</label>
												<div class="input-group col-md-6 col-sm-6 col-xs-6">
													<span class="input-group-addon"><i class="fa fa-user"></i></span>
													<form:input type="text" pattern="^[\w\-\s]+$" maxlength="50" path="orderContact.title" id="orderContact.title" class="form-control col-md-7 col-xs-12" name="title" placeholder="Title"  required="required" />
												</div>
												<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
											</div>
											<div class="item form-group has-feedback">
												<label for="email" class="control-label col-md-3 col-sm-3 col-xs-3 left">Email</label>
												<div class="input-group col-md-6 col-sm-6 col-xs-6">
													<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
													<form:input type="email" maxlength="50" path="orderContact.email" id="orderContact.email" class="form-control col-md-7 col-xs-12" name="email" placeholder="Email" required="required" />
												</div>
												<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
											</div>
											<div class="item form-group has-feedback">
												<label for="phone" class="control-label col-md-3 col-sm-3 col-xs-3 left">Phone</label>
												<div class="input-group col-md-6 col-sm-6 col-xs-6">
													<span class="input-group-addon"><i class="fa fa-phone"></i></span>
													<!-- pattern="^\s*(?:\+?(\d{1,3}))?[-. (]*(\d{3})[-. )]*(\d{3})[-. ]*(\d{4})(?: *ext(\d+))?\s*$" -->
													<form:input type="text" maxlength="50" path="orderContact.phone" id="orderContact.phone" class="form-control col-md-7 col-xs-12" name="phone" placeholder="Phone"  required="required" />
												</div>
												<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
											</div>
											<c:if test="${xForm2.orderForm.period ne 'August'}">
												<a id="deactivate-step-6" class="btn btn-primary btn-md">Back</a>
												<a id="activate-step-7" class="btn btn-primary btn-md text-center" onclick="review()">Review</a>
											</c:if>
										</div>
									</div>
									<c:if test="${xForm2.orderForm.period eq 'August'}">
										<div class="col-xs-12">
											<div class="col-md-12 well text-center">
												<h2 class="text-center"> Alternate Shipping Information</h2>
												<div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback">
													<!-- id is used in javascript -->
													<form:textarea path="orderContact.altContactInfo" id="orderContact.altContactInfo" rows="10" maxlength="1000" cssClass="form-control col-md-12 col-xs-12 has-feedback-left"/>
													<span class="fa fa-truck form-control-feedback left" aria-hidden="true"></span>
												</div>
												<a id="deactivate-step-6" class="btn btn-primary btn-md">Back</a>
												<a id="activate-step-7" class="btn btn-primary btn-md text-center" onclick="review()">Review</a>
											</div>
										</div>
									</c:if>
								</div>
								<!--  STEP 7 - REVIEW -->
								<div class="row setup-content" id="step-7">
									<div class="col-xs-12">
										<div class="col-md-12 well">
											<!-- START EXAMS -->
											<div class="col-md-7 col-sm-7 col-xs-7" >
												<div class="x_panel">
													<div class="x_title">
														<h2>Regents Exams</h2>
														<div class="clearfix"></div>
													</div>
													<div class="x_content">
														<div class="table-responsive">
															<table class="table table-striped jambo_table bulk_action">
																<thead>
																	<tr class="headings">
																		<th class="column-title">Exam Name </th>
																		<c:if test="${period eq 'June'}">
																			<th class="column-title">Students Pull from L0</th>
																		</c:if>
																		<th class="column-title">Blank Answer Sheets </th>
																		<th class="column-title">Students in CSV </th>
																	</tr>
																</thead>
																<tbody id="reviewExams">
																	<tr class="even pointer">
																		<td class=" ">Algebra 2 / Trigonometry - 02052CC</td>
																		<c:if test="${period eq 'June'}">
																			<td class=" ">61</td>
																		</c:if>
																		<td class=" ">10</td>
																		<td class=" ">1</td>
																	</tr>
																	<tr class="odd pointer">
																		<td class=" ">ELA (Common Core) - 01003CC</td>
																		<c:if test="${period eq 'June'}">
																			<td class=" ">116</td>
																		</c:if>
																		<td class=" ">20</td>
																		<td class=" ">0</td>
																	</tr>
																</tbody>
															</table>
														</div>
													</div>
												</div>
											</div>
											<!-- END EXAMS -->
											<!-- START DOCUMENTS -->
											<div class="col-md-5 col-sm-5 col-xs-5">
												<div class="x_panel">
													<div class="x_title">
														<h2>Non-Secure Documents</h2>
														<div class="clearfix"></div>
													</div>
													<div class="x_content">
														<div class="table-responsive">
															<table class="table table-striped jambo_table bulk_action">
																<thead>
																	<tr class="headings">
																		<th class="column-title">Non-Secure Document </th>
																		<th class="column-title">Quantity </th>
																	</tr>
																</thead>
																<tbody id="reviewDocuments">
																	<tr class="even pointer">
																		<td class=" ">Temp0</td>
																		<td class=" ">0</td>
																	</tr>
																	<tr class="odd pointer">
																		<td class=" ">Temp1</td>
																		<td class=" ">0</td>
																	</tr>
																</tbody>
															</table>
														</div>
													</div>
												</div>
											</div>
											<!-- END DOCUMENTS -->
											<!-- START BLANKS -->
											<div class="col-md-5 col-sm-5 col-xs-5 pull-right">
												<div class="x_panel">
													<div class="x_title">
														<h2>Location for Blanks</h2>
														<div class="clearfix"></div>
													</div>
													<div class="x_content">
														<ul class="list-unstyled">
															<li>
																<i class="fa fa-university"></i> <strong>District: </strong>
																<span id="reviewBlanks_district"></span>
															</li>
															<li>
																<i class="fa fa-graduation-cap"></i> <strong>School: </strong>
																<span id="reviewBlanks_school"></span>
															</li>
															<hr id="reviewBlanks_specialRequests_hr"/>
															<li id="reviewBlanks_specialRequests_li">
																<i class="fa fa-plus"></i>
																<strong>Special Requests:</strong><br />
																<span id="reviewBlanks_specialRequests" class="break-word"> </span>
															</li>
														</ul>
													</div>
												</div>
											</div>
											<!-- END BLANKS -->
											<div class="clearfix"></div>
											<!-- START OPTIONS -->
											<div class="col-md-7 col-sm-7 col-xs-7">
												<div class="x_panel">
													<div class="x_title">
														<h2>Options</h2>
														<div class="clearfix"></div>
													</div>
													<div class="x_content">
														<div class="table-responsive">
															<table class="table table-striped jambo_table bulk_action">
																<thead>
																	<tr class="headings">
																		<th class="column-title">Option </th>
																		<th class="column-title">Number Requested </th>
																	</tr>
																</thead>
																<tbody>
																	<tr class="even pointer">
																		<td class=" ">Scanning/Scoring Option: </td>
																		<td class=" ">
																			<input type="text" class="form-control" readonly="readonly" id="reviewScanOption" value="">
																		</td>
																	</tr>
																	<tr class="odd pointer">
																		<td class=" ">Printing Option: </td>
																		<td class=" ">
																			<input type="text" class="form-control" readonly="readonly" id="reviewPrintOption" value="">
																		</td>
																	</tr>
																	<tr class="even pointer">
																		<td class=" ">Reporting Option: </td>
																		<td class=" "> 
																			<input type="text" class="form-control" readonly="readonly" id="reviewReportingOption" value=""> 
																		</td>
																	</tr>
																</tbody>
															</table>
														</div>
													</div>
												</div>
											</div>
											<!-- END OPTIONS -->
											<!-- CONTACT -->
											<div class="col-md-5 col-sm-5 col-xs-5" >
												<div class="x_panel">
													<div class="x_title">
														<h2>Contact</h2>
														<div class="clearfix"></div>
													</div>
													<div class="profile_details">
														<div class="profile_view">
															<div class="col-xs-12">
																<h2>
																	<span id="reviewContactInfo_firstName"> </span> 
																	<span id="reviewContactInfo_middleName"> </span> 
																	<span id="reviewContactInfo_lastName"> </span> 
																</h2>
																<span id="reviewContactInfo_title" class="font-italic"> </span>
																<ul class="list-unstyled">
																	<li id="reviewContactInfo_email_li">
																		<i class="fa fa-envelope"></i> <strong>Email: </strong>
																		<span id="reviewContactInfo_email"></span>
																	</li>
																	<li id="reviewContactInfo_phone_li">
																		<i class="fa fa-phone"></i> <strong>Phone: </strong>
																		<span id="reviewContactInfo_phone"></span>
																	</li>
																	<c:if test="${period eq 'August'}">
																		<hr id="reviewContactInfo_alt_hr"/>
																		<li id="reviewContactInfo_alt_li">
																			<i class="fa fa-truck"></i>
																			<strong>Alternate Shipping Information:</strong><br />
																			<span id="reviewContactInfo_alt" class="break-word"> </span>
																		</li>
																	</c:if>
																</ul>
															</div>
														</div>
													</div>
												</div>
											</div>
											<!-- END CONTACT -->
											<div class="clearfix"></div>
											<!-- START SUBMIT -->
											<div class="col-md-12 col-sm-12 col-xs-12">
												<div class="x_panel">
													<div class="x_title">
														<h2 class="pull-left">Submit</h2>
														<a class="btn btn-default pull-right" onclick="window.print();"><i class="fa fa-print"></i> Print</a>
														<div class="clearfix"></div>
													</div>
													<div class="x_content text-center">
														<div class="form-group">
															<p class="text-danger"><strong>By clicking "Submit", you signify that you have permission to order these Regents forms from an appropriate person who can authorize this expenditure.</strong></p>
														</div>
														<sec:authorize access="hasRole('USER')">
															<input type="submit" value="Submit" class="btn btn-success"/> <a id="deactivate-step-7" class="btn btn-primary btn-md">Back</a> <a href="<c:url value='/orders' />" class="btn btn-primary">Cancel</a>
														</sec:authorize>
														<sec:authorize access="hasRole('ADMIN')">
															<input type="submit" value="Submit" class="btn btn-success"/> <a id="deactivate-step-7" class="btn btn-primary btn-md">Back</a> <a href="<c:url value='/admin/orders' />" class="btn btn-primary">Cancel</a>
														</sec:authorize>
													</div>
												</div>
											</div>
											<!-- END SUBMIT -->
										</div>
									</div>
								</div>
								<!--  END STEP 6 - REVIEW -->
							</form:form>
							<!-- End Wizard -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /page content -->
	<script type="text/javascript">
		function review() 
		{				
			//Exams
			exams();
			
			//Documents
			blanks();
			
			//Documents
			documents();
			
			//Printing Option
			printOption();
			
			//Scanning Option
			scanOption();
			
			//Reporting Option
			reportingOption();
			
			//Contact Info
			contactInfo();
		}
		
		function exams()
		{
			$('#reviewExams').empty();
			
			var i, count = 0;
			$('input[id^="selectedExams"][id$="selected1"]').each(function() { count++ });
			
			for(i = 0; i <= count-1; i++)
			{
				if(document.getElementById('selectedExams' + i + '.selected1').checked) 
				{
					var examName = document.getElementById("selectedExams" + i + ".orderExam.exam.name").value;	
					var answerSheetAmount = document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").value;
					var studentsPerCSV = document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").value;
					var evenOddClass = 'odd';
					
					if(answerSheetAmount == '')
					{
						answerSheetAmount = '0';
					}
					
					if(studentsPerCSV == '')
					{
						studentsPerCSV = '0';
					}
					
					if(isEven(i))
					{
						evenOddClass = 'even'
					}
										
					<c:choose>
						<c:when test="${period eq 'June'}">
							var examAmount = document.getElementById("selectedExams" + i + ".orderExam.examAmount").value;
							if(examAmount == '') {
								examAmount = '0';
							}
							$('#reviewExams').append("<tr class='" + evenOddClass + " pointer'><td>" + examName + "</td><td>" + examAmount + "</td><td>" + answerSheetAmount + "</td><td>" + studentsPerCSV + "</td></tr>");
						</c:when>
						<c:otherwise>
							$('#reviewExams').append("<tr class='" + evenOddClass + " pointer'><td>" + examName + "</td><td>" + answerSheetAmount + "</td><td>" + studentsPerCSV + "</td></tr>");
						</c:otherwise>
					</c:choose>
				}
			} 
		}
		
		function blanks()
		{
			var selectedDistrict = document.getElementById("districtList");		
			var selectedSchool = document.getElementById("schoolList");
		
			$('#reviewBlanks_district').empty();
			$('#reviewBlanks_school').empty();
			$('#reviewBlanks_specialRequests').empty();
			
			$('#reviewBlanks_district').append(selectedDistrict.options[selectedDistrict.selectedIndex].text);
			$('#reviewBlanks_school').append(selectedSchool.options[selectedSchool.selectedIndex].text);
		
			var alt = document.getElementById("specialRequests").value;
			if(alt != null && alt != "")
			{
				$('#reviewBlanks_specialRequests_hr').show();
				$('#reviewBlanks_specialRequests_li').show();
				
				var lines = alt.split('\n');   // lines is an array of strings
				for (var j = 0; j < lines.length; j++) {
				  $('#reviewBlanks_specialRequests').append(lines[j] + "<br />");
				}
			}
			else {
				$('#reviewBlanks_specialRequests_hr').hide();
				$('#reviewBlanks_specialRequests_li').hide();
			}
		}
		
		function documents()
		{
			$('#reviewDocuments').empty();
			
			var i, count = 0;
			$('input[id^="selectedDocuments"][id$="selected1"]').each(function() { count++ });
			
			for(i = 0; i <= count-1; i++)
			{
				if(document.getElementById('selectedDocuments' + i + '.selected1').checked) 
				{
					var docName = document.getElementById("selectedDocuments" + i + ".orderDocument.document.name").value;
					var docAmount = document.getElementById("selectedDocuments" + i + ".orderDocument.documentAmount").value;
					
					if(docAmount == '')
					{
						docAmount = '0';
					}
					
					$('#reviewDocuments').append("<tr class='even pointer'><td>" + docName + "</td><td>" + docAmount + "</td></tr>");
				}
			} 
		}
		
		function printOption()
		{
			var selectedOptionPrint = document.getElementById("selectedOptionPrint");
			var reviewPrintOption = document.getElementById("reviewPrintOption");
			reviewPrintOption.value = selectedOptionPrint.options[selectedOptionPrint.selectedIndex].text;
		}
		
		function scanOption()
		{
			var selectedOptionScanIndex = getRadioButtonIndex('selectedOptionScan');
			var selectedOptionScan = '';
			var reviewScanOption = document.getElementById("reviewScanOption");
			if(selectedOptionScanIndex != null)
			{
				selectedOptionScan = document.querySelector('label[for="selectedOptionScan' + selectedOptionScanIndex + '"]').textContent;
				reviewScanOption.value = selectedOptionScan;
			}
		}
		
		function reportingOption()
		{
			var reportingOption = false;
			if(document.getElementById('reportingOption1').checked) 
			{
				reportingOption = true;
			}
			var reviewReportingOption = document.getElementById("reviewReportingOption");
			if(reportingOption){reviewReportingOption.value = 'NERIC will load scores into Level 1'}
			else {reviewReportingOption.value = 'NERIC will NOT load scores into Level 1'}
		}
		
		function contactInfo()
		{
			var firstName = document.getElementById("orderContact.firstName").value;
			var middleName = document.getElementById("orderContact.middleName").value;
			var lastName = document.getElementById("orderContact.lastName").value;
			var title = document.getElementById("orderContact.title").value;
			var email = document.getElementById("orderContact.email").value;
			var phone = document.getElementById("orderContact.phone").value;
		
			$('#reviewContactInfo_firstName').empty();
			$('#reviewContactInfo_middleName').empty();
			$('#reviewContactInfo_lastName').empty();
			$('#reviewContactInfo_title').empty();
			$('#reviewContactInfo_email').empty();
			$('#reviewContactInfo_phone').empty();
			$('#reviewContactInfo_alt').empty();
					
			$('#reviewContactInfo_firstName').append(firstName);
			$('#reviewContactInfo_middleName').append(middleName);
			$('#reviewContactInfo_lastName').append(lastName);
			$('#reviewContactInfo_title').append(title);
			$('#reviewContactInfo_email').append(email);
			$('#reviewContactInfo_phone').append(phone);
			
			if(email != null && email != "")
			{
				$('#reviewContactInfo_email_li').show();
			}
			else {
				$('#reviewContactInfo_email_li').hide();
			}
			
			
			if(phone != null && phone != "")
			{
				$('#reviewContactInfo_phone_li').show();
			}
			else {
				$('#reviewContactInfo_phone_li').hide();
			}
			
			
			<c:if test="${period eq 'August'}">
				var alt = document.getElementById("orderContact.altContactInfo").value;
				if(alt != null && alt != "")
				{					
					$('#reviewContactInfo_alt_hr').show();
					$('#reviewContactInfo_alt_li').show();
		
					var lines = alt.split('\n');   // lines is an array of strings
					for (var j = 0; j < lines.length; j++) {
					  $('#reviewContactInfo_alt').append(lines[j] + "<br />");
					}
				}
				else {
					$('#reviewContactInfo_alt_hr').hide();
					$('#reviewContactInfo_alt_li').hide();
				}
			</c:if>
		}
		
		function getRadioButtonIndex(n) {
		    var i, r = document.getElementsByName(n);
		    for (i = 0; i < r.length; i++) {
		        if (r[i].checked) return r[i].value;
		    }
		    return null;
		}
		
		function isEven(n) 
		{
		   return n % 2 == 0;
		}
		
		function selectAllExams()
		{
			var i, count = 0;
			$('input[id^="selectedExams"][id$="selected1"]').each(function() { count++ });
			
			if(document.getElementById('isCheckedExams').checked) 
			{
				for(i = 0; i <= count-1; i++)
				{
					var x = document.getElementById('selectedExams' + i + '.selected1');
					x.checked = true;
					
					var parent = x.parentElement;
					parent.classList.add('checked');
				}
			}
			else
			{
				for(i = 0; i <= count-1; i++)
				{
					var x = document.getElementById('selectedExams' + i + '.selected1');
					x.checked = false;
					
					var parent = x.parentElement;
					parent.classList.remove('checked');
				}
			}
		}
		
		function selectAllDocuments()
		{
			var i, count = 0;
			$('input[id^="selectedDocuments"][id$="selected1"]').each(function() { count++ });
			
			if(document.getElementById('isCheckedDocuments').checked) 
			{
				for(i = 0; i <= count-1; i++)
				{
					var x = document.getElementById('selectedDocuments' + i + '.selected1');
					x.checked = true;
					
					var parent = x.parentElement;
					parent.classList.add('checked');
				}
			}
			else
			{
				for(i = 0; i <= count-1; i++)
				{
					var x = document.getElementById('selectedDocuments' + i + '.selected1');
					x.checked = false;
					
					var parent = x.parentElement;
					parent.classList.remove('checked');
				}
			}
		}
		
		function updateSchoolList() 
		{
			//Populate Schools Array, include associated District data
			var schools = new Array();
			<c:forEach items="${schoolsByOrderUserDistrict}" var="school" varStatus="status"> 
				schoolDetails = new Object();
				schoolDetails.id = ${school.id}; 
				schoolDetails.name = "${school.name}"; 
				
				districtDetails = new Object();
				districtDetails.id = ${school.district.id}; 
				districtDetails.name = "${school.district.name}"; 
				
				schoolDetails.district = districtDetails;
			    schools.push(schoolDetails);
		    </c:forEach> 
		
		    //Get Selected District from District Dropdwon
		    var d = document.getElementById('districtList');
		    var districtId = d.options[d.selectedIndex].value;
		
		  	//Get and Clear School Dropdown
		    var select = document.getElementById('schoolList');
		    select.innerHTML = null;
		    
		    //Fill School Dropdown with Schools associated to the select District
		    schools.forEach(function(school)
		    {
		    	if(school.district.id == districtId)
		   		{
		    		var opt = document.createElement('option');
			        opt.value = school.id;
		
			        var schoolId = ${schoolId};
			        if(school.id == schoolId)
		                  {
		                      opt.selected = true;
		                  }
		
			        opt.innerHTML = school.name;
			        select.appendChild(opt);
		   		}
		    });
		}
	</script>
	<jsp:include page="../fragments/footer.jsp" />
	<script>
		//Set default Schools in Contact School List
		$(document).ready(updateSchoolList);
	</script>
</html>