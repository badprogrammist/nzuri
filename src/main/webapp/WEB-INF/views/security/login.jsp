<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<layout:default title="Вход">

    <div class="row">
        <div class="col-md-4 col-md-offset-7">
            <div class="panel panel-default">
                <div class="panel-heading"><span class="glyphicon glyphicon-lock"></span> Вход</div>
                <div class="panel-body">
                    <form:form class="form-horizontal" role="form" method="post" action="signIn" modelAttribute="credentials" >
                        <div class="form-group">
                            <label for="emailInput" class="col-sm-3 control-label">Почта</label>
                            <div class="col-sm-9">
                                <form:input id="emailInput"  path="login" type="email" class="form-control"  placeholder="Введите почту" required="true"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword" class="col-sm-3 control-label">Пароль</label>
                            <div class="col-sm-9">
                                <form:password id="inputPassword" class="form-control" placeholder="Введите пароль" required="true" path="password"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-9">
                                <div class="checkbox">
                                    <label><input type="checkbox"/>Запомнить меня</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group last">
                            <div class="col-sm-offset-3 col-sm-9">
                                <button type="submit" class="btn btn-success btn-sm btn-block">Войти</button>
                            </div>
                        </div>
                    </form:form>
                </div>
                <div class="panel-footer">
                    Еще не зарегистрированы? <a href="registration">Зарегистрироваться</a>
                </div>
            </div>
        </div>
    </div>


</layout:default> 