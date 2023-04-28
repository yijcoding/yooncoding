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
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style type="text/css">



.select_wrap{
	display:inline-block;
}
#create-board{
	margin: 100px 0 ;
}
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


#insert-btn-wrap {
	float: right;
	
}

.imgs_file{
	margin:100px 0 0 0;
}


#insert-btn-wrap .btn-blue {
	background: #4aa8d8;
	color: #fff;
	height:35px;
	b
}
	
#insert-btn-wrap .btn-blue:hover, .btn-blue:focus {
	background: #298cbf;
	border-color: #298cbf;
	color: #fff;
	border: 2px solid black;
}

#insert-btn-wrap .btn {
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
	border-radius: 5px;
}

input[type=file] {
	display: none;
}

.my_button {
	display: inline-block;
	width: 200px;
	text-align: center;
	padding: 10px;
	background-color: #4aa8d8;
	color: #fff;
	text-decoration: none;
	border-radius: 5px;
	border: 2px solid black;
}

.my_button:hover, .my_button:focus, .my_button a {
	background-color:#298cbf;
	color: #fff;
}

.imgs_wrap {
	border: 2px solid #d6d6d6;
	margin-top: 30px;
	margin-bottom: 30px;
	padding-top: 10px;
	padding-bottom: 10px;
	border-radius: 5px;
}

.imgs_wrap img {
	max-width: 150px;
	margin-left: 10px;
	margin-right: 10px;
}


table, td, th {
  border-top : 1px solid #666666;
  border-bottom: 1px solid #666666;
  border-collapse : collapse;
  width:100%;
  margin: 15px 0;
}

td, th {
  text-align : center;
  vertical-align : middle;
}
</style>
<script type="text/javascript">

