package a3;


/**
 * CS146 Assignment 3 Node class 
 * This class is used for undirected graphs represented as adjacency lists
 * @author andreopo
 *
 */
public class Node {
	private int name;
	public int distance;
	public boolean visited;
	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	private Node next;
	
	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node(int s) {
		name = s;
		next = null;
		distance = Integer.MAX_VALUE;
		visited = false;
	}

	public Node(int s, Node n) {
		name = s;
		next = n;
		distance = Integer.MAX_VALUE;
		visited = false;
	}
}

