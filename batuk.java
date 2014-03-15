//
//  batuk.java
//  
//
//  Created by Nandan Dubey on 3/25/09.
//  Copyright 2009 IIT, Kanpur. All rights reserved.
//

	
	
	
	import java.io.*;
	import java.util.regex.*;
	
public class batuk {
		
		/**
		 * Verifies that each line of a text file starts with:
		 *   \N + tab + integer + tab + text
		 * where "text" contains word characters (\w).
		 *
		 * The corresponding regular expression is:
		 *   "^\\N\t(\d)+\t(\w)+"
		 *
		 * The String passed to Pattern needs to double every backslash:
		 *   "^\\\\N\\t(\\d)+\\t(\\w)+"
		 *
		 * If a line is not of the expected pattern, then an
		 * IllegalStateException is thrown.
		 */
		public static void findBadLines( final File aFile ) {
			//...checks on aFile are elided
			
			//Pattern and Matcher are used here, not String.matches(regexp),
			//since String.matches(regexp) would repeatedly compile the same
			//regular expression
			Pattern regexp = Pattern.compile("^\\\\N\\t(\\d)+\\t(\\w)+");
			Matcher matcher = regexp.matcher("");
			
			LineNumberReader lineReader = null;
			try {
				lineReader = new LineNumberReader( new FileReader(aFile) );
				String line = null;
				while ((line = lineReader.readLine()) != null){
					matcher.reset( line ); //reset the input
					if ( !matcher.find() ) {
						String msg = "Line " + lineReader.getLineNumber() + " is bad: " + line;
						throw new IllegalStateException(msg);
					}
				}
			}
			catch (FileNotFoundException ex) {
				ex.printStackTrace();
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
		}
		
		/**
		 * Test harness.
		 */
		public static void main (String... arguments) {
			final File file = new File( "/Users/nandan/Desktop/sth" );
			batuk.findBadLines( file );
		}
	} 

