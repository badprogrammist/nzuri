<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@attribute name="title" %>
<%@attribute name="left" fragment="true" %>
<layout:default title="${title}">
    <div class="row">
        <div class="col-sm-3">
            <jsp:invoke fragment="left"/>
        </div>
        <div class="col-sm-9">
            <jsp:doBody/>
        </div>
    </div>
</layout:default>