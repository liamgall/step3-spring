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
		        
		<h2>이메일 인증 성공</h2>
		<h3>2초 뒤에 이동합니다</h3>
		    
	</div>
</body>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(document).ready(function(){
		console.log();
		setTimeout(function(){
			var form = document.createElement('form');
			var objs;
			objs = document.createElement('input');
			objs.setAttribute('type', 'hidden');
			objs.setAttribute('name', 'eMail');
			objs.setAttribute('value', '${eMail}'.substring(0, '${eMail}'.length-1));
			form.appendChild(objs);
			form.setAttribute('method', 'post');
			form.setAttribute('action', "/join");
			document.body.appendChild(form);
			form.submit();
		}, 2000);
	});
</script>
</html>