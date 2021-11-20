import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        
        String A = "madam";
        
        String myString = "";
        
        int i = A.length()-1;
        while (i >=0) {
        	myString = myString + A.charAt(i);
        	i--;

        }
        
        
        if (myString.equals(A)) {
            System.out.println("Yes");
        } else System.out.println("No");

        /* Enter your code here. Print output to STDOUT. */
        
    }
}