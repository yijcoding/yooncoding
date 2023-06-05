<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>나의 위시리스트</title>
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
crossorigin="anonymous">
<script
src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
crossorigin="anonymous"></script>
<!-- 폰트 설정 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
href="https://fonts.googleapis.com/css2?family=Hahmlet&display=swap"
rel="stylesheet">
<!-- 아이콘 cdn-->
<link rel="stylesheet"
href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<!-- 애니메이션 cdn-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
  
<!-- nav 테스트 -->  
<!-- 내가만든 css -->
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
	height: 200px;
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
<!-- 로딩 애니메이션  -->
<script type="text/javascript" src="resources/js/loading.js"></script>
<!-- 즐겨찾기 하트 애니메이션 이벤트 -->
<script>
  $(function(){
    <!-- 일정시간 이벤트 막는 변수 -->
   	let isClickable = true;
   	
   	<!-- amuse_id 선언 -->
   	var amuse_id;
   	<!-- member_id el로 받아옴 -->
   	var member_id = "${member_id}";
   	console.log(member_id);
   	
  	/* if(member_id == "") {
   		alert('로그인X');
   	} else {
   		alert('로그인O');
   	} */
  	
    <!-- 위시리스트 추가 알림 선택자 -->
    var toastLiveExample = document.getElementById('liveToast');
    var toastLiveExample2 = document.getElementById('liveToast2')
    var toast = new bootstrap.Toast(toastLiveExample);
    var toast2 = new bootstrap.Toast(toastLiveExample2); 
    
	  <!-- 더보기 버튼이 눌린 뒤 다시 heartWraps받아옴 -->
	  <!-- 하트 누르면 즐겨찾기 등록 및 해제 --> 
	  $('#trigger').on('click',function() {
		  console.log('트리거 작동');
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
	  });
  });
</script>
<!-- ajax 페이지네이션 -->
<script type="text/javascript">
var heartWraps;
$(function(){
	var selected_id;
	<!-- 페이지 진입 시 즐겨찾기 목록 불러옴 -->
	$.ajax({
		url : "/selectedapi/selectedlist", // 서버의 URL
		type : "GET", // HTTP 요청 방식
		dataType : "json", // 서버가 반환하는 데이터 타입
		data : {
		},
		success : function(selectedData) {
			var str = '';
			for(var data of selectedData){
				str += '<div class="col mb-5">';
				str += '<div class="card h-100">';
				str += '<div class="position-relative overflow-hidden">';
				str += `<a href="amuseDetail?amuse_id=${'${data["amuse_id"]}'}">`; //수정
				str += `<img class="custom-card-img" src="${'${data["url"]}'}"/>`; // 수정
				str += '</a>';
				str += `<div data-id="${'${data["amuse_id"]}'}" class="animate__animated position-absolute heart-wrap" style="width: 35px; top: 10px; right: 10px;">`;
				str += '<i class="bi bi-balloon-heart-fill heart" style="font-size: 2rem; color: red;"></i>';
				str += '</div>';
				str += '</div>';
				str += '<div class="card-body p-4">';
				str += `<div class="text-center">${'${data["a_name"]}'}</div>`; //수정
				str += '</div> </div> </div>'; 
				// for문 마지막 selected_id 뽑아냄(controller로 보낼 id값)
				selected_id = `${'${data["selected_id"]}'}`;
			} 
			$('#insert-div').append(str);
			heartWraps = document.querySelectorAll('.heart-wrap');
			console.log(heartWraps);
			$('#trigger').trigger('click');
		},
		error : function(xhr, status, error) {
			console.log("에러");
		}
	});
	<!-- 리스트 갯수가 6개보다 많으면 더보기 버튼 추가 -->
	$.ajax({
		url : "/selectedapi/selectedcount", // 서버의 URL
		type : "POST", // HTTP 요청 방식
		dataType : "json", // 서버가 반환하는 데이터 타입
		data : {
		},
		success : function(countData) {
			if(countData > 6) {
				var bstr = '';
				bstr += '<button type="button" id="add-list-btn" class="btn btn-light">더보기</button>';
				$('#insert-btn').append(bstr);
			}
		},
		error : function(xhr, status, error) {
			console.log("에러");
		}
	});
	<!-- 버튼이 로드된 후에 이벤트 인식-->
	setTimeout(() => {
	<!-- 버튼 누를 시 다음 페이지목록 불러옴 -->
	$('#add-list-btn').on('click', function() {
		<!-- 리스트 갯수가 6개보다 부족하면 더보기 버튼 숨김 -->
		$.ajax({
			url : "/selectedapi/selectedcount", // 서버의 URL
			type : "POST", // HTTP 요청 방식
			dataType : "json", // 서버가 반환하는 데이터 타입
			data : {
				"selected_id" : selected_id
			},
			success : function(countData) {
				if(countData <= 6) {
					$('#add-list-btn').hide();
				}
			},
			error : function(xhr, status, error) {
				console.log("에러");
			}
		});
		$.ajax({
			url : "/selectedapi/selectedlist", // 서버의 URL
			type : "POST", // HTTP 요청 방식
			dataType : "json", // 서버가 반환하는 데이터 타입
			data : {
				"selected_id" : selected_id
			},
			success : function(selectedData) {
				var str = '';
				for(var data of selectedData){
					str += '<div class="col mb-5">';
					str += '<div class="card h-100">';
					str += '<div class="position-relative overflow-hidden">';
					str += `<a href="amuseDetail?amuse_id=${'${data["amuse_id"]}'}">`; 
					str += `<img class="custom-card-img" src="${'${data["url"]}'}"/>`; 
					str += '</a>';
					str += `<div data-id="${'${data["amuse_id"]}'}" class="animate__animated position-absolute heart-wrap" style="width: 35px; top: 10px; right: 10px;">`;
					str += '<i class="bi bi-balloon-heart-fill heart" style="font-size: 2rem; color: red;"></i>';
					str += '</div>';
					str += '</div>';
					str += '<div class="card-body p-4">';
					str += `<div class="text-center">${'${data["a_name"]}'}</div>`; //수정
					str += '</div> </div> </div>'; 
					// for문 마지막 selected_id 뽑아냄(controller로 보낼 id값)
					selected_id = `${'${data["selected_id"]}'}`;
				}
				
				$('#insert-div').append(str);
				heartWraps = document.querySelectorAll('.heart-wrap');
				$('#trigger').trigger('click');
			},
			error : function(xhr, status, error) {
				console.log("에러");
			}
		});
		
	});
	}, 100);
});
</script>

</head>
<body>
<!-- 메뉴(헤더) 추가 -->
<jsp:include page="../menu.jsp" />

  <main class="container custom-main-padding border-bottom">
    <div class="row">
      <jsp:include page="../mypagemenu.jsp" />
      <article class="offset-1 col-8">
			<h2>위시리스트</h2>
			<div
				class="row gx-4 gx-lg-5 row-cols-1 row-cols-md-2 row-cols-xl-3 py-4" id="insert-div">
				<!-- ajax로 페이징된 코드 추가됨 -->
				<!-- 하트모양 클릭에서 트리거로 사용될 input태그 -->
				<input type="hidden" id="trigger">
			</div>
			<div class="row"><div class="offset-5 col-2" id="insert-btn"></div></div>
      </article>
    </div>  
  </main>
  
  <!-- 푸터 추가 -->
<jsp:include page="../footer.jsp" />
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