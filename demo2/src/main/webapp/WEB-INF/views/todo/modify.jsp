<%--
  Created by IntelliJ IDEA.
  User: kimnamhyun
  Date: 2022/09/09
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<div class="container-fluid">
    <div class="row">
        <div class="col">
            <!--nav추가된부분-->
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Navbar</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Link</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Dropdown
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="#">Action</a></li>
                                    <li><a class="dropdown-item" href="#">Another action</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="#">Something else here</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link disabled">Disabled</a>
                            </li>
                        </ul>
                        <form class="d-flex">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </form>
                    </div>
                </div>
            </nav>
            <!--nav추가된부분-->
        </div>
    </div>
    <div class="row content">
        <!--card로 추가한부분-->
        <div class="col">
            <div class="card">
                <div class="card-header">
                    Featured
                </div>
                <div class="card-body">
                    <form action="<c:url value="/todo/remove"/>" method="post">
                        <div class="input-group mb-3">
                            <span class="input-group-text">TNO</span>
                            <input type="text" name="tno" class="form-control" value="<c:out value='${dto.tno}'></c:out>" readonly>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Title</span>
                            <input type="text" name="title" class="form-control" value="<c:out value='${dto.title}'></c:out>">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">DueDate</span>
                            <input type="date" name="dueDate" class="form-control date" value="<c:out value='${dto.dueDate}'></c:out>">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Writer</span>
                            <input type="text" name="Writer" class="form-control" value="<c:out value='${dto.writer}'></c:out>" readonly>
                        </div>

                        <div class="form-check">
                            <label class="form-check-label">
                                Finished&nbsp;&nbsp;
                            </label>
                            <input class="form-check-input" type="checkbox" name="finished" ${dto.finished?"checked":""}>
                        </div>
                        <!--검색/필터링 조건을 기억하도록 구현했기 때문에 검색/필터링 전 페이지와 사이즈는 필요가 없다-->
<%--                        <input type="hidden" name="page" value="${pageRequestDTO.page}">--%>
<%--                        <input type="hidden" name="size" value="${pageRequestDTO.size}">--%>
                        <div class="my-4">
                            <div class="float-end">
                                <button type="button" class="btn btn-danger">Remove</button>
                                <button type="button" class="btn btn-primary">Modify</button>
                                <button type="button" class="btn btn-secondary">List</button>
                            </div>
                            <script>
                                document.querySelector(".btn-danger").addEventListener("click",function(){
                                    document.getElementsByTagName("form").item(1).action="/todo/remove";
                                    document.getElementsByTagName("form").item(1).method="post";
                                    document.getElementsByTagName("form").item(1).submit();
                                });
                                document.querySelector(".btn-primary").addEventListener("click",function(){
                                    document.getElementsByTagName("form").item(1).action="/todo/modify?${pageRequestDTO.link}";
                                    document.getElementsByTagName("form").item(1).method="post";
                                    document.getElementsByTagName("form").item(1).submit();
                                });

                                document.querySelector(".btn-secondary")
                                    .addEventListener("click",function(e){
                                        e.preventDefault();
                                        e.stopPropagation();

                                        self.location="/todo/list?${pageRequestDTO.link}";
                                    },false);

                                //**검증에 문제가 생길시 사용되는 코드**
                                const serverValidResult = {};//js 배열 선언
                                <c:forEach items="${errors}" var="error">
                                    serverValidResult['${error.getField()}']='${error.defaultMessage}'
                                    alert(serverValidResult['${error.getField()}']);
                                </c:forEach>
                                console.log(serverValidResult);
                            </script>
                        </div>
                    </form>
                </div><!--card-body-->
            </div>
        </div>
        <!--card추가가한부분끝-->
    </div>
</div>
<div class="row footer">
    <div class="row fixed-bottom" style="z-index: -100">
        <footer class="py-1 my-1">
            <p class="text-center text-muted">Footer</p>
        </footer>
    </div>
</div>
</div>
</body>
</html>