$(document).ready(function(){
	
	 $("#input_imgs").on("change", handleImgFileSelect);
})
	function cancle(){
		if(confirm("글 작성을 취소하시겠습니까?")){
			location.href="/board/board";
		}
	}
	
	function insert(){
	
		var form = document.getElementById('form');
		console.log($('input:checkbox[name="check1"]').prop('checked')==false)
		if(${member_id eq null}){
			swal('세션이 만료되었습니다.','다시 로그인 해주세요','error');
			return false;
		}else if($('input[name="b_title"]').val().length === 0){
			swal("제목을 입력해주세요",'','warning');
			return false;
		}else if($('textarea[name="b_content"]').val().length === 0){
			swal("내용을 입력해주세요",'','warning');
			return false;
		}if($('select[name="b_type"]').val()=='0'){
			swal("말머리를 선택해주세요",'','warning');
			return false;
		}else if($('input:checkbox[name="check1"]').prop('checked')==false){
			swal("개인정보 동의에 체크해주세요",'','warning');
			return false;
		}else if(confirm('작성하신 문의는 수정 및 삭제가 불가능합니다')){
			form.submit();	
		}else{
        	return false;
        }   
		
	}
	
	function fileUploadAction() {
        console.log("fileUploadAction");
        $("#input_imgs").trigger('click');
    }

    function handleImgFileSelect(e) {

        // 이미지 정보들을 초기화
        sel_files = [];
        $(".imgs_wrap").empty();

        var files = e.target.files;
        var filesArr = Array.prototype.slice.call(files);

        var index = 0;
        filesArr.forEach(function(f) {
            if(!f.type.match("image.*")) {
                alert("확장자는 이미지 확장자만 가능합니다.");
                return;
            }

            sel_files.push(f);

            var reader = new FileReader();
            reader.onload = function(e) {
                var html = "<a href=\"#\" onclick=\"deleteImageAction("+index+")\" id=\"img_id_"+index+"\"><img src=\"" + e.target.result + "\" data-file='"+f.name+"' class='selProductFile' title='Click to remove'></a>";
                $(".imgs_wrap").append(html);
                index++;

            }
            reader.readAsDataURL(f);
            
        });
    }
    
    function deleteImageAction(index) {
        console.log("index : "+index);
        console.log("sel length : "+sel_files.length);

        sel_files.splice(index, 1);

        var img_id = "#img_id_"+index;
        $(img_id).remove(); 
    }

    function fileUploadAction() {
        console.log("fileUploadAction");
        $("#input_imgs").trigger('click');
    }
	
	
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/menu.jsp"/>
	<div style="margin:0 150px">
		<jsp:include page="/WEB-INF/views/customerService/customerMove.jsp"/>
	</div>
	<!-- create view area -->
	<div id="create-board">
		<div class="container">
			<div class="create-window">
			<div style="text-align:center;margin:0 0 50px 0">
				<h1>이용문의</h1>
			</div>
				<form action="/customer/announcementInquiry" id="form" method="post" onsubmit="return insert()" enctype="multipart/form-data">
					<div class='top-wrap'>
					
						<!-- 작성할 게시판 선택 -->
						<div class="select_wrap" style="width:100%">
							<select class="form-select" name="b_type"
								aria-label="Default select example" style="width: 100%">
								<option value='0' id='option'>전체</option>
								<option value='예매문의' id='option'>예매문의</option>
								<option value='요금문의' id='option'>요금문의</option>
								<option value='제휴할인' id='qna'>제휴할인</option>
								<option value='기타문의' id='option'>기타문의</option>
							</select><br>
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="floatingInput" name="b_title">
							 <label for="floatingInput">제목</label>
						</div>
					</div>
					<!-- top end -->
					<hr>
					<div class="mb-3">
						<textarea class="form-control" name="b_content"
							id="exampleFormControlTextarea1" rows="3"  placeholder=''></textarea>
					</div>
					
					<div class="imgs_file">
						<h2><b>이미지 미리보기</b></h2>
						<div class="input_wrap">
						<a href="#" onclick="fileUploadAction();" class="my_button">파일 업로드</a>
						<input type="file" name="file" id="input_imgs" multiple/>
						</div>
					</div>
						
					<div>
						<div class="imgs_wrap">
						    <img id="img" />
						</div>
					</div>
					
					<input type="hidden" name="member_id" value="${member_id}">
					<table>
						<tr style="height:100px;">
							<td style="width:20%">개인정보 수집,<br>이용동의서</td>
							<td><textarea rows="10"name="b_content" readonly style="width:100%; resize: none;">개인 정보 수집, 이용 동의서

본인은 방문 전 이용문의 작성과 관련하여 귀사가 아래와 같이 본인의 개인정보를 수집, 이용하는데 동의합니다.

개인정보 수집, 이용에 관한 사항


1.개인정보의 수집, 이용 목적
   고객의 요청ㆍ문의사항 확인, 사실조사를 위한 연락ㆍ통지, 처리결과 통보 등의 목적


2.수집하는 개인정보의 항목
   ㆍ필수입력사항

   이름, E-mail
   ㆍ서비스 이용과정이나 사업처리 과정에서 아래와 같은 정보들이 생성되어 수집될 수 있습니다.

   접속로그, 쿠키, 접속IP정보

3.개인정보의 보유, 이용기간
   수집, 이용에 관한 동의일로부터 1년(이후에는 작성내용만 보관됩니다.)
※ 귀하는 개인정보 수집, 이용에 대한 동의를 거부하실 권리가 있으며, 동의를 거부하실 경우 서비스 이용이 제한됩니다.</textarea></td>
						</tr>
					</table>
					<div style="float:right; margin:0 0 15px 0">
					<input type="checkbox" name="check1">&nbsp;개인정보 수집 및 이용에 동의합니다.
					</div>
					
					<div style="clear:both"></div>
					
					<div id="insert-btn-wrap">
						<input type="button" id="cancle-btn" class="btn btn-blue" onclick="cancle()" value="취소"> 
							<input type="submit" class="btn btn-blue" id="submit"  value="확인">
					</div>
				</form>
			</div>
		</div>
	</div>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body>
</html>