package org.cbs.service.impl;

import java.util.List;

import org.cbs.domain.Criteria;
import org.cbs.domain.Reply;
import org.cbs.mapper.ReplyMapper;
import org.cbs.service.ReplyService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	
	private final ReplyMapper replyMapper; 
	
	@Override
	public List<Reply> findAll(Criteria criteria, Long boardId) {
		
		log.info("findAll criteria : {},\n boardId : {}", criteria, boardId);
		
		return replyMapper.selectAllWithPaging(criteria, boardId);
	}

	@Override
	public int register(Reply reply) {

		log.info("register reply : {}", reply);
		
		return replyMapper.insert(reply);
	}

	@Override
	public Reply find(Long replyId) {

		log.info("find replyId : {}", replyId);
		
		return replyMapper.select(replyId);
	}

	@Override
	public int edit(Reply reply) {

		log.info("edit reply : {}", reply);
		
		return replyMapper.update(reply);
	}

	@Override
	public int remove(Long replyId) {

		log.info("remove replyId : {}", replyId);
		
		return replyMapper.delete(replyId);
	}

}
