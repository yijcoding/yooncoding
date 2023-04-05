<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"
	integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
	crossorigin="anonymous"></script>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<style type="text/css">
<
style
 
type
="
text
/
css
"
>
@media print {
	.form-section {
		display: inline !important
	}
	.form-pagebreak {
		display: none !important
	}
	.form-section-closed {
		height: auto !important
	}
	.page-section {
		position: initial !important
	}
}
</style>
<link type="text/css" rel="stylesheet"
	href="https://cdn02.jotfor.ms/themes/CSS/5e6b428acc8c4e222d1beb91.css?v=3.3.40728" />
<link type="text/css" rel="stylesheet"
	href="https://cdn03.jotfor.ms/css/styles/payment/payment_styles.css?3.3.40728" />
<link type="text/css" rel="stylesheet"
	href="https://cdn01.jotfor.ms/css/styles/payment/payment_feature.css?3.3.40728" />
<style>
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

#exampleFormControlTextarea1 {
	width: 100%;
	height: 300px;
	resize: none;
}

#select-wrap {
	width: 40px;
}

#b_type, #option {
	width: 20%;
	height: 20px;
	margin-bottom: 10px;
}

#b_title {
	width: 70%;
	height: 20px;
}

#insert-btn-wrap {
	float: right;
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
	background: transparent;
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
			location.href="/board/board";
		}
	}
	
	function insert(){
		var form = document.getElementById('form');
		
		if($('input[name="b_title"]').val().length === 0){
			alert("제목을 입력해주세요");
			return false;
		}else if($('textarea[name="b_content"]').val().length === 0){
			alert("내용을 입력해주세요");
			return false;
		}if($('select[name="b_type"]').val()=='0'){
			alert("말머리를 선택해주세요");
			return false;
		}else{
			form.submit();
		}
		
	}
	
	function PreviewImage() {
		var cnt=0;
        var preview = new FileReader();
        preview.onload = function (e) {
        document.getElementById("user_image").src = e.target.result;
    };
    preview.readAsDataURL(document.getElementById("user_profile_img").files[cnt]);
    cnt++;
 };
</script>
</head>
<body>
	<!-- create view area -->
	<div id="create-board">
		<div class="container">
			<div class="create-window">
				<form action="/board/createBoard" id="form" method="post" onsubmit="return insert()">
					<div id='top-wrap'>
						<!-- 작성할 게시판 선택 -->
						<select class="form-select" name="b_type"
							aria-label="Default select example" style="width: 100px">
							<option value='0' id='option'>선택</option>
							<option value='korea' id='option'>국내</option>
							<option value='global' id='option'>해외</option>
							<option value='free' id='option'>자유</option>
						</select><br>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="floatingInput" name="b_title">
							 <label for="floatingInput">제목</label>
						</div>
					</div>
					<!-- top end -->
					<hr>
					<div class="mb-3">
						<textarea class="form-control" name="b_content"
							id="exampleFormControlTextarea1" rows="3"></textarea>
					</div>
					<img id="user_image" src="#" alt="" >
						<input accept=".jpg,.png,.gif" onchange="PreviewImage();" type="file" id="user_profile_img" name='file' />
					
					
					<input type="hidden" name="member_id" value="admin">
					<div id="insert-btn-wrap">
						<input type="button" id="cancle-btn" class="btn-dark"
							onclick="cancle()" value="취소"> 
							<input type="submit" class="btn-dark" id="submit"  value="확인">
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>