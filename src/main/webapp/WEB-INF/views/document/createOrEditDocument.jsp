<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<jsp:include page="../fragments/header.jsp" />
<jsp:include page="../fragments/nav.jsp" />
<!-- page variables -->
<c:url value="/admin/documents/create" var="createDocumentLink" />
<c:url value="/admin/documents/${document.uuid}/edit" var="editDocumentLink" />
<sec:authorize access="hasRole('ADMIN')">
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
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
									<ul class="dropdown-menu" role="menu">
										<li>
											<a href="${createDocumentLink}">Create Document</a>
											<c:if test="${orderForm.locked != true}">
										<li><a href="${editDocumentLink}">Edit Document</a></li>
										</c:if>
									</ul>
								</li>
							</ul>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<form:form method="POST" modelAttribute="document" class="form-horizontal form-label-left" data-toggle="validator" role="form">
								<form:input type="hidden" path="id" id="id"/>
								<div class="item form-group has-feedback">
									<label for="name" class="control-label col-md-3 col-sm-3 col-xs-3 left">Document Name</label>
									<div class="input-group col-md-6 col-sm-6 col-xs-6">
										<span class="input-group-addon"><i class="fa fa-file-o"></i></span>
										<form:input type="text" path="name" id="name" class="form-control col-md-7 col-xs-12 active" name="name" placeholder="Document Name" required="required"/>
									</div>
									<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
									<!-- <div class="help-block with-errors">Hey look, this one has feedback icons!</div> -->
								</div>
								<%-- <div class="item form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-6"></label>
									<div class="col-md-3 col-sm-3 col-xs-6" has-feedback>
										<form:input type="text" path="name" id="name" class="form-control col-md-7 col-xs-12 has-feedback-left" name="name" placeholder="Document Name" required="required"/>
										<span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
										<div class="has-error">
											<form:errors path="name" class="help-inline"/>
										</div>
									</div>
									</div> --%>
								<div class="ln_solid"></div>
								<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<c:choose>
											<c:when test="${edit}">
												<input type="submit" value="Update" class="btn btn-success"/> <a href="<c:url value='/admin/documents' />" class="btn btn-primary">Cancel</a>
											</c:when>
											<c:otherwise>
												<input type="submit" value="Create" class="btn btn-success"/> <a href="<c:url value='/admin/documents' />" class="btn btn-primary">Cancel</a>
											</c:otherwise>
										</c:choose>
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
</sec:authorize>
<jsp:include page="../fragments/footer.jsp" />
<jsp:include page="../fragments/scripts.jsp" />
<jsp:include page="../fragments/close.jsp" />