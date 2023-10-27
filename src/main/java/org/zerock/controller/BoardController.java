package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
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
	@GetMapping("/list")
	public void list(Model model) {
		
		log.info("list .......................");
		model.addAttribute("list", service.getList());
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
	//상세보기 페이지  ,수정페이지 
	@GetMapping({"/get" , "/modify"}) //두개 같이 처리해줄수 있음 
	public void get(@RequestParam("bno")Long bno ,Model model) {
		model.addAttribute("board", service.get(bno));
		}
	
	//글수정
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		
		int count = service.modify(board);
		if(count == 1) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
	//글삭제
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		
		int count = service.remove(bno);
		
		if(count == 1) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
}
