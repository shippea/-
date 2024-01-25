package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.taglibs.standard.tag.el.core.ForTokensTag;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ListAction Service start...");
		
		BoardDao bd = BoardDao.getInstance();
		
		try {
			int totCnt = bd.getTotalCnt();
			
			// 난잡 Paging -> 나중에 spring에서 모듈화할 예정
			String pageNum = request.getParameter("pageNum");
			if (pageNum==null || pageNum.equals("")) {pageNum ="1";}
			int currentPage = Integer.parseInt(pageNum);	// 1
			int pageSize = 10, blockSize=10;
			int startRow = (currentPage - 1) * pageSize +1;	// 1    11
			int endRow = startRow + pageSize -1;	// 10   20
			int startNum = totCnt - startRow +1;
			
			// Board 조회							1		10
			List<Board> list = bd.boardList(startRow,endRow);
			
			//										37   /   10
			int pageCnt = (int)Math.ceil((double) totCnt/pageSize); // 4
			int startPage = (int)(currentPage-1)/blockSize*blockSize+1; //1
			int endPage = startPage + blockSize-1; //10
			// 공갈 Page 방지 10>4
			if (endPage > pageCnt) endPage = pageCnt;	// 4
			
			request.setAttribute("list", list);
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 38


		//      View 명칭   
		return "listForm.jsp";
	}

}
