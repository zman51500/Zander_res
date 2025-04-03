package a1;
/** * Name: * Student ID: * Description of solution: */
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class Q2 {// Return all sums that can be formed from subsets of elements in arr
	public static ArrayList<Integer> allSums( ArrayList<Integer> arr ) {
		return null;
		}
	
	
	public static void main(String[] args) {
		//https://www.baeldung.com/java-file-to-arraylist
		ArrayList<Integer> result = new ArrayList<Integer>();//= 
		//Files.readAllLines(Paths.get("nums.txt"));
		try {
			BufferedReader br = new BufferedReader(new FileReader("nums.txt"));
			while (br.ready()) {
				result.add(Integer.getInteger(br.readLine()));
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
			ArrayList<Integer> sums = allSums(result);
			
			System.out.println("Testing...");
			assertEquals(sums.size(), 8);
			assertEquals(sums.contains(0), true);
			assertEquals(sums.contains(1), true);
			assertEquals(sums.contains(2), true);
			assertEquals(sums.contains(4), true);
			assertEquals(sums.contains(3), true);
			assertEquals(sums.contains(5), true);
			assertEquals(sums.contains(6), true);
			assertEquals(sums.contains(7), true);
			System.out.println("Success!");
	}	
}