<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon" />
		<title>NERIC - Regents Order Form</title>
	
		<!-- Bootstrap -->
		<link href="<c:url value='/resources/vendors/bootstrap/dist/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">

		
		<!-- Font Awesome -->
		<link href="<c:url value='/resources/vendors/font-awesome/css/font-awesome.min.css' />" rel="stylesheet" type="text/css">
		
		<!-- Select2 -->
		<link href="<c:url value='/resources/vendors/select2/dist/css/select2.min.css' />" rel="stylesheet" type="text/css">
		
		<!-- iCheck2 -->
		<link href="<c:url value='/resources/vendors/iCheck/skins/flat/green.css' />" rel="stylesheet">

		<!-- Datatables -->
		<link href="<c:url value='/resources/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/resources/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/resources/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/resources/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/resources/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css' />" rel="stylesheet" type="text/css">
		
		<!-- Custom Theme Style -->
		<link href="<c:url value='/resources/build/css/custom.min.css' />" rel="stylesheet" type="text/css">	