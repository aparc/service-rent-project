<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Posts Page</title>

</head>
<body>
<a href="../">Back to main menu</a>

<br/>
<br/>

<h1>Post List</h1>

<c:if test="${!empty listPosts}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Type</th>
            <th width="120">Number of Rooms</th>
            <th width="120">Location</th>
            <th width="60">Price</th>
            <th width="60">Date</th>
        </tr>
        <c:forEach items="${listPosts}" var="post">
            <tr>
                <td>${post.postId}</td>
                <td>${post.type}</td>
                <td>${post.numberOfRooms}</td>
                <td>${post.location}</td>
                <td>${post.price}</td>
                <td>${post.date}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>