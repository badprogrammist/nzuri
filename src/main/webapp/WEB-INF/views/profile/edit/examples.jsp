<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/commons" %>
<layout:default title="Профиль">

    <ul class="nav nav-tabs">
        <li role="presentation"><a href="${pageContext.request.contextPath}/master/edit">Основное</a></li>
        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/master/edit/examples">Примеры</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/master/edit/address">Адрес</a></li>
    </ul>
    <div id="examples" class="row">
        <c:forEach var="exampleItem" items="${profile.examples}">
            <div class="col-xs-6 col-md-3">
                <a href="#" class="thumbnail">
                    <common:image fileId="${exampleItem.image.id}"/>
                </a>
            </div>
        </c:forEach>
    </div>

    <div class="row">
        <form action="${pageContext.request.contextPath}/profile/example/upload" class="dropzone" enctype="multipart/form-data">
            <div class="fallback col-xs-12">
                <input name="file" type="file" multiple/>
            </div>
        </form>
    </div>

</layout:default>
