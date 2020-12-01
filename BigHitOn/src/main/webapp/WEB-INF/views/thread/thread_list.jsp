<%@page import="com.bighit.on.thread.ThreadVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="hContext" value="${pageContext.request.contextPath }" ></c:set>     
 

    
<!DOCTYPE html>
<html lang="ko">
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
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
   <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <!-- Custom styles for this template-->
    <link href="${hContext }/resources/assets/css/sb-admin-2.min.css" rel="stylesheet">

   <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   
   <style type="text/css">
         table tr button { opacity:0; float:right }
         table tr:hover button { opacity:1 }
       </style>
   
<title>thread_list</title>

</head>
<body>
<%@ include file="/WEB-INF/views/main/remindermain.jsp" %>
<%@ include file="/WEB-INF/views/main/topBar.jsp" %>
<%@ include file="/WEB-INF/views/main/sideBar.jsp" %>
<%@ include file="/WEB-INF/views/main/profilemodal.jsp" %>

 <div class="row ">
          <form action="${hContext}/thread/ListView.do" 
               name="searchFrm" class="form-inline  col-lg-12 col-md-12 text-right">
              <input type="text" name="pageNum" id="pageNum" value="${searchVO.getPageNum()}" />
              <input type="text" name="pageSize"   id="pageSize"  value="${searchVO.getPageSize()}" />
              <input type="text" name="searchWord" id="searchWord" class="form-control  input-sm" value="${searchVO.getSearchWord()}"/>
              <input type="hidden" name="thrKey" id="thrKey"/>          
          </form>
