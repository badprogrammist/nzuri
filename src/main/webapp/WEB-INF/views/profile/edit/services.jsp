<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<layout:default title="Профиль">

    <profile:tabMenu activeTab="services"/>

    <form:form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/master/edit/updateServices" modelAttribute="servicesUpdateData" >

        <c:forEach items="${profileSpecializations}" var="profileSpecialization">
            <p>${profileSpecialization.specialization.title}</p>
            <c:forEach items="${profileSpecialization.profileServices}" var="profileService">
                <label>${profileService.service.title}</label>
                <form:input path="price" value="${profileService.price.value}" />
                <form:hidden path="serviceId" value="${profileService.service.id}"/>
            </c:forEach>
        </c:forEach>

        <div class="form-group last">
            <div class="col-sm-offset-3 col-sm-6">
                <button type="submit" class="btn btn-success btn-sm">Сохранить</button>
            </div>
        </div>
    </form:form>
    
    <form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/master/edit/attachServices" >

        <c:forEach items="${specializations}" var="specialization">
            <p>${specialization.title}</p>
            <c:forEach items="${specialization.services}" var="service">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="services"  value="${service.id}"/> ${service.title}
                    </label>
                </div>
            </c:forEach>
        </c:forEach>

        <div class="form-group last">
            <div class="col-sm-offset-3 col-sm-6">
                <button type="submit" class="btn btn-success btn-sm">Добавить</button>
            </div>
        </div>
    </form>

</layout:default>
