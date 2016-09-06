import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 recitation assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2RecitationWeek1 
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
	
	// Write a method that recursively prints out all the 
	//   items in a linked list, in regular order. Fill in 
	//   the prototype below:

	static void PrintRec(DataStruct ds) 
	{
		System.out.print(ds.KeyValue+" ");//prints the keyvalue and a space
		if(ds.Next != null)//if the next node is not null
		{
			PrintRec(ds.Next);//recursive call to the function
		}
	}

	// Write a function that recursively prints out all 
	//   the items in a linked list, in reverse order. 
	//   Fill in the prototype below:

	static void PrintBackRec(DataStruct ds) //same as the previously mentioned function
	{
		System.out.print(ds.KeyValue + " ");//except it prints out backwards
		if(ds.Prev != null)
		{
			PrintBackRec(ds.Prev);//still recursive
		}
	}

	// Write a function that iterates through a linked list 
	//   and adds 5 to each even number in the list and 
	//   subtracts 4 from each odd number in the list. For
	//   each member of the list print the before and after values.

	static void EditList(DataStruct ds) 
	{
		if(ds.KeyValue%2 == 0)//if the value is even
			ds.KeyValue+=5;//add 5
		else
			ds.KeyValue-=4;//else subtract 4
		if(ds.Next != null)
			EditList(ds.Next);//recursive call if the next node isn't null
	}
	
	// Write a recursive function that takes in two linked 
	//   lists and determines if the lists are equivalent. 
	//   For two lists to be equivalent, they must have the 
	//   same number of items and each corresponding item must 
	//   be equal. Thus, the lists 3, 5, 7 and 3, 5, 7 are 
	//   equal, but 3, 7, 5 does not equal 3, 5, 7 or 3, 7. 
	//   (Hint: As a base case, two lists are equal if they 
	//   are both NULL, and not equal if one is NULL and the 
	//   other isn’t.) Return 1 if the two lists passed in are 
	//   equal, and 0 otherwise.

	static int EqualLists(DataStruct list1, DataStruct list2) 
	{
		if(list1 == null && list2 == null)//if they are both null they are even
			return 1;
		else if((list1!= null && list2 == null)||(list1 == null && list2 != null))//check to see if the length is uneven
			return 0;
		else
		{
			if(list1.KeyValue == list2.KeyValue)//if the values are equal to each other recursive call
				return EqualLists(list1.Next,list2.Next);
			else
				return 0;//else return 0
		}
	}

	// Write a function that takes in a pointer to the front 
	//   of a linked list and returns 1 if all the nodes in 
	//   the linked list are in sorted order (from smallest 
	//   to largest, with repeats allowed), and 0 otherwise. 
	//   The prototype is given below:

	static int InOrder(DataStruct list) 
	{
		if(list.Next != null)
		{
			if(list.KeyValue<= list.Next.KeyValue)//check if current value is less than next value
				return InOrder(list.Next);
			else //if not return 0
				return 0;
		}
		else
			return 1;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		DataStruct ds = new DataStruct();
		ds.KeyValue = 0;
		for( int i=0; i<4; i++ )
		{
			DataStruct ds4 = new DataStruct();
			ds4.KeyValue = i + 3;
			ds.AddToEndOfList(ds4);
		}
		
		DataStruct ds2 = new DataStruct();
		ds2.KeyValue = 1;
		for( int i=0; i<4; i++ )
		{
			DataStruct ds3 = new DataStruct();
			ds3.KeyValue = 2 + i;
			ds2.AddToEndOfList(ds3);
		}

		// Test code here...
		PrintRec( ds );
		System.out.println();
		PrintBackRec( DataStruct.Last );
		System.out.println();
		EditList( ds );

		PrintRec(ds);
		
		int nEqual = EqualLists( ds, ds2 );
		System.out.print("\nIs Equal: "+nEqual+"\n");
		int nInOrder = InOrder(ds);
		System.out.print("Is in order: "+nInOrder+"\n");
		nInOrder = InOrder(ds2);
		System.out.print("Is in order: "+nInOrder+"\n");
		
		

	}

}