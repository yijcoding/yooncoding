<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View</title>
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
		margin: 0 auto;
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
		margin: 0 375px;
		
	}
	#emoticons img{
		width:20px;
	}
	#comment{
		width:100%;
		max-width:1100px;
		height:150px;
		resize: none;
		font-size:15px;
	}
	#table{
		width:100%;
	}
	#emoticons{
	
		width:100%;
		max-width:1100px;
		float:left;
	}
	#comment_cnt{
		clear:both;
		width:30%;
		max-width:250px;
		text-align:right;
		float:right;
    };
    
   #comment-insert, #comment-insert2{
    	position: relative;;
        top: 80px;
    }
    
    #clear{
    	clear:both;
    }
    
	#commentList,#commentform{
		width:1100px;
		padding:0 0 20px 0;
	}
	
	#file{
		margin:50px 0; 
		border:1px solid black;
		padding:20px 0;
		width:1100px;
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
	
	.image-wrap,img{
		max-width:700px;
	}
	
	td{
		border-bottom:1px solid Gainsboro;
		padding: 10px 0;
	}
	
	
	.advertisement1{
		width:680px;
		margin:0 auto;
	}
	
	#advertisement-jouen{
		width:600px;
		border:1px solid black;
		margin:0 auto;
		margin-top:50px;
	}
	
	#commentMove-top {
	  width: 75px;
	  height: 25px;
	  font-size:12px;
	  font-family: 'Nanum Gothic';
	  text-align: center;
	  border: solid 2px grey;
	  border-radius: 12px;
	  margin:0 0 0 10px;
	}
	
	#visitcount-top,#commentMove-top{
	  font-size:15px;
	  font-family: 'Nanum Gothic';
	}
	
	#table{
		border-top: 3px solid #4aa8d8;
		border-bottom: 3px solid #4aa8d8;
		margin: 10px 0 0 0;
	}
	
	
	ul.tabs{
	float:left;
		list-style: none;
	}
	
	ul.tabs li{
		display: inline-block;
		padding: 10px 15px;
		cursor: pointer;
		
		
	}
	
	
	
	ul.tabs li a{
		color:#666666;
		text-decoration: none;
	}
	
	.btn-search{
		background:#555;
		color:#fff;
		hight:40px;
	}
	
	#boardList td a{
		text-decoration: none;
		color:black;
	}
	
	.b_content{
		width:900px;
		max-width:900px;
		height:auto;
	}
	
	.favorite{
		width:auto;
		height:100px;
		display: inline-block;
		padding:25px 0;
	}
	
	.favorite-wrap{
		text-align: center;
	}
	
	.comment-show{
		cursor:pointer;
	}
	
	.paging{
		width:100%;
		text-align:center;
		float:left;
	}
	
	.commentPaging {
		display:inline-block;
	}	
	
	.commentPaging a {
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
		cursor:pointer;
	}
	
	
</style>
<script type="text/javascript">

