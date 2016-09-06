import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek1 
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

	// Directions: Return the number of even ints in the given 
	//   array. Note: the % "mod" operator computes the remainder, 
	//   e.g. 5 % 2 is 1. 

	// CountEvenNumbersInArray({2, 1, 2, 3, 4}) → 3
	// CountEvenNumbersInArray({2, 2, 0}) → 3
	// CountEvenNumbersInArray({1, 3, 5}) → 0
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		int with the number of even numbers in NumberList
	 */
	static int CountEvenNumbersInArray(int[] NumberList) 
	{
		int evenNums = 0;//initialize variable to be used
		for(int i = 0; i<NumberList.length;i++)//simple for loop for iterating through the loop
		{
			if( NumberList[i]%2 == 0)//mod to check if the number is even or odd
			{
				evenNums++;//if even add 1 to the evenNums variable
			}
		}
		return evenNums;//return the amount of evenNums
	}

	// Given an array of ints, return true if the array contains no 
	//   1's and no 3's.

	// LookForLucky13({0, 2, 4}) → true
	// LookForLucky13({1, 2, 3}) → false
	// LookForLucky13({1, 2, 4}) → false
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		returns false if there is a 1 or 3 in the list.
	 * 		returns true if there are no 1s or 3s in the list.
	 */
	static boolean LookForLucky13(int[] NumberList) 
	{
		for(int i = 0; i<NumberList.length;i++)//loop to iterate through the array
		{
			if(NumberList[i] == 1 || NumberList[i] == 3)//check to see whether there is a 1 or 3 in the array
				return false;//if there is then return false
		}
		return true;//if not return true
	}	

	// Given arrays NumberList1 and NumberList2 of the same length, 
	//   for every element in NumberList1, consider the 
	//   corresponding element in NumberList2 (at the same index). 
	//   Return the count of the number of times that the two 
	//   elements differ by 2 or less, but are not equal. 

	// MatchUpLists({1, 2, 3}, {2, 3, 10}) → 2
	// MatchUpLists({1, 2, 3}, {2, 3, 5}) → 3
	// MatchUpLists({1, 2, 3}, {2, 3, 3}) → 2
	
	static int MatchUpLists(int[] NumberList1, int[] NumberList2) 
	{
		int MatchUpNums = 0;//initialize variable used to store how many entries in the array meet the standard
		for(int i = 0; i<NumberList1.length;i++)//for loop to iterate trough the array
		{
			if(NumberList1[i] != NumberList2[i] && ((NumberList1[i] <= NumberList2[i]+2 && NumberList1[i] >= NumberList2[i])))
			{//if statement that checks if the numbers are equal to each other, if not then it checks
			 //to see if the number is in the +/-2 range
				MatchUpNums++;//if all these are met, then add one to the variable
			}
		}
		return MatchUpNums;//return the variable
	}	

	// Given an array of ints, return true if the array 
	//   contains either 3 even or 3 odd values all next 
	//   to each other. 

	// ModThreeNumbers({2, 1, 3, 5}) → true
	// ModThreeNumbers({2, 1, 2, 5}) → false
	// ModThreeNumbers({2, 4, 2, 5}) → true
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		return true if there are three consecutive evens
	 * 			or three consecutive odds
	 * 
	 * 		otherwise returns false
	 */
	public static boolean ModThreeNumbers(int[] NumberList) 
	{
		int even = 0,odd = 0;//initialize counting variables
		for(int i = 0; i<NumberList.length;i++)//for loop to iterate and check for odds
		{
			odd++;
			if(NumberList[i]%2 == 0)//check to see if number is even
				odd = 0;//if it set counter to 0
			if(odd == 3)//if counter reaches 3 return true
				return true;
		}
		for(int i = 0; i<NumberList.length;i++)//another for loop to check the evens
		{
			even++;
			if(NumberList[i]%2 != 0)//check to see if number is odd
				even = 0;//if it is reset counter to 0
			if(even == 3)//if counter reaches 3 return true
				return true;
		}
		return false;//if no triplets return false
	}

	// Return the "centered" average of an array of ints, 
	//   which we'll say is the mean average of the values, 
	//   except ignoring the largest and smallest values in 
	//   the array. If there are multiple copies of the 
	//   smallest value, ignore just one copy, and likewise 
	//   for the largest value. Use int division to produce 
	//   the final average. You may assume that the array is 
	//   length 3 or more. 

	// FindCenteredAverage({1, 2, 3, 4, 100}) → 3
	// FindCenteredAverage({1, 1, 5, 5, 10, 8, 7}) → 5
	// FindCenteredAverage({-10, -4, -2, -4, -2, 0}) → -3	
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		Average of the list of numbers without the
	 * 		first of the lowest numbers and the last of the
	 * 		highest numbers.
	 */
	static int FindCenteredAverage(int[] NumberList) 
	{
		int average = 0,low = NumberList[0],high = low;//initialize all variables
		boolean exemptHigh = false,exemptLow = true;// initialize checker variables
		for(int i = 0; i<NumberList.length;i++)//for loop to find the high and low in the array
		{
			if(NumberList[i] < low)//if current number is less than low
				low = NumberList[i];//make current number new low
			else if(NumberList[i] > high)//else if current number is greater than current high
				high = NumberList[i];//make current number new high
		}
		for(int i = 0; i<NumberList.length;i++)//for loop to add all the numbers together
		{
			if(NumberList[i] == low && !exemptLow)//if the current number is the low and it hasn't been 
			{//exempted yet
				average-=NumberList[i];//subtract it 
				exemptLow = true;//mark as exempted
			}
			else if(NumberList[i] == high && !exemptHigh)//if the current number is the high and it  
			{//hasn't been exempted yet
				average-=NumberList[i];//subtract it 
				exemptHigh = true;// mark as exempted
			}
			average+=NumberList[i];//add the current number to the sum
		}
		return average/(NumberList.length -2);//return the centered average
	}
	
	// Given an array of ints, return true if every 2 that 
	//   appears in the array is next to another 2. 

	// LookForTwoTwo({4, 2, 2, 3}) → true
	// LookForTwoTwo({2, 2, 4}) → true
	// LookForTwoTwo({2, 2, 4, 2}) → false
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		true if every 2 is adjacent to another 2
	 * 		otherwise false
	 */
	static boolean LookForTwoTwo(int[] NumberList) 
	{
		int twos = 0;//initialize counter variable
		boolean twosStarted = false;//initialize checker variable
		for(int i = 0;i<NumberList.length;i++)//for loop to count the number of twos in the array
		{
			if(NumberList[i] == 2)//if current number is a 2
				twos++;//add one to the counter
		}
		for(int i = 0;i<NumberList.length;i++)//for loop to check if the twos occur next to each other  
		{
			if(!twosStarted && NumberList[i] == 2)//if the twos haven't started and the current number
				twosStarted = true;//is 2 set the checker to true
			if(twosStarted && twos>0 && NumberList[i]!=2)//if the check is true and there are twos left
				return false;//but the current value isn't 2 return false
			else if(twosStarted && twos>0)//else if there are twos left and the value is 2
				twos--;//subtract 1 from the count
			if(twos == 0)//if twos equals 0 return true
				return true;
		}
		return false;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		int[] a1 = {0,1,2,3,4,5,6,7,8,9,10};
		int[] a2 = {1,5,3,8,9,5,8,2,3,5,1};
		int[] a3 = {1,3,2,2,2,2,5,6,7,3,9};
		int even = CountEvenNumbersInArray(a1);
		boolean Luck = LookForLucky13(a1);
		int Match = MatchUpLists(a1,a2);
		boolean Three = ModThreeNumbers(a2);
		int center = FindCenteredAverage(a3);
		boolean twos = LookForTwoTwo(a3);
		System.out.print("Even numbers: "+even+"\nLucky 13: "+Luck+"\nMatchups: "+Match+
				"\nMod three in a row: "+Three+"\nCentered Average: "+center+"\nAll twos in a row: "+twos);
	}
	
}