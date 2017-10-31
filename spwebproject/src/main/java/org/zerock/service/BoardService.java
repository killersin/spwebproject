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
	//����¡ ó�� Criteria Ŭ���� cri��ü�̿�
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	public int listCountCriteria (Criteria cri) throws Exception;
	//17.10.27 �˻��� dbó���� ���� �޼ҵ� �߰�
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
}
