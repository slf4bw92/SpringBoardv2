package org.cbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.cbs.domain.Board;
import org.cbs.domain.Criteria;

public interface BoardMapper {
	
	
	//@Select("SELECT * FROM Board WHERE bno > 0") //BoardMapper 인터페이스 테스트용으로 어노테이션 활용	
	
	/* 총 게시글 수 조회 */
	public int selectTotal(Criteria criteria);
	
	/* 게시글 목록 조회(페이징 X) */
	public List<Board> selectAll();
	
	/* 게시글 목록 조회(페이징 처리) */
	public List<Board> selectAllWithPaging(Criteria criteria);
	
	/* 게시글 작성 */
	public int insert(Board board);

	/* 게시글 조회 */
	public Board select(Long boardId);
	
	/* 게시글 삭제 */
	public int delete(Long boardId);
	
	/* 게시글 수정 */
	public int update(Board board);
	
	
}
