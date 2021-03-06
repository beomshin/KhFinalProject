<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>
	    /*중복아이디체크관련*/
   td.idMsg-container{position:relative; padding:0px;}
   td.pwMsg-container{position:relative; padding:0px;}
   td.pwckMsg-container{position:relative; padding:0px;}
   td.nameMsg-container{position:relative; padding:0px;}
   td.emailMsg-container{position:relative; padding:0px;}
   td.emailNoMsg-container{position:relative; padding:0px;}
   span.idMsg{display:none;font-size: 12px;position:absolute; top:12px; right:10px;}
   span.pwMsg{display:none;font-size: 12px;position:absolute; top:12px; right:10px;}
   span.pwckMsg{display:none;font-size: 12px;position:absolute; top:12px; right:10px;}
   span.nameMsg{display:none;font-size: 12px;position:absolute; top:12px; right:10px;}
   span.emailMsg{display:none;font-size: 12px;position:absolute; top:12px; right:10px;}
   span.emailNoMsg{display:none;font-size: 12px;position:absolute; top:12px; right:10px;}
   span.ok{color:green;}
   span.no{color:red;}
   span.ok1{color:green;}
   span.no1{color:red;}
   span.ok2{color:green;}
   span.no2{color:red;}
   span.ok3{color:green;}
   span.no3{color:red;}
   span.ok4{color:green;}
   span.no4{color:red;}
   span.error{color:red;}
</style>
<section>
	<div class="container d-flex justify-content-center">
		<div class="enroll-container" style="width:600px; border:1px solid black; margin-top:50px;">
			<div class="d-flex justify-content-center" style="margin:30px;">
   				<img class="rounded-circle" style="margin:5px;width:100px;height:100px;cursor: pointer;"
					alt="rogo" src="${pageContext.request.contextPath }/resources/img/king.png">
			</div>
		 	<div class="d-flex text-center" style="margin:30px;">
				<hr style="width:175px;">
				<h4 style="margin-left:10px; margin-right:10px;">정보 입력</h4>
				<hr style="width:175px;">
			</div>
			<div class="enroll-form d-flex justify-content-center">
				<form action="${path}/member/businessEnrollEnd.do" method="post" onsubmit="return fn_checkEndB();">
				<table style="width:500px;">
					<tr style="margin-bottom:30px;">
						<th>아이디</th>
						<td class="idMsg-container">
							<input type="text" id="userId" name="b_Id" class="form-control" placeholder="숫자와 영(소)문자 조합 5~10자리를 사용 " required>
							<span class="idMsg ok">사용가능한 아이디입니다.</span>
							<span class="idMsg no">이미 존재하는 아이디입니다.</span>
							<span class="idMsg error">올바른 아이디 형식이 아닙니다.</span>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td class="pwMsg-container">
							<input type="password" id="userPw" name="b_Pw" class="form-control" placeholder="숫자와 영(소)문자 조합 5~10자리를 사용" required>
							<span class="pwMsg ok3">사용가능한 비밀번호 입니다.</span>
							<span class="pwMsg no3">올바른 비밀번호 형식이 아닙니다.</span>
						</td>
					</tr>
					<tr>
						<th></th>
						<td class="pwckMsg-container">
							<input type="password" id="userPwck" name="b_Pwck" class="form-control" placeholder="비밀번호 확인" required>
							<span class="pwckMsg ok1">비밀번호가 일치합니다.</span>
							<span class="pwckMsg no1">비밀번호가 일치하지 않습니다.</span>
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" id="name" name="b_Name" class="form-control" placeholder="이름 입력" required></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="email" name="b_Email" class="form-control" value="${b_Email }" readonly="readonly"></td>
					</tr>
					<tr>
						<th>사업자 번호</th>
						<td><input type="text" id="businesscode" name="b_BusinessCode" class="form-control" placeholder="사업자 번호 입력" required></td>
					</tr>
				</table>
				<div class="d-flex justify-content-center" style="margin:30px;">
					<input class="btn btn-outline-dark" type="submit" value="회원가입">
				</div>
				</form>
			</div>
		</div>
	</div>
