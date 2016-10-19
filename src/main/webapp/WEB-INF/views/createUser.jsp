<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<jsp:include page="fragments/header.jsp" />
	<!-- page content -->
	<div class="right_col" role="main">
		<div class="">
			<!--             <div class="page-title"> -->
			<!--               <div class="title_left"> -->
			<!--                 <h3>Form Validation</h3> -->
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
							<h2>Create User</h2>
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
							<%--                     <form:form method="POST" modelAttribute="user" class="form-horizontal form-label-left" novalidate> --%>
							<form:form method="POST" modelAttribute="user" class="form-horizontal form-label-left">								
								<span class="section">User Info</span>
								<form:input type="hidden" path="id" id="id"/>
								
								<div class="item form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-6"></label>
									<div class="col-md-3 col-sm-3 col-xs-6" has-feedback>
										<form:input type="text" path="firstName" id="firstName" class="form-control col-md-7 col-xs-12 has-feedback-left" data-validate-length-range="6" data-validate-words="1" name="firstName" placeholder="First Name" required="required"/>
										<span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
										<div class="has-error">
											<form:errors path="firstName" class="help-inline"/>
										</div>
									</div>
									<div class="col-md-3 col-sm-3 col-xs-6" has-feedback>
										<form:input type="text" path="lastName" id="lastName" class="form-control col-md-7 col-xs-12 has-feedback-right" data-validate-length-range="6" data-validate-words="1" name="lastName" placeholder="Last Name" required="required"/>
										<span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
										<div class="has-error">
											<form:errors path="lastName" class="help-inline"/>
										</div>
									</div>
									
								</div>
								
								
								<div class="item form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Username <span class="required">*</span>
									</label>
									<div class="col-md-6 col-sm-6 col-xs-12">
										<form:input type="text" path="username" id="username" class="form-control col-md-7 col-xs-12 input-sm" data-validate-length-range="6" data-validate-words="1" name="username" placeholder="" required="required"/>
										<div class="has-error">
											<form:errors path="username" class="help-inline"/>
										</div>
									</div>
								</div>
								
								<div class="item form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Password <span class="required">*</span>
									</label>
									<div class="col-md-6 col-sm-6 col-xs-12">
										<form:input type="password" path="password" id="password" class="form-control col-md-7 col-xs-12 input-sm" data-validate-length-range="6" data-validate-words="1" name="password" placeholder="" required="required"/>
										<div class="has-error">
											<form:errors path="password" class="help-inline"/>
										</div>
									</div>
								</div>
								
								<div class="item form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Confirm Password <span class="required">*</span>
									</label>
									<div class="col-md-6 col-sm-6 col-xs-12">									
										<form:input type="password" path="password" id="passwordV" class="form-control col-md-7 col-xs-12 input-sm" data-validate-length-range="6" data-validate-words="1" name="passwordV" data-validate-linked="password" required="required"/>
										<div class="has-error">
											<form:errors path="password" class="help-inline"/>
										</div>
									</div>
								</div>
								
								<div class="item form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Email <span class="required">*</span>
									</label>
									<div class="col-md-6 col-sm-6 col-xs-12">									
										<form:input type="email" path="email" id="email" class="form-control col-md-7 col-xs-12 input-sm" data-validate-length-range="6" data-validate-words="1" name="email" required="required"/>
										<div class="has-error">
											<form:errors path="email" class="help-inline"/>
										</div>
									</div>
								</div>
								
								<div class="item form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">LEA <span class="required">*</span>
									</label>
										<div class="col-md-3 col-sm-3 col-xs-12">
				                          <select class="select2_single form-control select2-hidden-accessible" tabindex="-1" aria-hidden="true">
				                            <option></option>
				                            <option value="000001">District 1</option>
				                            <option value="000002">District 2</option>
				                            <option value="000003">District 3</option>
				                          </select><span class="select2 select2-container select2-container--default" dir="ltr" style="width: 251px;"><span class="selection"><span class="select2-selection select2-selection--single" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="-1" aria-labelledby="select2-taf7-container"><span class="select2-selection__rendered" id="select2-taf7-container"><span class="select2-selection__placeholder">Select LEA</span></span><span class="select2-selection__arrow" role="presentation"><b role="presentation"></b></span></span></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
				                        </div>
								</div>
								
								<div class="item form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Role <span class="required">*</span>
									</label>
									<div class="col-md-6 col-sm-6 col-xs-12">									
										<form:select path="userProfiles" items="${roles}" multiple="false" itemValue="id" itemLabel="type" class="form-control col-md-7 col-xs-12" required="required"/>
										<div class="has-error">
											<form:errors path="userProfiles" class="help-inline"/>
										</div>
									</div>
								</div>
								
								
								                 
								<!--                       <div class="item form-group"> -->
								<!--                         <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Confirm Email <span class="required">*</span> -->
								<!--                         </label> -->
								<!--                         <div class="col-md-6 col-sm-6 col-xs-12"> -->
								<!--                           <input type="email" id="email2" name="confirm_email" data-validate-linked="email" required="required" class="form-control col-md-7 col-xs-12"> -->
								<!--                         </div> -->
								<!--                       </div> -->
								<!--                       <div class="item form-group"> -->
								<!--                         <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">Number <span class="required">*</span> -->
								<!--                         </label> -->
								<!--                         <div class="col-md-6 col-sm-6 col-xs-12"> -->
								<!--                           <input type="number" id="number" name="number" required="required" data-validate-minmax="10,100" class="form-control col-md-7 col-xs-12"> -->
								<!--                         </div> -->
								<!--                       </div> -->
								<!--                       <div class="item form-group"> -->
								<!--                         <label class="control-label col-md-3 col-sm-3 col-xs-12" for="website">Website URL <span class="required">*</span> -->
								<!--                         </label> -->
								<!--                         <div class="col-md-6 col-sm-6 col-xs-12"> -->
								<!--                           <input type="url" id="website" name="website" required="required" placeholder="www.website.com" class="form-control col-md-7 col-xs-12"> -->
								<!--                         </div> -->
								<!--                       </div> -->
								<!--                       <div class="item form-group"> -->
								<!--                         <label class="control-label col-md-3 col-sm-3 col-xs-12" for="occupation">Occupation <span class="required">*</span> -->
								<!--                         </label> -->
								<!--                         <div class="col-md-6 col-sm-6 col-xs-12"> -->
								<!--                           <input id="occupation" type="text" name="occupation" data-validate-length-range="5,20" class="optional form-control col-md-7 col-xs-12"> -->
								<!--                         </div> -->
								<!--                       </div> -->
								<!--                       <div class="item form-group"> -->
								<!--                         <label for="password" class="control-label col-md-3">Password</label> -->
								<!--                         <div class="col-md-6 col-sm-6 col-xs-12"> -->
								<!--                           <input id="password" type="password" name="password" data-validate-length="6,8" class="form-control col-md-7 col-xs-12" required="required"> -->
								<!--                         </div> -->
								<!--                       </div> -->
								<!--                       <div class="item form-group"> -->
								<!--                         <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12">Repeat Password</label> -->
								<!--                         <div class="col-md-6 col-sm-6 col-xs-12"> -->
								<!--                           <input id="password2" type="password" name="password2" data-validate-linked="password" class="form-control col-md-7 col-xs-12" required="required"> -->
								<!--                         </div> -->
								<!--                       </div> -->
								<!--                       <div class="item form-group"> -->
								<!--                         <label class="control-label col-md-3 col-sm-3 col-xs-12" for="telephone">Telephone <span class="required">*</span> -->
								<!--                         </label> -->
								<!--                         <div class="col-md-6 col-sm-6 col-xs-12"> -->
								<!--                           <input type="tel" id="telephone" name="phone" required="required" data-validate-length-range="8,20" class="form-control col-md-7 col-xs-12"> -->
								<!--                         </div> -->
								<!--                       </div> -->
								<!--                       <div class="item form-group"> -->
								<!--                         <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">Textarea <span class="required">*</span> -->
								<!--                         </label> -->
								<!--                         <div class="col-md-6 col-sm-6 col-xs-12"> -->
								<!--                           <textarea id="textarea" required="required" name="textarea" class="form-control col-md-7 col-xs-12"></textarea> -->
								<!--                         </div> -->
								<!--                       </div> -->
								<div class="ln_solid"></div>
								<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<c:choose>
											<c:when test="${edit}">
												<input type="submit" value="Update" class="btn btn-success"/> <a href="<c:url value='/list' />" class="btn btn-primary">Cancel</a>
											</c:when>
											<c:otherwise>
												<input type="submit" value="Register" class="btn btn-success"/> <a href="<c:url value='/list' />" class="btn btn-primary">Cancel</a>
											</c:otherwise>
										</c:choose>
										<!--                           	<button type="submit" class="btn btn-primary">Cancel</button> -->
										<!--                           	<button id="send" type="submit" class="btn btn-success">Submit</button> -->
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
</html>