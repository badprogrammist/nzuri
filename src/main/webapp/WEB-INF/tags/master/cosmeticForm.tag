<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute  name="cosmeticItem" required="true" type="ru.nzuri.domain.master.Cosmetic" %>

<div class="form-group">
    <label for="title" class="col-sm-3 control-label">Название</label>
    <div class="col-sm-6">
        <form:input id="title" value="${cosmeticItem.title}" path="title" type="text" class="form-control"  placeholder="Введите название косметики"/>
    </div>
</div>
<form:hidden path="id" value="${cosmeticItem.id}"/> 


