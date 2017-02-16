<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
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
                  <div class="x_content">          
	                  <div class="alert alert-success alert-dismissible fade in" role="alert">
	                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
	                    </button>
	                   ${success}
	                  </div>
						Go to <a href="<c:url value='/printOptions' />">Print Options</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- /page content -->
<jsp:include page="fragments/footer.jsp" />
</html>