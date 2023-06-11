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
					<div style="float: right">
						<button type="button" class="btn btn-primary btn-block"
								onclick="location.href='/statistics'">뒤로 가기</button>
					</div>
					<p><b>조회 날짜 : ${startDate } ~ ${endDate }</b></p>
					<form action="/statistics/date" method="post">
						<div class="row">
							<div style="border: 3px solid;">
								<p><b>기간 내 총 판매량  </b></p>
								<p><b>${maxVolume }개</b></p>
							</div>
							<div style="border: 3px solid;">
								<p><b>기간 내 총 매출액  </b></p>
								<p><b>${maxRevenue }원</b></p>
							</div>
							<div style="border: 3px solid;">
								<p><b>기간 내 최다 판매제품  </b></p>
								<p><b>${topSellingProduct }</b></p>
							</div>
						</div>
						<br>
					</form>
					<table class="table">
						<thead>
							<tr>
								<th>No.</th>
								<th>상품코드</th>
								<th>상품명</th>
								<th>수량</th>
								<th>단가</th>
								<th>금액</th>
								<th>날짜</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="product" items="#{totalListByDate}">
								<tr>
									<td><c:out value="${product.number}"></c:out></td>
									<td><c:out value="${product.code}"></c:out></td>
									<td><c:out value="${product.name}"></c:out></td>
									<td><c:out value="${product.volume}"></c:out></td>
									<td><c:out value="${product.unitPrice}"></c:out></td>
									<td><c:out value="${product.totalPrice}"></c:out></td>
									<td><c:out value="${product.date}"></c:out></td>
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
		<%@include file="/WEB-INF/views/common/footer.jsp"%>
	</div>


</body>
</html>