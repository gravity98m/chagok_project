package com.chagok.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chagok.domain.BoardVO;
import com.chagok.service.ChallengeService;
import com.chagok.service.UserService;

@Controller
public class BoardController {
	
	private static final Logger mylog = LoggerFactory.getLogger(ChagokController.class);
	
	@Inject
	private ChallengeService service;
	
	@Inject
	private UserService uservice;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	// =================================================================================
	// 후기글 리스트 (b_sort=1)
	// http://localhost:8080/reviewboard
	@GetMapping(value = "/reviewboard")
	public String reviewboardGET(HttpSession session,Model model) throws Exception {
		mylog.debug(" /reviewboard 호출");
		
		List<BoardVO> boardList = service.getBoardList(1);
		
		mylog.debug(boardList+"");
		
		model.addAttribute("boardList", boardList);
		
		return "/community/reviewboard";
	}

	// 후기글 작성 GET
	// http://localhost:8080/review?cno=1
	@GetMapping(value = "/review")
	public String reviewGET(@RequestParam("cno") int cno, Model model, HttpSession session) throws Exception {
	
		mylog.debug(cno + "");

		model.addAttribute("review", service.getChallengeInfo(cno));

		return "/community/review";
	}
	
	// 후기글 작성 POST
	@PostMapping(value = "/review")
	public String reviewPOST(BoardVO vo, RedirectAttributes rttr) throws Exception {
		mylog.debug(" reviewPOST 호출");

		mylog.debug(vo + "");

		service.createReview(vo);

		mylog.debug("게시판 글쓰기 완료");

		rttr.addFlashAttribute("result", "createOK");

		return "redirect:/community/reviewboard";
	}
	
	
	// 후기 게시판 수정 GET
	// http://localhost:8080/challenge/reviewupdate?bno=4
	@GetMapping(value= "/reviewupdate")
	public void reviewupdateGET(@RequestParam("bno") int bno, Model model, HttpSession session) throws Exception{
					
		mylog.debug(" reviewupdate 호출");
			
		List<BoardVO> boardList = service.getBoardList(bno);
			
		mylog.debug(boardList+"");
			
		model.addAttribute("boardList", boardList);
			
		model.addAttribute("vo",service.getBoardList(bno));
					
	}
				
	// 후기 게시판 수정 POST
	@PostMapping(value = "/reviewupdate")
	public String reviewupdatePOST(BoardVO vo,RedirectAttributes rttr) throws Exception{
					
		mylog.debug(vo+"");
			
			
		Integer result = service.updateBoard(vo);
					
		if(result > 0) {
				
			rttr.addFlashAttribute("result", "modOK");
						
		}
							
		return "/community/reviewupdate";
							
	}
		
	// 후기 글 삭제하기
	// http://localhost:8080/reviewremove?bno=4
	@PostMapping(value = "/reviewremove")
	public String reviewremovePOST(int bno,RedirectAttributes rttr) throws Exception{
		mylog.debug(bno+"");
					
		service.deleteBoard(bno);
					
		rttr.addFlashAttribute("result", "delOK");
					
		return "/community/reviewboard";
					
	}
	// =================================================================================
	
	// =================================================================================
	// 공지 글 리스트 (b_sort=2)
	// http://localhost:8080/notice
	@GetMapping(value = "/notice")
	public String noticeGET(Model model,HttpSession session) throws Exception {
			
		List<BoardVO> boardList = service.getBoardList(2);
			
		mylog.debug(boardList+"");
			
		model.addAttribute("boardList", boardList);
			
		return "/community/notice";
	}
	
	// 공지 글 상세
	// http://localhost:8080/noticecontent?bno=4
	@GetMapping(value = "/noticecontent")
	public String noticecontentGET(HttpSession session,Model model,@RequestParam("bno") int bno) throws Exception {
			
		BoardVO vo = service.getBoardContent(bno);
			
		model.addAttribute("vo",vo);
			
		return "/community/noticecontent";
	}
	// 공지 글 작성하기
	// http://localhost:8080/noticewrite
	@GetMapping(value = "/noticewrite")
	public void noticewriteGET() throws Exception {
			
		mylog.debug(" noticewriteGET 호출");
			
	}
		
	// 공지글 작성하기 (post)
	@PostMapping(value = "/noticewrite")
	public String registPOST(BoardVO vo, RedirectAttributes rttr) throws Exception {
			
		mylog.debug(" noticewriteGET -> noticewritePOST 호출 ");
			
		mylog.debug(vo+"");
			
		service.insertBoard(vo);
			
		rttr.addFlashAttribute("result", "createOK");
			
		return "/community/notice";
	}	
	
	// 공지 글 수정하기 GET
	// http://localhost:8080/noticeupdate?bno=4
	@GetMapping(value = "/noticeupdate")
	public String noticeupdateGET(@RequestParam("bno") int bno, Model model, HttpSession session) throws Exception{
		mylog.debug(" reviewupdate 호출");
			
		mylog.debug(bno+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		BoardVO board = service.getBoardContent(bno);
			
		mylog.debug(board+"");
			
		model.addAttribute("board", board);
		
		return "/community/noticeupdate";
			
	}
		
	// 공지 글 수정하기 POST
	@PostMapping(value = "/noticeupdate")
	public String noticeupdatePOST(BoardVO vo,RedirectAttributes rttr) throws Exception {
		mylog.debug(vo+"");
			
			
		Integer result = service.updateBoard(vo);
					
		if(result > 0) {
				
		rttr.addFlashAttribute("result", "modOK");
						
		}
							
		return "/community/notice";
			
	}
	
	// 공지 글 삭제하기
	// http://localhost:8080/noticedelete?bno=
	@PostMapping(value = "/noticedelete")
	public String noticedeleteGET(int bno,RedirectAttributes rttr) throws Exception {
		mylog.debug(bno+"");
			
		service.deleteBoard(bno);
					
		rttr.addFlashAttribute("result", "delOK");
					
		return "/community/notice";
	}
	
	// =================================================================================
	
	// =================================================================================
	// 자유 게시판 (b_sort = 3)
	//  http://localhost:8080/freeboard
	@GetMapping(value = "/freeboard")
	public String FreeBoardGET(HttpSession session,Model model) throws Exception {
		mylog.debug(" /freeboard 호출");
			
		List<BoardVO> boardList = service.getBoardList(3);
			
		mylog.debug(boardList+"");
			
		model.addAttribute("boardList", boardList);
			
		return "/community/freeboard";
	}
	
	// 자유게시판 글 작성 GET
	// http://localhost:8080/challenge/freeboardwrite
	@GetMapping(value = "/freeboardwrite")
	public void freeboardwriteGET() throws Exception {
		
		mylog.debug(" freeboardwriteGET 호출");
		
	}
	
	// 자유게시판 글 작성 POST
	@PostMapping(value = "/freeboardwrite")
	public String freeboardwritePOST(BoardVO vo, RedirectAttributes rttr) throws Exception {
		mylog.debug(" freeboardwritePOST 호출");

		mylog.debug(vo + "");

		service.createReview(vo);

		mylog.debug("자유게시판 글쓰기 완료");

		rttr.addFlashAttribute("result", "createOK");

		return "redirect:/community/freeboard";
	}
	
	// 자유 게시판 삭제
	@GetMapping(value = "/freedelete")
	public String freedeleteGET(int bno,RedirectAttributes rttr) throws Exception {
		mylog.debug(bno+"");
		
		service.deleteBoard(bno);
				
		rttr.addFlashAttribute("result", "delOK");
				
		return "/community/freeboard";
	}
	// =================================================================================
}
