<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터</title>
<link href="<c:url value="/resources/css/board.css" />" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.6.4.min.js"
	integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
	crossorigin="anonymous"></script>

<style type="text/css">
.notice{
	margin: 0 0 30px 0;
	width:100%;
}
ul.tabs {
	float: left;
	list-style: none;
}

ul.tabs li {
	display: inline-block;
	padding: 10px 15px;
	cursor: pointer;
}

ul.tabs li a {
	color: #666666;
	text-decoration: none;
}

.search-wrap .btn-search {
	background: #555;
	color: #fff;
	hight: 40px;
}

#boardList td a {
	text-decoration: none;
	color: black;
}

.menu_select {
  margin:0 0 30px 100px;
  width:150px;
}

.menu_select .text{
  width:150px;
  
}
.menu_select span{
	float:left;
}
.menu_select .text img,.menu_select span img {
	width:20px;
	margin:0 5px;
}

.menu_select .text span {
	position: relative;
	top:3px;
}

.menu_select .option-list {
  position: relative;
  top: 100%;
  left: 0;
  width: 100%;
  overflow: hidden;
  max-height: 0;
  left:5px; 
}

.menu_select .option-list a {
  	text-decoration: none;
	color:black;
}

.menu_select.active .option-list {
  max-height: none;
}

.board-table tbody tr td a{
	text-decoration: none;
	color:black;
}

.banner{
	text-align:center;
	margin:0 0 100px 0;
}

.title{
	font-weight:bold;
}

.board-table{
	font-size:16px;
}
</style>

<script type="text/javascript">

$(document).ready(function(){
	
	$("#write-bottom").on('click',() =>{
		
		location.href="/customer/insertAnnouncement";
	});
});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/menu.jsp" />
	<div style="margin:0 150px">
		<jsp:include page="/WEB-INF/views/customerService/customerMove.jsp"/>
	</div>

	<section class="notice">
		<div class="banner" style="margin:30px 0">
			<h1>공지 사항</h1>
		</div>
		
		<!-- board list area -->
		<div id="board-list" style="clear: both">
			<div class="page-title" style="text-align: center"></div>


			<div class="container">
			
				<table class="board-table">
					<thead>
						<tr>
							<th scope="col" class="th-num" style="width: 15%">번호</th>
							<th scope="col" class="th-num" style="width: 15%">분류</th>
							<th scope="col" class="th-title" style="width: 35%">제목</th>
							<th scope="col" class="th-date" style="width: 35%">등록일</th>
						</tr>
					</thead>
					<tbody id="boardList">
					<tbody>
						<c:forEach var="list" items="${announcementList }">
							<tr>
								<td>${list.announcement_num }</td>
								<td>${list.c_type }</td>
								<td class="title"><a href="/customer/view?announcement_num=${list.announcement_num }" >${list.c_title }</a></td>
								<td>${list.postdate }</td>
							</tr>
						</c:forEach>
					</tbody>
					<!-- 일반반복문으로 출력 -->
					</tbody>
				</table>

				<div class="page_wrap">
					<span class="page_nation">
						<!-- 페이징 -->
						${paging}
					</span>
					<span class="write-bottom-wrap"style="float:right">
						<button type="submit" id="write-bottom" class="btn btn-blue top" style="height: 40px;">글쓰기</button>
					</span>
				</div><!-- board paging end -->
				<div style="clear:both"></div>
			</div>
		</div>
	</section>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>