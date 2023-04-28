<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 놀이공원 관리</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous"><style type="text/css">
  .wrap{
  	padding:10px;
  }
  
   .paging a{
  	float:left;
  	padding:5px;
  }
</style>
<script type="text/javascript">
  function searchAmusement(){
    var input = document.getElementsByName('search')[0];
    var search = input.value;
    location.href ="/adminAmusement?search="+search; 
  }

</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/menu.jsp"/>
  <div class="wrap">
    <a href="/admin ">관리자메인</a>   
    <div style="text-align:right">
    	<button onclick='location.href="/insertAdminAmusement"'>놀이공원 등록</button>
    </div>
    <div>
	<h1>놀이공원 관리</h1>
	<p>놀이공원검색</p>
	<input type="text" value="${search }" name="search">
    
    <button type="button" onclick="searchAmusement()">검색</button>
    <div class="container text-center">
      <div class="row">
      	<c:forEach var="list" items="${amusement}">
    	  <div class="col-4" style="border:1px solid #aaa; padding: 20px 0">
	    	<img alt="" src="${list.a_img }" style="width:200px;"> 
	    	<br>
	    	  ${list.a_name }
	    	<br>
	    	  ${list.a_country }
	    	<br>
	    	  <button onclick="location.href='/amuseDetail?amuse_id=${list.amuse_id}'">놀이공원 페이지로 이동</button>
	    	  <button onclick="location.href='/adminAmusementDetail?amuse_id=${list.amuse_id}'">관리자 상세보기</button>
	    	<br>
    	    </div>
    	   
    	  </c:forEach>
  		</div>
      </div>
      <div class="paging">
       	${paging}
      </div>
    </div>
  </div>
</body>
</html>