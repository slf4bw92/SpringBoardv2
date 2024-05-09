package org.cbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cbs.domain.Criteria;
import org.cbs.domain.Reply;

public interface ReplyMapper {
	
	
	/* 댓글 등록 */
	public int insert(Reply reply);
	
	/* 특정 댓글 조회 */
	public Reply select(Long replyId);
	
	/* 댓글 삭제 */
	public int delete(Long replyId);
	
	/* 댓글 수정 */
	public int update(Reply reply);
	
	/**  
	 * 특정 게시글의 댓글 목록(페이징 처리 포함)
	 * @Param : 마이바티스 애노테이션
	 *   1) 용도 : mapper.xml 에서 parameter 여러개 받아서 쓰고싶을 때 사용  
	 *   2) 파라미터명 생략 불가 
	 */
	public List<Reply> selectAllWithPaging(@Param("criteria") Criteria criteria, @Param("boardId") Long boardId);
}
