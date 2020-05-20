<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${path }/resources/css/header.css"/> 
</head>
<body>
	<header class="container-fluid">
		<div class="login">
		
			 <c:if test="${loginMember==null}">
			<div >
				<span onclick="location.replace('${path}/member/login.do')">로그인</span>
				<span onclick="location.replace('${path}/member/memberEnroll1.do')">회원가입</span>
				<span onclick="location.replace('${path}/#')">주문표</span>
			</div>
			</c:if>
			
			<c:if test="${loginMember!=null}">
			<div>
				<span onclick="location.replace('${path}/member/logout.do')">로그아웃</span>
				<span onclick="location.replace('${path}/#')">마이페이지</span>
				<span onclick="location.replace('${path}/#')">주문표</span>
			</div>
			</c:if>
		
		</div>
		<div class="row headerrow">
			<div class="col-4">
			</div>
			<div class="col-4 d-flex" style="align-items: center;justify-content: center;">
				<img class="rounded-circle" style="margin:5px;width:100px;height:100px;cursor: pointer;"
					alt="rogo" src="${pageContext.request.contextPath }/resources/img/king.png" onclick="location.replace('${path}')">
			</div>
			<div class="col-4"></div>

		</div>
	</header>
