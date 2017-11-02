package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{
	@Inject
	private BoardDAO bdao;
	
	@Transactional
	@Override
	public void regist(BoardVO board) throws Exception {
		bdao.create(board);
		String[] files = board.getFiles();
		if(files==null) { return; }
		for(String fileName : files) {
			bdao.addAttach(fileName);
		}
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(Integer bno) throws Exception {
		bdao.updateViewCnt(bno);
		return bdao.read(bno);
	}
	
	@Transactional //세가지 작업이 동시에 이루어져야 함
	@Override
	public void modify(BoardVO board) throws Exception {
		System.out.println("!!!!!시작 보드 게시글 수정 시작!!!!");
		bdao.update(board); //원래 게시물 수정
		System.out.println("!!!!!!!원래 게시물 수정 성공!!!!!!");
		Integer bno = board.getBno();
		bdao.deleteAttach(bno); //기존 첨부파일 목록 삭제
		System.out.println("!!!!!!!기존 첨부파일 목록 삭제 성공!!!!!!");
		String[] files = board.getFiles();
		if(files==null) { return; }
		for(String fileName : files) {
			bdao.replaceAttach(fileName, bno); // 새로운 첨부 파일 정보 입력
			System.out.println("!!!!!!!새로운 첨부파일 정보 입력 성공!!!!!!");
		}
	}
	
	@Transactional
	@Override
	public void remove(Integer bno) throws Exception {
		bdao.deleteAttach(bno); //tbl_attach가 tbl_board를 참조하기 떄문에 
		bdao.delete(bno); //첨부파일과 관련된 정보를 삭제하고 게시글을 삭제해야한다.
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

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return bdao.getAttach(bno);
	}
	
	
}
