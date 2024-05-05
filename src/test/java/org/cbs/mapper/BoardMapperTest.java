package org.cbs.mapper;


import static org.assertj.core.api.Assertions.*;
import java.util.List;
import org.cbs.domain.Board;
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
@DisplayName("게시판 Mapper 테스트")
public class BoardMapperTest {

	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	@DisplayName("게시글 목록")
	void selectAll() {
		// when
		List<Board> boardList = boardMapper.selectAll();
		
		// then
		boardList.forEach(board -> log.info("{}", board));
	}

	@Test
	@DisplayName("게시글 입력") 
	void insert() { 
	    // given 
		// 입력용 Board 객체(= 요청 데이터)
		Board board = new Board();
		board.setTitle("새로 작성하는 글"); 
		board.setContent("새로 작성한 내용");
		board.setWriter("test User");
	  
	    // when 
		int result = boardMapper.insert(board);
		
	    // then 
		assertThat(result).isEqualTo(1);
		
		
	}
	
	@Test
	@DisplayName("게시글 조회") 
	void select() {
		//when
		Board board = boardMapper.select(11L);
		
		//then
		log.info("11번 게시글 조회 : {}", board);

	}
	
	@Test
	@DisplayName("게시글 업데이트") 
	void update() {
		//given 
		//업데이트용 Board 객체(= 요청 데이터)
		Board board = new Board();
		board.setId(6L);
		board.setTitle("수정된 제목6");
		board.setContent("수정된 내용6");
		board.setWriter("수정된 작성자6");
		
		//when
		// 6번 게시글 업데이트
		int result = boardMapper.update(board);
		// 6번 게시글 조회
		Board selectedBoard =  boardMapper.select(6L);
		
		//then
		//업데이트 된 데이터 개수가 1인지 확인
		assertThat(result).isEqualTo(1);
		
		//업데이트 이후 조회한 게시글을 수정용 객체와 비교 
		assertThat(selectedBoard.getContent()).isEqualTo(board.getContent());
		assertThat(selectedBoard.getTitle()).isEqualTo(board.getTitle());
		assertThat(selectedBoard.getWriter()).isEqualTo(board.getWriter());
		
	}
	
	@Test
	@DisplayName("게시글 삭제") 
	void delete() {
		//given
		Long id = 20L;

		//when
		int result = boardMapper.delete(id);
		
		//then
		//삭제된 데이터가 1개인지 확인
		assertThat(result).isEqualTo(1);

	}
	 
}
