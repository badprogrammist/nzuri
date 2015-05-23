<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${message != null and !message.isEmpty()}">
    <div class="alert alert-success" role="alert">${message}</div>
</c:if>
