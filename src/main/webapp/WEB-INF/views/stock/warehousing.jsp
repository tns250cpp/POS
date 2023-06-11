<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>STOCK</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link href="/resources/css/floating-labels.css" rel="stylesheet">

</head>
<body class="text-center">

	<!-- ======================= 헤더 =============================== -->
	<%@include file="/WEB-INF/views/common/stock_header.jsp"%>
	
	<form class="form-signin" action="/stock/doWarehousing" method="post">
		<h1 class="h3 mb-3 font-weight-normal">상품 입고</h1>	

		<div class="form-label-group">
			<input type="text" id="inputCode" name="code" class="form-control"
						placeholder="상품코드" required autofocus>
			<label for="inputCode">상품코드</label>
		</div>
		<div class="form-label-group">
			<input type="text" id="inputName" name="name" class="form-control"
						placeholder="상품명" required>
			<label for="inputName">상품명</label>
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

		<button type="submit" class="btn btn-lg btn-primary btn-block">상품입고</button>
		<button type="button" class="btn btn-lg btn-primary btn-block"
			onclick="location.href='/stock'">뒤로 가기</button>
		<hr>
		<p class="mt-5 mb-3 text-muted">&copy; POS</p>
	</form>
	 
	

</body>
</html>