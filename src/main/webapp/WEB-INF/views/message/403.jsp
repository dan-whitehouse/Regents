<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<jsp:include page="../fragments/header.jsp" />
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12">
				<div class="col-middle">
					<div class="text-center text-center">
						<h1 class="error-number">403</h1>
						<h2>Access Denied</h2>
						<p>${error_message}</p>
						<p>${error_url}</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../fragments/footer.jsp" />