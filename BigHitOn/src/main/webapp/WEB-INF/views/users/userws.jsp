<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="hContext" value="${pageContext.request.contextPath }" ></c:set> 	
<%@page import="com.bighit.on.workspace.WorkSpaceVO" %>
<%@page import="com.bighit.on.workspace.WorkSpaceServiceImpl" %>
<%@page import="com.bighit.on.email.EmailVO" %>
	
<!DOCTYPE html>	
<html>	
<head>	
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->

  <title>ON 로그인</title>	
  <!-- Bootstrap cdn 설정 -->	
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">	
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">	
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">	
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>	
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>	
  <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>	
  <link rel="icon" href="${hContext}/resources/img/favicon.ico" type="image/x-icon">
 <!-- Fonts -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">
<!-- Icons -->
<link rel="stylesheet" href="${hContext}/resources/css/nucleo.css" type="text/css">

</head>	
<body>	
  <div id="wrapper">
   <!-- div container -->
    <div class="container">
	    <div class="page-header">
	    	<h1>로그인/워크스페이스</h1>
	    </div>
  <!-- Tab을 구성할 영역 설정-->	
  <div style="margin:10px;">	
    <!-- tab 영역이다. -->	
    <ul class="nav nav-tabs">	
      <!-- Tab 아이템이다. 태그는 li과 li > a이다. li태그에 active는 현재 선택되어 있는 탭 메뉴이다. -->	
      <li class="active" ><a href="#doLogin" data-toggle="tab">로그인</a></li>
      <li class="disabled disabledTab"><a href="#myWs" data-toggle="tab">내 워크스페이스</a></li>	
    </ul>	
    
    <!-- Tab이 선택되면 내용이 보여지는 영역이다. -->	
    <!-- 태그는 div이고 class는 tab-content로 설정한다. -->	
    <div class="tab-content">	
      <!-- 각 탭이 선택되면 보여지는 내용이다. 태그는 div이고 클래스는 tab-pane이다. -->	
      <!-- active 클래스는 현재 선택되어 있는 탭 영역이다. -->
      
      <div class="tab-pane fade in active" id="doLogin">        
        <h1>이메일로 로그인</h1>
         <input class="form-control" placeholder="name@work-email.com" type="email" name="email" id="email">
      	 <button type="button" class="btn btn-primary my-4" id="doLoginBtn">이메일로 로그인</button>

      </div>
      
      <div class="tab-pane fade" id="myWs">
        <h1>내 워크스페이스</h1>
      	<table class="table table-striped table-bordered table-hover table-condensed">
      				<thead class="bg-primary">
						<th class="">워크스페이스 링크</th>
						<th class="">워크스페이스 이름</th>
						<th class="">실행</th>
					</thead>
      		<tbody id="wsTable"></tbody>      		
      	</table>
      		
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

    //모든 컨트롤(element)가 로딩이 완료되면.
	$(document).ready(function(){   
		console.log("document ready");   		
		
	});//document ready 

	
	//로그인 이벤트
	$("#doLoginBtn").on("click",function(){
		//alert("wsName_btn");
		var wsLink = $('#email').val();
		if(null == wsLink || wsLink.trim().length==0){
			$('#email').focus();
			alert("이메일을 입력해주세요.");
			return;
		}
		$('a[href="#myWs"]').tab('show');

		$.ajax({
            type:"GET",
            url:"${hContext}/users/wsList.do",
            dataType:"html", 
            data:{
                 "email" :$("#email").val()                    
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
                    html += '<td id=\'wslink\'>'+ list[i].wsLink+'</td>';
                    html += '<td >'+ list[i].wsName+'</td>';
                    //<button class=\'btn btn-primary btn-lg btn-block\' id=\'myWsBtn\' value=\'실행\' />
                    html += "<td> <button type= \'button\' class =\'btn btn-primary btn-md btn-block\' id=\'myWsBtn\'> " + "실행" +"</button></td>"
                    html += '<tr>';
                  }                     
              	 $("#wsTable").empty();
                 $("#wsTable").append(html);
                 
               }
            },
            error:function(xhr,status,error){
             alert("error:"+error);
            },
            complete:function(data){
            
            }   
          
        });//--ajax
	});
	
	$(document).on("click", "#myWsBtn", function(){
	    var wslink = $(this).parent().parent().children("#wslink").text();
	    $.ajax({
			type:"POST",
            url:"${hContext}/users/doLogin.do",
            dataType:"html",
			data:{
			"ws_link": wslink,
			"email":$("#email").val()
			},
		success : function(data){
			var jsonData = JSON.parse(data);			
			window.location.href="${hContext}/main/index.do"
		},
		complete:function(data){
             
        },
        error:function(xhr,status,error){
            alert("error:"+error);
        }

		});
	});
	//클릭으로 못넘어가게
	$('.nav-tabs li.disabled > a[data-toggle=tab]').on('click', function(e) {
    	e.stopImmediatePropagation(); 
	}); 
	
    </script>
</body>			
</html>