<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<!-- Header -->
	<%@ include file="/WEB-INF/views/includes/header.jsp" %>
		
		<!-- Content Title  -->
        <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">게시판 /</span> 게시글 상세</h4>
        
             <div class="row">
                <div class="col-xl">
                  <div class="card mb-4">
                    <div class="card-header d-flex justify-content-between align-items-center">
                      <h5 class="mb-0">Detail</h5>
                      <small class="text-muted float-end">Default label</small>
                    </div>
                    <div class="card-body">
                      	<div class="mb-3">
                          <label class="form-label" for="basic-default-fullname">#게시글 번호</label>
                          <input type="text"   class="form-control" id="basic-default-fullname" readonly="readonly" 
                                 name="boardId"     value="<c:out value='${board.boardId }'/>"/>
                        </div>
                        <div class="mb-3">
                          <label class="form-label" for="basic-default-fullname">제목</label>
                          <input type="text"   class="form-control" id="basic-default-fullname" readonly="readonly" 
                          		 name="title"  value="<c:out value='${board.title }'/>"/>
                        </div>
                        <div class="mb-3">
                          <label class="form-label" for="basic-default-company">작성자</label>
                          <input type="text"   class="form-control" id="basic-default-company" readonly="readonly" 
                                 name="writer" value="<c:out value='${board.writer }'/>" />
                        </div>

                        <div class="mb-3">
                          <label class="form-label" for="basic-default-message">내용</label>
                          <textarea id="basic-default-message" class="form-control"  rows="5" readonly="readonly"
                                    name="content"><c:out value='${board.content }'/></textarea>
                        </div>
                        <!-- data-* 사용자 커스텀 속성 추가 --> 
                        <button data-oper="modify" class="btn btn-primary">수정</button>
                        <button data-oper="list"   class="btn btn-secondary">목록으로</button>
                        
                        <!-- 수정, 목록으로 버튼 클릭시 form 통한 이동 -->
                        <form id="operForm" action="/boards/${board.boardId }/edit" method="get">
                        	<!-- 페이지 정보 보관용(현재 페이지, 페이지당 게시글 수) -->
                        	<input type="hidden" name="pageNum" value="${criteria.pageNum }">
                        	<input type="hidden" name="amount"  value="${criteria.amount }">
                        	
                        	<!-- 검색 정보 보관용(검색 타입, 검색 키워드) -->
                        	<input type="hidden" name="type"    value="${criteria.type }">
                        	<input type="hidden" name="keyword" value="${criteria.keyword }">
                        </form>
                    </div>
                  </div>
                </div>
              </div>
			  <!-- /.row -->	
	
	
	<!-- JS  -->
	<script type="text/javascript">
		$(document).ready(function(){
			
			/**
			 * Form 동작 함수(수정, 목록으로)
			 * 버튼이 form 밖에 있으므로 함수로 연결
			 * 1. 수정 버튼 누르면 form 의 action 속성 값을 수정 url 로 변경후 form 제출
			 * 2. 삭제 버튼 클릭시 form 의 action 속성 값을 삭제 url 로 변경후 form 제출
			 */
			var operForm = $("#operForm");
			
			$("button[data-oper='modify']").on("click", function(e){
				
				operForm.attr("action", "/boards/${board.boardId}/edit").submit();
			});
			
			$("button[data-oper='list']").on("click", function(e){
				
				operForm.attr("action", "/boards").submit();
			});
		});
	</script>
	
	<!-- reply module : ajax 통신 + reply 관련 비즈니스로직 제공 -->
	<script type="text/javascript" src="/resources/js/reply.js"></script>
	
	<!-- reply module Test 로직, 완료 후 주석처리 -->
	<script type="text/javascript" src="/resources/js/replyServiceTest.js"></script>
	
	<!-- reply 이벤트 처리 --> 
	<script type="text/javascript">
		$(document).ready(function(){
			
		});
	
	</script>	
	
	
	
	<!-- Footer -->
	<%@ include file="/WEB-INF/views/includes/footer.jsp" %>