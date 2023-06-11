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
<link rel="stylesheet" href="/resources/css/basic.css">
</head>
<body>
	<!-- ======================= 헤더 =============================== -->
	<%@include file="/WEB-INF/views/common/stock_header.jsp" %>	
		
	<main role="main">
		<div class="container">
			<div class="text-center">
				<div class="notice margin-top">
					<h3 class="hidden">입고일 조회</h3>
					
					<form action="/stock/searchByDate" method="post">
						<input type="text" name="date" placeholder="ex) yyyy-mm-dd">
						<button type="submit" class="btn btn-sm btn-secondary">검색</button>	
					</form>
					<br>
					<table class="table">
						<thead>
							<tr>
								<th>입고일</th>
								<th>상품코드</th>
								<th>상품명</th>
								<th>수량</th>
								<th>가격</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="product" items="#{productListByDate}">
							<tr>
								<td><c:out value="${product.date }"></c:out></td>
								<td><c:out value="${product.code}"></c:out></td>
								<td><c:out value="${product.name}"></c:out></td>
								<td><c:out value="${product.volume}"></c:out></td>
								<td><c:out value="${product.unitPrice}"></c:out></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</main>


	<!-- ============================= 푸터 ============================= -->
	<div class="footer">
		<%@include file="/WEB-INF/views/common/footer.jsp" %>
	</div>


</body>
</html>