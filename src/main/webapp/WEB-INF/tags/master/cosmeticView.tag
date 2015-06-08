<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="cosmeticItem" required="true" type="ru.nzuri.domain.master.Cosmetic"%>

<div class="form-horizontal">
    <c:if test="${cosmeticItem.title != null and !cosmeticItem.title.isEmpty()}">
        <div class="form-group">
            <label for="title" class="col-sm-3 control-label">Название</label>
            <div class="col-sm-6">
                <p id="title" class="form-control-static">${cosmeticItem.title}</p>
            </div>
        </div>   
    </c:if>

</div>

