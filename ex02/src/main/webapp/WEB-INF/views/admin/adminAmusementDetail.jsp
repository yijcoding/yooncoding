<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 디테일</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

<style type="text/css">
  .wrap{
  	padding:10px;
  }
</style>

<script type="text/javascript">
	function deleteAmusemnet(){
		if(confirm("놀이공원을 삭제하시겠습니까?")){
			if(confirm("재확인")) location.href="/deleteAmusment?amuse_id=${amuse[0].amuse_id}";
		}
	}
	
	function deleteRide(e){
		if(confirm("놀이기구를 삭제하시겠습니까?")){
			if(confirm("재확인"))
				location.href="/deleteRide?rides_id="+e+"&amuse_id=${amuse[0].amuse_id}";
		}
	}
	
	$(document).ready((e)=>{
		 $('#ridesSH').on('click',() => {
		    var commentListDisplay = document.getElementById('ridesTable');
			var commentHide = document.getElementById('ridesSH');
			if(commentListDisplay.style.display =='none'){
				commentListDisplay.style.display="";
				commentHide.innerText = '접기';
			}else{
				commentListDisplay.style.display="none";
				commentHide.innerText = '펼치기';
			}
		 })
	})
</script>
</head>
<body>
  <jsp:include page="/WEB-INF/views/menu.jsp"/>
  <div class="wrap">
    <div>
      <p style="font-size:20px;font-weight: bolder">놀이공원 이름</p>
      <p>${amuse[0].a_name }</p>
      <p style="font-size:20px;font-weight: bolder">나라</p>
      <p>${amuse[0].a_country }</p>
      <p  style="font-size:20px;font-weight: bolder">운영시간</p>
      <p>${amuse[0].a_time }</p>
      <p style="font-size:20px;font-weight: bolder">설명</p>
      <div style="max-width:1100px;">  
	      ${amuse[0].a_info }
      </div>
      <p style="font-size:20px;font-weight: bolder">좌표</p>
      <p>${amuse[0].a_lat }
  		${amuse[0].a_lng }</p>
      <p style="font-size:20px;font-weight: bolder">이미지</p>
      <div style="display:inline-block">
      <c:forEach var="imgs" items="${amuseimg}">
        <img src="${imgs.url}" style="width:200px;">
      </c:forEach>
      </div>
      <div style="text-align:right">
     	<button onclick="location.href='/updateAmusmentDetail?amuse_id=${amuse[0].amuse_id}'">수정</button>
     	<button onclick="deleteAmusemnet()">삭제</button>
      </div>
    </div>
    <div>
    	<hr style="color:#888">
    	<h3>놀이기구</h3>
    	<span class="ridesSH" id="ridesSH" style="cursor:pointer">펴기</span>
    	<div style="text-align:right"><button onclick="location.href='/insertRide?amuse_id=${amuse[0].amuse_id}'">놀이기구 추가</button></div>
    	<table id="ridesTable" style="display:none">
    		<tr>
    			<th>이미지</th>
    			<th>이름</th>
    			<th>위치</th>
    			<th>수정</th>
    			<th>삭제</th>
    		</tr>
    		<c:forEach var="list" items="${rides }">
    			<tr>
	    			<td><a href="${list.r_img} "><img src ="${list.r_img }"  style="width:100px"></a></td>
	    			<td><a href="/ridesDetail?rides_id=${list.rides_id }">${list.r_name }</a></td>
	    			<td>${list.r_location }</td>
	    			<td><button onclick="location.href='/updateRide?rides_id=${list.rides_id}'">수정</button></td>
	    			<td><button onclick="deleteRide('${list.rides_id}')">삭제</button></td>
    			</tr>
    		</c:forEach>
    	</table>
    </div>
  </div>
	
</body>
</html>