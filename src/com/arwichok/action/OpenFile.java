package com.arwichok.action;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JFrame;
import java.awt.FileDialog;
import java.io.Reader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JOptionPane;


import com.arwichok.Main;

public class OpenFile implements ActionListener{
	JTextPane textPane;
	String fileName = "", fileDirectory, fileContent = "";
	JFrame cake;
	String encoding;

	public OpenFile(JTextPane textPane, JFrame cake, String encoding){
		this.textPane = textPane;
		this.cake = cake;
		this.encoding = encoding;
	}

	@Override
	public void actionPerformed(ActionEvent ae){
		if(!ae.getActionCommand().equals("Open")) return;

		
		
		FileDialog dialogFile = new FileDialog(new JFrame(), "Open");
		dialogFile.setVisible(true);

		fileName = dialogFile.getFile();
		fileDirectory = dialogFile.getDirectory();


		if(fileName == null) return;

		cake.setTitle(Main.CAPTION + " - " + fileName);

		openInPane(encoding);
	}

	public void openInPane(String encod){

		fileContent = "";
		
		if(fileName.equals("")) return;
		//if(fileContent.equals("")) return;

		//try(FileReader readFile = new FileReader(fileDirectory + fileName)){
		try(Reader readFile = 
			new BufferedReader(
				new InputStreamReader(
					new FileInputStream(fileDirectory + fileName), encod))){
			int intSet;

			do{
			
				intSet = readFile.read();
				if(intSet == -1) break;
				fileContent += (char) intSet;

			}while(intSet != -1);


			// while((intSet = readFile.read()) != -1){
			// 	fileContent += (char) intSet;
			// }

		}catch(IOException e){
			JOptionPane.showMessageDialog(cake, e, "Error Open", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}

		textPane.setText(fileContent);
	}
}