<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 <!-- Sidebar -->
 <%@ include file="/WEB-INF/views/main/channeldelete.jsp" %>
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas">BIG HIT</i>
                </div>
                <div class="sidebar-brand-text mx-3">ON<sup>0.1</sup></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">
			
			<li class="nav-item active">
                <a class="nav-link" href="${hContext}/workspace/teamUserAdd_view.do">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>WorkSpace Create</span>
                </a>
            </li>
			
			<!-- workspace transform -->
			 <!-- Divider -->
            <hr class="sidebar-divider my-0">
			
			<li class="nav-item active" >
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMes"
                    aria-expanded="true" aria-controls="collapseMes">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>WorkSpace transform</span>
                </a>
                <div id="collapseMes" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded" id="workspacetfBtn">                    
                    	<c:forEach var="WorkSpaceVO" items="${workspaceList}">
   						 	<a class="collapse-item" type="button" id="wstranfrom_btn">
   						 		<c:out value="${WorkSpaceVO.wsName}"/>
   						 		<div id="workspaceLk" style="display: none"><c:out value="${WorkSpaceVO.wsLink}" /></div>   						 		
   						 	</a>
   						 	
						</c:forEach>      	
                    </div>
                </div>
            </li>
            
			<!-- Divider -->
            <hr class="sidebar-divider">
			
            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="index.html">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Thread</span></a>
            </li>
            
            <!-- Divider -->
            <hr class="sidebar-divider my-0">

			<li class="nav-item active">
                <a class="nav-link" href="index.html">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Mentions & reactions</span></a>
            </li>

			<!-- Divider -->
            <hr class="sidebar-divider">

            

            <!-- Nav Item - Pages Collapse Menu -->
            <!-- channels -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMem"
                    aria-expanded="true" aria-controls="collapseMem">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>Channels</span>
                </a>
                <div id="collapseMem" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded" id="channelBtn">
                    
                    	<c:forEach var="ChannelVO" items="${channelList}">
   						 	<a class="collapse-item" type="button" id="channel_btn">
   						 		<c:out value="${ChannelVO.chName}" />
   						 		<div id="channelLk" style="display: none"><c:out value="${ChannelVO.chLink}" /></div>
   						 	</a>
						</c:forEach>
   					
                       	
                        <a id="addChannelin" class="collapse-item" href="#" data-toggle="modal" data-target="#channelcreateModal">
                                + Add Channel
                        </a>
                        
                                      	
                    </div>
                </div>
            </li>

			<!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMessage"
                    aria-expanded="true" aria-controls="collapseMessage">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>Direct Message</span>
                </a>
                <div id="collapseMessage" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                    	<c:forEach var="ChannelVO" items="${channelListDM}">
   						 	<a class="collapse-item">#<c:out value="${ChannelVO.chName}" /></a>
						</c:forEach>
						
                        <a class="collapse-item" href="utilities-other.html" data-toggle="modal" data-target="#addteamModal">
                        	+ Add Teammates
                        </a>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseApps"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>앱</span>
                </a>
                <div id="collapseApps" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="utilities-color.html">GIT HUB</a>
                        <a class="collapse-item" href="utilities-border.html">CISCO</a>
                        <a class="collapse-item" href="utilities-animation.html">Google Drive</a>
                        <a class="collapse-item" href="utilities-other.html">+ Add Apps</a>
                    </div>
                </div>
            </li>
            
                   
        </ul>
        <!-- End of Sidebar -->
        
        <script type="text/javascript">

       	
     	// 클릭된 타겟 찾기
     	
     	
		$("#workspacetfBtn").click(function(event) {
			console.log("=============");
			var tmp = $(event.target).children("#workspaceLk").text(); //.parent().children("#workspaceLk").text();
			console.log("====="+tmp);
			$.ajax({
			    type:"GET",
			    url:"${hContext}/main/wsChanger.do",
			    dataType:"html", 
			    data:{
				    "toWsLink" : tmp
			    },
			    success:function(data){ //성공
				    //console.log("success");
				    //location.reload();	 
				    window.location.href="${hContext}/main/index.do?searchWord="+data;      
			    },
			    error:function(xhr,status,error){
			     alert("error:"+error);
			    },
			    complete:function(data){
			    	//window.location.href="${hContext}/main/index.do?searchWord="+"${sessionScope.channelVO.chLink}"; 
			    }   
			  
			});//--ajax 
			
			
		}); 

		$("#channel_btn").click(function(event) {
			
			/*var tmp = $(event.target).children("#channelLk").text(); //.parent().children("#workspaceLk").text();
			console.log("====="+tmp); */
			window.location.href="${hContext}/main/index.do?searchWord="+"${ChannelVO.chLink}";						
		});


		$("#channelDelBtn").click(function(event) {
			alert("chdel test");
			 $("channel_btn").hide();		
		});

		//마우스우클릭
		$("#channelBtn").ready(function(){
			  //Show contextmenu:
			  $("#channelBtn").contextmenu(function(e){
				
			    //Get window size:
			    var winWidth = $(document).width();
			    var winHeight = $(document).height();
			    //Get pointer position:
			    var posX = e.pageX;
			    var posY = e.pageY;
			    //Get contextmenu size:
			    var menuWidth = $(".contextmenu").width();
			    var menuHeight = $(".contextmenu").height();
			    //Security margin:
			    var secMargin = 10;
			    //Prevent page overflow:
			    if(posX + menuWidth + secMargin >= winWidth
			    && posY + menuHeight + secMargin >= winHeight){
			      //Case 1: right-bottom overflow:
			      posLeft = posX - menuWidth - secMargin + "px";
			      posTop = posY - menuHeight - secMargin + "px";
			    }
			    else if(posX + menuWidth + secMargin >= winWidth){
			      //Case 2: right overflow:
			      posLeft = posX - menuWidth - secMargin + "px";
			      posTop = posY + secMargin + "px";
			    }
			    else if(posY + menuHeight + secMargin >= winHeight){
			      //Case 3: bottom overflow:
			      posLeft = posX + secMargin + "px";
			      posTop = posY - menuHeight - secMargin + "px";
			    }
			    else {
			      //Case 4: default values:
			      posLeft = posX + secMargin + "px";
			      posTop = posY + secMargin + "px";
			    };
			    //Display contextmenu:
			    $(".contextmenu").css({
			      "left": posLeft,
			      "top": posTop
			    }).show();
			    //Prevent browser default contextmenu.
			    return false;
			  });
			  //Hide contextmenu:
			  $(document).click(function(){
			    $(".contextmenu").hide();
			  });
			});
		
        </script>