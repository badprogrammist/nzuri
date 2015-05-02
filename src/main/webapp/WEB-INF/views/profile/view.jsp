<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:default title="Профиль">
    
    <t:image fileId="${profile.user.userData.icon.id}"/>
    <h1>${profile.user.userData.fullName}</h1>
    
    <div id="examples">
        <c:forEach var="exampleItem" items="${profile.examples}">
            <t:image fileId="${exampleItem.image.id}"/>
        </c:forEach>
    </div>
    


</t:default>
