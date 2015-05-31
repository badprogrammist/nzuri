<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="master" tagdir="/WEB-INF/tags/master" %>
<layout:two-column title="Профиль">
    <jsp:attribute name="left">
        <common:image styleClass="img-responsive img-border" fileId="${master.user.userData.icon.id}"/>
        <h2>${master.user.userData.fullName}</h2>
    </jsp:attribute>
    <jsp:body>
        <div class="block">
            <c:if test="${editable}">
                <a href="master/edit">Редактировать</a>
            </c:if>
            <h2>Примеры работ</h2>
            <c:forEach var="example" items="${master.examples}">
                <div class="col-xs-6 col-md-3">
                    <a href="#" class="thumbnail">
                        <common:image fileId="${example.image.id}"/>
                        <div class="caption">
                            <p>${example.comment}</p>
                        </div>
                    </a>
                </div>
            </c:forEach>
            <h2>Комментарии</h2>
            <div id="master_comments">
                <jsp:include page="comment/_comments.jsp"/>
            </div>
                <master:addCommentForm updateContainerSelector="#master_comments"/>
        </div>


    </jsp:body>
</layout:two-column>
