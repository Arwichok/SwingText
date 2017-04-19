package com.arwichok.update;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;


public class Update{
	private final static String LINK_DOWNLOADER = 
	"http://arwichok.tk/repository/Downloader.jar";

	public Update(String link, String version){
		ReadXML rxml = getReadXML(link);
		if(rxml != null){
			if(!version.equals(rxml.getTag("version"))){
				if(questionUpdate(version, rxml.getTag("version"))){
					downloadUpdater();
					launchUpdater(rxml.getTag("url"));	
				}
				
			}
		}
	}

	private ReadXML getReadXML(String link){

		try{

			return new ReadXML(new URL(link).openConnection().getInputStream());
		
		}catch(MalformedURLException e){
			System.out.println("MalformedURLException: " + e);
			return null;
		}catch(IOException e){
			System.out.println("IOException: " + e);
			return null;
		}catch(NullPointerException e){
			System.out.println("NullPointerException: " + e);
			return null;
		}
	}

	private void downloadUpdater(){
		
		try{

			InputStream in = new URL(LINK_DOWNLOADER).openConnection().getInputStream();
			FileOutputStream out = new FileOutputStream(getNameFile(LINK_DOWNLOADER));

			while(true){
				int t = in.read();
				if(t == -1) break;
				out.write(t);
			}

		}catch(MalformedURLException e){
			System.out.println("MalformedURLException: " + e);
			return;
		}catch(IOException e){
			System.out.println("IOException: " + e);
			return;
		}catch(NullPointerException e){
			System.out.println("NullPointerException: " + e);
			return;
		}
	}

	private String getNameFile(String link){
		String[] tempArr = link.split("/");
		return tempArr[tempArr.length-1];
	}

	// public static void main(String[] args) {
	// 	new Update("http://arwichok.tk/content/SwingText.xml", "0.03");
	// }

	void launchUpdater(String link){
		try{
			Process proc = Runtime.getRuntime().exec("java -jar Downloader.jar " + link);
		}catch(IOException e){
			System.out.println("Updater.java:launchUpdater(), IOException: " + e);
		}
		// Сделать загрузчик
		System.exit(0);
	}

	boolean questionUpdate(String currentVersion, String newVersion){
		int q = JOptionPane.showConfirmDialog(
			null,
			"Download new Version? \n" + 
			"Current - " + currentVersion + "\n" + 
			"New - " + newVersion,
			"Update",
			JOptionPane.YES_NO_OPTION);
		if(q == JOptionPane.YES_OPTION) return true;
		else return false;
	}
}
