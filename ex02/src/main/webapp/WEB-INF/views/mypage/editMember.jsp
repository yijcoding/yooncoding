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
.editContainer {
	font-size: 18px;
	font-family: Dotum, "돋움", Helvetica, sans-serif;
}

@media ( min-width : 0px) {
	.editContainer {
		width: 800px;
	}
}
</style>

</head>
<body>

	<main class="container custom-main-padding border-bottom">
		<div class="row">
			<%@ include file="/WEB-INF/views/mypagemenu.jsp"%>
			<article class="col-9">
				<div class="row">
					<!--@@@@@@@@@@@@@ 각자 작성한 리스트 코드 넣는곳@@@@@@@@@@@@@@@ -->

					<div class="container mt-2 mx-5 mb-5 editContainer">
						<div class="row">
							<div class="col-7 text-left border-start border-4 border-info"
								style="font-weight: 1000; font-size: 30px">
								회원정보 <span style="color: rgb(48, 166, 202)">수정</span>
							</div>
						</div>


						<c:choose>
							<c:when test="${sessionScope.kakaoId ne null}">

								<div class="card mt-5">
									<div class="card-header">카카오프로필</div>
									<div class="card-body">
										<div class="row">
											<div class="ms-2 col-3" style="width: 100px">
												<img src="${sessionScope.thumbnail}" class="rounded-circle" style="width:70px; height:70px;">
											</div>
											<div class="col-8 my-auto">
												<div class="row">${dto.m_email}</div>
												<div class="row">${sessionScope.nickname}</div>
											</div>
										</div>
										<div class="row mt-2" id="imageUpload">
											<div class="col-10">
												<form action="/imageUpload" method="post"
													enctype="multipart/form-data" id="fileForm">
													<input type="file" name="file" id="file"> <input
														id="submitImage" type="submit" value="전송">
												</form>
											</div>
										</div>
									</div>
								</div>

							</c:when>
							<c:when test="${sessionScope.kakaoId eq null}">

								<div class="card mt-5">
									<div class="card-header">프로필</div>
									<div class="card-body">
										<div class="row">
											<div class="ms-2 col-3" style="width: 100px">
												<a id="imageClick" href="#"> <!-- <i class="bi bi-person-circle"
											style="font-size: 50px"></i>-->
												</a>
												<%-- <img src="resources/upload/mypage/${fileName}"> --%>
												<!-- <i class="bi bi-person-circle" style="font-size: 50px"></i> -->
											</div>
											<div class="col-8 my-auto">
												<div class="row">${dto.m_email}</div>
												<div class="row">${dto.member_id}</div>
											</div>
										</div>
										<div class="row mt-2" id="imageUpload">
											<div class="col-10">
												<form action="/imageUpload" method="post"
													enctype="multipart/form-data" id="fileForm">
													<input type="file" name="file" id="file"> <input
														id="submitImage" type="submit" value="전송">
												</form>
											</div>
										</div>
									</div>
								</div>

							</c:when>
						</c:choose>

						<div class="row mt-5 mx">
							<div class="col-12 border-bottom border-2 border-info pb-1"
								style="font-weight: 1000">필수 회원 정보</div>
						</div>

						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								이 름</div>
							<div class="col-1"></div>
							<div class="col-7 p-0">${dto.m_name}</div>
						</div>
						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								아이디</div>
							<div class="col-1"></div>
							<div class="col-7 p-0">${dto.member_id}</div>
						</div>
						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								비밀번호</div>
							<div class="col-1"></div>
							<input type="password" name="m_pass"
								class="col-4 border-1 rounded-1"
								style="height: 33px; font-size: 13px"></input>
							<div class="alertpw" id="alertpw"></div>
						</div>
						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								비밀번호 확인</div>
							<div class="col-1"></div>
							<input class="col-4 rounded-1 border-1" name="m_pass2"
								type="password" style="height: 33px; font-size: 13px"></input>
							<div class="alertpww" id="alertpww"></div>
						</div>
						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								휴대전화</div>
							<div class="col-1"></div>
							<input class="col-1 rounded-1 border-1" name="m_phone1"
								type="text" style="width: 60px; height: 33px; font-size: 13px"></input>
							<input class="col-2 mx-2 rounded-1 border-1" name="m_phone2"
								type="text" style="height: 33px; font-size: 13px"></input> <input
								class="col-2 rounded-1 border-1" name="m_phone3" type="text"
								style="height: 33px; font-size: 13px"></input>
							<div class="alertphone" id="alertphone"></div>
						</div>
						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								이메일</div>
							<div class="col-1"></div>
							<input class="col-2 border-1 rounded-1" name="m_email1"
								style="height: 33px; font-size: 13px"></input>
							<div class="col-1 text-center my-auto">@</div>
							<input class="col-2 border-1 rounded-1" name="m_email2"
								style="height: 33px; font-size: 13px"></input> <select
								class="col-2 ms-2 border-1 rounded-1" id="emailSelect"
								name="emailSelect" style="font-size: 12px; width: 110px">
								<option value="">-직접입력-</option>
								<option value="naver.com">naver.com</option>
								<option value="google.com">google.com</option>
								<option value="daum.net">daum.net</option>
							</select>
							<div class="alertemail" id="alertemail"></div>
						</div>
						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px;">
								주소</div>
							<div class="col-1"></div>
							<input class="col-5 border-1 rounded-1" id="m_address"
								name="m_address"
								style="width: 240px; height: 33px; font-size: 13px" readonly></input>
							<button class="col-2 ms-2 border border-secondary rounded-1"
								style="font-size: 14px" id="findAddress">주소찾기</button>
							<div class="alertaddress" id="alertaddress"></div>
						</div>

						<div class="row mt-5 mx">
							<div class="col-12 border-bottom border-2 border-info pb-1"
								style="font-weight: 600; font-size: 18px">추가 회원 정보</div>
						</div>

						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								성별</div>
							<div class="col-1"></div>
							<div class="col-3 px-0">
								<input type="radio" id="m_gender1" name="m_gender" value="남"
									checked> 남</input> <input type="radio" class="ms-3"
									id="m_gender2" name="m_gender" value="여"> 여</input>
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px;">
								생년월일</div>
							<div class="col-1"></div>
							<input class="col-1 border-1 rounded-1" id="m_year" name="m_year"
								style="width: 70px; font-size: 13px; height: 33px;"></input>
							<div class="col-1">년</div>
							<select class="col-1 border-1 rounded-1" id="m_month"
								name="m_month" style="width: 80px; font-size: 12px">
								<option value="0">-월-</option>
								<option value="01">1</option>
								<option value="02">2</option>
								<option value="03">3</option>
								<option value="04">4</option>
								<option value="05">5</option>
								<option value="06">6</option>
								<option value="07">7</option>
								<option value="08">8</option>
								<option value="09">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
							</select>
							<div class="col-1">월</div>
							<select class="col-1 border-1 rounded-1" id="m_day" name="m_day"
								style="width: 80px; font-size: 12px;">
								<option value="0">-일-</option>
								<option value="01">1</option>
								<option value="02">2</option>
								<option value="03">3</option>
								<option value="04">4</option>
								<option value="05">5</option>
								<option value="06">6</option>
								<option value="07">7</option>
								<option value="08">8</option>
								<option value="09">9</option>
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
						<div class="row" style="margin-top: 70px">
							<button class="col-3 mx-auto btn btn-info" type="button"
								id="editButton" style="width: 150px;">수정하기</button>
						</div>

						<div class="row mt-5 mx" style="margin-top: 70px">
							<div class="col-12 border-bottom border-2 pb-1"
								style="font-weight: 600; font-size: 18px"></div>
						</div>
						
						<form action="/withdrawal" method="post" onsubmit="return withdrawal();">
						<div class="row mt-3">
							<div class="col-3 border-end border-1" style="font-size: 17px">
								회원 탈퇴</div>
							<div class="col-1"></div>
							<input type="submit" class="col-2 rounded-1 btn-secondary"
								style="font-size: 14px" value="회원탈퇴">
						</div>
						</form>

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
	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<script>
	
		function withdrawal(){
			
			var result = confirm("회원탈퇴를 하시겠습니까?");
			if(result){
				return
			}else{
				return false;
			}
		}
		
		/* 페이지 시작되면 파일업로드 영역 감춘다. */
		$('#imageUpload').hide();

		/* 이미지를 클릭하면 업로드 버튼을 누르게 된다. */
		$('#imageClick').on('click', function(e) {
			e.preventDefault();
			let result = confirm("회원사진을 수정하시겠습니까?");

			if (!result) {
				return false;
			}

			$('#file').trigger('click');
		});

		/* 업로드 input value값에 변화가 오면 submit 버튼을 클릭한다. */
		$('#file').change(function() {
			$('#submitImage').trigger('click');
		});

		/* 업로드한 사진이 있을때 없을떄 분기 */
		var img = new Image();
		img.src = "resources/upload/mypage/${fileName}";
		img.onerror = function() {
			var m_image = '${dto.m_image}';
			/* $('#imageClick').html('<i class="bi bi-person-circle" style="font-size: 50px"></i>'); */
			if (m_image === 'no') {
				$('#imageClick')
						.html(
								'<i class="bi bi-person-circle" style="font-size: 50px"></i>');
			} else {
				$('#imageClick')
						.html(
								'<img src="resources/upload/mypage/${dto.m_image}" class="rounded-circle" style="width:70px; height:70px;">');
			}
		}
		img.onload = function() {
			alert("이미지가 있습니다.");
			$('#imageClick').html(
					'<img src="resources/upload/mypage/${fileName}">');

		}

		/* 클릭한 이메일 input에 표시 */
		$("select[name=emailSelect]").on("change", function() {
			console.log("this.val : " + $(this).val());
			$("input[name=m_email2]").val($(this).val());
		});

		/* 수정하기 버튼 클릭시에 form 동적으로 생성해서 submit */
		$("#editButton")
				.on(
						'click',
						function() {

							if ($('input[name=m_pass]').val() == '') {
								$('.alertpw')
										.html(
												"<font size='2' color='red'>필수 입력값 입니다.</font>");
								return false;
							}
							;
							if ($('input[name=m_pass2]').val() == '') {
								$('.alertpww')
										.html(
												"<font size='2' color='red'>필수 입력값 입니다.</font>");
								return false;
							}
							;
							if ($('input[name=m_phone1]').val() == '') {
								$('.alertphone')
										.html(
												"<font size='2' color='red'>필수 입력값 입니다.</font>");
								return false;
							}
							;
							if ($('input[name=m_phone2]').val() == '') {
								$('.alertphone')
										.html(
												"<font size='2' color='red'>필수 입력값 입니다.</font>");
								return false;
							}
							;
							if ($('input[name=m_phone3]').val() == '') {
								$('.alertphone')
										.html(
												"<font size='2' color='red'>필수 입력값 입니다.</font>");
								return false;
							}
							;
							if ($('input[name=m_address]').val() == '') {
								$('.alertaddress')
										.html(
												"<font size='2' color='red'>필수 입력값 입니다.</font>");
								return false;
							}
							;
							if ($('input[name=m_email1]').val() == '') {
								$('.alertemail')
										.html(
												"<font size='2' color='red'>필수 입력값 입니다.</font>");
								return false;
							}
							;
							if ($('input[name=m_email2]').val() == '') {
								$('.alertemail')
										.html(
												"<font size='2' color='red'>필수 입력값 입니다.</font>");
								return false;
							}
							;

							var newForm = document.createElement('form');
							// set attribute (form) 
							newForm.name = 'editForm';
							newForm.method = 'post';
							newForm.action = '/editMember';

							// create element (input)
							var m_pass = $("input[name=m_pass]").val();

							var m_email1 = $("input[name=m_email1]").val();
							var m_email2 = $("input[name=m_email2]").val();

							var m_year = $("input[name=m_year]").val();
							var m_month = $("select[name=m_month]").val();
							var m_day = $("select[name=m_day]").val();

							var m_phone1 = $("input[name=m_phone1]").val();
							var m_phone2 = $("input[name=m_phone2]").val();
							var m_phone3 = $("input[name=m_phone3]").val();

							var m_gender = $("input[name=m_gender]").val();

							var m_address = $("input[name=m_address]").val();

							var input1 = document.createElement('input');
							var input2 = document.createElement('input');
							var input3 = document.createElement('input');
							var input4 = document.createElement('input');
							var input5 = document.createElement('input');
							var input6 = document.createElement('input');
							var input7 = document.createElement('input');
							var input8 = document.createElement('input');
							var input9 = document.createElement('input');
							var input10 = document.createElement('input');
							var input11 = document.createElement('input');

							// set attribute (input)
							input1.setAttribute("type", "hidden");
							input1.setAttribute("name", "m_pass");
							input1.setAttribute("value", m_pass);

							input2.setAttribute("type", "hidden");
							input2.setAttribute("name", "m_email1");
							input2.setAttribute("value", m_email1);

							input3.setAttribute("type", "hidden");
							input3.setAttribute("name", "m_email2");
							input3.setAttribute("value", m_email2);

							input4.setAttribute("type", "hidden");
							input4.setAttribute("name", "m_address");
							input4.setAttribute("value", m_address);

							input5.setAttribute("type", "hidden");
							input5.setAttribute("name", "m_phone1");
							input5.setAttribute("value", m_phone1);

							input6.setAttribute("type", "hidden");
							input6.setAttribute("name", "m_phone2");
							input6.setAttribute("value", m_phone2);

							input7.setAttribute("type", "hidden");
							input7.setAttribute("name", "m_phone3");
							input7.setAttribute("value", m_phone3);

							input8.setAttribute("type", "hidden");
							input8.setAttribute("name", "m_gender");
							input8.setAttribute("value", m_gender);

							input9.setAttribute("type", "hidden");
							input9.setAttribute("name", "m_year");
							input9.setAttribute("value", m_year);

							input10.setAttribute("type", "hidden");
							input10.setAttribute("name", "m_month");
							input10.setAttribute("value", m_month);

							input11.setAttribute("type", "hidden");
							input11.setAttribute("name", "m_day");
							input11.setAttribute("value", m_day);

							// append input (to form)
							newForm.appendChild(input1);
							newForm.appendChild(input2);
							newForm.appendChild(input3);
							newForm.appendChild(input4);
							newForm.appendChild(input5);
							newForm.appendChild(input6);
							newForm.appendChild(input7);
							newForm.appendChild(input8);
							newForm.appendChild(input9);
							newForm.appendChild(input10);
							newForm.appendChild(input11);

							// append form (to body)
							document.body.appendChild(newForm);

							// submit form
							newForm.submit();

						});

		$("#findAddress").on('click', function() {
			goPopup();
		})

		// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
		//document.domain = "abc.go.kr";
		function goPopup() {
			// 주소검색을 수행할 팝업 페이지를 호출합니다.
			// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
			var pop = window.open("http://localhost:8080/juso", "pop",
					"width=570,height=420, scrollbars=yes, resizable=yes");

			// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
			//var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
		}
	</script>

</body>
</html>