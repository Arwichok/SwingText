package com.arwichok.action;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.arwichok.Main;

public class AboutHelp implements ActionListener{
	JFrame frame;
	String version = Main.VERSION;
	Runtime r;
	long memory;

	public AboutHelp(JFrame frame){
		this.frame = frame;
		r = Runtime.getRuntime();
	}
	@Override
	public void actionPerformed(ActionEvent ae){
		
		memory = r.totalMemory() - r.freeMemory();
		

		JOptionPane.showMessageDialog(frame, 
			"SwingText - v" + version + "    |    RAM: " + memory/1048576 + " Mb", 
			"About", 
			JOptionPane.DEFAULT_OPTION);


	}
}