package sbc.board.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sbc.board.domain.Board;
import sbc.board.domain.ListResult;
import sbc.board.domain.SerachResult;
import sbc.board.mapper.BoardMapper;
import static sbc.board.consts.BoardConst.*;
import static sbc.file.fileset.FilePath.FILE_STORE;

@Service
public class MemoryBoardService implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<Board> boardListS(ListResult listResult) {
		return boardMapper.boardList(listResult);
	}
	@Override
	public void write(Board board) {
		boardMapper.insert(board);
	}
	@Override
	public void remove(long seq) {
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
		long totalCount = -1;
		long nowPage = -1;
		long nowMinPage = -1;
		long nowMaxPage = -1;
		String totalCountStr = boardMapper.boardCount();
		
		if(totalCountStr == null) totalCount = 0;
		else totalCount = Long.parseLong(totalCountStr);
		
		long totalPageCount = getMaxPage(ps, totalCount);
		
		for(long i = 0; i < totalPageCount; i++) {
			nowPage++;
			nowMinPage = ((nowPage-1) * BOARD_PAGE_NUM)+1;
			nowMaxPage = nowPage * BOARD_PAGE_NUM;
			if(cp <= nowMaxPage && cp >= nowMinPage) {
				break;
			}
		}
		long min = ps*(cp-1);
		long max = ps*cp;
		if(nowMaxPage < 1) nowMaxPage = 1;
		if(nowMinPage < 0) nowMinPage = 1;
		
		ListResult listResult = new ListResult(ps, cp, totalPageCount, totalCount, nowMaxPage, nowMinPage, max, min);
		return listResult;
	}
	@Override
	public Board saveStore(MultipartFile file, Board board) {
		String ofname = file.getOriginalFilename();
		if(ofname != null) ofname.trim();
		if(ofname.length() != 0) {
			int idx = ofname.lastIndexOf(".");
			String ofheader = ofname.substring(0, idx); // 대부분 (이상, 미만) 이다.
			String ext = ofname.substring(idx);
			long ms = System.currentTimeMillis();
			
			StringBuilder sb = new StringBuilder(); // + 연산자를 사용하면 계속 새로운 스트링 객체가 생기기떄문에 StringBuilder 를 시용해서 메모리 사용을 최소화 해준다. 
			sb.append(ofheader);
			sb.append("_");
			sb.append(ms);
			sb.append(ext);
			String saveFileName = sb.toString();
			
			long fsize = file.getSize();
			
			// 업로드 로직
			boolean flag = writeFile(file, saveFileName);
			if(flag) {
				board.setOfname(ofname);
				board.setFsize(fsize);
				board.setFname(saveFileName);
				
				return board;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	@Override
	public List<Board> serachListS(SerachResult serachResult){
		return boardMapper.serachList(serachResult);
	}
	@Override
	public SerachResult serachResultSet(long ps, long cp, SerachResult serachResult) {
		long nowPage = -1;
		long nowMinPage = -1;
		long nowMaxPage = -1;
		long totalPageCount = -1;
		long totalCount = -1;
		String totalCountStr = boardMapper.serachListCount(serachResult);
		if(totalCountStr == null) totalCount = 0;
		else totalCount = Long.parseLong(totalCountStr);
		
		if(totalCount != 0) {
			totalPageCount = getMaxPage(ps, totalCount);
			for(long i = 0; i < totalPageCount; i++) {
				nowPage++;
				nowMinPage = ((nowPage-1) * BOARD_PAGE_NUM)+1;
				nowMaxPage = nowPage * BOARD_PAGE_NUM;
				if(cp <= nowMaxPage && cp >= nowMinPage) {
					break;
				}
			}
		}
		long min = ps*(cp-1);
		long max = ps*cp;
		if(nowMaxPage < 1) nowMaxPage = 1;
		if(nowMinPage < 0) nowMinPage = 1;
		if(totalPageCount == -1) totalPageCount = 0; 
		
		serachResult.setPs(ps);
		serachResult.setCp(cp);
		serachResult.setTotalpagecount(totalPageCount);
		serachResult.setTotalcount(totalCount);
		serachResult.setNowmaxpage(nowMaxPage);
		serachResult.setNowminpage(nowMinPage);
		serachResult.setMax(max);
		serachResult.setMin(min);
		
		System.out.println("ps: "+ ps);
		System.out.println("cp: "+ cp);
		System.out.println("totalPageCount: "+ totalPageCount);
		System.out.println("totalCount: "+ totalCount);
		System.out.println("nowMaxPage: "+ nowMaxPage);
		System.out.println("nowMinPage: "+ nowMinPage);
		System.out.println("max: "+ max);
		System.out.println("min: "+ min);
		
		return serachResult;
	}
	private boolean writeFile(MultipartFile file, String savaFileName) {
		File dir = new File(FILE_STORE);
		if(!dir.exists()) dir.mkdirs();
		
		FileOutputStream fos = null;
		try {
			byte data[] = file.getBytes();
			fos = new FileOutputStream(FILE_STORE+savaFileName);
			fos.write(data);
			fos.flush();
			
			return true;
		}catch(IOException ie) {
			return false;
		}finally {
			try {
				if(fos != null) fos.close();
			}catch(IOException ie) {}
		}
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
