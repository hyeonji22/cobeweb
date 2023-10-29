package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	private final BoardService service;
	
	//목록
//	@GetMapping("/list")
//	public void list(Model model) {
//		
//		log.info("list .......................");
//		model.addAttribute("list", service.getList());
//	}
	//목록 페이징처리
	@GetMapping("/list")
	public void list(Criteria cri,Model model) {
		log.info("---------cri-------------------");
		log.info(cri);
		log.info("list .......................");
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
	}
	//글등록페이지
	@GetMapping("/register")
	public void registreGet() {
		
	}
	//글등록
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		log.info("board:--------------- "+board);
		
		Long bno = service.register(board);
		log.info("BNO:"+ bno);
		rttr.addFlashAttribute("result",bno); //한번만 사용할때 사용 addFlashAttribute
		
		return "redirect:/board/list";
	}
	//상세보기 페이지  ,수정페이지  -->수정할때도 페이지 필요 
	@GetMapping({"/get" , "/modify"}) //두개 같이 처리해줄수 있음 
	public void get(@RequestParam("bno")Long bno , @ModelAttribute("cri")Criteria cri ,Model model) {
		model.addAttribute("board", service.get(bno));
		}
	
	//글수정
	@PostMapping("/modify")
	public String modify(BoardVO board,Criteria cri, RedirectAttributes rttr) {
		
		int count = service.modify(board);
		if(count == 1) {
			rttr.addFlashAttribute("result","success"); 
		}
		//글 수정시에도 페이징 같이감 
		rttr.addAttribute("pageNum" ,cri.getPageNum()); 
		rttr.addAttribute("amount" ,cri.getAmount()); 
		
		return "redirect:/board/list";
	}
	//글삭제  
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno,Criteria cri, RedirectAttributes rttr) {
		
		int count = service.remove(bno);
		
		if(count == 1) {
			rttr.addFlashAttribute("result","success");
		}
		//글 수정시에도 페이징 같이감 
		rttr.addAttribute("pageNum" ,cri.getPageNum()); 
		rttr.addAttribute("amount" ,cri.getAmount()); 
		
		return "redirect:/board/list";
	}
}
