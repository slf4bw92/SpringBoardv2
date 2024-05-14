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
                    <hr class="mb-0 mx-4 mt-0 border border-1">
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
			  
			  <!-- 댓글 -->	
			  <div class="row">
			  	<div class="col-xl">
                  <div class="card mb-4">
                    
                    <!-- 댓글 헤더 -->
                    <div class="card-header d-flex justify-content-between align-items-center bg-primary mb-3">
                      <h5 class="mb-0 text-white"><i class="bi bi-chat-dots-fill"></i> Reply List</h5>
                      <button type="button" id="addReplyBtn" class="btn btn-info">댓글 등록</button>
                    </div>
                    
                    <!-- 댓글 목록 -->
                    <div class="card-body mb-0 pb-0">
					  <ul class="list-group list-group-flush" id="chat">
                        <li class="list-group-item px-0" data-replyid="12">
                          <div>
                            <div class="header d-flex justify-content-between">
                        	  <strong>user00</strong>
                        	  <small class="text-muted">yyyy-MM-dd hh:mm</small>
                            </div>
                            <p>댓글 내용이 들어갈 부분입니다.</p>
                          </div>
                        </li>
                      </ul>
                    </div>
                    
                    <hr class="mx-4 mt-0 mb-5">
                    <!-- 댓글 페이징 -->
                    <div class="card-footer mb-3">
                    	
                    </div>
                  
                  </div>
                </div>
			  </div>	
			  <!-- 댓글 /.row -->
	  			
	  		  
  			  
  			  <!-- 댓글 Modal(공통) -->
  			  <!-- 댓글 등록/조회/수정/삭제 다 해당 모달창에서 사용, 각 기능에따라 jQuery통해 숨기고 추가하고 함 -->
			  <div class="mt-3">
                 <div class="modal fade" id="replyModal" tabindex="-1" style="display: none;" aria-hidden="true">
                   <div class="modal-dialog" role="document">
                     <div class="modal-content">
                       
                       <!-- 댓글 모달 헤더  -->
                       <div class="modal-header">
                         <h5 class="modal-title" id="exampleModalLabel1">댓글</h5>
                         <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                       </div>
					   <hr class="border mx-4 mb-0">
                       
                       <!-- 댓글 모달 바디(input)  -->
                       <div class="modal-body">
                         <div class="row">
                           <div class="col mb-3">
                             <label for="nameBasic" class="form-label">댓글 내용</label>
                             <input type="text" name="replyContent" class="form-control" placeholder="댓글 내용">
                           </div>
                         </div>
                         <div class="row">
                           <div class="col mb-3">
                             <label for="emailBasic" class="form-label">작성자</label>
                             <input type="text" name="replyer" class="form-control" placeholder="댓글 작성자">
                           </div>
                         </div>
                         <div class="row">
                           <div class="col mb-3">
                             <label for="dobBasic" class="form-label">등록일</label>
                             <input type="text" name="regDate" class="form-control" placeholder="yyyy-MM-dd hh:mm">
                           </div>
                         </div>
                       </div>
                       
                       <!-- 댓글 모달 푸터(button)  -->
                       <div class="modal-footer">
				         <button id='modalEditBtn'     type="button" class="btn btn-warning">수정</button>
				         <button id='modalRemoveBtn'   type="button" class="btn btn-danger">삭제</button>
				         <button id='modalRegisterBtn' type="button" class="btn btn-primary">등록</button>
				         <button id='modalCloseBtn'    type="button" class="btn btn-secondary" data-bs-dismiss="modal">돌아가기</button>
                       </div>
                     
                     </div>
                   </div>
                 </div>
              </div>
    		  <!-- 댓글 /.modal -->
			  
	
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
			
			var boardIdValue = <c:out value='${board.boardId}'/>
			//reply Unoredered List : 댓글 리스트의 <ul> 요소
			var replyUL = $("#chat");
			
			showList(1);
			
			/**
			 * 댓글 목록 함수(페이징 O)
			 * 1)reply 모듈에 전달할 param 객체 추가 (page는 reply객체가아니므로 param이라 명명)
			 * 2)댓글 목록모듈 성공시 함수 호출
			 *   (1)페이지 번호가 -1이면 마지막 페이지로 이동(댓글 등록시 마지막페이지 이동 용)
			 *   (2)조회된 list가 없거나 0이면 댓글목록 화면 비우고 리턴
			 *   (3)list 값이 있으면 for문 돌려서 댓글 전부 출력 
			 *   (4)page : page || 1 의미 : page값이 없으면 1 대입(자동으로 1페이지 세팅)
			 */
			function showList(page) {
				
				var param = {
					boardId : boardIdValue,
					page : page || 1
				};
				 
				replyService.findAll(param , function(replyCnt, list){

					if(page == -1) {
					    //pageNum : 마지막 페이지 계산
					    //10 : 한 페이지당 댓글 수(amount): JS에선 마지막페이지 계산할 때, 소수(형변환 용)로 안해도 되는데 자바에서 했으므로 일관성 지키기위해 함
						pageNum = Math.ceil(replyCnt / 10.0);
						showList(pageNum);
						return;
					}
					
					var str="";
					
					//댓글 없으면 돌아가기
					if (list == null || list.length == 0) {
						
						replyUL.html("");
						//댓글 없을때 페이지 번호도 안보이는 이유 : 여기서 그냥 반환 되므로 뒤에있는 showReplyPage 함수가 동작안함,
						return;
					}
					
					for (var i = 0, len = (list.length || 0); i < len; i++  ) {
						
						str += "<li class='list-group-item px-0' data-replyid='" + list[i].replyId + "'>";
						str += "  <div>";
						str += "    <div class='header d-flex justify-content-between'>";   
						str += "      <strong>" + list[i].replyer + "</strong>";
						str += "      <small class='text-muted'>" + replyService.displayTime(list[i].regDate) + "</small>";
						str += "    </div>";
						str += "  <p>" + list[i].replyContent + "</p>";
						str += "  </div>";
						str += "</li>";					
					}
					replyUL.html(str);
					
					showReplyPage(replyCnt);
				});
			} // end showList function
			
			//  페이지번호 (전역변수)
			var pageNum = 1;
			var replyPageFooter = $(".card-footer");
			
			/**
			 * 페이지 버튼 보이는 함수
			 * 이전 버튼, 다음 버튼, 페이지 번호를 출력하는 용도
			 * @Param replyCnt : 전체 댓글 수 (진짜 끝 페이지 체크용)
			 */
			function showReplyPage(replyCnt) {
				console.log("showReplyPage pageNum : ", pageNum);
				var endNum = Math.ceil(pageNum / 10.0) * 10;
				var startNum = endNum - 9;
				
				//이전 페이지 이동 버튼 : 시작 페이지 번호가 1만 아니면 존재 
				var prev = startNum != 1;
				var next = false;
				
				//진짜 끝 페이지 찾기
				if (endNum * 10 >= replyCnt) {
					endNum = Math.ceil(replyCnt/10.0);
				}
				
				//다음 페이지 이동 버튼 : 끝 페이지 번호 * 10 이 전체 댓글 수보다 작으면 존재 
				if(endNum * 10 < replyCnt) {
					next = true;
				}
				
				var str = "<ul class='pagination justify-content-center'>";
				
				if(prev) {
					str += "<li class='page-item prev'>";
					str += "  <a class='page-link' href='" + (startNum - 1) + "'><i class='tf-icon bx bx-chevrons-left'></i></a>";
					str += "</li>";
				}
				
				for(var i = startNum; i <= endNum; i++) {
					var active = (pageNum == i ? "active" : "");
					
					str += "<li class='page-item " + active + "'>";
					str += "  <a class='page-link' href='" + i + "'>" + i + "</a>";
					str += "</li>";
				}
				
				if (next) {
					str += "<li class='page-item next'>";
					str += "  <a class='page-link' href='" + (endNum + 1) + "'><i class='tf-icon bx bx-chevrons-right'></i></a>";
					str += "</li>";
				}
				
				str += "</ul>";
				
				replyPageFooter.html(str);
			}
			
			/**
			 * 페이지 버튼 클릭시 새 댓글목록 조회
			 * 이벤트 대상을 replyPageFooter 에서 <li>의 하위 <a> 태그로 이벤트 대상 변경
			 * 1.기존 a 이벤트 막음
			 * 2.페이지 번호를 클릭한 a태그의 href 값으로 변경한다 
			 * 3.해당 페이지번호로 댓글 목록 조회
			 */
			 replyPageFooter.on("click", "li a", function(e){
				 
				 e.preventDefault();
				 
				 // $(this)는 위임된 곳이 선택 된다 = <a> 태그
				 pageNum = $(this).attr("href");
				 
				 showList(pageNum);
			 });
			 
			 
			/**
			 * 댓글 모달창 관련 객체 
			 * 여러 함수에서 공통으로 사용하므로 함수 외부에 선언
			 */
			var modal = $(".modal");
			var modalInputReplyContent = modal.find("input[name='replyContent']");
			var modalInputReplyer = modal.find("input[name='replyer']");
			var modalInputRegDate = modal.find("input[name='regDate']");
			
			var modalEditBtn = $("#modalEditBtn");
			var modalRemoveBtn = $("#modalRemoveBtn");
			var modalRegisterBtn = $("#modalRegisterBtn");
			
			/**
			 * 댓글 등록창 열기 함수
			 * 댓글 등록창 버튼 클릭시 
			 *   1)모달창 하위 input 요소 value 비움 
			 *     -새로 입력할 것이므로, 기존에 있는 값(= ex: 조회할 때 쓰던 값) 비움
			 *   2)댓글 등록날짜 관련 div는 숨김 
			 *     -댓글 등록 수행시, 등록 날짜는 보이면 안되므로
			 *   3)게시글 등록 버튼(modalRegisterBtn), 돌아가기 버튼(modalCloseBtn)만 남기고 수정,삭제 버튼은 숨김
			 *   4)모달창 보여주기
			 */
			$("#addReplyBtn").on("click", function(e){
				
				modal.find("input").val("");
				
				modalInputRegDate.closest("div").hide();
				// 댓글 작성자 입력창 readonly속성 제거
				modalInputReplyer.removeAttr("readonly");
				modal.find("button[id != 'modalCloseBtn']").hide();
				modal.find("button[id = 'modalRegisterBtn']").show();
				
				
				$("#replyModal").modal("show");	
			});
			
 
			/**
			 * 댓글 등록 처리 함수
			 * 등록 버튼 클릭시
			 *   1)댓글 모듈(replyService)에 넘길 reply 객체 생성 (사용자가 입력한 내용 + 게시글 번호)
			 *   2)replyService 호출해 댓글 등록 수행
			 *   3)등록 성공시 함수
			 *     (1)alert창에 응답 메시지 출력 
			 *     (2)모달창 입력 값 비움
			 *     (3)모달창 숨기기
			 *     (4)게시글 1페이지로 이동
			 */  
			$("#modalRegisterBtn").on("click", function(){
				
				var reply = {
					replyContent : modalInputReplyContent.val(),
					replyer : modalInputReplyer.val(),
					boardId : boardIdValue
				}
				
				replyService.add(reply, function(result){
					
					alert(result);
					
					modal.find("input").val("");
					
					modal.modal("hide");
					
					showList(-1);
				})
			});
			
			/**
			 * 특정 댓글 클릭 이벤트 처리
			 * 댓글 목록은 동적으로 생기므로 이벤트 위임 형태로 작성 ($(this) -> <li> 요소)
			 * 댓글 li 요소 클릭시
			 *   1)댓글 모듈에 넘길 reply 객체 생성
			 *   2)댓글 조회 수행후 성공시 함수 호출 
			 *     (1)reply객체를 모달창 input요소에 추가
			 *        -Ajax 함수는 응답이 JSON 형식인 경우 자동으로 javascript 객체로 변환해줌
			 *        -replyId는 수정,삭제에 사용하므로 data에 등록해놓음
			 *     (2)모달창 버튼 수정, 삭제, 돌아가기 버튼만 보이게 변경
			 *     (3)모달창 보여주기
			 */
			$("#chat").on("click", "li", function(e){
				  
				var reply = {
					replyId : $(this).data("replyid"), 
					boardId : boardIdValue
				};
				
				replyService.find(reply, function(reply){
					
					modalInputReplyContent.val(reply.replyContent);
					modalInputReplyer.val(reply.replyer).attr("readonly", "readonly");
					modalInputRegDate.val(replyService.displayTime(reply.regDate)).attr("readonly", "readonly");
					modal.data("replyid", reply.replyId);
					
					modal.find("button[id != 'modalCloseBtn']").hide();
					modalEditBtn.show();
					modalRemoveBtn.show();
					
					$(".modal").modal("show");
				})
			});
			 
			/**
			 * 댓글 수정 처리 함수
			 * 댓글 수정 버튼 클릭시
			 *   1)댓글 모듈에 넘길 reply 객체 생성(게시글 번호, 댓글 번호, 댓글 내용)
			 *   2)댓글 업데이트 수행후 성공시 함수 호출
			 *     (1)결과 메시지 alert 창으로 표시
			 *     (2)모달창 숨기기
			 *     (3)현재 댓글이 포함된 페이지로 돌아가기
			 *        -특정 페이지로 이동시 전역변수의 pageNum 값이 이미 변경 돼있으므로 그 값만 사용하면 됨
			 */
			modalEditBtn.on("click", function(e){
				
				var reply = {
					boardId : boardIdValue,
					replyId : modal.data("replyid"),
					replyContent : modalInputReplyContent.val()
				};
				
				replyService.edit(reply, function(result){
					
					alert(result);
					modal.modal("hide");
					showList(pageNum);
				});
				
			});
			
			 
		   /**
			* 댓글 삭제 처리 함수
			* 댓글 삭제 버튼 클릭시
			*   1)댓글 모듈에 넘길 reply 객체 생성(게시글 번호, 댓글 번호)
			*   2)댓글 삭제 수행후 성공시 함수 호출
			*     (1)결과 메시지 alert 창으로 표시
			*     (2)모달창 숨기기
			*     (3)현재 댓글이 포함된 페이지로 돌아가기
			*        -특정 페이지로 이동시 전역변수의 pageNum 값이 이미 변경 돼있으므로 그 값만 사용하면 됨
			*/
			modalRemoveBtn.on("click", function(e){
				
				var reply = {
				
					boardId : boardIdValue,
					replyId : modal.data("replyid")
				};
				
				replyService.remove(reply, function(result){
					
					alert(result);
					modal.modal("hide");
					showList(pageNum);
				});
			});
			
			  
		});
	</script>	
	
	
	
	<!-- Footer -->
	<%@ include file="/WEB-INF/views/includes/footer.jsp" %>