$(document).ready(function() {
    $('#comment').on('keyup', function() {
        $('#comment_cnt').html("("+$('#comment').val().length+" / 200)<input type='button' class='btn btn-blue' name='comment-insert' id='comment-insert' onclick='commentInsert()' value='작성'>");
 
        if($('#comment').val().length > 200) {
            $('#comment').val($('#comment').val().substring(0, 200));
            $('#comment_cnt').html("(200 / 200)");
            alert('댓글 200자 제한');
        }
    });
    
    $('#comment-update').on('keyup', function() {
        $('#comment_cnt').html("("+$('#comment-update').val().length+" / 200)<input type='button' class='btn btn-blue' name='comment-insert' id='comment-insert' onclick='commentInsert()' value='작성'>");
 
        if($('#comment-update').val().length > 200) {
            $('#comment-update').val($('#comment-update').val().substring(0, 200));
            $('#comment_cnt').html("(200 / 200)");
            alert('댓글 200자 제한');
        }
    });
    
    $('#board_update').on('click', function() {
    	
    	
    	location.href='/board/updateBoard?board_id=${boardView.board_id}';
    });
    
    $('#board_delete').on('click', function() {
    	
    	if(confirm("글을 삭제하시겠습니까?")){
    		
    	location.href='/board/deleteBoard?board_id=${boardView.board_id}';
    	}
    });
    
    
    $('.page-link').on('click',() => {
    	var commentListMove = document.getElementById('commentList');
    	commentListMove.scrollIntoView({behavior:'smooth'});
    	alert('1111111111111111');
    });
    
    $('#commentMove-top').on('click',() => {
    	var commentListMove = document.getElementById('commentList');
    	commentListMove.scrollIntoView({behavior:'smooth'});
    });
    
    
    $('#commentHide').on('click',() => {
    	var commentListDisplay = document.getElementById('table');
	   	var commentHide = document.getElementById('commentHide');
	   	if(commentListDisplay.style.display =='none'){
	   		commentListDisplay.style.display="";
		   	commentHide.innerText = '댓글 접기';
	   	}else{
			commentListDisplay.style.display="none";
	    	commentHide.innerText = '댓글 펼치기';
	    }
	   
   
    });
   
  
   
    $('ul.tabs li').click(function(){

    	$('ul.tabs li').removeClass('current');

    		$(this).addClass('current');
    		
    		    
    	})

    	
    	$(function(){
    		var form = document.getElementById("#write_form");
    		$('#write-top').on('click',function(){
    			if(${member_id ne null}){
    				location.href="/board/createBoard";
    			}else{
    				alert('로그인 해주세요!');
    				location.href="/login";
    			}
    		});
    		
    		$('#write-bottom').on('click',function(){
    			if(${member_id ne null}){
    				location.href="/board/createBoard";
    			}else{
    				alert('로그인 해주세요!');
    				location.href="/login";
    			}
    		});
		});
	
	if(${param.b_type eq 1}){
		$('#li-1').css({"border-width":"1px 1px 0 1px","border-style":"solid","background":"#ededed","border-color":"#999999","font-weight":"bold","border-radius":"5px 5px 0 0"});
	}else if(${param.b_type eq 2}){
		$('#li-2').css({"border-width":"1px 1px 0 1px","border-style":"solid","background":"#ededed","border-color":"#999999","font-weight":"bold","border-radius":"5px 5px 0 0"});
	}else if(${param.b_type eq 3}){
		$('#li-3').css({"border-width":"1px 1px 0 1px","border-style":"solid","background":"#ededed","border-color":"#999999","font-weight":"bold","border-radius":"5px 5px 0 0"});
	}else if(${param.b_type eq 4}){
		$('#li-4').css({"border-width":"1px 1px 0 1px","border-style":"solid","background":"#ededed","border-color":"#999999","font-weight":"bold","border-radius":"5px 5px 0 0"});
	}else{
		$('#li-0').css({"border-width":"1px 1px 0 1px","border-style":"solid","background":"#ededed","border-color":"#999999","font-weight":"bold","border-radius":"5px 5px 0 0"});
	}
	
	
	
	
	boardList();
    commentList();
    favorite();
});



function emo(img) {
    var TA = img.parentNode.previousElementSibling;
    var T = TA.value;
    console.log(img.alt);
    var textBefore = '';
    var textAfter = '';
    if (img.tagName == "IMG") {
      textBefore = ' ' + img.alt + ' ';
      textAfter = '';
    }
    if (document.selection) {
      TA.focus();
      document.selection.createRange().text = textBefore + document.selection.createRange().text + textAfter;
    } else if (TA.selectionStart || TA.selectionStart == '0') {
      var startPos = TA.selectionStart;
      var endPos = TA.selectionEnd;
      TA.value = T.slice(0, startPos) + textBefore + T.slice(startPos, endPos) + textAfter + T.slice(endPos);
      TA.focus();
      TA.selectionStart = startPos + textBefore.length;
      TA.selectionEnd = endPos + textBefore.length;
    }
  }
  


