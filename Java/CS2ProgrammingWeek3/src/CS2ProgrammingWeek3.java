import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek3 
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
	//	Given a string and a non-empty substring sub, compute recursively if at 
	//	least n copies of sub appear in the string somewhere, possibly with 
	//	overlapping. N will be non-negative.

	//	subCopies("catcowcat", "cat", 2) → true
	//	subCopies("catcowcat", "cow", 2) → false
	//	subCopies("catcowcat", "cow", 1) → true
	
	/**
	 * 
	 * @param str, sub, n
	 * 		String str contains the original string to be tested against
	 * 		String sub contains the string that is used to test against str
	 * 		int n specifies how many copies of sub to check for
	 * 
	 * @return
	 * 		returns true if there are n copies of sub in str
	 * 		returns false if there are not n copies of sub in str
	 */
	static boolean subCopies(String str, String sub, int n) 
	{
		String Str = str.substring(0, sub.length());//create a substring of str the length of sub
		if(Str.equals(sub))//if the string and sub match, subract one from n
			n-=1;
		if(n<=0 && str.length() == sub.length())//if n = 0 and the string length and sub length are the same
			return true;
		else if(n>0&& str.length() == sub.length())//if n is not 0 and the str and sub length are the same
			return false;
		else//recursive step
		{
			str = str.substring(1, str.length());
			return subCopies(str,sub,n);
		}
	}

	//	Problem #2
	//	Given a non-negative int n, return the sum of its digits recursively 
	//	(no loops). Note that mod (%) by 10 yields the rightmost 
	//	digit (126 % 10 is 6), while divide (/) by 10 removes the 
	//	rightmost digit (126 / 10 is 12).

	//	sumDigitsInNumber(126) → 9
	//	sumDigitsInNumber(49) → 13
	//	sumDigitsInNumber(12) → 3
	
	/**
	 * 
	 * @param n
	 * 		int n contains integer to deal with.
	 * 
	 * @return integer
	 * 		integer that is the sum of each digit in int n.
	 */
	static int sumDigitsInNumber(int n) 
	{
		int i = n%10;//take the last digit
		int j = (int)n/10;//shift the number to the right
		if(n>10)
			return i+sumDigitsInNumber(j);//if the number is still greater than 10 return i + the function
		else
			return i;//else return i
	}	

	//	Problem #3
	//	Given base and n that are both 1 or more, compute recursively (no loops) 
	//	the value of base to the n power, so powerN(3, 2) is 9 (3 squared).

	//	exponential(3, 1) → 3
	//	exponential(3, 2) → 9
	//	exponential(3, 3) → 27
	
	/**
	 * 
	 * @param base, n
	 * 		int base containing the base of the term
	 * 		int n containing the exponent of the term
	 * 
	 * @return integer
	 * 		integer that corresponds with equating base to the n power
	 */
	static int exponential(int base, int n) 
	{
		if(n>=1)//if the exponent is greater than or equal to 1
			return base*exponential(base,n-1);//return the base time the function
		else
			return 1;//esle return 1
	}	

	//	Problem #4
	//	Given a string, compute recursively (no loops) a new string 
	//	where all the lowercase 'x' chars have been changed to 'y' chars. 

	//	changeXtoY("codex") → "codey"
	//	changeXtoY("xxhixx") → "yyhiyy"
	//	changeXtoY("xhixhix") → "yhiyhiy"
	
	/**
	 * 
	 * @param str
	 * 		String containing original string of chars to deal with
	 * 
	 * @return String
	 * 		String of characters where the lowercase x's have been changed to y's
	 */
	public static String changeXtoY(String str) 
	{
		char c = str.charAt(str.length()-1);//set the character to the last letter
		/*if(c == 'X')  This was to change uppercase xs to ys but that was not in the instructions
			c = 'Y';*/
		if(c == 'x')
			c = 'y';
		if(str.length()<=1)
		{
			if(c == 'x')
				c='y';
			return c+"";//if the end has been reached, return the last letter 
		}
		str = str.substring(0,str.length()-1);//shrink the string by 1
		return changeXtoY(str)+c;//return the letters recursively
	}
	
	//	Problem #5
	//	Given an array of ints, compute recursively if the array 
	//	contains a 6. We'll use the convention of considering only 
	//	the part of the array that begins at the given index. 
	//	In this way, a recursive call can pass index+1 to move down 
	//	the array. The initial call will pass in index as 0. 

	//	find6({1, 6, 4}, 0) → true
	//	find6({1, 4}, 0) → false
	//	find6({6}, 0) → true	
	
	/**
	 * 
	 * @param nums, index
	 * 		int[] list containing the original array of numbers
	 * 		int containing the position to start in nums
	 * 
	 * @return boolean
	 * 		returns true if a 6 is found in the array
	 * 		returns false if no 6 is found in the array
	 */
	static boolean find6(int[] nums, int index) 
	{
		if(nums[index]==6)//if the number at the index is 6
			return true;
		else if(nums.length-1 == index)//if the index is the size of the array
			return false;
		else
			return find6(nums,index+1);//recursive call to find if the next number is six
	}
	
	//	Problem #6
	//	Given a string, compute recursively a new string where all 
	//	the adjacent chars are now separated by a "*".   

	//	insertAsterisk("hello") → "h*e*l*l*o"
	//	insertAsterisk("abc") → "a*b*c"
	//	insertAsterisk("ab") → "a*b"
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars
	 * 
	 * @return AbridgedList
	 * 		String with an asterisk between each char
	 */
	static String insertAsterisk(String str) 
	{
		char c = str.charAt(str.length()-1);//take the char from the back
		if(str.length()==1)//if end of string
			return Character.toString(c);//return that char
		str = str.substring(0, str.length()-1);//shrink the string by 1
		return insertAsterisk(str)+"*"+c;//return the recursive call 
	}
	
	//	Problem #7
	//	We'll say that a "pair" in a string is two instances of 
	//	a char separated by a char. So "AxA" the A's make a pair. 
	//	Pair's can overlap, so "AxAxA" contains 3 pairs -- 2 for 
	//	A and 1 for x. Recursively compute the number of 
	//	pairs in the given string.  

	//	numberPairs("axa") → 1
	//	numberPairs("axax") → 2
	//	numberPairs("axbx") → 1
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars provided
	 * 
	 * @return 
	 * 		int with the number of pairs as defined above
	 */
	static int numberPairs(String str) 
	{
		if(str.length()>=3)//if the string 3 or above in length
		{
			char c = str.charAt(0),b = str.charAt(2);//set the variables to chars 1 char apart
			if(c == b)//if the variables equal eachother
				return 1+numberPairs(str.substring(1, str.length()));//return1 + the recursive call
			else
				return numberPairs(str.substring(1,str.length()));//else return the recursive call
		}
		return 0;//else return 0
	}
	
	//	Problem #8
	//	Given a string, return recursively a "cleaned" string where 
	//	adjacent chars that are the same have been reduced 
	//	to a single char. So "yyzzza" yields "yza".  

	//	reduceChars("yyzzza") → "yza"
	//	reduceChars("abbbcdd") → "abcd"
	//	reduceChars("Hello") → "Helo"
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars
	 * 
	 * @return 
	 * 		String with all repeated, adjacent chars are removed
	 */
	static String reduceChars(String str) 
	{
		if(str.length()>1)
		{
			char c = str.charAt(0),b = str.charAt(1);//set char variables at the first and second places in the string
			if(c == b)
				return reduceChars(str.substring(1, str.length()));//if the variales equal each other reduce
			else
				return c+reduceChars(str.substring(1,str.length()));//if they don't reduce and store the first variable
		}
		else
			return str;
	}
	
	//	Problem #9
	//	Given a string, return true if it is a nesting of zero or more 
	//	pairs of parenthesis, like "(())" or "((()))". Suggestion: 
	//	check the first and last chars, and then recur on what's inside them.  

	//	nestedParens("(())") → true
	//	nestedParens("((()))") → true
	//	nestedParens("(((x))") → false
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars
	 * 
	 * @return 
	 * 		returns true if there are zero or more pairs of parenthesis
	 * 		returns false if there are not zero or more pairs of parenthesis
	 */
	static boolean nestedParens(String str) 
	{
		if(str.length()>=1)//if the string is greater than 1
		{
			if(str.charAt(0)=='('&&str.charAt(str.length()-1)==')')// if the first and last eql paren
				return nestedParens(str.substring(1, str.length()-1));//recursive call
			else
				return false;//else
		}
		else
			return true;
	}
	
	//	Problem #10

	//	Given a string and a non-empty substring sub, compute 
	//	recursively the largest substring which starts and 
	//	ends with sub and return its length.  

	//	subStrSub("catcowcat", "cat") → 9
	//	subStrSub("catcowcat", "cow") → 3
	//	subStrSub("cccatcowcatxx", "cat") → 9
	
	/**
	 * 
	 * @param str, sub
	 * 		String containing the original chars to be tested against
	 * 		String containing the original chars to test with
	 * 
	 * @return 
	 * 		integer containing the largest number of chars in string 
	 * 		that start and end with sub
	 */
	static int subStrSub(String str, String sub) 
	{
		if(str.length()>=3)
		{
			String Str = str.substring(0, sub.length());//set substring of str 
			String Str1 = str.substring(str.length()-sub.length(), str.length());//set substring of str
			if(sub.equals(Str) && sub.equals(Str1))//if the substrings equal sub
				return str.length();//return the length
			if(Str != sub)//esle
				return subStrSub(str.substring(1, str.length()),sub);//shrink
			if(Str1 != sub)//else 
				return subStrSub(str.substring(0, str.length()-1),sub);//shrink
			else
				return 0;//else 
		}
		else return 0;
	}
	
	///////////////////////////////////////////
	//										 //
	// End of assignment code.				 //
	//										 //
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		int[] j = {1,2,3,4,5,6,7,8,9};
		boolean b = subCopies("catdogcat","cat",2);
		int i = sumDigitsInNumber(126);
		int i1 = exponential(3,3);
		String s = changeXtoY("xxxxxxxxxxxxxxxkajfdinfsojaejw");
		boolean b1 = find6(j,0);
		String s1 = insertAsterisk("Ernie");
		int i2 = numberPairs("axaxddlr");
		String s2 = reduceChars("wwwwhhhho aaaaarrreee yooooooouuu");
		boolean b2 = nestedParens("(())");
		int i3 = subStrSub("catcowcat","cow");
		System.out.print("subCopies: "+ b
				+"\nSum of Digits: "+i+"\nexponential: "+i1+"\nchange x to y: "+s
				+"\nfind6: "+b1+"\ninsert Asterisk: "+s1+"\nnumber Pairs: "+i2+"\nreduced Char: "
				+s2+"\nnested Parens: "+b2+"\nsubStrSub: "+i3);
		}
	
}