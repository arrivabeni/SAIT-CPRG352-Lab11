<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${forgotMode}">
                <h1>Reset Password</h1>
                <p>Please enter your email address to reset your password.</p>
                <form action="reset" method="post">
                    <input type="hidden" name="action" value="forgot">
                    <input type="email" name="email" required>
                    <br><br>
                    <input type="submit" value="Submit">
                </form>
            </c:when>
            <c:otherwise>
                <h1>Completed</h1>
                <p>${message}</p>
                <a href="login">Go to login page.</a>
            </c:otherwise>
        </c:choose>
    </body>
</html>
