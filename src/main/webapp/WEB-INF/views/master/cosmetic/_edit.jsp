<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="master" tagdir="/WEB-INF/tags/master" %>


<form:form
    class="form-horizontal"
    role="form"
    method="post"
    ic-post-to="${pageContext.request.contextPath}/master/edit/cosmetic/update"
    ic-target="#master_cosmetics"
    ic-transition="none"
    modelAttribute="cosmetic" >
    <master:cosmeticForm cosmeticItem="${cosmetic}" />
    <div class="form-group last">
        <div class="col-sm-offset-3 col-sm-3">
            <button type="submit" class="btn btn-success btn-sm btn-block">Сохранить</button>
        </div>
    </div>
    <div class="form-group last">
        <div class="col-sm-offset-3 col-sm-3">
            <button
                ic-get-from="${pageContext.request.contextPath}/master/cosmetic/list"
                ic-target="#master_cosmetics"
                ic-transition="none"
                class="btn btn-success btn-sm btn-block">
                Отмена
            </button>
        </div>
    </div>
</form:form>

