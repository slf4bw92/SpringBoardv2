package org.cbs.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	
	private int startPage;			//시작 페이지 번호
	private int endPage;			//끝 페이지 번호
	private boolean prev, next;		//이전, 다음 페이지
	
	private int total;				//총 데이터 수 
	private Criteria criteria;		//페이지 기준 (현재 페이지번호, 한 페이지당 데이터 수) 
	
	public PageDTO(int total, Criteria criteria) {
		
		this.total = total;
		this.criteria = criteria;

		
		/**
		 * 	끝 페이지 번호(endPage)
		 *  화면에 조회되는 끝 페이지 번호 + 시작 페이지 계산 용도
		 *  현재 페이지번호 1~10  -> 끝페이지 10
		 *  현재 페이지번호 11~20 -> 끝페이지 20
		 */
		this.endPage = (int) (Math.ceil((double) criteria.getPageNum() / 10)) * 10;
		
		
		/**
		 * 시작 페이지 번호(startPage)
		 * 화면에 조회되는 시작 페이지 번호
		 * 시작 페이지 = 끝 페이지 - 9 
		 */
		this.startPage = this.endPage - 9;
		
		/**
		 * 	진짜 끝 번호 (realEnd) : 페이지 데이터 수가 10페이지 분량이 안되는경우 
		 *  한 페이지에 10개씩 보여주는경우 전체 데이터수가 80개이면 진짜 끝페이지는 10페이지가아닌 8페이지
		 */
		int realEnd = (int) (Math.ceil((double) total / criteria.getAmount()));
		
		/**
		 *  끝 페이지 번호 교체 로직
		 *  진짜 끝 번호 < 끝 페이지 번호인 경우 끝 페이지 번호를 진짜 끝 번호로 교체 
		 */  
		if ( realEnd < endPage ) {
			this.endPage = realEnd;
		}
		
		/**
		 * 이전 페이지 : 시작페이지가 1 보다 크면 존재
		 * 다음 페이지 : 끝 페이지 번호가 진짜 끝번호 보다 작으면 존재 
		 */
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		
		
	}
	
	
	
	
}
