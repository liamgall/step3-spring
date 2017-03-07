<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
	    
	<div align="center">
		        
		<h2>그런 이메일 없습니다</h2>
		<h3>2초 뒤에 메인화면으로 이동합니다</h3>
		    
	</div>
	<form id="form" action="/" method="get">
	</form>
</body>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(document).ready(function(){
		console.log();
		setTimeout(function(){
			$('#form').submit();
		}, 2000);
	});
</script>
</html>