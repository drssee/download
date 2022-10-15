<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <script src="../javascript/jquery-3.6.0.min.js"></script>

    <script>
        $(document).ready(function () {
            // 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
            var key = getCookie("key");
            $("#u_id").val(key);
            // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
            if ($("#u_id").val() != "") {
                $("#chk1").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
            }

            $("#chk1").change(function () { // 체크박스에 변화가 있다면,
                if ($("#chk1").is(":checked")) { // ID 저장하기 체크했을 때,
                    setCookie("key", $("#u_id").val(), 7); // 7일 동안 쿠키 보관
                } else { // ID 저장하기 체크 해제 시,
                    deleteCookie("key");
                }
            });


            // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
            $("#u_id").keyup(function () { // ID 입력 칸에 ID를 입력할 때,
                if ($("#chk1").is(":checked")) { // ID 저장하기를 체크한 상태라면,
                    setCookie("key", $("#u_id").val(), 7); // 7일 동안 쿠키 보관
                }
            });
            // 쿠키 저장하기
            // setCookie => saveid함수에서 넘겨준 시간이 현재시간과 비교해서 쿠키를 생성하고 지워주는 역할
            function setCookie(cookieName, value, exdays) {
                var exdate = new Date();
                exdate.setDate(exdate.getDate() + exdays);
                var cookieValue = escape(value) +
                    ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
                document.cookie = cookieName + "=" + cookieValue;
            }

            // 쿠키 삭제
            function deleteCookie(cookieName) {
                var expireDate = new Date();
                expireDate.setDate(expireDate.getDate() - 1);
                document.cookie = cookieName + "= " + "; expires=" +
                    expireDate.toGMTString();
            }

            // 쿠키 가져오기
            function getCookie(cookieName) {
                cookieName = cookieName + '=';
                var cookieData = document.cookie;
                var start = cookieData.indexOf(cookieName);
                var cookieValue = '';
                if (start != -1) { // 쿠키가 존재하면
                    start += cookieName.length;
                    var end = cookieData.indexOf(';', start);
                    if (end == -1) // 쿠키 값의 마지막 위치 인덱스 번호 설정
                        end = cookieData.length;
                    console.log("end위치  : " + end);
                    cookieValue = cookieData.substring(start, end);
                }
                return unescape(cookieValue);
            }
        })
    </script>



    <script>
        $(document).ready(function() {

            //아이디/비밀번호에 포커스하면 텍스트 효과
            $(".u_txt input").focus(function() {
                $(this).siblings("label").addClass("focus");
            }).blur(function() {
                if ($(this).val() == "") {
                    $(this).siblings("label").removeClass("focus");
                } else {
                    $(this).siblings("label").addClass("focus");
                }
            })


            //아이디저장, 로그인상태 유지
            $("#chk1,#chk2").change(function() {
                var chk1 = $("#chk1").prop("checked");
                var chk2 = $("#chk2").prop("checked");

                if (chk1) {
                    $(".type02").addClass("checked");
                } else {
                    $(".type02").removeClass("checked");
                }

                if (chk2) {
                    $(".type03").addClass("checked");
                } else {
                    $(".type03").removeClass("checked");
                }
            })

        })

    </script>
    <!-- ------------------------------------------- -->
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/login.css">
    <link rel="stylesheet" href="../css/common.css">

</head>
<!-- ------------------------------------------- -->

<body>
    <script src="../javascript/header_sub.js"></script>
    <div id="wrap">
        <script src="common/header_sub.js"></script>
        <main id="main">
            <div id="login_bg">

            </div>

            <div id="login">
                <div id="top_wrap">
                    <h1><a href="#">SEOUL FESTIVAL</a></h1>
                </div>

                <div id="login_con">
                    <div id="login1" class="login_box">
                        <form action="/login" method="post">
                            <p class="u_txt"><label for="u_id" class="u_id_txt">아이디</label>
                                <input type="text" id="u_id" autocomplete="off" name="id"
                                       placeholder=""></p>
                            <p class="u_txt"><label for="u_id">비밀번호</label>
                                <input type="password" id="u_pwd" autocomplete="off" name="pwd"></p>
                            <p id="chk_wrap1">
                                <input type="checkbox" name="chk" id="chk1" value="1">
                                <label for="chk1" class="type01 type02">아이디 저장</label>
                                <input type="checkbox" name="chk" id="chk2" value="2">
                                <label for="chk2" class="type01 type03">로그인 상태 유지</label>
                            </p>
                            <p id="btn_wrap1">
                                <input type="submit" id="s_btn1" class="s_btn" value="로그인">
                            </p><!-- btn_wrap1 -->

                            <p class="login_account">
                                <a href="#" class="search1">아이디/비밀번호 찾기</a>
                                <a href="../join.html" class="join1">회원가입</a>
                            </p>
                        </form>
                    </div><!-- 회원로그인, login1 -->
                </div><!-- login_con -->
            </div>
        </main>
        <!--        FOOTER 불러오기 -->
        <script src="common/footer.js"></script>
        <!--        FOOTER 불러오기 끝-->
    </div>

</body>

</html>
