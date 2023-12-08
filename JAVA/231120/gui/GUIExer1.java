package edu.java.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIExer1 extends Frame {
	
		Image image = null; 
		
		GUIExer1(){
			this.setTitle("처음 만들어보는 프레임");
			this.setBackground(Color.ORANGE);
			this.setSize(400,400);
			this.setLayout(new BorderLayout());
			
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing (WindowEvent e) {
					System.out.println(e.getClass().getName());
					dispose();
				}
			});
			
			Button button = new Button("버튼");
			button.setBackground(Color.DARK_GRAY);
			button.setSize(200,200);
			this.add(button, BorderLayout.NORTH);
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("버튼을 눌렀어요!");
				}
			});

			String imgPath = "C:/data/lunch.png";
			image = Toolkit .getDefaultToolkit().getImage(imgPath);
			
			TextField tf = new TextField("여기에입력",10);
			Button button2 = new Button("확인");
			
			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println(tf.getText() + "가 입력되었어요!");
				}
			});
			
			Panel panel = new Panel(new BorderLayout());
			panel.add(tf, BorderLayout.WEST);
			panel.add(button2, BorderLayout.EAST);
			
			this.add(panel, BorderLayout.SOUTH);
					
		
//			this.pack(); // 불필요한 부분을 압수
			
			this.setVisible(true);
		}	
		public static void main(String[] args) {
			new GUIExer1();
		} // main
		@Override
		public void paint(Graphics g) {
			g.drawImage(image,0,0,this);
			// 이미지를 불러오는게 아니라 받아서 pnint가 그림을 그림.
		} // paint
		
		
		
		
	
} // class
