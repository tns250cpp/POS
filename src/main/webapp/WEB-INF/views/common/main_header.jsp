<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<a class="nav-link active" href="/home">HOME</a>
		<ul class="navbar-nav mr-auto" style="float: center">
			<li class="nav-item"><a class="nav-link" href="/stock">재고 조회</a></li>
			<li class="nav-item"><a class="nav-link" href="/sale">판매</a></li>
			<li class="nav-item"><a class="nav-link" href="/statistics">매출 통계</a></li>
		</ul>
		<form class="form-inline my-2 my-lg-0">
			<button class="btn btn-primary btn-lg" type="button"
				onclick="location.href='/home/logout' ">로그아웃</button>
		</form>
	</div>
</nav>
<br>
<br>
<br>
