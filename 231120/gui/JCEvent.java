package edu.java.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class JCEvent implements ActionListener {
	
	JTextField textField;
	JCLogic jcLogic;
	// event가 일어날떄마다 JCLoigc의 정보를 가져오기 위해서
	// -> textField를 넘겨받기위한 방식과 똑같은 방식
	
	public JCEvent() {
	}


	public JCEvent(JTextField textField, JCLogic jcLogic) {
		this.textField = textField;
		this.jcLogic = jcLogic;
	}
	// textField를 넘겨받기 위해 JCGUI class의 new JCEvent 안에 textField를 넣고
	//	JCEvent에도 멤버변수와 생성자를 만듬 -> spring의 '생성자주입'에서 배움
	
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		// actioncommand를 지정해야 string값이 그대로 나오는 것이 아니라 액션을 취함
		
		String sc = e.getActionCommand();
		jcLogic.content += sc;
		if("ENTER".equals(sc)) {
			textField.setText(
					String.valueOf(jcLogic.add(jcLogic.content))
		);
					
		}
		
	}
	
	
	
}
