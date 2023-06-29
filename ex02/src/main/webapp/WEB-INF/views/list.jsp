<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>세계 각지의 놀이공원</title>
<!-- 타이틀 파비콘 -->
 <link rel="shortcut icon" type="image/x-icon" href="https://play-lh.googleusercontent.com/W9AN3AyNH7rgBaGO7Jv2MEMk2piGUEerZTZN7hG-xNJFq6QW1Dzs4HLukka0-oKIsw">
<!-- jquery cdn -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"
	integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
	crossorigin="anonymous"></script>
<!-- 부트스트랩 cdn -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
<!-- 폰트 설정 -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Hahmlet&display=swap"
	rel="stylesheet" />
<!-- 아이콘 cdn-->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" />
<!-- 애니메이션 cdn-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
<!-- Leaflet 지도 라이브러리 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.min.js"></script>

<style>
body {
	font-family: 'Hahmlet', serif;
}

.custom-carousel-img {
	max-width: 100%;
	height: 280px;
	object-fit: cover;
}

.custom-card-img {
	max-width: 100%;
	height: 300px;
	object-fit: cover;
}

.custom-main-padding {
	padding-bottom: 200px;
}

.custom-card-body-height {
	height: 100px;
}

img{ transition: 0.3s; }

a:hover img, a:focus img{ 
  transform: scale(1.2);
}
</style>

<!-- 로딩 이벤트 -->
<script type="text/javascript" src="resources/js/loading.js"></script>

<!-- 즐겨찾기 하트 애니메이션 이벤트 -->
<script>
  window.onload = function(){
	  
    var a = 0;
    
    <!-- 일정시간 이벤트 막는 변수 -->
   	let isClickable = true;
   	
   	<!-- amuse_id 선언 -->
   	var amuse_id;
   	<!-- member_id el로 받아옴 -->
   	var member_id = "${member_id}";
     
    var heartWraps = document.querySelectorAll('.heart-wrap');
    
    <!-- 페이지 진입 시 즐겨찾기 체크 후에 하트 채우기 or 비우기 -->
     heartWraps.forEach( (heartWrap) => {
    	var heart = heartWrap.querySelector('.heart');
    	amuse_id = heartWrap.dataset.id;
			$.ajax({
				url : "/indexapi/check", // 서버의 URL
				type : "POST", // HTTP 요청 방식
				dataType : "json", // 서버가 반환하는 데이터 타입
				data : {
					"amuse_id" : amuse_id,
					"member_id" : member_id
				},
				success : function(check) {
		    	if (check['check'] == 1) {
	    	      heart.classList.add('bi-balloon-heart-fill');
	    	    } else {
	    	      heart.classList.add('bi-balloon-heart');
	    	    } 
				},
				error : function(xhr, status, error) {
					console.log("에러");
				}
			});
    }); 
     
    <!-- 위시리스트 추가 알림 선택자 -->
    var toastLiveExample = document.getElementById('liveToast');
    var toastLiveExample2 = document.getElementById('liveToast2')
    var toast = new bootstrap.Toast(toastLiveExample);
    var toast2 = new bootstrap.Toast(toastLiveExample2);
    <!-- 하트 누르면 즐겨찾기 등록 및 해제 -->
	  heartWraps.forEach( (heartWrap) => {
		    heartWrap.addEventListener('click', function(){
		    	var heart = document.querySelector('.heart');
		    	amuse_id = heartWrap.dataset.id;
		   		if (!isClickable) {
		    	    return;
		    	  }
		   		if(member_id == "") {
		   	   		location.href = "/login";
		   	   	} else {
					$.ajax({
						url : "/indexapi/insertDelete", // 서버의 URL
						type : "POST", // HTTP 요청 방식
						dataType : "json", // 서버가 반환하는 데이터 타입
						data : {
							"amuse_id" : amuse_id,
							"member_id" : member_id
						},
						success : function(check) {
			    	  // 일정 시간 동안 클릭 이벤트를 막기
			    	  isClickable = false;
			    	  heartWrap.classList.add('animate__bounce');
			    	  setTimeout(() => {
				    	    isClickable = true;
				    	    heartWrap.classList.remove('animate__bounce');
					          if (check['check'] == 1) {
					        	  	heartWrap.querySelector('.heart').classList.remove('bi-balloon-heart');
					            	heartWrap.querySelector('.heart').classList.add('bi-balloon-heart-fill');
					            	toast2.hide();
						            toast.show();
					            } else {
					            	heartWrap.querySelector('.heart').classList.remove('bi-balloon-heart-fill');
					        	  	heartWrap.querySelector('.heart').classList.add('bi-balloon-heart');
					        	  	toast.hide();
					        	  	toast2.show();
					            }
				    	 }, 1000);
						},
						error : function(xhr, status, error) {
							console.log("에러");
						}
					});
		   	   	}
				
				});
		  });
  }
</script>
<!-- radio 세팅 -->
<!-- radio선택 시 submit 동작(a태그를 대체하여 나라,정렬,검색 정보를 폼으로 보냄) -->
<script>
  $(function() { 
    const radioLabel = document.querySelectorAll('.index-radio');
    const floatingInput = document.querySelector('#floatingInput');
    const form = document.querySelector('#search-form');  
    var searchCountry = "${searchCountry}";
    var order = "${order}";
		
    
    radioLabel.forEach((r) => {
        const radioId = "#"+r.getAttribute('for');
        const radio = document.querySelector(`${'${radioId}'}`);
        if (radio.value == searchCountry) {
       	 radio.setAttribute("checked", "checked");
        }
        if (radio.value == order) {
       	 radio.setAttribute("checked", "checked");
        }
    });

    radioLabel.forEach((r) => {
      r.addEventListener('click', () => {
        setTimeout(() => {
         form.submit();
        },200);
      });
    });
	})
