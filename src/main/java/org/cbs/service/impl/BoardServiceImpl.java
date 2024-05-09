package org.cbs.service.impl;

import java.util.List;

import org.cbs.domain.Board;
import org.cbs.domain.Criteria;
import org.cbs.mapper.BoardMapper;
import org.cbs.service.BoardService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper boardMapper;
	

	@Override
	public int getTotal(Criteria criteria) {
		
		return boardMapper.selectTotal(criteria);
	}
	
	@Override
	public List<Board> findAll() { 
		
		return boardMapper.selectAll();
	}

	@Override
	public List<Board> findAll(Criteria criteria) {

		return boardMapper.selectAllWithPaging(criteria);
	}
	
	@Override
	public int register(Board board) {
		
		return boardMapper.insert(board);
	}

	@Override
	public Board find(Long boardId) {

		return boardMapper.select(boardId);
	}

	@Override
	public boolean edit(Board board) {
		
		return boardMapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long boardId) {

		return boardMapper.delete(boardId) == 1;
	}

	
}
