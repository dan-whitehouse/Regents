<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Welcome Dear ${loggedinuser}</a>
        </div>
        <c:url value="/" var="urlHome"/>
        <c:url value="/list" var="urlAListUsers"/>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="${urlHome}">Home</a></li>
                <li><a href="${urlAListUsers}">List of users</a></li>
            </ul>
        </div><!--/.nav-collapse -->
        <div class="authbar floatRight">
            <span class=""><a href="<c:url value="/logout" />">Logout</a></span>
        </div>
    </div>
</nav>