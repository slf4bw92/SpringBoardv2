package org.cbs.service.impl;

import java.util.List;

import org.cbs.domain.Board;
import org.cbs.mapper.BoardMapper;
import org.cbs.service.BoardService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper boardMapper;
	
	@Override
	public List<Board> findAll() {
		
		return boardMapper.selectAll();
	}

	@Override
	public int register(Board board) {
		
		return boardMapper.insert(board);
	}

	@Override
	public Board find(Long id) {

		return boardMapper.select(id);
	}

	@Override
	public boolean edit(Board board) {
		
		return boardMapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long id) {

		return boardMapper.delete(id) == 1;
	}
	
}
