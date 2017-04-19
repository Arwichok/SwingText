package com.arwichok.update;

import java.io.*;

public class ReadXML{
	private String all;
	
	public ReadXML(String nameFile){
		
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(nameFile)));
			all = "";
			String temp = "";

			while(true){
				temp = in.readLine();
				if(temp == null) break;
				all += temp;
			}

		}catch(IOException e){
			System.out.println(e);
		}
	}

	public ReadXML(InputStream inStream) throws IOException{

		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
			all = "";
			String temp = "";

			while(true){
				temp = in.readLine();
				if(temp == null) break;
				all += temp;
			}

		}catch(IOException e){
			System.out.println(e);
		}
	}

	String getTag(String find){
		String openTeg, closedTeg;
		openTeg = "<" + find + ">";
		closedTeg = "</" + find + ">";

		return cutString(all, openTeg, closedTeg);
	}


	String getTagAttrValue(String in, String word){

		String tag = "<" + word;
		String endTag = "</" + word + ">";

		int start = in.indexOf(word) + word.length();

		int tegStart = in.indexOf(">", start) + 1;

		return cutString(in, endTag, tegStart);
	}

	String getTag(String in, String find){
		
		String openTeg, closedTeg;
		openTeg = "<" + find + ">";
		closedTeg = "</" + find + ">";

		return cutString(in, openTeg, closedTeg);

	}

	String getAttrValue(String in, String word, String attr){

		int start = in.indexOf(word) + word.length();
		
		int count = in.indexOf(">", start) - start;

		String insideTeg = new String(in.toCharArray(), start, count);

		String[] attrs = insideTeg.split(" ");

		String value = "";

		for(String a : attrs){
			if(a.indexOf(attr) != 0) continue;
			value = cutString(a, "\"");
		}

		return value;
	}




	private String cutString(String words, String word){
		int start, count;

		start = words.indexOf(word) + word.length();
		count = words.lastIndexOf(word) - start;

		return new String(words.toCharArray(), start, count);
	}



	private String cutString(String words, String startWord, String endWord){
		int start, count;

		start = words.indexOf(startWord) + startWord.length();
		count = words.indexOf(endWord, start) - start;

		return new String(words.toCharArray(), start, count);
	}

	private String cutString(String words, String endWord, int start){

		int count = words.indexOf(endWord, start) - start;

		return new String(words.toCharArray(), start, count);

	}


	public static void main(String[] args) {
		new ReadXML("test.xml");
	}
}