package org.zerock.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.domain.MessageVO;
import org.zerock.persistence.MessageDAO;
import org.zerock.persistence.PointDAO;

@Service
public class MessageServiceImpl implements MessageService{
	@Inject
	private MessageDAO messageDAO;
	
	@Inject
	private PointDAO pointDAO;
	
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		messageDAO.create(vo);
		pointDAO.updatePoint(vo.getSender(), 10); //메시지를 보낸 사람에게 10점을 추가하는 작업
	}

	@Override
	public MessageVO readMessage(String uid, Integer mid) throws Exception {
		messageDAO.updateState(mid); //메시지의 상태가 변경
		pointDAO.updatePoint(uid, 5); //메시지를 본 사람의 포인트는 5점 증가
		return messageDAO.readMessage(mid); //모든 메시지를 조회하여 반환
	}
	
}