function commentInsert(){
	if(${member_id eq null}){
		alert("로그인해주세요!");
		location.href="/login";
	}else if($('#comment').val()==''){
		alert("빈 내용은 작성하실 수 없습니다");
	}else{
		$.ajax({
			type:'get',
			url:'/board/reply-insert',
			datatype:'json',
			data:{
				'b_reply':$('#comment').val(),
				'board_id':"${boardView.board_id}", 
				'member_id':'${member_id}' 
			},success : function(data){
				if(data == 1){
					alert('댓글 추가완료');
					document.getElementById("comment").value=''
				    commentList();
				}
			}
		
		});
	}
	
}


	
function commentList(e){
	console.log(e);
	$.ajax({
		type:'get',
		url:'/board/replyList',
		data:{
			'board_id':"${boardView.board_id}",
			'pageNum' : e
		},success : function(data){
			console.log(data);
			tr='';
			paging = '';
			console.log(data[0]['cnt']);
			console.log('으아아아아아아아아아아아아');
			var member_id ='${member_id}';
			if(data[0]['cnt']!=0){
				tr+="<table id='table'>";
				paging = data[0]["paging"];
				for(row of data){
					var row_id = row.member_id;
					tr+="<tbody>"
						console.log(row.member_id);
						if(row.seq==0){
							tr+="<tr id='reply"+row.reply_num+"' >";
								tr+="<td style='width:20%;text-align:center' colspan='2'>"+row.member_id+"</td>";
								tr+="<td style='width:30%; min-height:200px;'>"+row.b_reply+"</td>";
								tr+="<td style='width:25%;text-align:center'>"+row.postdate+"</td>";
								tr+="<td style='width:10%;text-align:center'><button type='button' class='btn btn-white' onclick='re_reply(this,"+row.ref+","+row.seq+")'>답글달기</button></td>";
								if(${m_admin eq 1} || member_id === row_id){
									tr+="<td style='width:5%;text-align:center'><button onclick='comment_update("+row.reply_num+","+'row.member_id'+","+row.ref+","+row.seq+")' class='btn btn-white' id='comment_update'>수정</button></td>";
									tr+="<td style='width:5%;text-align:center'><button onclick='comment_delete("+row.reply_num+")' class='btn btn-white' id='comment_delete'>삭제</button></td>";
								}
							tr+="</tr>";
						}else{
							tr+="<tr id='reply"+row.reply_num+"'style='background:#eee;'>";
							tr+="<td style='width:8%;text-align:center;'></td>";
							tr+="<td style='width:12%;text-align:center;'><img src='https://e7.pngegg.com/pngimages/230/115/png-clipart-computer-icons-arrow-symbol-arrow-angle-triangle.png' style='width:30px'>"+row.member_id+"</td>";
							tr+="<td style='width:30%; min-height:200px;'>&emsp;"+row.b_reply+"</td>";
							tr+="<td style='width:25%;text-align:center'>&emsp;"+row.postdate+"</td>";
							tr+="<td style='width:10%;text-align:center'><button type='button' class='btn btn-white' onclick='re_reply(this,"+row.ref+","+row.seq+")'>답글달기</button></td>";
							if(${m_admin eq 1} || member_id === row_id){
								tr+="<td style='width:5%;text-align:center'><button onclick='comment_update("+row.reply_num+","+'row.member_id'+","+row.ref+","+row.seq+")' class='btn btn-white' id='comment_update'>수정</button></td>";
								tr+="<td style='width:5%;text-align:center'><button onclick='comment_delete("+row.reply_num+")' class='btn btn-white' id='comment_delete'>삭제</button></td>";
							}else{
								tr+="<td style='width:5%;text-align:center'></td>";
								tr+="<td style='width:5%;text-align:center'></td>";
							}
						tr+="</tr>";										
					}
					tr+="</tbody>"
				}
				tr+="</table>";
				$('.commentPaging').html(paging);
				$('#table').html(tr);
			}else{
				$('#table').html(tr);
			}
		
		}
	
	});
}


