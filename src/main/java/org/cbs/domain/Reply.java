package org.cbs.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Reply {
	
	private Long replyId;			//댓글 번호(PK)
	private Long boardId;			//게시글 번호(FK) : Board 테이블 참조
	private String replyContent;	//댓글 내용
	private String replyer;			//댓글 작성자
	private Date regDate;			//댓글 등록일
	private Date updateDate;		//댓글 수정일
	
}
