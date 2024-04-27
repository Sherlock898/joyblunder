<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Joy Bundler</title>
    <style>
        *{
            font-family: "Comic Sans MS", cursive, sans-serif;
        }
    </style>
</head>

<body>
    <h1 style="color: green;">Joy Bundler Names</h1>
    <div>
        <h2>Register</h2>
        <form:form action="/register" method="POST" modelAttribute="user">
            <div>
                <form:label path="name" for="name">Name:</form:label>
                <form:input path="name" id="name" name="name" type="text" />
                <form:errors path="name" />
            </div>
            <div>
                <form:label path="email" for="email">Email:</form:label>
                <form:input path="email" id="email" name="email" type="email" />
                <form:errors path="email" />
            </div>
            <div>
                <form:label path="password" for="password">Password:</form:label>
                <form:input path="password" id="password" name="password" type="password" />
                <form:errors path="password" />
            </div>
            <div>
                <form:label path="passwordConfirm" for="passwordConfirm">Password Confirmation:</form:label>
                <form:input path="passwordConfirm" id="passwordConfirm" name="passwordConfirm" type="password" />
                <form:errors path="passwordConfirm" />
            </div>
            <input type="submit" value="Register" />
        </form:form>
    </div>
    <div>
        <h2>Log in</h2>
        <c:out value="${error}" />
        <form action="/login" method="POST">
            <div>
                <label for="_email">Email:</label>
                <input type="email" id="_email" name="_email" />
            </div>
            <div>
                <label for="_password">Password:</label>
                <input type="password" id="_password" name="_password" />
            </div>
            <input type="submit" value="Login" />
        </form>
    </div>
</body>
</html>