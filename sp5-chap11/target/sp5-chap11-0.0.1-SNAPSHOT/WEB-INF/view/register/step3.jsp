<%--
  Created by IntelliJ IDEA.
  User: nimoh
  Date: 2023/02/27
  Time: 1:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title><spring:message code="member.register"></title>
</head>
<body>
    <p>
        <spring:message code="register.btn"
                        arguments="${registerRequest.name}" />
    </p>
    <p>
        <a href="<c:url value='/main'/>">[<spring:message code="go.main">]</a>
    </p>
</body>
</html>
