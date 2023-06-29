<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>Detail Design</title>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"
	integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
	crossorigin="anonymous"></script>
	
<!-- slick slide -->
<script src="${path}/resources/amuse/slick/slick.min.js"></script>
<link rel="stylesheet" href="${path}/resources/amuse/slick/slick.css" />
<link rel="stylesheet" href="${path}/resources/amuse/slick/slick-theme.css" />
	
<script src="/resources/amuse/script/test.js"></script>
<link href="/resources/amuse/css/test.css" rel="stylesheet"/> 

<!-- 부트스트랩 cdn -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<!-- 폰트 설정 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Hahmlet&display=swap" rel="stylesheet">
<!-- 아이콘 cdn-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">


<style type="text/css">
.a-images {
	width: 150px;
	height: 100px;
}
.main-img {
	width: 900px;
	height: 600px;
}
.card{margin-left: 5px;}

.custom-card-img {
	max-width: 100%;
	height: 300px;
	object-fit: cover;
}
.custom-card-body-height {height: 110px;}
.custom-card-body-height2 {height: 230px;}

.card-body-2{height: 100px;}
.hidden-img{overflow: hidden;}

.r-img{width: 100%;}
.rImg{height: 150px;}

.f-img{
	width: 100px;
	height: 100px;
}
.insert-btn{
	float: right;
}
.starFrm fieldset{
    display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
    direction: rtl;
    border: 0; /* 필드셋 테두리 제거 */
}
.starFrm input[type=radio]{
	display: none;
}
.starFrm label{
    font-size: 2em; /* 이모지 크기 */
    color: transparent; /* 기존 이모지 컬러 제거 */
    text-shadow: 0 0 0 lightgray; /* 새 이모지 색상 부여 */
}
.starFrm label:hover{
    text-shadow: 0 0 0 gold; /* 마우스 호버 */
}
.starFrm label:hover ~ label{
    text-shadow: 0 0 0 gold; /* 마우스 호버 뒤에오는 이모지들 */
}
.starFrm input[type=radio]:checked ~ label{
    text-shadow: 0 0 0 gold; /* 마우스 클릭 체크 */
}

.starFrmBack{
    color: transparent; /* 기존 이모지 컬러 제거 */
    text-shadow: 0 0 0 lightgray; /* 새 이모지 색상 부여 */
}

.back{
	color: lightgray;
}

.ul-li > li > a {
	text-decoration: none;
	font-size: 1.2em;
}
.star-a {text-decoration: none;}

.table{
	width: 100%;
	height: 30px;
	text-align: center;
}
.pagination{display: inline-flex;}
.pagination li > a {text-decoration: none;}

#thead{
	font-size: 1.3rem;
	color: lightblue;
}

.side-widget{
	position: sticky;
	position: -webkit-sticky;
	top: 0px;
}
.aside{height: 4000px;}

#tbody > tr > th{line-height: 40px;}

.answer-form{display: none;}

.card-body-height{height: 140px;}

