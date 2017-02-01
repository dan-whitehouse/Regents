<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
							<h2>Manage Document</h2>
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
							<form:form method="POST" modelAttribute="xForm" class="form-horizontal form-label-left">
							
								 <!-- EXAMS -->
								<h2>Exams3</h2>
								<table>
									<tr>
										<th width="200px">Select</th>
										<th width="200px">Test</th>
									</tr>
								    <c:forEach items="${xForm.allAvailableExams}" var="x" varStatus="status">
								        <tr>
								            <th scope="row"><input name="allAvailableExams[${status.index}].selected" type="checkbox" class="flat"></th>
								            <th scope="row"><form:input path="allAvailableExams[${status.index}].orderExam.examAmount" type="text" class="form-control col-md-3 col-xs-12"/></th>
<%-- 								            <th scope="row"><input name="allAvailableExams[${status.index}].orderExam.examAmount" type="text" class="form-control col-md-3 col-xs-12"></th> --%>
								            <td scope="row">${x.orderExam.exam.name}</td>
								        </tr>
								    </c:forEach>
							    </table>
							    
							     <!-- DOCUMENTS -->
							    <h2>Documents2</h2>
							    <table>
									<tr>
										<th width="200px">Select</th>
										<th width="200px">Test</th>
									</tr>
								    <c:forEach items="${xForm.allAvailableDocuments}" var="x" varStatus="status">
								        <tr>
								            <th scope="row"><input name="allAvailableDocuments[${status.index}].selected" type="checkbox" class="flat"></th>
								            <td scope="row">${x.orderDocument.document.name}</td>
								        </tr>
								    </c:forEach>
							    </table>
							    
							    <!-- PRINTING -->
							    <h2>Printing4</h2>
							    <div class="form-group">
	                        		<label class="control-label col-md-3 col-sm-3 col-xs-12">Printing Option: 
	                        			<span class="badge bg-black" data-toggle="tooltip" data-placement="top" title="" data-original-title="If nothing is selected, Alpha will be chosen by default." >
			                          		<span class=" fa fa-info"></span>
			                          	</span>
			                        </label>
			                        <div class="col-md-6 col-sm-6 col-xs-12">
			                        
<!-- 			                       		<select class="form-control"> -->
<%-- 				                        	<c:forEach items="${xForm.allAvailableOptionPrints}" var="x" varStatus="status"> --%>
<%-- 				                        		<option value="allAvailableOptionPrints[${status.index}].selected">${x.optionPrint.name}</option> --%>
<%-- 	                      					</c:forEach> --%>
<!--                       					</select> -->
                      					
                      					<form:select path="allAvailableOptionPrints[${status.index}].selected" items="${xForm.allAvailableOptionPrints}" var="x" varStatus="status" />
                      					
                      					
<%--                       					<c:forEach items="${xForm.allAvailableOptionPrints}" var="x" varStatus="status"> --%>
<!-- 			                        		<div class="radio"> -->
<!-- 					                            <label> -->
<%-- 					                            	<input name="allAvailableOptionPrints[${status.index}].selected" type="radio" class="flat"/>${x.optionPrint.name } --%>
<!-- 					                            </label> -->
<!-- 				                          	</div> -->
<%-- 			                        	</c:forEach> --%>

                        			</div>
                      			</div>
							    
							    
							    <!-- SUBMIT -->
							    <div class="ln_solid"></div>
								<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<c:choose>
											<c:when test="${edit}">
												<input type="submit" value="Update" class="btn btn-success"/> <a href="<c:url value='/list' />" class="btn btn-primary">Cancel</a>
											</c:when>
											<c:otherwise>
												<input type="submit" value="Create" class="btn btn-success"/> <a href="<c:url value='/list' />" class="btn btn-primary">Cancel</a>
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