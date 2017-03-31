package com.arwichok.action;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JFrame;
import java.awt.FileDialog;
import java.io.IOException;
import java.io.Writer;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import javax.swing.JOptionPane;

import com.arwichok.Main;


public class SaveFile implements ActionListener{
	JTextPane textPane;
	String fileName = "", fileDirectory;
	JFrame cake;
	String encoding;

	public SaveFile(JTextPane textPane, JFrame cake, String encoding){
		this.textPane = textPane;
		this.cake = cake;
		this.encoding = encoding;
	}

	@Override
	public void actionPerformed(ActionEvent ae){
		
		if(ae.getActionCommand().equals("Save as") | fileName.equals("")){

			FileDialog dialogFile = new FileDialog(new JFrame(), "Save", FileDialog.SAVE);
			dialogFile.setVisible(true);
			
			fileName = dialogFile.getFile();
			fileDirectory = dialogFile.getDirectory();

			if(fileName == null) fileName = "";
			else cake.setTitle(Main.CAPTION + " - " + fileName);

			
		}

		if(fileName.equals("")) return;

		//try(FileOutputStream writeFile = new FileOutputStream(fileDirectory + fileName)){
		try(Writer writeFile = 
			new BufferedWriter(
				new OutputStreamWriter(
					new FileOutputStream(fileDirectory + fileName), encoding))){
			
			writeFile.write(textPane.getText());
			

		}catch(IOException e){
			JOptionPane.showMessageDialog(cake, e, "Error Save", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}
	}
}