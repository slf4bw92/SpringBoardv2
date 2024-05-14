/**
 * Reply Module
 * replyService 객체 생성 로직
 * 즉시 실행 함수 내에 여러함수(댓글 목록/등록/조회/수정/삭제)를 구현 후 반환하여 객체 생성  
 * Ajax 통신을 통해서 댓글관련 비즈니스로직 처리함
 */
 	console.log("Reply Module...");
		 
	 	var replyService = (function(){
	 		
	 		/**
	 		 * 댓글 등록
	 		 * @param reply    : 댓글 등록에 사용할 객체(게시글 내용, 작성자, 게시글 번호)
	 		 *                   (Controller에서 @RequestBody로 reply 객체 받는 경우 reply로 작성함, 그 외엔 param)
	 		 * @param callback : 요청 성공시 호출용 함수
	 		 * @param error    : 요청 에러 발생시 호출용 함수 (테스트시 구현 안해 놓음)
	 		 */
	 		function add(reply, callback, error) {
	 			
	 			console.log("add reply...");
	 			
	 			$.ajax({
	 				type : "post",
	 				url  : "/boards/" + reply.boardId + "/replies",
	 				data : JSON.stringify(reply),
	 				contentType : "application/json;charset=utf-8",
	 				success : function(result, status, xhr) {
	 					//의미: callback파라미터에 인자로 함수가 들어왔다면 함수호출
	 					if (callback) { 		
	 						callback(result);
	 					}
	 				},
	 				error : function(xhr, status, err) {
	 					//의미: error파라미터에 인자로 함수가 들어왔다면 error함수 호출
	 					if (error) {	
	 						error(err);  
	 					}
	 				}
	 			});
	 		}
	 		
	 		
	 		/**
	 		 * 댓글 목록
	 		 * @param param    : Ajax통신에 쓸 파라미터 모아놓은 객체(게시글번호, 페이지 번호) 
	 		 * @param callback : 요청 성공시 호출용 함수
	 		 * @param error    : 요청 에러 발생시 호출용 함수
	 		 */	 		
	 		function findAll(param, callback, error) {
	 			
	 			console.log("findAll ready...");
	 			
	 			var boardId = param.boardId;
	 			var page = param.page || 1;

	 			$.getJSON("/boards/"+ boardId + "/replies?pageNum=" + page,
 					function(data) {
	 					
	 					if (callback) {
	 						// callback(data); // 댓글 목록만 가져옴
	 						callback(data.replyCnt, data.list); // 댓글 숫자와 댓글 목록 가져옴
	 					}
	 			}).fail(function(xhr, status, err){
	 				
	 				if(error) {
	 					error();
	 				}
	 			});
	 				
 			}


	 		/**
	 		 * 댓글 삭제
	 		 * @param param    : Ajax통신에 쓸 파라미터 모아놓은 객체(게시글번호, 댓글 번호) 
	 		 * @param callback : 요청 성공시 호출용 함수
	 		 * @param error    : 요청 에러 발생시 호출용 함수 (테스트에 구현 해놓음 : 단순 alert 함수)
	 		 */	 			 		
	 		function remove(param, callback, error) {
	 			
	 			console.log("remove ready...");
	 			
	 			var boardId = param.boardId;
	 			var replyId = param.replyId;
	 			
	 			$.ajax({
	 				type : "delete",
	 				url  : "/boards/" + boardId + "/replies/" + replyId,
	 				success : function(deleteResult, status, xhr) {

	 					if (callback) {
	 						callback(deleteResult);
	 					}
	 				},
	 				error   : function(xhr,status,err) {

	 					if (error) {
	 						error(err);
	 					}
	 				} 
	 			});
	 		}
	 		
	 		
	 		/**
	 		 * 댓글 조회
	 		 * @param param    : Ajax통신에 쓸 파라미터 모아놓은 객체(게시글번호, 댓글 번호) 
	 		 * @param callback : 요청 성공시 호출용 함수
	 		 * @param error    : 요청 에러 발생시 호출용 함수 ( 테스트에 구현 : 단순 alert 함수)
	 		 * @method $.get() : Ajax에 get방식으로 전송할 때 사용(코드 단축)
	 		 */	 		 		
	 		function find(param, callback, error) {
	 			
	 			console.log("find ready...");
	 			
	 			var boardId = param.boardId;
	 			var replyId = param.replyId;
	 			
	 			$.get("/boards/" + boardId + "/replies/"+replyId, function(result){
	 				
	 				if (callback) {
	 					callback(result);
	 				}
	 			}).fail(function(xhr, status, err){
	 				
	 				if (error) {
	 					error(err);
	 				}
	 			});
	 		}
	 		
	 		
	 		/**
	 		 * 댓글 수정
	 		 * @param reply    : 댓글 수정에 사용 할 객체(게시글 번호, 댓글 내용, 게시글 내용)
	 		 *                   (Controller에서 @RequestBody로 reply 객체 받는 경우 reply로 작성함, 그 외엔 param)
	 		 * @param callback : 요청 성공시 호출용 함수
	 		 * @param error    : 요청 에러 발생시 호출용 함수 (테스트시 구현 안해놓음)
	 		 */	 		
	 		function edit(reply, callback, error) {
	 			
	 			console.log("edit ready...");
	 			
	 			$.ajax({
	 				type : "patch",
	 				url  : "/boards/" + reply.boardId + "/replies/" + reply.replyId,
	 				data : JSON.stringify(reply),
	 				contentType : "application/json;charset=utf-8",
	 				success : function(result, status, xhr) {
	 					if (callback) {
	 						callback(result);
	 					}
	 				},
	 				error : function(xhr, status, err) {
	 					if (error) {
	 						error(err);
	 					}
	 				}
	 			});
	 		}
	 		
	 		
	 		/**
	 		 * 시간 포맷 바꿔주는 함수
	 		 * 오늘 날짜 - 객체 등록 날짜 (밀리 초로 반환됨)
	 		 * 밀리 초 기준으로 하루 지났는지 비교해서
	 		 * 오늘 등록한 데이터 : 시:분(mm):초 , 하루 지난 데이터 : 년/월(MM)/일 출력
	 		 * 배열을 활용해 인덱스마다 (숫자, :, / ) 넣고 join('') 메서드로 문자열로 변환  
	 		 */
	 		function displayTime(timeValue) {
	 			
	 			var today = new Date();
	 			var gap = today.getTime() - timeValue;
	 			
	 			var dateObj = new Date(timeValue);
	 			var str = "";
	 			
	 			
	 			if (gap < (1000 * 60 * 60 * 24)) {
	 				
	 				var hh = dateObj.getHours();
	 				var mm = dateObj.getMinutes();
	 				var ss = dateObj.getSeconds();
	 				
	 				// 01 ~ 09 표현하기위한 조건 연산자
	 				return [ (hh > 9 ? '' : '0') + hh, ':', (mm > 9 ? '' : '0') + mm, ':', (ss > 9 ? '' : '0') + ss ].join('');
	 			} else {
	 				
	 				var yy = dateObj.getFullYear();
	 				var MM = dateObj.getMonth() + 1; // getMonth() is zero-based
	 				var dd = dateObj.getDate();
	 				
	 				// 01 ~ 09 표현하기위한 조건 연산자
	 				return [ yy, '/', (MM > 9 ? '' : '0') + MM, '/', (dd > 9 ? '' : '0') + dd ].join(''); 
	 			}
	 			
	 		}
	 		
	 		return { 
	 			add: add, 
				findAll : findAll,
				find : find,
				remove : remove,
				edit : edit,
				displayTime : displayTime
			};
	 	})();
	 	
	 	// 콘솔창에 객체 확인용
		$(document).ready(function(){
			
			console.log("replyService : ", replyService);
		});
	
 
 