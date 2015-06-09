<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="master" tagdir="/WEB-INF/tags/master" %>
<layout:default title="Профиль">

    <master:tabMenu activeTab="main"/>

    <form:form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/master/edit/main/update" modelAttribute="master" >
        
        <div class="form-group">
            <label for="description" class="col-sm-3 control-label">О себе</label>
            <div class="col-sm-6">
                <form:textarea id="description" value="${master.masterData.description}" path="masterData.description" type="text" class="form-control"  placeholder="Напишите немного о себе"/>
            </div>
        </div>
        
        <div class="form-group last">
            <div class="col-sm-offset-3 col-sm-6">
                <button type="submit" class="btn btn-success btn-sm btn-block">Сохранить</button>
            </div>
        </div>
        <form:hidden path="id" value="${master.id}"/>
    </form:form>
    

</layout:default>
