<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 파일 업로드 버튼 -->
<a data-toggle="modal" data-target="#fileUploadModal1"
	id="fileUploadModal"> fileUpload </a>
<!-- 얘를 갖다가 쓰면 됩니다. 누르면 모달창 켜짐! -->


<div class="modal fade" id="fileUploadModal1" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">파일 업로드</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">

				<form name="fileUpload" method="post" action="file_test.do" id="form_data_file_upload" enctype="multipart/form-data">
					<input type="file" name="form_data_file_upload_input" id="form_data_file_upload_input" accept=".jpg, .zip, .7z, .pdf .docx" /> 
				</form>


			</div>
			<div class="modal-footer">
				<button type="reset" class="btn btn-default" data-dismiss="modal">취소</button>
				<input data-dismiss="modal" class="btn btn-primary" type="button" value="업로드" id="uploadFileMain2" />
			</div>
		</div>
	</div>
</div>

<input type="text" id="tmpThrKey2" placeholder="tmpThrKey2" value="">
<input type="text" id="tmpChLink2" placeholder="tmpChLink2" value="">

<script type="text/javascript">

//-----------파일 업로드--------------
	$("#uploadFileMain2").on("click", function(){
			console.log("uploadFileMain clicked");
			//uploadFileMain();
			console.log($("input[name=form_data_file_upload_input]")[0].files[0]);
		});
	
	function uploadFileMain(paramThrKey) {
		var formData = new FormData($("#form_data_file_upload")[0]);
		formData.append("file", $("input[name=form_data_file_upload_input]")[0].files[0]);
		var fileType = document.getElementById("form_data_file_upload_input").value.split(".");
		var last_element = fileType[fileType.length - 1];

		//----------추가해야할 값--------------
		var thrKey = paramThrKey;
		var chLink = $("#searchWord").val();
		//----------추가해야할 값--------------
		
		formData.append("fileType", last_element);
		formData.append("thrKey", thrKey);
		formData.append("chLink", chLink);
		console.log("formData : " + formData);
		console.log("doUploadFile")
		$.ajax({
			url : '${hContext}/main/doUpload.do',
			data : formData,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false,
			type : 'POST',
			success : function(data) {
				console.log("uploadFile Success!");
			},
			error : function(err) {
				console.log("error");
			}
		});

	}

	//-----------파일 업로드--------------
</script>