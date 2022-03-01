package sbc.board.mapper;

import java.util.List;

import sbc.board.domain.Board;
import sbc.board.domain.ListResult;
import sbc.board.domain.SerachResult;

public interface BoardMapper {
	List<Board> boardList(ListResult listResult);
	String boardCount();
	void insert(Board board);
	void delete(long seq);
	Board content(long seq);
	void update(Board board);
	List<Board> serachList(SerachResult serachResult);
	String serachListCount(SerachResult serachResult);
}
