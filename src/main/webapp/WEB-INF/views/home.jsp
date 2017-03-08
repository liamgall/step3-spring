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
	<a href="" id="urlLink"></a>

</body>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$('#submit').click(function() {
		$.ajax({
			url : "/eMailValidation",
			type : "post",
			data : $('#eMail').val(),
			success : function(response) {
				if(response!="error"){
					$('#urlLink').attr('href', response);
					$('#urlLink').text(response);
				}else{
					alert('이메일이 존재합니다.');
				}
			}
		})
	});
</script>
</html>
