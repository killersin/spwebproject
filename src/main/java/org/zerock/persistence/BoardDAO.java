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
	//����¡ ó��
	public List<BoardVO> listPage(int page) throws Exception;
	//����¡ ó�� real, cri��ü�� ���޹޾� getPageStart()�� getPerPageNum �޼ҵ带 ���
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	//tbl_board count �޼ҵ�
	public int countPaging(Criteria cri) throws Exception;
	
	//17.10.27 �˻��� dbó���� ���� �޼ҵ� �߰�
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
}
