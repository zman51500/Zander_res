package a3;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.LinkedList;


/**
 * CS146 Assignment 3 Node class 
 * This class is used for undirected graphs represented as adjacency lists
 * @author andreopo
 *
 * Zander Bonnet
 * 012661623
 */

public class NetworkAdjList {

	private static LinkedList<Integer>[] adjacencyList;
	
	public static void createAdjacencyList() throws IOException {
		// the largest key in the file plus 1
		int max_num_vertices = 4039;
		//initialize array 
		adjacencyList = new LinkedList[max_num_vertices];
		//initialize array elements(objects of LinkedList)
		for (int j=0; j<max_num_vertices; j++)
		    adjacencyList[j] = new LinkedList<Integer>();
		
		File f = new File("data/3980.edges");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		// runs until there are no more lines to read
		boolean done = false;
		while(!done)
		{
			String line = br.readLine();
			if(line == null)
				done = true;
			// splits the line at the space and converts the string numbers
			// to ints
			else {
				String[] numbs = line.split(" ");
				int a = Integer.parseInt(numbs[0]);
				int b = Integer.parseInt(numbs[1]);
				//System.out.println(a + " " + b);
				addFriend(a,b);
			}
		}
		br.close();
		fr.close();
	}
	// Adds user b to user a's friends list.  Not reversed because the reciprocal
	// also occurs in the list
	public static void addFriend(int a, int b) {
		adjacencyList[a].add(b);
	}
	
	//Checks to see if two users are friends in one direction
	public static boolean areFriends(int A, int B) {
		if(adjacencyList[A].contains(B))
			return true;
		return false;
	}
	
	public static void BFStraversal(int start) {
		/**
		 * Not needed for now
		 */
	}

	public static void main(String[] args) throws IOException {
		createAdjacencyList();
		
		/**
		 * These test cases assume the file 3980.edges was used
		 */
		System.out.println("Testing...");
		assertEquals(areFriends(4038, 4014), true);
		System.out.println("1 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(3982, 4037), true);
		System.out.println("2 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(4030, 4017), true);
		System.out.println("3 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(4030, 1), false);
		System.out.println("4 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(1, 4030), false);
		System.out.println("5 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(4003, 3980), true);
		System.out.println("6 of 7");
		
		System.out.println("Testing...");
		assertEquals(areFriends(3985, 4038), false);
		System.out.println("7 of 7");
		System.out.println("Success!");
	}

}
