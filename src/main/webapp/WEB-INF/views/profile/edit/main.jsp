<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:default title="Профиль">

    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/master/edit">Основное</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/master/edit/examples">Примеры</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/master/edit/address">Адрес</a></li>
    </ul>


</t:default>
