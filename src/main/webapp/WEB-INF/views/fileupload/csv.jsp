<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

    <jsp:include page="../fragments/header.jsp" />

    <!-- page content -->
    <div class="right_col" role="main">
        <div class="row">

            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Upload</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <form:form action="${postUrl}?${_csrf.parameterName}=${_csrf.token}" method="POST" class="x_content dropzone" enctype="multipart/form-data"></form:form>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- /page content -->

    <jsp:include page="../fragments/footer.jsp" />
</html>