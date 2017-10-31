package org.zerock.domain;

import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

//페이지 처리용 클래스 설계
public class PageMaker {
	//전체 데이터의 개수, DB에서 계산되는 데이터
	private int totalCount; 	
	
	//계산을 통해 만들어지는 데이터
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	//화면에 보여지는 페이지 번호의 수 if 페이지 번호를 5개만 보여주고 싶다면 5로 변경
	private int displayPageNum=10; 
	private Criteria cri;
	
	public void setCri(Criteria cri) {
		this.cri=cri;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount=totalCount;
		
		calcData();
	}
	
	private void calcData() {
						// endPage = (현재 페이지 번호 / 페이지 번호의 수) 페이지 번호의 수 
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum) * displayPageNum);
						// startPage = (마지막페이지번호 - 패이지 번호의 수) + 1;
		startPage = (endPage - displayPageNum) + 1;
						// tempEndPage = Math.ceil(전체 데이터의 개수 / 한 번에 보여지는 데이터의 수) 올림 함수
		int tempEndPage = (int) (Math.ceil(totalCount/ (double) cri.getPerPageNum()) );
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		//startPage 1이면 트루이기에 false, 아니면 true
		prev = startPage == 1 ? false : true;
		//perPageNum=10, endPage=10, totalCount=101이라면 거짓이기 때문에 next는 true
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
		
	}
	
	//17.10.26 페이지번호(page)와 페이지에서보여지는 데이터의 수(perPageNum)을 전달
	// ex) http://localhost:8081/board/listPage?page=3&perPageNum=20
	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
		return uriComponents.toUriString();
	}
	
	//17.10.27 검색 기능을 위한 메소드 추가, UriComponents를 이용해서 페이징 처라에 필요한 데이터 생성
	public String makeSearch(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria) cri).getSearchType())
				.queryParam("keyword", encoding(((SearchCriteria) cri).getKeyword())).build();
		return uriComponents.toUriString();
	}
	private String encoding(String keyword) {
		if(keyword==null || keyword.trim().length()==0) {
			return "";
		}
		try {
			return URLEncoder.encode(keyword, "UTF-8");
		}catch(Exception e){
			return "";
		}
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public Criteria getCri() {
		return cri;
	}
	public int getTotalCount() {
		return totalCount;
	}
	
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
	
	
	
}
