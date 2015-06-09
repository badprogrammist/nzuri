<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${masterAction.action.ownType == 'CUSTOM'}">
    <div class="form-group">
        <label for="title" class="col-sm-3 control-label">Название</label>
        <div class="col-sm-6">
            <form:input id="title" value="${masterAction.action.data.title}" path="action.data.title" type="text" class="form-control"  placeholder="Введите название"/>
        </div>
    </div>
</c:if>

<div class="form-group">
    <label for="price" class="col-sm-3 control-label">Цена</label>
    <div class="col-sm-6">
        <form:input id="price" value="${masterAction.price.value}" path="price.value" type="text" class="form-control"  placeholder="Введите цену"/>
    </div>
</div>
<form:hidden path="action.id" value="${masterAction.action.id}"/> 
<form:hidden path="id" value="${masterAction.id}"/>