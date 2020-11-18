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

<body id="page-top">
	<div class="row ">
          <form action="${hContext}/thread/ListView.do" 
               name="searchFrm" class="form-inline  col-lg-12 col-md-12 text-right">
              <input type="hidden" name="pageNum" id="pageNum" value="${searchVO.getPageNum()}" />
              <input type="hidden" name="pageSize"   id="pageSize"  value="${searchVO.getPageSize()}" />
              <input type="hidden" name="searchWord" id="searchWord" class="form-control  input-sm" value="${searchVO.getSearchWord()}"/>
              <input type="hidden" name="thrKey" id="thrKey"/>          
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

                    
        <c:set var="lefthalf" value = "padding-right: 20px;width:65%;float:left;display:inline;height:900px;overflow-y:auto;white-space:nowrap;" />
        <c:set var="leftfull" value = "padding-right: 20px;width:100%;float:left;display:inline;height:900px;overflow-y:auto;white-space:nowrap;" />
		<div id="divScroll" style="${lefthalf}"class="media">
			<c:if test="${!empty threadList}">
				<c:set var = "vs" value="any"/>
				
				<div style="padding-left:10px; padding-right:10px;"class="media-left">
				    <a href="#">
				    <img class="media-object" src="${hContext}/resources/img/User1.jpg" alt="">
					</a>
				</div>		
				
				<div class="media-body" id="threadList">
					<c:forEach var="list" items="${threadList}" varStatus="status">	
					<c:if test="${status.first ||( !status.first && vs != list.regId)}">
				 		<h6 class="media-heading mouse_event" data-toggle="modal" data-target="#myModal"><c:out value="${list.regId}"/> <c:out value="(${list.regDt})"/></h6>
				 		<c:set var = "vs" value="${list.regId}"/>
				 	</c:if>
				 	
				 	<p><c:out value="${list.contents}"/></p>	
				 	</c:forEach>				    
				</div>				
			</c:if>
		</div>
				<!-- 한 셋트 -->
			 <c:set var="righthalf" value = "width:35%;float:left;display:inline;white-space:nowrap" />
			 <c:set var="rightfull" value = "width:0%;float:left;display:inline;white-space:nowrap" />
			<div id="rightSide" style="${righthalf}">
				<h5>hihihihi</h5>

			</div>
			
				
            </div>
            <!-- End of Main Content -->


			









            <!-- Footer -->
            <!-- 입력 폼 -->
            <form method="post" action="${hContext}/thread/doInsert.do">
			   	<input type="hidden" name="thrKey" id="thrKey"/>
			    <div class="sticky-footer bg-white">                
			                <div style="padding-left: 20px; padding-right: 20px;"class="row">
			                	<div class="col-md-12"><textarea class="form-control" name="contents" id="contents" placeholder="내용을 입력하세요"
			                    maxlength="400"></textarea>
			                    </div>  
			                    <input type="button" class="btn btn=primary btn-sm" value="Send" id="insertBtn"/>                  
			                </div>
			    </div>   
			  </form>
            <!-- End of Footer -->
			
			

        </div>
        <!-- End of Content Wrapper -->

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
							if(i==0 || (i!=0 && vs != list[i].regId)){
								 html += '<h6 class=\"media-heading mouse_event\" data-toggle=\"modal\" data-target=\"#myModal\">' + list[i].regId + "("+ list[i].regDt +")" + "</h6>";
								 vs = list[i].regId
							}                             
                            html += "<p>" + list[i].contents + "</p>" 
                            console.log(html);
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
		 document.getElementById("profileOpenModal").onclick = function() {
	           document.getElementById("modal").style.display="block";
	       }
	      
	       document.getElementById("modal_close_btn").onclick = function() {
	           document.getElementById("modal").style.display="none";
	       }   

	       document.getElementById("modal_save_close_btn").onclick = function() {
	           document.getElementById("modal").style.display="none";
	       }  
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