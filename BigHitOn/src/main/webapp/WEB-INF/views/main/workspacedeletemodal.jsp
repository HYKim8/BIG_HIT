<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<Style>
</Style>
<!-- Modal -->
<div class="modal fade" id="workspaceDeleteModal1" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">워크스페이스 삭제</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			
			<div class="modal-body">워크스페이스를 삭제하시겠습니까?</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>                  
				    
				    <button id="workspaceDeleteBtn" class="btn btn-secondary" type="button" data-dismiss="modal">삭제</button>
                </div>
            </div>
		</div>
	</div>

<script type="text/javascript">

//----------Delete Workspace----------
$("#workspaceDeleteBtn").on("click", function() {
	console.log("workspaceDeleteBtn Clicked");
	doWorkSpaceDelete();
})

function doWorkSpaceDelete() {
			$.ajax({
				type : "GET",
				url : "${hContext}/workspace/doDelete.do",
				dataType : "html",
				async : true,
				data : {
					"wsLink" : $("#wsLink").val()
				},
				success : function(data) {
					var jData = JSON.parse(data);
		              if(null != jData && jData.msgId=="1"){
		                alert(jData.msgContents);		                
		              }else{
		                alert(jData.msgId+"|"+jData.msgContents);
		              }

					window.location.href="<c:url value='/users/loginView.do' />"
				},
				error : function() {
					console.log("error");
				}
			});
		}
		// ----------Delete Workspace----------

</script>