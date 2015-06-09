<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<layout:default title="Заявки">
    
    <table class="table">
        <thead>
            <tr>
                <th>Пользователь</th>
                <th>Дата создания</th>
                <th>Дата исполнения</th>
                <th>Услуги</th>
            </tr>
        </thead>
        <c:forEach var="request" items="${requests}">
            <tr>
                <td>
                    <common:image style="height:30px;  vertical-align:middle;" styleClass="img-circle" fileId="${request.owner.userData.icon.id}"/>
                    ${request.owner.userData.fullName}
                </td>
                <td>
                    <fmt:formatDate value="${request.dateCreation}" pattern="dd.MM.yyyy HH:mm" />
                </td>
                <td>
                    <fmt:formatDate value="${request.exerciseDate}" pattern="dd.MM.yyyy HH:mm" />
                </td>
                <td>
                    <ul>
                        <c:forEach var="requestAction" items="${request.actions}">
                            <li>${requestAction.action.data.title} - ${requestAction.price.value}</li>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
        </c:forEach>
    </table>


</layout:default>

