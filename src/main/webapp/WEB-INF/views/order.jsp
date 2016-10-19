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
										Step 3<br />
										<small>Options</small>
										</span>
										</a>
									</li>
									<li>
										<a href="#step-5">
										<span class="step_no">5</span>
										<span class="step_descr">
										Step 5<br />
										<small>Authorize</small>
										</span>
										</a>
									</li>
								</ul>
								<div id="step-1">
									<div class="col-md-2 col-sm-2 col-xs-12"></div><!-- Empty div for proper spacing -->
									<div class="col-md-7 col-sm-7 col-xs-12">
										<h2 class="StepTitle">JUNE 2016 - REGENTS ANSWER SHEET SERVICES & NON SECURE DOCUMENTS ORDER FORM</h2>
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
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Algebra 1 (Common Core) - 02106" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <th scope="row"></th>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Algebra 2 (Common Core) - 20106CC" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Algebra 2 / Trigonometry - 02052CC" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <th scope="row"></th>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Comprehensive English - 01003" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <th scope="row"></th>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="ELA (Common Core) - 01003CC" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <th scope="row"></th>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Geometry (Common Core) - 02072CC" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <th scope="row"></th>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Global History & Geography - 04052" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <th scope="row"></th>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Integrated Algebra - 02052" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <th scope="row"></th>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Living Environment - 03051" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <th scope="row"></th>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Physical Settings / Chemstry - 03101" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <th scope="row"></th>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Physical Settings / Earth Science - 03001" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <th scope="row"></th>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Physical Settings / Physics - 03151" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <th scope="row"></th>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="US History & Government - 04101" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                          <th scope="row"></th>
					                        </tr>
					                      </tbody>
					                    </table>
					                </div>
									</form>
								</div>
								<div id="step-3">
									<div class="col-md-3 col-sm-3 col-xs-12"></div><!-- Empty div for proper spacing -->
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
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Chemistry" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Comprehensive English" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="ELA Common Core" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Global History & Geography" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Physical Settings/Earth Science" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="Physics" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                        </tr>
					                        <tr>
					                          <th scope="row"><input type="checkbox" class="flat"></th>
					                          <td><input id="middle-name" type="text" name="middle-name" readonly="readonly" value="US History & Government" class="form-control col-md-7 col-xs-12"></td>
					                          <td><input id="middle-name" type="text" name="middle-name" value="0" class="form-control col-md-3 col-xs-12"></td>
					                        </tr>
					                      </tbody>
					                    </table>
					                </div>
								</div>
								<div id="step-4">
									<form class="form-horizontal form-label-left">
									
										<div class="form-group">
			                        		<label class="control-label col-md-3 col-sm-3 col-xs-12">Scanning/Scoring Option: 
													<!-- <span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="If nothing is selected, Alpha will be chosen by default." >
					                          		<span class=" fa fa-info"></span>
 					                          	     </span> -->
					                        </label>
					                        <div class="col-md-6 col-sm-6 col-xs-12">
						                          <div class="radio">
						                            <label>
						                              <input type="radio" class="flat" checked name="iCheck"> Scan answer sheets at a Regional BOCES Scan Site by deadline.
						                            </label>
						                          </div>
						                          <div class="radio">
						                            <label>
						                              <input type="radio" class="flat" name="iCheck"> Manually score exams and mail or hand-deliver sheets to NERIC by deadline.
						                            </label>
						                          </div>
						                          <div class="radio">
						                            <label>
						                              <input type="radio" class="flat" name="iCheck"> Score answer sheets In-District by deadline. 
						                            </label>
						                          </div>
		                        			</div>
		                      			</div>
		                      			<br />
		                      			<div class="form-group">
			                        		<label class="control-label col-md-3 col-sm-3 col-xs-12">Reporting Option: 
													<!-- <span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="If nothing is selected, Alpha will be chosen by default." >
					                          		<span class=" fa fa-info"></span>
 					                          	     </span> -->
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
							                            <option>Choose option</option>
							                            <option>Alpha (Building/Student)</option>
							                            <option>Teacher (Building/Teacher/Student)</option>
							                            <option>Course Section (Building/Course Section/Student)</option>
						                          </select>
		                        			</div>
		                      			</div>
		                      			

		                      		</form>
								</div>
								<div id="step-5">
									<h2 class="StepTitle">Step 5 Content</h2>
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
										Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
									</p>
									<p>
										Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor
										in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
									</p>
									<p>
										Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor
										in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
									</p>
								</div>
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