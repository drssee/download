<%--
  Created by IntelliJ IDEA.
  User: kimnamhyun
  Date: 2022/09/09
  Time: 6:21 PM
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
  <!--검색창추가코드-->
  <div class="row content">
    <div class="col">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Search</h5>
          <form action="/todo/list" method="get">
            <input type="hidden" name="size" value="${pageRequestDTO.size}">
            <div class="mb-3">
              <input type="checkbox" name="finished" ${pageRequestDTO.finished?"checked":""}>완료여부
            </div>
            <div class="mb-3">
              <input type="checkbox" name="types" value="t" ${pageRequestDTO.checkType("t")?"checked":""}>제목
              <input type="checkbox" name="types" value="w" ${pageRequestDTO.checkType("w")?"checked":""}>작성자
              <input type="text" name="keyword" class="form-control" value="${pageRequestDTO.keyword}">
            </div>
            <div class="input-group mb-3 dueDateDiv">
              <input type="date" name="from" class="form-control" value="<c:out value='${pageRequestDTO.from}'></c:out>">
              <input type="date" name="to" class="form-control" value="<c:out value='${pageRequestDTO.to}'></c:out>">
            </div>
            <div class="input-group mb-3">
              <div class="float-end">
                <button class="btn btn-primary" type="submit">Search</button>
                <button class="btn btn-info clearBtn" type="reset">Clear</button>
              </div>
              <script>
                document.querySelector(".clearBtn").addEventListener("click",function(e){
                  e.preventDefault()
                  e.stopPropagation()

                  self.location="/todo/list" //self.location vs window.location 차이
                },false)
              </script>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <!--검색창추가코드-->
  <div class="row content">
    <!--card로 추가한부분-->
    <div class="col">
      <div class="card">
        <div class="card-header">
          Featured
        </div>
        <div class="card-body">
          <h5 class="card-title">Special title treatment</h5>
          <table class="table">
            <thread>
              <tr>
                <th scope="col">Tno</th>
                <th scope="col">Title</th>
                <th scope="col">Writer</th>
                <th scope="col">DueDate</th>
                <th scope="col">Finished</th>
              </tr>
            </thread>
            <tbody>
            <c:forEach var="dto" items="${responseDTO.dtoList}">
              <tr>
                <th scope="row"><c:out value="${dto.tno}"/></th>
                <td><a href="<c:url value='/todo/read?tno=${dto.tno}&${pageRequestDTO.link}'/>"><c:out value="${dto.title}"/></a></td>
<%--                <td><a href="/todo/read?tno=${dto.tno}&${pageRequestDTO.link}"><c:out value="${dto.title}"/></a></td>--%>
                <td><c:out value="${dto.writer}"/></td>
                <td><c:out value="${dto.dueDate}"/></td>
                <td><c:out value="${dto.finished}"/></td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
          <div class="float-end">
            <ul class="pagination flex-wrap">
              <c:if test="${responseDTO.prev}">
                <li class="page-item">
                  <a href="<c:url value='/todo/list?page=${responseDTO.start-1}&size=${responseDTO.size}'/>" class="page-link">Previous</a>
<%--                  <a data-num="${responseDTO.start-1}" class="page-link">Previous</a>--%>
                </li>
              </c:if>
              <c:forEach begin="${responseDTO.start}" end="${responseDTO.end}" var="num">
<%--                <li class="page-item ${responseDTO.page==num?"active":""}"><a class="page-link" href="<c:url value='/todo/list?page=${num}&size=${responseDTO.size}'/>">${num}</a></li>--%>
                <c:set value="${num}" var = "num"/>
                <li class="page-item ${responseDTO.page==num?"active":""}"><a class="page-link" href="<c:url value="/todo/list?${pageRequestDTO.getLink2(num)}"/>">${num}</a></li>
              </c:forEach>

              <c:if test="${responseDTO.next}">
                <li class="page-item">
                  <a href="<c:url value='/todo/list?page=${responseDTO.end+1}&size=${responseDTO.size}'/>" class="page-link">Next</a>
<%--                      <a data-num="${responseDTO.end+1}" class="page-link">Next</a>--%>
                </li>
              </c:if>
            </ul>
          </div>
<%--          <script>--%>
<%--            document.querySelector(".pagination").addEventListener("click",function(e){--%>
<%--              e.preventDefault();--%>
<%--              e.stopPropagation();--%>
<%--              const target = e.target // target을 이벤트의 타겟으로--%>
<%--              if(target.tagName != 'A'){--%>
<%--                return--%>
<%--              }--%>
<%--              const num = target.attr("data-num");--%>
<%--              self.location="todo/list?page="+num;--%>
<%--              // self.location=`/todo/list?page=\${num}`--%>
<%--            },false)--%>
<%--          </script>--%>
        </div>
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
