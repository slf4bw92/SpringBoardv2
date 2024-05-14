package org.cbs.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cbs.domain.Board;
import org.cbs.domain.Criteria;

public interface BoardService {
	
	/* 총 게시글 수 조회 */
	public int getTotal(Criteria criteria);
	
	/* 게시글 목록 조회(페이징 X) */
	public List<Board> findAll();
	
	/* 게시글 목록 조회(페이징 O) */
	public List<Board> findAll(Criteria criteria);
	
	/* 게시글 등록 */
	public int register(Board board);
	
	/* 게시글 조회 */
	public Board find(Long boardId);
	
	/* 게시글 수정 */
	public boolean edit(Board board);
	
	/* 게시글 삭제 */
	public boolean remove(Long boardId);
	

}
