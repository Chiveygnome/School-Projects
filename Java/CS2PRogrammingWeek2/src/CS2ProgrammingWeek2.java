import java.util.Arrays;
import java.util.Scanner;



public class CS2ProgrammingWeek2 {
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
return( "Wheaton,Ernest,E3388709");
}

//	Problem #1
// Directions: Return true if the array contains, somewhere,
// three increasing consecutive numbers like ....4, 5, 6,... or
// 23, 24, 25.

//	FindThreeIncreasingNumbers({1, 4, 5, 6, 2}) → true
//	FindThreeIncreasingNumbers({1, 2, 3}) → true
//	FindThreeIncreasingNumbers({1, 2, 4}) → false

/**
* 
* @param NumberList
* 		int[] list containing some numbers.
* 
* @return
* 		returns true if there are three increasing consecutive numbers
* 		returns false if there are not three increasing consecutive numbers
*/
static boolean FindThreeIncreasingNumbers(int[] NumberList) 
{
	for(int i = 0; i<NumberList.length-2;i++)
	{
		if(NumberList[i]==NumberList[i+1]-1 && NumberList[i] == NumberList[i+2]-2)//check that the next two numbers 
			return true;//are consecutively increasing, if true return true, else return false
	}
	return false;
}

//	Problem #2
//	For each multiple of 10 in the given array, change all the values 
//	following it to be that multiple of 10, until encountering another 
//	multiple of 10. So {2, 10, 3, 4, 20, 5} yields {2, 10, 10, 10, 20, 20}.

//	multiplesOfTen({2, 10, 3, 4, 20, 5}) → {2, 10, 10, 10, 20, 20}
//	multiplesOfTen({10, 1, 20, 2}) → {10, 10, 20, 20}
//	multiplesOfTen({10, 1, 9, 20}) → {10, 10, 10, 20}

/**
* 
* @param NumberList
* 		int[] list containing some numbers.
* 
* @return NumberList
* 		int[] list of the same numbers changed to multiples of
* 		ten as they are encountered.
*/
static int[] multiplesOfTen(int[] NumberList) 
{
	for(int i = 1; i<NumberList.length-1;i++)
	{
		if(NumberList[i-1]%10 == 0)// the number is a multiple of ten 
			if(NumberList[i+1]%10!=0)//and the next number is not a multiple of ten
			NumberList[i] = NumberList[i-1];//make the number the multiple of ten 
	}
	return NumberList;//return the number list
}	

//	Problem #3
//	We'll say that an element in an array is "alone" if there are 
//	values before and after it, and those values are different 
//	from it. Return a version of the given array where every instance 
//	of the given value which is alone is replaced by whichever 
//	value to its left or right is larger.

//	CheckForAloneNumbers({1, 2, 3}, 2) → {1, 3, 3}
//	CheckForAloneNumbers({1, 2, 3, 2, 5, 2}, 2) → {1, 3, 3, 5, 5, 2}
//	CheckForAloneNumbers({3, 4}, 3) → {3, 4}

/**
* 
* @param NumberList, changingNumber
* 		int[] list containing some numbers.
* 		int value of the number that should change in the array.
* 
* @return NumberList
* 		int[] list of numbers where every occurrence of changingNumber
* 		has been replaced by the larger of its two neighbors.
*/
static int[] CheckForAloneNumbers(int[] NumberList, int changingNumber) 
{
	for(int i = 0; i<NumberList.length;i++)
	{
		if(NumberList[i] == changingNumber && NumberList[i-1]!=changingNumber && NumberList[i+1]!=changingNumber)
		{//checks to see if the number is an "alone number"
			if(NumberList[i-1]>NumberList[i+1])//if less than left
				NumberList[i] = NumberList[i-1];//make equal to left
			else
				NumberList[i] = NumberList[i+1];// else make equal to right
		}
		
	}
	return NumberList;//return the number list
}	

//	Problem #4
//	Return a version of the given array where each zero value in 
//	the array is replaced by the largest odd value to the right 
//	of the zero in the array. If there is no odd value to the 
//	right of the zero, leave the zero as a zero. 

//	ReplaceZerosWithLargestOdd({0, 5, 0, 3}) → {5, 5, 3, 3}
//	ReplaceZerosWithLargestOdd({0, 4, 0, 3}) → {3, 4, 3, 3}
//	ReplaceZerosWithLargestOdd({0, 1, 0}) → {1, 1, 0}

/**
* 
* @param NumberList
* 		int[] list containing some numbers.
* 
* @return NumberList
* 		int[] list containing the numbers where the zeros have been
* 		replaced with the largest odd number to the right of them.
*/
public static int[] ReplaceZerosWithLargestOdd(int[] NumberList) 
{
	int odd = 0;
	for(int i = NumberList.length;i>0;i--)//for loop to iterate through the loop
	{
		odd = 0;
		if(odd<NumberList[i+1] && NumberList[i+1]%2 != 0)//checks if the right number is larger and if it is odd
			odd = NumberList[i];//if it is then set the replace value to odd 
		if(NumberList[i] == 0)//if the value is 0  
			NumberList[i] = odd;//then set it to the odd value
	}
	return NumberList;//return the number list
}
	

