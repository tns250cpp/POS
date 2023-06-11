<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link href="./resources/css/login.css" rel="stylesheet">
</head>
<body class="text-center">
	
	<form class="form-signin" action="/home/signup" method="post">
		<h1 class="h3 mb-3 font-weight-normal">Please Log In</h1>
		
		<input type="text" name="id" class="form-control"
			placeholder="사용할 ID를 입력하세요." required autofocus> 
		
		<input type="password" name="password" class="form-control"
			placeholder="사용할 PASSWORD를 입력하세요." required>
			
		<input type="password" name="confirmPassword" class="form-control"
			placeholder="확인 PASSWORD를 입력하세요." required>
		<br>
		<b>직책</b>
		<div>
			<label><input type="radio" name="role" value="manager" checked>매니저</label>
			<label><input type="radio" name="role" value="staff">직원</label>
		</div>
		<br>		
		<button class="btn btn-lg btn-primary btn-block" type="submit">회원가입</button>
		<button type="button" class="btn btn-lg btn-primary btn-block"
					onclick="location.href='/'">뒤로 가기</button>
		<p class="mt-5 mb-3 text-muted">&copy; POS</p>
		
	</form>
	
</body>
</html>