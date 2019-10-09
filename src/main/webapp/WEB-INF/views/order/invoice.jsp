<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="examTotal" value="0.00" />
<c:set var="docTotal" value="0.00" />
<c:set var="total" value="0.00" />
<c:url value="/order" var="createOrderLink" />
<c:url value="/order/${order.uuid}/edit" var="editOrderLink" />
<fmt:formatNumber var="inDistrictScanFee" value="${orderForm.inDistrictScanFee}" type="currency"/>
<fmt:formatNumber var="nonSecureDocumentFee" value="${orderForm.nonSecureDocumentFee}" type="currency"/>
<html>
	<jsp:include page="../fragments/header.jsp" />
	<!-- page content -->
	<div class="right_col" role="main">
		<div class="">
			<div class="clearfix"></div>
			<div class="row">
				<div class="col-md-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>Order</h2>
							<ul class="nav navbar-right panel_toolbox">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
									<ul class="dropdown-menu" role="menu">
										<li>
											<a href="${createOrderLink}">Create Order</a>
											<c:if test="${order.orderStatus ne 'Complete'}">
										<li><a href="${editOrderLink}">Edit Order</a></li>
										</c:if>
									</ul>
								</li>
							</ul>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<section class="content invoice">
								<!-- info row -->
								<div class="row invoice-info">
									<div class="col-sm-4 invoice-col">
										<strong>Order #: </strong> ${order.uuid}
									</div>
									<div class="col-sm-4 invoice-col"> 
										<strong>Date: </strong> ${order.orderDate} 
									</div>
									<div class="col-sm-4 invoice-col"> 
										<strong>Status: </strong> ${order.orderStatus} 
									</div>
								</div>
								<hr />
								<div class="row invoice-info">
									<div class="col-sm-4 invoice-col"> 
										<strong>Contact: </strong> ${order.orderContact.firstName} ${order.orderContact.middleName} ${order.orderContact.lastName}
									</div>
									<div class="col-sm-4 invoice-col"> 
										<strong>Email: </strong> ${order.orderContact.email} 
									</div>
									<div class="col-sm-4 invoice-col"> 
										<strong>Phone: </strong> ${order.orderContact.phone} 
									</div>
									<div class="col-sm-4 invoice-col"> 
										<strong>District: </strong> ${order.district.name}
									</div>
									<div class="col-sm-4 invoice-col"> 
										<strong>School: </strong> ${order.school.name}
									</div>
								</div>
								<br />
								<!-- Table row -->
								<c:if test="${fn:length(order.orderExams) > 0}">
									<div class="row">
										<div class="col-xs-12 table">
											<table class="table table-striped">
												<thead>
													<tr>
														<th>Exams</th>
														<c:if test="${orderForm.period eq 'June'}">
															<th># Students Pull from L0 </th>
														</c:if>
														<th># Blank Answer Sheets</th>
														<th># Students in Data File</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${sortedExamList}" var="orderExam">
														<tr>
															<td class="col-xs-6">${orderExam.exam.name} - ${orderExam.exam.code}</td>

															<c:if test="${orderForm.period eq 'June'}">
																<td>${orderExam.examAmount}</td>
															</c:if>
															<td>${orderExam.answerSheetAmount}</td>
															<td>${orderExam.studentsPerCSV}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</c:if>
								<!-- Table row -->
								<c:if test="${fn:length(order.orderDocuments) > 0}">
									<div class="row">
										<div class="col-xs-12 table">
											<table class="table table-striped">
												<thead>
													<tr>
														<th>Non-Secure Documents</th>
														<th>Quantity</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${sortedDocumentList}" var="orderDocument">
														<tr>
															<td class="col-xs-6">${orderDocument.document.name}</td>
															<td>${orderDocument.documentAmount}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</c:if>
								<div class="row">
									<!-- accepted payments column -->
									<div class="col-xs-12">
										<p class="lead">Billing:</p>
										<p class="text-muted well well-sm no-shadow" style="margin-top: 10px;">
											NERIC no longer charges by scanned exams. NERIC moved to a pay one price model based on RWADA (student counts for non-public schools) for testing services. 
											This will simplify the billing process for both BOCES and districts and you will now have exact testing charges ahead of time for budget planning rather than having to wait much later in the year to be billed for number of tests scanned.   
											<strong>This model will include all scanning and non-secure documents charges for the Regents Exams.</strong>
											If you are a public school district, this will be implemented immediately on the commitment forms that are sent to districts. Non-public schools will be billed in the fall of the current school year for testing services.  
											Non-public schools will be billed in the fall of 2018 for the 2018-2019 testing charge.  Questions about this billing model can be directed to <a href="mailto:testing@neric.org">testing@neric.org</a>
										</p>
									</div>
								</div>
								<!-- this row will not appear when printing -->
								<div class="row no-print">
									<div class="col-xs-12">
										<button class="btn btn-default pull-right" onclick="window.print();"><i class="fa fa-print"></i> Print</button>
									</div>
								</div>
							</section>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /page content -->
	<jsp:include page="../fragments/footer.jsp" />
</html>