package com.shikhir.datascience.StrWrangler4j;

import java.util.Arrays;
import java.util.logging.Logger;


public class StringOps {
	private static final Logger log = Logger.getLogger(StringOps.class.getName());
	
	public static int binarySearch(String[] arr, String x) 
    { 
        int l = 0, r = arr.length - 1; 
        while (l <= r) { 
            int m = l + (r - l) / 2; 
  
            int res = x.compareTo(arr[m]); 
  
            // Check if x is present at mid 
            if (res == 0) 
                return m; 
  
            // If x greater, ignore left half 
            if (res > 0) 
                l = m + 1; 
  
            // If x is smaller, ignore right half 
            else
                r = m - 1; 
        } 
  
        return -1; 
    } 
	public static String cleanText(String text){
		text = text.replaceAll("[^\\p{ASCII}]","");
		text = text.replaceAll("\\s+", " "); 
		text = text.replaceAll("\\p{Cntrl}", ""); 
		text = text.replaceAll("[^\\p{Print}]", "");
		text = text.replaceAll("\\p{C}", "");
		return text;
	}

	public static void sortStringArray(String[] str) {
		Arrays.sort(str);
		
		for(String s: str) {
			System.out.print("\""+s+"\",");
		}
	}

}
