 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <!-- Sidebar -->
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
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMem"
                    aria-expanded="true" aria-controls="collapseMem">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>Channels</span>
                </a>
                <div id="collapseMem" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                    
                    	<c:forEach var="ChannelVO" items="${channelList}">
   						 	<a class="collapse-item">#<c:out value="${ChannelVO.chName}" /></a>
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
                    	<c:forEach var="UsersVO" items="${usersList}">
   						 	<a class="collapse-item">#<c:out value="${UsersVO.nickname}" /></a>
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