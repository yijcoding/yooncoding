<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
	integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog=="
	crossorigin="anonymous" />
<script src="https://code.jquery.com/jquery-3.6.4.min.js"
	integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
	crossorigin="anonymous"></script>
	
<style type="text/css">

* {
	box-sizing: border-box;
}

body {
	background-color: #fff;
}

h1 {
	margin: 50px 0 30px 0;
	text-align: center;
}
.wrap{
	margin:100px 250px;
}
.customerMove{
	position:relative;
	width:100%;
}
.faq-container {
	max-width: 900px;
	margin: 0 auto;
}

.faq {
	background-color: transparent;
	border: 1px solid #9fa4a8;
	 box-shadow: 0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23);
	border-radius: 10px;
	margin: 20px 0;
	padding: 30px;
	position: relative;
	overflow: hidden;
	transition: 0.3s ease;
}

.faq.active {
	background-color: #fff;
 	box-shadow: 0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23);
}

.faq.active::before, .faq.active::after {
	content: '\f075';
	font-family: 'Font Awesome 5 Free';
	color: #2ecc71;
	font-size: 7rem;
	position: absolute;
	opacity: 0.2;
	top: 20px;
	left: 20px;
	z-index: 0;
}

.faq.active::before {
	color: #3498db;
	top: -10px;
	left: -30px;
	transform: rotateY(180deg);
}

.faq-title {
	margin: 0 35px 0 0;
}

.faq-text {
	display: none;
	margin: 30px 0 0;
}

.faq.active .faq-text {
	display: block;
}

.faq-toggle {
	background-color: transparent;
	border: 0;
	border-radius: 50%;
	cursor: pointer;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 16px;
	padding: 0;
	position: absolute;
	top: 30px;
	right: 30px;
	height: 30px;
	width: 30px;
}

.faq-toggle:focus {
	outline: 0;
}

.faq-toggle .fa-times {
	display: none;
}

.faq.active .faq-toggle .fa-times {
	color: #f0f0f0;
	display: block;
}

.faq.active .faq-toggle .fa-chevron-down {
	display: none;
}

.faq.active .faq-toggle {
	background-color: #9fa4a8;
}

.tab_wrap{
	clear:both;
	margin:100px auto;
	width:auto;
	height:75px;
	display: flex;
	justify-content: space-around;
	text-align:center;
	
	
	
}
	
ul.tabs{
	float:left;
	list-style: none;
}

ul.tabs li{
	display: inline-block;
	cursor: pointer;
	width:200px;
	border:1px solid grey;
}



ul.tabs li a{
	color:#666666;
	text-decoration: none;
	font-size:22px;
	
}

.page_nation_wrap{
	width:100%;
	text-align:center;
}
.page_nation {
	display:inline-block;
}
.page_nation .none {
	display:none;
}
.page_nation  a {
	display:block;
	margin:0 3px;
	float:left;
	width:50px;
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
	width:auto;
	padding: 0 8px;
}

</style>
<script type="text/javascript">

$(function(){
	
	
	
	$('.write_btn').on('click',()=>{
		const write = document.getElementById('write');
		//const writetest = !!document.getElementById('write');
		//console.log(writetest);
		//console.log(write);
		let write_style = write.style.display;
		console.log(write.style.display);
		console.log(write_style);
		
		if(write_style=='none'||write_style==''){
			console.log('너냐');
			write.setAttribute("style","display=''");
		}else{
			console.log('너냐');
			write.setAttribute("style","display='none'");
		}
	})
	
	$('.write_btn_compl').on('click',()=>{
		
		if($('.select').val()=='선택'){
			alert('분류를 선택해주세요');
		}else if($('.write_title').val()==''){
			alert('제목을 입력해주세요');
		}else if($('.write_content').val()==''){
			alert('내용을 입력해주세요');
		}else if(confirm("faq 추가 하시겠습니까?"))
			$.ajax({
				type:'post',
				datatype:'aplication/json',
				url:'/customer/faqWrite',
				data:{
					'title' : $('.write_title').val(),
					'content' : $('.write_content').val(),
					'f_type' : $('.select').val()
				},success : (data) =>{
					if(data=='1'){
						faqList()
					}
				}
				
			});//ajax끝
	});
	
	
	if(${param.f_type eq 1}){
		$('#li-1').css({"border-width":"1px 1px 0 1px","border-style":"solid","background":"#ededed","border-color":"#999999","font-weight":"bold","border-radius":"5px 5px 0 0"});
	}else if(${param.f_type eq 2}){
		$('#li-2').css({"border-width":"1px 1px 0 1px","border-style":"solid","background":"#ededed","border-color":"#999999","font-weight":"bold","border-radius":"5px 5px 0 0"});
	}else if(${param.f_type eq 3}){
		$('#li-3').css({"border-width":"1px 1px 0 1px","border-style":"solid","background":"#ededed","border-color":"#999999","font-weight":"bold","border-radius":"5px 5px 0 0"});
	}else if(${param.f_type eq 4}){
		$('#li-4').css({"border-width":"1px 1px 0 1px","border-style":"solid","background":"#ededed","border-color":"#999999","font-weight":"bold","border-radius":"5px 5px 0 0"});
	}else{
		$('#li-0').css({"border-width":"1px 1px 0 1px","border-style":"solid","background":"#ededed","border-color":"#999999","font-weight":"bold","border-radius":"5px 5px 0 0"});
	}
	
	
	faqList()
});


