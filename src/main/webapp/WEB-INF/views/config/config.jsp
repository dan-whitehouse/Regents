<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../fragments/header.jsp" />
<jsp:include page="../fragments/nav.jsp" />
<!-- page variables -->
<c:url value="/admin/schools/create" var="createSchool" />
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Configurables</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<table id="datatable" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th width="10%">Name</th>
									<th width="10%">Path</th>
									<th width="70%">Description</th>
<!-- 									<th width="40%">Preview</th> -->
									<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
										<th>Management</th>
									</sec:authorize>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${configurables}" var="config">
									<tr>
										<td>${config.id}</td>
										<td>${config.href}</td>
										<td>${config.description}</td>
<%-- 										<td>${fn:substring(config.data,0,300)}</td> --%>
										<sec:authorize access="hasRole('ADMIN')">
											<td>
												<a href="<c:url value='/admin/config/${config.uuid}/edit' />" class="btn btn-success custom-width" data-toggle="tooltip" data-placement="top" data-original-title="Edit"><i class="fa fa-pencil"></i></a>
											</td>
										</sec:authorize>
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
<jsp:include page="../fragments/footer.jsp" />
<jsp:include page="../fragments/scripts.jsp" />
<jsp:include page="../fragments/close.jsp" />