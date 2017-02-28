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
		        
		<h2>가입을 환영합니다.</h2>
		<table style="border: 1px solid black">
			<tr>
				<td>이메일주소 :</td>
				<td>${userForm.email}</td>
			</tr>
			<tr>
				<td>우편번호 :</td>
				<td>${userForm.postcode5}</td>
			</tr>
			<tr>
				<td>도로명주소 :</td>
				<td>${userForm.address}</td>
			</tr>
			<tr>
				<td>상세주소 :</td>
				<td>${userForm.details}</td>
			</tr>
			<tr>
				<td>참고항목 :</td>
				<td>${userForm.extra_info}</td>
			</tr>
			<tr>
				<td>전화번호 :</td>
				<td>${userForm.phoneNumber}</td>
			</tr>

		</table>
	</div>
</body>
</html>