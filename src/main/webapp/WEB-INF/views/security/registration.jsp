<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common" %>


<layout:default title="Регистрация">

    <div class="row">
        <div class="col-md-5 col-md-offset-7">
            <div class="panel panel-default">
                <div class="panel-heading"><span class="glyphicon glyphicon-lock"></span> Регистрация</div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" method="post" enctype="multipart/form-data" action="signUp">
                        <div class="form-group">
                            <common:image-uploader styleClass="form-control img-thumbnail" fileName="icon"/>
                        </div>
                        <div class="form-group">
                            <label for="role" class="col-sm-3 control-label">Я</label>
                            <div class="col-sm-9">
                                <select id="role" class="form-control" name="role">
                                    <c:forEach var="roleItem" items="${roles}">
                                        <option value="${roleItem.name()}">${roleItem.title}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label">Имя</label>
                            <div class="col-sm-9">
                                <input id="name" class="form-control" type="text" placeholder="Введите имя" name="name" required="true"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-3 control-label">Фамилия</label>
                            <div class="col-sm-9">
                                <input id="lastname" class="form-control" type="text" placeholder="Введите фамилию" name="lastname" required="true"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="patronymic" class="col-sm-3 control-label">Отчество</label>
                            <div class="col-sm-9">
                                <input id="patronymic" class="form-control" type="text" placeholder="Введите отчество" name="patronymic" required="true"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-3 control-label">Почта</label>
                            <div class="col-sm-9">
                                <input id="email" name="email" type="email" class="form-control"  placeholder="Введите почту" required="true"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-3 control-label">Пароль</label>
                            <div class="col-sm-9">
                                <input id="password" type="password" name="password" class="form-control" placeholder="Введите пароль" required="true" />
                            </div>
                        </div>

                        <div class="form-group last">
                            <div class="col-sm-offset-3 col-sm-9">
                                <button type="submit" class="btn btn-primary btn-sm btn-block">Зарегистрироваться</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-footer">
                    <a href="login">Войти в систему</a>
                </div>
            </div>
        </div>
    </div>




</layout:default> 