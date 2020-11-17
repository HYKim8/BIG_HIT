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

  <title>워크스페이스생성</title>	
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
	    	<h1>워크스페이스생성</h1>
	    </div>
  <!-- Tab을 구성할 영역 설정-->	
  <div style="margin:10px;">	
    <!-- tab 영역이다. -->	
    <ul class="nav nav-tabs">	
      <!-- Tab 아이템이다. 태그는 li과 li > a이다. li태그에 active는 현재 선택되어 있는 탭 메뉴이다. -->	
      <li class="active" ><a href="#wsLK" data-toggle="tab">워크스페이스 링크</a></li>
      <li class="disabled disabledTab" ><a href="#wsNM" data-toggle="tab" onclick="return false;">워크스페이스 이름</a></li>	
      <!-- a 태그의 href는 아래의 tab-content 영역의 id를 설정하고 data-toggle 속성을 tab으로 설정한다. -->	
      <li class="disabled disabledTab"><a href="#pjNM" data-toggle="tab">프로젝트명</a></li>	
      <li class="disabled disabledTab"><a href="#addT" data-toggle="tab">친구추가</a></li>	
      <li class="disabled disabledTab"><a href="#complete" data-toggle="tab">완료</a></li>	
    </ul>	
    
    <!-- Tab이 선택되면 내용이 보여지는 영역이다. -->	
    <!-- 태그는 div이고 class는 tab-content로 설정한다. -->	
    <div class="tab-content">	
      <!-- 각 탭이 선택되면 보여지는 내용이다. 태그는 div이고 클래스는 tab-pane이다. -->	
      <!-- active 클래스는 현재 선택되어 있는 탭 영역이다. -->
      
      <div class="tab-pane fade in active" id="wsLK">        
        <h1>워크스페이스 링크 입력</h1>
      	<input type="text" class="form-control" name="wsLink" id="wsLink" value="${vo.wsLink}" placeholder="워크스페이스 링크" />
      	<input type="button" value="계속" id="wsLink_btn" class="btn btn-primary btn-lg btn-block" />	     
      </div>
      
      <div class="tab-pane fade" id="wsNM">
        <h1>워크스페이스 이름 입력</h1>
      	<input type="text" class="form-control" name="wsName" id="wsName" value="${vo.wsName}" placeholder="워크스페이스 이름" />
      	<input type="button" value="계속" id="wsName_btn" class="btn btn-primary btn-lg btn-block" />	
      </div>
      <!-- id는 고유한 이름으로 설정하고 tab의 href와 연결되어야 한다. -->	
      
      <div class="tab-pane fade" id="pjNM">      
        <h1>프로젝트명 입력</h1>
      	<input type="text" class="form-control" name="project" id="project" value="${vo.project}" placeholder="프로젝트명" />
      	<input type="button" value="계속" id="project_btn" class="btn btn-primary btn-lg btn-block" />	
      </div>
      	
      <!-- fade 클래스는 선택적인 사항으로 트랜지션(transition)효과가 있다.	
      <!-- in 클래스는 fade 클래스를 선언하여 트랜지션효과를 사용할 때 in은 active와 선택되어 있는 탭 영역의 설정이다. -->	
      <div class="tab-pane fade" id="addT">
        <h1>추가하고 싶은 팀원 메일 입력</h1>
      	<input type="email" class="form-control" name="email" id="email" value="${vo.email}" placeholder="팀원 email" />
        <input type="button" value="계속" id="email_btn" class="btn btn-primary btn-lg btn-block" />
      </div>

      <div class="tab-pane fade" id="complete">
      	<input type="button" value="완료" id="complete_btn" class="btn btn-primary btn-lg btn-block" />
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

	
	//워크스페이스 링크 이벤트
	$("#wsLink_btn").on("click",function(){
		//alert("wsName_btn");
		var wsLink = $('#wsLink').val();
		if(null == wsLink || wsLink.trim().length==0){
			$('#wsLink').focus();
			alert("워크스페이스 링크를 입력해주세요.");
			return;
		}
		$('a[href="#wsNM"]').tab('show');

	});
	
	//워크스페이스 이름 이벤트
	$("#wsName_btn").on("click",function(){
		//alert("wsName_btn");
		var wsName = $('#wsName').val();
		if(null == wsName || wsName.trim().length==0){
			$('#wsName').focus();
			alert("워크스페이스 이름을 입력해주세요.");
			return;
		}
		if(wsName.trim().length<=2) {
			$('#wsName').focus();
			alert("워크스페이스 이름이 짧습니다. 다시 입력해주세요.");
			return;
		}	
		$('a[href="#pjNM"]').tab('show');

	});

	//프로젝트 이름 이벤트
	$("#project_btn").on("click",function(){
		var project = $('#project').val();
		if(null == project || project.trim().length==0){
			$('#project').focus();
			alert("프로젝트 이름을 입력해주세요.");
			return;
		}
		if(project.trim().length<=2){
			$('#project').focus();
			alert("프로젝트 이름이 짧습니다. 다시 입력해주세요.");
			return;
		}
		$('a[href="#addT"]').tab('show');

	});

	//팀원추가시 이메일 보내기 이벤트
	$("#email_btn").on("click",function(){
		var email = $('#email').val();
		if(null == email || email.trim().length==0){
			$('#email').focus();
			alert("추가할 팀원의 이메일을 입력해주세요.");
			return;
		}

		$.ajax({
		    type:"POST",
		    url:"${hContext}/workspace/sendEmail.do",
		    dataType:"html", 
		    data:{"email" :$("#email").val(),
			      "wsLink" : $("#wsLink").val(),
			      "wsName" : $("#wsName").val(),
			      "userId": "${sessionScope.usersVO.name }"
		    },
		    success:function(data){ //성공
		    	$('a[href="#complete"]').tab('show');		       
		    },
		    error:function(xhr,status,error){
		     alert("error:"+error);
		    },
		    complete:function(data){
		    
		    }   
		  
	});//--ajax

	});

	//완료시 이벤트
	$("#complete_btn").on("click",function(){
		$.ajax({
		    type:"POST",
		    url:"${hContext}/workspace/doInsert.do",
		    dataType:"html", 
		    data:{"wsLink" :$("#wsLink").val(),
		    	  "wsName" :$("#wsName").val(),
		          "project":$("#project").val(),
		          "regId" : "${sessionScope.usersVO.user_serial}"
		    },
		    success:function(data){ //성공
		    	 //json 분리해서 변수
			       var jsonObj = JSON.parse(data);
			       console.log("msgId="+jsonObj.regId);
			       console.log("msgContents="+jsonObj.msgContents);
			    
			       if(null !=jsonObj && jsonObj.regId=="1"){
			    	   alert(jsonObj.msgContents);
			    	   window.location.href="${hContext}/main/main.do=wsLink?"+jsonObj.msgContents;
			       }
		    },		       
		    error:function(xhr,status,error){
		     alert("error:"+error);
		    },
		    complete:function(data){
		    
		    }   
		  
	});//--ajax
		

	});

	//클릭으로 못넘어가게
	$('.nav-tabs li.disabled > a[data-toggle=tab]').on('click', function(e) {
    	e.stopImmediatePropagation(); 
	}); 
	
    </script>
</body>			
</html>