</style>
<!-- 로딩 이벤트 -->
<script type="text/javascript" src="resources/js/loading.js"></script>
<script type="text/javascript">
	$(function() {
		var winY = window.pageYOffset;
		console.log("winY = " + winY)
		
		//rides top
		const ridesPos = document.querySelector(".r_location");
		const posTop = ridesPos.pageYOFFset;
		
		$('body').sakura();
		
		//========놀이공원 사진========
		const input = document.querySelectorAll("#input");
		const image = document.querySelectorAll("#image");
		//console.log("input 개수 = " + input.length)
		const length = input.length

		for (let i = 0; i < length; i++) {
			//console.log("i = " + i)
			//console.log("input[" + i + "] =" + input[i].value)
			//console.log(image[i].value)
			$('#' + input[i].value).mouseover(function() {
				$(".main-img").attr("src", image[i].value)
			})
		}
		
		//========slide========
		let pressed;

		$(document).on("mousedown", ".slider-div", function(e){
			pressed = true;
			startX = e.clientX;
			//console.log(startX);
		})
		$(document).on("mouseleave", ".slider-div", function(e){
			pressed = false;
		})
		$(document).on("mouseup", ".slider-div", function(e){
			pressed = false;
		})
		$(document).on("mousemove", ".slider-div", function(e){
			if(!pressed) return;
			//Y축이 영향을 미치지 않게 X축에서만 이동하도록 설정
			$(".mouse-drag").css("opacity", "1.0");
			$("#notice").css("display", "none")
			e.preventDefault();
			this.scrollLeft += startX - e.clientX;
			//console.log(startX - e.clientX)
		})
		
		//========rides image 확대========
		$(document).on("mouseover", ".r-img", function(){
			$(this).css("transition", "transform, 3s, linear")
			$(this).css("transform", "scale(1.2)")
		})
		$(document).on("mouseleave", ".r-img", function(){
			$(this).css("transition", "transform, 3s, linear")
			$(this).css("transform", "translate(0px, 0px)")
		})
		
		//=========star rating========
		const rating = document.querySelectorAll("input[name=rating]");
		const ratingNum = document.querySelector("#rating_num")
		const frm = document.querySelector(".starFrm")
		
		const rating_len = rating.length
		console.log("rating_len = " + rating_len)
		
		for(let i = 0; i < rating_len; i++){
			label[i].addEventListener("mousedown", function(){
					ratingNum.innerHTML = rating[i].value
					let input = document.createElement("input")
					input.setAttribute("type", "hidden")
					input.setAttribute("id", "reviewRating")
					input.setAttribute("value", rating[i].value)
					frm.appendChild(input)
			})
		}
		
		//로그인 여부에 따라 글 작성 가능한지 아닌지 확인
		const textarea = document.querySelector("textarea");
		if($("member_id").val() === null){
			textarea.addEventListener("mousedown", function(){
				alert("로그인 후 이용해주세요");
				return false;
			})
		}
		
		//slick slider
	 	$('.slider-List').slick({
	        slide: 'div',        //슬라이드 되어야 할 태그 ex) div, li 
	        infinite : true,     //무한 반복 옵션     
	        slidesToShow : 4,        // 한 화면에 보여질 컨텐츠 개수
	        slidesToScroll : 1,        //스크롤 한번에 움직일 컨텐츠 개수
	        speed : 1000,     // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
	        arrows : true,         // 옆으로 이동하는 화살표 표시 여부
	        dots : false,         // 스크롤바 아래 점으로 페이지네이션 여부
	        autoplay : true,            // 자동 스크롤 사용 여부
	        autoplaySpeed : 2000,         // 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
	        pauseOnHover : true,        // 슬라이드 이동 시 마우스 호버하면 슬라이더 멈추게 설정
	        vertical : false,        // 세로 방향 슬라이드 옵션
	        draggable : true,     //드래그 가능 여부 
	    });
	})
	
	function facilityDetail(n){
		//alert("click")
		$.ajax({
			url: "/facilityDetail?facility_id=" + $("#facility_id").val(),
			type: "get",
			dataType:"json",
			success: function(response){
				//console.log(response["f_name"])
				//console.log(response["f_img"])
				//console.log(response["f_info"])
				
				let data = "";
				data += "<div class=" + "'card shadow-sm' align=" + "'center'>"
				data += "<div align=" + "'center'><img src=" + response["f_img"] + " class=" + "'custom-card-img f-img'></div>"
				data += "<div class=" + "'card-body shadow-sm custom-card-body-height2'>"
				data += "<h5 class=" + "'card-title'>" + response["f_name"] + "</h5>"
				data += "<p>" + response["f_info"] + "<br>" + "위치: " + response["f_location"] + "</p></div></div>"
				$(".f-div").html(data)
			},
			error:function(request,status,error){
				console.log("code: " + request.status)
				console.log("message: " + request.responseText)
				console.log("error: " + error);
			}
		})
	}
	
	function facilityBtnClick(n){
		//alert(n)
		$.ajax({
			url: "/facilityDetail?facility_id=" + n,
			type: "get",
			dataType:"json",
			success: function(response){
				//console.log(response["f_name"])
				//console.log(response["f_img"])
				//console.log(response["f_info"])
				
				let data = "";
				data += "<div class=" + "'card shadow-sm' align=" + "'center'>"
				data += "<div align=" + "'center'><img src=" + response["f_img"] + " class=" + "'custom-card-img f-img'></div>"
				data += "<div class=" + "'card-body shadow-sm custom-card-body-height'>"
				data += "<h5 class=" + "'card-title'>" + response["f_name"] + "</h5>"
				data += "<p>" + response["f_info"] + "<br>" + "위치: " + response["f_location"] + "</p>"
				data += "<button type=" + "'button' id=" + "'backBtn' class=" + "'btn btn-outline-dark mt-auto card-text' onclick=" + "'backBtn()'>" + "back" + "</button></div></div>"
				$(".f-div").html(data)
			},
			error:function(request,status,error){
				console.log("code: " + request.status)
				console.log("message: " + request.responseText)
				console.log("error: " + error);
			}
		})
	}
	
	const backBtn = function(){
		location.reload()
	}

	function chat(){
		const member_id = $("#member_id").val();
		if(member_id === "" || member_id == null){
			alert("로그인 후 이용 가능")
			location.reload();
		}
		else{
			let openWin = window.open("/chatRoom", "_blank", "width=600, height=600, resizable = no, scrollbars = no, top=300, left=500")
		}
	}
	
	function reviewBtn(){
		alert("click")
		const member_id = document.querySelector("#member_id").value
		const textContent = document.querySelector("#content").value
		const amuse_id = document.querySelector("#amuse_id").value
		const reviewRating = document.querySelector("#reviewRating").value
		console.log("member_id = " + member_id)
		console.log("textContent = " + textContent)
		console.log("amuse_id = " + amuse_id)
		console.log("reviewRating = " + reviewRating)
		
		if(textContent === ""){
			alert("내용을 입력하세요");
			return false;
		}
		
		const param = {
			"member_id": member_id, 
			"r_content":textContent, 
			"amuse_id":amuse_id, 
			"r_grade":reviewRating
			}
		
		if(member_id.length != 0){
			$.ajax({
				url: "http://localhost:8080/insertReview",
				type: "post",
				data: param,
				dataType:"text",
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				success: function(response){
					//console.log(response.equals("success"))
					if(response === "success"){
						document.querySelector("#tbody").innerHTML = "";
						getReviewList();
// 						const star = document.querySelectorAll(".starFrm #label");
// 						for(row of star){
// 							console.dir(row)
// 						}
					}
// 					location.reload();
				},
				error:function(request,status,error){
					console.log("code: " + request.status)
					console.log("message: " + request.responseText)
					console.log("error: " + error);
				}
			})
		}
		else{
			alert("로그인 후 이용하세요");
		}
		
// 		textContent = "";
// 		const labels = document.querySelectorAll("#label");
// 		for(row of labels){
// 			row.style.color = "transparent"
// 		}
	}
	
	function getReviewList(){
		const amuseId = ${dto.amuse_id};
		const data = {"currentPage" : 1, "amuse_id" : amuseId}
		
		$.ajax({
			url: "/reviewList",
			type: "post",
			data: data,
			success: function(response){ 
				var html = $("#tbody").html(response);
				var reviews = html.find("tbody#reviews").html();
				$("#tbody").html(reviews);
			},
			error:function(request,status,error){
				console.log("code: " + request.status)
				console.log("message: " + request.responseText)
				console.log("error: " + error);
			}
		})
		
		const content = document.querySelector("#content");
		content.value = "";
	}
	
	function moveClick(n){
// 		const ridesTop = document.querySelector(".r_location");
// 		const facilityTop = document.querySelector(".f_location");
// 		const reviewTop = document.querySelector(".re_location");
		const ridesTop = document.querySelector(".r_location").getBoundingClientRect().top;
		const facilityTop = document.querySelector(".f_location").getBoundingClientRect().top;
		const reviewTop = document.querySelector(".re_location").getBoundingClientRect().top;
// 		console.log("reviewTop = " + reviewTop)
// 		console.log("facilityTop = " + facilityTop)
// 		console.log("ridesTop = " + ridesTop)
		
// 		Element.scrollIntoView()
		
		//rides top
		const ridesPos = document.querySelector(".r_location");
		const posTop = ridesPos.pageYOFFset;
		
		if(n === 1){
			//amusement
			window.scrollTo({ left: 0, top: 0, behavior: "smooth" });
		}
		else if(n === 2){
			//rides
			window.scrollTo({ left: 0, top: 1198, behavior: "smooth" });
		}
		else if(n === 3){
			//facility
			window.scrollTo({ left: 0, top: 1800, behavior: "smooth" });
		}
		else if(n === 4){
			//review
			window.scrollTo({ left: 0, top: 2436, behavior: "smooth" });
		}
		else if(n === 5){
			//recommendation
			window.scrollTo({ left: 0, top: 3223, behavior: "smooth" });
		}
	}
	
	function topBtn(){
		window.scrollTo({ left: 0, top: 0, behavior: "smooth" });
	}
	
	function viewAmuseBtn(n){
		location.href="/amuseDetail?amuse_id=" + n
	}
			
	// num => currentPage
	function pagingClick(num){
		const amuseId = ${dto.amuse_id};
		const currentPage = num;
		$("#pageNum").val(num);
		
		const data = {"currentPage" : num, "amuse_id" : amuseId}
		
		$.ajax({
			url: "/reviewPaging",
			data: data,
			type: "post",
			success: function(response){
// 				console.log(response)
				var html = $("#tbody").html(response);
				var reviews = html.find("tbody#reviews").html();
				$("#tbody").html(reviews);
			} 
		})
	}
	
	function reviewDelete(reviewId){
		if(confirm("삭제할까요?")){
			//삭제 controller로 ajax 요청하고 reviewPaging 불러오기
			$.ajax({
				url:"/deleteReview?review_id="+reviewId,
				type: "get",
				success: function(response){
					if(response === "delete"){
						alert("삭제했습니다");
						location.reload();
					}
				},
				error:function(request,status,error){
					console.log("code: " + request.status)
					console.log("message: " + request.responseText)
					console.log("error: " + error);
				}
			})
		}
	}
	
	function reviewAnswer(reviewId){
		const review_id = reviewId
		
		if($(".answer-form").css("display") === "none"){
			$(".answer-form").css("display", "block")
		}
		else{
			$(".answer-form").css("display", "none");
		}
		
		if($(".answer-form").css("display") === "block"){
			$("#answer-form-reviewId").val(review_id)
		}
	}
	
	function insertAnswer(){
		const reviewId = $("#answer-form-reviewId").val();
		const amuseId = ${dto.amuse_id};
		const memberId = $("#memberId").val();
		const r_content = $("#answer-text").val();
		const currentPage = $("#pageNum").val();
		
		const data = {"review_id":reviewId, "amuse_id":amuseId, 
			"member_id":memberId, "r_content":r_content,
			"currentPage":currentPage};
		
		console.log("reviewId = " + reviewId)
		console.log("amuesId = " + amuseId)
		console.log("memberId = " + memberId)
		console.log("r_content = " + r_content)
		console.log("currentPage = " + currentPage)
		
		//페이지 번호 그대로 화면을 갱신하려면 현재 몇번의 페이지 번호에 있는 리뷰를 선택했는지를 알아야 함
		//일단 답글 작성하면 1페이지로 이동하게끔
		
		$.ajax({
			url:"/insertAnswer",
			data: data,
			type: "post",
			success: function(response){
				console.log(response)
				var html = $("#tbody").html(response);
				var reviews = html.find("tbody#reviews").html();
				$("#tbody").html(reviews);
				
				$(".answer-form").css("display", "none");
			}
		})
	}
	
