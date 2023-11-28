package edu.java.project3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HPEvent {
	

//	labelImage / dataPanel / recordPanel
	
	HPGUI hpGUI;

    public HPEvent(HPGUI hpGUI) {
        this.hpGUI = hpGUI;
    }

    public void event() {

        hpGUI.btnList.get(0).jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDataPanel();
                
                // 검색을 눌렀을 때 나오는 새로운 GUI창
                // 미완성 -> size 조절이 안됨
                JFrame sortNameJFrame = new JFrame("운동종목");
                sortNameJFrame.setBounds(new Rectangle(200,100));
                sortNameJFrame.setLayout(null);
                JPanel sortNamePanel = new JPanel(new GridLayout(1, 1));
                sortNamePanel.setBounds(30, 20, 100, 50);
                
                JTextField sortNameTextField = new JTextField();
                
                sortNameJFrame.add(sortNamePanel);
                sortNamePanel.add(sortNameTextField);
                sortNameJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                sortNameJFrame.setVisible(true);
            }
        });

        hpGUI.btnList.get(1).jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
        });

        hpGUI.btnList.get(2).jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRecordPanel();
            }
        });

        hpGUI.btnList.get(3).jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
    }

    public void showDataPanel() {
        hpGUI.labelImage.setVisible(false);
        hpGUI.recordPanel.setVisible(false);
        hpGUI.dataPanel.setVisible(true);
    }

    public void showRecordPanel() {
        hpGUI.labelImage.setVisible(false);
        hpGUI.recordPanel.setVisible(true);
        hpGUI.dataPanel.setVisible(false);
    }
}
