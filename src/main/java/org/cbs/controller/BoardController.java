package org.cbs.controller;


import java.util.List;

import org.cbs.domain.Board;
import org.cbs.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String boards(Model model) {
		
		List<Board> boards = boardService.findAll();
		model.addAttribute("boards", boards);
		return "board/list";
	}
	
	@GetMapping("/{id}")
	public String board(@PathVariable Long id, Model model) {
		
		Board board = boardService.find(id);
		model.addAttribute("board", board);
		return "board/detail";
	}
	
	
	
	@GetMapping("/add")
	public String addForm(Model model) {
		
		List<Board> boards = boardService.findAll();
		model.addAttribute("boards", boards);
		return "board/addForm";
	}
	
	@PostMapping("/add")
	public String addBoard(Board board, RedirectAttributes redirectAttributes) {
		
		boardService.register(board);
		redirectAttributes.addFlashAttribute("result", board.getId());
		return "redirect:/boards";
	}
	
	@GetMapping("/{id}/edit")
	public String editForm(@PathVariable Long id, Model model) {
		
		Board board = boardService.find(id);
		model.addAttribute("board", board);
		return "board/editForm";
	}
	
	@PostMapping("/{id}/edit")
	public String editBoard(@PathVariable Long id, Board board, RedirectAttributes redirectAttributes) {
		 
		if(boardService.edit(board)) {
			redirectAttributes.addFlashAttribute("result", "updateSuccess");
			redirectAttributes.addFlashAttribute("id", id);
		}
		
		return "redirect:/boards";
	}
	
	@PostMapping("/{id}/delete")
	public String deleteBoard(@PathVariable Long id, Board board, RedirectAttributes redirectAttributes) {
		// 유림짱  
		if(boardService.remove(id)) {
			redirectAttributes.addFlashAttribute("result", "deleteSuccess");
			redirectAttributes.addFlashAttribute("id", id);
		}
		
		return "redirect:/boards";
	}
}
