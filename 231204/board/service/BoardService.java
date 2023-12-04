package board.service;

import java.util.List;

import board.bean.Board;

// Board 서비스 facade (출입구) 
// -> 모든 Board 서비스는 여기를 이용
public interface BoardService {
	
	// 목록
	public abstract List<Board> getBoardList();
	
	// 등록
	public abstract void registBoard(Board board);
	
	// 삭제
	public abstract void deleteBoard(int bid);
	
	// 수정
	public abstract void updateBoard(Board board);

}
