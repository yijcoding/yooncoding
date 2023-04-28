<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

.personal-info {
  margin: 50px auto;
  max-width: 600px;
  padding: 40px;
  background-color: #f7f7f7;
  box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.2);
  border-radius: 10px;
}

h1 {
  color: #003366;
  font-size: 32px;
  text-align: center;
  margin-bottom: 40px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  padding: 10px 15px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #003366;
  color: #fff;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 1px;
}

td {
  font-weight: 500;
}

.label {
  font-weight: 700;
  padding-right: 10px;
}

.value {
  font-size: 18px;
  font-weight: 500;
}

</style>
<!-- 로딩 이벤트 -->
<script type="text/javascript" src="resources/js/loading.js"></script>
</head>
<body>
	<jsp:include page="../menu.jsp" />
	<main class="container custom-main-padding border-bottom">
		<div class="row">
			<jsp:include page="../mypagemenu.jsp"></jsp:include>
			<article class="offset-1 col-8">
				<div class="row">
					<!--@@@@@@@@@@@@@ 각자 작성한 리스트 코드 넣는곳@@@@@@@@@@@@@@@ -->
					<h1>개인 정보</h1>
					<div class="personal-info">
						<c:forEach items="${data }" var="data">
							<table>
								<tr>
									<th colspan="2">인적 사항</th>
								</tr>
								<tr>
									<td class="label">이름:</td>
									<td class="value">${data.m_name }</td>
								</tr>
								<tr>
									<td class="label">생년월일:</td>
									<td class="value">${data.m_birth }</td>
								</tr>
								<tr>
									<td class="label">성별:</td>
									<td class="value">${data.m_gender}</td>
								</tr>
								<tr>
									<td class="label">전화번호:</td>
									<td class="value">${data.m_phone}</td>
								</tr>
								<tr>
									<td class="label">이메일:</td>
									<td class="value">${data.m_email}</td>
								</tr>
								<tr>
									<td class="label">주소:</td>
									<td class="value">${data.m_address }</td>
								</tr>
								<tr>
									<td class="label">가입일:</td>
									<td class="value">${data.m_regidate }</td>
								</tr>
							</table>
						</c:forEach>
					</div>
				</div>
			</article>
		</div>
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>