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
			<input type="file" name="file" id="file_test"/>
			<input type="button" value="업로드" id="upload">
		</div>
		
		<div>
			<label>file id</label>
			<input type="text" id="file_id" name="file_id"/>
			<input type="button" id="submit_download" name="submit_download" value="다운로드"/>
			<label>filedownload url</label>
			<a id="url_value" href="" download>Download Receipt</a>
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

    /* function fileTypeCheck(evt){
        	var fileVal = document.getElementById("file_test").value;
        	var type = fileVal.split(".")[1];
        	
			console.log(type);
        } */
    // document.getElementById("file_test").value.split(".")[1]
    
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

								function downloadFile(data, fileName, type="text/plain") {
									  // Create an invisible A element
									  const a = document.createElement("a");
									  a.style.display = "none";
									  document.body.appendChild(a);

									  // Set the HREF to a Blob representation of the data to be downloaded
									  a.href = window.URL.createObjectURL(
									    new Blob([data], { type })
									  );

									  // Use download attribute to set set desired file name
									  a.setAttribute("download", fileName);

									  // Trigger the download by simulating click
									  a.click();

									  // Cleanup
									  window.URL.revokeObjectURL(a.href);
									  document.body.removeChild(a);
									}
								
							    
								//window.open(parseData);
								 //chrome
							    /* var filename = parseData;
							    var xhr = new XMLHttpRequest();
							    xhr.responseType = 'blob';
							    xhr.onload = function () {
							        var a = document.createElement('a');
							        a.href = window.URL.createObjectURL(xhr.response); // xhr.response is a blob
							        a.download = filename; // Set the file name.
							        a.style.display = 'none';
							        document.body.appendChild(a);
							        a.click();
							        delete a;
							    };
							    xhr.open('GET', parseData);
							    xhr.send(); */
								
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