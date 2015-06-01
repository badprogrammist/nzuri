<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="action" tagdir="/WEB-INF/tags/action" %>
<layout:default title="${action.data.title}">
    
    
    
    <form:form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/action/update" modelAttribute="action" >
        
        <action:form/>
        
        <div class="form-group last">
            <div class="col-sm-offset-3 col-sm-6">
                <button type="submit" class="btn btn-success btn-sm btn-block">Сохранить</button>
            </div>
        </div>
    </form:form>


</layout:default>

