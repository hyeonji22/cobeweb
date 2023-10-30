package org.zerock.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Criteria {

	private int pageNum; //페이지번호
	private int amount;  //한페이지당 보여줄 게시물 갯수 
	
	//검색
	private String keyword;
	private String type; //t ,tx,tcw , cw

	public Criteria() {
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null? new String[] {} : type.split("");
	}
	

}
