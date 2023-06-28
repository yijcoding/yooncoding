<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="http://code.jquery.com/jquery-1.7.js"></script>
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>
<script language="javascript"
	src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<meta charset="UTF-8">
<title>네이버 회원가입</title>
<link rel="stylesheet" href="style.css">
<script src="login.js"></script>
</head>
<body>
	<div class="whole">
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
							<input class="locateBtn border border-info rounded-0" id="locationTowriting${status.index}" value="작성한곳으로 이동"
								style="height: 30px" type="button">
							<input type="hidden" id="view_id${status.index}" value="${item.view_id}"/>
						</div>
					</div>
				</div>
			</div>
			<div class="border-bottom m-auto w-80"></div>
		</c:forEach>

		<div class="row">
			<div class="paging my-5 m-auto">
				<div class='pull-right'>
					<!-- justify-content-center는 row가 적용된 콘테이너 안에서만 적용되는듯 하다. row가 없으면 적용이 안된다.  -->
					<nav class="d-flex justify-content-center"
						aria-label="Page navigation">
						<ul class='pagination pagination-sm'>
							<c:if test="${pageMaker.cri.pageNum ne 1}">
								<li class="page-item paginate_button fistPage"><a
									class="page-link" id="page-link-asc" href="1">처음</a></li>
							</c:if>
							<c:if test="${pageMaker.prev}">
								<li class="page-item paginate_button previous"><a
									class="page-link" id="page-link-asc" href="${pageMaker.startPage -1}">
										&lt;&lt; </a></li>
							</c:if>

							<!-- 정수를 begin 부터 end 까지 증가시키고 그떄마다 num에 저장하는 구조인가 보다. -->
							<c:forEach var="num" begin="${pageMaker.startPage}"
								end="${pageMaker.endPage}">
								<li
									class='page-item paginate_button ${pageMaker.cri.pageNum == num ? "active" : ""}'><a
									href="${num}" class="page-link" id="page-link-asc">${num}</a></li>
							</c:forEach>

							<c:if test="${pageMaker.next}">
								<li class="page-item paginate_button next"><a
									href="${pageMaker.endPage+1}" class="page-link" id="page-link-asc">>></a></li>
							</c:if>
						</ul>
					</nav>
				</div>
			</div>
		</div>
		
		<form id='actionForm' method='get'>
			<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
			<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
			<input type='hidden' name='member_id'
				value='${pageMaker.cri.member_id}'>
		</form>

	</div>
</body>
</html>