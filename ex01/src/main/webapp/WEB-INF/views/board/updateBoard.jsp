<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<style type="text/css">

#create-board .create-window,#form,.container {
	/*   padding-right: 124px; */
	
	width: 1100px;
	margin:0 200px 0 100px;
}

#exampleFormControlTextarea1 {
	width: 1100px;
	height: 300px;
	resize:none;
}
#select-wrap{
	width:40px;
}
#b_type,#option{
	width:200px;
	height:40px;
	margin-bottom:10px;
	
}
#insert-btn-wrap{
	float:right;
}

#user_profile_img{
	display:none;
}

img{
	width:200px;
}

.btn-blue {
	background: #4aa8d8;
	color: #fff;
	height:35px;

}
	
.btn-blue:hover, .btn-blue:focus {
	background: #298cbf;
	border-color: #298cbf;
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
  border: 2px solid black;
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
	<div class="wrap">
	<jsp:include page="/WEB-INF/views/board/advertisement_leftSide.jsp"/>
		<div id="create-board">
			<div class="container">
				<div class="create-window">
					<form id='form'method='post' onsubmit='return update()' enctype="multipart/form-data">
						<div id='top-wrap' >
						<!-- 작성할 게시판 선택 -->
						<select class="form-select" name='b_type' id='b_type' aria-label="Default select example">
							<option value='0' id='option'>선택</option>
							<option value='korea' id='option'>국내</option>
							<option value='global' id='option'>해외</option>
							<option value='free' id='option'>자유</option>
						</select><br>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" name="b_title" id="floatingInput" value="${boardView.b_title}">
							<label for="floatingInput">제목</label>
						</div>
						<!-- 버튼 -->
						</div>
							<!-- top end -->
							<hr>
							<textarea class="form-control" name="b_content" id="exampleFormControlTextarea1" rows="3" >${boardView.b_content}</textarea>
							<div id="insert-btn-wrap">
								<input type="button" id="cancle-btn" class="btn btn-blue" onclick="cancle()"value="취소">
								<input type="submit" class="btn btn-blue" id="submit" value="확인">
							</div>
							<br>
							<div>
								<h2>본문 이미지</h2>
								<c:forEach var="img" items="${boardImg }" >
									<div class="image-wrap">
										<img alt="" src="${img.boardImg }" style="margin:20px 0; max-width:600px">
									</div>
								</c:forEach>
								<img alt="" src="boardImg">
							</div>
							<h2>업로드할 이미지</h2>
							<img id="user_image" src="#" alt="" >
							<label for="user_profile_img">
	 							<div class="btn btn-blue" style="padding : 5px 0; width:100px">파일 업로드</div>
							</label>
							<input accept=".jpg,.png,.gif" onchange="PreviewImage();" type="file" id="user_profile_img" name='file' multiple="multiple"/>
							<input type="hidden" name="board_id" value="${boardView.board_id }">
							
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>