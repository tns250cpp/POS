<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>HOME</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link href="/resources/css/floating-labels.css" rel="stylesheet">
</head>
<body>
	<!-- ======================= 헤더 =============================== -->
	<%@include file="/WEB-INF/views/common/stock_header.jsp" %>	
		
	<form class="form-signin" action="/stock/doModify" method="post">
		<h1 class="h3 mb-3 font-weight-normal">변경 내용을 입력하세요.</h1>	

		<div class="form-label-group">
			<input type="text" id="inputCode" name="code" class="form-control"
						placeholder="변경할 상품(상품코드)" required autofocus>
			<label for="inputCode">상품코드</label>
		</div>
		<div class="form-label-group">
			<input type="text" id="inputCode" name="name" class="form-control"
						placeholder="상품명" required autofocus>
			<label for="inputCode">상품명</label>
		</div>
		<div class="form-label-group">
			<input type="text" id="inputVolume" name="volume" class="form-control"
						placeholder="수량" required>
			<label for="inputVolume">수량</label>
		</div>
		<div class="form-label-group">
			<input type="text" id="inputPrice" name="price" class="form-control"
						placeholder="가격" required>
			<label for="inputPrice">가격</label>
		</div>

		<button type="submit" class="btn btn-lg btn-primary btn-block">변경</button>
		<button type="button" class="btn btn-lg btn-primary btn-block"
			onclick="location.href='/stock'">뒤로 가기</button>
		<hr>
		<p class="mt-5 mb-3 text-muted">&copy; POS</p>
	</form>


</body>
</html>