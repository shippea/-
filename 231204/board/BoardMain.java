package board;


import board.bean.Board;
import board.dao.BoardDao;
import board.service.BoardService;

public class BoardMain extends BoardTemplate {

	static BoardService boardService = BoardDao.getInstance();		
	// 최상위인 boardservice 형태로만 받아서 사용
	
	public static void main(String[] args) {
		
		BoardMain bm = new BoardMain();
		// non-static method를 static main에 부르려면 객체를 생성해서 사용
		bm.printList();
		
		boardService.deleteBoard(2);
		
		bm.printList();
		
		Board board4 = new Board(4, "제목4","내용4");
		boardService.registBoard(board4);
		
		bm.printList();
		
		board4.setTitle("새제목4");
		board4.setContent("새내용4");
		boardService.updateBoard(board4);
		
		bm.printList();
	}
	
	// boardList 각각의 하나에 {}안의 내용을 적용시키는 람다식

	@Override
	public void printTop() {
		System.out.println("#### 리스트 출력 시작");
		
	}

	@Override
	public void printMiddle() {
		boardService.getBoardList().forEach(bl -> {
			System.out.println("제목: " + bl.getTitle());
			System.out.println("내용: " + bl.getContent());
		});
	}

	@Override
	public void printBottom() {
		System.out.println("#### 리스트 출력 끝");
	}

	@Override
	public void printList() {
	}
	
}
