<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Nzuri</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/masters">Мастера</a></li>
                <sec:authorize  access="hasRole('ROLE_ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/specializations">Специализации</a></li>
                </sec:authorize>
                
            </ul>
            <sec:authorize access="isAuthenticated()">
                
                <sec:authentication var="principal" property="principal" />
                <p class="navbar-text navbar-right">
                    <common:image style="height:30px;  vertical-align:middle;" styleClass="img-circle" fileId="${principal.userData.icon.id}"/>
                    ${principal.username}
                </p>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="${pageContext.request.contextPath}/login">Вход</a></li>
                    <li><a href="${pageContext.request.contextPath}/registration">Регистрация</a></li>
                </ul>
            </sec:authorize>
        </div><!--/.nav-collapse -->
    </div>
</nav>

