<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="specialization" tagdir="/WEB-INF/tags/specialization" %>
<layout:default title="${specialization.title}">
    
    <a href="${pageContext.request.contextPath}/service/new/${specialization.id}">Добавить услугу</a>
    <table class="table">
        <c:forEach var="service" items="${services}">
            <tr>
                <td>${service.title}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/service/edit/${service.id}">Редактировать</a>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/service/remove/${service.id}" method="POST">
                        <input type="hidden" name="specializationId" value="${specialization.id}"/>
                        <button class="btn bg-primary btn-sm" type="submit">Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    

</layout:default>