function faqList(){
	$.ajax({
		type:'get',
		datatype:'aplication/json',
		url:'/customer/getfaqList',
		data:{
			'f_type':'${param.f_type}',
			'start' : '${start}'
		},
		success:(data)=>{
			tr='';
			for(row of data){
			tr+='<div class="faq">';
					tr+='<p style="color:grey">'+row.f_type+'</p>';
					tr+='<h3 class="faq-title">'+row.title+'</h3>';
					tr+='<p class="faq-text">'+row.content+'</p>';
					tr+='<button type="button" class="faq-toggle" onclick="clickMe(this)" >';
					tr+='<i class="fas fa-chevron-down"></i> <i class="fas fa-times"></i>';
					tr+='</button>';
					tr+='<div class="ud">';
					<c:if test="${m_admin eq 1 }">
						tr+='<button class="update" onclick="faqUpdate('+row.faq_num+',this)" >관리자 수정</button>';
						tr+='<button class="delete" onclick="faqDelete('+row.faq_num+')">관리자 삭제</button>';
					</c:if>
					tr+='</div>';
				tr+='</div>';
			}
			$('.faq-container').html(tr);
		}
	})
}

// 펼치기
function clickMe(e){
		e.parentNode.classList.toggle("active");
}

function faqDelete(num){
	if(confirm('삭제하시겠습니까?')){
		$.ajax({
			type:'post',
			datatype:'aplication/json',
			url:'/customer/faqDelete',
			data:{
				'faq_num' : num
			},success : (data)=>{
				console.log(data);
				faqList()
			}
		})
	}
}

function faqUpdate(num,e){
	const faq=e.parentNode.parentNode;
	tr='<form action="/customer/faqUpdate" method="get">'
		+'<input type="hidden" name="faq_num" >'
		+'<input type="hidden" name="f_type">'
		+'제목 <br> <input type="text" name="title"class="update_title" id="" style="width:100%">'
		+'<br> 내용 <br> <textarea rows=""  cols="" name="content" class="update_content" style="width:100%"></textarea>'
		+'<button type="button" class="update_one" onclick="faqUpdateImpl('+num+')">수정</button>'
		+'<button type="button" class="cancle" onclick="faqList()">취소</button>'
		+'</form>';
		$(faq).html(tr);
		
}

function faqUpdateImpl(num){
	
	if($('.update_title').val()==''){
		alert('제목을 입력해주세요');
	}else if($('.update_content').val()==''){
		alert('내용을 입력해주세요');
	}else if(confirm('수정하시겠습니까?')){
		$.ajax({
			type:'post',
			datatype:'aplication/json',
			url:'/customer/faqUpdate',
			data:{
				'faq_num' : num,
				'title' : $('.update_title').val(),
				'content' : $('.update_content').val(),
				
				
			},success : (data)=>{
				console.log(data);
				faqList()
			}
		})
	}else{
		faqList()
	}
}
	
	


</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/menu.jsp" />
	
	<jsp:include page="/WEB-INF/views/board/advertisement_leftSide.jsp"/>
	<div class="wrap">
	<div class="customerMove">
		<jsp:include page="/WEB-INF/views/customerService/customerMove.jsp"/>
	
	</div>
			<div style="clear:both"></div>
		<div style="margin:30px 0"><h1>자주 묻는 질문</h1></div> 
		<div class="tab_wrap" >
				<div class="tab">
					<ul class="tabs">
						<li class="tab-link current" id="li-0"><a href="/customer/faq"><div style="width:100%;height:100%">전체</div></a></li>
						<li class="tab-link" id="li-1"><a href="/customer/faq?f_type=1"><div style="width:100%;height:100%">이용정보</div></a></li>
						<li class="tab-link" id="li-2"><a href="/customer/faq?f_type=2"><div style="width:100%;height:100%">우대정보/이벤트</div></a></li>
						<li class="tab-link" id="li-3"><a href="/customer/faq?f_type=3"><div style="width:100%;height:100%">예매</div></a></li>
						<li class="tab-link" id="li-4"><a href="/customer/faq?f_type=4"><div style="width:100%;height:100%">기타</div></a></li>
					</ul>
				</div>
			</div>
		<div class="faq-container">
			
		</div>
		<div class="page_nation_wrap">
			<div class="page_nation">
			${paging }
		</div>
		</div>
		<c:if test="${m_admin eq 1 }">
			<div class="write_form">
				
				<button type="button" class='write_btn' >관리자 글쓰기</button>
				<br>
				<div class="write" id="write" style="display:none;">
					<br>
					<p><select name="select" class="select">
						<option value='선택'>선택</option>
						<option value='이용정보'>이용정보</option>
						<option value='예매'>예매</option>
						<option value='우대정보/이벤트'>우대정보/이벤트</option>
						<option value='기타'>기타</option>
					</select></p>
					제목
					<P><input type="text" class="write_title" style="width:474px"></P>
					내용
					<p><textarea rows="5" cols="50" class="write_content"></textarea></p>
					<button class="write_btn_compl">글쓰기</button>
				</div>
			</div>
		</c:if>
	</div>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
	
	<form action="customer/updateFaq" method="post" id="update"></form>
</body>
</html>