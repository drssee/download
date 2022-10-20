<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>행사 목록</title>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/sub.css">
</head>

<body>
    <script src="javascript/header_sub.js"></script>
    <script>
        let query = window.location.search;
        let param = new URLSearchParams(query);
        let msg = param.get('msg');
        if(msg!=null){
            alert(msg)
        }
    </script>
    <main id="festival_list">
        <div class="sub_tit_wrap">
            <div class="sub_tit_inner">
                <h2>2022 행사 예약</h2>
            </div>
        </div>
        <!--     게시판 검색 영역       -->
        <div class="container_wrap">
            <div id="search_wrap">
                <input type="text" name="list_search" value="검색어 입력" class="list_search">
                <button type="submit" id="list_search_btn">검색</button>
            </div>
            <ul id="list_wrap">
                <c:forEach items="${requestScope.pageResponse.getPageList()}" var="culture">
                    <li>
                        <a href="<c:url value="/detail?cno=${culture.getCno()}"/>">
                            <p id="list_img">
                                <img src="${culture.getImg_url()}" alt=""> </p>
                            <div class="list_box">
                                <p id="list_pro_name">${culture.getSvc_nm()}</p>
                                <ul id="list_pro_info">
                                    <li><strong>장소명</strong><span>${culture.getPlace_nm()}</span></li>
                                    <li><strong>이용대상</strong><span>${culture.getUse_tgt_info()}</span></li>
                                    <li><strong>접수기간</strong><span>${culture.getRcpt_bgn_dt()}~${culture.getRcpt_end_dt()}</span></li>
                                    <li><strong>이용기간</strong><span>${culture.getSvc_opn_bgn_dt()}~${culture.getSvc_opn_end_dt()}</span></li>
                                </ul>
                            </div>
                        </a>
                    </li>
                </c:forEach>
                <div class="nav">
                    <ul>
                        ${requestScope.pageResponse}
                        <c:if test="${requestScope.pageResponse.isShowPrev()}">
                            <a href="/project/list?page=${requestScope.pageResponse.page-1}&size=${requestScope.pageResponse.size}">
                                <li>[PREV]</li>
                            </a>
                        </c:if>
                        <c:forEach begin="${requestScope.pageResponse.start}" end="${requestScope.pageResponse.end}" var="num">
                            <a href="/project/list?page=${num}&size=${requestScope.pageResponse.size}">
                                <li class="${num==requestScope.pageResponse.page?'sel':''}">${num}</li>
                            </a>
                        </c:forEach>
                        <c:if test="${requestScope.pageResponse.isShowNext()}">
                            <a href="/project/list?page=${requestScope.pageResponse.page+1}&size=${requestScope.pageResponse.size}">
                                <li>[NEXT]</li>
                            </a>
                        </c:if>
                    </ul>
                </div>

            </ul>
        </div>

    </main>
</body></html>