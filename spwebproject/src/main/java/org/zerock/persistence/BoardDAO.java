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
	
	//17.11.1 댓글 카운트 변경을 위한 메소드
	public void updateReplyCnt(Integer bno, int amount) throws Exception;
	public void updateViewCnt(Integer bno) throws Exception;
	public void addAttach(String fullName) throws Exception; //첨부 파일 정보를 저장하는 기능
	public List<String> getAttach(Integer bno) throws Exception; //특정 게시물의 첨부 파일을 순서대로 가져오는 메소드
	
	//17.11.2 기존 업로드한 모든 파일의 정보는 삭제하고, 새롭게 첨부파일과 관련된 정보를 넣는 방법의 메소드
	public void deleteAttach(Integer bno) throws Exception;
	public void replaceAttach(String fullName, Integer bno) throws Exception;
}
