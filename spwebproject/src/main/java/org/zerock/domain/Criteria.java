package org.zerock.domain;

//Criteria : 검색 기준, 분류 기준
//파라미터가 여러 개로 늘어나면 관리가 어려워져 클래스를 따로 만들어 객체로 처리하는 것이 바람직하다.
public class Criteria {
	private int page; //현재 조회하는 페이지의 번호
	private int perPageNum; //한 페이지당 출력하는 데이터의 갯수
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public void setPage(int page) {
		if(page<=0) {
			this.page=1;
			return;
		}
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <=0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getPage() {
		return page;
	}
	
	//method for MyBatis SQL Mapper
	public int getPageStart() {
		//시작 데이터 번호 = (페이지번호-1) * 페이지 당 보여지는 개수
		return (this.page-1)*perPageNum;
	}
	
	//method for MyBatis SQL Mapper
	public int getPerPageNum() {
		return perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	

	
	
}
