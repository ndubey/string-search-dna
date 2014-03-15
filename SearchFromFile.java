//
//  SearchFromFile.java
//  
//
//  Created by Nandan Dubey on 3/25/09.
//  Copyright 2009 IIT, Kanpur. All rights reserved.
//
/*
 *ASUMPTION
 1) if ID occurs at start of line => start of new protein
 
 
 
 *
 */
import java.util.regex.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class SearchFromFile{
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter file name for search: ");
		String filename = in.readLine();
		File file = new File(filename);
		if(!filename.endsWith(".txt")){
			System.out.println("This is not a text file.");
			System.out.println("Please enter file name withe \".txt\" extension.");
			System.exit(0);
		}
		else if(!file.exists()){
			System.out.println("File not found.");
			System.exit(0);
		}
		
		FileChannel fileChannel = new FileInputStream(filename).getChannel();
		CharBuffer charBuffer = Charset.forName("8859_1").newDecoder().decode(
																			  fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, (int)fileChannel.size()));
		System.out.print("Enter string to search from the file: ");
		String string = in.readLine();
		Pattern pattern = Pattern.compile(string);
		Matcher matcher = pattern.matcher(charBuffer);
		
		int a = 0;
		while(matcher.find()){
			a++;
			String matchStrings = matcher.group();
			System.out.println(matchStrings);
		}
		System.out.println("Total occurance of the word in the file is: " + a);
	}
}