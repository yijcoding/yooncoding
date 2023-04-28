<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<style type="text/css">
	
	.E-Solution a,.E-Solution a:hover{
		text-decoration: none;
		color:#fff;
	}
	
	.btn_open{
		font-weight:bold; margin:5px; padding:4px 6px; padding:10px
	}
	
	.pop_wrap{
		position:fixed; top:0; left:0; right:0; bottom:0; background:rgba(0,0,0,.5); font-size:0; text-align:center;
		
	}
	
	.pop_wrap:after{
		display:inline-block; height:100%; vertical-align:middle; content:'';
	}
	
	.pop_wrap .pop_inner{
		display:inline-block; padding:20px 30px; background:#fff; width:200px; vertical-align:middle; font-size:15px;
	}
	
	.btn_close{
		margin:20px 0 10px 0;
		background-color:#0a0a23;
	    color: #fff;
	    border:none; 
	    border-radius:10px; 
	    padding: 5px 0;
	    min-height:20px; 
	    min-width: 100px;
	    font-size:13px;
		
	}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('.btn_open').on('click',()=>{
		let open=document.querySelector('.pop_wrap');
		open.style.display='block';
	})
	
	$('.btn_close').on('click',()=>{
		let open=document.querySelector('.pop_wrap');
		open.style.display='none';
	})
})

Kakao.init("6b91f3bcf4e7a2744f0dd70c0c85c6ab");   //어플의 Javascript Key 값

function sendLinkCustom() {
	debugger;
	Kakao.Link.sendCustom({
		templateId : 53911  //숫자값
	});
}

try {
	function sendLinkDefault() {
		
	Kakao.Link.sendDefault({
	  objectType : 'feed', //임의로 정한 타입
	  content : {
		title : 'amusement',
		description : '#놀이공원 #할인 #Link Description',
		imageUrl : 'https://cdn.aitimes.com/news/photo/202205/144590_150861_334.jpg',
		link : {
			mobileWebUrl : 'https://developers.kakao.com',
			webUrl : document.location.href,
		},
	  },
	  social : {
		likeCount : 100,
		commentCount : 200,
		sharedCount : 300,
	  },
	  buttons : [
		{
			title : '보러가기',
			link : {
				mobileWebUrl : 'https://developers.kakao.com',
				webUrl : document.location.href,
			},
		}, ],
	})
	}
	;
	window.kakaoDemoCallback && window.kakaoDemoCallback()
} catch (e) {
	window.kakaoDemoException && window.kakaoDemoException(e)
}

</script>
</head>
<body>
	<div class="content E-Solution" id="content">
		<a href="#pop_info_1" class="btn_open" id="btn_open"><img src="https://cdn-icons-png.flaticon.com/512/1244/1244758.png?w=360" width="50" height="50"></a>
		<div id="pop_info_1" class="pop_wrap" id="pop_wrap" style="display: none;">
			<div class="pop_inner">
				<img src="https://play-lh.googleusercontent.com/Ob9Ys8yKMeyKzZvl3cB9JNSTui1lJwjSKD60IVYnlvU2DsahysGENJE-txiRIW9_72Vd" style="width:70px; cursor:pointer;" onclick="sendLinkDefault()">
				<br><button type="button" class="btn_close" >닫기</button>
			</div>
		</div>
	</div>	
</body>
</html>