<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<!-- 내 댓글보기 스타일  -->
<style>
.viewBtn, .locateBtn {
	background-color: #e6f2ff;
	color: #337ab7;
	border: none;
	border-radius: 5px;
	font-size: 14px;
	height: 30px;
	padding: 5px 10px;
}

.viewBtn:hover, .locateBtn:hover {
	background-color: #ccebff;
}


.container-sm {
	font-size: 14px;
	font-family: Dotum, '돋움', Helvetica, sans-serif;
}


</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/menu.jsp"%>
	<main class="container custom-main-padding border-bottom">
		<div class="row">
	<%@ include file="/WEB-INF/views/mypagemenu.jsp"%>
			<article class="col-9">
				<div class="row">
					<!--@@@@@@@@@@@@@ 각자 작성한 리스트 코드 넣는곳@@@@@@@@@@@@@@@ -->
					
					<!-- 내댓글보기  -->
						<div class="container-sm mt-1 px-4">
							<div class="row w-80 m-auto pt-3 bg-info rounded-0"
								style="height: 54px">
								<div class="w-50 mx-auto text-center h5"
									style="color: #fff; font-weight: 600">게시글/댓글 모음</div>
							</div>
							<div class="row w-80 m-auto mt-4">
								<span>${list[0].member_id} 님의 게시글 모음</span>
							</div>
							<div class="row w-80 m-auto mt-1 text-muted">
								<span style="font-size: 12px;">*총 ${total}개의 게시글 작성</span>
							</div>
							<div class="m-auto w-80 mt-1 border-bottom border-2"></div>
							<ul class="nav w-80 m-auto nav-tabs mt-2">
								<li class="nav-item mt-1"><a class="nav-link active"
									id="latest" aria-current="page" href="#">최신순</a></li>
								<li class="nav-item mt-1"><a class="nav-link" id="past"
									href="#">과거순</a></li>
							</ul>


							<div class="all" id="all">
								<c:forEach var="item" items="${list}" varStatus="status">
									<div class="card-body pe-0 m-auto w-80">
										<div class="row my-3">
											<div class="col-8">
												<h5 class="card-title" style="font-weight: 600">
													${item.b_title}</h5>
											</div>
											<div class="col-4">
												<p class="text-center" id="category${status.index}" style="font-size: 12px">${item.category}</p>
											</div>
											<div class="card-text content${status.index}">${item.b_content}</div>
											<div class="row text-muted">
												<div class="col-4 px-3 pt-2" style="font-size: 12px">${item.postdate}</div>
												<div class="col-8 ps-5 d-flex justify-content-end">
													<input class="locateBtn border border-info rounded-0" id="locationTowriting${status.index}" 
														style="height: 30px" type="button" value="작성한곳으로 이동">
													<input type="hidden" id="view_id${status.index}" value="${item.view_id}"/>
												</div>
											</div>
										</div>
									</div>
									<div class="border-bottom m-auto w-80"></div>
								</c:forEach>

								<div class="row pagingDiv">
									<div class="paging my-5 m-auto">
										<div class='pull-right'>
											<!-- justify-content-center는 row가 적용된 콘테이너 안에서만 적용되는듯 하다. row가 없으면 적용이 안된다.  -->
											<nav class="d-flex justify-content-center"
												aria-label="Page navigation">
												<ul class='pagination pagination-sm'>
													<c:if test="${pageMaker.cri.pageNum ne 1}">
														<li class="page-item paginate_button fistPage"><a
															class="page-link" id="page-link" href="1">처음</a></li>
													</c:if>
													<c:if test="${pageMaker.prev}">
														<li class="page-item paginate_button previous"><a
															class="page-link" id="page-link"
															href="${pageMaker.startPage -1}"> &lt;&lt; </a></li>
													</c:if>

													<!-- 정수를 begin 부터 end 까지 증가시키고 그떄마다 num에 저장하는 구조인가 보다. -->
													<c:forEach var="num" begin="${pageMaker.startPage}"
														end="${pageMaker.endPage}">
														<li
															class='page-item paginate_button ${pageMaker.cri.pageNum == num ? "active" : ""}'><a
															href="${num}" class="page-link" id="page-link">${num}</a></li>
													</c:forEach>

													<c:if test="${pageMaker.next}">
														<li class="page-item paginate_button next"><a
															href="${pageMaker.endPage+1}" class="page-link"
															id="page-link">>></a></li>
													</c:if>
												</ul>
											</nav>
										</div>
									</div>
								</div>
							</div>
						</div>

						<form id='actionForm' method='get'>
							<input type='hidden' name='pageNum' id='pageNum'
								value='${pageMaker.cri.pageNum}'> <input type='hidden'
								name='amount' id='amount' value='${pageMaker.cri.amount}'>
							<input type='hidden' name='member_id' id='member_id'
								value='${pageMaker.cri.member_id}'>
						</form>

					<!-- 내 댓글보기 끝 -->


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

	<!-- 내 댓글보기 스크립트 -->
	<script>
		
		/* 리스트 content의 len보다 긴 문자열을 자름  */
		function cutString(len) {

			for (i = 0; i < 10; i++) {
				var content = $(".content" + i).html();
				var text = textLengthOverCut(content, len, "...");

				$(".content" + i).html(text);
			}
		}

		function textLengthOverCut(text, len, lastText) {
			if (len == "" || len == null) { // 기본값
				len = 10;
			}
			if (lastText == "" || lastText == null) { // 기본값
				lastText = "...";
			}
			if (text == "" || text == null) {
				return text;
			}
			if (text.length > len) {
				text = text.substr(0, len) + lastText;
			}
			return text;
		}

		function locationTowriting(i) {
			
			var category = $("#category"+i).html();

			console.log("category : " + category);

			if (category === "자유게시판") {
				var board_id = $('#view_id'+i).val();
				location.href = "/board/view?board_id="+board_id;
			} else if (category === "문의게시판") {
				var inquiry_num = $('#view_id'+i).val();
				location.href = "/customer/consultationView?inquiry_num="+inquiry_num;
			} else if (category === "게시판 댓글") {
				var board_id = $('#view_id'+i).val();
				location.href = "/board/view?board_id="+board_id;
			}
		}
		
		
		/* 최신순에서 paging 번호 눌렀을때 이동 */
		$(function() {
			
			cutString(10);

			$(".all").on("click", "#locationTowriting0", function() {
				console.log("locationToWriting click event 진입");
				locationTowriting(0);
			});
			$(".all").on("click", "#locationTowriting1", function() {
				console.log("locationToWriting click event 진입");
				locationTowriting(1);
			});
			$(".all").on("click", "#locationTowriting2", function() {
				console.log("locationToWriting click event 진입");
				locationTowriting(2);
			});
			$(".all").on("click", "#locationTowriting3", function() {
				console.log("locationToWriting click event 진입");
				locationTowriting(3);
			});
			$(".all").on("click", "#locationTowriting4", function() {
				console.log("locationToWriting click event 진입");
				locationTowriting(4);
			});
			$(".all").on("click", "#locationTowriting5", function() {
				console.log("locationToWriting click event 진입");
				locationTowriting(5);
			});
			$(".all").on("click", "#locationTowriting6", function() {
				console.log("locationToWriting click event 진입");
				locationTowriting(6);
			});
			$(".all").on("click", "#locationTowriting7", function() {
				console.log("locationToWriting click event 진입");
				locationTowriting(7);
			});
			$(".all").on("click", "#locationTowriting8", function() {
				console.log("locationToWriting click event 진입");
				locationTowriting(8);
			});
			$(".all").on("click", "#locationTowriting9", function() {
				console.log("locationToWriting click event 진입");
				locationTowriting(9);
			});

			/*class paginate_button 의 형제요소 a tag 선택  */
			$(".all").on(
					"click",
					"#page-link",
					function(e) {
						console.log('click');
						/*a tag를 클릭했을때 page이동을 막는다.  */
						e.preventDefault();

						var actionForm = $("#actionForm");

						/* 여기서 this는 클릭했을때 요소 a tag를 말한다.  */
						actionForm.find("input[name='pageNum']").val(
								$(this).attr("href"));

						/* actionForm.attr("action","/latestPost");
						actionForm.submit(); */

						$.ajax({
							url : "/latestPost",
							type : "post",
							/* dataType : "text", */
							data : {
								pageNum : $("input[name='pageNum']").val(),
								amount : $("input[name='amount']").val()
							},
						}).done(function(result) {
							console.log("결과확인");

							$(".nav-tabs").siblings('.card-body').detach();
							$(".pagingDiv").detach();
							$("#actionForm").detach();

							var html = jQuery('<div>').html(result);
							console.log("html : " + html);
							var contents = html.find("div.whole").html();
							console.log("contents : " + contents);

							/* $(".nav-tabs").after(contents); */
							$(".all").html(contents);

							cutString(10);

						}).fail(function(jqXHR, textStatus, errorThrown) {
							console.log("에러");
							console.log(jqXHR);
							console.log(textStatus);
							console.log(errorThrown);
						});

					});

			/* 과거순에서 paging 번호 눌렀을때 이동 */
			$(".all").on(
					"click",
					"#page-link-asc",
					function(e) {
						console.log('click');
						/*a tag를 클릭했을때 page이동을 막는다.  */
						e.preventDefault();

						var actionForm = $("#actionForm");

						/* 여기서 this는 클릭했을때 요소 a tag를 말한다.  */
						actionForm.find("input[name='pageNum']").val(
								$(this).attr("href"));
						/* actionForm.attr("action","/pastPost");
						actionForm.submit(); */

						$.ajax({
							url : "/pastPost",
							type : "post",
							/* dataType : "text", */
							data : {
								pageNum : $("input[name='pageNum']").val(),
								amount : $("input[name='amount']").val()
							},
						}).done(function(result) {
							console.log("결과확인");

							$(".nav-tabs").siblings('.card-body').detach();
							$(".pagingDiv").detach();
							$("#actionForm").detach();

							var html = jQuery('<div>').html(result);
							console.log("html : " + html);
							var contents = html.find("div.whole").html();
							console.log("contents : " + contents);

							/* $(".nav-tabs").after(contents); */
							$(".all").html(contents);

							cutString(10);

						}).fail(function(jqXHR, textStatus, errorThrown) {
							console.log("에러");
							console.log(jqXHR);
							console.log(textStatus);
							console.log(errorThrown);
						});

					});

			/* 최신순 목록 눌렀을때  */
			$('#latest').on("click", function() {
				$('#past').removeClass("active");
				$('#latest').addClass("active");
				console.log("latest click");

				$.ajax({
					url : "/latestPost",
					type : "post",
					dataType : "text",
				/* data : formData, */
				}).done(function(result) {
					console.log("결과확인");

					$(".nav-tabs").siblings('.card-body').detach();
					$(".pagingDiv").detach();
					$("#actionForm").detach();

					var html = jQuery('<div>').html(result);
					console.log("html : " + html);
					var contents = html.find("div.whole").html();
					console.log("contents : " + contents);

					/* $(".nav-tabs").after(contents); */
					$(".all").html(contents);

					$('.all').on('ready', '.card-text', function() {

						console.log("heello");

					});

					cutString(10);

				}).fail(function(jqXHR, textStatus, errorThrown) {
					console.log("에러");
					console.log(jqXHR);
					console.log(textStatus);
					console.log(errorThrown);
				});

			});

			/* 과거순 목록 눌렀을때 */
			$('#past').on("click", function() {
				$('#latest').removeClass("active");
				$('#past').addClass("active");
				console.log("past click");

				$.ajax({
					url : "/pastPost",
					type : "post",
					dataType : "text",
				/* data : formData, */
				}).done(function(result) {
					console.log("결과확인");

					$(".nav-tabs").siblings('.card-body').detach();
					$(".pagingDiv").detach();
					$("#actionForm").detach();

					var html = jQuery('<div>').html(result);
					console.log("html : " + html);
					var contents = html.find("div.whole").html();
					console.log("contents : " + contents);

					/* $(".nav-tabs").after(contents); */
					$(".all").html(contents);

					cutString(10);

				}).fail(function(jqXHR, textStatus, errorThrown) {
					console.log("에러");
					console.log(jqXHR);
					console.log(textStatus);
					console.log(errorThrown);
				});

			});

		});
	</script>

</body>
</html>