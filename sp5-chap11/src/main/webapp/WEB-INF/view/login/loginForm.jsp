<%--
  Created by IntelliJ IDEA.
  User: nimoh
  Date: 2023/03/03
  Time: 11:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="login.title"/></title>
</head>
<body>
    <form:form modelAttribute="loginCommand">
        <form:errors/>
        <p>
            <label><spring:message code="email"/>:<br>
                <form:input path="email"/>
                <form:errors path="email"/>
            </label>
        </p>
        <p>
            <label><spring:message code="password"/>:<br>
                <form:input path="password"/>
                <form:errors path="password"/>
            </label>
        </p>
        <p>
            <label>
                <spring:message code="rememberEmail"/>:
                <form:checkbox path="rememberEmail"/>
            </label>
        </p>
        <input type="submit" value="<spring:message code="login.btn"/>">
    </form:form>
</body>
</html>
