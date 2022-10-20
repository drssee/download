<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>축제</title>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/common.css">
</head>
<script>
    //로그인유지체크 + 로그인성공시 msg=remember_login 이 전달됨
    let query = window.location.search;
    let param = new URLSearchParams(query);
    let msg = param.get('msg');
    //체크된 경우
    if(msg!=null&&msg.length>0){
        alert(msg)
        if(msg==='remember_login'){
            //logined_cookie를 생성
            setCookie("logined_cookie","true",7);
        }
    }
    function setCookie(cookieName, value, exdays) {
        var exdate = new Date();
        exdate.setDate(exdate.getDate() + exdays);
        var cookieValue = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
        document.cookie = cookieName + "=" + cookieValue;
    }
</script>
<body>
<%--    <script src="javascript/header_sub.js"></script>--%>
    <jsp:include page="header_sub.jsp" flush="true"/>
    <main>
        <div id="main">
            <section id="visual">
               <div class="main_bg">
                   <p class="bg00"></p>
                   <p class="bg01"></p>
                   <p class="bg02"></p>
                   <p class="bg03"></p>
               </div>
               <p class="main_txt">SEOUL FESTIVAL
                   <a href="<c:url value="/list"/>">list</a>
                </p>
            </section>
        </div>
    </main>
</body>
</html>