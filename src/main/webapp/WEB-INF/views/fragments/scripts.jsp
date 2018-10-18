<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

    <!-- jQuery -->
    <script src="<c:url value='/resources/vendors/jquery/dist/jquery.min.js' />"></script>
    
    <!-- Bootstrap -->    
    <%-- <script src="<c:url value='/resources/vendors/bootstrap/dist/js/bootstrap.min.js' />"></script> --%>
    <script src="<c:url value='/resources/vendors/bootstrap-4.1.3/js/bootstrap.min.js' />"></script>

    <!-- FastClick -->    
    <script src="<c:url value='/resources/vendors/fastclick/lib/fastclick.js' />"></script>

    <!-- validator -->
    <script src="<c:url value='/resources/build/js/validator.min.js' />"></script>

    <!-- Datatables -->
    <script src="<c:url value='/resources/vendors/datatables.net/js/jquery.dataTables.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js' />"></script>
       
    <!-- iCheck2 -->
    <script src="<c:url value='/resources/vendors/iCheck/icheck.min.js'/>"></script>
     
     <!-- Custom Theme Scripts -->
    <script src="<c:url value='/resources/build/js/custom.min.js' />"></script>

    <!-- Datatables -->
    <script>
      $(document).ready(function() {
        $('#datatable').dataTable();
      });
    </script>
    <!-- /Datatables -->