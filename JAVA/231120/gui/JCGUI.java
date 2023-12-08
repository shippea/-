package edu.java.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JCGUI extends JFrame {
	
	List<JCButton> btnList;
	JPanel northPanel;
	JPanel southPanel;
	JTextField textField;
	JButton clearButton;
			

	JCGUI() {
		init();
	}
	
	void init() {
		
		this.setTitle("Java Calculator");
		this.setBounds(0, 0, 400, 400);
		this.setLayout(new BorderLayout());
		
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		textField = new JTextField("0", 20);
		textField.setHorizontalAlignment(JTextField.RIGHT);
		textField.setFont(new Font("Arial", Font.BOLD, 16));
		clearButton = new JButton("clear");
		northPanel.add(textField);
		northPanel.add(clearButton);
		
		southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(4, 4));
		
		btnList = new ArrayList<JCButton>();
		btnList.add(new JCButton(new JButton("1"), "1"));
		btnList.add(new JCButton(new JButton("2"), "2"));
		btnList.add(new JCButton(new JButton("3"), "3"));
		btnList.add(new JCButton(new JButton("+"), "plus"));
		btnList.add(new JCButton(new JButton("4"), "4"));
		btnList.add(new JCButton(new JButton("5"), "5"));
		btnList.add(new JCButton(new JButton("6"), "6"));
		btnList.add(new JCButton(new JButton("-"), "sub"));
		btnList.add(new JCButton(new JButton("7"), "7"));
		btnList.add(new JCButton(new JButton("8"), "8"));
		btnList.add(new JCButton(new JButton("9"), "9"));
		btnList.add(new JCButton(new JButton("*"), "mul"));
		btnList.add(new JCButton(new JButton("0"), "0"));
		btnList.add(new JCButton(new JButton("."), "dot"));
		btnList.add(new JCButton(new JButton("ENTER"), "enter"));
		btnList.add(new JCButton(new JButton("/"), "dev"));
		
		for (JCButton jcbutton : btnList) {
			jcbutton.jbutton.addActionListener(new JCEvent(textField, new JCLogic()));
			// JCEVent class에서 설정한 actioncommand 기능을 button에 넣어줌
			// new JCEvent()에 textField를 넣어주고 JCEvent에 멤버변수, 생성자를 만들어야
			//	JCEvent class에서도 textField를 사용할 수 있음
			southPanel.add(jcbutton.jbutton);
		}

		this.add(northPanel, BorderLayout.NORTH);
		this.add(southPanel, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
}








