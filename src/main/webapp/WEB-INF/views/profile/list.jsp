<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:default title="Профиль">

    <c:forEach var="profileItem" items="${profiles}">
        <a href="master/${profileItem.id}">
            <t:image fileId="${profileItem.user.userData.icon.id}"/>
            <h2>${profileItem.user.userData.fullName}</h2>
        </a>
    </c:forEach>


</t:default>
