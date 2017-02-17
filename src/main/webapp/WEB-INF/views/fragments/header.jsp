<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:url value="/" var="home" />
<c:url value="/order" var="order" />
<c:url value="/orders" var="orders" />
<c:url value="/profile-${loggedinuser}" var="profile" />
<c:url value="/admin/users" var="users" />
<c:url value="/admin/users/create" var="createUser" />
<c:url value="/admin/orderForms" var="orderForms" />
<c:url value="/admin/orderForms/create" var="createOrderForm" />
<c:url value="/admin/documents" var="documents" />
<c:url value="/admin/exams" var="exams" />
<c:url value="/admin/printOptions" var="printOptions" />
<c:url value="/admin/scanOptions" var="scanOptions" />
<c:url value="/admin/settings" var="settings" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- Meta, title, CSS, favicons, etc. -->
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>NERIC - Regents Order Form</title>
		
		<!-- NProgress -->
		<link href="<c:url value='resources/vendors/nprogress/nprogress.css' />" rel="stylesheet">
		<link href="<c:url value='resources/vendors/iCheck/skins/flat/green.css' />" rel="stylesheet">
		
		<!-- Bootstrap -->
		<link href="<c:url value='/resources/vendors/bootstrap/dist/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
		<!-- Font Awesome -->
		<link href="<c:url value='/resources/vendors/font-awesome/css/font-awesome.min.css' />" rel="stylesheet" type="text/css">
		
		<!-- Select2 -->
		<link href="<c:url value='/resources/vendors/select2/dist/css/select2.min.css' />" rel="stylesheet" type="text/css">
		
		<!-- Datatables -->
		<link href="<c:url value='resources/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='resources/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='resources/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='resources/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='resources/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css' />" rel="stylesheet" type="text/css">
		
		<!-- Custom Theme Style -->
		<link href="<c:url value='/resources/build/css/custom.min.css' />" rel="stylesheet" type="text/css">
		
		
	</head>
	<body class="nav-md footer_fixed">
		<div class="container body">
		<div class="main_container">
		<div class="col-md-3 left_col">
			<div class="left_col scroll-view">
				<div class="navbar nav_title" style="border: 0;">
					<a href="${home}" class="site_title"><i><img src="resources/images/NericLogo_2c_icon.png"/></i><span>Regents Order Form</span></a>
				</div>
				<div class="clearfix"></div>
				<!-- menu profile quick info -->
				<!--             <div class="profile"> -->
				<!--               <div class="profile_pic"> -->
				<!--                 <img src="images/img.jpg" alt="..." class="img-circle profile_img"> -->
				<!--               </div> -->
				<!--               <div class="profile_info"> -->
				<%--                 <span>Welcome, ${loggedinuser}</span> --%>
				<!--               </div> -->
				<!--             </div> -->
				<!-- /menu profile quick info -->
				<br />
				<!-- sidebar menu -->
				<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
					<div class="menu_section">
							<sec:authorize access="hasRole('ADMIN')">
								<h3>General</h3>
								<ul class="nav side-menu">
									<li><a href="${home}"><i class="fa fa-home"></i> Home</span></a></li>
									<li><a href="${order}"><i class="fa fa-edit"></i> Order Form</span></a></li>
									<li><a href="${orders}"><i class="fa fa-truck"></i>Orders</a></li>
									<li>
										<a><i class="fa fa-gears"></i> Admin <span class="fa fa-chevron-down"></span></a>
										<ul class="nav child_menu">
	<%-- 										<li><a href="${users}">Users</a></li> --%>
											<li><a><i class="fa fa-sitemap"></i> Users <span class="fa fa-chevron-down"></span></a>
					                          	<ul class="nav child_menu">
					                            	<li class="sub_menu"><a href="${users}">Users List</a></li>
					                            	<li><a href="${createUser}">Create User</a></li>
					                          	</ul>
					                        </li>
					                        <li><a><i class="fa fa-check-square"></i> Order Forms <span class="fa fa-chevron-down"></span></a>
					                        	<ul class="nav child_menu">
				                        			<li class="sub_menu"><a href="${orderForms}">Form List</a></li>
					                        		<li class="sub_menu"><a href="${createOrderForm}">Create Form</a></li>
				                        		</ul>
			                        		</li>
			                        		<li><a href="${documents}"><i class="fa fa-file-text"></i> Documents </a></li>
			                        		<li><a href="${exams}"><i class="fa fa-list"></i> Exams </a></li>
			                        		<li><a href="${printOptions}"><i class="fa fa-print"></i> Print Options </a></li>
			                        		<li><a href="${scanOptions}"><i class="fa fa-clipboard"></i> Scan Options </a></li>
										</ul>
									</li>
								</ul>
							</sec:authorize>
					</div>
				</div>
				<!-- /sidebar menu -->
				<!-- /menu footer buttons -->
<!-- 				<div class="sidebar-footer hidden-small"> -->
<!-- 					<a data-toggle="tooltip" data-placement="top" title="Settings"> -->
<!-- 					<span class="glyphicon glyphicon-cog" aria-hidden="true"></span> -->
<!-- 					</a> -->
<!-- 					<a data-toggle="tooltip" data-placement="top" title="FullScreen"> -->
<!-- 					<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span> -->
<!-- 					</a> -->
<!-- 					<a data-toggle="tooltip" data-placement="top" title="Lock"> -->
<!-- 					<span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span> -->
<!-- 					</a> -->
<!-- 					<a data-toggle="tooltip" data-placement="top" title="Logout"> -->
<!-- 					<span class="glyphicon glyphicon-off" aria-hidden="true"></span> -->
<!-- 					</a> -->
<!-- 				</div> -->
				<!-- /menu footer buttons -->
			</div>
		</div>
		<!-- top navigation -->
		<div class="top_nav">
			<div class="nav_menu">
				<nav>
					<div class="nav toggle">
						<a id="menu_toggle"><i class="fa fa-bars"></i></a>
					</div>
					<ul class="nav navbar-nav navbar-right">
						<li class="">
							<a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
							${loggedinuser}
							<span class=" fa fa-angle-down"></span>
							</a>
							<ul class="dropdown-menu dropdown-usermenu pull-right">
<%-- 								<li><a href="${profile}"> Profile</a></li> --%>
<!-- 								<li> -->
<!-- 									<a href="javascript:;"> -->
<!-- 									<span class="badge bg-red pull-right">50%</span> -->
<!-- 									<span>Settings</span> -->
<!-- 									</a> -->
<!-- 								</li> -->
<!-- 								<li><a href="javascript:;">Help</a></li> -->
								<li>
									<a href="
									<c:url value="/logout" />
									"><i class="fa fa-sign-out pull-right"></i> Log Out</a>
								</li>
							</ul>
						</li>
					</ul>
				</nav>
			</div>
		</div>
		<!-- /top navigation -->