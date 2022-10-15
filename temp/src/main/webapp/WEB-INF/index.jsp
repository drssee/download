<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>축제</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/common.css">
</head>
<body>
    <script src="../javascript/header_sub.js"></script>
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
</p>
           <span>수정</span>
                ${user}
                <a href="/logout">logout</a>
            </section>
        </div>
    </main>
</body>
</html>