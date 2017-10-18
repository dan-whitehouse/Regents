<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/admin/users/${user.uuid}/edit" var="editLink" />
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
							<h2>User</h2>
							<ul class="nav navbar-right panel_toolbox">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="${editLink}">Edit User</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<div class="col-md-2 col-sm-2 col-xs-12 profile_left">
								<h3>${user.firstName} ${user.lastName}</h3>
								<ul class="list-unstyled user_data">
									<li><i class="fa fa-user user-profile-icon"></i> ${user.username}
									</li>
									<li>
										<i class="fa fa-group user-profile-icon"></i>
										<c:forEach items="${user.userProfiles}" var="up" varStatus="status">
											${up.type}
											<c:if test="${not(status.count eq fn:length(user.userProfiles))}">,</c:if>
										</c:forEach>
									</li>
								</ul>
							</div>
							<div class="col-md-10 col-sm-10 col-xs-12">
								<div class="" role="tabpanel" data-example-id="togglable-tabs">
									<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
										<li role="presentation" class="active"><a href="#tab_content1" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">Orders</a>
										</li>
										<li role="presentation" class=""><a href="#tab_content2" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="false">Not Administering</a>
										</li>
										<li role="presentation" class=""><a href="#tab_content3" role="tab" id="profile-tab2" data-toggle="tab" aria-expanded="false">Districts</a>
										</li>
									</ul>
									<div id="myTabContent" class="tab-content">
										<div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="home-tab">
											<!-- ORDERS -->
											<table id="datatable" class="table table-striped table-bordered">
												<thead>
													<tr>
														<th>Order ID</th>
														<th>Order Date</th>
														<th>Period</th>
														<th>Status</th>
														<th>District</th>
                                                        <th>School</th>
														<sec:authorize access="hasRole('ADMIN')">
                                                            <th></th>
                                                        </sec:authorize>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${user.orders}" var="order" varStatus="status">
													    <fmt:formatDate var="schoolYear" value="${order.orderForm.startDate}" pattern="yyyy" />
														<tr>
															<td><span style="text-decoration: underline;"><a href="<c:url value='/order/${order.uuid}' />">${order.uuid}</a></span></td>
															<td>${order.orderDate}</td>
															<td>
															    ${order.orderForm.period} ${schoolYear}
															</td>
															<td>
																<c:choose>
																	<c:when test="${order.orderStatus == 'Processing'}">
																		<p class="red">${order.orderStatus}</p>
																	</c:when>
																	<c:otherwise>
																		<p class="green">${order.orderStatus}</p>
																	</c:otherwise>
																</c:choose>
															</td>
															<td>${order.district.name}</td>
                                                            <td>${order.school.name}</td>
                                                            <sec:authorize access="hasRole('ADMIN')">
                                                                <td>
                                                                    <p  class="pull-right">
                                                                        <a href="<c:url value='/order/${order.uuid}/edit' />" class="btn btn-success custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Edit"><i class="fa fa-pencil"></i></a>
                                                                        <a type="button" class="btn btn-danger custom-width" data-toggle="modal" data-target=".modal-sm-${order.uuid}"><i class="fa fa-trash"></i></a>
                                                                        <c:choose>
                                                                            <c:when test="${order.orderStatus == 'Processing'}">
                                                                                <a href="<c:url value='/order/${order.uuid}/complete/true' />" class="btn btn-default custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Incomplete"><i class="fa fa-square"></i></a>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <a href="<c:url value='/order/${order.uuid}/complete/false' />" class="btn btn-info custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Complete"><i class="fa fa-check-square"></i></a>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </p>
                                                                </td>
                                                            </sec:authorize>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<sec:authorize access="hasRole('ADMIN')">
                                                <c:forEach items="${user.orders}" var="order">
                                                    <div class="modal fade modal-sm-${order.uuid}" tabindex="-2" role="dialog" aria-hidden="true">
                                                        <div class="modal-dialog modal-sm">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">?</span>
                                                                    </button>
                                                                    <h4 class="modal-title" id="myModalLabel2">Confirmation</h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <h5>Delete Order: </h5>
                                                                    <p>${order.uuid}</p>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                                                    <a href="<c:url value='/admin/order/${order.uuid}/delete' />" class="btn btn-danger">Delete</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </sec:authorize>
											<!-- END ORDERS -->
										</div>
										<div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="profile-tab">
											<!-- NOT ADMINISTERING -->
											<table id="datatable-2" class="table table-striped table-bordered">
												<thead>
													<tr>
														<th>Not Administering ID</th>
														<th>Date</th>
														<th>District</th>
														<sec:authorize access="hasRole('ADMIN')">
														    <th></th>
														</sec:authorize>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${optOuts}" var="optOut" varStatus="status">
														<tr>
															<td>${optOut.uuid}</td>
															<td>${optOut.optOutDate}</td>
															<td>${optOut.district.name}</td>
															<sec:authorize access="hasRole('ADMIN')">
                                                                <td>
                                                                    <p  class="pull-right">
                                                                        <a type="button" class="btn btn-danger custom-width" data-toggle="modal" data-target=".modal-sm-${optOut.uuid}"><i class="fa fa-trash"></i></a>
                                                                    </p>
                                                                </td>
                                                            </sec:authorize>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<sec:authorize access="hasRole('ADMIN')">
                                                <c:forEach items="${optOuts}" var="optOut">
                                                    <div class="modal fade modal-sm-${optOut.uuid}" tabindex="-3" role="dialog" aria-hidden="true">
                                                        <div class="modal-dialog modal-sm">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">?</span>
                                                                    </button>
                                                                    <h4 class="modal-title" id="myModalLabel3">Confirmation</h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <h5>Delete Not Administering: </h5>
                                                                    <p>${optOut.uuid}</p>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                                                    <a href="<c:url value='/admin/optout/${optOut.uuid}/delete' />" class="btn btn-danger">Delete</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </sec:authorize>
											<!-- END NOT ADMINISTERING -->
										</div>
										<div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="profile-tab">
											<!-- DISTRICTS -->
											<table id="datatable-3" class="table table-striped table-bordered">
												<thead>
													<tr>
														<th>Name</th>
														<th>BEDS Code</th>
														<sec:authorize access="hasRole('ADMIN')">
                                                            <th></th>
                                                        </sec:authorize>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${user.userDistricts}" var="userDistrict" varStatus="status">
														<tr>
															<td>
                                                                <sec:authorize access="hasRole('ADMIN')">
															        <span style="text-decoration: underline;">
															            <a href="<c:url value='/admin/districts/${userDistrict.district.uuid}' />">
															                ${userDistrict.district.name}
                                                                        </a>
                                                                    </span>
                                                                    </sec:authorize>
                                                                    <sec:authorize access="hasRole('USER')">
                                                                        ${userDistrict.district.name}
                                                                </sec:authorize>
                                                            </td>
															<td>${userDistrict.district.bedsCode}</td>
															<sec:authorize access="hasRole('ADMIN')">
                                                                <td>
                                                                    <p  class="pull-right">
                                                                        <a href="<c:url value='/admin/districts/${userDistrict.district.uuid}/edit' />" class="btn btn-success custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Edit"><i class="fa fa-pencil"></i></a>
                                                                        <a type="button" class="btn btn-danger custom-width" data-toggle="modal" data-target=".modal-sm-${userDistrict.district.uuid}"><i class="fa fa-trash"></i></a>
                                                                        <!-- Visible -->
                                                                        <c:choose>
                                                                            <c:when test="${userDistrict.district.visible == true}">
                                                                                <a href="<c:url value='/admin/districts/${userDistrict.district.uuid}/hide/false' />" class="btn btn-default custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Visible"><i class="fa fa-eye"></i></a>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <a href="<c:url value='/admin/districts/${userDistrict.district.uuid}/hide/true' />" class="btn btn-default custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Hidden"><i class="fa fa-eye-slash"></i></a>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                        <!-- Lock -->
                                                                        <c:choose>
                                                                            <c:when test="${userDistrict.district.locked == true}">
                                                                                <a href="<c:url value='/admin/districts/${userDistrict.district.uuid}/lock/false' />" class="btn btn-dark custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Locked"><i class="fa fa-lock"></i></a>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <a href="<c:url value='/admin/districts/${userDistrict.district.uuid}/lock/true' />" class="btn btn-dark custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Unlocked"><i class="fa fa-unlock"></i></a>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </p>
                                                                </td>
                                                            </sec:authorize>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<sec:authorize access="hasRole('ADMIN')">
                                                <c:forEach items="${user.userDistricts}" var="userDistrict">
                                                    <div class="modal fade modal-sm-${userDistrict.district.uuid}" tabindex="-1" role="dialog" aria-hidden="true">
                                                        <div class="modal-dialog modal-sm">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">?</span>
                                                                    </button>
                                                                    <h4 class="modal-title" id="myModalLabel1">Confirmation</h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <h5>Delete District: </h5>
                                                                    <p>${userDistrict.district.name}</p>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                                                    <a href="<c:url value='/admin/districts/${userDistrict.district.uuid}/delete' />" class="btn btn-danger">Delete</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </sec:authorize>
											<!-- END DISTRICTS -->
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /page content -->
	<jsp:include page="fragments/footer.jsp" />
</html>