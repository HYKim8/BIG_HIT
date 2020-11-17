<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="hContext" value="${pageContext.request.contextPath }" ></c:set> 
<%@page import="com.bighit.on.user.dao.UsersVO" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>users</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Start your development with a Dashboard for Bootstrap 4.">
 <meta name="author" content="Creative Tim">
 <link rel="icon" href="${hContext}/resources/img/favicon.ico" type="image/x-icon">
 <!-- Fonts -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">
<!-- Icons -->
<link rel="stylesheet" href="${hContext}/resources/css/nucleo.css" type="text/css">
<link rel="stylesheet" href="${hContext}/resources/css/all.min.css" type="text/css">
<!-- Argon CSS -->
<link rel="stylesheet" href="${hContext}/resources/css/argon.css" type="text/css">

<title>users first page</title>

</head>
<body class="bg-white">
<div class="main-content">
    <div class="header bg-white py-7 py-lg-8 pt-lg-9">
      <div class="container">
        
      </div>
    </div>
	 <!-- Page content -->
    <div class="container mt--8 pb-5">
      <div class="row justify-content-center">
        <div class="col-lg-5 col-md-7">
          <div class="card bg-white mb-0">
            <div class="card-header bg-light pb-5">
              <div class="text-muted text-center mt-2 mb-3"><h2>On에서 새 워크스페이스에 참여</h2></div>
              <div class="btn-wrapper btn-block text-center">
                <a href="#" class="btn btn-neutral btn-icon">
                  <span class="btn-inner--icon"><img src="${hContext}/resources/img/google.svg"></span>
                  <span class="btn-inner--text">Google</span>
                </a>
              </div>
            </div>
            <div class="card-body px-lg-5 py-lg-5">
              <div class="text-center text-muted mb-4">
                <small>또는</small>
              </div>
              <div>
              	<p class="text-lead text-gray "><small> 성명</small> </p>
			  </div>
			  
              <form role="form" action="${hContext}/users/signUpView.do">
              	<input type="hidden" id="ws_link" name="ws_link" value="${usersVO.getWs_link()}">
              	<input type="hidden" id="email" name="email" value="${usersVO.getEmail()}">
                <div class="form-group mb-3">
                  <div class="input-group input-group-merge input-group-alternative">
                    <input class="form-control" placeholder="고객님의 이름" type="text" name="name" id="name">
                  </div>
                </div>
                <p class="text-lead text-gray"><small> 비번</small> </p>
                <div class="form-group">
                  <div class="input-group input-group-merge input-group-alternative">
                    
                    <input class="form-control" placeholder="고유한 비밀번호" type="password" name="password" id="password">
                  </div>
                </div>
                <div class="text-center">
                  <button type="button" class="btn btn-primary my-4" id="doSignUp">Sign up</button>
                </div>
                <div class="custom-control custom-control-alternative custom-checkbox">
                  <input class="custom-control-input" id=" customCheckLogin" type="checkbox">
                  <label class="custom-control-label" for=" customCheckLogin">
                    <span class="text-muted">on과 관련해서 이메일을 받겠습니다.</span>
                  </label>
                </div>
               
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
</div>

	 <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${hContext}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
		$("#doSignUp").on("click", function(){

			if($("#name").val().length < 5 || $("#name").val().length > 80) {
				alert("이름은 4자 이상 80자 이하로 입력해주세요.");
				return ;
			}

			if($("#password").val().length < 5 || $("#password").val().length > 80) {
				alert("비밀번호는 4자 이상 80자 이하로 입력해주세요.");
				return ;
			}

			if(confirm("이대로 입력하시겠습니까?") == false)return;

			$.ajax({
				type:"POST",
	            url:"${hContext}/users/doSignUp.do",
	            dataType:"html",
				data:{
				"name":$("#name").val().trim(),
				"password":$("#password").val().trim()
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

		

	
	</script>	


</body>
</html>

