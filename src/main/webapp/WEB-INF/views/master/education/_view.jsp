<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@taglib prefix="master" tagdir="/WEB-INF/tags/master" %>

<master:educationView educationItem="${education}"/>
<c:if test="${editable}">
    <form
        method="post"
        ic-post-to="${pageContext.request.contextPath}/master/edit/education/remove/${education.id}"
        ic-target="#master_educations"
        ic-transition="none">
        <button class="btn bg-primary btn-sm" type="submit">Удалить</button>
    </form>
    <button
        ic-target="#education_item_${status.index}"
        ic-transition="none"
        ic-get-from="${pageContext.request.contextPath}/master/education/edit/${education.id}"
        class="btn btn-default">
        Редактировать
    </button>
</c:if>


