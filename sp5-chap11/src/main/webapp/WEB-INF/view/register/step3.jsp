<%--
  Created by IntelliJ IDEA.
  User: nimoh
  Date: 2023/02/27
  Time: 1:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
    <p><strong>${registerRequest.name}</strong>회원 가입을 완료했습니다.</p> <!-- 커맨드 객체의 첫 글자를 소문자로 바꾼 클래스 이름 -->
    <p><a href="<c:url value='/main'/>">[첫 화면 이동]</a></p>
</body>
</html>
