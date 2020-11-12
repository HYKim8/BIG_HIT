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
<div class="container">
	<form name="fileUpload" method="post"
		action="file_test.do" id="form_data"
		enctype="multipart/form-data">
		<div>
			<label>Select File</label> <br /> 
			<!-- 확장자 제한 걸 것. -->
			<input type="file" name="file" id="file_test" accept=".jpg, .zip, .7z, .pdf"/>
			<input type="button" value="업로드" id="upload"/>
			<input type="button" value="프로필업로드" id="uploadProfileImg"/>
		</div>
		
		<div>
			<label>file id</label>
			<input type="text" id="file_id" name="file_id"/>
			<input type="button" id="submit_download" name="submit_download" value="다운로드"/>
			<label>filedownload url</label>
			<a id="url_value" href="" rel="nofollow" download>Download Receipt</a>
			<label>test img</label>
			<!-- img 없을 경우(404) 이미지 다시 불러오기. thumbnail에 이용 -->
			<img alt="" src="" id="test_view_img">
			<div id="test_thumb">
				<img alt="" src="C|BIGHIT_thumbnail\1.jpg">
			</div>
			<input type="button" name="thumb_test_btn" id="thumb_test_btn" value="thumb"/>
		</div>
		
		<div>
			<input type="text" id="ch_link">
			<input type="button" id="row_button" value="테이블 표시">
		</div>
		
		<div class="row">
			<div class="table-responsive">
				<!-- table -->
				<table id="file_table" class="table table-striped table-bordered table-hover table-condensed">
					<thead class="bg-primary">
						<th class="">file_id</th>
						<th class="">file_name</th>
						<th class="">thr_key</th>
						<th class="">file_url</th>
						<th class="">reg_id</th>
						<th class="">reg_dt</th>
					</thead>
					<tbody></tbody>
				</table>
			
			</div>
			<!-- end table-responsive -->
		</div>
	</form>
</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- javascript -->
    <script type="text/javascript">


    	
		$("#thumb_test_btn").on("click", function(){
				console.log("thumb_test_btn Clicked");
				doTestMakeDir();
			})
			// folder 만들기
		function doTestMakeDir(){
			$.ajax({
				type:'POST',
				url:'${hContext}/file/do404Chk.do',
				dataType:"html",
	            async: true,
	            data:{
		            },
		        success:function(data){
			        		console.log("success");							
			        	}  
				});

			}
	
        <!-- 확장자 jpg로 통일할 것 -->
		$("#uploadProfileImg").on("click", function(){
				console.log("uploadProfileImg Clicked");
				doUpdateProfileImg();
			})
        
		$("#upload").on("click", function(){
				console.log("upload Clicked");
				doUpload();			
			})
		
		$("#submit_download").on("click", function(){
				console.log("submit_download Clicked");
				doDownload();

			})
		$("#row_button").on("click", function(){
				console.log("row_button Clicked");
				doSelectList();
			})
		
		
		//let today = new Date();
		//today - ~~ 큰 3days
		//	{ 파일 만료로 변환 }
		//쓰레드는 몇일치만 보여줄 것인지?	 
		
		//MQ를 써서 doUpdateProfileImg() 를 했을 경우 다른 client들에게 다시 전송하라고 메시지 전송.
		
		//thumbnail은 db에 thumbnail 칼럼 하나 만들어서 거기다가 주소 넣고, 이미지 변경을 했을 때 그 주소로 넣기
		//새로운 thumbnail 추가
		
		<!-- 확장자 jpg로 통일할 것 -->
		function doUpdateProfileImg(){
				console.log("doUpdateProfileImg")
				var fileType = document.getElementById("file_test").value.split(".");
				var last_element = fileType[fileType.length - 1];
				console.log(last_element);
				var formData = new FormData($("#form_data")[0]);
				formData.append("fileType", last_element);
				console.log("formData : " + formData);
				$.ajax({
			        url: '${hContext}/file/doUpdateProfileImg.do',
			        data: formData,
			        processData: false,
			        contentType: false,
			        type: 'POST',
			        success: function(data){
			            console.log("success");
			        },
			        error: function(err){
			            console.log("error");
			        }
			    });
    		}
		
		function doDownload(){
				console.log("doDownload()");
				// file_id를 보내고 거기서 query doSelectOne을 돌려서 url(key_name)을 뽑고 그걸로 파일을 갖고 온다.
				$.ajax({
					type:'POST',
					url:'${hContext}/file/doDownload.do',
					dataType:"html",
		            async: true,
		            data:{
						"fileId":$("#file_id").val()
			            },
			        success:function(data){
				        		var parseData = JSON.parse(data);
								console.log("get download url success!");
								console.log("parseData : " + parseData);
								document.getElementById("url_value").href = parseData;
								document.getElementById("test_view_img").src = parseData;								
				        	}  
					});
			}
		
		function doUpload(){
			console.log("doUpload()");
			var fileType = document.getElementById("file_test").value.split(".");
			var last_element = fileType[fileType.length - 1];
			console.log(last_element);
			var formData = new FormData($("#form_data")[0]);
			formData.append("fileType", last_element);
			console.log("formData : " + formData);
			$.ajax({
		        url: '${hContext}/file/doUpload.do',
		        data: formData,
		        processData: false,
		        contentType: false,
		        type: 'POST',
		        success: function(data){
		            console.log("success");
		        },
		        error: function(err){
		            console.log("error");
		        }
		    });
		}

		/* function doThumbTest(){
			$.ajax({
				type:"POST",
				url:"${hContext}/file/file/do404Chk.do",
				dataType:"html",
				async:true,
				data:{
					
					},
				success:function(data){

					}



				});
			} */
		
		function doSelectList(){
			$.ajax({
				type:"POST",
	               url:"${hContext}/file/doSelectList.do",
	               dataType:"html",
	               async: true,
	               data:{
	               		"chLink":$("#ch_link").val()
	               },
	               success: function(data){
						var parseData = JSON.parse(data);
						$("#file_table>tbody").empty();
						var html = "";
						$.each(parseData, function(i, value) {
							  html += "<tr>";
							  html += "<td class='text-center'>"+value.fileId+"</td>";
							  html += "<td class='text-left'>"+value.fileName+"</td>";
							  html += "<td class='text-left'>"+value.thrKey+"</td>";
							  html += "<td class='text-center'>"+value.fileUrl+"</td>";
							  html += "<td class='text-left'>"+value.regId+"</td>";
							  html += "<td class='text-center'>"+value.regDt+"</td>";
							  html += "</tr>";
						  });
						
						$("#file_table>tbody").append(html);
		               }
				});
		}
		
	    
    </script>
	
	
	
</body>
</html>