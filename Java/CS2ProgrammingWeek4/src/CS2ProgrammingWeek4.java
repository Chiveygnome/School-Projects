import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek4 
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
	
	//	Problem #1
	//	Given an array of ints, is it possible to choose a group 
	//	of some of the ints, such that the group sums to the given 
	//	target? This is a classic backtracking recursion problem. 
	//	Once you understand the recursive backtracking strategy in 
	//	this problem, you can use the same pattern for many problems to
	//	search a space of choices. Rather than looking at the whole array, 
	//	our convention is to consider the part of the array starting at 
	//	index start and continuing to the end of the array. The caller 
	//	can specify the whole array simply by passing start as 0. No loops 
	//	are needed -- the recursive calls progress down the array. 

	//	groupSumsTarget(0, {2, 4, 8}, 10) → true
	//	groupSumsTarget(0, {2, 4, 8}, 14) → true
	//	groupSumsTarget(0, {2, 4, 8}, 9) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget(int start, int[] nums, int target) 
	{
		if(target == 0) return true;//if the target number is 0, then then numbers are included
		if(target < 0) return false;//if the target number has gone below 0, then the numbers are not whats needed
		if(start >= nums.length) return false;//else if the end of the array is reached, false
		return groupSumsTarget(start+1,nums,target-nums[start]) || groupSumsTarget(start +1, nums, target);
		//recursive back tracking method by having the function return either next number included or excluded
		//if one of these return true, return true
	}
	

	//	Problem #2
	//	Given an array of ints, is it possible to choose a group of 
	//	some of the ints, beginning at the start index, such that 
	//	the group sums to the given target? However, with the additional 
	//	constraint that all 6's must be chosen. (No loops needed.)

	//	groupSumsTarget6(0, {5, 6, 2}, 8) → true
	//	groupSumsTarget6(0, {5, 6, 2}, 9) → false
	//	groupSumsTarget6(0, {5, 6, 2}, 7) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including all 6's in the group
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget6(int start, int[] nums, int target) 
	{
		if(target == 0) return true;
		if(target < 0) return false;
		if(start >= nums.length) return false;
		if(nums[start] == 6) return groupSumsTarget6(start+1,nums,target - nums[start]);
		//including 6s is mandatory for this function
		else return groupSumsTarget6(start + 1, nums, target - nums[start]) || groupSumsTarget6(start +1, nums, target);
	}	
	
	//	Problem #3
	//	Given an array of ints, is it possible to choose a group of some 
	//	of the ints, such that the group sums to the given target with this 
	//	additional constraint: If a value in the array is chosen to be in 
	//	the group, the value immediately following it in the array 
	//	must not be chosen. (No loops needed.)

	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 12) → true
	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 14) → false
	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 7) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTargetNoAdj(int start, int[] nums, int target) 
	{
		if(target == 0) return true;
		if(target < 0 ) return false;
		if(start>=nums.length) return false;
		return groupSumsTargetNoAdj(start + 2, nums, target - nums[start]) || groupSumsTargetNoAdj(start + 1, nums, target);
		//if number is included, next number has to be excluded
	}	

	//	Problem #4
	//	Given an array of ints, is it possible to choose a group of some 
	//	of the ints, such that the group sums to the given target with these 
	//	additional constraints: all multiples of 5 in the array must be 
	//	included in the group. If the value immediately following a multiple 
	//	of 5 is 1, it must not be chosen. (No loops needed.) 

	//	groupSumsTarget5(0, {2, 5, 10, 4}, 19) → true
	//	groupSumsTarget5(0, {2, 5, 10, 4}, 17) → true
	//	groupSumsTarget5(0, {2, 5, 10, 4}, 12) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget5(int start, int[] nums, int target) 
	{
		if(target == 0) return true;
		if(start >= nums.length) return false;
		if(nums[start]%5 == 0)//if nultiple of 5
		{
			if(nums[start + 1] == 1) return groupSumsTarget5(start + 2, nums, target - nums[start]);//if following value is 1, exclude it
			return groupSumsTarget5(start + 1, nums, target - nums[start]);
		}
		return groupSumsTarget5(start + 1, nums, target - nums[start]) || groupSumsTarget5(start + 1, nums, target);
	}
	
	//	Problem #5
	//	Given an array of ints, is it possible to choose a group of some of 
	//	the ints, such that the group sums to the given target, with this 
	//	additional constraint: if there are numbers in the array that are adjacent 
	//	and the identical value, they must either all be chosen, or none of 
	//	them chosen. For example, with the array {1, 2, 2, 2, 5, 2}, either all 
	//	three 2's in the middle must be chosen or not, all as a group. (one loop 
	//	can be used to find the extent of the identical values). 

	//	groupSumsTargetClump(0, {2, 4, 8}, 10) → true
	//	groupSumsTargetClump(0, {1, 2, 4, 8, 1}, 14) → true
	//	groupSumsTargetClump(0, {2, 4, 4, 8}, 14) → false	
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTargetClump(int start, int[] nums, int target) 
	{
		int i = 1;
		if(target == 0) return true;
		if(start+i >= nums.length|| target < 0) return false;
		while(nums[start + i-1] == nums[start + i])//loop to find the "clumps" of similar numbers
		{
			i++;
			if(start+i>=nums.length)
			{
				i--;
				break;
			}
		}
		return groupSumsTargetClump(start + i, nums, target - (i*nums[start])) || groupSumsTargetClump(start+1, nums, target);
		//include "clumps" by multiplying the first number by how many there are and subtracting that from the target
	}
	
	//	Problem #6
	//	Given an array of ints, is it possible to divide the ints into two 
	//	groups, so that the sums of the two groups are the same. Every int must 
	//	be in one group or the other. Write a recursive helper method that takes 
	//	whatever arguments you like, and make the initial call to your recursive 
	//	helper from splitArray(). (No loops needed.)    

	//	divideArray({2, 2}) → true
	//	divideArray({2, 3}) → false
	//	divideArray({5, 2, 3}) → true
	
	/**
	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met
	 */
	static boolean divideArray(int[] nums) //first function
	{
		int i =0,j = 0;//set the groups to 0
		return divideArray1(0,nums,i,j);//go to helper
	}
	static boolean divideArray1(int start, int[] nums, int group1, int group2)
	{
		if(group1 == group2 && start >= nums.length)return true;//if the groups are the same and start is at the end of the array retur true
		if(start >= nums.length) return false;//else if start is at the end of the array, return false
		return divideArray1(start+1, nums, group1 + nums[start],group2) || divideArray1(start+1,nums, group1, group2 + nums[start]);
		//add the current number to either left or right, if one returns true, return true
	}
	
	//	Problem #7
	//	Given an array of ints, is it possible to divide the ints into two groups, 
	//	so that the sum of one group is a multiple of 10, and the sum of the 
	//	other group is odd. Every int must be in one group or the other. Write 
	//	a recursive helper method that takes whatever arguments you like, and 
	//	make the initial call to your recursive helper from 
	//	splitOdd10(). (No loops needed.)  

	//	oddDivide10({5, 5, 5}) → true
	//	oddDivide10({5, 5, 6}) → false
	//	oddDivide10({5, 5, 6, 1}) → true
	
	/**
	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met 
	 */
	static boolean oddDivide10(int[] nums) 
	{
		int i = 0,j = 0;
		return oddDivide101(0,nums,i,j);
	}
	
	static boolean oddDivide101(int start, int[] nums, int group1, int group2)
	{
		if((group1%10 == 0 && group2%2 == 1) || (group2%10 == 0 && group1%2 == 1)) return true;
		if(start >= nums.length) return false;
		return oddDivide101(start+1, nums, group1 + nums[start], group2) || oddDivide101(start+1, nums,group1, group2+ nums[start]);
	}
	
	//	Problem #8
	//	Given an array of ints, is it possible to divide the ints into 
	//	two groups, so that the sum of the two groups is the same, with 
	//	these constraints: all the values that are multiple of 5 must 
	//	be in one group, and all the values that are a multiple of 3 
	//	(and not a multiple of 5) must be in the other. (No loops needed.)  

	//	divide53({1,1}) → true
	//	divide53({1, 1, 1}) → false
	//	divide53({2, 4, 2}) → true
	
	/**
	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met
	 */
	static boolean divide53(int[] nums) 
	{
		int i = 0, j= 0;
		return divide531(0,nums,i,j);
	}
	
	static boolean divide531(int start, int[] nums, int group5s, int group3s)
	{
		if(group5s == group3s) return true;
		if(start >= nums.length) return false;
		if(nums[start]%5 == 0) return divide531(start+1,nums,group5s+nums[start],group3s);
		if(nums[start]%3 == 0 && nums[start]%5 != 0) return divide531(start + 1, nums, group5s, group3s+nums[start]);
		return divide531(start+1, nums, group5s+nums[start],group3s) || divide531(start+1, nums, group5s, group3s+nums[start]);
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		boolean b,b1,b2,b3,b4,b5,b6,b7;
		int[] i = {0,1,2,3,4,5,6,7,8,9},j = {1,1,1,1,2,3,4,5,5,6,7}, k = {3,5,15,20,6,24,8};
		b = groupSumsTarget(0,i,1);
		b1 = groupSumsTarget6(0,i,20);
		b2 = groupSumsTargetNoAdj(0,j,15);
		b3 = groupSumsTarget5(0,j,24);
		b4 = divideArray(j);
		b5 = oddDivide10(i);
		b6 = divide53(k);
		b7 = groupSumsTargetClump(0,j,21);
		System.out.println("method 1: "+b+"\nmethod 2: "+b1+"\nmethod 3: "+b2+"\nmethod 4: "+b3+"\nmethod 5: "+b4+"\nmethod 6: "+ b5+ "\nmethod 7: "+b6+"\nmethod 8: "+b7);
	}
	
}