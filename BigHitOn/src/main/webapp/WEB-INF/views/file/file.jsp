<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="hContext" value="${pageContext.request.contextPath }" ></c:set> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="fileUpload" method="post"
		action="file_test.do" id="form_data"
		enctype="multipart/form-data">
		<label>Select File</label> <br /> 
		<input type="file" name="filename" id="file_test"/>
		<input type="button" value="테스트" id="test">
		<input type="button" value="다운로드" id="download"/>
		<input type="text" id="real_path" name="real_path" value="" />
	</form>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>   
    
    <!-- javascript -->
    <script type="text/javascript">
		$("#test").on("click", function(){
				console.log("Test Clicked");
				doInsert();			
			})
		
		$("#download").on("click", function(){
				console.log("download Clicked");
				doDownload();

			})
		
		function doDownload(){
				console.log("doDownload()");
				
			}
		
		function doInsert(){
			console.log("doInsert()");
			var formData = new FormData($("#form_data")[0]);
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
		
		
	    
    </script>
	
	
	
</body>
</html>