</section>
<script>
	//아이디 중복체크
	$(function(){
		$("#userId").keyup(function(){
			const id=$(this).val();
			const reg = /^(?=.*[a-z])(?=.*\d)[a-z\d]{5,10}$/;
			if(id.trim().length>4){
				if(!reg.test(id)){
					$(".idMsg.ok").hide();
					$(".idMsg.no").hide();
					$(".idMsg.error").show();
					$(".idMsg.ok").attr("name","no");
					$(".idMsg.no").attr("name","no");
					$(".idMsg.error").attr("name","ok");
				}else{	
					$.ajax({
						url:"${path}/member/checkIdB",
						data:{userId:id},
						success:function(data){
							console.log(data);
							if(data=='false'){
								$(".idMsg.ok").hide();
								$(".idMsg.no").show();
								$(".idMsg.error").hide();
								$(".idMsg.ok").attr("name","no");
								$(".idMsg.no").attr("name","ok");
								$(".idMsg.error").attr("name","no");
							}else{
								$(".idMsg.ok").show();
								$(".idMsg.no").hide();
								$(".idMsg.error").hide();
								$(".idMsg.ok").attr("name","ok");
								$(".idMsg.no").attr("name","no");
								$(".idMsg.error").attr("name","no");
							}
						}
					});
				}
			}else{
				$(".idMsg").hide();
				return;
			}
		})
	})
	//비밀번호 일치체크
	$("#userPwck").keyup(function(){
		let pw=$("#userPw").val();
		let pwck=$("#userPwck").val();
	
			if(pwck.trim().length>=pw.trim().length){
				if(pw == pwck) {
					$(".no1").hide();
					$(".ok1").show();
					$(".no1").attr("name","no");
					$(".ok1").attr("name","ok");
				}else{
					$(".no1").show();
					$(".ok1").hide();
					$(".no1").attr("name","ok");
					$(".ok1").attr("name","no");
				}
			}else{
				$(".no1").hide();
				$(".ok1").hide();
				$(".no1").attr("name","no");
				$(".ok1").attr("name","no");
			}
		
	});
	$("#userPw").keyup(function(){
		let pw=$("#userPw").val();
		let pwck=$("#userPwck").val();
		const reg = /^(?=.*[a-z])(?=.*\d)[a-z\d]{5,10}$/;
		if(pw.trim().length>3){
			if(!reg.test(pw)){
				$(".ok3").hide();
				$(".no3").show();
				$(".ok3").attr("name","no");
				$(".no3").attr("name","ok");
				
			}else{
				$(".ok3").show();
				$(".no3").hide();
				$(".ok3").attr("name","ok");
				$(".no3").attr("name","no");
			}
		}else{
			$(".ok3").hide();
			$(".no3").hide();
			$(".ok3").attr("name","no");
			$(".no3").attr("name","no");
		}
		if(pw != "" && pwck != ""){
			if(pwck.trim().length=pw.trim().length){
				if(pw == pwck) {
					$(".no1").hide();
					$(".ok1").show();
					$(".no1").attr("name","no");
					$(".ok1").attr("name","ok");
					
				}else{
					$(".no1").show();
					$(".ok1").hide();
					$(".no1").attr("name","ok");
					$(".ok1").attr("name","no");
					
				}
			}
		}
	});
	//중복 아이디+ 형식에 맞지 않은 아이디  + 비밀번호 불일치 + 형식에 맞지 않는 비밀번호 가입방지
    function fn_checkEndB(){
		
		//아이디 1
        let idMsg1 = $(".no").attr("name");
      	//아이디 2
        let idMsg2 = $(".error").attr("name");
		//재확인
        let pwckMsg = $(".no1").attr("name");
		//비번
		let pwMsg = $(".no3").attr("name");
        if(idMsg1=="ok"){
        	alert("중복된 아이디는 가입할 수 없습니다.");
        	return false;
        }
        if(idMsg2=="ok"){
        	alert("올바른 형식의 아이디를 입력해주세요.");
        	return false;
        }
        else if(pwMsg=="ok"){
        	alert("올바른 형식의 비밀번호를 입력해주세요.");
        	return false;  
        }
        else if(pwckMsg=="ok"){
        	alert("비밀번호가 일치하지 않습니다.");
        	return false;
        }

        return true;
	}  		
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>