package org.cbs.service;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.cbs.domain.Criteria;
import org.cbs.domain.Reply;
import org.cbs.domain.ReplyPageDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@DisplayName("댓글 서비스 테스트")
public class ReplyServiceTest {
	
	@Autowired ReplyService replyService;
	
	@Test
	@DisplayName("게시글에 대한 댓글 목록 테스트(페이징 X)")
	void findAll() {
		//given
		Criteria criteria = new Criteria(3,10);
		Long boardId = 313L;
		
		//when
		ReplyPageDTO replyPageDTO = replyService.findAll(criteria, boardId);
		
		//then
		log.info("313번 게시글의 댓글 수 {} " , replyPageDTO.getReplyCnt());
		replyPageDTO.getList().forEach(reply -> log.info("reply : {}", reply));
	}

	@Test
	@DisplayName("댓글 등록 테스트")
	void register() {
		//given
		Reply reply = new Reply();
		reply.setBoardId(313L);
		reply.setReplyContent("ReplyServiceTest");
		reply.setReplyer("testUser01");
		
		//when
		int registerResult = replyService.register(reply);
		Reply findReply = replyService.find(reply.getReplyId());
		
		//then	
		assertThat(registerResult).isEqualTo(1);
		assertThat(findReply.getBoardId()).isEqualTo(reply.getBoardId());
		assertThat(findReply.getReplyContent()).isEqualTo(reply.getReplyContent());
		assertThat(findReply.getReplyer()).isEqualTo(reply.getReplyer());
	}
	
	@Test
	@DisplayName("댓글 수정 테스트")
	void edit() {
		//given
		Reply reply = new Reply();
		reply.setReplyId(16L);
		reply.setReplyContent("ReplyService edit Test");
		
		//when
		int editResult = replyService.edit(reply);
		
		//then
		assertThat(editResult).isEqualTo(1);
	}
	
	@Test
	@DisplayName("댓글 삭제 테스트")
	void remove() {
		//given
		Long replyId = 15L;
		
		//when
		int removeResult = replyService.remove(replyId);
		
		//then		
		assertThat(removeResult).isEqualTo(1);
	}
	
}
