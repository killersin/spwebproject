package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;

public interface BoardService {
	public void regist(BoardVO board) throws Exception;
	public BoardVO read(Integer bno) throws Exception;
	public void modify(BoardVO board) throws Exception;
	public void remove(Integer bno) throws Exception;
	public List<BoardVO> listAll() throws Exception;
	//페이징 처리 Criteria 클래스 cri객체이용
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	public int listCountCriteria (Criteria cri) throws Exception;
	//17.10.27 검색의 db처리를 위한 메소드 추가
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
}
