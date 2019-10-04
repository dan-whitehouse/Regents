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
                        <c:choose>
                            <c:when test="${edit}">
                                <h2>Edit User</h2>
                            </c:when>
                            <c:otherwise>
                                <h2>Create User</h2>
                            </c:otherwise>
                        </c:choose>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <form:form method="POST" modelAttribute="user" class="form-horizontal form-label-left"
                                   data-toggle="validator" role="form">
                            <form:input type="hidden" path="id" id="id"/>
                            <div class="item form-group has-feedback">
                                <label for="firstName" class="control-label col-md-3 col-sm-3 col-xs-3 left">First
                                    Name</label>
                                <div class="input-group col-md-6 col-sm-6 col-xs-6">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <form:input type="text" pattern="^[\w\-\s]+$" maxlength="50" path="firstName"
                                                id="firstName" class="form-control col-md-7 col-xs-12" name="firstName"
                                                placeholder="First Name" required="required"/>
                                </div>
                                <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <label for="lastName" class="control-label col-md-3 col-sm-3 col-xs-3 left">Last
                                    Name</label>
                                <div class="input-group col-md-6 col-sm-6 col-xs-6">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <form:input type="text" pattern="^[\w\-\s]+$" maxlength="50" path="lastName"
                                                id="lastName" class="form-control col-md-7 col-xs-12" name="lastName"
                                                placeholder="Last Name" required="required"/>
                                </div>
                                <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <label for="username"
                                       class="control-label col-md-3 col-sm-3 col-xs-3 left">Username</label>
                                <div class="input-group col-md-6 col-sm-6 col-xs-6">
                                    <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                    <c:choose>
                                        <c:when test="${edit}">
                                            <form:input type="email" path="username" id="username"
                                                        class="form-control col-md-7 col-xs-12" name="username"
                                                        placeholder="test@district.com"
                                                        data-error="That email address is invalid" maxlength="50"
                                                        required="required" readonly="true"/>
                                        </c:when>
                                        <c:otherwise>
                                            <form:input type="email" path="username" id="username"
                                                        class="form-control col-md-7 col-xs-12" name="username"
                                                        placeholder="test@district.com"
                                                        data-error="That email address is invalid" maxlength="50"
                                                        required="required"/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <label for="password"
                                       class="control-label col-md-3 col-sm-3 col-xs-3 left">Password</label>
                                <div class="input-group col-md-6 col-sm-6 col-xs-6">
                                    <span class="input-group-addon"><i class="fa fa-asterisk"></i></span>
                                    <form:input type="password" path="password" id="password" name="password"
                                                class="form-control col-md-7 col-xs-12" placeholder="Password"
                                                data-error="Bruh, that email address is invalid" maxlength="50"
                                                required="required"/>
                                </div>
                                <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <label for="userDistricts" class="control-label col-md-3 col-sm-3 col-xs-3 left">Districts</label>
                                <div class="input-group col-md-6 col-sm-6 col-xs-6">
                                    <span class="input-group-addon"><i class="fa fa-university"></i></span>
                                    <c:choose>
                                        <c:when test="${edit}">
                                            <form:select path="userDistricts" multiple="true"
                                                         class="select2_multiple form-control" required="required">
                                                <form:option value=""></form:option>
                                                <c:forEach items="${selectedDistricts}" var="selectedDistrict">
                                                    <c:choose>
                                                        <c:when test="${selectedDistrict.selected eq 'true'}">
                                                            <form:option selected="true"
                                                                         value="${selectedDistrict.district.id}"
                                                                         label="${selectedDistrict.district.name}"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form:option value="${selectedDistrict.district.id}"
                                                                         label="${selectedDistrict.district.name}"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </form:select>
                                        </c:when>
                                        <c:otherwise>
                                            <form:select path="userDistricts" multiple="true"
                                                         class="select2_multiple form-control" required="required">
                                                <form:option value=""></form:option>
                                                <c:forEach items="${districts}" var="district">
                                                    <form:option value="${district.id}">${district.name}</form:option>
                                                </c:forEach>
                                            </form:select>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <label for="userProfiles"
                                       class="control-label col-md-3 col-sm-3 col-xs-3 left">Role</label>
                                <div class="input-group col-md-6 col-sm-6 col-xs-6">
                                    <span class="input-group-addon"><i class="fa fa-users"></i></span>
                                    <form:select path="userProfiles" items="${roles}" itemValue="id" itemLabel="type"
                                                 multiple="false" class="form-control col-md-7 col-xs-12"
                                                 required="required"/>
                                </div>
                                <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                            </div>
                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-md-offset-3">
                                    <c:choose>
                                        <c:when test="${edit}">
                                            <input type="submit" value="Update" class="btn btn-success"/> <a
                                                href="<c:url value='/admin/users' />" class="btn btn-primary">Cancel</a>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="submit" value="Register" class="btn btn-success"/> <a
                                                href="<c:url value='/admin/users' />" class="btn btn-primary">Cancel</a>
                                        </c:otherwise>
                                    </c:choose>
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
<!-- Select2 -->
<script src="<c:url value='/resources/vendors/select2/dist/js/select2.full.min.js' />"></script>
<!-- Select2 -->
<script>
    $(document).ready(function () {
        $(".select2_single").select2({
            placeholder: "Select a state",
            allowClear: true
        });
        $(".select2_group").select2({});
        $(".select2_multiple").select2({
            maximumSelectionLength: 15,
            placeholder: "With Max Selection limit 15",
            allowClear: true
        });
    });
</script>
<!-- /Select2 -->
<script type="text/javascript">
    $(document).ready(function () {
        $('#user').bootstrapValidator({
            container: '#messages',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                firstName: {
                    validators: {
                        notEmpty: {
                            message: 'The full name is required and cannot be empty'
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: 'The email address is required and cannot be empty'
                        },
                        emailAddress: {
                            message: 'The email address is not valid'
                        }
                    }
                },
                title: {
                    validators: {
                        notEmpty: {
                            message: 'The title is required and cannot be empty'
                        },
                        stringLength: {
                            max: 100,
                            message: 'The title must be less than 100 characters long'
                        }
                    }
                },
                content: {
                    validators: {
                        notEmpty: {
                            message: 'The content is required and cannot be empty'
                        },
                        stringLength: {
                            max: 500,
                            message: 'The content must be less than 500 characters long'
                        }
                    }
                }
            }
        });
    });
</script>
</html>