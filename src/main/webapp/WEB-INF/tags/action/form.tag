<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="form-group">
    <label for="title" class="col-sm-3 control-label">Название</label>
    <div class="col-sm-6">
        <form:input id="title" value="${action.data.title}" path="data.title" type="text" class="form-control"  placeholder="Введите название"/>
    </div>
</div>
<form:hidden path="id" value="${action.id}"/>        
<input type="hidden" name="specializationId" value="${action.specialization.id}"/>        