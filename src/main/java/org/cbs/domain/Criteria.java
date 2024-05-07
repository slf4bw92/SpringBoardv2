package org.cbs.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/* 검색, 페이지 기준 클래스 */
@Getter
@Setter
@ToString
public class Criteria {
	
	private int pageNum;	// 페이지 번호
	private int amount;		// 페이지 당 데이터 수
	

	public Criteria() {
		this(1,10);
	}	
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	
	
}
