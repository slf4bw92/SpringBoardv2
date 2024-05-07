<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
	<!-- Header -->
	<%@ include file="/WEB-INF/views/includes/header.jsp" %>
		
		<!-- Content Title  -->
        <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">게시판 /</span> 게시글 목록</h4>
		
   			  <!-- Basic Bootstrap Table -->
              <div class="card mb-4">
               	<h5 class="card-header">Board List Page</h5>
				
				<div class="d-grid gap-2 col-2 ms-auto mx-2">
				  <button class="btn btn-primary" type="button" onclick="location.href='boards/add'">게시글 등록</button>
				</div>
                
                <div class="table-responsive text-nowrap">
                  <table class="table">
                    <thead>
                      <tr>
                        <th>#게시글 번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>수정일</th>
                      </tr>
                    </thead>
                    <tbody class="table-border-bottom-0">
                    <c:forEach var="board" items="${boards }" >
                      <tr>
                        <td><i class="fab fa-angular fa-lg text-danger me-3"></i> <strong>${board.id }</strong></td>
                        <td class="move" 
                            onclick="location.href='/boards/${board.id}?pageNum=${pageMaker.criteria.pageNum }&amount=${pageMaker.criteria.amount }'">
                            <c:out value="${board.title }"/> </td>                            
                        <td><c:out value="${board.writer }"/> </td>
                        <td><fmt:formatDate value="${board.regDate }" pattern="yyyy-MM-dd"/></td>
                        <td><fmt:formatDate value="${board.updateDate }" pattern="yyyy-MM-dd"/></td>
                      </tr>
                     </c:forEach> 
                    </tbody>
                  </table>
                </div>
				  <nav class="m-3" aria-label="Page navigation">
                    <ul class="pagination justify-content-end">
                      <!-- 이전 페이지 버튼  -->
                      <c:if test="${pageMaker.prev }">
                        <li class="page-item prev">
                          <a class="page-link" href="<c:out value='${pageMaker.startPage - 1 }'/>"><i class="tf-icon bx bx-chevrons-left"></i></a>
                        </li>
                      </c:if>
                      
                      <!-- 페이지 번호  -->
                      <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">  
                        <!-- 페이지번호랑 현재 페이지 번호와 같으면 active -->
                        <li class="page-item ${pageMaker.criteria.pageNum == num ? 'active' : '' } ">
                          <a class="page-link" 
                             href="/boards?pageNum=${num }&amount=${pageMaker.criteria.amount}" >${num }</a>
                        </li>
                      </c:forEach>
                      
                      <!-- 다음 페이지 버튼  -->  
                      <c:if test="${pageMaker.next }">
                        <li class="page-item next"> 
                          <a class="page-link" href="<c:out value='${pageMaker.endPage + 1 }'/>"><i class="tf-icon bx bx-chevrons-right"></i></a>
                        </li>
                      </c:if>
                    </ul>
                    <!-- 페이지 이동 용 <form> 태그 -->
                    <form id="actionForm" action="/boards" method="get">
                    	<input type="hidden" name="pageNum"  value="<c:out value='${pageMaker.criteria.pageNum }'/>" >
                    	<input type="hidden" name="amount"   value="<c:out value='${pageMaker.criteria.amount}'/>" >
                    </form>
                  </nav>		
              </div>
              <!--/ Basic Bootstrap Table -->



              <!-- Modal 추가 -->
              <div class="modal fade" id="myModal" tabindex="-1" aria-hidden="true">
                <div class="modal-dialog" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="exampleModalLabel1">Modal title</h5>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" 
                             aria-hidden="true" aria-label="Close">&times;</button>
                    </div>
                    <div class="modal-body">처리가 완료되었습니다</div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                  </div>
               	  <!--/.Modal-content  -->
               </div>
               <!--/.Modal-dialog -->
              </div>
			  <!--/.Modal  -->
	
	
	<!-- JS 부분  -->
	<script type="text/javascript">
		$(document).ready(function(){
			
			/* 게시글 CUD 결과 변수 */
			var result = '<c:out value="${result}"/>';
			
			checkModal(result);
			
			/**
			 * 게시글 결과 안내 모달
			 * 게시글 동작 결과(result)에 값이 없으면 그냥 리턴(아무 행동도 안한 상태)
			 * result 값 : 수정 성공(updateSuccess), 삭제 성공(deleteSuccess), 등록 성공(등록된 게시글 번호) 
			 * 등록 성공 result의 경우 문자열 -> 정수로 형변환 필요
			 *
			 */
			function checkModal(result) {
				
				if (result === '') {
					return;
				}
				
				if (result === 'updateSuccess') {
					$(".modal-body").html("<c:out value="${id}"/>번 게시글이 수정 되었습니다.")
				}
				
				if (result === 'deleteSuccess') {
					$(".modal-body").html("<c:out value="${id}"/>번 게시글이 삭제 되었습니다.")
				}
				
				if (parseInt(result) > 0) {
					$(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다.")
				}
				
				$("#myModal").modal("show");
			}
			
			/**
			 * 이거 이제 사용 안하고 그냥 <a> 나 location.href 사용
			 * 페이지 이동 함수 
			 * 페이지 이동 관련 버튼(이전, 번호, 다음) 클릭시
			 *   1) 기존 a 태그 이벤트 막음
			 *   2) actionForm 요소내 페이지 번호(pageNum)를 클릭한 번호로 변경
			 *   3) actionForm 제출
			 */
/* 			var actionForm = $("#actionForm");
			
			$(".page-item a").on("click", function(e){
				
				e.preventDefault();
				
				actionForm.find("input[name='pageNum']").val($(this).attr("href"));
				actionForm.submit();
			}); */
			 
			 
		});
	</script>
	
	
	<!-- Footer -->
	<%@ include file="/WEB-INF/views/includes/footer.jsp" %>