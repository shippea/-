package board.service;

import java.util.List;

import board.bean.Board;


// Board interface - 구현 class 매개체가 되는 class
public abstract class AbstractBoard implements BoardService {
	
	@Override
	public List<Board> getBoardList() {
		return null;
	}

	@Override
	public void registBoard(Board board) {
		
	}
	
	@Override
	public void deleteBoard(int bid) {
		
	}
	
	@Override
	public void updateBoard(Board board) {
		
	}
	
}
