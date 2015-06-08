<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="masterItem" required="true" type="ru.nzuri.domain.master.Master"%>
<c:if test="${!masterItem.specializations.isEmpty()}">
    <form class="form-horizontal" role="form" method="post"
          action="${pageContext.request.contextPath}/request/new">
        <c:forEach var="masterSpecialization" items="${masterItem.specializations}" varStatus="specializationVarStatus">
            <p class="help-block">${masterSpecialization.specialization.data.title}</p>
            <c:forEach var="masterAction" items="${masterSpecialization.masterActions}" varStatus="actionVarStatus">
                <div class="form-group">
                    <label for="masterAction${specializationVarStatus.index}${actionVarStatus.index}" class="col-sm-3 control-label">${masterAction.action.data.title} - ${masterAction.price.value}</label>
                    <div class="col-sm-9">
                        <input id="masterAction${specializationVarStatus.index}${actionVarStatus.index}" type="checkbox" name="masterActions" value="${masterAction.id}"/> 
                    </div>
                </div>
            </c:forEach>
        </c:forEach>
        <div class="form-group">
            <label for="exercise_date" class="col-sm-3 control-label">На дату</label>
            <div class="col-sm-9 date input-group datetimepicker">
                <input id="exercise_date" type="text" name="exerciseDate" class="form-control" placeholder="На дату" required="true" />
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
        <input type="hidden" name="masterId" value="${masterItem.id}"/>
        <div class="form-group last">
            <div class="col-sm-6">
                <button type="submit" class="btn btn-success btn-sm">Создать заявку</button>
            </div>
        </div>
    </form>
</c:if>
<c:if test="${masterItem.specializations.isEmpty()}">
    <p>Нет услуг</p>
</c:if>     