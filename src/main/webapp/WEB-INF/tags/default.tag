<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="title" %>
<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/picedit/picedit.min.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/dropzone/dropzone.min.css"/>
    </head>
    <body>
        <jsp:directive.include  file="default-header.tag"/>
        <sec:authorize access="isAuthenticated()">
            <sec:authentication var="principal" property="principal" />
            ${principal.username}
            <t:image fileId="${principal.userData.icon.id}"/>
        </sec:authorize>
        <jsp:doBody/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.11.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/picedit/picedit.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/dropzone/dropzone.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
    </body>
</html>