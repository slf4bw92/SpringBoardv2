package org.cbs.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.cbs.domain.Board;
import org.cbs.domain.Criteria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisplayName("게시판 Service 테스트")
@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceTest {
	
	@Autowired
	private BoardService boardService;
	
	
	/**
	 * 게시글 등록 테스트
	 * given : 게시글 등록용 객체 생성(요청 데이터)
	 * when  : 게시글 등록, 게시글 번호로 DB에서 게시글 가져옴
	 * then  : 1개의 데이터가 입력됐는지 테스트
	 *         조회된 게시글과 게시글 등록용 객체의 id와 제목이 일치하는지 테스트    
	 */
	@Test
	@DisplayName("게시글 등록")
	void register() {
		
		//given
		Board board = new Board();
		board.setTitle("서비스 게시글 테스트 제목");
		board.setContent("서비스 게시글 테스트 내용");
		board.setWriter("작성자");
		
		//when
		int result = boardService.register(board);
		Board findBoard = boardService.find(board.getBoardId());
		
		//then
		assertThat(result).isEqualTo(1);
		assertThat(findBoard.getBoardId()).isEqualTo(board.getBoardId());
		assertThat(findBoard.getTitle()).isEqualTo(board.getTitle());
		
	}
	
	@Test	
	@DisplayName("게시글 목록")
	void findAll() {
		
		//when
		List<Board> boardList = boardService.findAll();
		
		//then
		//조회된 게시글 개수가  DB에 저장된 게시글 개수(19)와 일치하는지 테스트
		assertThat(boardList.size()).isEqualTo(19);
		
	}
	
	@Test
	@DisplayName("게시글 수정")
	void edit() {
		//given
		Board board = new Board();
		board.setBoardId(10L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("수정된 작성자");
		
		//when
		boolean result = boardService.edit(board);
		Board findBoard = boardService.find(board.getBoardId());
		
		//then
		assertThat(result).isEqualTo(true);
		assertThat(findBoard.getTitle()).isEqualTo(board.getTitle());
		assertThat(findBoard.getContent()).isEqualTo(board.getContent());
		
	}
	
	@Test
	@DisplayName("게시글 삭제")
	void remove() {
		//given
		Long boardId = 2L;
		
		//when
		boolean result = boardService.remove(boardId);
		
		//then
		assertThat(result).isEqualTo(true);
	}
	
	@Test
	@DisplayName("게시글 목록 조회(페이징 O)")
	void findAllWithPaging() {
		// given
		// 현재 2페이지에 페이지당 데이터 수 10개
		Criteria criteria = new Criteria(2, 10);
		
		// when
		List<Board> boardList = boardService.findAll(criteria);
		
		// then
		assertThat(boardList.size()).isEqualTo(10);
		boardList.forEach(board -> log.info("{}", board));
	}
	
}
