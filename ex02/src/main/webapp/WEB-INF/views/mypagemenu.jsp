<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<aside class="col-3 ">
		<div class="row rounded shadow mb-5" style="height: 100px;">
			<div id="divimage">
				<i class="bi bi-person-circle" id="iimage"
					style="font-size: 2.5rem; color: cornflowerblue;">&nbsp;</i>${sessionScope.member_id}님
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
							<i class="bi bi-clipboard-check "></i>나의 활동 정보
						</button>
					</h2>
					<div id="mylist-collapse" class="accordion-collapse collapse"
						aria-labelledby="headingOne"
						data-bs-parent="#mylist-collapse-parent">
						<div class="accordion-body">
							<a href="/orderlist"
								class="d-block text-decoration-none text-dark p-0"><div
									class="rounded shadow mb-3 p-3 ">
									<i class="bi bi-cart"></i>구매 내역
								</div></a> <a href="/mypage/selectedmypage"
								class="d-block text-decoration-none text-dark p-0"><div
									class="rounded shadow mb-3 p-3 ">
									<i class="bi bi-bookmark-heart"></i>위시리스트
								</div></a> <a href="/writeList"
								class="d-block text-decoration-none text-dark p-0"><div
									class="rounded shadow mb-3 p-3 ">
									<i class="bi bi-chat-right-text"></i>댓글 리스트
								</div></a><a href="/refund"
								class="d-block text-decoration-none text-danger p-0"><div
									class="rounded shadow mb-3 p-3 ">
									<i class="bi bi-cart-dash-fill text-danger"></i>환불 신청
								</div></a>
						</div>
					</div>
				</div>
			</div>
			<a class="d-block text-decoration-none text-dark p-0"
				href="/editMember"><div class="rounded shadow mb-3 p-3">
					<i class="bi bi-person-gear"></i>내 정보 수정
				</div></a> <a class="d-block text-decoration-none text-dark p-0" href="/mypoint"><div
					class="rounded shadow mb-3 p-3">
					<i class="bi bi-credit-card"></i>나의 포인트
				</div></a>
		</div>
		<div class="row rounded shadow mb-5" style="height: 130px;">
			<a class="d-block text-decoration-none" href="/customer/faq?f_type=">
				<div class="rounded shadow my-3 p-2 bg-dark text-white ">
					<i class="bi bi-question-circle"></i>FAQ
				</div>
			</a> <a class="d-block text-decoration-none " href="/customer/announcementInquiry">
				<div class="rounded shadow mb-2 p-2 bg-dark text-white">
					<i class="bi bi-chat-dots"></i>체험단 문의하기
				</div>
			</a>
		</div>
		<a class="d-block text-decoration-none text-dark p-0" id="logoutLink"
			href="/logout"><div class="row rounded shadow p-3"
				style="height: 60px;">로그아웃</div></a>
	</aside>

	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script>
		Kakao.init('824fd1009e4c444d385c98e35623ee6e'); //발급받은 키 중 javascript키를 사용해준다.
		console.log(Kakao.isInitialized()); // sdk초기화여부판단

		function kakaoLogout() {
			if (Kakao.Auth.getAccessToken()) {
				alert("kakaoLogout() 옴");
				Kakao.API.request({
					url : '/v1/user/unlink',
					success : function(response) {
						console.log("response : " + response);
					},
					fail : function(error) {
						console.log("error : " + error)
					},
				})
				Kakao.Auth.logout();

				Kakao.Auth.setAccessToken(undefined);
			}
		}

		$(function() {
			const session_id = $('#idsession').val();
			console.log("session_id : " + session_id);
			const kakaoId = '${sessionScope.kakaoId}';
			console.log("kakaoId : " + kakaoId);

			if (kakaoId) {
				console.log("kakaoId is not null");
				$('#iimage').remove();
				$('#divimage')
						.append(
								'<img src="${sessionScope.thumbnail}" class="rounded-circle mt-2" style="width:50px; height:50px;">');
			} else if (session_id == null || session_id == "" || kakaoId == null) {
				console.log("session_id is null");
				alert("로그인 해주세요.");
				location.href = '/login';

			} else {
				console.log("session_id is not null");

				$
						.post(
								"/imageCheck",
								{
									member_id : session_id
								},
								function(data) {
									console.log("data : " + data);
									if (data == 'no') {
										$('#iimage')
												.attr("style",
														"font-size: 1.5rem; color: green;");
									} else if (data != 'no') {
										$('#iimage').remove();
										$('#divimage')
												.append(
														'<img src="/resources/upload/mypage/'+data+'" class="rounded-circle mt-2" style="width:50px; height:50px;">');
									}
								});

				$('#emoticon')
						.attr("style", "font-size: 1.5rem; color: green;");
				$('#loginLink').attr("href", "/mypage");

			}

			/* 전송하기 클릭시 카카오 로그아웃 한다. */
			$('#logoutLink').on('click', function(e) {
				e.preventDefault();
				kakaoLogout();
				location.href = '/logout';
			});

		});
	</script>


</body>
</html>