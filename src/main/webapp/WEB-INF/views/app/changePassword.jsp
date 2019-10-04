<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="../fragments/header.jsp"/>
<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="clearfix"></div>
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Change Password</h2>
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                   aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="#">Settings 1</a>
                                    </li>
                                    <li><a href="#">Settings 2</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="close-link"><i class="fa fa-close"></i></a>
                            </li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">


                        <form:form method="POST" modelAttribute="userPassword" class="form-horizontal form-label-left">
                            <span class="section">Change Password</span>
                            <form:input type="hidden" path="userId" id="userId"/>


                            <div class="item form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Current Password
                                    <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <form:input type="password" path="oldPassword" id="oldPassword"
                                                class="form-control col-md-7 col-xs-12 input-sm"
                                                data-validate-length-range="6" data-validate-words="1"
                                                name="oldPassword" placeholder="" required="required"/>
                                    <div class="has-error">
                                        <form:errors path="oldPassword" class="help-inline"/>
                                    </div>
                                </div>
                            </div>

                            <div class="item form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">New Password <span
                                        class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <form:input type="password" path="newPassword" id="newPassword"
                                                class="form-control col-md-7 col-xs-12 input-sm"
                                                data-validate-length-range="6" data-validate-words="1"
                                                name="newPassword" placeholder="" required="required"/>
                                    <div class="has-error">
                                        <form:errors path="newPassword" class="help-inline"/>
                                    </div>
                                </div>
                            </div>

                            <div class="item form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">New Password
                                    (Confirm)<span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <form:input type="password" path="newPasswordConfirm" id="newPasswordConfirm"
                                                class="form-control col-md-7 col-xs-12 input-sm"
                                                data-validate-length-range="6" data-validate-words="1"
                                                name="newPasswordConfirm" placeholder="" required="required"/>
                                    <div class="has-error">
                                        <form:errors path="newPasswordConfirm" class="help-inline"/>
                                    </div>
                                </div>
                            </div>

                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-md-offset-3">
                                    <input type="submit" value="Save" class="btn btn-success"/> <a
                                        href="<c:url value='/' />" class="btn btn-primary">Cancel</a>
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
<jsp:include page="../fragments/footer.jsp"/>
</html>