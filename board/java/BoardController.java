package sbc.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sbc.board.domain.Board;
import sbc.board.domain.ListResult;
import sbc.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@GetMapping("/boardList.do")
	public ModelAndView boardList(@RequestParam("ps") String psStr, @RequestParam("cp") String cpStr) {
		long ps = getPs(psStr);
		long cp = getCp(cpStr);
		ListResult listResult = boardService.resultSet(ps, cp);
		List<Board> boardList = boardService.boardListS(listResult);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/list");
		mv.addObject("boardList", boardList);
		mv.addObject("listResult", listResult);
		return mv;
	}
	@GetMapping("/index.do")
	public String index() {
		return "index";
	}
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	@PostMapping("/insert.do")
	public String insert(Board board) {
		boardService.insertS(board);
		return "redirect:boardList.do";
	}
	@GetMapping("/delete.do")
	public String delete(long seq) {
		boardService.deleteS(seq);
		return "redirect:boardList.do";
	}
	@GetMapping("/updateList.do")
	public ModelAndView updateList(long seq) {
		Board board = boardService.contentS(seq);
		ModelAndView mv = new ModelAndView("board/update", "board", board);
		return mv;
	}
	@PostMapping("/update.do")
	public String update(Board board) {
		boardService.updateS(board);
		return "redirect:boardList.do";
	}
	@GetMapping("/content.do")
	public ModelAndView content(long seq) {
		Board board = boardService.contentS(seq);
		ModelAndView mv = new ModelAndView("board/content", "board", board);
		return mv;
	}
	private long getPs(String psStr){
		long ps = -1;
		if(psStr != null){
			psStr = psStr.trim();
			if(psStr.length() != 0){
				try{
					ps = Integer.parseInt(psStr);
					return ps;
				}catch(NumberFormatException nfe){
				}
			}
		}
		return ps;
	}
	private long getCp(String cpStr){
		long cp = -1;
		if(cpStr != null){
			cpStr = cpStr.trim();
			if(cpStr.length() != 0){
				try{
					cp = Integer.parseInt(cpStr);
					return cp;
				}catch(NumberFormatException nfe){
				}
			}
		}
		return cp;
	}
}
