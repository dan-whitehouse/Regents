<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:url value="/" var="home" />
<c:url value="/order" var="order" />
<c:url value="/orders" var="orders" />
<c:url value="/notadministration" var="optout" />
<c:url value="/notadministrations" var="optouts" />
<c:url value="/profile-${loggedinuser}" var="profile" />
<c:url value="/admin/users" var="users" />
<c:url value="/admin/users/create" var="createUser" />
<c:url value="/admin/orders" var="adminOrders" />
<c:url value="/admin/notadministrations" var="adminOptouts" />
<c:url value="/admin/orderForms" var="orderForms" />
<c:url value="/admin/orderForms/create" var="createOrderForm" />
<c:url value="/admin/documents" var="documents" />
<c:url value="/admin/exams" var="exams" />
<c:url value="/admin/printOptions" var="printOptions" />
<c:url value="/admin/scanOptions" var="scanOptions" />
<c:url value="/admin/districts" var="districts" />
<c:url value="/admin/schools" var="schools" />
<c:url value="/admin/settings" var="settings" />
<c:url value="/admin/dashboard" var="dashboard" />
<c:url value="/dev/csv" var="upload" />
	</head>
	<body class="nav-md footer_fixed">
		<div class="container body">
		    <div class="main_container">
		        <div class="col-md-3 left_col">
			        <div class="left_col scroll-view">
                        <div class="navbar nav_title" style="border: 0;">
                            <a href="${home}" class="site_title"><i><img src="<c:url value='/resources/images/NericLogo_2c_icon.png' />"/></i><span>Regents Order Form</span></a>
                        </div>
                        <div class="clearfix"></div>
                        <!-- sidebar menu -->
                        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                            <div class="menu_section">
                                <ul class="nav side-menu">
                                    <sec:authorize access="hasAnyRole('USER', 'ADMIN')">
                                        <li><a href="${home}"><i class="fa fa-home"></i> Home</span></a></li>
                                        <li><a href="${order}"><i class="fa fa-edit"></i> Order Form</span></a></li>
                                        <li><a href="${optout}"><i class="fa fa-sign-out"></i> Not Administering</span></a></li>
                                        <li><a href="${orders}"><i class="fa fa-truck"></i>My Orders</a></li>
                                        <li><a href="${optouts}"><i class="fa fa-exclamation-triangle"></i> Past Non-Administrations</span></a></li>
                                    </sec:authorize>

                                    <sec:authorize access="hasRole('ADMIN')">
                                        <li>
                                            <a><i class="fa fa-gears"></i> Admin <span class="fa fa-chevron-down"></span></a>
                                            <ul class="nav child_menu">
                                                <li><a href="${dashboard}"><i class="fa fa-dashboard"></i> Dashboard</span></a></li>
                                                <li><a><i class="fa fa-sitemap"></i> Users <span class="fa fa-chevron-down"></span></a>
                                                    <ul class="nav child_menu">
                                                        <li class="sub_menu"><a href="${users}">Users List</a></li>
                                                        <li><a href="${createUser}">Create User</a></li>
                                                    </ul>
                                                </li>
                                                <li><a><i class="fa fa-check-square"></i> Order Forms <span class="fa fa-chevron-down"></span></a>
                                                    <ul class="nav child_menu">
                                                        <li class="sub_menu"><a href="${orderForms}">Order Form List</a></li>
                                                        <li class="sub_menu"><a href="${createOrderForm}">Create Form</a></li>
                                                    </ul>
                                                </li>
                                                <li><a href="${adminOrders}"><i class="fa fa-truck"></i>Orders</a></li>
                                                <li><a href="${adminOptouts}"><i class="fa fa-exclamation-triangle"></i> Not Administering</span></a></li>
                                                <li><a href="${documents}"><i class="fa fa-file-text"></i> Documents </a></li>
                                                <li><a href="${exams}"><i class="fa fa-list"></i> Exams </a></li>
                                                <li><a href="${printOptions}"><i class="fa fa-print"></i> Print Options </a></li>
                                                <li><a href="${scanOptions}"><i class="fa fa-clipboard"></i> Scan Options </a></li>
                                                <li><a href="${districts}"><i class="fa fa-university"></i> Districts </a></li>
                                                <li><a href="${schools}"><i class="fa fa-graduation-cap"></i> Schools </a></li>
                                            </ul>
                                        </li>
                                    </sec:authorize>

                                    <sec:authorize access="hasRole('DEV')">
                                        <li>
                                            <a><i class="fa fa-code"></i> Dev <span class="fa fa-chevron-down"></span></a>
                                            <ul class="nav child_menu">
                                                <li><a href="${upload}"><i class="fa fa-upload"></i> Upload</span></a></li>
                                            </ul>
                                        </li>
                                    </sec:authorize>
                                </ul>
                            </div>
                        </div>
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
                                        ${loggedinusername}
                                        <span class=" fa fa-angle-down"></span>
                                    </a>
                                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                                        <sec:authorize access="hasAnyRole('USER', 'ADMIN')">
                                            <li><a href="<c:url value="/profile" />"><i class="fa fa-user pull-right"></i> Profile</a></li>
                                            <li><a href="<c:url value="/logout" />"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                                            <li><a href="<c:url value="/changePassword" />"><i class="fa fa-asterisk pull-right"></i> Change Password</a></li>
                                        </sec:authorize>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
		<!-- /top navigation -->