<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="hasRole('ROLE_USER')">
    <div class="col-xs-6 col-md-3">
        <form:form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/master/comment/add/${master.id}" modelAttribute="comment" >
            <div class="form-group">
                <label for="content" class="control-label">Комментарий</label>
                <form:textarea id="content" value="${comment.content}" path="content" type="text" class="form-control"  placeholder="Введите комментарий"/>
            </div>
            <div class="form-group last">
                <div class="col-sm-offset-3 col-sm-6">
                    <button type="submit" class="btn btn-success btn-sm btn-block">Добавить</button>
                </div>
            </div>
        </form:form>
    </div>
</sec:authorize>
