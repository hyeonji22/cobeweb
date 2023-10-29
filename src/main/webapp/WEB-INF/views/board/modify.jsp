<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<%@include file="../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Modify/Delete</h1>
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
                        <form>
                        <!-- 수정할때도 페이지 번호 필요함 -->
                        <input type="hidden" name="pageNum" value="${cri.pageNum }">
                        <input type="hidden" name="pageNum" value="${cri.amount }">
                        <div class="panel-body">
	                           <div class="form-group">
	                                <label>Bno</label>
	                                <input class="form-control" name="bno" readonly="readonly" value="<c:out value ='${board.bno}'/>">
	                           </div>
	                           <div class="form-group">
	                                <label>Title</label>
	                                <input class="form-control" name="title"  value="<c:out value ='${board.title}'/>">
	                           </div>
	                           <div class="form-group">
	                                <label>Content</label>
	                                <textarea rows="5" cols="50" name="content" class="form-control"><c:out value ='${board.content}'/></textarea>
	                          </div>
	                           <div class="form-group">
	                                <label>Writer</label>
	                                <input class="form-control" name="writer"  value="<c:out value ='${board.writer}'/>">
	                          </div>
	                          
	                          <button  class="btn btn-default" 	data-oper='modify'>수정</button>
                              <button  class="btn btn-danger" 	data-oper='remove'>삭제</button>
                              <button  class="btn btn-info" 	data-oper='list'>목록</button>
                        </form>
                        
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>

<script>
$(document).ready(function(){
	
	//폼태그 변수 생성  --> 폼태그에  액션값 메소드 값 주기위해 
	var formObj = $("form");
	
	//버튼클릭할때 
	$('.btn').click(function(e){
		//이벤트 처리할때는 기본동작막음  ex) form태그안에서 모든 버튼 submit 됨
		e.preventDefault();
		
		var operation = $(this).data("oper");
		console.log(operation);
		
		//목록 ,삭제 ,수정 버튼 클릭시 
		if(operation === 'list'){
			self.location ="/board/list";
		}else if(operation === 'remove'){  
			formObj.attr("action","/board/remove")//폼테그에 액션 추가 
			.attr("method","post"); ///폼테그에 메소드 추가   //추가해주고 개발자 화면에서  action 바뀌는지 먼저 확인해보기
			formObj.submit();   //submit 막아뒀으니깐 직접 실행해주기 
			
		}else if(operation === 'modify'){
			formObj.attr("action","/board/modify")
			.attr("method","post"); 
			formObj.submit();
		}
	})
	
});




</script>


<%@include file="../includes/footer.jsp" %>
