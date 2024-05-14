package org.cbs.mapper;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.cbs.domain.Criteria;
import org.cbs.domain.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
@DisplayName("댓글 테스트")
public class ReplyMapperTest {
	
	@Autowired private ReplyMapper replyMapper;
	
	@Test
	@DisplayName("replyMapper 스프링컨테이너에 빈 객체로 생성되는지 테스트")
	void testMapper() {
		log.info("replyMapper : {}", replyMapper);
	}
	
	@Test
	@DisplayName("댓글 등록 테스트")
	void insert() {
		//given
		Reply reply = new Reply();
		reply.setBoardId(313L);
		reply.setReplyer("Replyer01");
		reply.setReplyContent("Reply Test");
			
		//when
		int insertResult = replyMapper.insert(reply);
		Reply findReply = replyMapper.select(reply.getReplyId());
		
		//then
		log.info("insertResult : {}", insertResult);
		assertThat(findReply.getBoardId()).isEqualTo(reply.getBoardId());
		assertThat(findReply.getReplyer()).isEqualTo(reply.getReplyer());
		assertThat(findReply.getReplyContent()).isEqualTo(reply.getReplyContent());
	}
	
	@Test
	@DisplayName("댓글 삭제 테스트")
	void delete() {
		//given
		Long replyId = 14L;
		
		//when
		int deleteResult = replyMapper.delete(replyId);
		
		//then
		assertThat(deleteResult).isEqualTo(1);
	}
	@Test
	@DisplayName("댓글 수정 테스트")
	void update() {
		//given
		Reply reply = new Reply();
		reply.setReplyContent("update unit test");
		reply.setReplyId(12L);
		
		//when
		int updateResult = replyMapper.update(reply);
		Reply findReply = replyMapper.select(reply.getReplyId());
		
		//then
		assertThat(updateResult).isEqualTo(1);
		assertThat(findReply.getReplyContent()).isEqualTo(reply.getReplyContent());
		
	}
	
	@Test
	@DisplayName("특정 게시글에 대한 댓글 목록 테스트(페이징 O)")
	void selectAllWithPaging() {
		//given
		Long boardId = 313L;
		Criteria criteria = new Criteria();
		
		//when
		List<Reply> replyList = replyMapper.selectAllWithPaging(criteria, boardId);
		
		//then
		//댓글 테이블에서 게시글번호가 313인 레코드 수(10)만큼 가져왔는지 테스트 
		assertThat(replyList.size()).isEqualTo(10);
		// 가져온 게시글 출력
		replyList.forEach(reply -> log.info("reply : {}", reply));
	}	
	
	@Test
	@DisplayName("특정 게시글에 대한 댓글 수 테스트")
	void selectTotalByBoardId() {
		//given
		Long boardId = 313L;
		
		//when
		int count = replyMapper.selectTotalByBoardId(boardId);
		
		//then
		log.info("count : {}", count);
	}
	
}	
