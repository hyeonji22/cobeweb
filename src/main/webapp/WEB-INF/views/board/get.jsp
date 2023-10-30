<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<%@include file="../includes/header.jsp" %>

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
                        		
                        	<script>
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
                        	
                        	
                        	</script>	
                        
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>



<%@include file="../includes/footer.jsp" %>
