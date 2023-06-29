<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
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
<!-- 로딩 이벤트 -->
<script type="text/javascript" src="resources/js/loading.js"></script>
</head>
<body>
  <jsp:include page="menu.jsp" />
  <main class="container custom-main-padding border-bottom">
    <div class="row">
      <aside class="col-3 ">
        <div class="row rounded shadow mb-5" style="height: 100px;">
          <div><i
            class="bi bi-person-circle"
            style="font-size: 2.5rem; color: cornflowerblue;">&nbsp;</i>${member_id}님 환영합니다</div>
        </div>
        <div class="row mb-5" style="height: auto;">
					<div class="accordion p-0 mb-3 accordion-flush" id="mylist-collapse-parent">
						<div class="accordion-item">
							<h2 class="accordion-header rounded shadow border-0 " id="headingOne">
								<button class="accordion-button collapsed rounded p-3" type="button"
									data-bs-toggle="collapse" data-bs-target="#mylist-collapse"
									aria-expanded="true" aria-controls="collapseOne"><i class="bi bi-clipboard-check "></i>나의
									신청리스트</button>
							</h2>
							<div id="mylist-collapse" class="accordion-collapse collapse"
								aria-labelledby="headingOne"
								data-bs-parent="#mylist-collapse-parent">
								<div class="accordion-body">
									<a href="/orderlist" class="d-block text-decoration-none text-dark p-0"><div class="rounded shadow mb-3 p-3 "><i class="bi bi-cart"></i>구매 내역</div></a>
									<a href="/mypage/selectedmypage" class="d-block text-decoration-none text-dark p-0"><div class="rounded shadow mb-3 p-3 "><i class="bi bi-bookmark-heart"></i>위시리스트</div></a> 
									<a href="" class="d-block text-decoration-none text-dark p-0"><div class="rounded shadow mb-3 p-3 "><i class="bi bi-chat-right-text"></i>댓글 리스트</div></a>
								</div>
							</div>
						</div>
					</div>
					<a class="d-block text-decoration-none text-dark p-0" href=""><div class="rounded shadow mb-3 p-3"><i class="bi bi-person-gear"></i>내 정보 수정</div></a>
          <a class="d-block text-decoration-none text-dark p-0" href=""><div class="rounded shadow mb-3 p-3"><i class="bi bi-credit-card"></i>나의 포인트</div></a>
        </div>
        <div class="row rounded shadow mb-5" style="height: 130px;">
          <a class="d-block text-decoration-none" href="">
          <div class="rounded shadow my-3 p-2 bg-dark text-white "><i class="bi bi-question-circle"></i>FAQ</div></a>
          <a class="d-block text-decoration-none " href="">
          <div class="rounded shadow mb-2 p-2 bg-dark text-white"><i class="bi bi-chat-dots"></i>체험단 문의하기</div></a>
        </div>
        <a class="d-block text-decoration-none text-dark p-0" href=""><div class="row rounded shadow p-3" style="height: 60px;">
          로그아웃
        </div></a>
      </aside>
      <article class="offset-1 col-8">
        <div class="row">
          <!--@@@@@@@@@@@@@ 각자 작성한 리스트 코드 넣는곳@@@@@@@@@@@@@@@ -->
          
          
          
          
        </div>
      </article>
    </div>  
  </main>
<jsp:include page="footer.jsp" />
</body>
</html>