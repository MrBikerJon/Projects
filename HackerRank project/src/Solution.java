import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int counter = 0;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        String AString = in.nextLine();
        String[] AStringArray = AString.split(" ");
        System.out.println(Arrays.toString(AStringArray));
        
        int[] A = new int[AStringArray.length];
        for(int i = 0;i < n;i++)
        {
           A[i] = Integer.parseInt(AStringArray[i]);
        }
        System.out.println(Arrays.toString(A));
        
        for(int i = 0; i < n; i++) {
        	if (A[i] < 0) {
        		counter++;
        	}
    		int test = A[i];
        	for (int j = i+1; j < A.length - i; j++) {
        		test = test + A[j];
        		if (test < 0) {
        			counter++;
        		}
        	}
        	// Problem - program only counts forwards from current position, need to count backwards too
        }
        
        System.out.println(counter);
        
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    in.close();
    }
}