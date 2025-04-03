package a1;

/**
 * Name: Zander Bonnet
 * Student ID: 01221623
 * Description of solution: The function takes a string and finds all of its
 * potential substrings. consecutive and none consecutive.
*/
import java.util.ArrayList;
import static org.junit.Assert.*;

public class Q1 {
	private static ArrayList<String> subs = new ArrayList<String>();
	
	//This method takes a string and finds all of the possible substrings
	public static ArrayList<String> allSubstrings(String s){
		//base case
		if(s == "")
			return subs;
		else{
			String substring = "";
			//checks to see if the input is in the Arraylist. If not it is added
			if(!subs.contains(s))
			{
				subs.add(s);
			}
			//removes letters to allow for all substrings to be found
			for(int i = s.length(); i >= 0; i--){
				for(int j = 1; j <= i; j++){
					substring = s.substring(0, j - 1) + s.substring(j,i);					
					if(!subs.contains(substring)) {
						subs.add(substring);
						allSubstrings(substring);
						}
					}
				}
			}
		return subs;
	}
	
	
	public static void main(String[] args){
		ArrayList<String> s = allSubstrings("abcde");
		for(String test: s)
			System.out.println(test);
		System.out.println("Testing...");
		assertEquals(s.size(), 32);
		/*
		 * These are all the 32 substrings that should be contained in Q1. 
		 */
		assertEquals(s.contains(""), true);
		assertEquals(s.contains("a"), true);
		assertEquals(s.contains("b"), true);
		assertEquals(s.contains("c"), true);
		assertEquals(s.contains("d"), true);
		assertEquals(s.contains("e"), true);
		assertEquals(s.contains("ab"), true);
		assertEquals(s.contains("ac"), true);
		assertEquals(s.contains("ad"), true);
		assertEquals(s.contains("ae"), true);
		assertEquals(s.contains("bc"), true);
		assertEquals(s.contains("bd"), true);
		assertEquals(s.contains("be"), true);
		assertEquals(s.contains("cd"), true);
		assertEquals(s.contains("ce"), true);
		assertEquals(s.contains("de"), true);
		assertEquals(s.contains("abc"), true);
		assertEquals(s.contains("abd"), true);
		assertEquals(s.contains("abe"), true);
		assertEquals(s.contains("acd"), true);
		assertEquals(s.contains("ace"), true);
		assertEquals(s.contains("ade"), true);
		assertEquals(s.contains("bcd"), true);
		assertEquals(s.contains("bce"), true);
		assertEquals(s.contains("bde"), true);
		assertEquals(s.contains("cde"), true);
		assertEquals(s.contains("abcd"), true);
		assertEquals(s.contains("abce"), true);
		assertEquals(s.contains("acde"), true);
		assertEquals(s.contains("abde"), true);
		assertEquals(s.contains("bcde"), true);
		assertEquals(s.contains("abcde"), true);
		System.out.println("Success!");
	}
}