function comment_update(num,id,ref,seq){
	console.log(num);
	var id1=id;
	if(confirm('작성하신 댓글을 수정 하시겠습니까?')){
		tr="<table id='table'> ";
		tr+="<thead>";
			tr+="<tr>";
			tr+="<td style='width:20%;text-align:center'>"+id1+"</td>";
			tr+="<td style='width:40%' colspan='4'>"
			+'<textarea name="comment-update" class="comment-update" cols="" rows="4" style="width:80%;height:100px;padding: 10px 0 0 10px;resize:none"></textarea>'

			+'<div id="emoticons" style="float:left; width:30%">'
			+'이모티콘: <br><img onclick="emo(this);"'
					+'src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgDOF9QXLzGS_NHTBGgV9RPiBY8_QwVmHlN6vvCdrIvQ&s"'
					+'alt="&#128512" title=":)">'
					 +'<img onclick="emo(this);"'
					 +'src="https://em-content.zobj.net/thumbs/120/google/350/grinning-face-with-smiling-eyes_1f604.png"'
					 +'alt="&#128513" title=";)"> <img onclick="emo(this);"'
					 +'src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/Emojione_1F602.svg/167px-Emojione_1F602.svg.png"'
					 +'alt="&#128514" title=":P"> <img onclick="emo(this);"'
					 +'src="https://as2.ftcdn.net/v2/jpg/01/95/45/25/1000_F_195452530_bYg7cpLNVOSO9tHDv6gD5ixlAuYC6kTY.jpg"'
					 +'alt="&#128517" title="8D"> <img onclick="emo(this);"'
					 +'src="https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRYKBGcZI4LwEUKj7hG7IUIOS1LjdNtQnx-kt8gLXZPKR4TJ3aU"'
					 +'alt="&#128561" title=":("> <img onclick="emo(this);"'
					 +'src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSjhc7gkbcSy9YJX7ZP5QiP1WBMTkc3iJF0Bu_7vNof9WauDkQo"'
					 +'alt="&#128565" title="--;">'
			+'</div>'
			+'<div id="comment_cnt" style="float:right;width:40%">'
			+"<button onclick='comment_update_commit("+ref+","+seq+")' class='btn btn-white' id='comment_update'>수정</button>"
			+"<button onclick='update_cancle()' class='btn btn-white' id='comment_update_cancle'>취소</button>"
			+'</div>'
			+'<div style="clear:both;margin:0 0 30px 0">'
			+'</div>'
			tr+="</td>";
			tr+="<td style='width:5%'></td>";
			tr+="<td style='width:5%'></td>";
			tr+="</tr>";
		tr+="</thead>";
		tr+="</table>";
		
	$('#reply'+num).html(tr);
		
	}
}




function comment_update_commit(ref,seq){
console.log(ref)
console.log(seq)
	if(confirm('정말 댓글을 수정 하시겠습니까?')){
		$.ajax({
			type:'post',
			url:'/board/replyUpdate',
			datatype:'json',
			data:{
				'ref':ref,
				'seq':seq,
				'b_reply':$('.comment-update').val()
			},success : function(data){
				commentList()
			}
		
		});
	}
}

function comment_delete(num){
	if(confirm('작성하신 댓글을 삭제 하시겠습니까?')){
		$.ajax({
			type:'get',
			url:'/board/replyDelete',
			datatype:'json',
			data:{
				'reply_num':num 
			},success : function(data){
				commentList()
			}
		
		});
	}
}


