<%--
  Created by IntelliJ IDEA.
  User: kimnamhyun
  Date: 2022/10/19
  Time: 1:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
detail
dtlcont의 모든 img 태그를 작동 안되게 하고 img_url 사용?
${fn:replace(fn:replace(requestScope.culture.getDtlcont(), "\\r\\n", " "), "\\t", " ")}
<h1>리뷰리뷰리뷰</h1>
<h1>예약예약예약</h1>
<form action="<c:url value="/detail"/>" method="post">
    <input type='date' name='res_dt'/>
    <%--res_dt=2022-10-29--%>
    <input type="hidden" name="cno" value="${requestScope.culture.getCno()}"/>
    <input type="hidden" name="id" value="${sessionScope.user}"/>
    <button type="submit" onclick="return confirm('예약 하시겠습니까?')">전송</button>
</form>
</body>
</html>
