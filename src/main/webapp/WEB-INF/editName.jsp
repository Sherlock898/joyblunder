<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar nombre</title>
    <style>
        *{
            font-family: "Comic Sans MS", cursive, sans-serif;
        }
    </style>
</head>
<body>
    <h1>Change ${name.name}</h1>
    <form:form action="/names/${name.id}/edit" method="POST" modelAttribute="babyName">
        <div>
            <form:label path="gender" for="gender">Typical gender</form:label>
            <form:select path="gender" name="gender">
                <form:option value="Neutral">Neutral</form:option>
                <form:option value="Male">Male</form:option>
                <form:option value="Female">Female</form:option>    
            </form:select>
        </div>
        <div>
            <form:label path="origin" for="origin">Origin</form:label>
            <form:input type="text" path="origin" name="origin"/>
            <form:errors path="origin"/>
        </div>
        <div>
            <form:label path="meaning" for="meaning">Meaning</form:label>
            <form:input type="text" path="meaning" name="meaning"/>
            <form:errors path="meaning"/>
        </div>
        <input type="submit" value="Submit"/>
    </form:form>
    <form action="/names/${name.id}/delete" method="POST"><input type="submit" value="DELETE"></form>
    <a href="/home"><button>Cancel</button></a>
</body>
</html>