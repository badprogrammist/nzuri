<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@attribute name="fileId" required="true" %>
<img style="width: 200px;" src="${pageContext.request.contextPath}/file/${fileId}"/>
