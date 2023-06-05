<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</style>
<title>Insert title here</title>
</head>
<body>
<header class="container mb-2">
		<div
			class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-2 border-bottom"
			style="height: 130px;">
			<div class="col-2 ">
				<a href="/"
					class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
					<img class="card-img"
					src="https://play-lh.googleusercontent.com/W9AN3AyNH7rgBaGO7Jv2MEMk2piGUEerZTZN7hG-xNJFq6QW1Dzs4HLukka0-oKIsw"
					alt="">
				</a>
			</div>
			<!-- 헤더 a태그 주소 넣을곳-->
<%-- 			<c:set var="member_id" value="kim" /> --%>
			<ul class="nav col-8 mb-2 justify-content-center mb-md-0">
				<li><a href="/customer/announcement" class="nav-link px-2 link-secondary">고객센터</a></li>
				<li><a href="promotion?member_id=kim" class="nav-link px-2 link-dark">프로모션</a></li>
				<li><a href="/board/board" class="nav-link px-2 link-dark">게시판</a></li>
				<li><a href="/list" class="nav-link px-2 link-danger">놀이공원</a></li>
				<c:if test="${m_admin eq 1}">
					<li><a href="/admin" class="nav-link px-2 link-danger">관리자</a></li>
				</c:if>
				
			</ul>
			<div class="col-2 ">
				<ul class="nav justify-content-end">
					<li class="nav-item">
						<button class="btn" type="button" data-bs-toggle="offcanvas"
							data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">
							<i class="bi bi-search pr-2"
								style="font-size: 1.5rem; color: cornflowerblue;"></i>
						</button>

						<div class="offcanvas offcanvas-end" tabindex="-1"
							id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
							<div class="offcanvas-header">
								<!-- 검색 -->
								<div class="offcanvas-title" id="offcanvasRightLabel">
									<form action="">
										<div class="row">
											<div class="col-2">
												<button class="btn" type="submit">
													<i class="bi bi-search pr-2"
														style="font-size: 1.5rem; color: cornflowerblue;"></i>
												</button>
											</div>
											<div class="col">
												<div class="form-floating mb-3">
													<input type="search" class="form-control"
														id="floatingInput" placeholder="놀이공원을 찾아볼까요?"> <label
														for="floatingInput">놀이공원을 찾아볼까요?</label>
												</div>
											</div>
										</div>
									</form>
								</div>
								<button type="button" class="btn-close"
									data-bs-dismiss="offcanvas" aria-label="Close"></button>
							</div>
							<div class="offcanvas-body">
								<div class="accordion" id="accordionPanelsStayOpenExample">
									<div class="accordion-item">
										<h2 class="accordion-header" id="panelsStayOpen-headingOne">
											<button class="accordion-button" type="button"
												data-bs-toggle="collapse"
												data-bs-target="#panelsStayOpen-collapseOne"
												aria-expanded="true"
												aria-controls="panelsStayOpen-collapseOne">
												<strong>국내 놀이공원</strong>
											</button>
										</h2>
										<div id="panelsStayOpen-collapseOne"
											class="accordion-collapse collapse show"
											aria-labelledby="panelsStayOpen-headingOne">
											<div class="accordion-body">
												<div class="row">
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>전국</a>
													</div>
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>서울</a>
													</div>
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>경기</a>
													</div>
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>대전</a>
													</div>
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>충청</a>
													</div>
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>대구</a>
													</div>
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>경북</a>
													</div>
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>부산</a>
													</div>
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>경남</a>
													</div>
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>광주</a>
													</div>
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>전라</a>
													</div>
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>제주</a>
													</div>
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>기타</a>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="accordion-item">
										<h2 class="accordion-header" id="panelsStayOpen-headingTwo">
											<button class="accordion-button collapsed" type="button"
												data-bs-toggle="collapse"
												data-bs-target="#panelsStayOpen-collapseTwo"
												aria-expanded="false"
												aria-controls="panelsStayOpen-collapseTwo">
												<strong>해외 놀이공원</strong>
											</button>
										</h2>
										<div id="panelsStayOpen-collapseTwo"
											class="accordion-collapse collapse"
											aria-labelledby="panelsStayOpen-headingTwo">
											<div class="accordion-body">
												<div class="row">
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>일본</a>
													</div>
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>미국</a>
													</div>
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>중국</a>
													</div>
													<div class="col-3">
														<a class="text-decoration-none text-dark" href=""><i
															class="bi bi-geo-alt"></i>기타</a>
													</div>
												</div>
											</div>
										</div>
									</div>

								</div>

							</div>
						</div>
					</li>
					
					<li id="loginBtn" class="nav-item">
					<!-- session 설정 되어있는지 안되어있는지 확인  -->
					<input type="hidden" id="idsession" name="idsession" value="${sessionScope.member_id}"/>
					<!-- kakaoLogin 했을시 정보 담기 -->
					<input type="hidden" id="kakaoId" name="kakaoId" value="${sessionScope.kakaoId}"/>
					<input type="hidden" id="nickname" name="nickname" value="${sessionScope.nickname}"/>
					<input type="hidden" id="thumbnail" name="thumbnail" value="${sessionScope.thumbnail}"/>
					
						<!-- 마이페이지 링크 --> <a class="nav-link" id="loginLink" href="/login"><i
							class="bi bi-person-circle"
							id="emoticon"
							style="font-size: 1.5rem; color: cornflowerblue;"></i></a>
					</li>
				</ul>
				
			</div>

		</div>
	</header>
<script>

/* .bi-person-circle 아이콘 클릭시 로그인 안되있으면 /login 으로 이동 || 로그인 되어있으면 mypage로 이동 */
/* session 여부와 kakao 로그인에 따른 image 변경 */ 
$(function(){
	const session_id = $('#idsession').val();
	const kakaoId = $('#kakaoId').val();
	const thumbnail = $('#thumbnail').val();
	const nickname = $('#nickname').val();
	/* const kakao_id = '${kakaoId}'; */
	console.log("session_id : "+session_id);
	console.log("kakaoId : "+ kakaoId);
	console.log("thumbnail : " +thumbnail);
	console.log("nickname : " +nickname);
		
	if(kakaoId){
		console.log("kakao_id is not null");
		const nickname = $('#nickname').val();
		const thumbnail = $('#thumbnail').val();
		
		$('#emoticon').remove();
  	    $('#loginLink').append('<img src="${sessionScope.thumbnail}" class="rounded-circle" style="width:40px; height:40px;">');
  	    console.log("뭐지");
  	    $('#loginLink').attr("href","/mypage");
  	  
	}else if(session_id == null || session_id == ""){
		console.log("session_id is null");
	}else{
		console.log("session_id is not null");
			
	   	   $.post("/imageCheck",{member_id:session_id},function(data){
	   	   		  console.log("data : " + data);
	   	   	      if (data == 'no'){
	   	   	   		 $('#emoticon').attr("style","font-size: 1.5rem; color: green;");
	   	   	      }else if(data != 'no'){
	   	   	    	 $('#emoticon').remove();
	   	   	    	 $('#loginLink').append('<img src="/resources/upload/mypage/'+data+'" class="rounded-circle" style="width:40px; height:40px;">');
	   	   	      }
	   	   	   }); 
		
		$('#emoticon').attr("style","font-size: 1.5rem; color: green;");
		$('#loginLink').attr("href","/mypage");
		
	}
});
</script>
</body>
</html>