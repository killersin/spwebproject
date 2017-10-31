package org.zerock.persistence;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;

public interface BoardDAO {
	public void create(BoardVO bvo) throws Exception;
	public BoardVO read(Integer bno) throws Exception;
	public void update(BoardVO bvo) throws Exception;
	public void delete(Integer bno) throws Exception;
	public List<BoardVO> listAll() throws Exception;
	//페이징 처리
	public List<BoardVO> listPage(int page) throws Exception;
	//페이징 처리 real, cri객체를 전달받아 getPageStart()와 getPerPageNum 메소드를 사용
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	//tbl_board count 메소드
	public int countPaging(Criteria cri) throws Exception;
	
	//17.10.27 검색의 db처리를 위한 메소드 추가
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
}
