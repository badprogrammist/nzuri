<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="form-group">
    <label for="institution" class="col-sm-3 control-label">Образовательное учреждение</label>
    <div class="col-sm-6">
        <form:input id="institution" value="${education.institution}" path="institution" type="text" class="form-control"  placeholder="Введите образовательное учреждение"/>
    </div>
</div>
<div class="form-group">
    <label for="speciality" class="col-sm-3 control-label">Специальность</label>
    <div class="col-sm-6">
        <form:input id="speciality" value="${education.speciality}" path="speciality" type="text" class="form-control"  placeholder="Введите специальность"/>
    </div>
</div>
<div class="form-group">
    <label for="start_date" class="col-sm-3 control-label">Год начала</label>
    <div class="col-sm-6">
        <form:select id="start_date" path="startYear" class="form-control" >
            <c:forEach begin="1970" end="2015" var="year">
                <form:option value="${year}">${year}</form:option>
            </c:forEach>
        </form:select>
    </div>
</div>
<div class="form-group">
    <label for="end_date" class="col-sm-3 control-label">Год окончания</label>
    <div class="col-sm-6">
        <form:select id="end_date" path="endYear" class="form-control" >
            <c:forEach begin="1970" end="2015" var="year">
                <form:option value="${year}">${year}</form:option>
            </c:forEach>
        </form:select>
    </div>
</div>
<form:hidden path="id" value="${education.id}"/> 