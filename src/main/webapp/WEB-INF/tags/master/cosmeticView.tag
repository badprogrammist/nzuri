<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="educationItem" required="true" type="ru.nzuri.domain.master.Education"%>

<div class="form-horizontal">
    <c:if test="${educationItem.institution != null and !educationItem.institution.isEmpty()}">
        <div class="form-group">
            <label for="institution" class="col-sm-3 control-label">Образовательное учреждение</label>
            <div class="col-sm-6">
                <p id="institution" class="form-control-static">${educationItem.institution}</p>
            </div>
        </div>   
    </c:if>

    <c:if test="${educationItem.speciality != null and !educationItem.speciality.isEmpty()}">
        <div class="form-group">
            <label for="speciality" class="col-sm-3 control-label">Специальность</label>
            <div class="col-sm-6">
                <p id="speciality" class="form-control-static">${educationItem.speciality}</p>
            </div>
        </div>
    </c:if>

    <c:if test="${educationItem.startYear != null and !educationItem.startYear.isEmpty()}">
        <div class="form-group">
            <label for="start_date" class="col-sm-3 control-label">Год начала</label>
            <div class="col-sm-6">
                <p id="start_date" class="form-control-static">${educationItem.startYear}</p>
            </div>
        </div>
    </c:if>

    <c:if test="${educationItem.endYear != null and !educationItem.endYear.isEmpty()}">
        <div class="form-group">
            <label for="end_date" class="col-sm-3 control-label">Год окончания</label>
            <div class="col-sm-6">
                <p id="end_date" class="form-control-static">${educationItem.endYear}</p>
            </div>
        </div>
    </c:if>
</div>

