<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"
	integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
	crossorigin="anonymous"></script>
<link href="<c:url value="/resources/css/board.css" />" rel="stylesheet" />
<style type="text/css">
#bottom_form{
	margin:30px 0; 
}
.page_wrap {
	width:100%;
	text-align:center;
	font-size:0;
	float:left;
	margin:30px 0;
	
 }
.page_nation {
	display:inline-block;
	padding:0 0 0 20%;
}
.page_nation .none {
	display:none;
}
.page_nation a {
	display:block;
	margin:0 3px;
	float:left;
	width:28px;
	height:28px;
	line-height:28px;
	text-align:center;
	background-color:#fff;
	font-size:13px;
	color:#999999;
	text-decoration:none;
}

.page_nation .page-item a:hover,.page_nation .page-item a:focus {
	text-decoration:underline;
	color:#3333FF;
}

.page_nation .page-item a{
	border:1px solid #e6e6e6;
	color:3399FF;
}
.write_form_wrap{
  width:100%;
  max-width:1100px;
  margin:auto;
  height:20px;
}
.write_form{
	float:right;
}
.btn-board-top{
	background:#fff;
	border:2px solid 000099;
	width:90px;
	height:30px;
	font-size:13px;
}


</style>
<script type="text/javascript">
	
$(document).ready(function(){
	boardList()
	
	$(function(){
		var form = document.getElementById("#write_form");
		$('#write-top').on('click',function(){
			location.href="/board/createBoard";
		});
		
		$('#write-bottom').on('click',function(){
			location.href="/board/createBoard";
		});
	});
		
});
	
	function boardList(){
		var select=$('select[name=select]').val();
		var search=$('input[name=search]').val();
		
		$.ajax({
			type:'get',
			url:'/board/boardList',
			datatype:'json',
			data:{
				'select' : $('select[name=select]').val(),
				'search' : $('input[name=search]').val(),
				'start' : '${startend.start}'
			},success : function(data){
				console.log(data);
				tr="";
				for(row of data){
					tr+="<tr>";
					tr+="<td>"+row.board_id+"</td>";
					tr+="<td style='text-align:left;padding:0 0 0 10px'><a href='/board/view?board_id="+row.board_id+"'>"+row.b_title+"</a></td>";
					tr+="<td>"+row.member_id+"</td>";
					tr+="<td>"+row.postdate+"</td>";
					tr+="<td>"+row.visitcount+"</td>";
					tr+="</tr>";
				}
				boardPaging(data[0]["cnt"],select,search)
				$('#boardList').html(tr);
			}
			
		});
	} 
	
	function boardPaging(cnt,select,search){
		$.ajax({
			type:'get',
			url:'/board/boardPaging',
			data:{
				'select' : select,
				'search' : search,
				'cnt' : cnt,
				'start' : '${startend.start}'
			},success : function(data){
				console.log(data);
				
				tr="["+data+"]";
				
				$('.page_nation').html(tr);
			}
		
		});
	} 
	
	
</script>
</head>
<body>
	<section class="notice">
		<div class="page-title">
		</div>
			<div class="write_form_wrap">
				<div class="write_form">
					<button type="submit" id="write-top" class="btn-board-top" ><img src="https://cdn-icons-png.flaticon.com/512/5218/5218705.png" style="width:15px; top:5px">글쓰기</button>
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
							<th scope="col" class="th-date"style="width:35%">조회수</th>
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
				
				<!-- board paging start-->
				<div class="page_wrap">
					<span class="page_nation"></span>
					<span style="float:right">
						<button type="submit" id="write-bottom" class="btn btn-dark top" style="height: 40px;">글쓰기</button>
					</span>
				</div><!-- board paging end -->
				
			</div>
		</div>
		<div style="clear:both;"></div>
		<!-- board search area -->
		<div id="board-search">
			<div class="container">
				<div class="search-window">
					<div class="search-wrap">

						<select name="select"
							style="width: 20%; height: 40px; float: left; text-align: center; font-size: 14px;">
							<option value="b_title">제목</option>
							<option value="b_content">내용</option>
							<option value="member_id">작성자</option>
						</select> 
						<label for="search" class="blind">공지사항 내용 검색</label> 
						<input id="text" type="search" name="search" value="${param.search }">
						<button type="button"  class="btn btn-dark" onclick="boardList()">검색</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	
</body>
</html>