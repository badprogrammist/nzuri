<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="master" tagdir="/WEB-INF/tags/master" %>
<%@taglib prefix="request" tagdir="/WEB-INF/tags/request" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<layout:default title="Профиль">

    <c:set var="headerImagePath" value="${!master.examples.isEmpty() ? pageContext.request.contextPath.concat('/file/'.concat(master.examples.get(0).image.id)) : pageContext.request.contextPath.concat('/resources/images/master-view-default-header.jpg')}"/>
    <header class="master-view-header" style="background-image:url(${headerImagePath})"></header>

    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="row-space-bottom-4 row-space-top-4">
                    <div class="col-md-3 text-center">
                        <common:image styleClass="img-circle" style="width: 90px; height: 90px;"  fileId="${master.user.userData.icon.id}"/>
                    </div>
                    <div class="col-md-9">
                        <h1 class="h3 row-space-top-0">${master.user.userData.fullName}</h1>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">

            </div>
        </div>




        <div class="row">
            <h2>Примеры работ</h2>
            <c:forEach var="example" items="${examples}">
                <div class="col-xs-6 col-md-3">
                    <a href="#" class="thumbnail">
                        <common:image fileId="${example.image.id}"/>
                        <div class="caption">
                            <p>${example.comment}</p>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div> 
    </div>



    <c:if test="${editable}">
        <a href="master/edit">Редактировать</a>
    </c:if>





    <div class="row">
        <h2>Образование</h2>
        <c:forEach var="education" items="${master.educations}">
            <div class="row">
                <master:educationView educationItem="${education}"/>
            </div>
        </c:forEach>
    </div>

    <div class="row">
        <h2>Косметика</h2>
        <c:forEach var="cosmetic" items="${master.cosmetics}">
            <div class="row">
                <master:cosmeticView cosmeticItem="${cosmetic}"/>
            </div>
        </c:forEach>
    </div>


    <div class="row">
        <h2>Услуги</h2>
        <sec:authorize access="hasRole('ROLE_USER')">
            <request:form masterItem="${master}"/>
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
    </div>


    <h2>Комментарии</h2>

    <div id="master_comments">
        <jsp:include page="comment/_comments.jsp"/>
    </div>
    <master:addCommentForm updateContainerSelector="#master_comments"/>

</layout:default>
