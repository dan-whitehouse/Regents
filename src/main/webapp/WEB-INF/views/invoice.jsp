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

<html>
	<jsp:include page="fragments/header.jsp" />
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
								<li><a href="${createOrderLink}">Create Order</a>
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
                       		<strong>Contact: </strong> ${order.orderContact.name}
                      	</div>
                      	<div class="col-sm-4 invoice-col"> 
                       		<strong>Email: </strong> ${order.orderContact.email} 
                      	</div>
                      	<div class="col-sm-4 invoice-col"> 
                       		<strong>Phone: </strong> ${order.orderContact.phone} 
                      	</div>
                      	<div class="col-sm-4 invoice-col"> 
                       		<strong>District: </strong> ${order.orderContact.district.name}
                      	</div>
                      	<div class="col-sm-4 invoice-col"> 
                       		<strong>School: </strong> ${order.orderContact.school.name}
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
		                              	<th width="80%">Product</th>
		                                <th width="10%">Qty</th>
	                                	<!-- <th width="10%">Subtotal</th> -->
		                              </tr>
		                            </thead>
		                            <tbody>
		                            	<c:forEach items="${order.orderExams}" var="orderExam">
											<tr>
												<td>${orderExam.exam.name} - ${orderExam.exam.code}</td>
												<td>${orderExam.examAmount}</td>
		 										<%-- <td>
		 											$<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${(orderExam.examAmount * 2.10)}" />
		 										</td>
		 										
		 										<!-- Calculate the subtotal cost of exams. Will be used to calculate total  -->
		 										<c:set var="examTotal" value="${examTotal + (orderExam.examAmount * 2.10)}" /> --%>
											</tr>
										</c:forEach>	
		                            </tbody>
	                          	</table>
	                        </div>
                      	</div>
					  </c:if>


                      <!-- Table row -->
                      <c:if test="${fn:length(order.orderExams) > 0}">
	                      <div class="row">
	                        <div class="col-xs-12 table">
	                          <table class="table table-striped">
	                            <thead>
	                              <tr>
	                              	<th width="80%">Product</th>
	                                <th width="10%">Qty</th>
	                                <!-- <th width="10%">Subtotal</th> -->
	                              </tr>
	                            </thead>
	                            <tbody>
	                            	<c:forEach items="${order.orderDocuments}" var="orderDocument">
										<tr>
											<td>${orderDocument.document.name}</td>
											<td>${orderDocument.documentAmount}</td>
											<%-- <td>
												$<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${(orderDocument.documentAmount * 0.40)}" />
											</td> 

											<!-- Calculate the subtotal cost of documents. Will be used to calculate total  -->
											<c:set var="docTotal" value="${docTotal + (orderDocument.documentAmount * 0.40)}" /> --%>
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
                             Your district will be billed for the precise number of tests and non-secure documents processed through NERIC. The rate for the Regents scanning/scoring service is $2.10 per student per test processed. The rate for non-secure documents is $0.40 per document ordered. Your district will be billed in the academic year. If you need a data file you must request it by sending an email to testing@neric.org; do this only after you confirm the accuracy of the scores on your reports.
                          </p>
                        </div>

                        <%-- <div class="col-xs-6">
                          <p class="lead">Amount Due:</p>
                          <div class="table-responsive">
                            <table class="table">
                              <tbody>
                                <tr>
                                  <th style="width:50%">Subtotal:</th>
                                  <td>
                                  		<c:set var="total" value="${(docTotal + examTotal)}" />
                                  		$<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${total}" />
								  </td>
                                </tr>
                                <tr>
                                  <th>Tax (8%)</th>
                                  <td>
                                  		<c:set var="tax" value="${(total * 0.08)}" />
                                  		$<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${tax}" />
                                  </td>
                                </tr>
                                <tr>
                                  <th>Total:</th>
                                  <td>
                                  		$<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${total + tax}" />
                                  </td>
                                  
                                </tr>
                              </tbody>
                            </table>
                          </div>
                        </div> --%>
                      </div>

                      <!-- this row will not appear when printing -->
                      <div class="row no-print">
                        <div class="col-xs-12">
                          <button class="btn btn-default pull-right" onclick="window.print();"><i class="fa fa-print"></i> Print</button>
<!--                           <button class="btn btn-success pull-right"><i class="fa fa-credit-card"></i> Submit Payment</button> -->
<!--                           <button class="btn btn-primary pull-right" style="margin-right: 5px;"><i class="fa fa-download"></i> Generate PDF</button> -->
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
	<jsp:include page="fragments/footer.jsp" />
</html>