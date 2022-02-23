package sbc.board.mapper;

import java.util.List;

import sbc.board.domain.Board;
import sbc.board.domain.ListResult;

public interface BoardMapper {
	List<Board> boardList(ListResult listResult);
	List<Long> boardCount();
	void insert(Board board);
	void delete(long seq);
	Board content(long seq);
	void update(Board board);
}
