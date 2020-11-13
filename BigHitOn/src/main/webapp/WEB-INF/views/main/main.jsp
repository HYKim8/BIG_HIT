<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="hContext" value="${pageContext.request.contextPath }" ></c:set> 
<!DOCTYPE html>
<html lang="en">

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

    <!-- Custom styles for this template-->
    <link href="${hContext }/resources/assets/css/sb-admin-2.min.css" rel="stylesheet">
	<style>
		.my_img{
			margin-left: 20px;
			margin-right: 5px;
			margin-bottom: 20px;
		}
		
		.my_thread{
			line-height: 1.1;
			padding-top: 0px;
		}
		
		.name_p{
			padding-top: 5px;
			line-height: 0.8;
		}
		
		.thread_box{
			padding-left: 20px;
		}
	</style>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

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
                        <a class="collapse-item" href="buttons.html">#Channel 1</a>
                        <a class="collapse-item" href="cards.html">#Channel 2</a>
                        <a class="collapse-item" href="cards.html">#Channel 3</a>
                        <a class="collapse-item" href="">+ Add Channel</a>
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
                        <a class="collapse-item" href="utilities-color.html">User 1</a>
                        <a class="collapse-item" href="utilities-border.html">User 2</a>
                        <a class="collapse-item" href="utilities-animation.html">User 3</a>
                        <a class="collapse-item" href="utilities-other.html">+ Add Teammates</a>
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

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Search -->
                    <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - Alerts -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-danger badge-counter">3+</span>
                            </a>
                            <!-- Dropdown - Alerts -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    Alerts Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-primary">
                                            <i class="fas fa-file-alt text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 12, 2019</div>
                                        <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-success">
                                            <i class="fas fa-donate text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 7, 2019</div>
                                        $290.29 has been deposited into your account!
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-warning">
                                            <i class="fas fa-exclamation-triangle text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 2, 2019</div>
                                        Spending Alert: We've noticed unusually high spending for your account.
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">User Name</span>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg"> <!-- 썸네일 주소 -->
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->


				<div class="row thread_box">
					<div class="my_img">
						<img alt="" src="${hContext }/resources/img/User1.jpg"> 
					</div>
					<div class="col-md-10 my_thread">
						<p class="name_p">김건희 오전 11:53</p>
						<p>안녕하세요.</p>
						<p>반가워요.</p>
						<p>하이요.</p>
					</div>
				</div>
				
				<div class="row thread_box">
					<div class="my_img">
						<img alt="" src="${hContext }/resources/img/User2.jpg"> 
					</div>
					<div class="col-md-10 my_thread">
						<p class="name_p">김재훈 오전 12:00</p>
						<p>오늘 잘 지내셨나요?</p>
						<p>안녕하세요.</p>
						<p>수업 언제 끝나나요?</p>
						<p>SB Admin 2 makes extensive use of Bootstrap 4 utility classes in order to reduce
                                        CSS bloat and poor page performance. Custom CSS classes are used to create
                                        custom components and custom utility classes.</p>
					</div>
				</div>
				<div class="row thread_box">
					<div class="my_img">
						<img alt="" src="${hContext }/resources/img/User1.jpg"> 
					</div>
					<div class="col-md-10 my_thread">
						<p class="name_p">김건희 오전 11:53</p>
						<p>안녕하세요.</p>
						<p>반가워요.</p>
						<p>하이요.</p>
					</div>
				</div>
				
				<div class="row thread_box">
					<div class="my_img">
						<img alt="" src="${hContext }/resources/img/User2.jpg"> 
					</div>
					<div class="col-md-10 my_thread">
						<p class="name_p">김재훈 오전 12:00</p>
						<p>오늘 잘 지내셨나요?</p>
						<p>안녕하세요.</p>
						<p>수업 언제 끝나나요?</p>
						<p>SB Admin 2 makes extensive use of Bootstrap 4 utility classes in order to reduce
                                        CSS bloat and poor page performance. Custom CSS classes are used to create
                                        custom components and custom utility classes.</p>
					</div>
				</div>
				<div class="row thread_box">
					<div class="my_img">
						<img alt="" src="${hContext }/resources/img/User1.jpg"> 
					</div>
					<div class="col-md-10 my_thread">
						<p class="name_p">김건희 오전 11:53</p>
						<p>안녕하세요.</p>
						<p>반가워요.</p>
						<p>하이요.</p>
					</div>
				</div>
				
				<div class="row thread_box">
					<div class="my_img">
						<img alt="" src="${hContext }/resources/img/User2.jpg"> 
					</div>
					<div class="col-md-10 my_thread">
						<p class="name_p">김재훈 오전 12:00</p>
						<p>오늘 잘 지내셨나요?</p>
						<p>안녕하세요.</p>
						<p>수업 언제 끝나나요?</p>
						<p>SB Admin 2 makes extensive use of Bootstrap 4 utility classes in order to reduce
                                        CSS bloat and poor page performance. Custom CSS classes are used to create
                                        custom components and custom utility classes.</p>
					</div>
				</div>

				
            </div>
            <!-- End of Main Content -->












            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container row">
                    <textarea class="form-control" style="width:90%;" rows="2"></textarea>
                    <input class="btn btn-primary" type="button" value="전송">
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
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="${hContext }/resources/assets/vendor/jquery/jquery.min.js"></script>
    <script src="${hContext }/resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${hContext }/resources/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${hContext }/resources/assets/js/sb-admin-2.min.js"></script>


</body>

</html>