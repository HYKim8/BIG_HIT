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
		.mouse_event:hover {text-decoration: underline;}
	</style>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">
		
        <%@ include file="/WEB-INF/views/main/sideBar.jsp" %>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

               <%@ include file="/WEB-INF/views/main/topBar.jsp" %>

                    

				<!-- 한 셋트 -->
				<div style="padding-right: 20px;"class="media">
				  <div style="padding-left:10px; padding-right:10px;"class="media-left">
				    <a href="#">
				      <img class="media-object" src="${hContext }/resources/img/User1.jpg" alt="">
				    </a>
				  </div>
				  <div class="media-body">
				    <h6 class="media-heading mouse_event" data-toggle="modal" data-target="#myModal">김건희 오후 2:23</h6>
				    <p>SB Admin 2 makes extensive use of Bootstrap 4 utility classes in order to reduce CSS 
				    bloat and poor page performance. Custom CSS classes are used to create custom components and custom utility classes.</p>
				    <p>SB Admin 2 makes extensive use of Bootstrap 4 utility classes in order to reduce CSS bloat and poor page 
				    performance. Custom CSS classes are used to create custom components and custom utility classes.</p>
				    <!-- 같은 아이디면 p태그를 추가하는 식으로. -->
				    
				  </div>
				</div>
				<!-- 한 셋트 -->
				
            </div>
            <!-- End of Main Content -->












            <!-- Footer -->
            <!-- 입력 폼 -->
            <footer class="sticky-footer bg-white">
                <div style="padding-left: 20px; padding-right: 20px;"class="row">
                	<div class="col-md-12"><textarea class="form-control" rows="2"></textarea></div>                    
                </div>
            </footer>
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
    
    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript">
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