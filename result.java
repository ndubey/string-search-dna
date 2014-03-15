//
//  final.java
//  
//
//  Created by Nandan Dubey on 3/26/09.
//  Copyright 2009 IIT, Kanpur. All rights reserved.
//
/*
 * the code is really easy to understand :D :) 
 it will make Disease.txt as output file
 *ASUMPTION
 1) if ID<3space> occurs only at the start of new protein
 2) DR<3 spaces>PDB occurs only when protein has 3d structure
 
 
 
 *
 */
import java.util.regex.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class result {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter file name for search: ");
		String filename = in.readLine();
		File file = new File(filename);
		FileOutputStream out; // declare a file output object
		PrintStream p = null;
		String line;
		if(!filename.endsWith(".txt")){
			System.out.println("This is not a text file.");
			System.out.println("Please enter file name withe \".txt\" extension.");
			System.exit(0);
		}
	else if(!file.exists()){
		System.out.println("File not found.");
		System.exit(0);
	}
	try
	{
		out = new FileOutputStream("Disease.txt");
		p = new PrintStream( out );
		p.println ("***************Disease *******************");
	}
	catch (Exception e)
	{
		System.err.println ("Error writing to file");
	}
		int counter = 0;
	String to_print = "";
	String to_match1 = "ID   ";//ID
	String to_match2 = "CC   -!- DISEASE";//DISEASE (CC<3 spaces>-!-<1 space>DISEASE)
	File aFile = new File(filename);
	LineNumberReader lineReader = null;
	StringTokenizer stringTokenizer;
	boolean flag = false;
	try {
		lineReader = new LineNumberReader( new FileReader(aFile) );
		while ((line = lineReader.readLine()) != null)
		{
			//System.out.println("reading line "+ line);
			if((line.indexOf(to_match1) != -1)|| (flag) )
			{
				//System.out.println("matched ID");
				if(!flag)//ID is read because flag is false
					line = lineReader.readLine();
				stringTokenizer = new StringTokenizer(line);
				stringTokenizer.nextToken();
				to_print = stringTokenizer.nextToken();
				int index = to_print.indexOf(';');
				to_print = to_print.substring(0,index);
				while ((line = lineReader.readLine()) != null)
				{
					//System.out.println("reading line "+ line);
					if((line.indexOf(to_match1) != -1) || (line.indexOf(to_match2) != -1))
					{
						if((line.indexOf(to_match2) > -1))//found disease
						{
							counter++;
							//System.out.println("matched a disease");
							p.println (to_print);
						}
						else//no disease in this protein
							flag = true;
						break;//analysis of this protein is complete
					}
				}
			}
		}
	}
	catch (IOException ex){
		ex.printStackTrace();
	}
	finally {
		try {
		if (lineReader!= null) lineReader.close();
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

}
