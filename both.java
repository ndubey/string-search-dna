//
//  pair.java
//  
//
//  Created by Nandan Dubey on 3/26/09.
//  Copyright 2009 IIT, Kanpur. All rights reserved.
//

/*
 *
 *
 */
import java.util.regex.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;


public class both {
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter file name to search from(file having pairs): ");
		String pairFilename = in.readLine();
		File pairFile = new File(pairFilename);
		System.out.print("Enter the first file name containing items to search: ");
		String filename1 = in.readLine();
		File file1 = new File(filename1);
		System.out.print("Enter the second file name containing items to search: ");
		String filename2 = in.readLine();
		File file2 = new File(filename2);
		System.out.println("The output will be in file ");
		FileOutputStream out; // declare a file output object
		PrintStream p = null;
		String line1, line2;
		if(!pairFilename.endsWith(".txt")){
			System.out.println("This is not a text file.");
			System.out.println("Please enter file name withe \".txt\" extension.");
			System.exit(0);
		}
		else if(!pairFile.exists()){
			System.out.println("File not found.");
			System.exit(0);
		}
		try
		{
			out = new FileOutputStream("both.txt");
			p = new PrintStream( out );
			p.println ("***************Common *******************");
		}
		catch (Exception e)
		{
			System.err.println ("Error writing to file");
		}
		int counter = 0, pos = 1;
		String to_print = "";
		String temp = "";
		LineNumberReader lineReader1 = null;
		LineNumberReader lineReader2 = null;
		StringTokenizer stringTokenizer;
		boolean flag = false;
		String first = null;
		String second = null;
		boolean done = false;
		boolean end = true;
		try {
			lineReader1 = new LineNumberReader( new FileReader(pairFile) );
			while ((line1 = lineReader1.readLine()) != null)
			{
				stringTokenizer = new StringTokenizer(line1);
				to_print = stringTokenizer.nextToken();
				//System.out.println("checking for pair line "+ line1);
				
				while(stringTokenizer.hasMoreTokens()){
					temp = stringTokenizer.nextToken();
					if(temp.indexOf("DIP") == -1){
						end = false;
						if(pos == 1){
							first = temp;
							second = null;
							pos++;
						}
						else
						{
							//System.out.println("actual pair "+ line1);
							second = temp;
							pos = 1;
						}			
					}
				}
				if(end)
					first = null;
				end = true;
				pos = 1;
				lineReader2 = new LineNumberReader( new FileReader(filename1) );
				if(first == null){
					System.out.println("seems that end of file is reached quiting ..  "+ line1);
					System.out.println("total of "+counter+"number of proteins cause disease");
					p.println("total of "+counter+"number of proteins cause disease");
					System.exit(0);
				}
				if(second == null){
					if(search(file1,first)&&search(file2,first)){
						//System.out.println("first is matched with "+ line2);
						counter++;
						p.println (to_print);
					}
				}
				else{
					if(search(file1,first)&&search(file2,first)&&search(file2,second)&&search(file2,second))
					{
						//System.out.println("either of first or second is matched with "+ line2);
						counter++;
						p.println (to_print);
					}
				}
			}
			p.println("total of "+counter+"number of proteins cause disease");
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
		finally {
			try {
				if (lineReader1!= null) lineReader1.close();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		try
		{
			p.close();
		}
		catch (Exception e)
		{
			System.err.println ("Error writing to file");
		}
		System.out.println("total of "+counter+"number of proteins cause disease");
	}
	
	public static boolean search(File file, String to_search){
		boolean flag = false;
		String line;
		LineNumberReader lineReader = null;
		try {
			lineReader = new LineNumberReader( new FileReader(file) );
			while ((line = lineReader.readLine()) != null)
			{
				//System.out.println("reading line "+ line);
				if((line.indexOf(to_search) != -1) )
				{	
					return true;
				}
			}
			return false;
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
		return false;
	}
	
}
