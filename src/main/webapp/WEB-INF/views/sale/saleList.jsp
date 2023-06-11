<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<%@include file="/WEB-INF/views/common/main_header.jsp"%>

	<main role="main">
		<div class="container">
			<div class="text-center">
				<div class="notice margin-top">
					<br>

					<form action="/sale/pay" method="post">
						<button type="button" class="btn btn-primary btn"
							onclick="location.href='/sale/reset'" style="float: left;">초기화</button>

						<button type="button" class="btn btn-primary btn"
							onclick="location.href='/sale/register'" style="float: center;">판매
							상품 등록</button>

						
						
						<button type="submit" class="btn btn-primary btn" style="float: right;">계산하기</button>
						<input type="text" name="sumTotalPrice" value="${sumTotalPrice }"
							readonly="readonly" style="float: right"> 
						
						<br>
						<table class="table">
							<thead>
								<tr>
									<th>상품코드</th>
									<th>상품명</th>
									<th>수량</th>
									<th>단가</th>
									<th>금액</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="saleProduct" items="#{saleProductList}">
									<tr>
										<td>
										<input type="text" name="code" readonly="readonly" size="5"
										value="<c:out value="${saleProduct.code}"></c:out>">
										</td>
										<td><c:out value="${saleProduct.name}"></c:out></td>
										<td><c:out value="${saleProduct.volume}"></c:out></td>
										<td><c:out value="${saleProduct.unitPrice}"></c:out></td>
										<td><c:out value="${saleProduct.totalPrice}"></c:out></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</main>


	<!-- ============================= 푸터 ============================= -->
	<div class="footer">
		<%@include file="/WEB-INF/views/common/footer.jsp"%>
	</div>


</body>
</html>