package a4;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;

/**
 * Template code for assignment 4, CS146
 * @author andreopo
 *
 *Zander Bonnet
 *012661623
 */
public class Sort {

	/**
	 * Build a random array
	 * @param rand a Random object
	 * @param LENGTH The range of the integers in the array 
	 *             will be from 0 to LENGTH-1
	 * @return
	 */
	private static int[] build_random_array(Random rand, int LENGTH) {
		int[] array = new int[LENGTH];
		//set index 0 to 0 for consistency with CLRS, where sorting starts at index 1
		array[0] = 0; 
		for (int i = 1; i < LENGTH; i++) {
	        // Generate random integers in range 0 to 999 
	        int rand_int = rand.nextInt(LENGTH); 
	        array[i] = rand_int;
		}
		return array;
	}
	
	/**
	 * Assert the array is sorted correctly
	 * @param array_unsorted The original unsorted array
	 * @param array_sorted The sorted array
	 */
	public static void assert_array_sorted(int[] array_unsorted, int[] array_sorted) {
		int a_sum = array_unsorted[0];
		int b_sum = array_sorted[0];
		for (int i = 1; i < array_unsorted.length; i++) {
			a_sum += array_unsorted[i];
	    }
		for (int i = 1; i < array_sorted.length; i++) {
			b_sum += array_sorted[i];
			assertTrue(array_sorted[i-1] <= array_sorted[i]);
	    }
		assertEquals(a_sum, b_sum);
	}
	
	// a helper function that swaps elements a and b with the array
	public static void swap(int[] array, int a, int b){
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	//sorts in place from left to right
	public static void insertionSort(int[] array) {
		for(int i = 1; i < array.length; i++)
			for(int j = i; j > 0; j--)
				if(array[j] < array[j-1])
					swap(array, j, j-1);
				else break;
	}

	//finds the smallest element in teh array and swaps it with at the front of the unsorted part of the array
	public static void selectionSort(int[] array){
		for(int i = 0; i < array.length - 1; i++) {
			int minIndex = i;
			for(int j = i + 1; j < array.length; j++) 
				if(array[minIndex] > array[j])
					minIndex = j;
			if(minIndex != i)
				swap(array, i, minIndex);
		}
	}
	
	// Return the index of the leftchild of the node currently at pos 
    private static int leftChild(int pos) 
    { 
        return (2 * pos); 
    } 
  
    // Return the index of the rightchild of the node currently at pos 
    private static int rightChild(int pos) 
    { 
        return (2 * pos) + 1; 
    } 


    //Function to heapify the node at position i, up to the position n 
    private static void maxHeapify(int[] HeapArray, int i, int n) 
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
        	swap(HeapArray, i, largest);
        	maxHeapify(HeapArray,largest, n);
        }
    	
    }
   
    // builds the maxheap
    public static void buildMaxHeap(int[] HeapArray) {
    	for (int i = (int) Math.floor(HeapArray.length/2) - 1; i > 0; i--)
            maxHeapify(HeapArray,i,HeapArray.length - 1); 
    }
    //transforms a maxheap array into a sorted array
	public static void heapSort(int[] array){
		buildMaxHeap(array);
    	for(int i = array.length - 1; i >= 2; i--) {
    		swap(array,1, i);
    		maxHeapify(array,1, i - 1);
    	}
	}
	
	//Recursively splits the array and then merges it
	public static void sortMerge(int[] array, int left, int right) {
		if(left < right) {
			int mid = (left+right)/2;
			
			sortMerge(array, left, mid);
			sortMerge(array,mid+1, right);
			merge(array,left,mid,right);
		}
	}
	
	//merges two sorted arrays into sorted order
	public static void merge(int[] array, int left, int mid, int right) {
		int leftSize = mid - left + 1 ;
		int rightSize = right - mid;
		
		int[] leftArr = new int[leftSize];
		int[] rightArr = new int[rightSize];
		
		/*Copy data to temp arrays*/
        for (int i=0; i<leftSize; ++i) 
            leftArr[i] = array[left + i]; 
        for (int j=0; j<rightSize; ++j) 
            rightArr[j] = array[mid + 1+ j]; 
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = left; 
        //checks the smallest value of each array and puts it in the array marked with index k
        while (i < leftSize && j < rightSize) 
        { 
            if (leftArr[i] <= rightArr[j]) 
            { 
                array[k] = leftArr[i]; 
                i++; 
            } 
            else
            { 
                array[k] = rightArr[j]; 
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of leftArr[] if any */
        while (i < leftSize) 
        { 
            array[k] = leftArr[i]; 
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of rightArr[] if any */
        while (j < rightSize) 
        { 
            array[k] = rightArr[j]; 
            j++; 
            k++; 
        }  	
	}

	//sorts the array by splitting it into individual arrays and then merging them in sorted order
	public static void mergeSort(int[] array){
		sortMerge(array, 0, array.length - 1);
		}
			
	//sorts the array around a partition pivot
	public static void sortQuick(int[] array, int start, int end) {
		if(start < end) {
			int partIndex = partition(array, start, end);
			sortQuick(array, start, partIndex - 1);
			sortQuick(array, partIndex + 1, end);
		}		
	}
	
	//partitions the array around a pivot
	public static int partition(int[] array, int start, int end) {
		int pivot = array[end];
		int i = start - 1;
		for(int j = start; j < end; j++) 
			if(array[j] <= pivot) {
				i++;
				swap(array, i, j);
			}
		swap(array, i + 1, end);
		return i+1;
		
	}
	
	//sorts an array using quicksort method
	public static void quickSort(int[] array){
		sortQuick(array, 0, array.length - 1);
	}

	// Splits the array into buckets, then sorts the buckets, and recreates the array in sorted order
	public static void bucketSort(int[] array) {
		int bucketCount = array.length/2;
		int minIntValue = 0;
		int maxIntValue = array.length - 1;
        // Create bucket array
        List<Integer>[] buckets = new List[bucketCount];
        // Associate a list with each index in the bucket array         
        for(int i = 0; i < bucketCount; i++){
            buckets[i] = new LinkedList<>();
        }
        
        // Assign integers from array to the proper bucket
        /**
         * TODO implement
         */
        for(int i = 0; i < array.length; i++) {
        	double bucket = Math.floor(((double)array[i] * (double)bucketCount)/(double)(maxIntValue + 1));
        	buckets[(int)bucket].add(array[i]);
        }
        // sort buckets
        for(List<Integer> bucket : buckets){
            Collections.sort(bucket);
        }
        int i = 0;
        // Merge buckets to get sorted array
        for(List<Integer> bucket : buckets){
            for(int num : bucket){
                array[i++] = num;
            }
        }
	}

	public static void main(String[] args) {
		int NUM_RUNS = 3;
        // create instance of Random class 
        Random rand = new Random(); 
        
        /////////////////////////////////////////
		int LENGTH=100;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_100 = build_random_array(rand, LENGTH);

		//For runtime computations
		long startTime, endTime;
		
		double duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		
		
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        /////////////////////////////////////////
		LENGTH=1000;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_1000 = build_random_array(rand, LENGTH);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		
		
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		
		
        /////////////////////////////////////////
		LENGTH=10000;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_10000 = build_random_array(rand, LENGTH);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		
		
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		
        /////////////////////////////////////////
		LENGTH=100000;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_100000 = build_random_array(rand, LENGTH);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		
		
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);
	}

}
