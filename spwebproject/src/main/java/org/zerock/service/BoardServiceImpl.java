package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{
	@Inject
	private BoardDAO bdao;
	
	@Override
	public void regist(BoardVO board) throws Exception {
		bdao.create(board);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return bdao.read(bno);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		bdao.update(board);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		bdao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return bdao.listAll();
	}
	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return bdao.listCriteria(cri);
	}
	@Override
	public int listCountCriteria(Criteria cri) throws Exception{
		return bdao.countPaging(cri);
	}
	//17.10.27 검색의 db처리를 위한 메소드 추가
	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return bdao.listSearch(cri);
	}
	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception{
		return bdao.listSearchCount(cri);
	}
	
	
}
