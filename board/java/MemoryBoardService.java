package sbc.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbc.board.domain.Board;
import sbc.board.domain.ListResult;
import sbc.board.mapper.BoardMapper;
import static sbc.board.consts.BoardConst.*;

@Service
public class MemoryBoardService implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<Board> boardListS(ListResult listResult) {
		return boardMapper.boardList(listResult);
	}
	@Override
	public void insertS(Board board) {
		boardMapper.insert(board);
	}
	@Override
	public void deleteS(long seq) {
		boardMapper.delete(seq);
	}
	@Override
	public Board contentS(long seq) {
		return boardMapper.content(seq);
	}
	@Override
	public void updateS(Board board) {
		boardMapper.update(board);
	}
	@Override
	public ListResult resultSet(long ps, long cp) {
		ListResult listResult = new ListResult();
		long psTemp = -1;
		long cpTemp = -1;
		if(ps == -1 || ps == 0) {
			psTemp = BOARD_PS;
		}else {
			psTemp = ps;
		}
		if(cp == -1 || cp == 0) {
			cpTemp = BOARD_CP;
		}else {
			cpTemp = cp;
		}
		
		long totalCount = boardCountS();
		long totalPageCount = getMaxPage(psTemp, totalCount);
		long nowPage = -1;
		long nowMinPage = -1;
		long nowMaxPage = -1;
		for(long i = 0; i < totalPageCount; i++) {
			nowPage++;
			nowMinPage = ((nowPage-1) * BOARD_PAGE_NUM)+1;
			nowMaxPage = nowPage * BOARD_PAGE_NUM;
			if(cpTemp <= nowMaxPage && cpTemp >= nowMinPage) {
				break;
			}
		}
		long min = psTemp*(cpTemp-1);
		long max = psTemp*cpTemp;
		
		listResult.setMin(min);
		listResult.setMax(max);
		listResult.setNowminpage(nowMinPage);
		listResult.setNowmaxpage(nowMaxPage);
		listResult.setPs(psTemp);
		listResult.setCp(cpTemp);
		listResult.setTotalpagecount(totalPageCount);
		listResult.setTotalcount(totalCount);
		return listResult;
	}
	private long boardCountS(){
		List<Long> boardCountList = boardMapper.boardCount();
		long boardSize = boardCountList.size();
		return boardSize;
	}
	private long getMaxPage(long ps, long boardCount) {
		long maxPage = -1;
		maxPage = boardCount/ps;
		if(boardCount != ps*maxPage) {
			maxPage = boardCount/ps+1;
		}
		return maxPage;
	}
}
