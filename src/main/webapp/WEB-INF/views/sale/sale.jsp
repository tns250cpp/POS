<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SALE</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link href="/resources/css/floating-labels.css" rel="stylesheet">
</head>
<body>
	<!-- ======================= 헤더 =============================== -->
	<%@include file="/WEB-INF/views/common/main_header.jsp" %>	
		
	<form class="form-signin" action="/sale/doRegister" method="post">
		<h1 class="h3 mb-3 font-weight-normal">판매 상품 등록</h1>
		<hr>

		<div class="form-label-group">
			<input type="text" id="inputName" name="name" class="form-control" 
				placeholder="상품명" required autofocus>
			<label for="inputName">상품명</label>
		</div>
		<div class="form-label-group">
			<input type="text" id="inputVolume" name="volume" class="form-control" 
				placeholder="수량" required>
			<label for="inputVolume">수량</label>
		</div>

		<hr>
		<button type="submit" class="btn btn-lg btn-primary btn-block">등록</button>
		<button type="button" class="btn btn-lg btn-primary btn-block"
			onclick="location.href='/sale'">뒤로 가기</button>
		<hr>
		<p class="mt-5 mb-3 text-muted">&copy; POS</p>
	</form>

</body>
</html>