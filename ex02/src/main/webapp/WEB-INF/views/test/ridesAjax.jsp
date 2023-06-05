<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ridesAjax.jsp</title>
</head>
<body>
<div id="jsp-main-section">
<section class="py-5 sec1" style="background-image: url('${ride.r_img}')">
	<div class="container px-4 px-lg-5 my-5">
		<div class="row gx-lg-5 align-items-center ajaxDiv">
			<div class="col-md-6">
				<input type="hidden" id="r_img" value="${ride.r_img }">
				<img class="card-img-top mb-5 mb-md-0 bg-gray tem card shadow-sm main-img" src="${ride.r_img }"/>
			</div>
			<div class="col-md-6 sec2">
				<div class="d-flex small text-warning mb-2">
					<div class="bi-star-fill"></div>
					<div class="bi-star-fill"></div>
					<div class="bi-star-fill"></div>
					<div class="bi-star-fill"></div>
				</div>
				<h3 class="fw-bolder">${ride.r_name }</h3>
				<div class="fs-5 mb-5">
					<div>
					${ride.r_info }
					</div><br>
					<span class="fw-bolder">위치: </span><span>${ride.r_location }</span><br>
					<c:choose>
						<c:when test="${ride.r_max_height eq 0}">
							<span class="fw-bolder">키: </span><span>${ride.r_min_height } 이상</span>
						</c:when>
						<c:otherwise>
							<span class="fw-bolder">키: </span><span>${ride.r_min_height } 이상 ${ride.r_max_height } 이하</span>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="d-flex">
					<button class="btn imgs btn-outline-dark flex-shrink-0" type="button" onclick="#">
						Like
					</button>
					<button class="btn imgs btn-outline-dark flex-shrink-0" type="button" onclick="history.back();">
						List
					</button>
				</div>
			</div>
		</div>
	</div>
</section>
<section class="py-5 bg-light">
	<c:choose>
		<c:when test="${not empty ride.r_video || ride.r_video ne null}">
			<div class="container px-4 px-lg-5 my-5">
				<h3 class="fw-bolder">탑승 영상</h3>
				<div style="position: relative; z-index: 1; width: 100%; height: 100%">
					<iframe width="100%" height="500" src="${ride.r_video }&autoplay=1&mute=1" 
					title="YouTube video player" frameborder="0"
					allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
				</div>
				<hr>
			</div>
		</c:when>
		<c:otherwise>
			영상 없음
		</c:otherwise>
	</c:choose>
</section>
</div>
</body>
</html>