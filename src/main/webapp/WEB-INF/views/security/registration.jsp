<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:default title="Login">





    <form method="post" enctype="multipart/form-data" action="signUp">

        <t:image-uploader fileName="icon"/>

        <select name="role">
            <c:forEach var="roleItem" items="${roles}">
                <option value="${roleItem.name()}">${roleItem.title}</option>
            </c:forEach>
        </select>


        <label for="name">Имя</label>
        <input id="name" type="text"  name="name"/>
        <br/>
        <label for="lastname">Фамилия</label>
        <input id="lastname" type="text" name="lastname"/>
        <br/>
        <label for="patronymic">Отчество</label>
        <input id="patronymic" type="text" name="patronymic"/>
        <br/>
        <label for="email">E-mail</label>
        <input id="email" type="text" name="email"/>
        <br/>
        <label for="password">Пароль</label>
        <input id="password" type="password"  name="password"/>
        <br/>
        <button type="submit">Save</button>
    </form>
</t:default> 