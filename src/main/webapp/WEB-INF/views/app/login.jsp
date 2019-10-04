<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../fragments/header.jsp"/>
<c:url var="loginUrl" value="/login"/>

<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="clearfix"></div>
        <div class="login_wrapper">
            <div class="login-card">
                <div class="login-form">
                    <form action="${loginUrl}" method="post" class="form-horizontal">

                        <c:if test="${param.error != null}">
                            <div class="alert alert-danger">
                                <p>${error}</p>
                            </div>
                        </c:if>
                        <c:if test="${param.logout != null}">
                            <div class="alert alert-success">
                                <p>${msg}</p>
                            </div>
                        </c:if>
                        <br/>
                        <br/>
                        <br/>
                        <!--  <img src="resources/images/NericLogo_horizontal_2c_sanstxt.png" style="width:90%;"> -->
                        <div class="logo">
                            <img src="resources/images/logo.png" style="width:90%;">
                            <span class="logoText">Regents Order Form</span>
                        </div>
                        <br/>
                        <div class="input-group input-sm">
                            <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                            <input type="text" class="form-control" id="username" name="username"
                                   placeholder="Enter Username" required>
                        </div>
                        <div class="input-group input-sm">
                            <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Enter Password" required>
                        </div>
                        <div class="input-group input-sm">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" id="rememberme" name="remember-me"> Remember Me
                                </label>
                            </div>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="form-actions">
                            <input type="submit" class="btn btn-block btn-primary btn-default" value="Log in">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /page content -->

<jsp:include page="../fragments/footer.jsp"/>