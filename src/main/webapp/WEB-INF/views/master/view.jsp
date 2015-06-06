<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="master" tagdir="/WEB-INF/tags/master" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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


            <h2>Образование</h2>
            <c:forEach var="education" items="${master.educations}">
                <div class="col-xs-6 col-md-3">
                    <p>${education.institution}</p>

                    <p>${education.speciality}</p>

                    <p>${education.startYear}</p>

                    <p>${education.endYear}</p>
                </div>
            </c:forEach>


            <h2>Услуги</h2>
            <sec:authorize access="hasRole('ROLE_USER')">
                <c:if test="${!master.specializations.isEmpty()}">
                    <form class="form-horizontal" role="form" method="post"
                          action="${pageContext.request.contextPath}/request/new">
                        <c:forEach var="masterSpecialization" items="${master.specializations}">
                            <p>${masterSpecialization.specialization.data.title}</p>
                            <c:forEach var="masterAction" items="${masterSpecialization.masterActions}">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="masterActions"
                                               value="${masterAction.id}"/> ${masterAction.action.data.title} ${masterAction.price.value}
                                    </label>
                                </div>
                            </c:forEach>
                        </c:forEach>
                        <input type="hidden" name="masterId" value="${master.id}"/>
                        <div class="form-group last">
                            <div class="col-sm-6">
                                <button type="submit" class="btn btn-success btn-sm">Создать заявку</button>
                            </div>
                        </div>
                    </form>
                </c:if>
                <c:if test="${specializations.isEmpty()}">
                    <p>Нет услуг</p>
                </c:if>
            </sec:authorize>
            <sec:authorize access="!hasRole('ROLE_USER')">
                <c:if test="${!master.specializations.isEmpty()}">
                    <c:forEach var="masterSpecialization" items="${master.specializations}">
                        <p>${masterSpecialization.specialization.data.title}</p>
                        <c:forEach var="masterAction" items="${masterSpecialization.masterActions}">
                            ${masterAction.action.data.title} ${masterAction.price.value}
                        </c:forEach>
                    </c:forEach>
                </c:if>
                <c:if test="${specializations.isEmpty()}">
                    <p>Нет услуг</p>
                </c:if>
            </sec:authorize>

            <h2>Комментарии</h2>

            <div id="master_comments">
                <jsp:include page="comment/_comments.jsp"/>
            </div>
            <master:addCommentForm updateContainerSelector="#master_comments"/>
        </div>


    </jsp:body>
</layout:two-column>
