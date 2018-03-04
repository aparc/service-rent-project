<%--
  Created by IntelliJ IDEA.
  User: aparc
  Date: 25.02.2018
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
    <link rel="stylesheet" type="text/css" href="../styles/styles.css">
</head>
<body>
<form:form action="/postLists" method="GET">
    <div class="radius">
        <div>
            <label>
                Type
                <input type="text" name="type">
            </label>
        </div>

        <div>
            <label>
                Price
                <input type="text" name="price">
            </label>
        </div>

        <div>
            <input type="submit" value="Search">
        </div>
    </div>
</form:form>


</body>
</html>
