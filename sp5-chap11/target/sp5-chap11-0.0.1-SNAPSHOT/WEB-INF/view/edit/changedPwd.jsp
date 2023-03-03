<%--
  Created by IntelliJ IDEA.
  User: nimoh
  Date: 2023/03/03
  Time: 1:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="change.pwd.title"/></title>
</head>
<body>
  <p>
    <spring:message code="change.pwd.done"/>
  </p>
  <p>
    <a href="<c:url value='/main'/>">
      [<spring:message code="go.main"/>]
    </a>
  </p>
</body>
</html>
