<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="master" tagdir="/WEB-INF/tags/master" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col-xs-12">
    <c:forEach var="comment" items="${comments}">
        <div class="media">
            <div class="media-left">
                <common:image style="width: 64px; height: 64px;" styleClass="media-object" fileId="${comment.user.userData.icon.id}"/>
            </div>
            <div class="media-body">
                <h4 class="media-heading">${comment.user.userData.fullName}</h4>
                <fmt:formatDate
                    pattern="dd.MM.yyyy HH:mm"
                    var="formattedDateCreated"
                    value="${comment.dateCreated}"/>
                <small>${formattedDateCreated}</small>
                <p>${comment.content}</p>
            </div>
        </div>
    </c:forEach>  
</div>
