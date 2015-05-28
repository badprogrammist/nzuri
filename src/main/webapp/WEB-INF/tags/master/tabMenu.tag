<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="activeTab" required="true" %>
<ul class="nav nav-tabs">
    <li role="presentation" class="${activeTab.equals('main') ? 'active' : ''}"><a href="${pageContext.request.contextPath}/master/edit">Основное</a></li>
    <li role="presentation" class="${activeTab.equals('examples') ? 'active' : ''}"><a href="${pageContext.request.contextPath}/master/edit/examples">Примеры</a></li>
    <li role="presentation" class="${activeTab.equals('address') ? 'active' : ''}"><a href="${pageContext.request.contextPath}/master/edit/address">Адрес</a></li>
    <li role="presentation" class="${activeTab.equals('actions') ? 'active' : ''}"><a href="${pageContext.request.contextPath}/master/edit/actions">Услуги</a></li>
</ul>       