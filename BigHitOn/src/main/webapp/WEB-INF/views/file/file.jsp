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
		action="file_test.do"
		enctype="multipart/form-data">
		<label>Select File</label> <br /> 
		<input type="file" name="file" id="file_test"/>
		<input type="button" value="테스트" id="test">
		<input type="text" id="real_path" name="real_path" value="" />
	</form>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>   
    
    <!-- javascript -->
    <script type="text/javascript">
		$("#test").on("click", function(){
			var formData = new FormData();
			formData.append("file", $("#file_test")[0].files[0]);

			
			console.log("on doUpload()");
			type:"POST",
			url:"${hContext}/file/doUpload.do",
			processData: false,
			contentType: false,
			data: formData,
			success: function(data){

				}




			
		})

		
		
	    
    </script>
	
	
	
</body>
</html>