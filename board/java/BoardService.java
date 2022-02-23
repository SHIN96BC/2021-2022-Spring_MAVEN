package sbc.board.service;

import java.util.List;

import sbc.board.domain.Board;
import sbc.board.domain.ListResult;

public interface BoardService {
	List<Board> boardListS(ListResult listResult);
	void insertS(Board board);
	void deleteS(long seq);
	Board contentS(long seq);
	void updateS(Board board);
	ListResult resultSet(long ps, long cp);
}