</div>

   <div id="divScroll" style="width:65%;float:left;display:inline;height:900px;overflow-y:auto;white-space:nowrap;">
   <table id="threadListTable"style="width:100%";display:inline;>
      <!--
      <colgroup>
         <col style="width:5%;" />
         <col style="width:auto;" />
         <col style="width:15%;" />
         <col style="width:10%;" />
         <col style="width:10%;" />
      </colgroup>
      -->
      <thead>
      <tr>
         <th>쓰레드키</th>
         <th>내용</th>
         <th>고정유무</th>
         <th>작성자</th>
         <th>작성일</th>
         <th>수정일</th>
         <th>댓글갯수</th>
         <th></th>
      </tr>
      </thead>
      <tbody id="threadList">
         <c:choose>
            <c:when test="${!empty threadList}">
               <c:forEach var="list" items="${threadList}">
                  <tr>
                     <td><c:out value="${list.thrKey}"/></td>
                     <td><c:out value="${list.contents}"/></td>
                     <td>
                        <c:if test="${list.isPin == 1}">
                           <c:out value="${list.pinId}님이 고정함"/>
                        </c:if>
                     </td>
                     <td><c:out value="${list.regId}"/></td>
                     <td><c:out value="${list.regDt}"/></td>
                     <td data-toggle="tooltip" data-placement="right" title="${list.modDt}">
                        <c:if test="${list.modDt != list.regDt }">
                           <c:out value="편집됨"/>                           
                        </c:if>
                     </td>
                     <td>
                        <c:if test="${list.childCnt != 0}">
                           <c:out value="${list.childCnt}"/>
                        </c:if>
                     </td>
                     <td>
                     <button type ="button" class="selectOneBtn">조회</button> 
                        <a data-toggle="modal" data-target="#fileUploadModal1"
						id="fileUploadModal"><button type ="button"> fileUpload </button></a> 
                     </td>
                     
                     
                     
                  </tr>
               </c:forEach>
            </c:when>
         </c:choose>
      </tbody>      
   </table>
   
   <form method="post" action="${hContext}/thread/doInsert.do">
   <div class="form-group">
   <input type="hidden" name="thrKey" id="thrKey"/>
   <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
      <input type="text" class="form-control" name="contents" id="contents" placeholder="내용을 입력하세요"
                    maxlength="400"/>
      <input type="button" class="btn btn=primary btn-sm" value="Send" id="insertBtn"/>
      </div>
   </div>
   </form>
   </div>
   
    <div style="width:35%;float:left;display:inline;white-space:nowrap">
   <table>
      <thead>
         <tr>
            <th>쓰레드키</th>
            <th>내용</th>
            <th>고정유무</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>수정일</th>
            <th>댓글갯수</th>
         </tr>
     </thead>
     
     
    
     <tbody id="selectOneList" style="width:100%;">
                  <tr>
                     <td ><c:out value="${list2.thrKey}"/></td>
                     <td><c:out value="${list2.contents}"/></td>
                     <td>
                        <c:if test="${list2.isPin == 1}">
                           <c:out value="${list2.pinId}님이 고정함"/>
                        </c:if>
                     </td>
                     <td><c:out value="${list2.regId}"/></td>
                     <td><c:out value="${list2.regDt}"/></td>
                     <td data-toggle="tooltip" data-placement="right" title="${list2.modDt}">
                        <c:if test="${list2.modDt != list2.regDt }">
                           <c:out value="편집됨"/>                           
                        </c:if>
                     </td>
                     <td>
                        <c:if test="${list2.childCnt != 0}">
                           <c:out value="${list2.childCnt}"/>
                        </c:if>
                     </td>                     
                  </tr>              
      </tbody>                 
   </table>
   <div>
   
   <table>
      <thead>
         <tr>
            <th>스레드 키</th>
            <th>채널링크</th>
            <th>내용</th>
            <th>등록자</th>
            <th>등록일</th>
            <th>부모키</th>
            <th>
         </tr>
      </thead>
      <tbody id="selectChildList" style="width:100%;">
      
                  <tr>             
                  </tr>
           
      </tbody>
   </table>
                  <form method="post" action="${hContext}/thread/doInsertRep.do"> 
                  <div class="form-group">               
                   <input type="hidden" name="parentKey" id="parentKey"/>
                   <input type="hidden" name="thrKey" id="thrKey"/>
                         <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                           <input type="text" class="form-control" name="contentsRep" id="contentsRep" placeholder="내용을 입력하세요"
                                maxlength="400"/>
                           <input type="button" class="btn btn=primary btn-sm" value="Send" id="insertRepBtn"/>
                       </div>      
                   </div>           
               </form>
   </div>
   </div>
   
   
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${hContext}/resources/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script type="text/javascript">
      $(document).ready(function(){
          $("#divScroll").scroll(function(){
              console.log($("#divScroll").scrollTop());
              if($("#divScroll").scrollTop() == 0){
                 $("#pageNum").val( parseInt($("#pageNum").val()) +1);
                  console.log($("#pageNum").val());
                  console.log($("#pageSize").val());
                  console.log($("#searchWord").val());
                  $.ajax({
                      type:"GET",
                      url:"${hContext}/thread/moreList.do",
                      dataType:"html", 
                      data:{
                           "pageNum" :$("#pageNum").val(),
                           "pageSize":$("#pageSize").val(),
                           "searchWord":$("#searchWord").val()                     
                      },  
                      success:function(data){ //성공
                         console.log("data="+data);
                         //alert("data:"+data);
                         
                         //json 분리해서 변수
                         var list = JSON.parse(data);
                         if(list.length > 0){
                            var html = "";
                            for(var i=0;i<list.length;i++){
                              html += '<tr>';
                              html += '<td>'+ list[i].thrKey+'</td>';
                              html += '<td>'+ list[i].contents+'</td>';
                              html += '<td>';
                              if(list[i].isPin==1) html += list[i].pinId + "님이 고정함";
                              html += '</td>';
                              html += '<td>'+ list[i].regId+'</td>';
                              html += '<td>'+ list[i].regDt+'</td>';
                              html += '<td data-toggle=\"tooltip\" data-placement="\right\" title=\"' +list[i].modDt +'\">';
                              if(list[i].regDt != list[i].modDt ) {
                                 html += '편집됨';                           
                              }
                              html +='</td>';
                              html += '<td>';
                              if(list[i].childCnt != 0 )html += list[i].childCnt+'</td>';
							  html += '<td>' + '<button type ="button" class="selectOneBtn">'+"조회"+'</button>'+'</td>'; 	
                              html += '</tr>';
                            }                     
                        
                           $("#threadList").prepend(html);
                           //페이즈 사이즈 만큼 내려오게끔 
                           //window.scrollTo(0,200);
                           $("#divScroll").scrollTop($("#divScroll").scrollTop()+100);
                           
                         }
                      },
                      error:function(xhr,status,error){
                       alert("error:"+error);
                      },
                      complete:function(data){
                      
                      }   
                  
                  });//--ajax
                  }
          });
          
      });
      $("#insertRepBtn").on("click", function(){
          console.log("insertRepBtn");

          var contentsRep = $("#contentsRep").val();
          console.log("contentsRep:"+contentsRep);
         
         
          
          if(null == contentsRep || contentsRep.trim().length==0){
             $("#contents").focus();
             alert("내용을 입력하세요");
             return;
             }


          
          $.ajax({
             type:"POST",
             url:"${hContext}/thread/doInsertRep.do",
             dataType:"html",
             data:{
                   //"thrKey" : $("thrKey").val(),
                   "parentKey": $("#parentKey").val(),
                   "chLink" : $("#searchWord").val(),
                   "contents" : $("#contentsRep").val(),
                   "regId" : $("#regId").val(),
                   "regDt" : $("#regDt").val()
                },
                success:function(data){

                var jsonObj = JSON.parse(data);
                console.log("regId="+jsonObj.regId);
                 console.log("contents="+jsonObj.contentsRep);

                 },
                    error:function(xhr,status,error){
                     alert("error:"+error);
                    },
                    complete:function(data){
                    
                 }  
                      
             });
          
          });
    
      $("#insertBtn").on("click", function(){
         console.log("insertBtn");

         var contents = $("#contents").val();
         console.log("contents:"+contents);

         if(null == contents || contents.trim().length==0){
            $("#contents").focus();
            alert("내용을 입력하세요");
            return;
            }
         $.ajax({
            type:"POST",
            url:"${hContext}/thread/doInsert.do",
            dataType:"html",
            data:{
                  "thrKey" : $("thrKey").val(),
                  "chLink" : $("#chLink").val(),
                  "contents" : $("#contents").val(),
                  "regId" : $("#regId").val(),
                  "regDt" : $("#regDt").val()
               },
               success:function(data){

               var jsonObj = JSON.parse(data);
               console.log("regId="+jsonObj.regId);
                console.log("contents="+jsonObj.contents);

                },
                   error:function(xhr,status,error){
                    alert("error:"+error);
                   },
                   complete:function(data){
                   
                }  
                     
            });
         
         });
    
       
       $(document).on("hover","#modDt",function(){
         
           });


        $('.selectOneBtn').bind('click',function() {
           //console.log("#boardListTable>tbody");
           var trs = $(this).closest('tr');
           console.log(trs);
           var tds = trs.children();
           var thrKey = tds.eq(0).text();
           
           //console.log("thrkey:"+thrKey);
            var parentKey = $("#parentKey").val(thrKey);
        
        $.ajax({
            type:"GET",
            url:"${hContext}/thread/doSelectOne.do",
            data:{"thrKey" : thrKey
                 },
            dataType:"html",
            success:function(data){ //성공
                  //console.log("data="+data);
                     console.log(data);

                 var list2 = JSON.parse(data);
                 $("#selectChildList").empty();
                 console.log(list2.childCnt);
                 //console.log(thrkey);
                 if(list2.childCnt != 0){
                     console.log("왔니?");
                     $.ajax({
                        type:"GET",
                        url:"${hContext}/thread/doSelectChildList.do",
                        data:{"thrKey" : thrKey},
                        dataType:"html",
                        
                        success:function(data2){
                           console.log(data2);
                        
                        var childList = JSON.parse(data2);
							console.log(childList);
                        var html ="";
                        for(var i=0;i<childList.length;i++){
                        	html += '<tr name="chilereplist">';
                       		html += '<td>'+childList[i].thrKey+'</td>';
                       	 	html += '<td>'+childList[i].chLink+'</td>';
                            html += '<td>'+childList[i].contents+'</td>';
                            html += '<td>'+childList[i].regId+'</td>';
                            html += '<td>'+childList[i].regDt+'</td>';
                            html += '<td>'+childList[i].parentKey+'</td>';   
                            html += '</tr>';
                            console.log(html);  
                            
                        }      
                        $("#selectChildList").html(html);         
                        },
                        error:function(xhr,status,error){
                            alert("error:"+error);
                             },
                          complete:function(data){
                          }
                         }); 
                         }
                   var html = "";
                   
                   html += '<tr>';
                   html += '<td>'+list2.thrKey+'</td>';
                   html += '<td>'+list2.contents+'</td>';
                   html += '<td>'+list2.isPin+'</td>';
                   html += '<td>'+list2.regId+'</td>';
                   html += '<td>'+list2.regDt+'</td>';
                   html += '<td>'+list2.childCnt+'</td>';
                   html += '</tr>';
                   console.log(html);

                   $("#selectOneList").html(html);     
              },
            error:function(xhr,status,error){
              alert("error:"+error);
             },
             complete:function(data){
             
             } 
            });
        });
    </script>
    <!-- Bootstrap core JavaScript-->
    <script src="${hContext }/resources/assets/vendor/jquery/jquery.min.js"></script>
    <script src="${hContext }/resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${hContext }/resources/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${hContext }/resources/assets/js/sb-admin-2.min.js"></script>
</body>
</html>