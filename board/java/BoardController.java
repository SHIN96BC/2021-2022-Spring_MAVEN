package sbc.board.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sbc.board.domain.Board;
import sbc.board.domain.ListResult;
import sbc.board.domain.SerachResult;
import sbc.board.service.BoardService;
import static sbc.board.consts.BoardConst.*;
import static sbc.file.fileset.FilePath.FILE_STORE;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@GetMapping("boardList.do")
	public ModelAndView boardList(@RequestParam(value="ps", required=false, defaultValue=BOARD_PS+"") long ps, @RequestParam(value="cp", required=false, defaultValue=BOARD_CP+"") long cp) {
		ListResult listResult = boardService.resultSet(ps, cp);
		List<Board> boardList = boardService.boardListS(listResult);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/list");
		mv.addObject("boardList", boardList);
		mv.addObject("listResult", listResult);
		return mv;
	}
	@GetMapping("index.do")
	public String index() {
		return "index";
	}
	@GetMapping("write.do")
	public String write() {
		return "board/write";
	}
	@PostMapping("insert.do")
	public ModelAndView insert(Board board, MultipartFile file) {
		
		Board newBoard = boardService.saveStore(file, board);
		boolean flag = false;
		if(newBoard != null) {
			boardService.write(newBoard);
			flag = true;
		}
		
		return new ModelAndView("board/insert", "flag", flag);
	}
	@GetMapping("delete.do")
	public String delete(long seq) {
		boardService.remove(seq);
		return "redirect:boardList.do";
	}
	@GetMapping("updateList.do")
	public ModelAndView updateList(long seq) {
		Board board = boardService.contentS(seq);
		ModelAndView mv = new ModelAndView("board/update", "board", board);
		return mv;
	}
	@PostMapping("update.do")
	public String update(Board board) {
		boardService.updateS(board);
		return "redirect:boardList.do";
	}
	@GetMapping("content.do")
	public ModelAndView content(long seq) {
		Board board = boardService.contentS(seq);
		ModelAndView mv = new ModelAndView("board/content", "board", board);
		return mv;
	}
	@GetMapping("download.do")
	public ModelAndView download(String fname) {
		File file = new File(FILE_STORE, fname);
		if(file.exists()) {
			return new ModelAndView("fileDownloadView","downloadFile", file);
		}else {
			return new ModelAndView("redirect:list.do");
		}
	}
	@RequestMapping(value="serach.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView serach(@RequestParam(value="ps", required=false, defaultValue=BOARD_PS+"") long ps, @RequestParam(value="cp", required=false, defaultValue=BOARD_CP+"") long cp, String kategorie, String keyword) {
		String likeKey = "";
		if(keyword != null) {
			likeKey = keyword.trim();
			if(likeKey.length() != 0) {
				SerachResult serachResult = new SerachResult();
				serachResult.setColumname(kategorie);
				serachResult.setLikekey(likeKey);
				serachResult = boardService.serachResultSet(ps, cp, serachResult);
				List<Board> boardList = boardService.serachListS(serachResult);
				
				ModelAndView mv = new ModelAndView();
				mv.setViewName("board/list");
				mv.addObject("boardList", boardList);
				mv.addObject("serachResult", serachResult);
				return mv;
			}else {
				return new ModelAndView("redirect:boardList.do");
			}
		}else {
			return new ModelAndView("redirect:boardList.do");
		}
	}
}
