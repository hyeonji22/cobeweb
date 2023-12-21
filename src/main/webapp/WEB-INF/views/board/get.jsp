<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<%@include file="../includes/header.jsp" %>
<%@include file="reply.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Read</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board Read
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
	                           <div class="form-group">
	                                <label>Bno</label>
	                                <input class="form-control" name="title" readonly="readonly" value="<c:out value ='${board.bno}'/>">
	                           </div>
	                           <div class="form-group">
	                                <label>Title</label>
	                                <input class="form-control" name="title" readonly="readonly" value="<c:out value ='${board.title}'/>">
	                           </div>
	                           <div class="form-group">
	                                <label>Content</label>
	                                <textarea rows="5" cols="50" name="content" class="form-control"><c:out value ='${board.content}'/></textarea>
	                          </div>
	                           <div class="form-group">
	                                <label>Writer</label>
	                                <input class="form-control" name="writer"  value="<c:out value ='${board.writer}'/>">
	                          </div>
	                          
	                        <form id="actionForm" action="/board/list" method="get">
  								<input type="hidden" name="pageNum" value="${cri.pageNum}">        					 
  								<input type="hidden" name="amount" value="${cri.amount}">        					 
  								<input type="hidden" name="bno" value="${board.bno}">        					 
  								<input type="hidden" name="type" value="${cri.type}">        					 
  								<input type="hidden" name="keyword" value="${cri.keyword}">        					 
          					</form>
          					
	                          
	                          <button type="button" class="btn btn-default listBtn"><a href="/board/list">목록</a></button>
                              <button type="button" class="btn btn-default modBtn"><a href="/board/modify?bno=<c:out value ='${board.bno}'/>">수정</a></button>
                   	
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>                      		
                        
        <!-- 댓글   -->
   <div class='row'>
	    <div class="col-lg-12">
	        <div class="panel panel-default">
	            <div class="panel-heading">
	                <i class="fa fa-comments fa-fw"></i> Reply
	            </div>
	            
	            <div class="panel-body">
	                <ul class="chat">
	                    <li class="left  clearfix" data-rno="12">
	                        <div>
	                            <div class="header">
	                                <strong class="primary-font">user000</strong>
	                                <small class="pull-right text-muted">2018-01-01 13: 13</small>
	                            </div>
	                            <p>good job</p>
	                        </div>
	                    </li>
	                </ul>
	            </div>
	            <div class="panel-footer"></div>
	        </div>
	    </div>
	</div>
                   
        <!-- 댓글 끝  -->                 
                        
                        
                        
                        		
                        
                        		
  
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript">


       	var actionForm = $("#actionForm");
       	
       	//목록으로 갈떄 페이지값도같이 가지고 가짐 
       	$(".listBtn").click(function(e){
       		e.preventDefault();
       		actionForm.find("input[name='bno']").remove(); //목록으로 갈땐 bno값 필요 없음
       		actionForm.submit();
       	});
       	//수정버튼 클릭시
       	$(".modBtn").click(function(e){
       		e.preventDefault();
       		actionForm.attr("action","/board/modify");                  	
       		actionForm.submit();
       	});
       	
 $(document).ready(function(){
	 
    	var bnoValue = '<c:out value = "${board.bno}"/>';
    	var replyUL =$(".chat");
    	
    	showList(1);
    	
    	function showList(page){
    		replyService.getList({bno:bnoValue ,page:1}, function(list){
    			
    			var str="";
    			if(list ==null || list.length ==0){
    				replyUL.html("");
    				return;
    			}
    			for(var i=0, len=list.length ||0; i<len; i++){
    				str+="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
    				str+="<div><div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong>";
    				str+="<small class='pull-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small><div>";
    				str+="<p>"+list[i].reply+"</p></div></li>";
    			}
    			replyUL.html(str);
    		});
    		
    	}
    	
      	replyService.add(
       			{
       				reply:"JS Test"
       				, replyer:"tester"
       				, bno:bnoValue
       				}
       			,
       			
       			function(result){
       				alert("Result:"+result);
       			}
       	);
      	replyService.getList({bno:bnoValue ,page:1}, function(list){
      		for(var i=0, len=list.length||0; i<len; i++){
      			console.log(list[i]);
      		}
      	});
      	
      	//3번댓글삭제 
      	replyService.remove(3,function(count){
      		console.log(count);
      		if(count ==="success"){
      			alert("removed");
      		}
      	},function(err){
      		alert('error....');
    
      	});
      	
      	replyService.update({
      		rno:22
      		,bno:bnoValue
      		,reply:"Modified reply..."
      	},function(result){
      		alert("수정완료");
      	});
      	
      	replyService.get(10,function(data){
      		console.log(data);
      	});
       	
    
       	
 });
 

 

       	
       	
       	
       	
       	
</script>	
                        

<%@include file="../includes/footer.jsp" %>
