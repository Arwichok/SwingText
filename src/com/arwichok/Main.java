package com.arwichok;

import com.arwichok.gui.SwingTextGUI;
import javax.swing.SwingUtilities;



public class Main{
	public final static String CAPTION = "SwingTextGUI";
	public final static String VERSION = "0.03";
	
	public static void main(String args[]){
		
		

		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new SwingTextGUI(CAPTION);
			}
		});
	}
}