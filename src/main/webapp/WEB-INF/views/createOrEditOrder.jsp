<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:url value="/order" var="order" />
<!-- http://bootsnipp.com/snippets/W7gNz -->
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
													<p class="list-group-item-text">Non-Secure Documents</p>
												</a>
											</li>
											<li class="enabled">
												<a href="#step-4">
													<h4 class="list-group-item-heading">Step 4</h4>
													<p class="list-group-item-text">Options</p>
												</a>
											</li>
											<li class="enabled">
												<a href="#step-5">
													<h4 class="list-group-item-heading">Step 5</h4>
													<p class="list-group-item-text">Contact</p>
												</a>
											</li>
											<li class="enabled">
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
								<!-- STEP 1 - INFO -->
								<div class="row setup-content" id="step-1">
									<div class="col-xs-12">
										<div class="col-md-12 well text-center">
											<h1 class="text-center"> Info</h1>
											<div class="col-md-12 col-sm-12 col-xs-12">
												<h2 class="StepTitle">JUNE 2017 - REGENTS ANSWER SHEET SERVICES & NON SECURE DOCUMENTS ORDER FORM</h2>
												<p>
													<strong>Directions: </strong>
													The order form and data <strong>must</strong> be recieved by NERIC no later than ${dueDate}. 
												</p>
												<p>
													<strong>Student Demographic Data File: </strong>
													Please see this document for additional information about the exact .csv file specification and submission instructions.
												</p>
												<p>
													<strong>Billing: </strong>
													Your district will be billed for the precise number of tests and non-secure documents processed through NERIC.
													The rate for the Regents scanning/scoring service is $2.10* per student per test processed.
													The rate for non-secure documents is $0.40 per document ordered.
													Your district will be billed in the ${billingYear} academic year.
													If you need a data file you must request it by sending an email to testing@neric.org; do this only after you confirm the accuracy of the scores on your reports.											
												</p>
												<div class="highlight">This form must be signed by your superintendent, business official or whoever else is authorized to approve this expenditure.</div>
											</div>
											<div class="col-md-12 col-sm-12 col-xs-12">
												<br>
											</div>
										</div>
									</div>
								</div>
								<!-- STEP 2 - EXAMS -->
								<div class="row setup-content" id="step-2">
									<div class="col-xs-12">
										<div class="col-md-12 well text-center">
											<h1> Regents Exams</h1>
											<!-- <form> -->               
											<div class="container col-xs-12">
												<div class="row clearfix">
													<div class="col-md-12 column">
														<table class="table" id="tab_logic">
															<thead>
																<tr>
																	<th>Order</th>
																	<th class="col-md-5 col-xs-12">Test Name</th>
																	<th>Number of Test
																		<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="Pull from Level 0: Number of students enrolled in a course resulting in an exam." >
																		<span class=" fa fa-info"></span>
																		</span>
																	</th>
																	<th>Answer Sheets 
																		<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="Number of blank in-district answer sheets." >
																		<span class=" fa fa-info"></span>
																		</span>
																	</th>
																	<th>Students Per CSV
																		<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="Number of students included on the CSV file." >
																		<span class=" fa fa-info"></span>
																		</span>
																	</th>
																	<th>P.A.S.
																		<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="Check if SED requires your district to use the Pearson Answer Sheets." >
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
																			<form:input path="selectedExams[${status.index}].orderExam.exam.name" value="${e.orderExam.exam.name } - ${e.orderExam.exam.code}"  class="form-control col-md-3 col-xs-12" />
																		</td>
																		<td>
																			<form:input path="selectedExams[${status.index}].orderExam.examAmount" class="form-control col-md-3 col-xs-12" />
																		</td>
																		<td>
																			<form:input path="selectedExams[${status.index}].orderExam.answerSheetAmount" class="form-control col-md-3 col-xs-12" />
																		</td>
																		<td>
																			<form:input path="selectedExams[${status.index}].orderExam.studentsPerCSV" class="form-control col-md-3 col-xs-12" />
																		</td>
																		<c:choose>
																			<c:when test="${e.orderExam.exam.name == 'Algebra 2 (Common Core)'}">
																				<th scope="row">
																					<form:checkbox path="selectedExams[${status.index}].orderExam.pearsonAnswerSheet" class="flat"/>
																				</th>
																			</c:when>
																			<c:otherwise>
																				<th scope="row"></th>
																			</c:otherwise>
																		</c:choose>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- STEP 3 - DOCUMENTS -->
								<div class="row setup-content" id="step-3">
									<div class="col-xs-12">
										<div class="col-md-12 well text-center">
											<h1 class="text-center">Non-Secure Document</h1>
											<table class="table">
												<thead>
													<tr>
														<th>Order</th>
														<th>Name</th>
														<th>Number Requested</th>
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
																<form:input path="selectedDocuments[${status.index}].orderDocument.document.name" value="${d.orderDocument.document.name }"  class="form-control col-md-3 col-xs-12" />
															</td>
															<td>
																<form:input path="selectedDocuments[${status.index}].orderDocument.documentAmount" class="form-control col-md-3 col-xs-12" />
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<!-- STEP 4 - OPTIONS -->
								<div class="row setup-content" id="step-4">
									<div class="col-xs-12">
										<div class="col-md-12 well text-center">
											<h1 class="text-center">Options</h1>
											<div class="form-group col-xs-12">
												<label class="control-label col-md-6 col-sm-6 col-xs-12">Scanning/Scoring Option: 
												<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="If nothing is selected, Alpha will be chosen by default." >
												<span class=" fa fa-info"></span>
												</span>
												</label>
												<div class="col-md-6 col-sm-6 col-xs-12">
													<form:radiobuttons path="selectedOptionScan" name="selectedOptionScan" items="${allScanOptions}" itemValue="id" itemLabel="name" cssClass="radio flat form-control"/>
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
														<label>
															<form:checkbox path="reportingOption" name="reportingOption" cssClass="form-control flat" label="NERIC will load scores into Level 1" />
														</label>
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
											<div class="col-md-12 col-sm-12 col-xs-12">
												<br>
											</div>
										</div>
									</div>
								</div>
								<!--  STEP 5 - CONTACT -->
								<div class="row setup-content" id="step-5">
									<div class="col-xs-12">
										<div class="col-md-12 well text-center">
											<h1 class="text-center"> Contact Information</h1>
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback"> 
												<%-- <input type="text" class="form-control has-feedback-left" readonly="readonly" id="inputSuccess1" value="${loggedinuser.district.name}"> --%>
												<span class="fa fa-university form-control-feedback left" aria-hidden="true"></span>
											</div>
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
												<form:select path="" items="${schoolsByDistrict}" itemValue="id" itemLabel="name" cssClass="form-control col-md-12 col-xs-12 has-feedback-left"/>
												<span class="fa fa-graduation-cap form-control-feedback left" aria-hidden="true"></span>
											</div>
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
												<input type="text" class="form-control has-feedback-left"
													id="inputSuccess2" placeholder="First Name"> 
												<span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
											</div>
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
												<input type="text" class="form-control has-feedback-left"
													id="inputSuccess3" placeholder="Last Name"> 
												<span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
											</div>
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
												<input type="text" class="form-control has-feedback-left"
													id="inputSuccess4" placeholder="Email">
												<span class="fa fa-envelope form-control-feedback left" aria-hidden="true"></span>
											</div>
											<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
												<input type="text" class="form-control has-feedback-left"
													id="inputSuccess5" placeholder="Phone">
												<span class="fa fa-phone form-control-feedback left" aria-hidden="true"></span>
											</div>
										</div>
									</div>
								</div>
								<!--  STEP 6 - REVIEW -->
								<div class="row setup-content" id="step-6">
									<div class="col-xs-12">
										<div class="col-md-12 well text-center">
											<h1 class="text-center"> Review</h1>
											<div class="col-md-12 col-sm-12 col-xs-12" >
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
																		<th class="column-title">Test Name </th>
																		<th class="column-title">Number of Test </th>
																		<th class="column-title">Answer Sheets </th>
																		<th class="column-title">Students Per CSV </th>
																		<th class="column-title">P.A.S. </th>
																	</tr>
																</thead>
																<tbody id="reviewExams">															
																	<tr class="even pointer">
																		<td class=" ">Algebra 2 / Trigonometry - 02052CC</td>
																		<td class=" ">61</td>
																		<td class=" ">10</td>
																		<td class=" ">1</td>
																		<td class=" "></td>
																	</tr>
																	<tr class="odd pointer">
																		<td class=" ">ELA (Common Core) - 01003CC</td>
																		<td class=" ">116</td>
																		<td class=" ">20</td>
																		<td class=" ">0</td>
																		<td class=" "></td>
																	</tr>
																</tbody>
															</table>
														</div>
													</div>
												</div>
											</div>
											<br />
											<!-- START DOCUMENTS -->
											<div class="col-md-6 col-sm-6 col-xs-6">
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
																		<th class="column-title">Name </th>
																		<th class="column-title">Number Requested </th>
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
											
											<!-- START OPTIONS -->
											<div class="col-md-6 col-sm-6 col-xs-6">
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
											<div class="col-md-6 col-sm-6 col-xs-6">
												<div class="x_panel">
													<div class="x_title">
														<h2>Submit</h2>
														<div class="clearfix"></div>
													</div>
													<div class="x_content">
														<div class="form-group">
															<p>By clicking "Submit", you signify that you have permission to order these Regents forms from an appropriate person who can authorize this expenditure.</p>
														</div>
														<input type="submit" value="Update" class="btn btn-success"/> <a href="<c:url value='/orders' />" class="btn btn-primary">Cancel</a>
													</div>
												</div>
											</div>
											
										</div>
									</div>
								</div>
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
					var examAmount = document.getElementById("selectedExams" + i + ".orderExam.examAmount").value;
					var answerSheetAmount = document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").value;
					var studentsPerCSV = document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").value;
					var pas = document.getElementById("selectedExams" + i + ".orderExam.pearsonAnswerSheet1");
					var pasValue = 'No';
					var evenOddClass = 'odd';
					
					if(pas != null && pas.checked) 
					{
						pasValue = 'Yes'
					}
	
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
					$('#reviewExams').append("<tr class='" + evenOddClass + " pointer'><td>" + examName + "</td><td>" + examAmount + "</td><td>" + answerSheetAmount + "</td><td>" + studentsPerCSV + "</td><td>" + pasValue + "</td></tr>");
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
</html>