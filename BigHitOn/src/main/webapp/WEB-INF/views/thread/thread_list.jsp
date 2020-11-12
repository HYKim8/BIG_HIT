<%@page import="com.bighit.on.thread.ThreadVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="hContext" value="${pageContext.request.contextPath }" ></c:set>     
    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <link rel="shortcut icon" type="image/x-icon" href="${hContext}/resources/img/favicon.ico" > 
    <!-- 부트스트랩 -->
    <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>thread_list</title>
</head>
<body>
 <div class="row ">
	    	<form action="${hContext}/thread/ListView.do" 
	    	     name="searchFrm" class="form-inline  col-lg-12 col-md-12 text-right">
	    	    <input type="text" name="pageNum" id="pageNum" value="${vo.getPageNum()}" />
	    	    <input type="text" name="pageSize"   id="pageSize"  value="${vo.getPageSize()}" />
	    	    <input type="text" name="searchWord" id="searchWord" class="form-control  input-sm" value="${vo.getSearchWord()}"/>    		
	    	</form>
</div>
	<table>
		
		<colgroup>
			<col style="width:5%;" />
			<col style="width:auto;" />
			<col style="width:15%;" />
			<col style="width:10%;" />
			<col style="width:10%;" />
			<col style="height:100px"/>
		</colgroup>
		
		<thead>
		<tr>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
		</tr>
		</thead>
		<tbody id="threadList">
			<c:choose>
				<c:when test="${!empty threadList}">
					<c:forEach var="list" items="${threadList}">
						<tr>
							<td><c:out value="${list.thrKey}"/></td>
							<td><c:out value="${list.contents}"/></td>
							<td><c:out value="${list.regId}"/></td>
							<td><c:out value="${list.regDt}"/></td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</tbody>		
	</table>
	 <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${hContext}/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	document.addEventListener('scroll',function(){
			if($(window).scrollTop() == 0){
				$("#pageNum").val( parseInt($("#pageNum").val()) +1);
				console.log($("#pageNum").val());
				console.log($("#pageSize").val());
				console.log($("#searchWord").val());
				$.ajax({
				    type:"GET",
				    url:"${hContext}/thread/moreList.do",
				    dataType:"html", 
				    data:{
				    	  "pageNum" :$("#pageNum").val(),
				    	  "pageSize":$("#pageSize").val(),
				    	  "searchWord":$("#searchWord").val()				         
				    },  
				    success:function(data){ //성공
				       console.log("data="+data);
				       //alert("data:"+data);
				       
				       //json 분리해서 변수
				       var list = JSON.parse(data);
				       if(list.length > 0){
					       var html = "";
					       for(var i=0;i<list.length;i++){
								html += '<tr>';
								html += '<td>'+ list[i].thrKey+'<td>';
								html += '<td>'+ list[i].contents+'<td>';
								html += '<td>'+ list[i].regId+'<td>';
								html += '<td>'+ list[i].regDt;
								if(list[i].regDt != list[i].modDt ) html += '편집됨';							
								html += '<td><tr>';
					       }							
					   
						   $("#threadList").prepend(html);
							//페이즈 사이즈 만큼 내려오게끔 
						   window.scrollTo(0,200);
				       }
				    },
				    error:function(xhr,status,error){
				     alert("error:"+error);
				    },
				    complete:function(data){
				    
				    }   
				  
				});//--ajax
				}
        	});
    </script>
</body>
</html>