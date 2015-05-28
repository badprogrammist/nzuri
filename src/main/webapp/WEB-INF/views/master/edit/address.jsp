<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="master" tagdir="/WEB-INF/tags/master" %>
<layout:default title="Профиль">
    
    <master:tabMenu activeTab="address"/>

    <form:form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/master/edit/address/update" modelAttribute="address" >
        <div class="form-group">
            <label for="city" class="col-sm-3 control-label">Город</label>
            <div class="col-sm-6">
                <form:input id="city" value="${address.city}" path="city" type="text" class="form-control"  placeholder="Введите город"/>
            </div>
        </div>
        <div class="form-group">
            <label for="street" class="col-sm-3 control-label">Улица</label>
            <div class="col-sm-6">
                <form:input id="street" value="${address.street}" path="street" type="text" class="form-control"  placeholder="Введите улицу"/>
            </div>
        </div>
        <div class="form-group">
            <label for="house" class="col-sm-3 control-label">Дом</label>
            <div class="col-sm-6">
                <form:input id="house" value="${address.house}"  path="house" type="text" class="form-control"  placeholder="Введите номер дома"/>
            </div>
        </div>
        <div class="form-group">
            <label for="building" class="col-sm-3 control-label">Корпус</label>
            <div class="col-sm-6">
                <form:input id="building" value="${address.building}" path="building" type="text" class="form-control"  placeholder="Введите корпус"/>
            </div>
        </div>
        <div class="form-group">
            <label for="flat" class="col-sm-3 control-label">Офис/квартира</label>
            <div class="col-sm-6">
                <form:input id="flat" value="${address.flat}" path="flat" type="text" class="form-control"  placeholder="Введите номер офиса/квартиры"/>
            </div>
        </div>
        <div class="form-group last">
            <div class="col-sm-offset-3 col-sm-6">
                <button type="submit" class="btn btn-success btn-sm btn-block">Сохранить</button>
            </div>
        </div>
    </form:form>

</layout:default>
