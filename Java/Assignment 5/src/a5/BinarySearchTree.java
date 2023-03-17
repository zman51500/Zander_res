package a5;

import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;

/**
 * This is an implementation of a Binary Search Tree (BST).
 * @author andreopo
 *
 * Zander Bonnet 012661623
 */
public class BinarySearchTree {

	/**
	 * Class containing key (name) for a node and right and left children
	 */
	class Node {

		private String key;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}


		private Node left;

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}


		private Node right;

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		Node(String key) {
			this.key = key;
			right = null;
			left = null;
		}
	}

	/**
	 * The root of the binary search tree (BST)
	 */
	Node root;

	// Constructor 
	BinarySearchTree() {  
		root = null;  
	} 

	// Search for a given key in BST
	public Node search(String key) 
	{ 
		Node node = null;
		node = searchRecursive(root, key);
		return node;
	}

	// Implement the search recursively in this helper function
	Node searchRecursive(Node root_node, String key) 
	{ 
		// Base Cases: root is null or key is present at root 
		if(root_node==null)
			return null;
		if (root_node.key==key) 
			return root_node; 

		// Checks if key is less than root_node. If so, recurse
		if (root_node.key.compareTo(key) > 0) { 
			return searchRecursive(root_node.left, key); 
		}

		// Checks if key is greater than root_node. If so, recurse
		if(root_node.key.compareTo(key) < 0) {
			return searchRecursive(root_node.right, key); 
		}
		return root_node;
	}

	// Insert a new Node with a key and value in BST
	public void insert(String key) {
		root = insertRecursive(root, key); 
	}

	// Implement the insert recursively in this helper function
	Node insertRecursive(Node root_node, String key) { 
		// If the tree is empty, return a new node
		if (root_node == null) { 
			root_node= new Node(key); 
			return root_node; 
		} 
		//System.out.println("root_node "+ root_node.getKey());
		// Otherwise, recurse down the tree
		if (root_node.key.compareTo(key) > 0) 
			root_node.left = insertRecursive(root_node.left, key); 
		else if (root_node.key.compareTo(key) < 0) 
			root_node.right = insertRecursive(root_node.right, key); 
		return root_node;
	} 


	// A recursive inorder traversal of the BST 
	void inorder(Node root, ArrayList<String> strList) { 
		if (root != null) { 
			inorder(root.getLeft(), strList); 
			System.out.println(root.getKey()); 
			strList.add(root.getKey());
			inorder(root.getRight(), strList); 
		}
	}

	public static void main(String[] args) throws IOException { 

		//For runtime computations
		long startTime, endTime;
		double duration = 0;


		startTime = System.currentTimeMillis();
		BinarySearchTree bst = new BinarySearchTree(); 
		FileReader fr = new FileReader("data/names.shuffled.unique.lowercase.txt");
//		FileReader fr = new FileReader("data/names.txt");
		BufferedReader br = new BufferedReader(fr);
		boolean done = false;
		while(!done) {
			String line = br.readLine();
			if(line == null)
				done = true;
			else 
				bst.insert(line);
		}
		br.close();
		fr.close();
		endTime = System.currentTimeMillis();
		duration += ((double) (endTime - startTime));
		System.out.println("BST insertion runtime is " + duration);

		/**
		 * So an inorder traversal of the tree, ensure the result is 
		 * order lexicographically
		 */
		ArrayList<String> strList = new ArrayList<String>();
		bst.inorder(bst.root, strList);
		//Ensure the inorder traversal gives a 
		//lexicographic ordering of the names
		for (int i = 1; i < strList.size(); i++) {
			//System.out.println(strList.get(i-1) + " " + strList.get(i));
			assertTrue(strList.get(i-1).compareTo(strList.get(i)) <= 0  );
		}

		/**
		 * Verify that search returns the correct result
		 */
		startTime = System.currentTimeMillis();
		Node n = bst.search("aaaa");
		assertEquals(n, null);
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("BST search runtime for aaaa is " + duration);


		startTime = System.currentTimeMillis();
		n = bst.search("alice");
		assertEquals(n.getKey(), "alice");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("BST search runtime for alice is " + duration);


		startTime = System.currentTimeMillis();
		n = bst.search("zoe");
		assertEquals(n.getKey(), "zoe");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("BST search runtime for zoe is " + duration);

	}
}
