<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
<link href="<c:url value="/resources/css/board.css" />" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<style type="text/css">
	html,body{
		width:100%;
		height:100%;
	}
	.wrap{
		position: relative;
		/*   padding-right: 124px; */
		margin: auto;
		width:100%;
	}
	
	.customerMove{
		position:relative;
		width:100%;
	}
	.aside-right-wrap{
		position:sticky;
		float:right;
		border:1px solid black;
		width:200px;
		height:600px;
	}
	
	
	#view-wrap,.comment-wrap {
		position: relative;
		/*   padding-right: 124px; */
		
		width:100%;
		max-width:1100px;
		margin: 50px 0 150px 250px;
		
	}
    #clear{
    	clear:both;
    }
    
	
	
	page_wrap write-bottom-wrap .btn-blue, .view-btn .btn-blue,#comment_cnt .btn-blue {
		background-color:#4aa8d8;
		color: #fff;
		height:40px;
		border:2px solid black;

	}
	
	.page_wrap .write-bottom-wrap .btn-blue:hover, .page_wrap .write-bottom-wrap .btn-blue:focus,.view-btn .btn-blue:hover,.view-btn .btn-blue:focus,#comment_cnt .btn-blue:hover,#comment_cnt .btn-blue:focus{
		background-color:#298cbf;
		color: #fff;
		border:2px solid black;
	}
	
	.btn-blue {
		background-color:#4aa8d8;
		color: #fff;
		height:40px;
		border:2px solid black;

	}
	
	.btn-blue:hover, .btn-blue:focus {
		background-color:#298cbf;
		color: #fff;
		border:2px solid black;
	}
	
	.btn-white {
	  background: #ffffff;
	  color: #555;
	}
	
	.btn-white:hover, .btn-blue:focus {
	  background: #white;
	  border-color: #373737;
	  color: #373737;
	}
	
	.header-inner{
	 	margin:20px; 
	 	width:1100px;
	 	float:left;
	}

	
		
	
	.view-btn{
		width:1100px;
		text-align:right;
	}
	
	hr{
		width:1100px;
		background:Gainsboro; 
		height:1px;
		border:0;
		margin:20px 0;
	}
	
	.file{
		width:1100px;
		border:1px solid Gainsboro;
		margin:20px 0;
		padding: 10px 5px;
	}
	
	.image-wrap img{
		max-width:700px;
		width:100%;
	}
	
	td{
		border-bottom:1px solid Gainsboro;
		padding: 10px 0;
	}
	
	
	.list-wrap{
		text-align: center;
		margin:0 0 50px 0;
	}
	
	#listback{
		font-size:28px;
		width:200px;
		height:75px;
		border-radius:45px;
		color:#fff;
		background:#4f87ba;
	}
	
	.movemenu{
		width:150px;
		margin: 0 auto;
		float:left;
	}
		
	.content{
		height:100%;
		width:100%;
	}
	.content-inner{
		height:100%;
		width:100%;
	}
	
	.image-inner-wrap{
		width:200px;
		display:inline-block;
	}
	
	.image-wrap{
		border: 1px solid #eee;
		padding: 0 10px;
		margin: 0 0 10px 0;
	}
	
	.heder-inner-inner a{
		text-decoration: none;
		color:black;
	}
</style>
<script type="text/javascript">

$(document).ready(function() {
	
    
    $('#board_delete').on('click', function() {
    	
    	if(confirm("글을 삭제하시겠습니까?")){
    		location.href="/customer/deleteconsultationView?inquiry_num=${view[0].inquiry_num}";
    	}
    });
    
    return false;
	
});


function answer(){
	if(confirm('작성하시면 수정하실 수 없습니다!')){
		
		if($('#adminAnswerContent').val()==''){
			alert('답변내용이 비어있습니다!!!!!');
			return false;
		}	
	}
}


</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/menu.jsp"/>
<div class='wrap'>
	
	<div id="view-wrap">
	<div class="customerMove">
		<jsp:include page="/WEB-INF/views/customerService/customerMove.jsp"/>
	</div>
		<!--상단 -->
		<div class="header">
			<div class="header-inner" >
				<p style="font-size:20px;float:left;"><b>${view[0].b_title}</b></p> 
				<div class="heder-inner-inner"style="clear:both;">
					<br>
					<span style="margin-bottom:10px;font-size:15px;"><a href="/customer/consultationDetails?member_id=${view[0].member_id}">${view[0].member_id}</a></span> 
					<span style="float:right">${view[0].postdate}</span>
				</div>
			</div>
		</div>
		
		<hr><br>
		<div class="main-content">
			

			<br> 	
			<div class="image-wrap">
					<c:forEach var="img" items="${boardImg}" >
						<div class="image-inner-wrap">
							<a href="${img.boardImg }"><img alt="" src="${img.boardImg }" style="margin:20px 0; max-width:1000px"></a>
						</div>
					</c:forEach>
				</div>
			<div class="content">
				<div class="content-inner">
					<c:forEach var="list" items="${view}">
						<div style="height:100%; min-height:400px; background:#eeeeee; padding:10px">
							${list.b_content }
						</div>
						<hr style="margin:50px 0; border:1px solid grey">
					</c:forEach>
				</div>
				
				
				<!-- 답변일 없을시  -->
			<c:if test="${m_admin eq 1 }">
				<c:if test="${view[0].cnt eq 1 }">
					<div>
						<form action="/customer/consultationView" method="post" id="adminAnswer" onsubmit="return answer()">
							<p>관리자 답변</p>
							<textarea rows="10" name="b_content" id="adminAnswerContent"style="width:100%"></textarea>
							<input type="submit" value="관리자 답변">
							<input type="hidden" name="inquiry_num" value="${view[0].inquiry_num }">
							<input type="hidden" name="b_title" value="${view[0].b_title }">
						</form> 
					</div>
				</c:if>
			</c:if>
				
				
			</div>
			
			<!-- 답글 -->
			<c:if test="${m_admin eq 1 }">
				<div class='view-btn'>
					<input type="button" class='btn btn-blue' id="board_delete"  value='삭제'>
				</div>
			</c:if>
			</div>
			<div class="list-wrap">
				<input type="button" class='' id="listback" onclick="location.href='/customer/consultationDetails'" value='목록'>
			</div> 
		<hr style="border:0; ">
	</div>	
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"/>

</body>
</html>