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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>BIGHIT</title>

    <!-- Custom fonts for this template-->
    <link href="${hContext }/resources/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <!-- Custom styles for this template-->
    <link href="${hContext }/resources/assets/css/sb-admin-2.min.css" rel="stylesheet">
	<style>
		#threadList #thrbtns #subBtns { opacity:0; float:right}
		#threadList #thrbtns:hover #subBtns { opacity:1}
	</style>
</head>
<body>
	
	
	<div id="wrapper" class="wrapper">
	<!-- sidebar -->
	<%@ include file="/WEB-INF/views/main/sideBar.jsp" %>
	
		<!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">
            
            <!-- topbar -->
			<%@ include file="/WEB-INF/views/main/topBar.jsp" %>	
            
            
            
            <div style="margin-left: 40px; margin-right: 40px;">
            <div class='card shadow h-100 py-2'>
				<div class='card-body'>
					<div>
						<h6><strong>나중에 사용하기 위해 메시지를 저장하세요.</strong></h6>
						<h6>해야 할 일을 표시하거나 다른 시간에 할 수 있도록 저장하세요. 저장된 항목은 고객님만 볼 수 있으므로 원하는 방식으로 사용하세요.</h6>
					</div>
				</div>
			</div>
			<br>
            </div>
            
			
            <div style="margin-left: 40px; margin-right: 40px;" id="sth"></div>
            
            
            
            </div>
            <!-- Main Content -->
        </div>
		<!-- Content Wrapper -->
	
	
	</div>
	
	
	




	




	<!-- profile Modal -->
    <%@ include file="/WEB-INF/views/main/profilemodal.jsp" %>
    
    <!-- reminder -->
	<%@ include file="/WEB-INF/views/main/remindermain.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript">

	// 저장된 스레드 목록
	window.onload = function(){
			doSelectListStoredThread();
		}
	
	function doSelectListStoredThread(){
			$.ajax({
				type:"POST",
	               url:"${hContext}/saveThread/doSelectList.do",
	               dataType:"html",
	               async: true,
	               data:{
	               },
	               success: function(data){
						var parseData = JSON.parse(data);
						$("#sth").empty();
						var html = "";
						
						$.each(parseData, function(i, value) {
							html += "<div class='card shadow h-100 py-2'>";
							html += "<div class='card-body'>";
							html += "<div>";
							html += "<img style='float:left; margin-right: 5px;' width='50px' height='50px' src='' alt=''>";
							html += "<h6><strong>"+value.regId+"</strong>";
							html += "<span> " + value.regDt + "</span></h6>";
							html += "<h6>" + value.contents + "</h6>";
							html += "</div>";
							html += "</div>";
							html += "</div>";
							html += "<br>";
						  });
						$("#sth").append(html);
		               }
				});
		}
	// 저장된 스레드 목록






	
	            
	
	</script>

    <!-- Bootstrap core JavaScript-->
    <script src="${hContext }/resources/assets/vendor/jquery/jquery.min.js"></script>
    <script src="${hContext }/resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${hContext }/resources/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${hContext }/resources/assets/js/sb-admin-2.min.js"></script>
</body>
</html>