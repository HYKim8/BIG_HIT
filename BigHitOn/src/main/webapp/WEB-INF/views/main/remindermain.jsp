<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/views/main/fileupload.jsp" %>

<input type="hidden" value="1" id="tmpThrKey">

<a data-toggle="modal" data-target="#reminderInsertBtn1"
	id="reminderInsertBtn"> reminder insert </a>

<div class="modal fade" id="reminderInsertBtn1" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">리마인더 등록</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div style="text-align: center;" class="modal-body">

				<form class="form-inline">
					<div style="border:0; outline:0;padding-left:40px ;padding-right: 50px">
						<input type="date" class="form-control" id="currentDate">
					</div>
					<div>
						<input type="time" class="form-control" id="currentTime">
					</div>
				</form>	    
    



			</div>
			<div class="modal-footer">
				<button type="reset" class="btn btn-default" data-dismiss="modal">취소</button>
				<input data-dismiss="modal" class="btn btn-primary" type="button" value="등록" id="insertReminderBtn" />
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
//----------------알람 등록------------------
$("#reminderInsertBtn").on("click", function(){
	var today = new Date();

	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var date = today.getDate();
	var hours = today.getHours();
	var minutes = today.getMinutes();
	var hoursnumber = Number(hours);


	if(Number(date)<10){
			date = "0" + date;
		}
	
	if(Number(minutes)<10){
			minutes = "0" + minutes;
		}
	if(hoursnumber<10){
			hours = "0" + hours;
		}
	
	var dateInput = year + '-' + month + '-' + date;
	var timeInput = hours + ':' + minutes;

	console.log("timeInput : "+timeInput);
	
	document.getElementById("currentDate").value = dateInput;
	document.getElementById("currentTime").value = timeInput;

})

$("#insertReminderBtn").on("click", function(){
	doInsertReminder();
	})
	
function doInsertReminder(){
	
	$.ajax({
		type : "POST",
		url : "${hContext}/reminder/doInsertFinal.do",
		dataType : "html",
		async : true,
		data : {
			"thrKey" : $("#tmpThrKey").val(),
			"date" : $("#currentDate").val(),
			"time" : $("#currentTime").val()
		},
		success : function(data) {
			console.log("성공!");
		}
	});

	
}
//----------------알람 등록------------------




//----------------알람 울리기---------------
$(document).ready(function(){
	console.log("load Complete");
	Notification.requestPermission().then(function(result) {
		  console.log(result);
		});
	doChkAlarm();
	setTimeout(time1, 60000);
});



function time1(){
	console.log("doChkAlarm");
	setTimeout(time2, 60000);
	doChkAlarm();
}

function time2(){
	console.log("doChkAlarm");
	setTimeout(time1, 60000);
	doChkAlarm();
}

function doChkAlarm(){
	$.ajax({
		type:"POST",
        url:"${hContext}/reminder/doChkAlarm.do",
        dataType:"html",
        async: true,
		data:{},
		success: function(data){
			var parseData = JSON.parse(data);

				console.log("parseData" + parseData);
				$.each(parseData, function(i, value){
					let today = new Date();
					let remindTime = new Date(value.remindTime);
					let calTime = Math.floor((today-remindTime)/1000);
					console.log("calTime : " + calTime);
					
					if(calTime < 60 && calTime > 0){
						console.log("작동해요~");
						var text = value.remindTime + " 입니다.";
						var notification = new Notification(value.regId+"님의 리마인더", { body: text});
						} else {
						console.log("작동 안해요~");
						}
					});
			}
	});
}
//----------------알람 울리기---------------
</script>
