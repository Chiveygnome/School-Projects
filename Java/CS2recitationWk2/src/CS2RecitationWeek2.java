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
		return( "Wheaton,Ernest,e3388709");
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
	static int[] swap(int des,int loc, int[] array)//swap function.  I didnt realize an in-place example was provided
	{
		int temp = array[des];//set temp to the destination var
		array[des] = array[loc];//set the destinatio to the swapped var
		array[loc] = temp;//replace the swapped var with the destination var
		return array;//return the whole array
	}
	
	static int[] DoBubbleSort( int[] DataIn )
	{
		for(int i = 0; i<DataIn.length-1;i++)//double for loop of array length - 1 to iterate through the array
		{//as many times as possible to sort every thing in order, lowest to highest
			for(int j = 0; j<DataIn.length-1;j++)
			{
				if(DataIn[j]>DataIn[j+1])
					DataIn = swap(j,j+1,DataIn);
			}
		}
		return DataIn;//return the sorted array
	}
    
   	static int[] DoInsertSort( int[] DataIn )
    {
    		int j, Val;
     		for(int i = 0; i<DataIn.length;i++)//for loop to iterate through the array
    		{
    			Val = DataIn[i];//set val to the value of the array at i
    			j=i;//set j to i
    			while(j>0 && DataIn[j-1] > Val)//go through the array backwards, starting at j while j is greater
    			{//than 0 and greater than the value below it.
    				DataIn[j] = DataIn[j-1];//move everything down 1
    				j--;
    			}
    			DataIn[j] = Val;//the value at the bottom of the swap chain is equal to Val
    		}
     	return DataIn;//return the sorted array
	}

    static int[] DoHeapSort( int[] DataIn )
	{
    	int n = DataIn.length-1,v;//initialize two variables
    	for(int i = n/2;i>=0;i--)//create heap out of the inputted array
    	{
    		siftDown(i,DataIn,n+1);
    	}
    	for(int i = n;i>0;i--)//sort heap back into an array
    	{
    		v=DataIn[0];
    		DataIn[0]=DataIn[i];
    		siftDown(0,DataIn,i);
    		DataIn[i]=v;
    	}
    	return DataIn;//return sorted array
	}
    static void siftDown(int i, int[] arr, int j)
    {
    	int a = arr[i];//initialize a to the value in the array at i
    	int b =2*i+1;//initialize b to the left leaf
    	while(b<=j-1)//while the the next left leaf is still within the heap
    	{
    		if(b<j-1 && arr[b]<arr[b+1])//if the leaf is in the heap and the left leaf is greater than the right leaf
    			b++;//move down the heap
    		if(a>=arr[b])//if the left leaf is less than the right leaf
    			break;//break out of the loop
    		arr[i]=arr[b];//the array at i is equal to the array at b
    		i=b;//
    		b=2*i+1;
    	}
    	arr[i]= a;
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
		if(nIndex == 0)//if root
			return 0;//return root
		else if(nIndex > heapSize)//if out side of heap
			return 0;//return root
		else
		{
			return (nIndex - 1)/2;//else return the parent
		}
	}

	static int GetLeft( int nIndex )
	{
		if(nIndex>heapSize)//if outside of heap
			return -1;//return flag
		else
		{
			return (nIndex*2);//else return left leaf
		}
	}
	
	static int GetRight( int nIndex )
	{
		if(nIndex>heapSize)//if outside return flag
			return -1;
		else
			return (nIndex*2)+1;//else return right leaf
	}

	// Function to swap two numbers in an array. Auxiliary method to SiftUp and
	// Heapify.
    	public static void swap(int arr[], int i, int j)//include swap funcion, see line 38-44
    	{
    		int temp = arr[i];
    		arr[i] = arr[j];
    		arr[j] = temp;
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
		int L, R;
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
		L = GetLeft(nIndex);
		R = GetRight(nIndex);
		// leftIdx <- GetLeft(nIndex)
		// rightIdx <- GetRight(nIndex)
		if(nHeap[L] <= nHeap[R] && nHeap[L] <= nHeap[nIndex])
		{
			nHeap = swap(nIndex,L,nHeap);
			Heapify(L);
		}
		else if(nHeap[R] <nHeap[L] && nHeap[R] < nHeap[nIndex])
		{
			nHeap = swap(nIndex,R,nHeap);
			Heapify(R);
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
			System.arraycopy(nHeap, 0, newHeap, 0, nHeap.length);
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
		if(nHeap[parent]>nHeap[nNodeIndex] && nNodeIndex >= 0)
		{
		// repeatedly sift up the element at nNodeIndex as long as its parent
		// node is greater or the element is the root (this is similar to
		// Heapify but it moves the element upwards instead of downwards)
			nHeap = swap(parent,nNodeIndex,nHeap);
			SiftUp(parent);
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
	public static int[] setRandomArray1(int n)
	{
		Random rnd = new Random();
		int[] a = new int[n];
		for(int i = 0; i<n;i++)
		{
			a[i] = rnd.nextInt(1000);
		}
		return a;
	}
	public static void DisplayIntegerArray(int[] a)
	{
		for(int i = 0;i<a.length;i++)
		{
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		
		// Code to test bubble sort.
		setRandomArray(15);
		int[] BubbleIn = setRandomArray1(15);
		int[] bubbleStudentOut1 = DoBubbleSort(nRandomNumbers);
		int[] bubbleStudentOut2 = DoBubbleSort(BubbleIn);
		DisplayIntegerArray(bubbleStudentOut1);
		DisplayIntegerArray(bubbleStudentOut2);
		
		// Code to test insert sort.
		setRandomArray(15);
		int[] InsertIn = setRandomArray1(15);
		int[] insertStudentOut1 = DoInsertSort(nRandomNumbers);
		int[] insertStudentOut2 = DoInsertSort(InsertIn);
		DisplayIntegerArray(insertStudentOut1);
		DisplayIntegerArray(insertStudentOut2);

		// Code to test heap sort.
		setRandomArray(15);
		int[] HeapIn = setRandomArray1(15);
		int[] heapStudentOut1 = DoHeapSort(nRandomNumbers);
		int[] heapStudentOut2 = DoHeapSort(HeapIn);
		DisplayIntegerArray(heapStudentOut1);
		DisplayIntegerArray(heapStudentOut2);

		// Code to test AddElement
		nHeap = new int[HeapIn.length];
		heapSize = 0;
		Random rnd = new Random();
		for (int i = 0; i < HeapIn.length; i++)
		{
			AddElement(rnd.nextInt(100));
		}

		// Show the array representation of the min-heap
		DisplayIntegerArray(nHeap);
	}
}