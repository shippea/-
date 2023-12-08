	package edu.java.gui;

import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class GUIExer2 extends JFrame {
	// J- awt에서 성능이 향상된 것을 의미
	
	GUIExer2(){
		init();
		
	}
	
	private void init() {
		this.setLayout(new GridLayout(3, 2));
		this.setBounds(new Rectangle(400, 400));
		this.setTitle("스윙 JFRAME");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// awt와 달리 close를 따로 이벤트처리 안해줘도 됨
		// 닫을떄 메모리를 삭제 (dispose close를 안하면 화면으로는 꺼져있는 것처럼 보여도 메모리 사용)
	
		
		JMenu jm = new JMenu("메뉴");
		JMenu jm1 = new JMenu("메뉴");
		JMenu jm2 = new JMenu("메뉴");
		JMenu jm3 = new JMenu("메뉴");
		JOptionPane.showConfirmDialog(this, "정말 하시겠습니까?");
		
	
		this.add(jm);
		this.add(jm1);
		this.add(jm2);
		this.add(jm3);
		this.setVisible(true);
	}
		
	public static void main(String[] args) {
		new GUIExer2();
	} // main

}
