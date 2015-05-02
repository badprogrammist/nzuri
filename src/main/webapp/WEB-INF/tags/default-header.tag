<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="${pageContext.request.contextPath}/masters">Мастера</a>
<sec:authorize access="isAuthenticated()">
    <a href="${pageContext.request.contextPath}/profile">Профиль</a>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <a href="${pageContext.request.contextPath}/login">Вход</a>
    <a href="${pageContext.request.contextPath}/registration">Регистрация</a>
</sec:authorize>
