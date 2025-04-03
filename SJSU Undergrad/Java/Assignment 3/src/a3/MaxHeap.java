/**
 * 
 */
package a3;

import static org.junit.Assert.*;

/**
 * Zander Bonnet 
 * 012661623
 */
public class MaxHeap {

    private int[] HeapArray; 
    public int[] getHeapArray() {
		return HeapArray;
	}

	private int size; 
    private int maxsize; 
  
    private static final int FRONT = 1; 
  
    public MaxHeap(int maxsize) 
    { 
        this.maxsize = maxsize; 
        this.size = maxsize;
        HeapArray = new int[maxsize]; 
        //initialize heap array to a set of numbers, rearranged a little
        for (int i = FRONT; i < HeapArray.length; i++) {
        	HeapArray[i] = maxsize-i;
        }
    } 
  
    // Return the index of the parent of the node currently at pos 
    private int parent(int pos) 
    { 
        return (pos / 2); 
    } 
  
    // Return the index of the leftchild of the node currently at pos 
    private int leftChild(int pos) 
    { 
        return (2 * pos); 
    } 
  
    // Return the index of the rightchild of the node currently at pos 
    private int rightChild(int pos) 
    { 
        return (2 * pos) + 1; 
    } 


    //Function to heapify the node at position i, up to the position n 
    private void maxHeapify(int i, int n) 
    { 
    	// sets i to the largest 
    	int largest = i;
        int left = leftChild(i);
        int right = rightChild(i);
        
        //checks if the left leaf is larger
        if(left <= n && HeapArray[left] > HeapArray[i])
        	largest = left;
        //checks if the right leaf is larger
        if(right <= n && HeapArray[right] > HeapArray[largest])
        	largest = right;
        //if i is not the largest it swaps the two
        if(largest != i) {
        	swap(i, largest);
        	maxHeapify(largest, n);
        }
    	
    }

    // builds the macheap
    public void buildMaxHeap() {
    	for (int i = (int) Math.floor(size/2) - 1; i > 0; i--)
            maxHeapify(i,size); 
    }

    //sorts the heap in post order traversal
    public void sort() {
    	buildMaxHeap();
    	for(int i = size - 1; i >= 2; i--) {
    		swap(1, i);
    		maxHeapify(1, i - 1);
    	}
    }
    // Swap two nodes of the heap at index first second
    private void swap(int first, int seconds) 
    { 
        int tmp; 
        tmp = HeapArray[first]; 
        HeapArray[first] = HeapArray[seconds]; 
        HeapArray[seconds] = tmp; 
    } 
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int SIZE = 30;
		MaxHeap heap = new MaxHeap(SIZE);
		heap.sort();
		int[] heapArr = heap.getHeapArray();
		assertEquals(heapArr[0], 0);
		assertEquals(heapArr[1], 1);
		assertEquals(heapArr[2], 2);
		assertEquals(heapArr[SIZE-1], SIZE-1);
		assertEquals(heapArr.length, SIZE);
		for(int i = 0; i < SIZE; i++)
			System.out.print(heapArr[i] + ", ");
		System.out.println();
		
		//finds the sum of the two smallest values
		System.out.println("The minimum sum is:");
		System.out.println();
		System.out.print(heapArr[0] + "+");
		System.out.print(heapArr[1] + "=");
		System.out.println(heapArr[0] + heapArr[1]);
		System.out.println("Done");
	}

}
