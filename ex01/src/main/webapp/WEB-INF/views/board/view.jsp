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
	#emoticons img{
		width:20px;
	}
	#comment{
		width:80%;
		height:150px;
		resize: none;
		font-size:15px;
	}
	#table{
		border:1px solid black;
		width:80%;
	}
	#emoticons{
		width:73%;
		float:left;
	}
	#comment_cnt{
		width:10%;
		float:left;
		
    });
    
   #comment-insert{
    	position: relative;
        top: 80px;
    }
    
    #clear{
    	clear:both;
    }
	#commentList{
		
		margin:50px 0 0 0 ;
	}
	
	
</style>
<script type="text/javascript">
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
			}
		}
	
	});
}

function commentList(){
	$.ajax({
		type:'get',
		url:'/board/replyList',
		data:{
			'board_id':"${boardView.board_id}", 
		},success : function(data){
			console.log(data);
			tr="<table id='table'>";
			tr+="<thead>";
			for(row of data){
				tr+="<tr>";
				tr+="<td style='width:20%;text-align:center'>"+row.member_id+"</td>";
				tr+="<td style='width:50%'>"+row.b_reply+"</td>";
				tr+="<td style='width:30%;text-align:center'>";
				tr+=row.postdate;
				tr+="</td>";
				tr+="</tr>";
				}
			
			tr+="</thead>";
			tr+="</table>";
		$('#table').html(tr);
		}
	
	});
}

$(document).ready(function() {
    commentList()
    $('#comment').on('keyup', function() {
        $('#comment_cnt').html("("+$('#comment').val().length+" / 200)<input type='button' name='comment-insert' id='comment-insert' onclick='commentInsert()' value='작성'>");
 
        if($('#comment').val().length > 200) {
            $('#comment').val($('#comment').val().substring(0, 200));
            $('#comment_cnt').html("(200 / 200)");
            alert('댓글 200자 제한');
        }
    });
    
});
</script>
</head>
<body>
	${boardView.b_title} ${boardView.member_id}
	${fn:replace(boardView.postdate, 'T',' ')}
	<img alt="" src="${boardView.file}">
	<br> ${boardView.b_content}
	<div>
		첨부파일 <a href="https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E">https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E</a>
	</div>
	
	<form action="" id="#form">
		<textarea name="comment" id="comment" cols="" rows="4"
			placeholder="여러분의 소중한 댓글을 입력해주세요. 이미지나 링크는 주소만 적으시면 됩니다."></textarea>
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
		</div>
		<div id="comment_cnt">(0 / 200)
		
		<input type="button" name="comment-insert" id="comment-insert" onclick="commentInsert()" value="작성">
	</div>
	</form>
	<div id="clear"></div>
	<div id="commentList">
		<table id='table'>
		</table>
	</div>
</body>
</html>