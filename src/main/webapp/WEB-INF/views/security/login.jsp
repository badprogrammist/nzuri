<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:default title="Login">
    <form:form method="post" action="signIn" modelAttribute="credentials" >
        <form:label path="login">Логин</form:label>
        <form:input  path="login"/>
        
        <form:label path="password">Пароль</form:label>
        <form:password  path="password"/>
        <button type="submit">Save</button>
    </form:form>
</t:default> 