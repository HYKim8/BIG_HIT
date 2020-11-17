<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="hContext" value="${pageContext.request.contextPath }" ></c:set> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>File</title>
    <link rel="shortcut icon" type="image/x-icon" href="${hContext}/resources/img/favicon.ico" > 
    <!-- 부트스트랩 -->
    <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <!-- <link href="/EJDBC/css/layout.css" rel="stylesheet"> -->

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<h1>YO!</h1>
	<div class="container">
		<input type="button" id="test" value="hello">
		<input type="text" id="status" value="">
		<input type="text" id="asdf" value="asdf">
		<input type="hidden" value="regId">
		<form action="testImg.do" method="get">
			<input type="submit" id="submitbtn" value="submit">
		</form>
		<!-- 얘가 hContext.concat 안 붙을 앤지 붙을 앤지 체크 해야할듯 if 때려서-->
		<!-- 밑이랑 비교해서 if해서 다르면 이미지 찍고 같으면 이미지 안찍는 방식으로. -->
		<c:set var="user_serial" value="U5835RE6LL2thumb" />
		<c:if test="${sessionScope['U5835RE6LL2thumb'] eq '/resources/img/default.jpg' }">
			<img alt="" src="${hContext.concat(sessionScope['U5835RE6LL2thumb']) }">
		</c:if>
		
		<p id="output_text"></p>
		<input type="button" id="thumb_test" value="thumb_test"/>
		<img id="user1" alt="" src="">
	</div>
	
	
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- javascript -->
    <script type="text/javascript">

    
	$(document).ready(function(){
			console.log("load Complete");
			Notification.requestPermission().then(function(result) {
				  console.log(result);
				});
		})
		
		// 알림
	$("#thumb_test").on("click", function(){
			console.log("thumb_test clicked");
			var text = "gg";
			var notification = new Notification('asd', { body: text});
		})
	

	
    function loadImg(){
	    	console.log("loadImg");
			$.ajax({
		        url: '${hContext}/file/testImg.do',
		        dataType:"html",
		        data:{"keyName":$("#key_name").val()},
		        async: true,
		        type: 'GET',
		        success: function(data){
		            	console.log("success");
		            	var parseData = JSON.parse(data);
		            	console.log(parseData);
		            	document.getElementById('user1').src = parseData;
					}
		        });
	    	}
	
	$("#test").on("click", function(){
			console.log("testBtn clicked");
			test()

		})
		
	
    
    function test(){
			$.ajax({
		        url: '${hContext}/file/testImg.do',
		        dataType:"html",
		        data:{"keyName":$("#key_name").val()},
		        async: true,
		        type: 'GET',
		        success: function(){
		            	console.log("success");
		            	
					}
	        });
	    }
    
    
    </script>
</body>
</html>