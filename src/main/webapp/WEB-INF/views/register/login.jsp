<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<link href="/resources/css/login.css" rel="stylesheet">
</head>
<body class="text-center">
	<form class="form-signin" action="/home/login" method="post">
	
		<h1>TEAM</h1> 
		<h1>빈(bean)집털이</h1>
		<hr>
		<h1 class="h3 mb-3 font-weight-normal">POS Web Application</h1>
		

		<input type="text" name="id" class="form-control"
			placeholder="Id" required autofocus> 
		
		<input type="password" name="password" class="form-control"
			placeholder="Password" required>
				
		<button type="submit" class="btn btn-lg btn-primary btn-block">로그인</button>
		
		<button type="button" class="btn btn-lg btn-primary btn-block" onclick="location.href='/signup'">회원가입</button>
			
		<hr>
		<p class="mt-5 mb-3 text-muted">&copy; POS</p>
	</form>
</body>
</html>