package org.cbs.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/* 검색, 페이지 기준 클래스 */
@Getter
@Setter
@ToString
public class Criteria {
	
	/* 페이지 기준 */
	private int pageNum;	// 페이지 번호
	private int amount;		// 페이지 당 데이터 수
	
	/* 검색 기준 */
	private String type;	// 검색 타입(제목T, 내용C, 작성자W)
	private String keyword; // 검색 키워드

	public Criteria() {
		this(1,10);
	}	
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	/**
	 * 검색 타입 처리 메서드
	 * 검색타입이 빈 경우 빈배열 만들어주고
	 * 다중 검색시("TCW") 문자열을 한자한자 분할해서 배열처리({"T", "C", "W"})
	 * 
	 */
	public String[] getTypeArr() {
		
		return type == null ? new String[] {} : type.split("");
	}
	
	/**
	 * 쿼리 파라미터 관리 메서드
	 * 스프링에서 제공하는 URI 관리용 클래스인 UriComponentsBuilder 활용
	 * 쿼리 파라미터만 가지고 다니기 위해서 경로(path)를 빈문자("") 처리해서 제거후 사용
	 * 반환할 때 URI형태의 문자열로 변환해서 반환
	 * 
	 */
	public String getQueryParam() {
		
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount",  this.amount)
				.queryParam("type",    this.type)
				.queryParam("keyword", this.keyword);
		
		System.out.println("queryString: " + builder.toUriString());
		return builder.toUriString();
														   
	}
	
}
