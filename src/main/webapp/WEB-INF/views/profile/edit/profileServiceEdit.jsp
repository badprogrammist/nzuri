<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<layout:default title="Профиль">

    <profile:tabMenu activeTab="services"/>

    <div class="row">
        <div class="col-sm-6">
            <form:form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/master/edit/updateService" modelAttribute="profileService" >
                <div class="form-group">
                    <label for="price" class="col-sm-3 control-label">Цена</label>
                    <div class="col-sm-6">
                        <form:input id="flat" value="${profileService.price.value}" path="price.value" type="text" class="form-control"  placeholder="Введите цену"/>
                    </div>
                </div>
                <form:hidden path="id" value="${profileService.id}"/>
                <div class="form-group last">
                    <div class="col-sm-offset-3 col-sm-6">
                        <button type="submit" class="btn btn-success btn-sm btn-block">Сохранить</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</layout:default>

