<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="master" tagdir="/WEB-INF/tags/master" %>
<layout:default title="Профиль">

    <master:tabMenu activeTab="actions"/>

    <div class="row">
        <div class="col-sm-6">
            <h3>Услуги</h3>
            <c:forEach items="${masterSpecializations}" var="masterSpecialization">
                <p>${masterSpecialization.specialization.title}</p>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Услуга</th>
                            <th>Цена</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${masterSpecialization.masterActions}" var="masterAction" varStatus="status">
                            <tr>
                                <td><a href="#">${masterAction.action.title}</a></td>
                                <td>${masterAction.price.value}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/master/action/edit/${masterAction.id}">Редактировать</a>
                                </td>
                                <td>
                                    <a href="#">Удалить</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:forEach>
        </div>
        <div class="col-sm-6">
            <h3>Другие услуги</h3>
            <form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/master/edit/action/attach" >
                <c:forEach items="${specializations}" var="specialization">
                    <p>${specialization.title}</p>
                    <c:forEach items="${specialization.actions}" var="action">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="actions"  value="${action.id}"/> ${action.title}
                            </label>
                        </div>
                    </c:forEach>
                </c:forEach>

                <div class="form-group last">
                    <div class="col-sm-6">
                        <button type="submit" class="btn btn-success btn-sm">Добавить</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</layout:default>
