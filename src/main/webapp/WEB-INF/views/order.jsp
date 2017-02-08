<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:url value="/order" var="order" />

<html>
	<jsp:include page="fragments/header.jsp" />
	<!-- page content -->
	<div class="right_col" role="main">
		<div class="">
			<!--             <div class="page-title"> -->
			<!--               <div class="title_left"> -->
			<!--                 <h3>Form Wizards</h3> -->
			<!--               </div> -->
			<!--               <div class="title_right"> -->
			<!--                 <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search"> -->
			<!--                   <div class="input-group"> -->
			<!--                     <input type="text" class="form-control" placeholder="Search for..."> -->
			<!--                     <span class="input-group-btn"> -->
			<!--                               <button class="btn btn-default" type="button">Go!</button> -->
			<!--                           </span> -->
			<!--                   </div> -->
			<!--                 </div> -->
			<!--               </div> -->
			<!--             </div> -->
			<div class="clearfix"></div>
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>Regents Order Form <small>Beta</small></h2>
<!-- 							<ul class="nav navbar-right panel_toolbox"> -->
<!-- 								<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a> -->
<!-- 								</li> -->
<!-- 								<li class="dropdown"> -->
<!-- 									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a> -->
<!-- 									<ul class="dropdown-menu" role="menu"> -->
<!-- 										<li><a href="#">Settings 1</a> -->
<!-- 										</li> -->
<!-- 										<li><a href="#">Settings 2</a> -->
<!-- 										</li> -->
<!-- 									</ul> -->
<!-- 								</li> -->
<!-- 								<li><a class="close-link"><i class="fa fa-close"></i></a> -->
<!-- 								</li> -->
<!-- 							</ul> -->
							<div class="clearfix"></div>
						</div>
						
						<div class="x_content">
								<!-- Smart Wizard -->
	<!-- 							<p>This is a basic form wizard example that inherits the colors from the selected scheme.</p> -->
								<div id="wizard" class="form_wizard wizard_horizontal">
									<ul class="wizard_steps">
										<li>
											<a href="#step-1">
											<span class="step_no">1</span>
											<span class="step_descr">
											Step 1<br />
											<small>Info</small>
											</span>
											</a>
										</li>
										<li>
											<a href="#step-2">
											<span class="step_no">2</span>
											<span class="step_descr">
											Step 2<br />
											<small>Regents Exams</small>
											</span>
											</a>
										</li>
										<li>
											<a href="#step-3">
											<span class="step_no">3</span>
											<span class="step_descr">
											Step 3<br />
											<small>Non-Secure Documents</small>
											</span>
											</a>
										</li>
										<li>
											<a href="#step-4">
											<span class="step_no">4</span>
											<span class="step_descr">
											Step 4<br />
											<small>Options</small>
											</span>
											</a>
										</li>
										<li>
											<a href="#step-5">
											<span class="step_no">5</span>
											<span class="step_descr">
											Step 5<br />
											<small>Order Contact</small>
											</span>
											</a>
										</li>
										<li>
											<a href="#step-6">
											<span class="step_no">6</span>
											<span class="step_descr">
											Step 6<br />
											<small>Review</small>
											</span>
											</a>
										</li>
									</ul>
									
									<form:form method="POST" modelAttribute="wizard" class="form-horizontal form-label-left">
									<div id="step-1">
										<div class="col-md-2 col-sm-2 col-xs-12"></div><!-- Empty div for proper spacing -->
										<div class="col-md-7 col-sm-7 col-xs-12">
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
									</div>
									<div id="step-2">
										<form class="form-horizontal form-label-left">
										<div class="col-md-1 col-sm-1 col-xs-12"></div>
										<div class="col-md-10 col-sm-11 col-xs-12">
											<table class="table">
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
						                      	<c:forEach items="${wizard.allAvailableExams}" var="w" varStatus="status">
						                      		<tr>
						                      			<td><input path="allAvailableExams[${status.index}].selected" type="checkbox" class="flat" /></td>
						                      			<td><form:input path="allAvailableExams[${status.index}].orderExam.exam.name" value="${w.orderExam.exam.name } - ${w.orderExam.exam.code}"  class="form-control col-md-3 col-xs-12" /></td>
						                      			<td><form:input path="allAvailableExams[${status.index}].orderExam.examAmount" class="form-control col-md-3 col-xs-12" /></td>
						                          		<td><form:input path="allAvailableExams[${status.index}].orderExam.answerSheetAmount" class="form-control col-md-3 col-xs-12" /></td>
						                          		<td><form:input path="allAvailableExams[${status.index}].orderExam.studentsPerCSV" class="form-control col-md-3 col-xs-12" /></td>
						                          		<c:choose>
						                          			<c:when test="${w.orderExam.exam.name == 'Algebra 2 (Common Core)'}">
						                          				<th scope="row"><input path="allAvailableExams[${status.index}].pasSelected" type="checkbox" class="flat"></th>
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
										</form>
									</div>
									<div id="step-3">
										<div class="col-md-3 col-sm-3 col-xs-12"></div>Empty div for proper spacing
										<div class="col-md-6 col-sm-6 col-xs-12">
											<table class="table">
						                      <thead>
						                        <tr>
						                          <th>Order</th>
						                          <th>Name</th>
						                          <th>Number Requested</th>
						                        </tr>
						                      </thead>
						                      <tbody>
						                      	<c:forEach items="${documents }" var="document">
						                      		<tr>
						                          		<th scope="row"><input type="checkbox" class="flat"></th>
						                          		<td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="${document.name}" class="form-control col-md-7 col-xs-12"></td>
						                          		<td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
						                        	</tr>
						                      	</c:forEach>
						                      </tbody>
						                    </table>
						                </div>
									</div>
									<div id="step-4">
										<form class="form-horizontal form-label-left">
										
											<div class="form-group">
				                        		<label class="control-label col-md-3 col-sm-3 col-xs-12">Scanning/Scoring Option: 
 														<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="If nothing is selected, Alpha will be chosen by default." >
						                          		<span class=" fa fa-info"></span>
	 					                          	     </span>
						                        </label>
						                        <div class="col-md-6 col-sm-6 col-xs-12">
						                        	<c:forEach items="${optionScans}" var="optionScan">
						                        		<div class="radio">
							                            <label>
							                              <input type="radio" class="flat" checked name="iCheck"/>${optionScan.name }
							                            </label>
							                          </div>
						                        	</c:forEach>
			                        			</div>
			                      			</div>
			                      			<br />
			                      			<div class="form-group">
				                        		<label class="control-label col-md-3 col-sm-3 col-xs-12">Reporting Option: 
 														<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="If nothing is selected, Alpha will be chosen by default." >
						                          		<span class=" fa fa-info"></span>
	 					                          	     </span>
						                        </label>
						                        <div class="col-md-6 col-sm-6 col-xs-12">
						                        		
							                          <div class="checkbox">
							                            <label>
							                              <input type="checkbox" class="flat"> NERIC will load scores into Level 1
							                            </label>
							                          </div>
			                        			</div>
			                      			</div>
											<br />
											<div class="form-group">
				                        		<label class="control-label col-md-3 col-sm-3 col-xs-12">Printing Option: 
				                        			<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="If nothing is selected, Alpha will be chosen by default." >
						                          		<span class=" fa fa-info"></span>
						                          	</span>
						                        </label>
						                        <div class="col-md-6 col-sm-6 col-xs-12">
						                       		<select class="form-control">
							                        	<c:forEach items="${optionPrints}" var="optionPrint">
							                        		<option>${optionPrint.name }</option>
				                      					</c:forEach>
			                      					</select>
			                        			</div>
			                      			</div>
			                      		</form>
									</div>
									<div id="step-5">
										<h2 class="StepTitle">Order Contact</h2>
						                      <div class="x_content">
							                    <br />
							                    <form class="form-horizontal form-label-left input_mask">
							                    try populating user data based on checked field
											
											
												<div class="form-group" >
													<div class="col-md-6 col-sm-6 col-xs-12">
														<input type="checkbox" class="flat"> I am the order contact
													</div>
												</div>
							
							                      <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
							                        <input type="text" class="form-control has-feedback-left"
							                        	readonly="readonly"
														id="inputSuccess1" value="${loggedindistrict}">
														<span class="fa fa-university form-control-feedback left" aria-hidden="true"></span>
							                      </div>
							
							                      <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
							                        <input type="text" class="form-control has-feedback-left"
														id="inputSuccess1" placeholder="School"> 
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
							                    </form>
							                  </div>
							                </div>
	
									<div id="step-6">
									<!-- START STEP 6 -->
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
							
							                        <tbody>
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
							                          <tr class="even pointer">
							                            <td class=" ">Global History & Geography - 04052</td>
							                            <td class=" ">102</td>
							                            <td class=" ">20</td>
							                            <td class=" ">12</td>
							                            <td class=" "></td>
							                          </tr>
							                        </tbody>
							                      </table>
							                    </div>
							                  </div>
							                </div>
							              </div>
							              <br />
							              <!--  START DOCUMENTS -->
							              <div class="col-md-12 col-sm-12 col-xs-12">
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
							
							                        <tbody>
							                          <tr class="even pointer">
							                            <td class=" ">Chemistry</td>
							                            <td class=" ">50</td>
							                          </tr>
							                        </tbody>
							                      </table>
							                    </div>
							                  </div>
							                </div>
							              </div>
							              <!-- END DOCUMENTSS -->
							              <br />
							              <!-- START OPTIONS -->
							              <form class="form-horizontal form-label-left">
										
											<div class="form-group">
				                        		<label class="control-label col-md-3 col-sm-3 col-xs-12">Scanning/Scoring Option: 
 														<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="If nothing is selected, Alpha will be chosen by default." >
						                          		<span class=" fa fa-info"></span>
	 					                          	     </span>
						                        </label>
						                        <div
													class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
													<input type="text" class="form-control"
														readonly="readonly"
														id="inputSuccess1" value="Scan answer sheets at a Regional BOCES Scan Site by deadline."> <span
														aria-hidden="true"></span>
												</div>
			                      			</div>
			                      			<br />
			                      			<div class="form-group">
				                        		<label class="control-label col-md-3 col-sm-3 col-xs-12">Reporting Option: 
 														<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="If nothing is selected, Alpha will be chosen by default." >
						                          		<span class=" fa fa-info"></span>
	 					                          	     </span>
						                        </label>
						                        <div class="col-md-6 col-sm-6 col-xs-12">
						                        	<div
														class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
														<input type="text" class="form-control"
															readonly="readonly"
															id="inputSuccess1" value="NERIC will load scores into Level 1"> <span
															aria-hidden="true"></span>
													</div>
			                        			</div>
			                      			</div>
											<br />
											<div class="form-group">
				                        		<label class="control-label col-md-3 col-sm-3 col-xs-12">Printing Option: 
						                        </label>
						                         <div class="col-md-6 col-sm-6 col-xs-12">
						                        	<div>
														class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
														<input type="text" class="form-control"
															readonly="readonly"
															id="inputSuccess1" value="Teacher (Building/Teacher/Student)"> <span
															aria-hidden="true"></span>
													</div>
			                        			</div>
			                      			</div>
			                      			<br />
			                      			<div class="form-group">
			                      				<p>By clicking "Submit", you signify that you have permission to order these Regents forms from an appropriate person who can authorize this expenditure.</p>
			                      			</div>
			                      		</form>
							              <!-- END OPTIONS -->
										<!-- END STEP 6 -->
									</div>
									</form:form>
								</div>
								<!-- End SmartWizard Content -->
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /page content -->
	<jsp:include page="fragments/footer.jsp" />
</html>