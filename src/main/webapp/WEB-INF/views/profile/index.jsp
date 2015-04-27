<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:default title="Examples">
    <a href="example/create">Add</a>
    <c:forEach var="exampleItem" items="${examples}">
        <p>${exampleItem.comment}</p>
    </c:forEach>
    <a href="#" class="increment_button">Increment</a>    
    <div class="increment_value">
        <jsp:include page="count.jsp"/>
    </div>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".increment_button").click(function () {
                $.ajax({
                    url: 'example/increment',
                    type:'POST',
                    dataType: "text",
                    success: function (data) {
                        $('.increment_value').html(data);
                    }
                });
            });
        });
    </script>
</t:default>