//	Problem #5
//	Given start and end numbers, return a new array containing 
//	the sequence of integers from start up to but not including end, 
//	so start=5 and end=10 yields {5, 6, 7, 8, 9}. The end number 
//	will be greater or equal to the start number. 
//	Note that a length-0 array is valid. 

//	CreateIncreasingArray(5, 10) → {5, 6, 7, 8, 9}
//	CreateIncreasingArray(11, 18) → {11, 12, 13, 14, 15, 16, 17}
//	CreateIncreasingArray(1, 3) → {1, 2}	

/**
* 
* @param start, end
* 		Two integers stating the start and end of the sequence.
* 
* @return NumberList
* 		int [] containg numbers ranging from start to end
* 		in order from least to greatest.
*/
static int[] CreateIncreasingArray(int start, int end) 
{
	int[] array = null;//the array to a null array
	for(int i = 0;i<end-start;i++)
	{
		array = Arrays.copyOf(array, array.length+1);//allocate room for another value in the array
		array[i] = i+(end-start);//make the value one of the values between start and end
	}
	return array;
}

//	Problem #6
//	Given a non-empty array of ints, return a new array containing 
//	the elements from the original array that come before the 
//	first 4 in the original array. The original array will contain 
//	at least one 4. Note that it is valid in java to create 
//	an array of length 0.  

//	CopyNumbersBeforeFour({1, 2, 4, 1}) → {1, 2}
//	CopyNumbersBeforeFour({3, 1, 4}) → {3, 1}
//	CopyNumbersBeforeFour({1, 4, 4}) → {1}

/**
* 
* @param NumberList
* 		int[] list containing some numbers.
* 
* @return AbridgedList
* 		int[] list containing all the numbers that appeared
* 		before the first 4 in the array.
*/
static int[] CopyNumbersBeforeFour(int[] NumberList) 
{
	int j;
	int []a;
	for(j=0;j<NumberList.length;j++)//count until a four is encountered within the array
	{
		if(NumberList[j] == 4)//break at the four
			break;
	}
	a = new int[j];//allocate a new array sizeof j
	for(int i = 0;i<j-1;i++)
	{
		a[i]=NumberList[i];//copy the array
	}
	return a;
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
static int[] swap(int des,int loc, int[] array)//basic swap function
{
	int temp = array[des];
	array[des] = array[loc];
	array[loc] = temp;
	return array;
}
//	Problem #7
//	Return an array that contains the exact same numbers as 
//	the given array, but rearranged so that all the zeros 
//	are grouped at the start of the array. The order of the 
//	non-zero numbers does not matter. So {1, 0, 0, 1} becomes 
//	{0 ,0, 1, 1}. You may modify and return the 
//	given array or make a new array.  

//	MoveZerosToFront({1, 0, 0, 1}) → {0, 0, 1, 1}
//	MoveZerosToFront({0, 1, 1, 0, 1}) → {0, 0, 1, 1, 1}
//	MoveZerosToFront({1, 0}) → {0, 1}

/**
* 
* @param NumberList
* 		int[] list containing some numbers.
* 
* @return RearrangedList
* 		int[] list containing all the numbers from original
* 		list with the zeros moved to the front.
*/
static int[] MoveZerosToFront(int[] NumberList) 
{
	int place = 0;
	for(int i = 0; i<NumberList.length;i++)//iterate through the array
	{
		if(NumberList[i] == 0)//if value is a 0
		{
			NumberList = swap(place,i,NumberList);//move to the front
			place++;
		}
	}
	return NumberList;
}

//	Problem #8
//	Return an array that contains the exact same numbers as 
//	the given array, but rearranged so that all the even numbers 
//	come before all the odd numbers. Other than that, the 
//	numbers can be in any order. You may modify and 
//	return the given array, or make a new array.  

//	EvenFrontOddBack({1, 0, 1, 0, 0, 1, 1}) → {0, 0, 0, 1, 1, 1, 1}
//	EvenFrontOddBack({3, 3, 2}) → {2, 3, 3}
//	EvenFrontOddBack({2, 2, 2}) → {2, 2, 2}

/**
* 
* @param NumberList
* 		int[] list containing some numbers.
* 
* @return RearrangedList
* 		int[] list containing all the numbers from original
* 		list with the even numbers in the front and the
* 		odd numbers in the back.
*/
static int[] EvenFrontOddBack(int[] NumberList) 
{
	int place = 0;
	for(int i =0;i<NumberList.length;i++)
	{
		if(NumberList[i]%2 == 0)
		{
			NumberList = swap(place,i,NumberList);//same deal as last function, only catered to all even numbers
			place++;
		}
	}
	return NumberList;
}

///////////////////////////////////////////
//
// End of assignment code.
//
///////////////////////////////////////////

///////////////////////////////////////////
//
// Utility methods
//
//
///////////////////////////////////////////

/**
* Ask how many days late an assignment was turned in.
* 
* @return
* Return a number that is number of days * 10. This is the
*    penalty for turning in late.
*/
static double AskHowManyDaysLate()
{
Scanner in = new Scanner(System.in);
System.out.print("How many days late was this assignment turned in? ");
int nDaysLate = in.nextInt();
in.close();
double dPenaltyPercent = 0.0;
if( nDaysLate > 0 )
{
dPenaltyPercent = (double)nDaysLate * 20;
}
return( dPenaltyPercent );
}

public static void main(String[] args)
{

}

}