</script>
<!-- 지도 추가 -->
<script>
$(function() {
	var map = L.map("map");
	
	// 타일 레이어 추가
	L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
	  attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors',
	  maxZoom: 18,
	}).addTo(map);
	
	var markers = JSON.parse('${jsonString}');
	
 	// 마커 추가
	for (var i = 0; i < markers.length; i++) {
	  var marker = L.marker([markers[i]['a_lat'], markers[i]['a_lng']]).addTo(map);
	  marker.bindPopup(markers[i]['a_name']);
	}
	
	// 마커 포함하는 영역까지 줌확대 및 위치설정
	var group = new L.featureGroup(
	  markers.map((m) => L.marker([m['a_lat'], m['a_lng']]))
	);
	map.fitBounds(group.getBounds(), {
		  maxZoom: 15
	}); 
});
</script>
</head>
<body>
	<!-- 메뉴(헤더) 추가 -->
	<jsp:include page="./menu.jsp" />

	<main class="container px-4 px-lg-5 my-5 border-bottom">
		<form id="search-form" action="/list">
			<div id="map" class="row mb-3" style="height: 300px"></div>
			<div class="row">
				<div class="col-1">
					<button class="btn" type="submit">
						<i class="bi bi-search pr-2"
							style="font-size: 1.5rem; color: cornflowerblue;"></i>
					</button>
				</div>
				<div class="col">
					<div class="form-floating mb-3">
						<input type="search" name="searchName" class="form-control"
							id="floatingInput" placeholder="놀이공원을 찾아볼까요?"> <label
							for="floatingInput">놀이공원을 찾아볼까요?</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="d-flex">
					<div class="btn-group me-auto" role="group"
						aria-label="Basic radio toggle button group">
						<input type="radio" class="btn-check" name="searchCountry"
							id="country-radio1" autocomplete="off" value=""> <label
							class="btn btn-outline-primary index-radio px-4" for="country-radio1">전체</label>
						<input type="radio" class="btn-check" name="searchCountry"
							id="country-radio2" value="대한민국" autocomplete="off"> <label
							class="btn btn-outline-primary index-radio px-4" for="country-radio2">국내</label>
						<input type="radio" class="btn-check" name="searchCountry"
							id="country-radio3" value="일본" autocomplete="off"> <label
							class="btn btn-outline-primary index-radio px-4" for="country-radio3">일본</label>
						<input type="radio" class="btn-check" name="searchCountry"
							id="country-radio4" value="미국" autocomplete="off"> <label
							class="btn btn-outline-primary index-radio px-4" for="country-radio4">미국</label>
						<input type="radio" class="btn-check" name="searchCountry"
							id="country-radio5" value="중국" autocomplete="off"> <label
							class="btn btn-outline-primary index-radio px-4" for="country-radio5">중국</label>
					</div>
					<div class="btn-group" role="group"
						aria-label="Basic radio toggle button group">
						<input type="radio" class="btn-check" name="order"
							id="order-radio1" autocomplete="off" value="최신순"> <label
							class="btn btn-outline-secondary index-radio" for="order-radio1">최신순</label>
						<input type="radio" class="btn-check" name="order"
							id="order-radio2" value="조회순" autocomplete="off"> <label
							class="btn btn-outline-secondary index-radio" for="order-radio2">조회순</label>
					</div>
				</div>
			</div>
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 py-4">
				<c:forEach items="${listData}" var="data">
					<div class="col mb-5">
						<div class="card h-100">
							<div class="position-relative overflow-hidden">
								<a href="amuseDetail?amuse_id=${data['amuse_id']}"> <img class="custom-card-img" src="${data['url']}"
									alt="..." />
								</a>
								<div data-id="${data['amuse_id']}"
									class="animate__animated position-absolute heart-wrap"
									style="width: 35px; top: 10px; right: 10px;">
									<i class="bi heart" style="font-size: 2rem; color: red;"></i>
								</div>
							</div>
							<div class="card-body p-4">
								<div class="text-center">${data['a_name']}</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<nav class="d-flex justify-content-center pb-5">
				<div class="btn-toolbar" role="toolbar"
					aria-label="Toolbar with button groups">
					<div class="btn-group" role="group" aria-label="Basic example">
						${listPage}</div>
				</div>
			</nav>
		</form>
	</main>
	
	<!-- 푸터 추가 -->
	<jsp:include page="./footer.jsp" />	

<!-- 위시리스트 추가 알림 토글 -->
<div class="position-fixed start-50 translate-middle-x" style="z-index: 11; bottom: 30px">
   <div id="liveToast" class="toast align-items-center text-white bg-primary border-0" role="alert" aria-live="assertive" aria-atomic="true" >
     <div class="toast-body text-center ">
       <p class="" style="font-size: 1.3rem; color: white;"><strong><i class="bi bi-balloon-heart-fill"></i>위시리스트에 추가되었어요</strong></p>
     </div>
   </div>
 </div>
 <div class="position-fixed start-50 translate-middle-x" style="z-index: 11; bottom: 30px">
   <div id="liveToast2" class="toast" role="alert" aria-live="assertive" aria-atomic="true" >
     <div class="toast-body text-center">
       <p style="font-size: 1.3rem; color: gray;"><strong><i class="bi bi-balloon-heart-fill"></i>위시리스트에서 제외되었어요</strong></p>
     </div>
   </div>
 </div>

</body>
</html>
