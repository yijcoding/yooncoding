<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/menu.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>마이페이지</title>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
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

<!-- 회원정소 수정 style  -->
<style>
.container {
	font-size: 18px;
	font-family: Dotum, "돋움", Helvetica, sans-serif;
}
</style>

</head>
<body>

	<main class="container custom-main-padding border-bottom">
		<div class="row">
			<aside class="col-3 ">
				<div class="row rounded shadow mb-5" style="height: 100px;">
					<div>
						<i class="bi bi-person-circle"
							style="font-size: 2.5rem; color: cornflowerblue;">&nbsp;</i>${member_id}님
						환영합니다
					</div>
				</div>
				<div class="row mb-5" style="height: auto;">
					<div class="accordion p-0 mb-3 accordion-flush"
						id="mylist-collapse-parent">
						<div class="accordion-item">
							<h2 class="accordion-header rounded shadow border-0 "
								id="headingOne">
								<button class="accordion-button collapsed rounded p-3"
									type="button" data-bs-toggle="collapse"
									data-bs-target="#mylist-collapse" aria-expanded="true"
									aria-controls="collapseOne">
									<i class="bi bi-clipboard-check "></i>나의 신청리스트
								</button>
							</h2>
							<div id="mylist-collapse" class="accordion-collapse collapse"
								aria-labelledby="headingOne"
								data-bs-parent="#mylist-collapse-parent">
								<div class="accordion-body">
									<a href="" class="d-block text-decoration-none text-dark p-0">
										<div class="rounded shadow mb-3 p-3 ">
											<i class="bi bi-cart"></i>신청한 놀이공원
										</div>
									</a> <a href="/mypage/selectedmypage"
										class="d-block text-decoration-none text-dark p-0"><div
											class="rounded shadow mb-3 p-3 ">
											<i class="bi bi-bookmark-heart"></i>위시리스트
										</div></a> <a href="/writeList"
										class="d-block text-decoration-none text-dark p-0"><div
											class="rounded shadow mb-3 p-3 ">
											<i class="bi bi-chat-right-text"></i>댓글 리스트
										</div></a>
								</div>
							</div>
						</div>
					</div>
					<a class="d-block text-decoration-none text-dark p-0"
						href="/editMember"><div class="rounded shadow mb-3 p-3">
							<i class="bi bi-person-gear"></i>내 정보 수정
						</div></a> <a class="d-block text-decoration-none text-dark p-0" href=""><div
							class="rounded shadow mb-3 p-3">
							<i class="bi bi-credit-card"></i>나의 포인트
						</div></a>
				</div>
				<div class="row rounded shadow mb-5" style="height: 130px;">
					<a class="d-block text-decoration-none" href="">
						<div class="rounded shadow my-3 p-2 bg-dark text-white ">
							<i class="bi bi-question-circle"></i>FAQ
						</div>
					</a> <a class="d-block text-decoration-none " href="">
						<div class="rounded shadow mb-2 p-2 bg-dark text-white">
							<i class="bi bi-chat-dots"></i>체험단 문의하기
						</div>
					</a>
				</div>
				<a class="d-block text-decoration-none text-dark p-0" href=""><div
						class="row rounded shadow p-3" style="height: 60px;">로그아웃</div></a>
			</aside>
			<article class="col-9">
				<div class="row">
					<!--@@@@@@@@@@@@@ 각자 작성한 리스트 코드 넣는곳@@@@@@@@@@@@@@@ -->

					<div class="container mt-5 mx-5 mb-5">
						<div class="row">
							<div class="col-7 text-left border-start border-4 border-info"
								style="font-weight: 1000; font-size: 30px">
								회원정보 <span style="color: rgb(48, 166, 202)">수정</span>
							</div>
						</div>

						<div class="card mt-5">
							<div class="card-header">프로필</div>
							<div class="card-body">
								<div class="row">
									<div class="ms-2 col-3" style="width: 100px">
										<i class="bi bi-person-circle" style="font-size: 50px"></i>
									</div>
									<div class="col-8 my-auto">
										<div class="row">${m_email}</div>
										<div class="row">${member_id}</div>
									</div>
								</div>
							</div>
						</div>

						<div class="row mt-5 mx">
							<div class="col-12 border-bottom border-2 border-info pb-1"
								style="font-weight: 1000">필수 회원 정보</div>
						</div>

						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								이 름</div>
							<div class="col-1"></div>
							<div class="col-7 p-0">${m_name}</div>
						</div>
						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								아이디</div>
							<div class="col-1"></div>
							<div class="col-7 p-0">${member_id}</div>
						</div>
						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								비밀번호</div>
							<div class="col-1"></div>
							<input type="password" class="col-4 border-1 rounded-1"
								style="font-size: 17px"></input>
						</div>
						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								비밀번호 확인</div>
							<div class="col-1"></div>
							<input class="col-4 rounded-1 border-1" type="password"></input>
						</div>
						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								휴대전화</div>
							<div class="col-1"></div>
							<input class="col-1 rounded-1 border-1" type="text"
								style="width: 60px"></input> <input
								class="col-2 mx-2 rounded-1 border-1" type="text"></input> <input
								class="col-2 rounded-1 border-1" type="text"></input>
						</div>
						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								이메일</div>
							<div class="col-1"></div>
							<input class="col-2 border-1 rounded-1"></input>
							<div class="col-1 text-center my-auto">@</div>
							<input class="col-2 border-1 rounded-1"></input> <select
								class="col-2 ms-2 border-1 rounded-1"
								style="font-size: 12px; width: 110px">
								<option value="">-직접입력-</option>
								<option value="naver.com">naver.com</option>
								<option value="google.com">google.com</option>
								<option value="daum.net">daum.net</option>
							</select>
						</div>
						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px;">
								주소</div>
							<div class="col-1"></div>
							<input class="col-5 border-1 rounded-1" id="m_address"
								style="width: 240px;"></input>
							<button class="col-2 ms-2 border border-secondary rounded-1"
								style="font-size: 14px" onclick="goPopup()">주소찾기</button>
						</div>

						<div class="row mt-5 mx">
							<div class="col-12 border-bottom border-2 pb-1"
								style="font-weight: 600; font-size: 18px">추가 회원
								정보</div>
						</div>

						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								성별</div>
							<div class="col-1"></div>
							<div class="col-3 px-0">
								<input type="radio" id="sex" name="sex" value="1" checked>
								남</input> <input type="radio" class="ms-3" id="sex" name="sex" value="2">
								여</input>
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								생년월일</div>
							<div class="col-1"></div>
							<input class="col-1 border-1 rounded-1" style="width: 70px;"></input>
							<div class="col-1">년</div>
							<select class="col-1 border-1 rounded-1" name="month"
								style="width: 80px; font-size: 12px">
								<option value="0">-월-</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
							</select>
							<div class="col-1">월</div>
							<select class="col-1 border-1 rounded-1" name="day"
								style="width: 80px; font-size: 12px;">
								<option value="0">-일-</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
								<option value="21">21</option>
								<option value="22">22</option>
								<option value="23">23</option>
								<option value="24">24</option>
								<option value="25">25</option>
								<option value="26">26</option>
								<option value="27">27</option>
								<option value="28">28</option>
								<option value="29">29</option>
								<option value="30">30</option>
								<option value="31">31</option>
							</select>
							<div class="col-1">일</div>
						</div>

						<div class="row mt-5">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								회원 탈퇴</div>
							<div class="col-1"></div>
							<button type="button" class="col-2 rounded-1 btn-secondary"
								style="font-size: 14px">회원탈퇴</button>
						</div>
						<div class="row mt-2">
							<div class="col-1" style="width: 10px"></div>
							<div class="col-11 text-muted" style="font-size: 12px">
								* 탈퇴 신청 즉시 <span style="color: brown">자유이용권,프로모션 등 유료 서비스</span>에
								대한 정보와 작성된 게시물이 삭제됩니다.
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
	</main>
	<footer class="container mb-5">
		<div class="row">
			<div class="col-3">
				<ul class="list-group list-group-flush">
					<li class="list-group-item">고객지원실 운영안내</li>
					<li class="list-group-item">월~금 09:30~06:30(점심시간 13:15~14:30)
					</li>
					<li class="list-group-item">주말/공휴일 제외, 한국시간 기준</li>
				</ul>
			</div>
			<div class="col-2 offset-3">
				<p>소개</p>
			</div>
			<div class="col-2">
				<p>파트너</p>
			</div>
			<div class="col-2">
				<p>지원</p>
			</div>
		</div>
	</footer>

	<script>
    		// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
		//document.domain = "abc.go.kr";
		function goPopup() {
			// 주소검색을 수행할 팝업 페이지를 호출합니다.
			// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
			var pop = window.open(
					"http://localhost:8080/juso",
					"pop",
					"width=570,height=420, scrollbars=yes, resizable=yes");

			// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
			//var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
		}

		function jusoCallBack(roadFullAddr) {
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
			$('#address').html(roadFullAddr);
			/* document.form.roadFullAddr.value = roadFullAddr; */

		}

  </script>

</body>
</html>