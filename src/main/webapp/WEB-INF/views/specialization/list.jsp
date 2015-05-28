<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<layout:default title="Специализации">
    
    <a href="${pageContext.request.contextPath}/specialization/new">Создать специализацию</a>
    <table class="table">
        <c:forEach var="specialization" items="${specializations}">
            <tr>
                <td>${specialization.title}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/specialization/edit/${specialization.id}">Редактировать</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/specialization/actions/${specialization.id}">Услуги</a>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/specialization/remove/${specialization.id}" method="POST">
                        <button class="btn bg-primary btn-sm" type="submit">Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>


</layout:default>

