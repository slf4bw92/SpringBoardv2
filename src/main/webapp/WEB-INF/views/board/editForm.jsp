<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<!-- Header -->
	<%@ include file="/WEB-INF/views/includes/header.jsp" %>
		
		<!-- Content Title  -->
        <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">게시판 /</span> 게시글 수정</h4>
        
             <div class="row">
                <div class="col-xl">
                  <div class="card mb-4">
                    <div class="card-header d-flex justify-content-between align-items-center">
                      <h5 class="mb-0">Detail</h5>
                      <small class="text-muted float-end">Default label</small>
                    </div>
                    <div class="card-body">
                      <form role="form" action="/boards/<c:out value='${board.id }'/>/edit" method="post">
                      	<div class="mb-3">
                          <label class="form-label" for="basic-default-fullname">#게시글 번호</label>
                          <input type="text"   class="form-control" id="basic-default-fullname" readonly="readonly" 
                                 name="id"     value="<c:out value='${board.id }'/>"/>
                        </div>
                        <div class="mb-3">
                          <label class="form-label" for="basic-default-fullname">제목</label>
                          <input type="text"   class="form-control" id="basic-default-fullname" 
                          		 name="title"  value="<c:out value='${board.title }'/>"/>
                        </div>
                        <div class="mb-3">
                          <label class="form-label" for="basic-default-company">작성자</label>
                          <input type="text"   class="form-control" id="basic-default-company" readonly="readonly" 
                                 name="writer" value="<c:out value='${board.writer }'/>" />
                        </div>

                        <div class="mb-3">
                          <label class="form-label" for="basic-default-message">내용</label>
                          <textarea id="basic-default-message" class="form-control"  rows="5" 
                                    name="content"><c:out value='${board.content }'/></textarea>
                        </div>
                        
                        <div class="mb-3">
<!--                           <label class="form-label" for="basic-default-company">Regdate</label> -->
                          <input type="text"   class="form-control" id="basic-default-company" readonly="readonly" hidden="hidden" 
                                 name="regDate" value='<fmt:formatDate pattern ="yyyy/MM/dd" value="${board.regDate }"/>'  />
                        </div>
                        
                        <div class="mb-3">
<!--                           <label class="form-label" for="basic-default-company">updateDate</label> -->
                          <input type="text"   class="form-control" id="basic-default-company" readonly="readonly" hidden="hidden" 
                                 name="updateDate" value='<fmt:formatDate pattern ="yyyy/MM/dd" value="${board.updateDate }"/>' />
                        </div>

                        
                        <!-- 사용자 커스텀 속성(oper 속성) 추가 -->
                       	  <button data-oper="edit"    type="submit" class="btn btn-primary">수정</button>
                       	  <button data-oper="delete"  type="submit" class="btn btn-danger" >삭제</button>
                       	  <button data-oper="list"    type="submit" class="btn btn-secondary">취소</button>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
			  <!-- /.row -->	
	
	
	<!-- JS 부분  -->
	<script type="text/javascript">
		$(document).ready(function() {
			
			var formObj = $("form");
			
			/**  
			 * form 요소의 수정,삭제,취소 submit 한번에 처리하는 로직   	
			 * 1. form 요소내 버튼 클릭시 생기는 기본 이벤트(submit)를 막는다.
			 * 2. oper 속성 값에 따라 다른 방식으로 요청 한다
			 *    1) delete : 게시글 삭제, FORM 요소의 action 변경 -> /boards/{id}/delete 
			 *    2) list   : 목록 돌아가기, location.href 활용해 이동 -> /boards
			 *    3) edit   : 게시글 수정, FORM 요소의 action 그대로 사용
			 * 3. 직접 submit 이벤트 수행
			 */
			$('button').on("click", function(e){
				
				e.preventDefault();
				
				var operation = $(this).data("oper");
				console.log(operation);
				
				if(operation === 'delete') {
					formObj.attr("action", "/boards/<c:out value='${board.id }'/>/delete");
				} else if (operation === 'list') {
					location.href = "/boards";
					return;
				}			
				formObj.submit();
				
			});
			
		});
			
		
	
	
	
	</script>
	
	
	<!-- Footer -->
	<%@ include file="/WEB-INF/views/includes/footer.jsp" %>