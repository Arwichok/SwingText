package com.arwichok.action;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExitFile implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent ae){
		if(ae.getActionCommand().equals("Exit")) System.exit(0);
	}
}