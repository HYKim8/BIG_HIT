<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div>

	<input type="text" id="tmpThrKey1" value="" placeholder="thrKey">
	<input type="text" id="tmpChLink1" value="" placeholder="chLink">
	<input type="text" id="tmpfileId1" value="" placeholder="fileId">

	<input type="button" id="ListingFileDownloadThrKey" value="thrKey">
	<input type="button" id="ListingFileDownloadChLink" value="chLink">
	<input type="button" id="getFileDownloadURL" value="get url">
</div>



<script type="text/javascript">

//-----FileVO-----
//fileName
//thrKey
//fileUrl
//regId
//regDt
//fileId
//chLink
//-----FileVO-----

$("#ListingFileDownloadThrKey").on("click", function(){
	console.log("ListingFileDownloadThrKey clicked");
	ListingFileDownloadThrKey();
})

$("#ListingFileDownloadChLink").on("click", function(){
	console.log("ListingFileDownloadChLink clicked");
	ListingFileDownloadChLink();
})

$("#getFileDownloadURL").on("click", function(){
	console.log("getFileDownloadURL clicked");
	getFileDownloadURL();
})


function ListingFileDownloadThrKey(){
	$.ajax({
		type : "POST",
		url : "${hContext}/main/fileDownloadListThrKey.do",
		dataType : "html",
		async : true,
		data : {
			"thrKey" : $("#tmpThrKey1").val()
		},
		success : function(data) {
			var parseData = JSON.parse(data);
			console.log("ListingFileDownloadThrKey success");
			$.each(parseData, function(i, value) {
				  console.log(value);
			  });
		}
	});
}

function ListingFileDownloadChLink(){
	$.ajax({
		type : "POST",
		url : "${hContext}/main/fileDownloadListChLink.do",
		dataType : "html",
		async : true,
		data : {
			"chLink" : $("#tmpChLink1").val()
		},
		success : function(data) {
			var parseData = JSON.parse(data);
			console.log("ListingFileDownloadChLink success");
			$.each(parseData, function(i, value) {
				  console.log(value);
			  });
		}
	});
	
}

function getFileDownloadURL(){
	$.ajax({
		type:'POST',
		url:'${hContext}/file/doDownload.do',
		dataType:"html",
        async: true,
        data:{
			"fileId":$("#tmpfileId1").val()
            },
        success:function(data){
	        		var parseData = JSON.parse(data);
					console.log("get download url success!");
					console.log("parseData : " + parseData);

										
	        	}  
		});
}
</script>