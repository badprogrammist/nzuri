<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="master" tagdir="/WEB-INF/tags/master" %>
<%@taglib prefix="specialization" tagdir="/WEB-INF/tags/specialization" %>
<layout:default title="Профиль">

    <master:tabMenu activeTab="actions"/>

    <form:form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/master/specialization/save" modelAttribute="specialization" >
        
        <specialization:form/>
        
        <div class="form-group last">
            <div class="col-sm-offset-3 col-sm-6">
                <button type="submit" class="btn btn-success btn-sm btn-block">Сохранить</button>
            </div>
        </div>
    </form:form>

</layout:default>
