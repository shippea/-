package edu.java.thread;

import java.util.List;

public class Room {
	
	private String roomName;
	private List<Seat> roomList;
	
	public Room() {
		
	}

	public Room(String roomName, List<Seat> roomList) {
		super();
		this.roomName = roomName;
		this.roomList = roomList;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


	public List<Seat> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<Seat> roomList) {
		this.roomList = roomList;
	}

	@Override
	public String toString() {
		return "Room [roomName=" + roomName + ", roomList=" + roomList + "]";
	}
	
	
}
