<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/createUser" var="createUser" />
<c:set var="examTotal" value="0.00" />
<c:set var="docTotal" value="0.00" />
<c:set var="total" value="0.00" />

<html>
	<jsp:include page="fragments/header.jsp" />
	<!-- page content -->
	<div class="right_col" role="main">
          <div class="">
<!--             <div class="page-title"> -->
<!--               <div class="title_left"> -->
<!--                 <h3>Invoice <small>Some examples to get you started</small></h3> -->
<!--               </div> -->

<!--               <div class="title_right"> -->
<!--                 <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search"> -->
<!--                   <div class="input-group"> -->
<!--                     <input type="text" class="form-control" placeholder="Search for..."> -->
<!--                     <span class="input-group-btn"> -->
<!--                       <button class="btn btn-default" type="button">Go!</button> -->
<!--                     </span> -->
<!--                   </div> -->
<!--                 </div> -->
<!--               </div> -->
<!--             </div> -->

            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Order</h2>
                    <sec:authorize access="hasRole('ADMIN')">
	                   <ul class="nav navbar-right panel_toolbox">
<!-- 	                   	<li class="pull-right"><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li> -->
	                     <li class="dropdown pull-right">
	                       <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
	                       <ul class="dropdown-menu" role="menu">
	                         <li>
	                         		<a href="#">Edit Order</a>
	                         </li>
	                       </ul>
	                     </li>
	                     
	                   </ul>
                    </sec:authorize>
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
                      
                      <br />


                       	<!-- Table row -->
						<c:if test="${fn:length(order.orderExams) > 0}">
                      		<div class="row">
		                        <div class="col-xs-12 table">
		                          <table class="table table-striped">
		                            <thead>
		                              <tr>
		                                <th width="10%">Qty</th>
	                                	<th width="80%">Product</th>
	                                	<th width="10%">Subtotal</th>
		                              </tr>
		                            </thead>
		                            <tbody>
		                            	<c:forEach items="${order.orderExams}" var="orderExam">
											<tr>
												<td>${orderExam.examAmount}</td>
												<td>${orderExam.exam.name} - ${orderExam.exam.code}</td>
		 										<td>$${(orderExam.examAmount * 2.10)} </td>
		 										
		 										<c:set var="examTotal" value="${examTotal + (orderExam.examAmount * 2.10)}" />
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
	                                <th width="10%">Qty</th>
	                                <th width="80%">Product</th>
	                                <th width="10%">Subtotal</th>
	                              </tr>
	                            </thead>
	                            <tbody>
	                            	<c:forEach items="${order.orderDocuments}" var="orderDocument">
										<tr>
											<td>${orderDocument.documentAmount}</td>
											<td>${orderDocument.document.name}</td>
											<td>$${(orderDocument.documentAmount * 0.40)} </td> 
											
											<c:set var="docTotal" value="${docTotal + (orderDocument.documentAmount * 0.40)}" />
										</tr>
									</c:forEach>	
	                            </tbody>
	                          </table>
	                        </div>
	                      </div>
						</c:if>
                      
                      
						
                      <div class="row">
                        <!-- accepted payments column -->
                        <div class="col-xs-6">
                          <p class="lead">Billing:</p>
                          <p class="text-muted well well-sm no-shadow" style="margin-top: 10px;">
                             Your district will be billed for the precise number of tests and non-secure documents processed through NERIC. The rate for the Regents scanning/scoring service is $2.10 per student per test processed. The rate for non-secure documents is $0.40 per document ordered. Your district will be billed in the academic year. If you need a data file you must request it by sending an email to testing@neric.org; do this only after you confirm the accuracy of the scores on your reports.
                          </p>
                        </div>
                        <!-- /.col -->
                        <div class="col-xs-6">
                          <p class="lead">Amount Due:</p>
                          <div class="table-responsive">
                            <table class="table">
                              <tbody>
                                <tr>
                                  <th style="width:50%">Subtotal:</th>
                                  <td>
                                  		<c:set var="total" value="${(docTotal + examTotal)}" />
                                  		$${total}
								  </td>
                                </tr>
                                <tr>
                                  <th>Tax (8%)</th>
                                  <td>
                                  		<c:set var="tax" value="${(total * 0.08)}" />
                                  		$${tax}
                                  </td>
                                </tr>
                                <tr>
                                  <th>Total:</th>
                                  <td>$${total + tax}</td>
                                </tr>
                              </tbody>
                            </table>
                          </div>
                        </div>
                        <!-- /.col -->
                      </div>
                      <!-- /.row -->

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