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
		
	</style>
</head>
<body>
	<div class="container">
		<h1>Save Thread</h1>
		<hr>
		
		
		<label>저장할 쓰레드 키</label>
		<input type="text" value="p0000000000000003297" id="thread_key_for_save">
		
		<br>
		
		<label>저장할 아이디, 나중엔 세션으로, 컨트롤러 for test 확인.</label>
		<input type="text" value="아이디" id="user_id_for_save">
		<input class="btn btn-primary" type="button" value="스레드 저장" id="thread_save_btn" name="thread_save_btn">
		
		<br>
		<hr>
		
		<label>저장된 쓰레드 리스트</label>
		<input type="button" value="목록 보기" id="thread_list_btn" name="thread_list_btn"/>
		<div id="sth">
		
		</div>
      
      	<hr>
      	
      	<label>채널 세부 정보</label>
      	<label>session이든 뭐든 ch_link 값을 받아야 함.</label>
      	<label>채널 링크 : </label>
      	<input type="text" id="ch_link_for_detail" name="ch_link_for_detail">
		<input type="button" value="목록 보기" id="channel_detail_btn" name="channel_detail_btn"/>
      	<div id="detail_list">
      	
			<button class="btn btn-lg btn-light btn-block text-left">
				<h2><small id="file_count">0&nbsp;&nbsp;</small>FILE </h2>
			</button>
			
			<div id="file_list_detail">
			</div>
			
			<button class="btn btn-lg btn-light btn-block text-left">
				<h2><small id="thread_count">0&nbsp;&nbsp;</small>PINNED </h2>
			</button>
			
			<div id="pin_list_detail">
			</div>
			
      	</div> <!-- end datail_list -->
		
	</div> <!-- end container -->




	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript">

	// 채널 세부 정보
	$("#channel_detail_btn").on("click", function(){
			doChannelDetailFile();
			doChannelDetailPin();
		})
	
	function doChannelDetailFile(){
			$.ajax({
				type : "POST",
				url : "${hContext}/main/fileDownloadListChLink.do",
				dataType : "html",
				async : true,
				data : {
					"chLink" : $("#ch_link_for_detail").val()
				},
				success : function(data) {
					var parseData = JSON.parse(data);
					$("#file_list_detail").empty();
					var html = "";
					console.log("size " + parseData.length);
					document.getElementById('file_count').innerHTML = parseData.length + "&nbsp;&nbsp;";
					$.each(parseData, function(i, value) {
						html += "<div class='card shadow h-100 py-2'>";
						html += "<div style='padding:5px; padding-top:10px;' class='card-body'>";
						html += "<div>";
						html += "<img style='float:left; margin-right: 5px;' width='50px' height='50px' src='${hContext}/resources/img/files.jpg' alt=''>";
						html += "<h6><strong>"+value.fileName+"</strong>";
						html += "</h6>";
						html += "<h6><strong>" + value.regId + "</strong>&nbsp;"+ value.regDt + "</h6>";
						html += "</div>";
						html += "</div>";
						html += "</div>";
					  });
					$("#file_list_detail").append(html);
				},
				error : function(){
					
					}
			});

		}

	function doChannelDetailPin(){
			$.ajax({
				type:"POST",
	               url:"${hContext}/main/doSelectListIsPinned.do",
	               dataType:"html",
	               async: true,
	               data:{
	            	   "chLink" : $("#ch_link_for_detail").val()
	               },
	               success: function(data){
						var parseData = JSON.parse(data);
						$("#pin_list_detail").empty();
						var html = "";
						console.log("size " + parseData.length);
						document.getElementById('thread_count').innerHTML = parseData.length + "&nbsp;&nbsp;";
						$.each(parseData, function(i, value) {
							html += "<div class='card shadow h-100 py-2'>";
							html += "<div class='card-body'>";
							html += "<div>";
							html += "<img style='float:left; margin-right: 5px;' width='50px' height='50px' src='' alt=''>";
							html += "<h6><strong>"+value.regId+"</strong>";
							html += "<span> " + value.regDt + "</span></h6>";
							html += "<h6>" + value.contents + "</h6>";
							html += "<input type='hidden' value='"+ value.thrKey +"' name='thread_id_in_detail_list'>"
							html += "</div>";
							html += "</div>";
							html += "</div>";
						  });
						$("#pin_list_detail").append(html);
		               }
				});
		}
	// 채널 세부 정보

	
	// 스레드 저장
	$("#thread_save_btn").on("click", function(){
			doSaveThread();
		});

	function doSaveThread(){
			$.ajax({
				type : "POST",
				url : "${hContext}/save/doSaveThread.do",
				dataType : "html",
				async : true,
				data : {
					"thrKey" : $("#thread_key_for_save").val()
				},
				success : function(data) {
					console.log("쓰레드 저장 성공");
				},
				error : function(){
					console.log("쓰레드 저장 삭제");
					}
			});
		}
	// 스레드 저장
	
	// 저장된 스레드 목록
	$("#thread_list_btn").on("click", function(){
			doSelectList();	
		})
	
	function doSelectList(){
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
</body>
</html>