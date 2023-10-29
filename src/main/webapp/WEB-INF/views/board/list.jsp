<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<%@include file="../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            DataTables Advanced Tables
                              <button type="button" id="regBtn" class="btn btn-xs pull-right">Register New Board
                            </button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" >
                                <thead>
                                    <tr>
                                        <th>BNO</th>
                                        <th>Title</th>
                                        <th>Writer</th>
                                        <th>Regdate</th>
                                        <th>UpdateDate</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="board">
                                    <tr class="odd gradeX">
                                        <td>${board.bno}</td>
                                        <td><a class="move" href="<c:out value ='${board.bno}'/>"/>${board.title}</td>
                                        <td>${board.writer}</td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}"/></td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}"/></td>
                                    </tr>
                                </c:forEach>
                                 
                                </tbody>
                            </table>
          
          					<h3>${pageMaker }</h3>
          					<div class="pull-right">
          					<ul class="pagination">
          					 <c:if test="${pageMaker.prev}">
          					    <li class="page-item ">
							      <a class="page-link" href="${pageMaker.startPage -1}" tabindex="-1">Previous</a>
							    </li>
          					</c:if>	
          						<c:forEach begin="${pageMaker.startPage }"
          									end="${pageMaker.endPage }" var="num">
          							 <li class="page-item ${pageMaker.cri.pageNum == num?"active":""}"><a class="page-link" href="${num}">${num}</a></li>
          						</c:forEach>
          						 <c:if test="${pageMaker.next}">
          						 <li class="page-item ">
							      <a class="page-link" href="${pageMaker.endPage+1}" tabindex="-1">next</a>
          						 </c:if>
							    </li>
          					</ul>
          					
          					</div>
          					
          					<form id="actionForm" action="/board/list" method="get">
  								<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">        					 
  								<input type="hidden" name="amount" value="${pageMaker.cri.amount }">        					 
          					</form>
          					
          					
          					
          					
          					
          					
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>


<!-- 모달창   -->
		<div id="myModal" class="modal" tabindex="-1" role="dialog">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">Modal title</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <p>Modal body text goes here.</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary">Save changes</button>
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
<!-- 모달창  끝-->
            
<script>

<!-- 글작성 완료시 모달창 알림 -->
$(document).ready(function(){
	//bno값이 result에 담김 
	var result= '<c:out value ='${result}'/>';
	
	checkModal(result);
	history.replaceState({},null, null);
	
	function checkModal(result){
		
		if(result ==='' ||  history.state){
			return;
		}
		if(result ==='success'){ //삭제나 수정되었을때 
			$(".modal-body").html("정상적으로 처리 되었습니다.");
		}else if (parseInt(result) > 0 ){//글등록 되엇을때 bno값 담김 그때 
			$(".modal-body").html("게시글"+parseInt(result)+"번이 등록되었습니다.");
		}

		$("#myModal").modal("show"); //모달창띄어줌
	}
	
	// 글작성버튼 클릭시 글작성 페이지로 이동   
	$("#regBtn").click(function(){
		self.location= "/board/register";
	});
	
	//페이지 버튼
	var actionForm = $('#actionForm');
	
	$(".page-link").on("click", function(e){
		e.preventDefault(); //기본 동작 제한
		
		var targetPage =$(this).attr("href"); //버튼번호
		console.log(targetPage);
		
		actionForm.find("input[name='pageNum']").val(targetPage);
		actionForm.submit();
	});
	
	//상세보기 가기 버튼
	$(".move").on("click",function(e){
		e.preventDefault(); //기본 동작 제한
		var targetBno =$(this).attr("href"); //버튼번호
		
		console.log(targetBno);
		actionForm.append("<input type='hidden' name='bno' value='"+targetBno+"'>'");
		actionForm.attr("action","/board/get").submit();

		
	})

});

</script>



<%@include file="../includes/footer.jsp" %>
