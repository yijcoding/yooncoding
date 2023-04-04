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

img{
	width:200px;
}

.btn-dark {
  background: #555;
  color: #fff;
}

.btn-dark:hover, .btn-dark:focus {
  background: #373737;
  border-color: #373737;
  color: #fff;
}

.btn-dark {
  background: #555;
  color: #fff;
}

.btn-dark:hover, .btn-dark:focus {
  background: #373737;
  border-color: #373737;
  color: #fff;
}

	.btn {
  display: inline-block;
  padding: 0 30px;
  font-size: 15px;
  font-weight: 400;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
  touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  border: 1px solid transparent;
  text-transform: uppercase;
  -webkit-border-radius: 0;
  -moz-border-radius: 0;
  border-radius: 0;
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  -ms-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
}

</style>
<script type="text/javascript">
	function cancle(){
		if(confirm("글 작성을 취소하시겠습니까?")){
			location.href="/board/view?board_id=${boardView.board_id}";
		}
	}
	
	function update(){
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
		}else{
			form.action('/board/updateBoard');
			form.method = 'post';
			form.submit();
		}
	}
	
	function PreviewImage() {
        var preview = new FileReader();
        preview.onload = function (e) {
        document.getElementById("user_image").src = e.target.result;
    };
    preview.readAsDataURL(document.getElementById("user_profile_img").files[0]);
 };
</script>
</head>
<body>
	<!-- create view area -->
	<div id="create-board">
		<div class="container">
			<div class="create-window">
				<form id='form'method='post' onsubmit='return update()'>
					<div id='top-wrap' >
					<!-- 작성할 게시판 선택 -->
					<select name='b_type' id='b_type'>
						<option value='0' id='option'>선택</option>
						<option value='korea' id='option'>국내</option>
						<option value='global' id='option'>해외</option>
						<option value='free' id='option'>자유</option>
					</select><br>
					<input type="text" name="b_title" id='b_title' value="${boardView.b_title}"><br>
					<!-- 버튼 -->
					</div>
						<!-- top end -->
						<hr>
						<textarea name="b_content" id="b_content" >${boardView.b_content}</textarea>
						<div id="insert-btn-wrap">
							<input type="button" id="cancle-btn" class="btn btn-dark" onclick="cancle()"value="취소">
							<input type="submit" class="btn btn-dark" id="submit" value="확인">
						</div>
						<br>
						<div>
							<h2>본문 이미지</h2>
							<img alt="" src="${boardView.file }">
						</div>
						<h2>업로드할 이미지</h2>
						<img id="user_image" src="#" alt="" >
						<input accept=".jpg" onchange="PreviewImage();" type="file" id="user_profile_img" name='file' />
						<input type="hidden" name="board_id" value="${boardView.board_id }">
						
				</form>
			</div>
		</div>
	</div>
</body>
</html>