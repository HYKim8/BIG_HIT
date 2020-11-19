<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                                data-toggle="modal" data-target="#peopleinvite" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-users fa-fw"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-counter">+</span>
                            </a>
                            <!-- Dropdown - Alerts -->
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.usersVO.name}</span>
                                <img id="topBarThumb" class="img-profile rounded-circle"
                                    src="${sessionScope.usersVO.thumb}" onError="this.src='${hContext }/resources/img/default.jpg'"> <!-- 썸네일 주소 -->
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" data-toggle="modal" data-target="#profileOpenModal1" id="profileOpenModal">
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
                
                <div class="modal fade" id="peopleinvite" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="myModalLabel">채널에 초대</h4>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
				
								<div class="table-responsive">
									<!-- table -->
									<table id="invite_table" class="table table-striped table-bordered table-hover table-condensed">
										<thead class="bg-primary">
											<th class="text-gray-100 text-center">닉네임</th>
											<th class="text-gray-100 text-center">직책</th>
										</thead>
										<tbody id='inviteBtnInTbody'></tbody>
									</table>
								
								</div>
				
				
							</div>
							<div class="modal-footer">
								<button type="reset" class="btn btn-default" data-dismiss="modal">취소</button>
								<input data-dismiss="modal" class="btn btn-primary" type="button" value="업로드" id="uploadFileMain" />
							</div>
						</div>
					</div>
				</div>
				
				
				
				
				
				
				
				
				
				<!-- 넣어줘야할 값 : 현재 채널 링크-->
                <input type="hidden" value="C8E31O1N2WY" id="testChLink">
                <!-- 넣어줘야할 값 : 현재 채널 링크-->
                
                
                
                
                
                
                
                
                
                
<script type="text/javascript">


$("#invite_table tbody").on("click", "tr", function(){
	var tmp = $(this).find("td:eq(2)").text();
	var tmp2 = $(this).find("td");
	
	console.log("tmp2 : "+tmp2);
	var alertman = $(this).find("td:eq(0)").text();
	console.log(tmp);
	if(confirm(alertman+"님을 초대하시겠습니까?")) {
		console.log("초대함");
	} else {
		console.log("초대안함");
		return;
		}
	$.ajax({
		type : "POST",
		url : "${hContext}/main/doInvitePeople.do",
		dataType : "html",
		async : true,
		data : {
			"chLink" : $("#testChLink").val(),
			"userSerial" : tmp
		},
		success : function(data) {
			console.log("초대 성공!");
			tmp2.remove();
		}
	});
	
});

$("#alertsDropdown").on("click", function(){
	console.log("alertsDropdown clicked");
	// 
	$.ajax({
		type:"POST",
           url:"${hContext}/main/doInviteP.do",
           dataType:"html",
           async: true,
           data:{
           		"chLink":$("#testChLink").val()
           },
           success: function(data){
				var parseData = JSON.parse(data);
				$("#invite_table>tbody").empty();
				var html = "";
				$.each(parseData, function(i, value) {
					  html += "<tr>";
					  html += "<td class='text-center'>"+value.nickname+"</td>";
					  html += "<td class='text-left'>"+value.position+"</td>";
					  html += "<td style='display:none'>"+value.user_serial+"</td>";
					  html += "</tr>";
				  });
				
				$("#invite_table>tbody").append(html);
               }
		});
});


</script>