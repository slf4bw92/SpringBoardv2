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
                        <td id ="boardId"><i class="fab fa-angular fa-lg text-danger me-3"></i><strong>${board.boardId }</strong></td>
                        <td class="move"> 
                        	<a href="/boards/${board.boardId}?pageNum=${pageMaker.criteria.pageNum }&amount=${pageMaker.criteria.amount }">
                        		<c:out value="${board.title }"/>
	                        </a></td>                            
                        <td><c:out value="${board.writer }"/> </td>
                        <td><fmt:formatDate value="${board.regDate }" pattern="yyyy-MM-dd"/></td>
                        <td><fmt:formatDate value="${board.updateDate }" pattern="yyyy-MM-dd"/></td>
                      </tr>
                     </c:forEach> 
                    </tbody>
                  </table>
                </div>
				  
				  <nav class="m-2" aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                      <!-- 이전 페이지 버튼  -->
                      <c:if test="${pageMaker.prev }">
                        <li class="page-item prev">
                          <a class="page-link" href="${pageMaker.startPage - 1 }"><i class="tf-icon bx bx-chevrons-left"></i></a>
                        </li>
                      </c:if>
                      
                      <!-- 페이지 번호  -->
                      <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">  
                        <!-- 페이지번호랑 현재 페이지 번호와 같으면 active -->
                        <li class="page-item ${pageMaker.criteria.pageNum == num ? 'active' : '' } ">
                          <a class="page-link" 
                             href="${num }">${num }</a>
                        </li>
                      </c:forEach>
                      
                      <!-- 다음 페이지 버튼  -->  
                      <c:if test="${pageMaker.next }">
                        <li class="page-item next"> 
                          <a class="page-link" href="${pageMaker.endPage + 1 }"><i class="tf-icon bx bx-chevrons-right"></i></a>
                        </li>
                      </c:if>
                    </ul>
                    <!-- 검색 용 <form> 태그 -->
                    <form id="searchForm" action="/boards" method="get">
                    	<!-- 페이지 보관용  -->  
                       <input type="hidden" name="pageNum"  value="<c:out value='${pageMaker.criteria.pageNum }'/>" >
                       <input type="hidden" name="amount"   value="<c:out value='${pageMaker.criteria.amount}'/>" >
                       
                       <!-- 검색 타입(select box), 검색 키워드 입력  -->
                       <div class="row justify-content-md-center mb-3">
                    	  <div class="col-md-5">
	                        <div class="input-group">
	                    	  <div class="col-md-3 me-1">
	                    		<select class="form-select" id="inputGroupSelect02" name="type">
		                          <option value="TC"<c:if test="${pageMaker.criteria.type == null || pageMaker.criteria.type == 'TC'}">selected</c:if>>제목+내용</option>
		                          <option value="T" <c:if test="${pageMaker.criteria.type == 'T'}">selected</c:if>>제목</option>
		                          <option value="C" <c:if test="${pageMaker.criteria.type == 'C'}">selected</c:if>>내용</option>
		                          <option value="W" <c:if test="${pageMaker.criteria.type == 'W'}">selected</c:if>>작성자</option>
			                    </select>
			                  </div>
		                      <div class="col-md-6">
	   							<input class="form-control" type="text" name="keyword" placeholder="Search" aria-label="Search"
	   								   value='<c:out value="${pageMaker.criteria.keyword }"></c:out>'>
	   								   
 							  </div>
  							  <div class="col-md-2 ms-1">
	   						    <button class="btn btn-outline-primary" type="submit">Search</button>
   							  </div>
	   					   </div>
   						 </div>
 					   </div>
   						
                    </form>
                    
                    <!-- 페이지 이동용 Form 태그  -->
                    <form id="actionForm" action="/boards" method="get">
  						
  					   <!-- 페이지 정보 보관용(현재 페이지, 페이지당 게시글 수) -->
                       <input type="hidden" name="pageNum"  value="<c:out value='${pageMaker.criteria.pageNum }'/>" >
                       <input type="hidden" name="amount"   value="<c:out value='${pageMaker.criteria.amount}'/>" >
                       
                       <!-- 검색 정보 보관용(검색 타입, 검색 키워드)  -->
					   <input type='hidden' name='type'     value="<c:out value='${pageMaker.criteria.type }'/>"> 
					   <input type='hidden' name='keyword'  value="<c:out value='${pageMaker.criteria.keyword }'/>">
   						
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
					$(".modal-body").html("<c:out value="${boardId}"/>번 게시글이 수정 되었습니다.")
				}
				
				if (result === 'deleteSuccess') {
					$(".modal-body").html("<c:out value="${boardId}"/>번 게시글이 삭제 되었습니다.")
				}
				
				if (parseInt(result) > 0) {
					$(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다.")
				}
				
				$("#myModal").modal("show");
			}
			
			/**
			 * 페이지 이동 jQuery 함수 1
			 * 페이지 이동 관련 버튼(이전, 번호, 다음) 클릭시
			 *   1) 기존 a 태그 이벤트 막음
			 *   2) actionForm 요소내 페이지 번호(pageNum)를 클릭한 번호로 변경
			 *   3) actionFrom 요소내 action 의 값을 "/boards" (목록 페이지)로 변경    
			 *      why? actionForm을 페이지 조회, 이동이 같이 사용하고있어서 조회 -> 뒤로가기 -> 페이지이동(번호 클릭)시
			 *           조회페이지에 썼던 action 값(URL)을 그대로 쓰면 "/boards/ + boardId" 에 의해 조회 페이지로 이동해버림   
			 *   4) actionForm 제출
			 */
			var actionForm = $("#actionForm");
			
			$(".page-item a").on("click", function(e){

				e.preventDefault();
				
				actionForm.find("input[name='pageNum']").val($(this).attr("href"));
				
				actionForm.attr("action", "/boards");
				actionForm.submit();
			});
			 

			/**
			 * 목록 -> 조회 페이지 이동 jQuery 함수
			 * 게시글 제목 클릭시
			 *   1) 기존 a 태그 막고
			 *   2) 게시글 번호 가져온다.
			 *   3) 게시글 번호 활용해 action 속성에 조회 페이지 요청 url 작성
			 *   4) actionForm 제출
			 */
			$(".move").on("click", function(e) {

				e.preventDefault();
				
				// 선택한 요소의 가장 가까운 부모 tr을 찾고, 그 하위요소중 boardId 이름을가진 요소의 내용을 가져온다.
				var boardId = $(this).closest("tr").find("#boardId").text(); 
				
				actionForm.attr("action", "/boards/" + boardId);
				actionForm.submit();

			});
			
			
			/**
			 * 검색 버튼 이벤트 처리 jQuery 함수
			 * 1. 검색 키워드 유효성 검사 : 키워드 입력 안하면 경고창 띄우고, false 반환 
			 * 2. 키워드 입력시
			 *       1) 기본 이벤트 막음
			 *       2) 1 페이지로 보냄 : 검색된 내용을 처음부터 봐야 하므로
			 *       3) form 제출 
			 */
			var searchForm = $("#searchForm")
			
			$("#searchForm button").on("click", function(e){
				
				if(!searchForm.find("input[name='keyword']").val()){
					alert("키워드를 입력하세요");
					return false;
				}
				
				e.preventDefault();
				
				searchForm.find("input[name='pageNum']").val("1");
				searchForm.submit();
			});
			
			
		});
	</script>
	
	
	<!-- Footer -->
	<%@ include file="/WEB-INF/views/includes/footer.jsp" %>