function update_cancle(){
	if(confirm('댓글 수정을 취소하시겠습니까?')){
		commentList()
		
	}
}


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
			'start' : '${startend.start}',
			'end' : '${startend.end}',
			'b_type' : '${param.b_type}'
		},success : function(data){
			console.log(data);
			tr="";
			for(row of data){
				tr+="<tr>";
				tr+="	<td>"+row.board_id+"</td>";
				tr+="	<td>"+row.b_type+"</td>";
				tr+="	<td style='text-align:left;padding:0 0 0 10px'><a href='/board/view?board_id="+row.board_id+"'>"+row.b_title+"</a></td>";
				tr+="	<td>"+row.member_id+"</td>";
				tr+="	<td>"+row.postdate+"</td>";
				tr+="	<td>"+row.visitcount+"</td>";
				tr+="</tr>";
			}
			if(data.length !=0){
				boardPaging(data[0]["cnt"],select,search)
			}else{
				boardPaging(0,select,search)
			}
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
			'start' : '${startend.start}',
			'b_type' : '${param.b_type}'
		},success : function(data){
			console.log(data);
			
			tr="["+data+"]";
			
			$('.page_nation').html(tr);
		}
	
	});
} 


function favorite(){
	$.ajax({
		type:'get',
		url:'/board/favoriteBoard',
		datatype:'aplication/json',
		data:{
			'board_id' : '${boardView.board_id}'
		},success : function(data){
			console.log(data);
			tr=''
			tr+='<span>'+data.favorite+'<span>';
			tr+='<span style="margin:0 10px"><span>';
			tr+='<span onclick="favoriteEvent(1)"><img src="https://cdn-icons-png.flaticon.com/512/4411/4411029.png"style="width:75px"></span>';
			tr+='<span onclick="favoriteEvent(2)"><img src="https://cdn-icons-png.flaticon.com/512/4510/4510629.png" style="width:75px"></span>';
			tr+='<span style="margin:0 10px"><span>';
			tr+='<span>'+data.hate+'<span>';
			
			$('.favorite').html(tr);
		}
	});		
}
	
function favoriteEvent(rs){
	if(${member_id eq null}){
		alert("로그인해주세요!");
	}else{
		$.ajax({
			type:'post',
			url:'/board/favoriteBoard',
			datatype:'aplication/json',
			data:{
				'board_id' : '${boardView.board_id}',
				'data' : rs,
				'member_id' :'${member_id}' ,
				'favorite' : '${boardView.favorite}',
				'hate' : '${boardView.hate}'
			},success : function(data){
				favorite();
			}
		});	
	}
	
		
}

function re_reply(e,ref,seq){
	
	if(${member_id eq null}){
		alert('로그인해주세요!');
		location.href="/login";
	}else{
		const re_reply_append = e.parentNode.parentNode.parentNode;
		console.log(re_reply_append);
		tr="<tr style='background:#eee;width:1100px;' >";
		tr+="<td style='width:20%;text-align:center'>${member_id}</td>";
		tr+="<td style='width:40%' colspan='4'>"
		+'<textarea name="comment-update" class="re_comment" cols="" rows="4" style="width:80%;height:100px;padding: 10px 0 0 10px;resize:none"></textarea>'

		+'<div id="emoticons" style="float:left; width:30%">'
		+'이모티콘: <br><img onclick="emo(this);"'
				+'src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgDOF9QXLzGS_NHTBGgV9RPiBY8_QwVmHlN6vvCdrIvQ&s"'
				+'alt="&#128512" title=":)">'
				 +'<img onclick="emo(this);"'
				 +'src="https://em-content.zobj.net/thumbs/120/google/350/grinning-face-with-smiling-eyes_1f604.png"'
				 +'alt="&#128513" title=";)"> <img onclick="emo(this);"'
				 +'src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/Emojione_1F602.svg/167px-Emojione_1F602.svg.png"'
				 +'alt="&#128514" title=":P"> <img onclick="emo(this);"'
				 +'src="https://as2.ftcdn.net/v2/jpg/01/95/45/25/1000_F_195452530_bYg7cpLNVOSO9tHDv6gD5ixlAuYC6kTY.jpg"'
				 +'alt="&#128517" title="8D"> <img onclick="emo(this);"'
				 +'src="https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRYKBGcZI4LwEUKj7hG7IUIOS1LjdNtQnx-kt8gLXZPKR4TJ3aU"'
				 +'alt="&#128561" title=":("> <img onclick="emo(this);"'
				 +'src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSjhc7gkbcSy9YJX7ZP5QiP1WBMTkc3iJF0Bu_7vNof9WauDkQo"'
				 +'alt="&#128565" title="--;">'
		+'</div>'
		+'<div id="comment_cnt" style="float:right;width:40%">'
		+"<button onclick='comment_re_reply_commit("+ref+","+seq+")' class='btn btn-white' id='reComment_update'>댓글달기</button>"
		+"<button onclick='update_cancle()' class='btn btn-white' id='comment_update_cancle'>취소</button>"
		+'</div>'
		+'<div style="clear:both;margin:0 0 30px 0">'
		+'</div>'
		+'</td>';
		tr+="<td style='width:5%'></td>";
		tr+="<td style='width:5%'></td>";
		tr+="</tr>";
	
		$(re_reply_append).append(tr);
	}
	
}

