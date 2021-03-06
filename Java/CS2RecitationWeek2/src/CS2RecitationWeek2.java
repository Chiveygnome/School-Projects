import java.util.Random;
import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 recitation assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2RecitationWeek2
{

	///////////////////////////////////////////
	//
	// Start of assignment code.
	//
	///////////////////////////////////////////

	/**
	 * Returns the last name, first name, and PID of the student.
	 * 
	 * This is required in order to get credit for the programming assignment.
	 */
	static String GetNameAndPID()
	{
		return( "Wheaton,Ernie,e3388709");
	}
	/**
	 * 
	 * @param des
	 * 		int that points to the destination in the array
	 * @param loc
	 * 		int that describes the initial location in the array
	 * @param array
	 * 		int[] that holds all values
	 */
	static int[] swap(int des,int loc, int[] array)
	{
		int temp = array[des];
		array[des] = array[loc];
		array[loc] = temp;
		return array;
	}
	
	static int[] DoBubbleSort( int[] DataIn )
	{
		for(int i = 0; i<DataIn.length-1;i++)
		{
			for(int j = 0; j<DataIn.length-1;j++)
			{
				if(DataIn[j]>DataIn[j+1])
					DataIn = swap(j,j+1,DataIn);
			}
		}
		return DataIn;
	}
    
   	static int[] DoInsertSort( int[] DataIn )
    {
    		int j, Val;
     		for(int i = 0; i<DataIn.length;i++)
    		{
    			Val = DataIn[i];
    			j=i;
    			while(j>0 && DataIn[j-1] > Val)
    			{
    				DataIn[j] = DataIn[j-1];
    			}
    			DataIn[j] = Val;
    		}
     	return DataIn;
	}

    static int[] DoHeapSort( int[] DataIn )
	{
    	int end;
    	heapify(DataIn,DataIn.length);
    	end = DataIn.length - 1;
    	while(end > 0)
    	{
    		swap(DataIn,end,0);
    		end-=1;
    	}
    	heapify(DataIn,1);
	}

	// The heap (min-heap).
	static int[] nHeap;

	// Randomly generated numbers to be used as input.
	static int[] nRandomNumbers;

	// The size of the heap, nHeap. The size of the heap may be smaller than
	// nHeap.length. The heap is comprised of the elements at indexes between 0
	// and heapSize-1 of nHeap.
	static int heapSize;

	// Methods to get index of parent, left child, and right child of node at
	// index nIndex of the heap  (zero-based index). The indexes outputted by
	// GetLeft and GetRight must be verified to actually exist within the heap
	// (use heapSize to determine this).

	private static int GetParentIndex(int nIndex)
	{
		if(nIndex == 0)
			return 0;
		else if(nIndex > heapSize)
			return 0;
		else
		{
			return nHeap[(nIndex - 1)/2];
		}
	}

	static int GetLeft( int nIndex )
	{
		if(nIndex>heapSize)
			return 0;
		else
		{
			return (nIndex*2)+1;
		}
	}
	
	static int GetRight( int nIndex )
	{
		if(nIndex>heapSize)
			return 0;
		else
			return (nIndex*2)+2;
	}

	// Function to swap two numbers in an array. Auxiliary method to SiftUp and
	// Heapify.
    	public static void swap(int arr[], int i, int j)
    	{
    	}
	
	static void BuildHeap()
	{
		// Input: static field nHeap, an array of ints, representing a full
		// binary tree (not a BST), except for the last level which may be
		// only partially filled from the left

		// Input: static field heapSize 

		// Output: modified nHeap such that it represents a min-heap  

		// Complexity: O(heapSize)
		for(int i = heapSize/2;i>=1;i--)
		{
			Heapify(i);
		}
		// Start at the parent of the last node (i.e., the right-most,
		// bottom-most node in the tree that is not a leaf)
		//    - the last node is heapSize - 1
		//    - the parent of any node is (nodeIdx - 1) / 2
		//    - therefore the parent of the last node is
		//      (heapSize - 2) / 2
		//    - equivalent to GetParentIndex(heapSize-1)

		// Algorithm:

		// for i = GetParentIndex(heapSize-1) DOWN TO  0:
		//      Heapify(i)
	}
	
	
	static void Heapify( int nIndex )
	{
		// Input: static field nHeap, an array of ints, representing a full
		// binary tree (not a BST), except for the last level which may be
		// only partially filled from the left
		int leftIdx, rightIdx;
		// Input: nIndex, an index into nHeap
		
		// Output: modified nHeap such that element nIndex has been "sifted
		// down" until that element is less than its children or that element
		// is a leaf 

		// Another name for this method might be "SiftDown".

		// Algorithm:
		if(nIndex*2 >= heapSize || nIndex*2+1 >= heapSize)
			return;
		// If nIndex is a leaf node (has no children), then there is nothing to
		// do (use heapSize to determine this)
		leftIdx = GetLeft(nIndex);
		rightIdx = GetRight(nIndex);
		// leftIdx <- GetLeft(nIndex)
		// rightIdx <- GetRight(nIndex)
		if(nHeap[leftIdx] <= nHeap[rightIdx] && nHeap[leftIdx] <= nHeap[nIndex])
		{
			nHeap = swap(nIndex,leftIdx,nHeap);
			Heapify(leftIdx);
		}
		else if(nHeap[rightIdx] <nHeap[leftIdx] && nHeap[rightIdx] < nHeap[nIndex])
		{
			nHeap = swap(nIndex,rightIdx,nHeap);
			Heapify(rightIdx);
		}
		else
			return;
		// smallest <- either the index leftIdx, rightIdx, or nIndex whose
		// corresponding heap node value is the smallest out of these three
		// (however, ensure that leftIdx and rightIdx are actually children;
		// use heapSize to determine this)
		
		// if smallest != nIndex:
		//    COMMENT: This means the node at nIndex has a child smaller than
		//    itself at index smallest. This smallest child needs to come up
		//    and replace the node at nIndex, and the node at nIndex needs to
		//    come down to replace the smallest child. We then repeat the
		//    process recursively at index smallest.

		//    swap node values at indexes smallest and nIndex
		//    Heapify(smallest)
	}


	// The following methods, AddElement and its auxiliary method SiftUp, are
	// not used with DoHeapSort. 

	// DoHeapSort uses BuildHeap instead, which is a more efficient way (O(n))
	// to build a heap starting from a list of numbers than AddElement. The way
	// to build a heap using AddElement is to start with an empty heap and
	// repeatedly add each element into the heap. This way is O(nlogn).
	// However, AddElement is useful to add new elements into an existing heap
	// once it has been built with BuildHeap.

	static void AddElement( int nNumber )
	{
		// Input: static fields nHeap, heapSize. nHeap must already be a valid
		// min-heap.

		// Input: nNumber, a new node to add to the heap

		// Output: heap with nNumber added, heapSize inceased by 1

		// Complexity: O(log(heapSize))
		if(heapSize+1>nHeap.length)
		{	
		// Precondition: heapSize+1 <= nHeap.length
		// If this is not the case, before running the algorithm, create a new
		// array that is double the size of nHeap, copy the elements of nHeap
		// into it, and assign nHeap to be the new larger array
			int[] newHeap;
			newHeap = new int[2*nHeap.length];
			System.arraycopy(nHeap, 0, newHeap, zero, nHeap.length);
			nHeap = new int[newHeap.length];
			nHeap = newHeap;
		}
		// Algorithm:
		heapSize+=1;
		nHeap[heapSize-1] = nNumber;
		SiftUp(heapSize-1);
		// Increase heapSize by 1
		// Copy nNumber to index heapSize-1 of the heap
		// SiftUp(heapSize-1)
	}
	
	static void SiftUp( int nNodeIndex )
	{
		int parent;
		parent = GetParentIndex(nNodeIndex);
		if(nHeap[parent]>nHeap[nNodeIndex])
		{
		// repeatedly sift up the element at nNodeIndex as long as its parent
		// node is greater or the element is the root (this is similar to
		// Heapify but it moves the element upwards instead of downwards)
			nHeap = swap(parent,nNodeIndex,nHeap);
			SiftIp(parent);
		}
	}

	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////

	public static void setRandomArray(int n)
	{
		Random rnd = new Random();
		nRandomNumbers = new int[n];
		for( int i=0; i<nRandomNumbers.length; i++ )
		{
			nRandomNumbers[i] = rnd.nextInt( 500 );
		}
	}
	
	public static void main(String[] args)
	{
		// Code to test bubble sort.
		setRandomArray(15);
		int[] bubbleStudentOut1 = DoBubbleSort(nRandomNumbers);
		int[] bubbleStudentOut2 = DoBubbleSort(BubbleIn);
		DisplayIntegerArray(bubbleStudentOut1);
		DisplayIntegerArray(bubbleStudentOut2);
		
		// Code to test insert sort.
		setRandomArray(15);
		int[] insertStudentOut1 = DoInsertSort(nRandomNumbers);
		int[] insertStudentOut2 = DoInsertSort(InsertIn);
		DisplayIntegerArray(insertStudentOut1);
		DisplayIntegerArray(insertStudentOut2);

		// Code to test heap sort.
		setRandomArray(15);
		int[] heapStudentOut1 = DoHeapSort(nRandomNumbers);
		int[] heapStudentOut2 = DoHeapSort(HeapIn);
		DisplayIntegerArray(heapStudentOut1);
		DisplayIntegerArray(heapStudentOut2);

		// Code to test AddElement
		nHeap = new int[HeapIn.length];
		heapSize = 0;
		for (int i = 0; i < HeapIn.length; i++)
		{
			AddElement(HeapIn[i]);
		}

		// Show the array representation of the min-heap
		DisplayIntegerArray(nHeap);
	}
}