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
		
	
</style>
<script type="text/javascript">

$(document).ready(function() {
    
    $('#board_update').on('click', function() {
    	
    	
    	location.href='/customer/updateAnnouncement?announcement_num=${view.announcement_num}';
    });
    
    $('#board_delete').on('click', function() {
    	
    	if(confirm("글을 삭제하시겠습니까?")){
    		
    	location.href='/customer/deleteAnnouncement?announcement_num=${view.announcement_num}';
    	}
    });

	
	$("#write-bottom").on('click',() =>{
		
		location.href="/customer/insertAnnouncement";
	});
    
	
});





</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/menu.jsp"/>
<div class='wrap'>
	
	<div id="view-wrap">
	<div class="customerMove">
		<jsp:include page="/WEB-INF/views/customerService/customerMove.jsp"/>
	</div>
	<div style="clear:both"></div>
		<!--상단 -->
		<div class="header">
			<div class="header-inner" >
				<p style="margin-bottom:10px;font-size:20px;"><b>${view.c_title}</b></p> 
				
				<span>${fn:replace(view.postdate, 'T',' ')}</span>
			</div>
		</div>
		
		<hr><br>
		<div class="main-content">
			<c:forEach var="img" items="${boardImg}" >
				<div class="image-wrap">
					<img alt="" src="${img.boardImg }" style="margin:20px 0; max-width:1000px">
				</div>
			</c:forEach>


			<br> 	
			
			<div>${view.c_content }</div>
			
			<c:if test="${m_admin eq 1 }">
				<div class='view-btn'>
					<input type="button" class='btn btn-blue' id="board_delete"  value='삭제'>
					<input type="button" class='btn btn-blue' id="board_update"  value='수정'>
				</div>
			</c:if>
		</div>
		<div class="list-wrap">
			<input type="button" class='' id="listback" onclick="location.href='/customer/announcement'" value='목록'>
		</div> 
		
		<hr style="border:0; ">
		<!-- comment List -->	
	</div>	
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"/>

</body>
</html>