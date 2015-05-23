<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<layout:two-column title="Профиль">
    <jsp:attribute name="left">
        <common:image styleClass="img-responsive img-border" fileId="${profile.user.userData.icon.id}"/>
        <h2>${profile.user.userData.fullName}</h2>
    </jsp:attribute>
    <jsp:body>
        <div class="block">
            <c:if test="${editable}">
                <a href="master/edit">Редактировать</a>
            </c:if>
            <h2>Примеры работ</h2>
            <c:forEach var="exampleItem" items="${profile.examples}">
                <div class="col-xs-6 col-md-3">
                    <a href="#" class="thumbnail">
                        <common:image fileId="${exampleItem.image.id}"/>
                        <div class="caption">
                            <p>${exampleItem.comment}</p>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>


    </jsp:body>
</layout:two-column>
