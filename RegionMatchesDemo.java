//
//  RegionMatchesDemo.java
//  
//
//  Created by Nandan Dubey on 3/25/09.
//  Copyright 2009 IIT, Kanpur. All rights reserved.
//

public class RegionMatchesDemo {
			public static void main(String[] args) {
			
			String searchMe = "Green Eggs and Ham";
			String findMe = "Eggs";
			int len = findMe.length();
			boolean foundIt = false;
			
			int i = 0;
			while (!searchMe.regionMatches(i, findMe, 0, len)) {
				i++;
				foundIt = true;
			}
			if (foundIt) {
				System.out.println(searchMe.substring(i, i+len));
			}
		}

}
