<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:default title="Профиль">
    
    <div id="examples">
        <c:forEach var="exampleItem" items="${profile.examples}">
            <t:image fileId="${exampleItem.image.id}"/>
        </c:forEach>
    </div>
    
    <form action="profile/example/upload" class="dropzone" enctype="multipart/form-data">
        <div class="fallback">
            <input name="file" type="file" multiple />
        </div>
    </form>


</t:default>
