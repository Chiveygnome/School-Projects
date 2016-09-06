import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek6
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
		return("Wheaton,Ernest,e3388709");
	}
	
	//	Problem #1
	//	Given a string, count the number of words ending in 'y' 
	//	or 'z' -- so the 'y' in "heavy" and the 'z' in "fez" count, 
	//	but not the 'y' in "yellow" (not case sensitive). We'll say 
	//	that a y or z is at the end of a word if there is not an 
	//	alphabetic letter immediately following it. (Note: 
	//	Character.isLetter(char) tests if a char is an alphabetic letter.) 

	//	wordEndYZ("fez day") → 2
	//	wordEndYZ("day fez") → 2
	//	wordEndYZ("day fyyyz") → 2
	
	/**
	 * 
	 * @param str
	 * 		str containing the original string
	 * 
	 * @return int
	 * 		int containing the # of words that end in y or z
	 */
	static int wordEndYZ(String str) 
	{
		int j = 0;		
		if(str.length()<1) return j;
		for(int i = 0;i<str.length();i++)
		{
			if((str.charAt(i) == ' ')&& (str.charAt(i-1) == 'y' || str.charAt(i-1) == 'z'))
			{
				j++;
			}
			if(i == str.length()-1 && (str.charAt(i) == 'y' || str.charAt(i) == 'z'))
				j++;
		}
		return j;	
	}

	//	Problem #2
	//	Given two strings, base and remove, return a version of the base 
	//	string where all instances of the remove string have been removed 
	//	(not case sensitive). You may assume that the remove string is length 
	//	1 or more. Remove only non-overlapping instances, so with "xxx" 
	//	removing "xx" leaves "x".

	//	removeFromBase("Hello there", "llo") → "He there"
	//	removeFromBase("Hello there", "e") → "Hllo thr"
	//	removeFromBase("Hello there", "x") → "Hello there"
	
	/**
	 * 
	 * @param base, remove
	 * 		base contains original string of characters
	 * 		remove contains original string that is to be removed from base
	 * 
	 * @return
	 * 		String containing the base with all instances of remove taken out
	 */
	static String removeFromBase(String base, String remove) 
	{
		String str;
		if(base.length()<remove.length() && base != remove) return base;
		str = base.substring(0, remove.length());
		if(str.equals(remove)) return removeFromBase(base.substring(str.length(),base.length()),remove);
		else return base.charAt(0) + removeFromBase(base.substring(1,base.length()),remove);
		
	}	

	//	Problem #3
	//	Given a string, return true if the number of appearances of 
	//	"is" anywhere in the string is equal to the number of appearances 
	//	of "not" anywhere in the string (case sensitive). 

	//	equalAppearance("This is not") → false
	//	equalAppearance("This is notnot") → true
	//	equalAppearance("noisxxnotyynotxisi") → true
	
	/**
	 * 
	 * @param str
	 * 		str contains the original string of characters
	 * 
	 * @return
	 * 		returns true if "is" appears as many times as "not"
	 * 		returns false if "is" does not appear as many times as "not"
	 */
	static boolean equalAppearance(String str) 
	{
		String str0, str1;
		int is = 0,not = 0, i = 0;
		while(i<=str.length()-3)
		{
			str0 = str.substring(i,i+2);
			str1 = str.substring(i, i+3);
			if(str0.equals("is")) is++;
			if(str1.equals("not")) not++;
			i++;
		}
		if(is == not) return true;
		else return false;
	}	

	//	Problem #4
	//	We'll say that a lowercase 'g' in a string is "happy" if there 
	//	is another 'g' immediately to its left or right. Return true if 
	//	all the g's in the given string are happy. 

	//	gisHappy("xxggxx") → true
	//	gisHappy("xxgxx") → false
	//	gisHappy("xxggyygxx") → false
	
	/**
	 * 
	 * @param str
	 * 		String containing original string of characters
	 * 
	 * @return
	 * 		returns true if 'g' appears with another 'g' to it's right or left
	 * 		returns false if this is not the case
	 */
	static boolean gisHappy(String str) 
	{
		int g = 0;
		for(int i = 0; i<=str.length()-1;i++)
		{
			if(str.charAt(i) == 'g')g++;
		}
		for(int i = 0; i<=str.length()-1;i++)
		{
			if(str.charAt(i) == 'g' && (str.charAt(i-1) == 'g' || str.charAt(i+1) == 'g')) g--;
		}
		if(g>0) return false;
		return true;
	}
	
	//	Problem #5
	//	We'll say that a "triple" in a string is a char appearing three times in a row. 
	//	Return the number of triples in the given string. The triples may overlap. 

	//	numberOfTriples("abcXXXabc") → 1
	//	numberOfTriples("xxxabyyyycd") → 3
	//	numberOfTriples("a") → 0	
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return
	 * 		Integer containing the # of "triples" in str
	 */
	static int numberOfTriples(String str) 
	{
		char a = str.charAt(0);
		if(str.length()<=2) return 0;
		for(int i =1;i<=2;i++)
		{
			if(str.charAt(i) != a)return numberOfTriples(str.substring(1, str.length()));
		}
		return 1+numberOfTriples(str.substring(1,str.length()));
	}
	
	//	Problem #6
	//	Given a string, return the sum of the digits 0-9 that appear in the 
	//	string, ignoring all other characters. Return 0 if there are no digits 
	//	in the string. (Note: Character.isDigit(char) tests if a char is one 
	//	of the chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.) 

	//	addUpDigits("aa1bc2d3") → 6
	//	addUpDigits("aa11b33") → 8
	//	addUpDigits("Chocolate") → 0
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the # sum of all digits that appear in str
	 */
	static int addUpDigits(String str) 
	{
		if(str.length() == 0) return 0;
		if(str.charAt(0)>='0'&& str.charAt(0)<='9') return Character.digit(str.charAt(0), 10)+ addUpDigits(str.substring(1, str.length()));
		else return addUpDigits(str.substring(1,str.length()));
	}
	
	//	Problem #7
	//	Given a string, return the longest substring that appears at 
	//	both the beginning and end of the string without overlapping. 
	//	For example, beginningAndEndOfString("abXab") is "ab". 

	//	beginningAndEndOfString("abXYab") → "ab"
	//	beginningAndEndOfString("xx") → "x"
	//	beginningAndEndOfString("xxx") → "x"
	
	/**
	 * 
	 * @param string
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		String containing the beginning and ending substrings that are the same
	 */
	static String beginningAndEndOfString(String string) 
	{
	String str, str1 = "";
	for(int i = 1; i<string.length();i++)
	{
		str = string.substring(0, i);
		if(str.equals(string.substring(string.length()-i, string.length()))) str1 = str;
		
	}
	return str1;
	}
	
	//	Problem #8
	//	Given a string, look for a mirror image (backwards) string at both 
	//	the beginning and end of the given string. In other words, zero or more 
	//	characters at the very beginning of the given string, and at the very 
	//	end of the string in reverse order (possibly overlapping). For example, 
	//	the string "abXYZba" has the mirror end "ab". 

	//	beginningMirrorEnd("abXYZba") → "ab"
	//	beginningMirrorEnd("abca") → "a"
	//	beginningMirrorEnd("aba") → "aba"
	
	/**
	 * 
	 * @param string
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		String containing the beginning of the string that is mirrored at the end
	 */
	static String beginningMirrorEnd(String string) 
	{
		String str = "",str1 = "";
		for(int i =0 ;i<=(string.length()-1)/2;i++)
		{
			str1 = "";
			for(int j = string.length()-1;j>=0;j--)
			{
				str1.concat(string.charAt(j)+"");
				if(!str.equals(str1))
					break;
			}
		}
		return str1;
	}
	
	//	Problem #9
	//	Given a string, return the length of the largest "block" in the string. 
	//	A block is a run of adjacent chars that are the same. 

	//	largestBlock("hoopla") → 2
	//	largestBlock("abbCCCddBBBxx") → 3
	//	largestBlock("") → 0
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the # of chars in the largest "block" in str
	 */
	static int largestBlock(String str) 
	{
		int count = 0, big = 1;
		for(int i = 1; i<=str.length()-1;i++)
		{
			count++;
			if(str.charAt(i) == str.charAt(i-1))// || str.charAt(i) == str.charAt(i+1))
			{
				if(count>=big) big = count;
			}
			else count = 0;
		}
		return big+1;
	}
	
	//	Problem #10
	//	Given a string, return the sum of the numbers appearing in the string, 
	//	ignoring all other characters. A number is a series of 1 or more digit 
	//	chars in a row. (Note: Character.isDigit(char) tests if a char is one 
	//	of the chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.)

	//	addUpNumbers("abc123xyz") → 123
	//	addUpNumbers("aa11b33") → 44
	//	addUpNumbers("7 11") → 18
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the sum of all the numbers that appear in str
	 */
	static int addUpNumbers(String str) 
	{
		String num;
		int j = 0, total = 0;
		int[] nums = new int[str.length()-1];
		for(int i = 0;i<=str.length()-1;i++)
		{
			num = "";
			if(str.charAt(i) >= '1' && str.charAt(i) <= '9')
			{
					nums[j] = Character.getNumericValue(str.charAt(i));
					j++;
			}
		}
		for(int i = 0;i<nums.length;i++)
		{
			total+=nums[i];
		}
		return total;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		String s = "day:yak";
		String s1 = "jfds4ohs8sfkjho9akjf5";
		String s2 = beginningMirrorEnd("aba");
		boolean b = gisHappy(s1);
		//String s2 = removeFromBase(s,s1);
		int i = wordEndYZ(s);
		System.out.print("Word end YZ:"+i);//+"\nremove From Base: "+);
	}
	
}