package edu.java.project3;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame {
	
	JPanel firstPanel;
	JPanel dataPanel;
	JPanel pastDataPanel;
	JPanel recordPanel;
	JPanel buttonPanel;
	JPanel todayPanel;
	JPanel currentDataPanel;
	
	JLabel search;
	
	JTextField pastDataTextField;
	JTextField todayNameTextField;
	JTextField todayRecordTextField;
	JTextField currentDataTextField;
	int sortNumber;
	
	
	GUI(){
		
		init();
	}
	
	void init() {
		
		this.setTitle("Health Program");
		this.setBounds(new Rectangle(700, 700));
		this.setLayout(null);
		// GridLayout, BorderLayout은 각 각의 크기를 조절하는법을 몰라 null로 두고 각각의 크기 조절함
		
		
		/* main 실행했을 때 처음 나오는 GUI창 */
		firstPanel = new JPanel(null);
		
		
		
		/* 검색으로 운동종목을 설정했을 때 나오는 GUI창 */
		dataPanel = new JPanel(null);
		dataPanel.setBounds(0,0,700,500);
		this.add(dataPanel);
		
		search = new JLabel("운동이름");
		search.setBounds(20,0,700,50);
		dataPanel.add(search);
		
		for(int i=1; i<=5; i++) {
			pastDataPanel = new JPanel (new GridLayout(1, 1));
			pastDataPanel.setBounds(10,50+65*(i-1),665,50);
			pastDataTextField = new JTextField("과거기록");
			
			dataPanel.add(pastDataPanel);
			pastDataPanel.add(pastDataTextField);
			
		}
		
		currentDataPanel = new JPanel (new GridLayout(1, 1));
		currentDataPanel.setBounds(10,400,665,60);
		currentDataTextField = new JTextField("입력기록");
		
		dataPanel.add(currentDataPanel);
		currentDataPanel.add(currentDataTextField);
		
		dataPanel.setVisible(true);
		
		
		/* 오늘을 눌렀을 떄 나오는 오늘 수행한 운동종목 종료와 기록 */
		recordPanel = new JPanel(null);
		recordPanel.setBounds(0,0,700,400);
		this.add(recordPanel);
		
		// 수행한 운동종목 개수 변수 sortNumber, sortNumber이 늘어날 떄마다 recordTextField 증가
		
		for(int i=1; i<(sortNumber=3); i++) {
			
			todayPanel = new JPanel (new GridLayout(2, 1));
			todayPanel.setBounds(10,5+70*(i-1),665,60);
			
			todayNameTextField = new JTextField("운동이름");
			todayRecordTextField = new JTextField("기록");
			
			recordPanel.add(todayPanel);
			todayPanel.add(todayNameTextField);
			todayPanel.add(todayRecordTextField);
		}
		
		recordPanel.setVisible(false);
		
		/* GUIL창 하단의 버튼 */
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setBounds(10,600,700,100);
		
		this.add(buttonPanel);
		
		buttonPanel.add(new JButton("검색"));
		buttonPanel.add(new JButton("입력"));
		buttonPanel.add(new JButton("오늘"));
		buttonPanel.add(new JButton("저장"));
		// flowlayout 사용해서 button 나열
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
} // class

