<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="hContext" value="${pageContext.request.contextPath }" ></c:set> 
<%@page import="com.bighit.on.user.dao.UsersVO" %>
<!DOCTYPE html>
<html>
<head>
	<title>Sign up_ON</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="${hContext}/resources/img/favicon2.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${hContext}/resources/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${hContext}/resources/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${hContext}/resources/css/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${hContext}/resources/css/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${hContext}/resources/css/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${hContext}/resources/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${hContext}/resources/css/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${hContext}/resources/css/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${hContext}/resources/css/util.css">
	<link rel="stylesheet" type="text/css" href="${hContext}/resources/css/main.css">
<!--===============================================================================================-->
</head>

<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title" style="background-image: url(${hContext}/resources/img/bg-01.jpg);">
					<span class="login100-form-title-1">
						워크스페이스에 참여
					</span>
				</div>

				<form class="login100-form validate-form" action="${hContext}/users/signUpView.do">
					<div class="wrap-input100 validate-input m-b-26" data-validate="Username is required">
						<span class="label-input100">Username</span>
						<input class="input100" type="email" name="name" id="name" placeholder="사용자 이름을 입력하세요">
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-18" data-validate = "Password is required">
						<span class="label-input100">Password</span>
						<input class="input100" type="password"  name="password" id="password" oninput="pwAvailCheck(password,value)" placeholder="고유한 비밀번호를 입력하세요">
						<span class="focus-input100"></span>
					</div>

					<div class="flex-sb-m w-full p-b-30">
							<p id="pwCheck" ></p>   
					</div>

					<div class="container-login100-form-btn">
						<button type="button" id="doCreate" class="login100-form-btn">
							참여하기
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	 <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${hContext}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	$("#doCreate").on("click", function(){
		var name = $('#name').val();
		var password = $('#password').val();
		if(null == name || name == false ) {
			alert("이름을 입력해주세요.");
			return ;
		}
		if(null == password || password == false ) {
			alert("비밀번호를 입력해주세요.");
			return ;
		}
		if(confirm("새 워크스페이스에 참여하시겠습니까?") == false)return;
		$.ajax({
			type:"POST",
            url:"${hContext}/users/doLogin.do",
            dataType:"html",
			data:{
			"name":name.trim(),
			"password":password.trim()
			},
		success : function(data){
			var jsonData = JSON.parse(data);
			window.location.href="${hContext}/main/main.do"
		},
		complete:function(data){
        },
        error:function(xhr,status,error){
            alert("error:"+error);
        }
		});
	});
	function pwAvailCheck(pw){
		$.ajax({
			type:"POST",
            url:"${hContext}/users/pwAvailCheck.do",
            dataType:"html", 
            data:{
                 "password" :$("#password").val()  
            },
            success:function(data){//통신성공, data
	        	//alert(data);
	            var jsonData = JSON.parse(data);
	            console.log("data: "+data);
				if(data == 'true'){
					pwCheck.innerHTML = "비밀번호로 사용가능합니다.";
					pwCheck.style.color= "black";
				}else{
					pwCheck.innerHTML = "영문자,숫자,특수문자를 적어도 한개 포함해야 합니다.";
					pwCheck.style.color= "blue";
					}
	        },
	        error:function(xhr,status,error){
	        },
	        complete:function(data){
	        }
		});
	}
	</script>	
</body>
</html>