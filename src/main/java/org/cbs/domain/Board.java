package org.cbs.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
	
	private Long boardId;		// 게시판 번호(PK)
	private String title;		// 게시판 제목
	private String content;		// 내용
	private String writer;		// 글쓴이
	private Date regDate;		// 게시글 등록일
	private Date updateDate;	// 게시글 수정일
	
}
