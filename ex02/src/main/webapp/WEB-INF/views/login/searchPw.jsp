<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/menu.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous" />

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<style>
input {
	height: 27px;
	font-size: 13px;
}

button {
	font-size: 11px;
	padding: 0;
}
</style>
</head>
<body>
	<div class="container-md mx-auto"
		style="width: 590px; margin-top: 100px">
		<form id="todo-form" action="/newPw" method="get">
			<div class="row border-bottom border-1" style="height: 35px">
				<div class="text-center border-bottom border-2 border-info input"
					style="width: 130px; font-weight: 600;">비밀번호 찾기</div>
				<div class="col-2"></div>
				<div class="col-7 text-right text-muted" style="font-size: 15px;">
					<span style="color: rgb(125, 182, 40)">01. 본인 확인</span> > 02. 비밀번호
					재설정
				</div>
			</div>
			<div class="row mt-5">
				<input class="col-1" type="radio" checked />
				<div class="col-7" style="font-size: 17px; font-weight: 600">
					회원정보에 등록한 휴대전화로 인증</div>
			</div>
			<div class="row">
				<div class="col-1"></div>
				<div class="col-11 text-muted" style="font-size: 13px">회원정보에
					등록한 휴대전화 번호와 입력한 휴대전화 번호가 같아야, 인증번호를 받을 수 있습니다.</div>
			</div>
			<div class="row mt-5">
				<div class="col-1"></div>
				<div class="col-3" style="font-size: 15; font-weight: 600">아이디</div>
				<input type="text" class="col-3" name="member_id"
					style="height: 27px;"></input>
			</div>
			<div class="row">
				<div class="col-1"></div>
				<div class="col-7" id="alert_name"></div>
			</div>
			<div class="row mt-3">
				<div class="col-1"></div>
				<div class="col-3" style="font-size: 15; font-weight: 600">이메일주소</div>
				<input type="text" class="col-5" name="m_email"></input> <input
					type="button" class="col-2 mx-3 border-info" id="confirmBtn"
					value="인증번호">
			</div>
			<div class="row">
				<div class="col-1"></div>
				<div class="col-9" id="alert_send"></div>
			</div>
			<div class="row mt-3">
				<div class="col-4"></div>
				<input type="text" name="comfirmNumber" class="col-5"></input> <input
					type="button" class="col-2 mx-3 border-info" id="confirmNumberBtn"
					value="확인">
				<div class="row mt-2">
					<div class="col-4"></div>
					<div class="col-5 text-muted px-0" style="font-size: 13px">
						인증번호가 오지 않나요 <i class="bi bi-question-square"
							data-bs-toggle="tooltip" data-bs-placement="bottom"
							title="EXCITING AMUSEMENT가 발송한 메일이 스팸 메일로 분류된 것은 아닌지 확인해 주세요. 스팸 메일함에도 메일이 없다면,다시 한 번 '인증번호 받기'를 눌러주세요."></i>
					</div>
				</div>
				<div class="row">
					<div class="col-1"></div>
					<div class="col-7" id="alert_number"></div>
				</div>
				<div class="row mt-5" style="margin-bottom:120px;">
					<button type="submit" class="col-2 m-auto border-info"
						id="submitBtn" style="font-size: 17px;">다음</button>
				</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<script>
		$(function() {
			$('input[name=comfirmNumber]').attr("disabled", true);
			$('#confirmNumberBtn').attr("disabled", true);
			$('#submitBtn').attr("disabled", true);
		});

		$('#confirmBtn')
				.on(
						'click',
						function() {
							const m_email = $('input[name=m_email]').val();
							const member_id = $('input[name=member_id]').val();
							if (m_email == "" || m_email == null) {
								$("#alert_name")
										.html(
												"<font size='2' color='red'>회원가입시에 등록한 아이디을 입력해주세요.</font>");
								return false;
							}
							$
									.post(
											"/sendEmail",
											{
												m_email : m_email,
												member_id : member_id
											},
											function(response) {
												if (response == -1) {
													console
															.log("confirmBtn Ajax -1 응답");
													$("#alert_send")
															.html(
																	"<font size='2' color='red'>회원가입시에 등록한 이메일을 입력해주세요.</font>");
												} else if (response == 0) {
													console
															.log("confirmBtn Ajax 0 응답");
													$("#alert_name")
															.html(
																	"<font size='2' color='red'>회원가입시에 등록한 아이디을 입력해주세요.</font>");
												} else if (response != -1) {
													console
															.log("confirmBtn Ajax 응답 성공");
													console.log("response : "
															+ response);
													$("#alert_send")
															.html(
																	"<font size='2' color='red'>인증 이메일이 전송되었습니다. 이메일을 확인해주세요.</font>");
													$("#todo-form")
															.append(
																	"<input type='hidden' name='confirmationNumber' value='"+response+"'/>");
													$(
															'input[name=comfirmNumber]')
															.attr("disabled",
																	false);
													$('#confirmNumberBtn')
															.attr("disabled",
																	false);
													$("#alert_name").html("");
												}
											});

						});

		$('#confirmNumberBtn')
				.on(
						'click',
						function() {
							const confirmationNumber = $(
									'input[name=confirmationNumber]').val();
							console.log("confirmationNumber : "
									+ confirmationNumber);

							const confirmNumber = $('input[name=comfirmNumber]')
									.val();
							if (confirmNumber == confirmationNumber) {
								$("#alert_number")
										.html(
												"<font size='2' color='blue'>인증번호가 일치 합니다.</font>");
								$('#submitBtn').attr("disabled", false);
							} else {
								$("#alert_number")
										.html(
												"<font size='2' color='red'>인증번호가 일치하지 않습니다.</font>");
							}
						});
	</script>
</body>
</html>
