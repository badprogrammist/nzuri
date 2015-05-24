<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="service" tagdir="/WEB-INF/tags/service" %>
<layout:default title="Создание услуги">
    <h3>${specialization.title}</h3>
    <form:form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/service/save/${specialization.id}" modelAttribute="service" >
        
        <service:form/>
        
        <div class="form-group last">
            <div class="col-sm-offset-3 col-sm-6">
                <button type="submit" class="btn btn-success btn-sm btn-block">Сохранить</button>
            </div>
        </div>
    </form:form>


</layout:default>

