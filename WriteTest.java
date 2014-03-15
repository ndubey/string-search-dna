//
//  WriteTest.java
//  
//
//  Created by Nandan Dubey on 3/25/09.
//  Copyright 2009 IIT, Kanpur. All rights reserved.
//
import java.io.*;
public class WriteTest {
			public static void main(String[] args)
			{	
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("test.txt"));
					out.write("aString");
					out.newLine();
					out.write("this is a");
					out.newLine();
					out.write("ttest");
					out.close();
				}
				catch (IOException e)
				{
					System.out.println("Exception ");		
				}
				
				return ;
			} // main ends here.
		}; // the class ends here.
