/**
 * Reply Module
 * Ajax 통신을 통해서 댓글관련 비즈니스로직 처리함
 *
 */
 	console.log("Reply Module...");
		 
	 	var replyService = (function(){
	 		
	 		/**
	 		 * 댓글 등록
	 		 * param : reply : 객체, callback : 함수, error : 함수
	 		 */
	 		function add(reply, callback, error) {
	 			
	 			console.log("add reply...");
	 			
	 			$.ajax({
	 				type : "post",
	 				url  : "/boards/${boardId}/replies",
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
	 		
	 		
	 		function findAll(param, callback, error) {
	 			
	 			console.log("findAll ready...");
	 			
	 			var page = param.page || 1;
	 			
	 			$.getJSON("/boards/${boardId}/replies?pageNum=" + page,
 					function(data) {
	 					
	 					if (callback) {
	 						callback(data); // 댓글 목록만 가져옴
	 					}
	 			}).fail(function(xhr, status, err){
	 				if(error) {
	 					error();
	 				}
	 			});
	 				
 			}
	 		
	 		function remove(replyId, callback, error) {
	 			
	 			console.log("remove ready...");
	 			
	 			$.ajax({
	 				type : "delete",
	 				url  : "/boards/${boardId}/replies/" + replyId,
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
	 		
	 		function find(replyId, callback, error) {
	 			
	 			console.log("find ready...");
	 			
	 			$.get("/boards/${boardId}/replies/"+replyId, function(result){
	 				
	 				if (callback) {
	 					callback(result);
	 				}
	 			}).fail(function(xhr, status, err){
	 				
	 				if (error) {
	 					error(err);
	 				}
	 			});
	 		}
	 		
	 		function edit(reply, callback, error) {
	 			
	 			console.log("edit ready...");
	 			
	 			$.ajax({
	 				type : "patch",
	 				url  : "/boards/${boardId}/replies/" + reply.replyId,
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
	 		
	 		return { 
	 			add: add, 
				findAll : findAll,
				find : find,
				remove : remove,
				edit : edit
			};
	 	})();
	 	
	 	// 콘솔창에 객체 확인용
		$(document).ready(function(){
			
			console.log(replyService);
		});
	
 
 