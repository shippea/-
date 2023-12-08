package edu.java.thread;

import java.util.ArrayList;
import java.util.List;

public class ReservationMain {
	
	public static final int ROOM_COUNT = 5;
	public static final int SEAT_COUNT_PER_ROOM = 25;
	public static final int CINEMAPERSON_COUNT = 200;
	
	public static void main(String[] args) {
		
		/* 1. seat 생성
		 * 2. room 생성
		 * 3. cinema 생성
		 * 4. cinemaperson 생성
		 * 5. 예매 logic 생성
		 * 6. 출력*/
		
		/* 1. seat 생성 */
		List<Seat> seatList = new ArrayList<Seat>();
		
		for(int i=0; i<SEAT_COUNT_PER_ROOM*ROOM_COUNT; i++) {
			seatList.add(new Seat(String.valueOf(i+1),false));
			// String.valueOf: int값을 string으로 반환
		}
		
		/* 2. room 생성 */
		List<Room> roomList = new ArrayList<Room>();
		
		for(int i=0; i<ROOM_COUNT; i++) {
			roomList.add(new Room(
					(i+1)+"관", seatList.subList(i*SEAT_COUNT_PER_ROOM, (i+1)*(SEAT_COUNT_PER_ROOM)))
					// sublist를 이용해서 25개씩 잘라 각 관에 넣음
			);
		} //for
		
		/* 3. cinema 생성 */
		Cinema cinema = new Cinema(roomList);
		
		/* 4. cinemaperson 생성 */
		List<CinemaPerson> cinemapersonList = new ArrayList<CinemaPerson>();
		
		for(int i=0; i<CINEMAPERSON_COUNT; i++) {
			cinemapersonList.add(new CinemaPerson("CP"+(i+1), null));
			
		}
		
		System.out.println(cinemapersonList);
		
		
		
	}	//main

}	//class
