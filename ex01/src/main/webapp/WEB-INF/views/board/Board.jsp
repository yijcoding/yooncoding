<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"
	integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
	crossorigin="anonymous"></script>
<link href="<c:url value="/resources/css/board.css" />" rel="stylesheet" />
<style type="text/css">
</style>
</head>
<body>
	<section class="notice">
		<div class="page-title">
			<div class="container">
				<h3>게시판</h3>
			</div>
		</div>

		<!-- board seach area -->
		<div id="board-search">
			<div class="container">
				<div class="search-window">
					<form action="">
						<div class="search-wrap">
							<select name="test" style="width:20%;height:40px;float:left;text-align:center; font-size:14px;">
								<option value="제목">제목</option>
							</select> 
							<label for="search" class="blind">공지사항 내용 검색</label> <input
								id="search" type="search" name="" placeholder="검색어를 입력해주세요."
								value="">
							<button type="submit" class="btn btn-dark">검색</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<!-- board list area -->
		<div id="board-list">
			<div class="container">
				<table class="board-table">
					<thead>
						<tr>
							<th scope="col" class="th-num" style="width:5%">번호</th>
							<th scope="col" class="th-title"style="width:45%">제목</th>
							<th scope="col" class="th-member"style="width:10%">작성자</th>
							<th scope="col" class="th-date"style="width:35%">등록일</th>
						</tr>
					</thead>
					<!-- 이부분 나중에 국가별로 나눠야됨  -->
					<!--  
					<c:choose>
						<c:when test="${boardtype eq 'korea'}">
						</c:when>					
						<c:when test="${boardtype eq 'global'}">
						</c:when>					
						<c:when test="${boardtype eq 'free'}">
						</c:when>	
					</c:choose>
					-->				
					<tbody>
							<c:forEach items="${boardList }" var="list">
						<tr>
								<td>${list.board_id }</td>
								<td><a href="board/view?board_id=${list.board_id }">${list.b_title }</a></td>
								<td>${list.member_id }</td>
								<td>${fn:replace(list.postdate, 'T',' ')}</td>
						</tr>
							</c:forEach>
					</tbody>
				</table>
				<!-- 글쓰기 -->
				<form action="board/createBoard">
					<div style="float:right">
						<button type="submit" class="btn btn-dark" style="height:40px;">글쓰기</button>
					</div>
				</form>
			</div>
		</div>

	</section>
</body>
</html>