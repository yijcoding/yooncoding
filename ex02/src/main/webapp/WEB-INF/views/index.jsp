<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>index</title>
<!-- jquery cdn -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"
	integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
	crossorigin="anonymous"></script>

<script src="${path}/resources/amuse/slick/slick.min.js"></script>
<link rel="stylesheet" href="${path}/resources/amuse/slick/slick.css" />
<link rel="stylesheet" href="${path}/resources/amuse/slick/slick-theme.css" />
	
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

<script src="/resources/amuse/script/test.js"></script>
<link href="/resources/amuse/css/test.css" rel="stylesheet"/> 

<!-- 내가만든 css -->
<style type="text/css">
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
.custom-focus{ transition: 0.3s; }
.custom-focus:hover{ 
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
   	console.log(member_id);
   	
  	/* if(member_id == "") {
   		alert('로그인X');
   	} else {
   		alert('로그인O');
   	} */
     
    var heartWraps = document.querySelectorAll('.heart-wrap');
    
    <!-- 즐겨찾기 체크 후에 하트 채우기 or 비우기 -->
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
<!--  -->
<script>
$(function() {
    $('#closeBtn').click(function() {
        $('#my-modal').modal('hide');
        var chkVal = $("input:checkbox[id=inactiveToday]:checked").val();
        $.ajax({
            url : '/indexapi/PopupCookie',
            type : 'POST',
            data : {inactiveToday : chkVal},
            dataType : "text",
            success : function(resData) {
            	 console.log(resData);
            },
						error : function(xhr, status, error) {
							console.log("에러");
						}
        });
    });
});
</script>
<!-- 모달 보이기 -->
<script type="text/javascript">
$(function() {
	cookieCheck = "${cookie.PopupClose.value ne 'off'}";
	if(cookieCheck == "true") {
		$("#my-modal").modal("show");
	} 
});
</script>
</head>
<body>
<!-- 메뉴(헤더) 추가 -->
	<jsp:include page="./menu.jsp" />

	<main class="container custom-main-padding border-bottom">
		<div class="row">
      <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
        <c:forEach var="fData" items="${promotionData}">
          <div class="carousel-item active" >
            <img class="d-block w-100 custom-carousel-img" src="${fData.promotion_img}" alt="...">
          </div>
         </c:forEach>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </div>

		<hr>
		<div class="row pt-5 pb-2">
			<h4>
				<a class="text-decoration-none text-dark" href="#">국내 놀이공원</a>
			</h4>
		</div>
		
		<div class="album py-5 bg-light ">
			<div class="container">
					<div class="row g-5 ">
			<c:forEach items="${koreaData}" var="kData">
						<div class="col-3">
								<div class="card shadow-sm">
								<div class="position-relative overflow-hidden">
									<a href="/amuseDetail?amuse_id=${kData['amuse_id']}" class="text-decoration-none text-dark">
										<img
											src="${kData['url']}"
											class="custom-card-img custom-focus" alt="...">
									</a>
									<!-- 하트 아이콘 -->
								  <div data-id="${kData['amuse_id']}" class="animate__animated position-absolute heart-wrap" style="width: 35px; top: 10px; right: 10px;">
	                	<i class="bi heart" style="font-size: 2rem; color: red;" ></i>
	              	</div>
								</div>
									<div class="card-body custom-card-body-height overflow-hidden">
										<h5 class="card-title">${kData['a_name']}</h5>
										<p class="card-text">${kData['a_country']}</p>
									</div>
								</div>
						</div>
				</c:forEach>
					</div>
			</div>
		</div>

		<hr>
		<div class="row pt-5 pb-2">
			<h4>
				<a class="text-decoration-none text-dark" href="#">해외 놀이공원</a>
			</h4>
		</div>
		
		<div class="album py-5 bg-light ">
			<div class="container">
					<div class="row g-5 ">
			<c:forEach items="${foreignData}" var="fData">
						<div class="col-3">
								<div class="card shadow-sm">
								<div class="position-relative overflow-hidden">
									<a href="/amuseDetail?amuse_id=${fData['amuse_id']}" class="text-decoration-none text-dark ">
										<img
											src="${fData['url']}"
											class="custom-card-img custom-focus" alt="...">
									</a>
									<!-- 하트 아이콘 -->
								  <div data-id="${fData['amuse_id']}" class="animate__animated position-absolute heart-wrap" style="width: 35px; top: 10px; right: 10px;">
	                	<i class="bi heart" style="font-size: 2rem; color: red;" ></i>
	              	</div>
								</div>
									<div class="card-body custom-card-body-height overflow-hidden">
										<h5 class="card-title">${fData['a_name']}</h5>
										<p class="card-text">${fData['a_country']}</p>
									</div>
								</div>
						</div>
				</c:forEach>
					</div>
			</div>
		</div>
</main>

<div class="modal" id="my-modal" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">이벤트 공지사항</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body text-center">
        <a href="/promotion"><img class="img-responsive"src="https://openimage.interpark.com/tourpark/tour/event/2017/170614_china/181106_china_01.jpg" 
        style="width : 450px;"></a>
      </div>
      <div class="modal-footer">
      <form name="popFrm">
        	<input type="checkbox" class="btn" id="inactiveToday" value="1" />
	        하루 동안 열지 않음&nbsp&nbsp
	        <input type="button" class="btn btn-dark" value="닫기" id="closeBtn" />
	    </form>
      </div>
    </div>
  </div>
</div>
  <!-- 푸터 추가 -->
<jsp:include page="./footer.jsp" />

<!-- 위시리스트 추가 알림 토글 -->
<div class="position-fixed start-50 translate-middle-x" style="z-index: 11; bottom: 30px">
  <div id="liveToast" class="toast align-items-center text-white bg-primary border-0" role="alert" aria-live="assertive" aria-atomic="true" data-bs-delay="1200">
    <div class="toast-body text-center ">
      <p class="" style="font-size: 1.3rem; color: white;"><strong><i class="bi bi-balloon-heart-fill"></i>위시리스트에 추가되었어요</strong></p>
    </div>
  </div>
</div>
<div class="position-fixed start-50 translate-middle-x" style="z-index: 11; bottom: 30px">
  <div id="liveToast2" class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-bs-delay="1200">
    <div class="toast-body text-center">
      <p style="font-size: 1.3rem; color: gray;"><strong><i class="bi bi-balloon-heart-fill"></i>위시리스트에서 제외되었어요</strong></p>
    </div>
  </div>
</div>

</body>
</html>