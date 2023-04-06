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
<style type="text/css">
	
	.wrap{
		position: sticky;
		/*   padding-right: 124px; */
		margin: 0 auto;
		width:100%;
	}
	.aside-wrap{
		position:sticky;
		width:120px;
		height:100vh;
		float:left;
		margin:0 50px;
		
	}
	.aside-inner{
		position:fixed;
		width:120px;
		height:500px;
		margin-top:150px;
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
		margin: 0 250px;
		
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
    });
    
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
		margin:50px 0; border:1px solid black;
		padding:20px 0;
		width:1100px;
	}
	

	}
	
	.btn-blue {
	  background: #4aa8d8;
	  color: #fff;
	}
	
	.btn-blue:hover, .btn-blue:focus {
	  background: #298cbf;
	  border-color: #298cbf;
	  color: #fff;
	}
	
	.btn-white {
	  background: #ffffff;
	  color: #555;
	}
	
	.btn-white:hover, .btn-dark:focus {
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
		width:100%;
		max-width:1100px;
		border:1px solid Gainsboro;
		margin:20px 0;
		padding: 10px 5px;
	}
	
	.image-wrap,img{
		width:700px;
	}
	
	td{
		border-bottom:1px solid Gainsboro;
		padding: 10px 0;
	}
	
	
	.advertisement1{
		width:680px;
		margin:0 auto;
	}
	#advertisement1{
		width:150px;
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
</style>
<script type="text/javascript">

$(document).ready(function() {
    $('#comment').on('keyup', function() {
        $('#comment_cnt').html("("+$('#comment').val().length+" / 200)<input type='button' class='btn btn-dark' name='comment-insert' id='comment-insert' onclick='commentInsert()' value='작성'>");
 
        if($('#comment').val().length > 200) {
            $('#comment').val($('#comment').val().substring(0, 200));
            $('#comment_cnt').html("(200 / 200)");
            alert('댓글 200자 제한');
        }
    });
    
    $('#comment-update').on('keyup', function() {
        $('#comment_cnt').html("("+$('#comment-update').val().length+" / 200)<input type='button' class='btn btn-dark' name='comment-insert' id='comment-insert' onclick='commentInsert()' value='작성'>");
 
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
    	
    	
    	location.href='/board/deleteBoard?board_id=${boardView.board_id}';
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
    
    
    
    commentList();
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
	$.ajax({
		type:'get',
		url:'/board/reply-insert',
		datatype:'json',
		data:{
			'b_reply':$('#comment').val(),
			'board_id':"${boardView.board_id}", 
			'member_id':'${boardView.member_id}' 
		},success : function(data){
			if(data == 1){
				alert('댓글 추가완료');
				document.getElementById("comment").value=''
			    commentList();
			}
		}
	
	});
}

function commentList(){
	$.ajax({
		type:'get',
		url:'/board/replyList',
		data:{
			'board_id':"${boardView.board_id}" 
		},success : function(data){
			console.log(data);
			tr='';
			tr+="<table id='table'>";
			for(row of data){
				tr+="<tr id='reply"+row.reply_num+"'>";
				tr+="<td style='width:20%;text-align:center'>"+row.member_id+"</td>";
				tr+="<td style='width:40%'>"+row.b_reply+"</td>";
				tr+="<td style='width:25%;text-align:center'>"+row.postdate+"</td>";
				tr+="<td style='width:5%;text-align:center'><button onclick='comment_update("+row.reply_num+","+'row.member_id'+")' class='btn btn-white' id='comment_update'>수정</button></td>";
				tr+="<td style='width:5%;text-align:center'><button onclick='comment_delete("+row.reply_num+")' class='btn btn-white' id='comment_delete'>삭제</button></td>";
				tr+="</tr>";
				}
			tr+="</table>";
		$('#table').html(tr);
		}
	
	});
}


function comment_update(num,id){
	var id1=id;
	if(confirm('작성하신 댓글을 수정 하시겠습니까?')){
		tr="<table id='table'> ";
		tr+="<thead>";
			tr+="<tr>";
			tr+="<td style='width:20%;text-align:center'>"+id1+"</td>";
			tr+="<td style='width:40%' colspan='4'>"
			+'<textarea name="comment-update" id="comment-update" cols="" rows="4" style="width:80%;height:100px;padding: 10px 0 0 10px"></textarea>'

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
			+"<button onclick='comment_update_commit("+num+")' class='btn btn-white' id='comment_update'>수정</button>"
			+"<button onclick='update_cancle()' class='btn btn-white' id='comment_update_cancle'>취소</button>"
			+'</div>'
			+'<div style="clear:both;margin:0 0 30px 0">'
			+'</div>'
			tr+="</tr>";
		tr+="</thead>";
		tr+="</table>";
		
	$('#reply'+num).html(tr);
		
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



function comment_update_commit(num){
	if(confirm('정말 댓글을 수정 하시겠습니까?')){
		$.ajax({
			type:'get',
			url:'/board/replyUpdate',
			datatype:'json',
			data:{
				'reply_num':num,
				'b_reply':$('#comment-update').val()
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





</script>
</head>
<body>
<div class='wrap'>
	<div class="aside-wrap" >
		<div class="aside-inner" >
			<a href="http://jg.tjoeunit.co.kr/"><img id="advertisement1"alt="" src="https://tpc.googlesyndication.com/simgad/2289797006222971694?sqp=4sqPyQQ7QjkqNxABHQAAtEIgASgBMAk4A0DwkwlYAWBfcAKAAQGIAQGdAQAAgD-oAQGwAYCt4gS4AV_FAS2ynT4&rs=AOga4qnEJ0oNxtfLOFYaUuhbbHSr4SxPpw"></a>
		</div>
	</div>
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
			<div class="image-wrap">
				<img alt="" src="${boardView.file}" style="margin:20px 0; width:600px">
			</div>
			<div class="aside-right-wrap" >
				<div class="aside-right-inner">
				
				</div>
			</div>
			<br> ${boardView.b_content}
			<!-- 가로 광고 -->
			<div class="advertisement1">
				<a href="http://jg.tjoeunit.co.kr/"><img id="advertisement-jouen"src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSr3FexjnmdRQNt-FcxByAuOozrhtelzrwG5eysWz1Ue5-3shjJ9-ok5Cvor9IryklNA&usqp=CAU"></a>
			</div>
			<!-- 첨부파일 -->
			<div class="file" >
				<p style="margin:0 0 10px 0"><b>원본 첨부파일</b></p>
				<a href="${boardView.file}">${fn:substringAfter(boardView.file,'/resources/upload/')}</a>
			</div>
			<div class='view-btn'>
				<input type="button" class='btn btn-dark' id="board_delete"  value='삭제'>
				<input type="button" class='btn btn-dark' id="board_update"  value='수정'>
			</div>
		</div>
		
		<hr style="border:0; ">
		<!-- comment List -->
		<div id="clear"></div>
	
		<div id="commentList">
			<div style="font:bold;font-size:12px">
				<b>전체 댓글 </b> <span style="color:red;">${boardReplyCnt}</span><b>개</b>
				<span id="commentHide" class="show" style="float:right">댓글 접기</span>
			</div>
			<div id="commentform">
				<table id='table'>
				
				</table>
			</div>
		</div>
	</div>	
	

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
					<input type="button" class='btn btn-dark' name="comment-insert" id="comment-insert" onclick="commentInsert()" value="작성">
				</div>
			</div>
			
		</form>
			
		<jsp:include page="/WEB-INF/views/board/Board.jsp"/>
	</div>
</div>
</body>
</html>