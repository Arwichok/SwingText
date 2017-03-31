package com.arwichok.action;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.FlowLayout;



public class SearchEdit implements ActionListener{
	
	JTextPane textPane;
	ImageIcon icon;
	String searchText = "";
	String allText = "";
	int a = 0, b;


	public SearchEdit(JTextPane textPane, ImageIcon icon){
		this.textPane = textPane;
		this.icon = icon;
	}

	@Override
	public void actionPerformed(ActionEvent ae){
		
		a = 0;

		JFrame searchWindow = new JFrame("Search");
		searchText = "";
		
		JTextField text = new JTextField(15);
		JButton button = new JButton(icon);

		searchWindow.setIconImage(icon.getImage());

		searchWindow.setSize(300, 75);
		searchWindow.setLocationRelativeTo(null);
		//searchWindow.setIconImage(icon.getImage());
		searchWindow.setLayout(new FlowLayout());
		searchWindow.add(text);
		searchWindow.add(button);

		searchWindow.setVisible(true);


		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				
				searchText = text.getText();
				textPane.select(0,0);
				

				if(searchText.length() == 0) return;

				allText = textPane.getText();

				if(allText.indexOf(searchText) == -1) return;

				a = allText.indexOf(searchText, a);
				
				b = a + searchText.length();

				if(a == -1) a = 0;

				textPane.select(a, b);

				a++;
			}
		});
	}
}