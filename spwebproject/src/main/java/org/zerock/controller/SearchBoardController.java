package org.zerock.controller;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.PageMaker;
import org.zerock.domain.SearchCriteria;
import org.zerock.service.BoardService;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Inject
	private BoardService service;
	
	//17.10.27 리스트 조회 메소드 검색까지 가능!
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
	    //logger.info(cri.toString());
	    //model.addAttribute("list", service.listCriteria(cri));
	    model.addAttribute("list", service.listSearch(cri));
	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);

	    //pageMaker.setTotalCount(service.listCountCriteria(cri));
	    pageMaker.setTotalCount(service.listSearchCount(cri));
	    model.addAttribute("pageMaker", pageMaker);
	  }
	//게시판 리스트 조회 기능
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model,
			@ModelAttribute("cri") SearchCriteria cri) throws Exception {
		model.addAttribute(service.read(bno));
	}
	
	//게시물 삭제 처리
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String removePage(@RequestParam("bno") int bno, RedirectAttributes rttr,
			SearchCriteria cri) throws Exception {
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/sboard/list";
	}
	
	//게시물 수정 처리
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPage(@RequestParam("bno") int bno, Model model,
			@ModelAttribute("cri") SearchCriteria cri) throws Exception {
		model.addAttribute(service.read(bno));
	}
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPage(BoardVO board, RedirectAttributes rttr,
						SearchCriteria cri) throws Exception {
		service.modify(board);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		//logger.info(rttr.toString());
		return "redirect:/sboard/list";
	}
	
	//게시물 등록 페이지 처리
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() throws Exception {
		logger.info("register get..................................");
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("register post..................................");
		logger.info(board.toString());
		
		service.regist(board);
		rttr.addFlashAttribute("msg", "SUCCEESS");
		return "redirect:/sboard/list";
	}
	
}
