<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div>
<%@ include file="/WEB-INF/views/main/fileupload.jsp" %>
</div>

<script type="text/javascript">

$(document).ready(function(){
	console.log("load Complete");
	Notification.requestPermission().then(function(result) {
		  console.log(result);
		});
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
					
					if(calTime < 60 && calTime > 0 && (value.status == "0")){
						console.log("작동해요~");
						var text = remindTime + " 입니다.";
						var notification = new Notification('알람', { body: text});
						} else {
						console.log("작동 안해요~");
						}
					});
			}
	});
}
</script>
