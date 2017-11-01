package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.persistence.BoardDAO;
import org.zerock.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Inject
	private ReplyDAO dao;
	
	@Inject
	private BoardDAO bdao;
	
	@Transactional
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		dao.create(vo);
		bdao.updateReplyCnt(vo.getBno(), 1); //댓글이 추가되면 +1
	}
	@Transactional
	@Override
	public void removeReply(Integer rno) throws Exception {
		int bno = dao.getBno(rno);
		dao.delete(rno);
		bdao.updateReplyCnt(bno, -1); //댓글이 사라지면 -1
	}
	@Override
	public List<ReplyVO> listReply(Integer bno) throws Exception {
		return dao.list(bno);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		dao.update(vo);
	}

	

	@Override
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
		return dao.listPage(bno, cri);
	}
	@Override
	public int count(Integer bno) throws Exception {
		return dao.count(bno);
	}
	
}
