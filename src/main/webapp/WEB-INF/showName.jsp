<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Name</title>
    <style>
        *{
            font-family: "Comic Sans MS", cursive, sans-serif;
        }
    </style>
</head>
<body>
    <h1>${babyName.name}</h1>
    <h2>(Added by ${babyName.user.name})</h2>
    <h2>Gender: ${babyName.gender}</h2>
    <h2>Origin: ${babyName.origin}</h2>
    <c:choose>
        <c:when test="${votedForThis}">
            <h2 style="color: magenta;">You voted for this name</h2>
        </c:when>
        <c:otherwise>
            <form action="/names/${babyName.id}/vote" method="POST">
                <input type="submit" value="upvote!"/>
            </form>
        </c:otherwise>
    </c:choose>
    <p>Meaning: ${babyName.meaning}</p>
    <c:if test="${isOwner}">
        <a href="/names/${babyName.id}/edit">
            <button>Edit</button>
        </a>
    </c:if>    
</body>
</html>