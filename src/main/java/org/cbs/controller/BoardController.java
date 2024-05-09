package org.cbs.controller;


import java.util.List;

import org.cbs.domain.Board;
import org.cbs.domain.Criteria;
import org.cbs.domain.PageDTO;
import org.cbs.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping
	public String boards( @ModelAttribute Criteria criteria, Model model) {
		
		log.info("criteria : {}", criteria);
		List<Board> boards = boardService.findAll(criteria);
		
		int total = boardService.getTotal(criteria);
		log.info("total : {}", total);
		
		PageDTO pageDTO = new PageDTO(total, criteria);
		
		model.addAttribute("boards", boards);
		model.addAttribute("pageMaker", pageDTO);
		return "board/list";
	}
	
	@GetMapping("/{boardId}")
	public String board( @PathVariable Long boardId, 
						 @ModelAttribute Criteria criteria, 
						 Model model) {
		
		Board board = boardService.find(boardId);
		model.addAttribute("board", board);
		return "board/detail";
	}
	
	@GetMapping("/add")
	public String addForm( Model model) {
		
		return "board/addForm";
	}
	
	@PostMapping("/add")
	public String addBoard(@ModelAttribute Board board, RedirectAttributes redirectAttributes) {
		
		boardService.register(board);
		redirectAttributes.addFlashAttribute("result", board.getBoardId());
		return "redirect:/boards";
	}
	
	@GetMapping("/{boardId}/edit")
	public String editForm(	@PathVariable Long boardId, 
							@ModelAttribute Criteria criteria,
							Model model) {
		
		Board board = boardService.find(boardId);
		model.addAttribute("board", board);
		return "board/editForm";
	}
	
	@PostMapping("/{boardId}/edit")
	public String editBoard( @PathVariable Long boardId, 
							 @ModelAttribute Criteria criteria, 
							 @ModelAttribute Board board, 
							 RedirectAttributes redirectAttributes) {
		 
		if(boardService.edit(board)) {
			redirectAttributes.addFlashAttribute("result", "updateSuccess");
			redirectAttributes.addFlashAttribute("boardId", boardId);
		}
		
		return "redirect:/boards" + criteria.getQueryParam();
	}
	
	@PostMapping("/{boardId}/delete")
	public String deleteBoard( @PathVariable Long boardId, 
							   @ModelAttribute Criteria criteria, 
							   @ModelAttribute Board board, 
							   RedirectAttributes redirectAttributes) {

		if(boardService.remove(boardId)) {
			redirectAttributes.addFlashAttribute("result", "deleteSuccess");
			redirectAttributes.addFlashAttribute("boardId", boardId);
		}
		
		
		return "redirect:/boards" + criteria.getQueryParam();
	}
}
