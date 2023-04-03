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
<script type="text/javascript">

	$(document).ready(function(){
		boardList()
	});
	
	function boardList(){
		$.ajax({
			type:'get',
			url:'/board/boardList',
			datatype:'json',
			data:{
				'select' : $('select[name=select]').val(),
				'search' : $('input[name=search]').val()
			},success : function(data){
				console.log(data);
				tr="";
				for(row of data){
					tr+="<tr>";
					tr+="<td>"+row.board_id+"</td>";
					tr+="<td><a href='/board/view?board_id="+row.board_id+"'>"+row.b_title+"</a></td>";
					tr+="<td>"+row.member_id+"</td>";
					tr+="<td>"+row.postdate+"</td>";
					tr+="</tr>";
				}
				
				$('#boardList').html(tr);
			}
		
		});
	} 
</script>
</head>
<body>
	<section class="notice">
		<div class="page-title">
			<div class="container">
				<h3>게시판</h3>
			</div>
		</div>

		<!-- board search area -->
		<div id="board-search">
			<div class="container">
				<div class="search-window">
						<div class="search-wrap">
							<select name="select" style="width:20%;height:40px;float:left;text-align:center; font-size:14px;">
								<option value="b_title">제목</option>
								<option value="b_content">내용</option>
								<option value="member_id">작성자</option>
							</select> 
							<label for="search" class="blind">공지사항 내용 검색</label> <input
								id="search" type="search" name="search" placeholder="검색어를 입력해주세요."
								>
							<button type="button" class="btn btn-dark" onclick="boardList()">검색</button>
						</div>
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
					<tbody id="boardList">
							
					</tbody>
				</table>
				<!-- 글쓰기 -->
				<form action="/board/createBoard">
					<div style="float:right">
						<button type="submit" class="btn btn-dark" style="height:40px;">글쓰기</button>
					</div>
				</form>
			</div>
		</div>

	</section>
</body>
</html>