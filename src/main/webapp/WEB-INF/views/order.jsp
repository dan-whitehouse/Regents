<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/order" var="order" />
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<fmt:formatNumber var="nonSecureDocumentFee" value="${orderForm.nonSecureDocumentFee}" type="currency"/>
<fmt:formatNumber var="processingFee" value="${orderForm.processingFee}" type="currency"/>


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
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<!-- Wizard 2 -->
							<div class="container">
								<div class="row">
									<div class="col-xs-12">
										<ul class="nav nav-pills nav-justified thumbnail2 setup-panel">
											<li class="active">
												<a href="#step-1">
													<h4 class="list-group-item-heading">Step 1</h4>
													<p class="list-group-item-text">Info</p>
												</a>
											</li>
											<li class="disabled">
												<a href="#step-2">
													<h4 class="list-group-item-heading">Step 2</h4>
													<p class="list-group-item-text">Regents Exams</p>
												</a>
											</li>
											<!-- <li class="disabled">
												<a href="#step-3">
													<h4 class="list-group-item-heading">Step 3</h4>
													<p class="list-group-item-text">Location for Blanks</p>
												</a>
											</li> -->
											<li class="disabled">
												<a href="#step-3">
													<h4 class="list-group-item-heading">Step 3</h4>
													<p class="list-group-item-text">Non-Secure Documents</p>
												</a>
											</li>
											<li class="disabled">
												<a href="#step-4">
													<h4 class="list-group-item-heading">Step 4</h4>
													<p class="list-group-item-text">Options</p>
												</a>
											</li>
											<li class="disabled">
												<a href="#step-5">
													<h4 class="list-group-item-heading">Step 5</h4>
													<p class="list-group-item-text">Contact</p>
												</a>
											</li>
											<li class="disabled">
												<a href="#step-6" onclick="review();">
													<h4 class="list-group-item-heading">Step 6</h4>
													<p class="list-group-item-text">Review</p>
												</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<form:form method="POST" modelAttribute="xForm2" cssClass="container">
								<%-- <form:hidden path="orderForm" value="${orderForm}"/> --%>
								<!-- STEP 1 - INFO -->
								<div class="row setup-content" id="step-1">
									<div class="col-xs-12">
										<div class="col-md-12 well text-center">
											<div class="col-md-12 col-sm-12 col-xs-12">	
												<h2 class="StepTitle">${orderForm.period} ${year} - REGENTS ANSWER SHEET SERVICES & NON SECURE DOCUMENTS ORDER FORM</h2>
												<p>
													<strong>Directions: </strong>
													The order form and data <strong>must</strong> be received by NERIC no later than ${dueDate}. 
												</p>
												<p>
													<strong>Student Demographic Data File: </strong>
													Please see this document for additional information about the exact .csv file specification and submission instructions.
												</p>
												<p>
													<strong>Billing: </strong>
													Your district will be billed for the precise number of tests and non-secure documents processed through NERIC.
													The rate for the Regents scanning/scoring service is ${processingFee}* per student per test processed.
													The rate for non-secure documents is ${nonSecureDocumentFee} per document ordered.
													Your district will be billed in the ${billingYear} academic year.
													If you need a data file you must request it by sending an email to testing@neric.org; do this only after you confirm the accuracy of the scores on your reports.											
												</p>
												<div class="highlight">This form must be signed by your superintendent, business official or whoever else is authorized to approve this expenditure.</div>
											</div>
											<div class="col-md-12 col-sm-12 col-xs-12">
												<br>
												<button id="activate-step-2" class="btn btn-primary btn-md">Next</button>
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
																	<c:if test="${orderForm.period eq 'June'}">
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
																<c:forEach items="${allExamOptions}" var="e" varStatus="status">
																	<form:hidden path="selectedExams[${status.index}].orderExam.exam.id" value="${e.orderExam.exam.id}"/>
																	<tr>
																		<td>
																			<form:checkbox path="selectedExams[${status.index}].selected" class="flat"/>
																		</td>
																		<td>
																			<form:input path="selectedExams[${status.index}].orderExam.exam.name" value="${e.orderExam.exam.name } - ${e.orderExam.exam.code}"  class="form-control col-md-3 col-xs-12" readonly="true"/>
																		</td>
																		<c:if test="${orderForm.period eq 'June'}">
																			<td>
																				<form:input path="selectedExams[${status.index}].orderExam.examAmount" class="form-control col-md-3 col-xs-12" />
																			</td>
																		</c:if>
																		<td>
																			<form:input path="selectedExams[${status.index}].orderExam.answerSheetAmount" class="form-control col-md-3 col-xs-12" />
																		</td>
																		<td>
																			<form:input path="selectedExams[${status.index}].orderExam.studentsPerCSV" class="form-control col-md-3 col-xs-12" />
																		</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</div>
											</div>
											<button id="activate-step-3" class="btn btn-primary btn-md">Next</button>
										</div>
									</div>
								</div>
								
								<!-- STEP 3 - Blanks Location -->
								<%-- <div class="row setup-content" id="step-3">
									<div class="col-xs-12">
										<div class="col-md-12 well text-center">
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback"> 
												<!-- id is used in javascript -->
												<form:select path="orderContact.district" id="districtList" items="${districtsByUser}" itemValue="id" itemLabel="name" onchange="updateSchoolList()" cssClass="form-control col-md-12 col-xs-12 has-feedback-left"/>
												<span class="fa fa-university form-control-feedback left" aria-hidden="true"></span>
											</div>
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
												<!-- id is used in javascript -->
												<form:select path="orderContact.school" id="schoolList" cssClass="form-control col-md-12 col-xs-12 has-feedback-left"/>
												<span class="fa fa-graduation-cap form-control-feedback left" aria-hidden="true"></span>
											</div>
	
										</div>
									</div>
		                       		
									<div class="col-xs-12">
										<div class="col-md-12 well text-center">
											<h2 class="text-center">Special Requests</h2>
											<div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback"> 
												<!-- id is used in javascript -->
												<form:textarea path="orderContact.altContactInfo" id="orderContact.altContactInfo" rows="10" maxlength="1000" cssClass="form-control col-md-12 col-xs-12 has-feedback-left"/>
												<span class="fa fa-truck form-control-feedback left" aria-hidden="true"></span>
											</div>
											<button id="activate-step-4" class="btn btn-primary btn-md">Next</button>
										</div>
									</div>
								</div> --%>
								
								<!-- STEP 3 - DOCUMENTS -->
								<div class="row setup-content" id="step-3">
									<div class="col-xs-12">
									
										<div class="alert alert-dismissible fade in" role="alert">
                    						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    							<span aria-hidden="true">×</span>
                    						</button>
                    						Non-secure documents include Essay Booklets for ELA, USH and GH, and Reference Tables for the sciences. 
											See Step 1 for price per document.
                  						</div>
									
										<div class="col-md-12 well text-center">
										
											<span>
												
											</span>
										
											<table class="table">
												<thead>
													<tr>
														<th width="75px">Order <input type="checkbox" id="isCheckedDocuments" onclick="selectAllDocuments()"/></th>
														<th>Non-Secure Document</th>
														<th>Quantity</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${allDocumentOptions}" var="d" varStatus="status">
														<form:hidden path="selectedDocuments[${status.index}].orderDocument.document.id" value="${d.orderDocument.document.id}"/>
														<tr>
															<td>
																<form:checkbox path="selectedDocuments[${status.index}].selected" class="flat"/>
															</td>
															<td>
																<form:input path="selectedDocuments[${status.index}].orderDocument.document.name" value="${d.orderDocument.document.name }"  class="form-control col-md-3 col-xs-12" readonly="true" />
															</td>
															<td>
																<form:input path="selectedDocuments[${status.index}].orderDocument.documentAmount" class="form-control col-md-3 col-xs-12" />
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<button id="activate-step-4" class="btn btn-primary btn-md">Next</button>
										</div>
									</div>
								</div>
								<!-- STEP 4 - OPTIONS -->
								<div class="row setup-content" id="step-4">
									<div class="col-xs-12">
										<div class="col-md-12 well">
											<div class="form-group col-xs-12">
												<label class="control-label col-md-6 col-sm-6 col-xs-12">Scanning/Scoring Option: 
												<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="If nothing is selected, Alpha will be chosen by default." >
												<span class=" fa fa-info"></span>
												</span>
												</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<div class="radio">
														<ul class="list-unstyled">
															<form:radiobuttons path="selectedOptionScan" name="selectedOptionScan" items="${allScanOptions}" itemValue="id" itemLabel="name" cssClass="radio flat form-control" element="li"/>
														</ul>
													</div>
												</div>
											</div>
											<br />
											<div class="form-group col-xs-12">
												<label class="control-label col-md-6 col-sm-6 col-xs-12">Reporting Option: 
												<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="If nothing is selected, Alpha will be chosen by default." >
												<span class=" fa fa-info"></span>
												</span>
												</label>
												
												<div class="col-md-6 col-sm-6 col-xs-12">
													<div class="checkbox">
														<c:choose>
															<c:when test="${orderForm.period eq 'August'}">
																<form:checkbox path="reportingOption" name="reportingOption" cssClass="form-control flat" label="NERIC will load scores into Level 1" disabled="true"/>
															</c:when>
															<c:otherwise>
																<form:checkbox path="reportingOption" name="reportingOption" cssClass="form-control flat" label="NERIC will load scores into Level 1" disabled="true" checked="checked"/>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</div>
											<br />
											<div class="form-group col-xs-12">
												<label class="control-label col-md-6 col-sm-6 col-xs-12">Printing Option: 
												<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="If nothing is selected, Alpha will be chosen by default." >
												<span class=" fa fa-info"></span>
												</span>
												</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<form:select path="selectedOptionPrint" items="${allPrintOptions}" itemValue="id" itemLabel="name" cssClass="form-control"/>
												</div>
											</div>
											<div class="col-md-12 text-center">
												<br />
												<button id="activate-step-5" class="btn btn-primary btn-md">Next</button>  
											</div>
										</div>
									</div>
								</div>
								<!--  STEP 6 - CONTACT -->
								<div class="row setup-content" id="step-5">
									<div class="col-xs-12">
										<div class="col-md-12 well text-center">
										
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback"> 
												<!-- id is used in javascript -->
												<form:select path="orderContact.district" id="districtList" items="${districtsByUser}" itemValue="id" itemLabel="name" onchange="updateSchoolList()" cssClass="form-control col-md-12 col-xs-12 has-feedback-left"/>
												<span class="fa fa-university form-control-feedback left" aria-hidden="true"></span>
											</div>
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
												<!-- id is used in javascript -->
												<form:select path="orderContact.school" id="schoolList" cssClass="form-control col-md-12 col-xs-12 has-feedback-left"/>
												<span class="fa fa-graduation-cap form-control-feedback left" aria-hidden="true"></span>
											</div>
										
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
												<form:input path="orderContact.name" type="text" class="form-control has-feedback-left" id="orderContact.name" placeholder="Name" required="required"/> 
												<span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
												<div class="has-error">
													<form:errors path="orderContact.name" class="help-inline"/>
												</div>
											</div>
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
												<form:input path="orderContact.title" type="text" class="form-control has-feedback-left" id="orderContact.title" placeholder="Title" /> 
												<span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
											</div>
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
												<form:input path="orderContact.email" type="text" class="form-control has-feedback-left" id="orderContact.email" placeholder="Email" />
												<span class="fa fa-envelope form-control-feedback left" aria-hidden="true"></span>
											</div>
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
												<form:input path="orderContact.phone" type="text" class="form-control has-feedback-left" id="orderContact.phone" placeholder="Phone" />
												<span class="fa fa-phone form-control-feedback left" aria-hidden="true"></span>
											</div>
											<c:if test="${orderForm.period ne 'August'}">
												<button id="activate-step-6" class="btn btn-primary btn-md" onclick="review()">Next</button>
											</c:if>
										</div>
									</div>
		                       		<c:if test="${orderForm.period eq 'August'}">
										<div class="col-xs-12">
											<div class="col-md-12 well text-center">
												<h2 class="text-center"> Alternate Shipping Information</h2>
												<div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback"> 
													<!-- id is used in javascript -->
													<form:textarea path="orderContact.altContactInfo" id="orderContact.altContactInfo" rows="10" maxlength="1000" cssClass="form-control col-md-12 col-xs-12 has-feedback-left"/>
													<span class="fa fa-truck form-control-feedback left" aria-hidden="true"></span>
												</div>
												<button id="activate-step-6" class="btn btn-primary btn-md" onclick="review()">Review</button> 
											</div>
										</div>
									</c:if>
								</div>
								<!--  STEP 7 - REVIEW -->
								<div class="row setup-content" id="step-6">
									<div class="col-xs-12">
										<div class="col-md-12 well text-center">
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
																		<th class="column-title">Students Pull from L0</th>
																		<th class="column-title">Blank Answer Sheets </th>
																		<th class="column-title">Students in CSV </th>
																	</tr>
																</thead>
																<tbody id="reviewExams">															
																	<tr class="even pointer">
																		<td class=" ">Algebra 2 / Trigonometry - 02052CC</td>
																		<td class=" ">61</td>
																		<td class=" ">10</td>
																		<td class=" ">1</td>
																	</tr>
																	<tr class="odd pointer">
																		<td class=" ">ELA (Common Core) - 01003CC</td>
																		<td class=" ">116</td>
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
											
											<!-- START CONTACT -->
											<div class="col-md-5 col-sm-5 col-xs-5" >
												<div class="x_panel">
													<div class="x_title">
														<h2>Contact</h2>
														<div class="clearfix"></div>
													</div>
													<div class="x_content" id="reviewContactInfo">
													
													</div>
												</div>
											</div>
											<!-- END CONTACT -->
											<div class="clearfix"></div>
											<!-- START SUBMIT -->
											<div class="col-md-12 col-sm-12 col-xs-12">
												<div class="x_panel">
													<div class="x_title">
														<h2>Submit</h2>
														<div class="clearfix"></div>
													</div>
													<div class="x_content">
														<div class="form-group">
															<p>By clicking "Submit", you signify that you have permission to order these Regents forms from an appropriate person who can authorize this expenditure.</p>
														</div>
														<input type="submit" value="Submit" class="btn btn-success"/> <a href="<c:url value='/orders' />" class="btn btn-primary">Cancel</a>
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
					var examAmount = '';
					
					<c:if test="${orderForm.period eq 'June'}">
						examAmount = document.getElementById("selectedExams" + i + ".orderExam.examAmount").value;
					</c:if>
							
					var answerSheetAmount = document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").value;
					var studentsPerCSV = document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").value;
					var evenOddClass = 'odd';
					
	
					if(examAmount == '')
					{
						examAmount = '0';
					}
					
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
					
					//Add HTML
					$('#reviewExams').append("<tr class='" + evenOddClass + " pointer'><td>" + examName + "</td><td>" + examAmount + "</td><td>" + answerSheetAmount + "</td><td>" + studentsPerCSV + "</td></tr>");
				}
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
			var name = document.getElementById("orderContact.name").value;
			var title = document.getElementById("orderContact.title").value;
			var email = document.getElementById("orderContact.email").value;
			var phone = document.getElementById("orderContact.phone").value;
			//
			
			var selectedDistrict = document.getElementById("districtList");		
			var selectedSchool = document.getElementById("schoolList");

			
			$('#reviewContactInfo').empty();
			$('#reviewContactInfo').append(name + "<br />");
			$('#reviewContactInfo').append(title + "<br />");
			$('#reviewContactInfo').append(email + "<br />");
			$('#reviewContactInfo').append(phone + "<br />");
			
			$('#reviewContactInfo').append(selectedSchool.options[selectedSchool.selectedIndex].text + ", ");
			$('#reviewContactInfo').append(selectedDistrict.options[selectedDistrict.selectedIndex].text + "<br />");
			
			<c:if test="${orderForm.period eq 'August'}">
				var alt = document.getElementById("orderContact.altContactInfo").value;
				if(alt != null && alt != "")
				{
					$('#reviewContactInfo').append("<hr />" + alt);
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

		function updateSchoolList() 
		{
			//Populate Schools Array, include associated District data
			var schools = new Array();
			<c:forEach items="${schoolsByDistrict}" var="school" varStatus="status"> 
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
			        opt.innerHTML = school.name;
			        select.appendChild(opt);
	    		}
		    });
		}
	</script>
	<jsp:include page="fragments/footer.jsp" />
	<script>
		//Set default Schools in Contact School List
		$(document).ready(updateSchoolList);
	</script>
