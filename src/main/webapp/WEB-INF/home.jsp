<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Project Manager Dashboard</title>
</head>
<style>
    *{
        font-family: "Comic Sans MS", cursive, sans-serif;
    }
</style>
<body>
    <div>
        <h2 style="color: green;">Hello, ${user.name}. Here are some name suggestions..</h2>
        <a href="/logout">Logout</a>
    </div>
    <div>
        <h2>Baby names</h2>
        <table>
            <thead>
            <tr>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th>Votes</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${babyNames}" var="name">
                <tr>
                    <td><form action="/names/${name.id}/vote" method="POST"><input type="submit" value="upvote!"/></form></a></td>
                    <td><a href="/names/${name.id}">${name.name}</a></td>
                    <td>${name.gender}</td>
                    <td>Origin: ${name.origin}</td>
                    <td>${name.voters.size()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <a href="/names/new">
        <button>new name</button>
    </a>
</body>
</html>