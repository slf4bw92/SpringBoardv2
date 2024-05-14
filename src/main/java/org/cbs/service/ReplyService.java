package org.cbs.service;


import org.cbs.domain.Criteria;
import org.cbs.domain.Reply;
import org.cbs.domain.ReplyPageDTO;


public interface ReplyService {
	
	/* 댓글 목록(페이징 O) */
	public ReplyPageDTO findAll(Criteria criteria, Long boardId);
	
	/* 댓글 등록 */
	public int register(Reply reply);
	
	/* 댓글 조회 */
	public Reply find(Long replyId);
	
	/* 댓글 수정 */
	public int edit(Reply reply);
	
	/* 댓글 삭제 */
	public int remove(Long replyId);
	
}
