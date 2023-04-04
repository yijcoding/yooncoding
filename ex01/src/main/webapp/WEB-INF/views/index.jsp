<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>index</title>
  <!-- jquery cdn -->
  <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
  <!-- 부트스트랩 cdn -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
  <!-- 폰트 설정 -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Hahmlet&display=swap" rel="stylesheet">
  <!-- 아이콘 cdn-->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
  
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
</head>
<body>

  <header class="container mb-5">
    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-2 border-bottom" style="height: 130px; ">
      <div class="col-2 ">
        <a href="#" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
          <img class="card-img" src="https://play-lh.googleusercontent.com/W9AN3AyNH7rgBaGO7Jv2MEMk2piGUEerZTZN7hG-xNJFq6QW1Dzs4HLukka0-oKIsw" alt="">
        </a>
      </div>  
      <!-- 헤더 a태그 주소 넣을곳-->
      <ul class="nav col-8 mb-2 justify-content-center mb-md-0">
        <li><a href="#" class="nav-link px-2 link-secondary">고객센터</a></li>
        <li><a href="#" class="nav-link px-2 link-dark">프로모션</a></li>
        <li><a href="#" class="nav-link px-2 link-dark">게시판</a></li>
        <li><a href="#" class="nav-link px-2 link-danger">놀이공원</a></li>
      </ul>
      <div class="col-2 ">
        <ul class="nav justify-content-end">
          <li class="nav-item">
            <button class="btn" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight"><i class="bi bi-search pr-2" style="font-size: 1.5rem; color: cornflowerblue;"></i></button>

            <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header">
                <!-- 검색 -->
                <div class="offcanvas-title" id="offcanvasRightLabel">
                <form action="">
                    <div class="row">
                    <div class="col-2">
                    <button class="btn" type="submit" ><i class="bi bi-search pr-2" style="font-size: 1.5rem; color: cornflowerblue;"></i></button>
                    </div>
                    <div class="col">
                    <div class="form-floating mb-3">
                      <input type="search" class="form-control" id="floatingInput" placeholder="놀이공원을 찾아볼까요?">
                      <label for="floatingInput">놀이공원을 찾아볼까요?</label>
                    </div>
                    </div>
                    </div>
                </form>
                </div>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <div class="accordion" id="accordionPanelsStayOpenExample">
                    <div class="accordion-item">
                      <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
                          <strong>국내 놀이공원</strong>
                        </button>
                      </h2>
                      <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">
                        <div class="accordion-body">
                            <div class="row">
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>전국</a></div>
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>서울</a></div>
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>경기</a></div>
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>대전</a></div>
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>충청</a></div>
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>대구</a></div>
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>경북</a></div>
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>부산</a></div>
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>경남</a></div>
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>광주</a></div>
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>전라</a></div>
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>제주</a></div>
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>기타</a></div>
                            </div>
                        </div>
                      </div>
                    </div>
                    <div class="accordion-item">
                      <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">
                            <strong>해외 놀이공원</strong>
                        </button>
                      </h2>
                      <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingTwo">
                        <div class="accordion-body">
                            <div class="row">
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>일본</a></div>
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>미국</a></div>
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>중국</a></div>
                                <div class="col-3"><a class="text-decoration-none text-dark" href=""><i class="bi bi-geo-alt"></i>기타</a></div>
                            </div>
                        </div>
                      </div>
                    </div>
                    
                  </div>
                  
            </div>
            </div>
          </li>
          <li class="nav-item">
            <!-- 마이페이지 링크 -->
            <a class="nav-link" href="#"><i class="bi bi-person-circle" style="font-size: 1.5rem; color: cornflowerblue;"></i></a>
          </li>
        </ul>
      </div>
      
    </div>
  </header>

  <main class="container custom-main-padding border-bottom">
    <div class="row">
      <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active" >
            <img src="https://img.freepik.com/free-photo/nakorn-ratchasrima-thailand-december-13-scenical-world-khao-yai-on-december-13-2016-in-nakorn-ratchasrima-thailand-it-was-built-for-tourist-in-khao-yai-new-point-tourist-attraction-and-check-in_1428-574.jpg?w=1380&t=st=1680262427~exp=1680263027~hmac=9e520de17ecaaf20675ccc06586d899a9c840cbc2043588d37cb8e94406cce6d" class="d-block w-100 custom-carousel-img" alt="...">
          </div>
          <div class="carousel-item" >
            <img src="https://img.freepik.com/free-photo/aerial-drone-view-of-the-big-square-decorated-for-christmas-in-sibiu-romania_1268-19592.jpg?w=1380&t=st=1680262359~exp=1680262959~hmac=c18bce7816e10a2ab52bf9de714159e0b7140131494e82d56ce5d2fbe8728406" class="d-block w-100 custom-carousel-img" alt="...">
          </div>
          <div class="carousel-item">
            <img src="https://storage.googleapis.com/cdn.media.bluedot.so/bluedot.tappik/2022/08/pexels-craig-adderley-3411132.jpg" class="d-block w-100 custom-carousel-img" alt="...">
          </div>
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
      <h4><a class="text-decoration-none text-dark"href="#">국내 놀이공원</a></h4>
    </div>
    <div class="album py-5 bg-light ">
      <div class="container">
      <div class="row g-5 " >
        <div class="col-3">
          <a href="" class="text-decoration-none text-dark ">
          <div class="card shadow-sm">
            <img src="https://cdn.pixabay.com/photo/2018/11/20/08/03/castle-3826771_960_720.jpg" class="custom-card-img"  alt="...">
            <div class="card-body custom-card-body-height overflow-hidden">
              <h5 class="card-title">카드 제목</h5>
              <p class="card-text">카드 내용</p>
            </div>
          </div>
          </a>
        </div>
        <div class="col-3">
          <a href="" class="text-decoration-none text-dark ">
          <div class="card shadow-sm">
            <img src="https://cdn.pixabay.com/photo/2018/07/21/14/08/entertainment-3552633_960_720.jpg" class="custom-card-img"  alt="...">
            <div class="card-body custom-card-body-height overflow-hidden">
              <h5 class="card-title">카드 제목</h5>
              <p class="card-text">카드 내용</p>
            </div>
          </div>
          </a>
        </div>
        <div class="col-3">
          <a href="" class="text-decoration-none text-dark ">
          <div class="card shadow-sm">
            <img src="https://cdn.pixabay.com/photo/2020/01/21/16/42/urbex-4783364_960_720.jpg" class="custom-card-img"  alt="...">
            <div class="card-body custom-card-body-height overflow-hidden">
              <h5 class="card-title">카드 제목</h5>
              <p class="card-text">카드 내용</p>
            </div>
          </div>
          </a>
        </div>
        <div class="col-3">
          <a href="" class="text-decoration-none text-dark ">
          <div class="card shadow-sm">
            <img src="https://cdn.pixabay.com/photo/2016/12/13/17/41/merry-go-round-1904715_960_720.jpg" class="custom-card-img"  alt="...">
            <div class="card-body custom-card-body-height overflow-hidden">
              <h5 class="card-title">카드 제목</h5>
              <p class="card-text">카드 내용</p>
            </div>
          </div>
          </a>
        </div>
      </div>
    </div>
    </div>

    <hr>
    <div class="row pt-5 pb-2">
      <h4><a class="text-decoration-none text-dark"href="#">해외 놀이공원</a></h4>
    </div>
    <div class="album py-5 bg-light ">
      <div class="container">
      <div class="row g-5 " >
        <div class="col-3">
          <a href="" class="text-decoration-none text-dark ">
          <div class="card shadow-sm">
            <img src="https://cdn.pixabay.com/photo/2016/09/18/20/34/wyoming-1678957__340.jpg" class="custom-card-img"  alt="...">
            <div class="card-body custom-card-body-height overflow-hidden">
              <h5 class="card-title">카드 제목</h5>
              <p class="card-text">카드 내용</p>
            </div>
          </div>
          </a>
        </div>
        <div class="col-3">
          <a href="" class="text-decoration-none text-dark ">
          <div class="card shadow-sm">
            <img src="https://cdn.pixabay.com/photo/2015/07/26/20/11/carousel-861705__340.jpg" class="custom-card-img"  alt="...">
            <div class="card-body custom-card-body-height overflow-hidden">
              <h5 class="card-title">카드 제목</h5>
              <p class="card-text">카드 내용</p>
            </div>
          </div>
          </a>
        </div>
        <div class="col-3">
          <a href="" class="text-decoration-none text-dark ">
          <div class="card shadow-sm">
            <img src="https://cdn.pixabay.com/photo/2018/09/02/17/51/oktoberfest-3649390_960_720.jpg" class="custom-card-img"  alt="...">
            <div class="card-body custom-card-body-height overflow-hidden">
              <h5 class="card-title">카드 제목</h5>
              <p class="card-text">카드 내용</p>
            </div>
          </div>
          </a>
        </div>
        <div class="col-3">
          <a href="" class="text-decoration-none text-dark ">
          <div class="card shadow-sm">
            <img src="https://cdn.pixabay.com/photo/2014/03/07/09/05/fair-281755__340.jpg" class="custom-card-img"  alt="...">
            <div class="card-body custom-card-body-height overflow-hidden">
              <h5 class="card-title">카드 제목</h5>
              <p class="card-text">카드 내용</p>
            </div>
          </div>
          </a>
        </div>
      </div>
    </div>
    </div>
  </main>

<footer class="container mb-5">
  <div class="row">
    <div class="col-3">
      <ul class="list-group list-group-flush">
        <li class="list-group-item">고객지원실 운영안내</li>
        <li class="list-group-item">월~금 09:30~06:30(점심시간 13:15~14:30) </li>
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
	
</div>
</body>
</html>