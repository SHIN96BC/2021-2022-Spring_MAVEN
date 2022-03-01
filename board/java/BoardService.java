package sbc.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import sbc.board.domain.Board;
import sbc.board.domain.ListResult;
import sbc.board.domain.SerachResult;

public interface BoardService {
	List<Board> boardListS(ListResult listResult);
	void write(Board board);
	void remove(long seq);
	Board contentS(long seq);
	void updateS(Board board);
	ListResult resultSet(long ps, long cp);
	Board saveStore(MultipartFile file, Board board);
	List<Board> serachListS(SerachResult serachResult);
	SerachResult serachResultSet(long ps, long cp, SerachResult serachResult);
}
