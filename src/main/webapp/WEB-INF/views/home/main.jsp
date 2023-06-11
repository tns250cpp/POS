<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>HOME</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="./resources/css/basic.css" >
</head>
<body>
	<!-- ======================= 헤더 =============================== -->
	<%@include file="/WEB-INF/views/common/main_header.jsp" %>	
	
	
	 <main role="main" class="container">
      <div class="jumbotron">
        <h1>[${role }] 님 안녕하세요!</h1>
        <h2>POS Application 입니다.</h2>
        <p class="lead">상단의 옵션바를 활용하여 원하시는 기능을 이용해주세요.</p>
      </div>
    </main>

	<!-- ============================= 푸터 ============================= -->
	<div class="footer">
		<%@include file="/WEB-INF/views/common/footer.jsp" %>
	</div>


</body>
</html>