function comment_re_reply_commit(ref,seq){
	if($('.re_comment').val()==''){
		alert('빈 내용을 작성하실 수 없습니다');
	}else{
		$.ajax({
			type:'post',
			url:'/board/insertReReply',
			data:{
				'ref' : ref,
				'seq' : seq,
				'b_reply' : $('.re_comment').val(),
				'member_id' : '${member_id}',
				'board_id' : '${boardView.board_id}'
			},success:(data) => {
				console.log(data);
				commentList();
			},error:(request, status, error)=>{
	
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	
			}
		});
	}
}
		


</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/menu.jsp"/>
<div class='wrap'>
	<jsp:include page="/WEB-INF/views/board/advertisement_leftSide.jsp"/>
	<div id="view-wrap">
		<!--상단 -->
		<div class="header">
			<div class="header-inner" >
				<p style="margin-bottom:10px;font-size:20px;"><b>${boardView.b_title}</b></p> 
				<span>${boardView.member_id} |</span>
				<span>${fn:replace(boardView.postdate, 'T',' ')}</span>
				<span style="float:right"><button type="button" id="commentMove-top">댓글 ${boardReplyCnt}</button></span>
				
				<span style="float:right" id="visitcount-top">조회수 ${boardView.visitcount}  | </span>	
			</div>
		</div>
		
		<hr><br>
		<div class="main-content">
			<c:forEach var="img" items="${boardImg }" >
				<div class="image-wrap">
					
					<img alt="" src="${img.boardImg }" style="margin:20px 0; max-width:800px">
				</div>
			</c:forEach>
			
			
				
				
	
			<br> 	
			
			<div style="width:1050px; padding:0 0 0 10px">${boardView.b_content }</div>
			
			<!--추천 -->
			<div class="favorite-wrap">
				<div class="favorite" ></div>
				<div style="margin: 30px 15px 0 0">
					<jsp:include page="/WEB-INF/views/kakaoApi.jsp"/>
				</div>
			</div>
			
			<!-- 가로 광고 -->
			<div class="advertisement1">
				<a href="http://jg.tjoeunit.co.kr/"><img id="advertisement-jouen"src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSr3FexjnmdRQNt-FcxByAuOozrhtelzrwG5eysWz1Ue5-3shjJ9-ok5Cvor9IryklNA&usqp=CAU"></a>
			</div>
			<!-- 첨부파일 -->
			<div class="file" >
				<p style="margin:0 0 10px 0"><b>원본 첨부파일</b></p>
				<c:forEach var="img" items="${boardImg }" >
					<a href="${img.boardImg}">${fn:substringAfter(img.boardImg,'/resources/upload/')}</a><br>
				</c:forEach>
			</div>
			<div class='view-btn'>
			<c:if test="${member_id eq boardView.member_id or m_admin eq 1  }">
				<input type="button" class='btn btn-blue' id="board_delete"  value='삭제' onclick="delteBoard()">
				<input type="button" class='btn btn-blue' id="board_update"  value='수정'>
			
			</c:if>
			</div>
		</div>
		
		<hr style="border:0; ">
		<!-- comment List -->
	
		<div id="commentList">
			<div style="font:bold;font-size:12px">
				<b>전체 댓글 </b> <span style="color:red;">${boardReplyCnt}</span><b>개</b>
				<span id="commentHide" class="comment-show" style="float:right">댓글 접기</span>
			</div>
			<div id="commentform">
				<table id='table'>
					<!-- 댓글 출력 -->
				</table>
				<div class="paging">
				<span class="commentPaging"></span>
					<!-- 페이징 출력 -->
				</div>
			</div>
		</div>
	</div>	
	
	<div class="bottom">
			<!-- comment insert -->
		<div class="comment-wrap">
			
			<form action="" id="#form">
				<textarea name="comment" id="comment" cols="" rows="4"
					placeholder="여러분의 소중한 댓글을 입력해주세요."></textarea>
				<div id="emoticons">
					이모티콘: <img onclick="emo(this);"
						src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgDOF9QXLzGS_NHTBGgV9RPiBY8_QwVmHlN6vvCdrIvQ&s"
						alt="&#128512" title=":)">
						 <img onclick="emo(this);"
						 src="https://em-content.zobj.net/thumbs/120/google/350/grinning-face-with-smiling-eyes_1f604.png"
						alt="&#128513" title=";)"> <img onclick="emo(this);"
						src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/Emojione_1F602.svg/167px-Emojione_1F602.svg.png"
						alt="&#128514" title=":P"> <img onclick="emo(this);"
						src="https://as2.ftcdn.net/v2/jpg/01/95/45/25/1000_F_195452530_bYg7cpLNVOSO9tHDv6gD5ixlAuYC6kTY.jpg"
						alt="&#128517" title="8D"> <img onclick="emo(this);"
						src="https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRYKBGcZI4LwEUKj7hG7IUIOS1LjdNtQnx-kt8gLXZPKR4TJ3aU"
						alt="&#128561" title=":("> <img onclick="emo(this);"
						src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSjhc7gkbcSy9YJX7ZP5QiP1WBMTkc3iJF0Bu_7vNof9WauDkQo"
						alt="&#128565" title="--;">
					<div id="comment_cnt">(0 / 200)
						<input type="button" class='btn btn-blue' name="comment-insert" id="comment-insert" onclick="commentInsert()" value="작성">
					</div>
				</div>
				
			</form>
				
			<section class="notice">
			<div class="page-title" style="text-align:center">
			</div>
				
		
			<!-- board list area -->
			<div id="board-list" style="clear:both">
				
				
				<div class="container">
				<div class="write_form_wrap">
					<div>
						<ul class="tabs">
							<li class="tab-link current" id="li-0"><a href="/board/board?b_type=0">전체</a></li>
							<li class="tab-link" id="li-1"><a href="/board/board?b_type=1">국내</a></li>
							<li class="tab-link" id="li-2"><a href="/board/board?b_type=2">해외</a></li>
							<li class="tab-link" id="li-3"><a href="/board/board?b_type=3">질문</a></li>
							<li class="tab-link" id="li-4"><a href="/board/board?b_type=4">잡담</a></li>
						</ul>
					</div>
					<div class="write_form">
						<button type="submit" id="write-top" class="btn-board-top" ><img src="https://cdn-icons-png.flaticon.com/512/5218/5218705.png" style="width:15px; top:5px">글쓰기</button>
					</div>
				</div>
					<table class="board-table">
						<thead>
							<tr>
								<th scope="col" class="th-num" style="width:5%">번호</th>
								<th scope="col" class="th-num" style="width:10%">분류</th>
								<th scope="col" class="th-title"style="width:35%">제목</th>
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
						<span class="write-bottom-wrap" style="float:right">
							<button type="submit" id="write-bottom" class="btn btn-blue top" style="height: 40px;">글쓰기</button>
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
							<button type="button"  class="btn btn-search" onclick="boardList()">검색</button>
						</div>
					</div>
				</div>
			</div>
		</section>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"/>

</body>
</html>