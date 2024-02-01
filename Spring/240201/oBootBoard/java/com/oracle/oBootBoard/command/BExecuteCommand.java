package com.oracle.oBootBoard.command;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootBoard.dao.BDao;
import com.oracle.oBootBoard.dao.JdbcDao;
import com.oracle.oBootBoard.dto.BDto;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class BExecuteCommand {
	
	private final BDao jdbcDao;
	
	public BExecuteCommand(BDao jdBDao) {
		this.jdbcDao = jdBDao;
	}
	
	public void bListCmd (Model model) {
		
		// Dao 연결
		ArrayList<BDto> boardDtoList = jdbcDao.boardList();
		System.out.println("BListCommand boardDtoList.size()-->"+boardDtoList.size());
		model.addAttribute("boardList",boardDtoList);
	}
	
	public void bWriteCmd(Model model) {
 		// 1)  model이용 , map 선언
		Map<String, Object> map = model.asMap();
 		
		// 2) request 이용 ->  bName  ,bTitle  , bContent  추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
 		// 3) dao  instance 선언
		// 이미 위에 BExecuteCommand 메소드로 선언했음
		
 		// 4) write method 이용하여 저장(bName, bTitle, bContent)
		jdbcDao.write(bName, bTitle, bContent);
	}
	
	// HW2
	public void bContentCmd(Model model) {
		// 1. model을 Map으로 전환
		Map<String, Object> map = model.asMap();
		
		// 2. request -> bId Get
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int bId = Integer.parseInt(request.getParameter("bId"));
		System.out.println("bContentCmd bId->" +bId);
		
		// 3. HW3
		BDto board = jdbcDao.contentView(bId);
		System.out.println("bContentCmd board.getbName->" +board.getbName());
		model.addAttribute("mvc_board", board);
		
	}
	
	public void bModifyCmd(Model model) {
		// 1. model Map 선언
		Map<String, Object> map = model.asMap();
		
		// 2. parameter -> bId, bName, bTitle, bContent
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int bId = Integer.parseInt(request.getParameter("bId"));
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		jdbcDao.modify(bId, bName, bTitle, bContent);
	}

	public void bReplyViewCmd(Model model) {
		// 1)  model이용 , map 선언
		Map<String, Object> map = model.asMap();
		
		// 2) request 이용 ->  bid  추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int bId = Integer.parseInt(request.getParameter("bId"));
		
		// 3) reply_view method 이용하여 (bid)
		BDto bDto = jdbcDao.reply_view(bId);
		System.out.println("bContentCmd board.getbName->" +bDto.getbName());
		model.addAttribute("reply_view", bDto);
	}

	public void bReplyCmd(Model model) {
		// 1)  model이용 , map 선언
		Map<String, Object> map = model.asMap();
		
		// 2) request 이용 -> bid,         bName ,  bTitle,
		// bContent ,  bGroup , bStep ,
		// bIndent 추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int bId = Integer.parseInt(request.getParameter("bId"));
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		int bGroup = Integer.parseInt(request.getParameter("bGroup"));
		int bStep = Integer.parseInt(request.getParameter("bStep"));
		int bIndent = Integer.parseInt(request.getParameter("bIndent"));
		System.out.println("BReplyCommand bGroup->" +bGroup);

		// 3) reply method 이용하여 댓글저장 
		// - dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
		// [1] bId SEQUENCE = bGroup 
		// [2] bName, bTitle, bContent -> request Value
		// [3] 홍해 기적
		// [4] bStep / bIndent   + 1
		jdbcDao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
	}

	public void bDeleteCmd(Model model) {
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int bId = Integer.parseInt(request.getParameter("bId"));
		
		jdbcDao.delete(bId);
		
	}
	
	
}
