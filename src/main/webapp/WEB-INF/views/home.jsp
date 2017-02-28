<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>회원 가입</h1>

	<input type="eMail" id="eMail" />
	<button id="submit" value="회원가입">이메일 인증받기</button>

	<br>
	<br>
	<a href="" id="urlLink" target="_blank"></a>

</body>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$('#submit').click(
			function() {
				$.ajax({
					url : "/eMailValidation",
					type : "post",
					data : $('#eMail').val(),
					success : function(response) {
						console.log(response);
						$('#urlLink').attr('href','http://localhost:8080/validation/' + response);
						$('#urlLink').text('http://localhost:8080/validation/' + response);
					}
				})
			});
</script>
</html>
