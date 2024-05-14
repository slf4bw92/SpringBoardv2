package org.cbs.domain;

import java.util.List;

import lombok.Data;

@Data
public class ReplyPageDTO {
	
	private int replyCnt;
	private List<Reply> list;
}
