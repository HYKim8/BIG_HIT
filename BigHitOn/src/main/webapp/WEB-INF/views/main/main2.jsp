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

<body id="page-top">
	<div class="row ">
          <form action="${hContext}/thread/ListView.do" 
               name="searchFrm" class="form-inline  col-lg-12 col-md-12 text-right">
              <input type="hidden" name="pageNum" id="pageNum" value="${searchVO.getPageNum()}" />
              <input type="hidden" name="pageSize"   id="pageSize"  value="${searchVO.getPageSize()}" />
              <input type="hidden" name="searchWord" id="searchWord" class="form-control  input-sm" value="${searchVO.getSearchWord()}"/>
              <!--  <input type="hidden" name="thrKey" id="thrKey"/> -->         
          </form>
	</div>
	
    <!-- Page Wrapper -->
    <div id="wrapper">
		
        <%@ include file="/WEB-INF/views/main/sideBar.jsp" %>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

               <%@ include file="/WEB-INF/views/main/topBar.jsp" %>
			   <c:if test="${!empty searchVO}"> <div><button id="chInfoBtn" style =" float:right" class="btn btn-primary"><i class="fa fa-info"></i></button> </div></c:if>
                    
        <c:set var="lefthalf" value = "padding-right: 20px;width:65%;float:left;display:inline;height:900px;overflow-y:auto;white-space:nowrap;" />
        <c:set var="leftfull" value = "padding-right: 20px;width:100%;float:left;display:inline;height:900px;overflow-y:auto;white-space:nowrap;" />
		<div id="divScroll" style="${leftfull}"class="media">
			<c:if test="${!empty threadList}">
				<c:set var = "vs" value="any"/>
				
				<div style="padding-left:10px; padding-right:10px;"class="media-left">
				    <a href="#">
				    <img class="media-object" src="${hContext}/resources/img/User1.jpg" alt="">
					</a>
				</div>		
				
				<div class="media-body" id="threadList">
					<c:forEach var="list" items="${threadList}" varStatus="status">	
					<div id="thrbtns">
					<c:if test="${list.isPin == 1 }"> <p><c:out value="${list.pinId}에 의해 고정 됌"></c:out> </p> </c:if>
					<c:if test="${status.first ||( !status.first && vs != list.regId)}">
				 		<h6 class="media-heading mouse_event" data-toggle="modal" data-target="#myModal"><c:out value="${list.regId}"/> <c:out value="(${list.regDt})"/></h6>
				 		<c:set var = "vs" value="${list.regId}"/>
				 	</c:if>				 	
				 	<%-- <div id="regId" style='display:none'>${list.regId}</div> --%>
					<div id="thrKey" style='display:none'>${list.thrKey}</div>
					<div id="isPinState" style='display:none'>${list.isPin}</div>
					<div id ="subBtns">
					 		<button id="saveThread" class="btn btn-default" type="button" ><div data-toggle="tooltip" data-placement="top" title="저장하기"><i class="fa fa-check-square"></i></div></button>
					 		<button id="likeBtns" class="btn btn-default" type="button" ><div data-toggle="tooltip" data-placement="top" title="좋아요"><i class="fa fa-thumbs-up"></i></div></button>
					 		<button id="pinBtns" class="btn btn-default" type="button" ><div data-toggle="tooltip" data-placement="top" title="고정하기"><i class="fa fa-paperclip"></i></div></button>
					 		<button id="childList" class="btn btn-default" type="button" ><div data-toggle="tooltip" data-placement="top" title="댓글보기"><i class="fa fa-list-alt"></i></div></button>
					 		
					</div>	
				 	<p>
				 		<c:out value="${list.contents}"/>
				 	</p>	 
					 				 		
				 					 					 		
				 		<c:if test="${list.modDt != list.regDt }">
				 			<div data-toggle="tooltip" data-placement="right" title="${list.modDt}">(편집됨)</div>
				 		</c:if>
				 		 
				 	
				 	<c:if test="${list.childCnt != 0 }">
				 		<button id="childList" class="btn btn-default" type="button" ><c:out value="${list.childCnt}개의 댓글"/></button></c:if>
				 	</div>					 	
				 	</c:forEach>				    
				</div>				
			</c:if>
		</div>
				<!-- 한 셋트 -->
			 <c:set var="righthalf" value = "width:35%;float:left;display:inline;white-space:nowrap;display:block" />
			 <c:set var="rightfull" value = "width:0%;float:left;display:inline;white-space:nowrap;display:none" />
			 <c:set var="off" value ="display:none"/>
			 <c:set var="on" value ="display:block"/>
			<div id="rightSide" style="${rightfull}">
				<button id="sideclosebtn" style =" float:right"class="btn btn-default" type="button" ><i class="fa fa-times"></i></button>
				<div id="childListFull" style ="${on}">
					<div class="media-body" id="selectOneList" style="width:100%;">					
					</div>
					<div class="media-body" id="selectChildList" style="width:100%;">					
					</div>
					<form method="post" action="${hContext}/thread/doInsertRep.do"> 
                  	<div class="form-group">               
                   	<input type="hidden" name="parentKey" id="parentKey"/>
                   	<input type="hidden" name="thrKey" id="thrKey"/>
                         <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                           <textarea name="contentsRep" id="contentsRep" placeholder="내용을 입력하세요"
                                maxlength="400"></textarea>
                           <input type="button" class="btn btn=primary btn-sm" value="Send" id="insertRepBtn"/>
                       </div>      
                   </div>           
               </form>					
				</div>
				<div id="chInfo"  style ="${off}">
					<ul class="navbar-nav bg-gradient-default sidebar sidebar-dark accordion" id="accordionSidebar">
						<li>
						<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#chInfos"
		                    aria-expanded="true" aria-controls="collapseMem">
		                    <i class="fas fa-fw fa-cog"></i>
		                    <span>채널 정보</span>
		                </a>
		                <div id="chInfos" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
		                    <div class="bg-white py-2 collapse-inner rounded">
		                    	<a class="collapse-item">채널명:<c:out value="${nowCh.chName}" /></a>
		                    	<a class="collapse-item">주제:<c:out value="${nowCh.topic}" /></a>
		   						<a class="collapse-item">채널설명:<c:out value="${nowCh.chDescription}" /></a>
								
		                       	         	
		                    </div>
	                	</div>
	                	</li>
	                	<li>
						<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#chuss"
		                    aria-expanded="true" aria-controls="collapseMem">
		                    <i class="fas fa-fw fa-cog"></i>
		                    <span>채널의 유저들</span>
		                </a>
		                <div id="chuss" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
		                    <div class="bg-white py-2 collapse-inner rounded">
		                    
		                    	<c:forEach var="us" items="${uslist}">
		   						 	<a class="collapse-item">@<c:out value="${us.name}" /></a>
								</c:forEach>
		                       	         	
		                    </div>
	                	</div>
	                	</li>	                	
	                	<li>
	                	<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#chpins"
		                    aria-expanded="true" aria-controls="collapseMem">
		                    <i class="fas fa-fw fa-cog"></i>
		                    <span>고정</span>
		                </a>
		                 <div id="chpins" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
		                    <div class="bg-white py-2 collapse-inner rounded">
		                    
		                    	<c:forEach var="pl" items="${pinList}">
		   						 	<a class="collapse-item"><c:out value="${pl.thrKey}" /></a>
								</c:forEach>
		                       	         	
		                    </div>
	                	</div>
	                	</li>
                	</ul>
				</div>
			</div>
			
				
            </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

	<!-- User Click Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-sm">
	    <div class="modal-content">
	      <div class="modal-body">
	        <img style="padding-bottom: 10px;" class="text-center" alt="" width="270" height="270" src="${hContext}/resources/img/User1Profile.jpg">
	        
	        <h4>김건희</h4>
	        <p>내용1</p>
	        <p>내용2</p>
	        
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary">Message</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- addteam Modal-->
    <div class="modal fade" id="addteamModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">팀원추가</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
					<input type="text" class="form-control" name="email" id="email" value="${vo.email}" placeholder="이메일주소" />
				</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" name="send_btn" id="send_btn" type="button" data-dismiss="modal">보내기</button>
                </div>
            </div>
        </div>
    </div>
	
	<!-- channelcreate Modal-->
    <div class="modal fade" id="channelcreateModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">새채널 생성</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
					<input type="text" class="form-control" name="chName" id="chName" value="${vo.chName}" placeholder="채널이름" />
				</div>
				<div class="modal-body">
					<input type="text" class="form-control" name="chTopic" id="chTopic" value="${vo.chDescription}" placeholder="채널 주제" />
				</div>
				<div class="modal-body">
					<input type="text" class="form-control" name="chDescription" id="chDescription" value="${vo.chDescription}" placeholder="설명" />
				</div>
				
                <div class="modal-footer">
                    <button id="channelCreateBtn" class="btn btn-secondary" type="button" data-dismiss="modal">생성</button>
                </div>
            </div>
        </div>
    </div>
    
    
    
	<!-- profile Modal -->
    <%@ include file="/WEB-INF/views/main/profilemodal.jsp" %>
  <!-- profile Modal -->
  
    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <c:url value="/users/logout.do" var="logOutUrl">
				    </c:url>
				    
			        <c:choose>
			        	<c:when test='${null != sessionScope.usersVO}'>
					  	<li class="nav-item">
					      	<a href="<c:out value='${logOutUrl}' />"   class="nav-link">
					      		<span class="btn btn-secondary">&nbsp;Logout</span>
					      	</a>
					      </li>
				      </c:when>
				      <c:otherwise>
		        		<script>
		        		$(document).ready(function(){
		        			window.location.href="<c:url value='/users/loginView.do' />"
		        	    });
		        		</script>
	        		 </c:otherwise>				      
				     </c:choose> 
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/views/main/remindermain.jsp" %>
     
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript">
	//스크롤시 쓰레드추가 
	$(document).ready(function(){
        $("#divScroll").scroll(function(){
            console.log($("#divScroll").scrollTop());
            if($("#divScroll").scrollTop() == 0){
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
                       //console.log("data="+data);
                       //alert("data:"+data);
                       
                       //json 분리해서 변수
                       var list = JSON.parse(data);
                       if(list.length > 0){
                          var html = "";
                          var vs = "";
                          for(var i=0;i<list.length;i++){
                            html += "<div id=\"thrbtns\">";
                            if(list[i].isPin == 1) html += '<p>' + list[i].pinId + '에 의해 고정 됌' + '</p>'
							if(i==0 || (i!=0 && vs != list[i].regId)){
								 html += '<h6 class=\"media-heading mouse_event\" data-toggle=\"modal\" data-target=\"#myModal\">' + list[i].regId + "("+ list[i].regDt +")" + "</h6>";
								 vs = list[i].regId;
							}                 
							html += "<div id=\"thrKey\" style=\'display:none\'>" + list[i].thrKey + "</div>";
							html += "<div id=\"isPinState\" style=\'display:none\'>" + list[i].isPin + "</div>";
							html += "<div id =\"subBtns\">";
							html += '<button id=\"saveThread\" class=\"btn btn-default\" type=\"button\" ><div data-toggle=\"tooltip\" data-placement=\"top\" title=\"저장하기\"><i class=\"fa fa-check-square\"></i></div></button>';
							html += '<button id=\"likeBtns\" class=\"btn btn-default\" type=\"button\" ><div data-toggle=\"tooltip\" data-placement=\"top\" title=\"좋아요\"><i class=\"fa fa-thumbs-up\"></i></div></button>';
							html += '<button id=\"pinBtns\" class=\"btn btn-default\" type=\"button\" ><div data-toggle=\"tooltip\" data-placement=\"top\" title=\"고정하기\"><i class=\"fa fa-paperclip\"></i></div></button>';
							html += '<button id=\"childList\" class=\"btn btn-default\" type=\"button\" ><div data-toggle=\"tooltip\" data-placement=\"top\" title=\"댓글보기\"><i class=\"fa fa-list-alt\"></i></div></button>';	
							html += "</div>"            
                            html += "<p>" + list[i].contents + "</p>" 
                            if(list[i].regDt != list[i].modDt )
                                html+= "<div data-toggle=\"tooltip\" data-placement=\"right\" title=\""+ list[i].modDt+ "\">(편집됨)</div>";
                            if(list[i].childCnt != 0) 
                                html += "<button id=\'childList\' class=\'btn btn-defualt\' type=\'button\' >" +list[i].childCnt +"개의 댓글</button>";
                            html += "</div>";
                            //console.log(html);
                          }               
                      
                         $("#threadList").prepend(html);
                         //페이즈 사이즈 만큼 내려오게끔 
                         //window.scrollTo(0,200);
                         $("#divScroll").scrollTop($("#divScroll").scrollTop()+100);
                         
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
		});
	$("#insertRepBtn").on("click", function(){
        console.log("insertRepBtn");

        var contentsRep = $("#contentsRep").val();
        console.log("contentsRep:"+contentsRep);
       
       
        
        if(null == contentsRep || contentsRep.trim().length==0){
           $("#contents").focus();
           alert("내용을 입력하세요");
           return;
           }


        
        $.ajax({
           type:"POST",
           url:"${hContext}/thread/doInsertRep.do",
           dataType:"html",
           data:{
                 //"thrKey" : $("thrKey").val(),
                 "parentKey": $("#parentKey").val(),
                 "chLink" : $("#searchWord").val(),
                 "contents" : $("#contentsRep").val(),
                 "regId" : "${sessionScope.usersVO.user_serial}",
                 "regDt" : ""
              },
              success:function(data){

             	 var jsonObj = JSON.parse(data);
              	console.log("regId="+jsonObj.regId);
               console.log("contents="+jsonObj.contentsRep);
               makeChildList($("#parentKey").val());
               $("#contentsRep").val('');
               },
                  error:function(xhr,status,error){
                   alert("error:"+error);
                  },
                  complete:function(data){
                  
               }  
                    
           });

		
        });
	 $("#insertBtn").on("click", function(){
         console.log("insertBtn");

         var contents = $("#contents").val();
         console.log("contents:"+contents);

         if(null == contents || contents.trim().length==0){
            $("#contents").focus();
            alert("내용을 입력하세요");
            return;
            }
         $.ajax({
            type:"POST",
            url:"${hContext}/thread/doInsert.do",
            dataType:"html",
            data:{
                  "thrKey" : "",
                  "parentKey" : "",
                  "chLink" : $("#searchWord").val(),
                  "contents" : $("#contents").val(),
                  "regId" : "${sessionScope.usersVO.user_serial}"
               },
               success:function(data){

               var jsonObj = JSON.parse(data);
               console.log("regId="+jsonObj.regId);
                console.log("contents="+jsonObj.contents);
                $("#contents").val('');
                location.reload();
                },
                   error:function(xhr,status,error){
                    alert("error:"+error);
                   },
                   complete:function(data){
                   
                }  
                     
            });
         
         });
		$(function () {
	    $('[data-toggle="popover"]').popover()
	    })
		
		 // 클릭된 타겟 찾기
		$("body").click(function(event) {
			console.log(event.target);
		}) 

		
		
		// ----------Add Channel----------
		$("#channelCreateBtn").on("click", function() {
			console.log("channelCreateBtn Clicked");
			doChannelCreate();
		})

		function doChannelCreate() {
			$.ajax({
				type : "POST",
				url : "${hContext}/main/addchannel.do",
				dataType : "html",
				async : true,
				data : {
					"chName" : $("#chName").val(),
					"topic" : $("#chTopic").val(),
					"chDescription" : $("#chDescription").val()
				},
				success : function(data) {
					var parseData = JSON.parse(data);
					console.log("success");
					console.log(parseData.chName);
					var html = "";
					html += "<a class='collapse-item'>#";
					html += parseData.chName;
					html += "</a>";

					$("#addChannelin").before(html);
				},
				error : function() {
					console.log("error");
				}
			});
		}
		// ----------Add Channel----------

		//팀원추가시 이메일 보내기 이벤트
		$("#send_btn").on("click", function() {
			var email = $('#email').val();
			if (null == email || email.trim().length == 0) {
				$('#email').focus();
				alert("추가할 팀원의 이메일을 입력해주세요.");
				return;
			}

			$.ajax({
				type : "POST",
				url : "${hContext}/workspace/sendEmail.do",
				dataType : "html",
				data : {
					  "email" :$("#email").val(),
				      "wsLink" : "${sessionScope.usersVO.ws_link }",
				      "wsName" : "11",
				      "userId": "${sessionScope.usersVO.name }",
				      "ws_link": "${sessionScope.usersVO.ws_link }",
				      "email": $("#email").val()
				},
				success : function(data) { //성공
					console.log("data="+data);
					var jsonObj = JSON.parse(data);
				    console.log("regId="+jsonObj.regId);
				    console.log("msgContents="+jsonObj.msgContents);
				    	
				    if(null !=jsonObj && jsonObj.regId=="1"){
				    	console.log("data="+data);
				    	alert(jsonObj.msgContents);			    	
					}
				    else if(null !=jsonObj && jsonObj.regId=="0"){
				    	alert(jsonObj.msgContents);				    	
					}
				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				},
				complete : function(data) {

				}

			});//--ajax

		});
		 /* document.getElementById("profileOpenModal").onclick = function() {
	           document.getElementById("modal").style.display="block";
	       }
	      
	       document.getElementById("modal_close_btn").onclick = function() {
	           document.getElementById("modal").style.display="none";
	       }   

	       document.getElementById("modal_save_close_btn").onclick = function() {
	           document.getElementById("modal").style.display="none";
	       }   */
	       //왼쪽/오른쪽 나누기 on off
	       function halfOnOff(bool){
				if(bool){
					console.log("open");
					$("#divScroll").attr('style',"${lefthalf}");
		    		$("#rightSide").attr('style','${righthalf}');
				}
				else {
					console.log("close");
		    		$("#divScroll").attr('style',"${leftfull}");
		    		$("#rightSide").attr('style','${rightfull}');
				}	
		   }
	       $("#sideclosebtn").click(function(){
	    	   halfOnOff(false);
		   });

		   /* $(document).on('click',"#likeBtns",function(){
			   var likeThrKey = $(this).parent().parent().children("#thrKey").text();
			   var resId = $(this).parent().parent().children("#regId").text();
			   console.log(likeThrKey);  
			   $.ajax({
				   		type : "POST",
				   		url : "${hContext}/reaction/doInsert.do",
					    dataType : "html",
					    data :{
								"thrKey" : tmpThrkey
								"resId" : regId
					    	  },
					    success : function(data){
						    console.log("succes");
						    console.log(data);
						    },error : function(xhr, status, error) {
								alert("error:" + error);
							},
							complete : function(data) {

							}
					    
				   });
			   }); */
		   
	       $(document).on('click',"#thrbtns",function(){
				var tmp = $(this).children("#thrKey").text();
		       console.log(tmp)
	    	   $("#tmpThrKey2").val(tmp);
	   		   $("#tmpChLink2").val($("#searchWord").val());

		       });
		    $(document).on('click',"#channel_btn",function(){
		    	var tmp = $(event.target).children("#channelLk").text(); //.parent().children("#workspaceLk").text();
				console.log("====="+tmp);
				window.location.href="${hContext}/main/index.do?searchWord="+tmp;
			    });
		   $(document).on("click","#chInfoBtn",function(){
			   if( $("#divScroll").attr('style') == "${leftfull}" ){
	    			halfOnOff(true);
		       }
			   else if($("#divScroll").attr('style') == "${lefthalf}" && $("#chInfo").attr('style') == '${on}')halfOnOff(false);
			   $("#chInfo").attr('style','${on}');
			   $("#childListFull").attr('style','${off}');
		   })
		   $(document).on("click","#childList",function(){
			   if( $("#divScroll").attr('style') == "${leftfull}" ){
				   halfOnOff(true);
		    	}
			   $("#chInfo").attr('style','${off}');
			   $("#childListFull").attr('style','${on}');
		    	var tmp = $(this).parent().children("#thrKey").text();
		    	if(tmp == "" ||tmp == null) tmp = $(this).parent().parent().children("#thrKey").text();
	    		console.log(tmp);
	    		$("#parentKey").val(tmp);
	    		makeChildList(tmp);
			});
			//쓰레드 저장 
		   $(document).on("click","#saveThread",function(){
			   var tmpThrKey = $(this).parent().parent().children("#thrKey").text();
			   $.ajax({
					type : "POST",
					url : "${hContext}/save/doSaveThread.do",
					dataType : "html",
					data : {
						  "thrKey" : tmpThrKey,
					      "regId" : "${sessionScope.usersVO.user_serial}",
					      "regDt" : ""				      
					},
					success : function(data) { //성공
						console.log("data="+data);
						var jsonObj = JSON.parse(data);
					    console.log("regId="+jsonObj.regId);
					    console.log("msgContents="+jsonObj.msgContents);
					    	
					    if(null !=jsonObj && jsonObj.regId=="1"){
					    	console.log("data="+data);
					    	alert(jsonObj.msgContents);			    	
						}
					    else if(null !=jsonObj && jsonObj.regId=="0"){
					    	alert(jsonObj.msgContents);				    	
						}
					},
					error : function(xhr, status, error) {
						alert("error:" + error);
					},
					complete : function(data) {

					}

				});//--ajax

			});
			//쓰레드 핀 고정 
		   $(document).on("click","#pinBtns",function(){
			   var thrKey = $(this).parent().parent().children("#thrKey").text();
			   var isPin = $(this).parent().parent().children("#isPinState").text();
			   console.log("pin ajax start");
			    $.ajax({
					type : "POST",
					url : "${hContext}/thread/doPin.do",
					dataType : "html",
					data : {
						  "thrKey" : thrKey,
					      "pinId" : "${sessionScope.usersVO.user_serial}",
					      "isPin" : isPin				      
					},
					success : function(data) { //성공
						console.log("data="+data);
						var jsonObj = JSON.parse(data);
					    console.log("regId="+jsonObj.regId);
					    console.log("msgContents="+jsonObj.msgContents);
					    	
					    if(null !=jsonObj && jsonObj.regId=="1"){
					    	console.log("data="+data);
					    	alert(jsonObj.msgContents);			    	
						}
					    else if(null !=jsonObj && jsonObj.regId=="0"){
					    	alert(jsonObj.msgContents);				    	
						}
					    location.reload();
					},
					error : function(xhr, status, error) {
						alert("error:" + error);
					},
					complete : function(data) {

					}

				});//--ajax 

			});
		  function makeChildList(thrKey){
			  $.ajax({
  	            type:"GET",
  	            url:"${hContext}/thread/doSelectOne.do",
  	            data:{"thrKey" : thrKey
  	                 },
  	            dataType:"html",
  	            success:function(data){ //성공
  	                  //console.log("data="+data);
  	                     console.log(data);

  	                 var list2 = JSON.parse(data);
  	                 $("#selectChildList").empty();
  	                 console.log(list2.childCnt);
  	                 //console.log(thrkey);
  	                 if(list2.childCnt != 0){
  	                     $.ajax({
  	                        type:"GET",
  	                        url:"${hContext}/thread/doSelectChildList.do",
  	                        data:{"thrKey" : thrKey},
  	                        dataType:"html",
  	                        
  	                        success:function(data2){
  	                           console.log(data2);
  	                        
  	                        var childList = JSON.parse(data2);
  								console.log(childList);
  	                        var html ="";
  	                        for(var i=0;i<childList.length;i++){
  	                        	 html += '<h6 class=\"media-heading mouse_event\" data-toggle=\"modal\" data-target=\"#myModal\">' + childList[i].regId + "("+ childList[i].regDt +")" + "</h6>";
  	                        	 html += "<div id=\"thrKey\" style=\'display:none\'>" + childList[i].thrKey + "</div>";            
  	                             html += "<p>" + childList[i].contents + "</p>" 
  	                             if(childList[i].regDt != childList[i].modDt )
  	                                 html+= "<div data-toggle=\"tooltip\" data-placement=\"right\" title=\""+ childList[i].modDt+ "\">(편집됨)</div>";  
  	                            
  	                            console.log(html);  
  	                            
  	                        }      
  	                        $("#selectChildList").html(html);         
  	                        },
  	                        error:function(xhr,status,error){
  	                            alert("error:"+error);
  	                             },
  	                          complete:function(data){
  	                          }
  	                         }); 
  	                         }
  	                   var html = "";
  	                   
  	                   html += '<h6 class=\"media-heading mouse_event\" data-toggle=\"modal\" data-target=\"#myModal\">' + list2.regId + "("+ list2.regDt +")" + "</h6>";
                      	 html += "<div id=\"thrKey\" style=\'display:none\'>" + list2.thrKey + "</div>";            
                           html += "<p>" + list2.contents + "</p>" 
                           if(list2.regDt != list2.modDt )
                               html+= "<div data-toggle=\"tooltip\" data-placement=\"right\" title=\""+ list2.modDt+ "\">(편집됨)</div>";
  	                   console.log(html);

  	                   $("#selectOneList").html(html);     
  	              },
  	            error:function(xhr,status,error){
  	              alert("error:"+error);
  	             },
  	             complete:function(data){
  	             
  	             } 
  	            });
		  }
	      /*  $("#childList").click(function(){
	    		
		   }); */
	       /* $(document).on('click',"#sideclosebtn",function(){
	    		console.log("hi");
	    		$("#divScroll").attr('style',"${leftfull}");
	    		$("#rightSide").attr('style','${rightfull}');
	    		$("")
	    		
		   }); */       
	    	   
	            
	
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