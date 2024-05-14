package org.cbs.service.impl;


import org.cbs.domain.Criteria;
import org.cbs.domain.Reply;
import org.cbs.domain.ReplyPageDTO;
import org.cbs.mapper.BoardMapper;
import org.cbs.mapper.ReplyMapper;
import org.cbs.service.ReplyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	
	private final ReplyMapper replyMapper; 
	private final BoardMapper boardMapper;
	
	@Override
	public ReplyPageDTO findAll(Criteria criteria, Long boardId) {
		
		log.info("findAll... criteria : {}", criteria);
		log.info("findAll... boardId : {}", boardId);
		
		//댓글 페이징 처리용 DTO
		ReplyPageDTO replyPageDTO = new ReplyPageDTO();
		//DTO에 전체 댓글 수 담기 (총 몇 페이지인지 계산하기 위해 필요 )
		replyPageDTO.setReplyCnt(replyMapper.selectTotalByBoardId(boardId));
		//DTO에 페이징 처리된 댓글 목록 담기
		replyPageDTO.setList(replyMapper.selectAllWithPaging(criteria, boardId));	
		
		return replyPageDTO;
	}
	
	@Transactional
	@Override
	public int register(Reply reply) {

		log.info("register... reply : {}", reply);
		
		boardMapper.updateReplyCnt(reply.getBoardId(), 1);
		return replyMapper.insert(reply);
	}

	@Override
	public Reply find(Long replyId) {

		log.info("find... replyId : {}", replyId);
		
		return replyMapper.select(replyId);
	}

	@Override
	public int edit(Reply reply) {

		log.info("edit... reply : {}", reply);
		
		return replyMapper.update(reply);
	}
	
	@Transactional
	@Override
	public int remove(Long replyId) {

		log.info("remove replyId : {}", replyId);
		
		// boardId를 찾기위해 reply 객체 가져옴
		Reply reply = replyMapper.select(replyId); 
		
		boardMapper.updateReplyCnt(reply.getBoardId(), -1);
		return replyMapper.delete(replyId);
	}

}
