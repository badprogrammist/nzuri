<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<layout:default title="Мастера">

    <div class="row full-height">
        <div class="col-sm-6 full-height">
            <div class="block">
                <c:forEach var="profileItem" items="${profiles}">
                    <div class="media">
                        <div class="media-left">
                            <a href="master/${profileItem.id}">
                                <common:image styleClass="media-object" style="width:64px;height:64px;" fileId="${profileItem.user.userData.icon.id}"/>
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">
                                <a href="master/${profileItem.id}">${profileItem.user.userData.fullName}</a>
                            </h4>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div id="map" class="col-sm-6 full-height">

        </div>
    </div>
    <script src="http://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <script type="text/javascript">
        ymaps.ready(init);
        var myMap;

        function init() {
            myMap = new ymaps.Map("map", {
                center: [55.76, 37.64],
                zoom: 7
            });
        }
    </script>



</layout:default>
