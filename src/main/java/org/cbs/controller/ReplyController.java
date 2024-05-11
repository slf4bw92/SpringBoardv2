package org.cbs.controller;

import java.util.List;

import org.cbs.domain.Criteria;
import org.cbs.domain.Reply;
import org.cbs.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards/{boardId}/replies")
public class ReplyController {
	
	private final ReplyService replyService;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE )
	public List<Reply> replies( @PathVariable Long boardId, @ModelAttribute Criteria criteria ) {
											   
		log.info("replies...  criteria : {} ", criteria);
		log.info("replies...  boardId : {} " , boardId);
		
		List<Reply> replyList = replyService.findAll(criteria, boardId);
		
		return replyList;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addReply(@PathVariable Long boardId, @RequestBody Reply reply) {
		
		log.info("addReply... boardId : {} ", boardId);
		log.info("addReply... Reply : {} ", reply);
		
		// 댓글 등록시 게시글번호(boardId)가 필요하므로 경로변수로 받은 boardId를 reply 객체에 넣어줌
		reply.setBoardId(boardId);
		
		int registerResult = replyService.register(reply);
		
		return registerResult == 1 
				? new ResponseEntity<>("success", HttpStatus.OK) 
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{replyId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Reply reply(@PathVariable Long replyId) {
		
		log.info("reply ... boardId : {}", replyId);
		
		Reply reply = replyService.find(replyId);
		
		return reply;
	}
	
	@PatchMapping(value = "/{replyId}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> editReply( @PathVariable Long replyId, @RequestBody Reply reply) {
		
		log.info("editReply... replyId : {}", replyId);
		log.info("editReply... reply : {}"  , reply);
		
		// @RequestBody로 만든 Reply 객체에는, @PathVariable로 받은값 자동으로 들어가지 않으므로 넣어줘야함
		reply.setReplyId(replyId);
		int editResult = replyService.edit(reply);
		
		return editResult == 1 
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value = "/{replyId}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> removeReply(@PathVariable Long replyId){
		
		log.info("removeReply... replyId : {} ", replyId );
		
		int removeResult = replyService.remove(replyId);
		
		return removeResult == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	
	
}
