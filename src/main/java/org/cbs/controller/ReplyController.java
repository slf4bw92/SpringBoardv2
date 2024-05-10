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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards/{boardId}/replys")
public class ReplyController {
	
	private final ReplyService replyService;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE )
	public List<Reply> replys( @PathVariable Long boardId, @ModelAttribute Criteria criteria ) {
											   
		log.info("replys...  criteria : {} ", criteria);
		log.info("replys...  boardId : {} " , boardId);
		
		List<Reply> replyList = replyService.findAll(criteria, boardId);
		
		return replyList;
	}
	
	@PostMapping(consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addReply(@RequestBody Reply reply) {
		
		log.info("addReply... Reply : {} ", reply);
		
		int registerResult = replyService.register(reply);
		
		return registerResult == 1 
				? new ResponseEntity<>("success", HttpStatus.OK) 
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{replyId}")
	public Reply reply(@PathVariable Long replyId) {
		
		log.info("reply ... boardId : {}", replyId);
		
		Reply reply = replyService.find(replyId);
		
		return reply;
	}
	
	@PatchMapping(value = "/{replyId}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> editReply( @PathVariable Long replyId, @RequestBody Reply reply) {
		
		log.info("editReply... replyId : {}", replyId);
		log.info("editReply... reply : {}"  , reply);
		
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
