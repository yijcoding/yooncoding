<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>놀이공원 추가하기</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
<style type="text/css">
  img{
    width:200px;
  }
  .image-wrap{
  	display:inline-block;
  }
</style>

<script type="text/javascript">
  var imgsInput=[];
  
  $(document).ready(()=>{
	  
  })
  
  function imageInput(){
    var imgs = document.getElementById('imgs');
  	var img =imgs.value;
	imgsInput.push(img);
  	const imgpreview = document.createElement('img');
  	imgpreview.setAttribute('src','');
  	$('.image-wrap').append(imgpreview);
  	for(row of imgsInput){
	  imgpreview.setAttribute('src',row);
	  $('.image-wrap').append(imgpreview);
  	}
  	
  	imgpreview.addEventListener('click', function() {
  	    var index = imgsInput.indexOf(imgpreview.getAttribute('src'));
  	    if (index > -1) {
  	      imgsInput.splice(index, 1);
  	    }
  	    imgpreview.remove();
  	  });
  }
	console.log(imgsInput);
  	
  	
  function insertAmusementCheck(){
	  const form = document.getElementById('form');
	  const imgforminput = document.createElement('input');
	  imgforminput.type = "hidden";
	  imgforminput.setAttribute('name', 'imgs');
	  
	  if($('.a_name').val()=='') {
		  alert('놀이공원 제목을 입력해주세요');
		  return false;
	  }else if($('select[name=a_country]').val()==''){
		  alert('나라를 입력해주세요');
		  return false;
	  }else if($('.a_time').val().length!=11){
		  alert("00:00~00:00 형식으로 입력해주세요!!!!!!");
		  return false;
	  }else if($('.a_lat').val()=="" && $('.a_lng').val()==""){
		  alert("좌표를 입력해주세요!!!!!!");
		  return false;
	  }else if($('.a_opening').val().length!=10){
		  alert("0000-00-00 형식으로 입력해주세요!!!!!!");
		  return false;
	  }else if($('.a_info').val().length==0){
		  alert("놀이공원 설명을 입력해주세요!");
		  return false;
	  }else{
		for(row of imgsInput){
			imgforminput.value += row + ",";
			alert(imgforminput.value);
		}
		imgforminput.value = imgforminput.value.slice(0, -1);
		form.appendChild(imgforminput);
	  }
  }
</script>
</head>
<body>
<div>
  <div>
    <form action="/insertAdminAmusement" method="post" id="form"onsubmit="return insertAmusementCheck()">
       <p>놀이공원이름</p>
       <p><input type="text" name="a_name" class="a_name"><br></p>
       <p> 나라</p>
       <p><select name="a_country"class="a_country">
         <option value=''>선택</option>
         <option value="대한민국">대한민국</option>
         <option value="일본">일본</option>
         <option value="미국">미국</option>
         <option value="독일">독일</option>
         <option value="영국">영국</option>
         <option value="아랍에미리트">아랍에미리트</option>
         <option value="호주">호주</option>
         <option value="루마니아">루마니아</option>
         <option value="프랑스">프랑스</option>
       </select></p>
       <p>운영시간</p>
       <p><input type="text" name="a_time" class="a_time" placeholder="00:00~00:00"></p>
       <p>놀이공원 좌표</p>
       <input type="text" name="a_lat" class="a_lat"placeholder="위도">
       <input type="text" name="a_lng" class="a_lng"placeholder="경도">
       <p>놀이공원 개장일</p>
       <input type="text" name="a_opening" class="a_opening" placeholder="0000-00-00">
       <p>놀이공원 설명</p>
       <p><textarea rows="10" cols="80"name="a_info" class="a_info"></textarea></p>
       
       
       <p>이미지</p>
       <p><input type="text" id="imgs"placeholder="이미지경로">
       <button type="button" onclick="imageInput()">입력</button></p>
       <div class="image-wrap">
       
       </div>
       
       <input type="submit" value="등록">
       
    </form>
   
  </div>		
</div>
</body>
</html>