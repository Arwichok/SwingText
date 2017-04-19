package com.arwichok;

import com.arwichok.gui.SwingTextGUI;
import javax.swing.SwingUtilities;
import com.arwichok.update.Update;



public class Main{
	public final static String CAPTION = "SwingText";
	public final static String VERSION = "0.04";
	private final static String UPDATE_XML = "http://arwichok.tk/content/SwingText.xml";
	
	public static void main(String args[]){
		
		new Update(UPDATE_XML, VERSION);
		

		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new SwingTextGUI(CAPTION);
			}
		});
	}
}