</script>

</head>
<body>
<input type="hidden" id="member_id" value="${member_id }">
<input type="hidden" id="amuse_id" value="${dto.amuse_id }">
<div class="container mt-5 detail-info showstep1 main">
	<div class="row container d-flex">
		<article class="col-sm-9 content-info">
			<div>
				<header class="mb-4">
					<h1 class="fw-bolder mb-1">${dto.a_name }</h1>
					<div class="small text-warning mb-2" style="width: 100px">
						<a href="javascript:void(0);" onclick="moveClick(4);" class="star-a d-flex text-warning">
						<c:choose>
							<c:when test="${not empty review }">
								<c:forEach begin="1" end="${(avg * 10) / 10}">
									<div class="bi-star-fill"></div>
								</c:forEach>
								&nbsp;<span style="color: black">( ${avg } )</span><br>
							</c:when>
							<c:otherwise>
								<div>작성한 리뷰가 없습니다</div>
							</c:otherwise>
						</c:choose>
						</a>
					</div>
					<a class="badge bg-secondary text-decoration-none link-light"
						href="/list?searchCountry=${dto.a_country }">${dto.a_country }</a>
					<div class="text-muted fst-italic mb-2">운영시간: ${dto.a_time }</div>
					<div class="text-muted fst-italic mb-2">조회수: ${dto.a_view }</div>
				</header>
				<figure class="mb-4">
					<img class="img-fluid rounded main-img" src="${dto.a_img }"
						alt="main-image" />
					<div class="d-flex">
						<c:forEach items="${a_images }" var="img" varStatus="status">
							<div>
								<img class="a-images img-thumbnail rounded" id="${status.index }" src="${img.url }">
							</div>
							<input type="hidden" id="input" value="${status.index }">
							<input type="hidden" id="image" value="${img.url }">
						</c:forEach>
					</div>
				</figure>
				<section class="mb-5">
					<p class="fs-5 mb-4">${dto.a_info }</p>
				</section>
			</div>
			<div>
				<header class="mb-4 r_location">
					<h3 class="fw-bolder mb-1">놀이기구</h3>
