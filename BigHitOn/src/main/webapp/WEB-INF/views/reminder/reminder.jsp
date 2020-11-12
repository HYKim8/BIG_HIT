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
    <title>Reminder</title>
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
    <style>
        #modal {
          display: none;
          position:relative;
          width:100%;
          height:100%;
          z-index:1;
        }
        
        #modal h2 {
          margin:0;
        }
        #modal button {
          display:inline-block;
          width:100px;
          margin-left:calc(100% - 100px - 10px);
        }
        
        #modal .modal_content {
          width:300px;
          margin:100px auto;
          padding:20px 10px;
          background:#fff;
          border:2px solid #666;
        }
        
        #modal .modal_layer {
          position:fixed;
          top:0;
          left:0;
          width:100%;
          height:100%;
          background:rgba(0, 0, 0, 0.5);
          z-index:-1;
        }   
</style> 
    
</head>
<body>
	
	<div class="container">
		<div class="page-header">
			<h1>REMINDER</h1>
		</div>
		<div>
			<input type="text" id="thr_key_input"/>
			<input type="text" id="reg_id_input"/>
			<input type="button" id="go" value="go"/>
		</div>
		<div class="row">
			<div class="table-responsive">
				<!-- table -->
				<table id="reminder_table" class="table table-striped table-bordered table-hover table-condensed">
					<thead class="bg-primary">
						<th class="">remind_id</th>
						<th class="">thr_key</th>
						<th class="">remind_time</th>
						<th class="">reg_id</th>
						<th class="">reg_dt</th>
					</thead>
					<tbody></tbody>
				</table>
			
			</div>
			<!-- end table-responsive -->
		</div>
		<!-- end row -->
		<form action="${hContext }/reminder/doInsert.do" name="reminder_edit" method="post">
		<!-- 
		/**
		 * need thrKey, remindTime, getId
		 */
		 -->
		
			<label>시간 입력 폼</label>
			<input type="text" id="time_input">	
			<input type="button" id="input_button" value="입력">
			
			<label>text input</label>
			<input type="text" id="text_input" onkeyup="enterkey()">
	
		</form>
		
		<label>자동완성 테스트</label>
		<input type="text" id="city">
		<input onclick="setTimeout(time1, 1000)" type="button" id="start_timeinterval" value="타임인터벌">
		
    	<button type="button" id="modal_opne_btn">모달 창 열기</button>
	</div>
   
       
</div>

<div id="modal">
   
    <div class="modal_content">
        <h2>Reminder</h2>
       
        <p>모달 창 입니다.</p>
       
        <button type="button" id="modal_close_btn">모달 창 닫기</button>
       
    </div>
   
    <div class="modal_layer"></div>
</div>
<script>
    document.getElementById("modal_opne_btn").onclick = function() {
        document.getElementById("modal").style.display="block";
    }
   
    document.getElementById("modal_close_btn").onclick = function() {
        document.getElementById("modal").style.display="none";
    }   
</script>
	<!-- end container -->



	





	

	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <!-- 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    -->
    <script type="text/javascript">

    function enterkey() {
        if (window.event.keyCode == 13) {
 
            	helloWorld();
        	}
		}

	function helloWorld(){
			if($("#text_input").val().trim()=="/remind"){
					// remind 명령어
					
				}
		}
	  
    function time1(){
			console.log("doChkAlarm");
			setTimeout(time2, 1000);
			doChkAlarm();
        }

    function time2(){
	    	console.log("doChkAlarm");
			setTimeout(time1, 1000);
			doChkAlarm();
        }

	function doChkAlarm(){
		$.ajax({
			type:"POST",
            url:"${hContext}/reminder/doChkAlarm.do",
            dataType:"html",
            async: true,
			data:{},
			success: function(data){
				var parseData = JSON.parse(data);

					console.log("parseData" + parseData);
					$.each(parseData, function(i, value){
						let today = new Date();
						let remindTime = new Date(value.remindTime);
						let calTime = Math.floor((today-remindTime)/1000);
						console.log("calTime : " + calTime);
						
						if(calTime < 60 && calTime > 0 && (value.status == "0")){
							console.log("작동해요~");
							<!-- 작동 후 어떻게 알릴건지 -->
							} else {
							console.log("작동 안해요~");
							}
						});
				}
		});
	}

	
	function doDelete(remindId){
			$.ajax({
				type:"POST",
				url:"${hContext}/reminder/doDelete.do",
				dataType:"html",
				async:true,
				data:{
					"remindId":remindId
					},
				success:function(){
					console.log("delete complete");
					}
				})
		}
	
	  $(function() {
	      var key_word = ["/remind", "/helloworld", "/ㅋㅋㅋ", "/4번째"];
	      $("#text_input").autocomplete({
	          source: key_word,
	          select: function(event, ui) {
	              console.log(ui.item);
	          },
	          focus: function(event, ui) {
	              return false;
	              //event.preventDefault();
	          },
	          autoFocus: true,
	          matchContains: false,
	          minLength: 1,
	          position: { my: "left top", at: "left bottom", collision: "none" }
	      });
	  });

    
	

	$("#go").on("click", function(){
			doSelectList();
			console.log("yo");
		});

	$("#input_button").on("click",function(){
		console.log("click input_button");
		doInsert();
	});

	function doInsert(){
			$.ajax({
					type:"POST",
	                url:"${hContext}/reminder/doInsert.do",
	                dataType:"html",
	                async: true,
					data:{
						"remindTime":$("#time_input").val()
						},
					success: function(data){
						var parseData = JSON.parse(data);
						console.log("성공!");
						}
				});
		}



	
     
     function doSelectList(){
			$.ajax({
				type:"GET",
	               url:"${hContext}/reminder/doSelectList.do",
	               dataType:"html",
	               async: true,
	               data:{
	               		"thrKey":$("#thr_key_input").val(),
	               		"regId":$("#reg_id_input").val()
	               },
	               success: function(data){
						var parseData = JSON.parse(data);
						$("#reminder_table>tbody").empty();
						var html = "";


						$.each(parseData, function(i, value) {
							  html += "<tr>";
							  html += "<td class='text-center'>"+value.remindId+"</td>";
							  html += "<td class='text-left'>"+value.thrKey+"</td>";
							  html += "<td class='text-left'>"+value.remindTime+"</td>";
							  html += "<td class='text-center'>"+value.regId+"</td>";
							  html += "<td class='text-left'>"+value.regDt+"</td>";
							  html += "</tr>";
						  });
						
						$("#reminder_table>tbody").append(html);


		               }
				});
		}

    
    </script>
</body>
</html>