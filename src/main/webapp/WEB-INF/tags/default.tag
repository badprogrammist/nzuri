<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@attribute name="title" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
    </head>
    <body>
        <jsp:directive.include  file="default-header.tag"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.11.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/aw.js"></script>
        <jsp:doBody/>
        
    </body>
</html>