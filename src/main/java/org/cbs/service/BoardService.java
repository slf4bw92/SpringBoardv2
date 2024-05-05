package org.cbs.service;

import java.util.List;

import org.cbs.domain.Board;

public interface BoardService {
	
	/* 게시글 목록 조회 */
	public List<Board> findAll();
	
	/* 게시글 등록 */
	public int register(Board board);
	
	/* 게시글 조회 */
	public Board find(Long id);
	
	/* 게시글 수정 */
	public boolean edit(Board board);
	
	/* 게시글 삭제 */
	public boolean remove(Long id);
}
