package edu.java.project3;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HPGUI extends JFrame {
	
	Scanner sc = new Scanner(System.in);
	
	List<HPButton> btnList;
	HPEvent hpEvent;
	
	JPanel firstPanel;
	JPanel dataPanel;
	JPanel pastDataPanel;
	JPanel recordPanel;
	JPanel buttonPanel;
	JPanel todayPanel;
	JPanel currentDataPanel;
	
	JLabel search;
	JLabel labelImage;
	File fileImage;
	BufferedImage bufferedImage;
	ImageIcon imageIcon;
	
	JTextField pastDataTextField;
	JTextField todayNameTextField;
	JTextField todayRecordTextField;
	JTextField currentDataTextField;
	
	int sortNumber;
	
	
	HPGUI(){
		
		init();
	}
	
	void init() {
		
		this.setTitle("Health Program");
		this.setBounds(new Rectangle(700, 700));
		this.setLayout(null);
		// GridLayout, BorderLayout은 각 각의 크기를 조절하는법을 몰라 null로 두고 각각의 크기 조절함
		// -> Grid
		
		
		
		/* main 실행했을 때 처음 나오는 GUI창 */
//		firstPanel = new JPanel(new GridLayout(1,1));
//		firstPanel.setBounds(10, 10, 650, 500);
		labelImage = new JLabel();
		fileImage = new File("C:/Users/admin/Desktop/H/project/project3/health.png");
		
		try {
			bufferedImage = ImageIO.read(fileImage);
			labelImage = new JLabel(new ImageIcon(bufferedImage));
			labelImage.setBounds(10, 10, 650, 500);
			labelImage.getIcon();
			this.getContentPane().add(labelImage);
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		labelImage.setVisible(true);
		
		
		
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
			pastDataTextField = new JTextField("운동기록");
			
			
			dataPanel.add(pastDataPanel);
			pastDataPanel.add(pastDataTextField);
		}
		
		currentDataPanel = new JPanel (new GridLayout(1, 1));
		currentDataPanel.setBounds(10,400,665,60);
		currentDataTextField = new JTextField();
		
		dataPanel.add(currentDataPanel);
		currentDataPanel.add(currentDataTextField);
		
		dataPanel.setVisible(false);

		
		
		/* 오늘을 눌렀을 떄 나오는 오늘 수행한 운동종목 종료와 기록 */
		recordPanel = new JPanel(null);
		recordPanel.setBounds(0,0,700,400);
		this.add(recordPanel);
		
		// 수행한 운동종목 개수 변수 sortNumber, sortNumber이 늘어날 떄마다 recordTextField 증가
		
		for(int i=1; i<(sortNumber=3); i++) {
			
			todayPanel = new JPanel (new GridLayout(2, 1));
			todayPanel.setBounds(10,5+80*(i-1),665,70);
			
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
		
		btnList = new ArrayList<HPButton>();
		btnList.add(new HPButton(new JButton("검색")));
		btnList.add(new HPButton(new JButton("입력")));
		btnList.add(new HPButton(new JButton("오늘")));
		btnList.add(new HPButton(new JButton("저장")));

		for (HPButton hpButton : btnList) {
			buttonPanel.add(hpButton.jButton);
		}

		hpEvent = new HPEvent(this);
		hpEvent.event();
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public void setButtonAction(JButton button, Runnable action) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action.run();
			}
		});
	}
	
} // class


//fileChooserImage = new JFileChooser("C:/Users/admin/Desktop/H/project/project3/health.png");
//firstPanel.add(fileChooserImage);
//firstPanel.add(ex);