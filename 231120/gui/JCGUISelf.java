package edu.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// JCGUI - UI클래스
// JavaCalculator - 메인클래스
// JCLogic - 로직클래스
// JCEvent - 이벤트처리 클래스
// JCButton - 버튼클래스

public class JCGUISelf extends JFrame {
	
	JCGUISelf(){

		this.setLayout(new BorderLayout());
		this.setBounds(new Rectangle(400,400));
		this.setTitle("Java Calculator");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	
		JPanel inputPanel = new JPanel(new GridLayout(1,2));
		
		JTextField jtf = new JTextField("",10);
		jtf.setSize(300,50);
		jtf.setLocation(10,5);
		
		JButton clearButton = new JButton("C");
		clearButton.setSize(60, 50);
		clearButton.setBackground(Color.GRAY);
		clearButton.setLocation(315, 5);
		
		this.add(inputPanel.add(jtf));
		this.add(inputPanel.add(clearButton));
		
		JPanel buttonPanel = new JPanel(new GridLayout(4,4));
		
		List <JCButton> btnList = new ArrayList<JCButton>();
		btnList.add(new JCButton(new JButton("1"),"1"));
		btnList.add(new JCButton(new JButton("2"),"1"));
		btnList.add(new JCButton(new JButton("3"),"1"));
		btnList.add(new JCButton(new JButton("+"),"plus"));
		btnList.add(new JCButton(new JButton("4"),"4"));
		btnList.add(new JCButton(new JButton("5"),"5"));
		btnList.add(new JCButton(new JButton("6"),"6"));
		btnList.add(new JCButton(new JButton("-"),"minus"));
		btnList.add(new JCButton(new JButton("7"),"7"));
		btnList.add(new JCButton(new JButton("8"),"8"));
		btnList.add(new JCButton(new JButton("9"),"9"));
		btnList.add(new JCButton(new JButton("*"),"mul"));
		btnList.add(new JCButton(new JButton("0"),"0"));
		btnList.add(new JCButton(new JButton("."),"."));
		btnList.add(new JCButton(new JButton("확인"),"1"));
		btnList.add(new JCButton(new JButton("/"),"divide"));
		
		

				
		

		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new JCGUISelf();
	}

} // class


