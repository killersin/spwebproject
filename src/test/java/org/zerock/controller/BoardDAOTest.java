package org.zerock.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.zerock.domain.BoardVO;
import org.zerock.domain.SearchCriteria;
import org.zerock.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDAOTest {
	@Inject
	private BoardDAO bdao;
	
	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	/* read test
	 * @Test
	public void testRead() throws Exception{
		logger.info(bdao.read(1).toString());
	}*/
	
	/* update test
	 * @Test
	public void testUpdate() throws Exception{
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("������ ���Դϴ�.");
		board.setContent("������ ���� �׽�Ʈ");
		bdao.update(board);
	}*/
	
	/* delete test
	 * @Test
	public void testDelete() throws Exception{
		bdao.delete(1);
	}*/
	
	/* listPage test
	 * @Test
	public void testListPage() throws Exception{
		int page = 3;
		List<BoardVO> list = bdao.listPage(page);
		for(BoardVO bvo : list) {
			logger.info(bvo.getBno()+" : "+ bvo.getTitle());
		}
	}*/
	/* listPage cri test
	 * @Test
	public void testListPage() throws Exception{
		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setPerPageNum(20);
		List<BoardVO> list = bdao.listCriteria(cri);
		for(BoardVO bvo : list) {
			logger.info(bvo.getBno()+" : "+ bvo.getTitle());
		}
	}*/
	
	/*	UriComponentsBuilder, URI�ۼ��� ����
	 * @Test
	public void testURI() throws Exception{
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.path("/board/read")
				.queryParam("bno", 12) //get����� ? �ڿ� �ٴ� ������
				.queryParam("perPageNum", 20)
				.build();
		
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}*/
	/*	
	 * @Test
	public void testURI2() throws Exception{
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.path("{module}/{page}")
				.queryParam("bno", 12) //get����� ? �ڿ� �ٴ� ������
				.queryParam("perPageNum", 20)
				.build()
				.expand("board", "read")
				.encode();
		
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}*/
	
	@Test
	public void testDynamic1() throws Exception{
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(1);
		cri.setKeyword("������");
		cri.setSearchType("t");
		logger.info("==========================");
		List<BoardVO> list = bdao.listSearch(cri);
		for(BoardVO bvo : list) {
			logger.info(bvo.getBno()+" : "+ bvo.getTitle());
		}
		logger.info("==========================");
		logger.info("COUNT:"+bdao.listSearchCount(cri));
	}
	
}//class end
