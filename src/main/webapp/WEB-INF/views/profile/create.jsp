<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:default title="New example">
    <form:form method="post" action="save" modelAttribute="example">
        <form:input  path="comment"/>
        <button type="submit">Save</button>
    </form:form>
</t:default> 