package edu.java.project3;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class HPEvent {
	
	HPGUI hpGUI;
//	labelImage / dataPanel / recordPanel
	
	HPEvent() {
		
		hpGUI.btnList.get(0).jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hpGUI.labelImage.setVisible(false);
				hpGUI.dataPanel.setVisible(true);
			}
		});
		hpGUI.btnList.get(1).jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hpGUI.labelImage.setVisible(false);
				hpGUI.dataPanel.setVisible(true);
			}
		});
		hpGUI.btnList.get(2).jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hpGUI.labelImage.setVisible(false);
				hpGUI.dataPanel.setVisible(true);
			}
		});
		hpGUI.btnList.get(3).jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hpGUI.labelImage.setVisible(false);
				hpGUI.dataPanel.setVisible(true);
			}
		});
		
		
	}
	



	// 오늘 버튼을 눌렀을 때 과
}
