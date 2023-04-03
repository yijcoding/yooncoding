<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
<style type="text/css">
#create-board .create-window {
	position: relative;
	/*   padding-right: 124px; */
	margin: 0 auto;
	width: 80%;
	max-width: 800px;
}

.container {
	margin: 0 auto;
}

#b_content {
	width: 100%;
	height: 300px;
	resize:none;
}
#select-wrap{
	width:40px;
}
#b_type,#option{
	width:20%;
	height:20px;
	margin-bottom:10px;
	
}
#b_title{
	width:70%;
	height:20px;
}
#insert-btn-wrap{
	float:right;
}

</style>
<script type="text/javascript">
	function cancle(){
		if(confirm("글 작성을 취소하시겠습니까?")){
			location.href="koreaBoard";
		}
	}
	
	function insert(){
		var form = document.getElementById('#form');
		
		if($('#b_title').val().length === 0){
			alert("제목을 입력해주세요");
			return false;
		}else if($('#b_content').val().length === 0){
			alert("내용을 입력해주세요");
			return false;
		}if($('select[name="b_type"]').val()=='0'){
			alert("말머리를 입력해주세요");
			return false;
		}
		
		form.submit();
	}
</script>
</head>
<body>
	<!-- create view area -->
	<div id="create-board">
		<div class="container">
			<div class="create-window">
				<form action="board/createBoard" id='form'method='post' onsubmit='insert()'>
					<div id='top-wrap' >
					<!-- 작성할 게시판 선택 -->
					<select name='b_type' id='b_type'>
						<option value='0' id='option'>선택</option>
						<option value='korea' id='option'>국내</option>
						<option value='global' id='option'>해외</option>
						<option value='free' id='option'>자유</option>
					</select><br>
					<input type="text" name="b_title" id='b_title' ><br>
					</div>
					<!-- top end -->
					<hr>
					<textarea name="b_content" id="b_content"></textarea>
					<br>
					<input type="file" name="file"> <input type="hidden"
						name="member_id" value="admin">
					<div id="insert-btn-wrap">
						<input type="button" id="cancle-btn" onclick="cancle()"value="취소">
						<input type="submit" id="submit" value="확인">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>