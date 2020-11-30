<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<Style>
</Style>
<!-- Modal -->
<div class="modal fade" id="profileOpenModal1" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">내 프로필 편집</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			
			<div class="media">
				<div class="media-body">
					<div style="padding-top: 20px;" class="container">
						<h4 class="media-heading">이름</h4>
						<input class="form-control" readonly type="text" placeholder="이름" value="${sessionScope.usersVO.name}">
						<br>
						<h4 class="media-heading">표시 이름</h4>
						<input id="mo_nickName" class="form-control" type="text" placeholder="표시 이름" value="${sessionScope.usersVO.nickname}">
						<br>
						<h4 class="media-heading">직책</h4>
						<input id="mo_position" class="form-control" type="text" placeholder="직책" value="${sessionScope.usersVO.position}">
						<br>
						<h4 class="media-heading">전화번호</h4>
						<input id="mo_phonenumber" class="form-control" type="text" placeholder="전화번호" value="${sessionScope.usersVO.phone_num}">
						<br>
					</div>
				</div>
				<div class="media-right">
					<img height="308px" width="300px"  style="padding-top: 50px; padding-right: 15px" class="media-object"
						src="${sessionScope.usersVO.profile_img }" alt="" id="profileImgModalView" onError="this.src='${hContext }/resources/img/default.jpg'">
					<div>
						<br>
						<form name="fileUpload" method="post" action="" id="form_data_img" enctype="multipart/form-data">
							<input style="width:285px" name="profileImgChange" id="profileImgChange" class="btn btn-primary btn-sm" type="file" value="사진 변경" accept=".jpg"/>
						</form>
					</div>
 
				</div>

			</div>
			<div class="modal-footer">
				<button type="reset" class="btn btn-default" data-dismiss="modal" id="cancelProfile">취소</button>
				<button id="profileSaveBtn" type="button" data-dismiss="modal" class="btn btn-primary">변경사항 저장</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">



	//---------이미지 미리보기----------
	$(document).ready(function(){
			$("#profileImgChange").on("change", handleImgFileSelect);
		});

	function handleImgFileSelect(e) {
			var files = e.target.files;
			var filesArr = Array.prototype.slice.call(files);

			filesArr.forEach(function(f){
					sel_file = f;

					var reader = new FileReader();
					reader.onload = function(e){
							$("#profileImgModalView").attr("src", e.target.result);
						}
					reader.readAsDataURL(f);
				});
		}
	//---------이미지 미리보기----------
	
	
	//---------프로필 저장----------
	$("#profileSaveBtn").on("click", function(){
			doUpdateProfileImg();
			doUpdateAll();
		});
		
	function doUpdateAll(){
	
	$.ajax({
			type : "POST",
			url : "${hContext}/main/doUpdateUser.do",
			dataType : "html",
			async : true,
			data : {
				"nickname" : $("#mo_nickName").val(),
				"position" : $("#mo_position").val(),
				"phone_num" : $("#mo_phonenumber").val()
			},
			success : function(data) {
				console.log("성공!");
			}
		});

	}

	function doUpdateProfileImg() {
		var formData = new FormData($("#form_data_img")[0]);
		formData.append("file", $("input[name=profileImgChange]")[0].files[0]);
		
		var fileType = document.getElementById("profileImgChange").value.split(".");
		var last_element = fileType[fileType.length - 1];
		
		if(last_element!="jpg"){
			return;
			}
		
		console.log("formData : " + formData);
		console.log("doUpdateProfileImg")
		$.ajax({
			url : '${hContext}/file/doUpdateProfileImg.do',
			data : formData,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false,
			type : 'POST',
			success : function(data) {
				console.log("success");
			},
			error : function(err) {
				console.log("error");
			}
		});
	}
	//---------프로필 저장----------
</script>

			