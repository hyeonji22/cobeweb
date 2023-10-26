package org.zerock.controller;

import java.awt.BorderLayout;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardServicelmpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@Log4j
public class BoardController {

	private final BoardServicelmpl service;
	
	@GetMapping("/")
	public String boardList(Model model) {
		
		log.info("boardList..............");
		List<BoardVO> boardList = service.getList();
		
		model.addAttribute("boardList", boardList);
		return "board/list";
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		log.info("board:--------------- "+board);
		
		Long bno = service.register(board);
		log.info("BNO:"+ bno);
		rttr.addFlashAttribute("result",bno);
		
		return "redirect:/";
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		
		int count = service.modify(board);
		if(count == 1) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/";
	}
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		
		int count = service.remove(bno);
		
		if(count == 1) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/";
	}
}
