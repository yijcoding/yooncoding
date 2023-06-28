<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.menu_select {
		margin:auto;
		width:auto;
	}
	
	.menu_select .text{
		width:auto;
		cursor: pointer;
		
	  
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
	  cursor: pointer;
	}
	
	.menu_select .option-list a {
	  	text-decoration: none;
		color:black;
		display:inline-block;
	}
	
	.menu_select.active .option-list {
	  max-height: none;
	}
	
	ul {
    list-style:none;
    margin:0;
    padding:0;
}

li {
    margin: 0 0 0 0;
    padding: 0 15px 0 0;
    border : 0;
    float: left;
}
</style>
<script type="text/javascript">

$(document).ready(function(){
	
	$(".text").on('click',() =>{
		let list = document.querySelector('.menu_select .option-list');
		
		if(list.style.overflow=="hidden" || list.style.overflow==""){
			list.style.overflow="visible";
		}else{
			list.style.overflow="hidden";
		}
	});
	
});
</script>
</head>
<body>
	<div class="menu_select">
		<span onclick="location.href='/'" style="cursor: pointer;"><img src="https://cdn-icons-png.flaticon.com/512/20/20176.png">|</span>
		<div class="text">
			<span><b>공지사항</b></span>
			<img src="https://cdn-icons-png.flaticon.com/512/3519/3519316.png">
		<ul class="option-list">
			<li><a href="/customer/announcement">공지사항</a></li>
			<li><a href="/customer/faq?f_type=">FAQ</a></li>
			<li><a href="/customer/announcementInquiry">문의하기</a></li>
			<li><a href="/customer/consultationDetails">상담내역</a></li>
		</ul>
		</div>
	</div>
</body>
</html>