<!-- 					<a class="badge bg-secondary text-decoration-none link-light" -->
<!-- 						href="/ridesAllList">more</a> -->
				</header>
				<figure class="mb-4 py-5 bg-light wrapper">
					<div class="container">
						<div class="g-5 d-flex overflow-hidden slider-div">
							<c:forEach items="${rides }" var="rides">
							<div class="col-4 rounded mouse-drag">
						        <div class="card shadow-sm">
						            <div class="hidden-img"><img src="${rides.r_img }" class="custom-card-img r-img"></div>
						            <div class="card-body shadow-sm custom-card-body-height" align="center">
						              <h5 class="card-title"><a href="/ridesDetail?rides_id=${rides.rides_id }" class="text-decoration-none text-dark">
						              ${rides.r_name }</a></h5>
						              <button type="button" class="btn btn-outline-dark mt-auto card-text" 
						              onclick="location.href='ridesDetail?rides_id=${rides.rides_id}&amuse_id=${rides.amuse_id }'">Detail</button>
						            </div>
					            </div>
					        </div>
					        </c:forEach>
						</div>
					</div>
				</figure>
				
				<header class="mb-4 f_location">
					<h3 class="fw-bolder mb-1">편의시설</h3>
				</header>
				<figure class="card-body mb-4 py-5 bg-light album wrapper">
					<div class="container f-div">
						<div class="g-5 row overflow-hidden">
							<c:forEach items="${facility }" var="facility" varStatus="status">
								<div class="col-4">
							        <div class="card shadow-sm" align="center">
							            <div align="center"><img src="${facility.f_img }" class="custom-card-img f-img"></div>
							            <div class="card-body shadow-sm custom-card-body-height">
							              <h5 class="card-title">${facility.f_name }</h5>
							              <button type="button" class="f-detail-btn btn btn-outline-dark mt-auto card-text"
							              onclick="facilityBtnClick(${facility.facility_id});">Detail</button>
							            </div>
						            </div>
						        </div>
					        </c:forEach>
						</div>
					</div>
				</figure>
			</div>

			<section class="mb-5">
				<header class="mb-4 re_location">
					<h3 class="fw-bolder mb-1">Review</h3>
				</header>
				<div class="card bg-light">
					<div class="card-body">
						<!-- Comment form-->
						<form class="mb-4 starFrm" method="post">
							<fieldset>
						      <input type="radio" name="rating" value="5" id="rate1"><label id="label" for="rate1">⭐</label>
						      <input type="radio" name="rating" value="4" id="rate2"><label id="label" for="rate2">⭐</label>
						      <input type="radio" name="rating" value="3" id="rate3"><label id="label" for="rate3">⭐</label>
						      <input type="radio" name="rating" value="2" id="rate4"><label id="label" for="rate4">⭐</label>
						      <input type="radio" name="rating" value="1" id="rate5"><label id="label" for="rate5">⭐</label>
							</fieldset>
							점수: <span id="rating_num"> </span> / 총 리뷰 개수: <span>${totalCnt }</span>
							<br>
							<input type="hidden" id="member_id" value="${member_id }">
							<input type="hidden" id="amuse_id" value="${dto.amuse_id }">
							<textarea class="form-control" rows="3" id="content" placeholder="리뷰 작성"></textarea>
							<button type="button" class="btn btn-outline-dark mt-auto card-text insert-btn"
							onclick="reviewBtn();">write</button>
						</form>
						<br>
						<div class="mb-4">
							<div class="ms-3">
								<table class="table table-borderless">
									<thead id="thead" class="border-bottom border-3">
										<tr>
											<th>Id</th>
											<th>Content</th>
											<th>Register</th>
											<th></th>
										</tr>
									</thead>
									<tbody id="tbody">
										<c:forEach items="${review }" var="rList" varStatus="status">
											<c:set var="rList_member_id" value="${rList.member_id }"/>
											<c:set var="length" value="${fn:length(rList_member_id)}"/>
											<c:set var="last_two" value="${fn:substring(rList_member_id, length-3, length)}"/>
											<tr>
												<c:choose>
													<c:when test="${rList_member_id eq 'hong1' }">
														<th>관리자</th>
														<th>&nbsp;&nbsp;&nbsp;ㄴ ${rList.r_content }</th>
													</c:when>
													<c:otherwise>
														<th>${fn:replace(rList_member_id,last_two,"***")}</th>
														<th>${rList.r_content }</th>
													</c:otherwise>
												</c:choose>
												<th>${rList.r_regidate }</th>
												<c:if test="${member_id eq rList_member_id }">
												<th><button type="button" class="btn btn-outline-dark mt-auto" onclick="reviewDelete(${rList.review_id});">Delete</button></th>
												</c:if>
												<c:if test="${member_id eq 'hong1' && rList_member_id ne 'hong1'}">
												<th><button type="button" class="btn btn-outline-dark mt-auto" onclick="reviewAnswer(${rList.review_id});">Answer</button></th>
												</c:if>
											</tr>
										</c:forEach>
									</tbody>
									<tbody class="pageNum">
										<tr>
											<th colspan="4">
												<ul class="pagination">
													<c:forEach begin="1" end="${totalPageCount }" var="num">
														<li>
														<a href="javascript:void(0);" onclick="pagingClick(${num});">${num}</a>
														</li>&nbsp;&nbsp;&nbsp;
													</c:forEach>
												</ul>
											</th>
										</tr>
									</tbody>
								</table>
								<div class="answer-form">
									<form class="mb-4 mt-4 d-flex">
										<input type="hidden" id="answer-form-reviewId">
										<input type="hidden" id="pageNum" value="1">
										<input type="hidden" id="memberId" value="${member_id }">
										<textarea class="form-control" rows="2" id="answer-text" placeholder="답글 작성"></textarea>
										<button type="button" class="btn btn-outline-dark mt-auto card-text insert-btn" 
										onclick="insertAnswer();">Answer</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			
			<header class="mb-4 re_location">
					<h3 class="fw-bolder mb-1">추천 놀이공원</h3>
			</header>
			<section class="py-5 bg-light">
				<div class="container px-4 px-lg-5 mt-5">
					<h4 class="fw-bolder mb-4">평균 점수 높은 순서</h4>
					<div class="d-flex slider-List" id="img-list">
						<c:forEach items="${inAGList }" var="inAGList">
							<div class="col mb-5 div">
								<div class="card h-100 imgs">
									<img class="card-img-top rImg" src="${inAGList.a_img }"/>
									<div class="card-body-2 p-4">
										<div class="text-center">
											<h5 class="fw-bolder">${inAGList.a_name }</h5>
											<div class="d-flex justify-content-center">
											<c:forEach begin="1" end="${(inAGList.avg_grade * 10) / 10 }">
												<div class="text-warning bi-star-fill"></div>
											</c:forEach>&nbsp;(${inAGList.avg_grade })
											</div>
										</div>
									</div>
									<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
										<div class="text-center">
											<button type="button" class="btn btn-outline-dark mt-auto"
											onclick="viewAmuseBtn(${inAGList.amuse_id });">View options</button>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
			
			<section class="py-5 bg-light">
				<div class="container px-4 px-lg-5 mt-5">
					<h4 class="fw-bolder mb-4">리뷰 많은 순서</h4>
					<div class="d-flex slider-List" id="img-list">
						<c:forEach items="${inRCList }" var="inRCList">
							<div class="col mb-5 div">
								<div class="card h-100 imgs">
									<img class="card-img-top rImg" src="${inRCList.a_img }"/>
									<div class="card-body-2 p-4">
										<div class="text-center">
											<h5 class="fw-bolder">${inRCList.a_name }</h5>
										</div>
										<div class="text-center">
											<h5 class="fw-bolder">(${inRCList.review_cnt }개)</h5>
										</div>
									</div>
									<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
										<div class="text-center">
											<button type="button" class="btn btn-outline-dark mt-auto"
											onclick="viewAmuseBtn(${inRCList.amuse_id });">View options</button>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
			
			<section class="py-5 bg-light">
				<div class="container px-4 px-lg-5 mt-5">
					<h4 class="fw-bolder mb-4">해외 이색 놀이공원</h4>
					<div class="d-flex slider-List" id="img-list">
						<c:forEach items="${outSpecialList }" var="outSpecialList">
							<div class="col mb-5 div">
								<div class="card h-100 imgs">
									<img class="card-img-top rImg" src="${outSpecialList.a_img }"/>
									<div class="card-body-2 p-4 card-body-height2">
										<div class="text-center">
											<h5 class="fw-bolder">${outSpecialList.a_name }</h5>
										</div>
									</div>
									<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
										<div class="d-flex justify-content-center">
											<c:forEach begin="1" end="${(outSpecialList.avg_grade * 10) / 10 }">
												<div class="text-warning bi-star-fill"></div>
											</c:forEach>&nbsp;(${outSpecialList.avg_grade })
										</div>
										<div class="text-center">
											<h5 class="fw-bolder">(${outSpecialList.review_cnt }개)</h5>
										</div>
										<div class="text-center">
											<button type="button" class="btn btn-outline-dark mt-auto"
											onclick="viewAmuseBtn(${outSpecialList.amuse_id });">View options</button>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
		</article>
		
		<!-- Side widgets-->
		<aside class="aside col-sm-3 container">
			<div class="side-widget">
				<div class="card mb-4 cate">
					<div class="card-header">Categories</div>
					<%-- <div style="top: 0px; right: 0px;"><jsp:include page="/WEB-INF/views/kakaoApi.jsp"/></div> --%>
					<div class="card-body">
						<div class="row">
							<div>
								<ul class="list-unstyled mb-0 ul-li">
									<li><a href="javascript:void(0);" onclick="moveClick(1);">Amusement</a></li>
									<li><a href="javascript:void(0);" onclick="moveClick(2);">Rides</a></li>
									<li><a href="javascript:void(0);" onclick="moveClick(3);">Facility</a></li>
									<li><a href="javascript:void(0);" onclick="moveClick(4);">Review</a></li>
									<li><a href="javascript:void(0);" onclick="moveClick(5);">Recommendation</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="card mb-4 side-chat">
					<div class="card-header">Live Chat</div>
					<div class="card-body">
						<button type="button" 
						class="btn btn-outline-dark mt-auto card-text"
						onclick="chat();">Chat</button>
					</div>
				</div>
			</div>
		</aside>
	</div>
</div>
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
</body>
<!-- Footer-->
	<!-- Bootstrap core JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>
