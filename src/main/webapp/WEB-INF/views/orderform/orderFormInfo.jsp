<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../fragments/header.jsp" />
<!-- Custom CSS -->
<link href="<c:url value='/resources/vendors/trumbowyg/ui/trumbowyg.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/vendors/trumbowyg/plugins/colors/ui/trumbowyg.colors.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../fragments/nav.jsp" />

<!-- page variables -->
<c:url value="/order" var="order" />
<c:url value='/orders' var="ordersLink"/>
	<!-- page content -->
	<div class="right_col" role="main">
		<div class="">
			<div class="clearfix"></div>
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>Order Form Info</h2>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<form:form method="POST" modelAttribute="config" role="form">
								<form:input type="hidden" path="id" id="id"/>
								<form:textarea path="data" id="editor" cssClass="trumbowyg-textarea"/>
							  	<!-- <textarea id="editor" class="trumbowyg-textarea"></textarea> -->
							  <input type="submit" value="Update" class="btn btn-success"/> <a href="<c:url value='/admin/orderFormInfo' />" class="btn btn-primary">Cancel</a>
							</form:form>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /page content -->

<jsp:include page="../fragments/footer.jsp" />
<jsp:include page="../fragments/scripts.jsp" />

<!-- Custom Scripts -->
<!-- trumbowyg.js -->
   	<script src="<c:url value='/resources/vendors/trumbowyg/trumbowyg.min.js' />"></script>
   	<script src="<c:url value='/resources/vendors/trumbowyg/plugins/colors/trumbowyg.colors.min.js' />"></script>
	<script type="text/javascript">
	  $('#editor').trumbowyg({
		  btns: [
			    ['viewHTML'],
		        ['undo', 'redo'], // Only supported in Blink browsers
		        ['formatting'],
		        ['strong', 'em'],
		        ['superscript', 'subscript'],
		        ['foreColor', 'backColor'],
		        ['link'],
		        ['insertImage'],
		        ['justifyLeft', 'justifyCenter', 'justifyRight', 'justifyFull'],
		        ['unorderedList', 'orderedList'],
		        ['horizontalRule'],
		        ['removeformat']
		    ]
	  });
	  
	  $('#editor').trumbowyg('html', '${config.data}');
	</script>
<jsp:include page="../fragments/close.jsp" />