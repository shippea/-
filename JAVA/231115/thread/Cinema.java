package edu.java.thread;

import java.util.List;

public class Cinema {
	
	/* #1. Thread 동기화 실습1
	 * 1. 요구사항
	 * 	영화관 내에 5개의 상영관이 있습니다.
	 * 	각 상영관에는 25개의 좌석이 있습니다.
	 * 	200명의 사람이 선착순으로 모든 좌석을 예매할 수 있습니다. (Math.random())
	 * 	모든 상영관의 모든 좌석이 예매되면 예매현황을 출력
	 *  
	 * 2. 필요클래스
	 * 	Cinema 영화관 클래스
	 * 	Room 상영관 클래스
	 * 	Seat 좌석 클래스
	 * 	CinemaPerson 예매자정보클래스 (예매자명은 CP1 ~ CP200) 
	 * 	ReservationThread 예매스레드
	 * 	ReservationMain 메인클래스
	 * 
	 * 3. 출력예시
	 * [영화관 예매 현황]
	 * 1관 1석 - CP10
	 * 1관 2석 - CP123
	 * ...
	 * 예매실패자: 75명 (CP1, Cp3, ...)*/
	
	private List<Room> roomList;
	
	public Cinema() {
		
	}

	public Cinema(List<Room> roomList) {
		super();
		this.roomList = roomList;
	}

	public List<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}

	@Override
	public String toString() {
		return "Cinema [roomList=" + roomList + "]";
	}
	
	
		
}
