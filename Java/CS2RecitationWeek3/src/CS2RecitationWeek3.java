import java.util.Scanner;

public class CS2RecitationWeek3 
{
    ///////////////////////////////////////////
	//
	// Start of assignment code.
	//
	///////////////////////////////////////////
	
        /*
            Note: All of the following assignments must be completed without the use of any loops including (for, while, and do-while).
            You may not utilize them at any point in your programming to help enhance your use of recursion.
        */
    
    
    
	/**
	 * Returns the last name, first name, and PID of the student.
	 * 
	 * This is required in order to get credit for the programming assignment.
	 */
	static String GetNameAndPID()
	{
		return( "Wheaton,Ernest,e3388709");
	}    
        
        /*  Directions: Recursive function that returns the value of a desired Fibonatchi number
            Note: For all recursive functions you should always be some calls that do
            not result in a recursive call without one your function will end up in an
            infinite loop.
        */
        //FibonatchiNumber(1) -> 1
        //FibonatchiNumber(7) -> 13
        //FibonatchiNumber(17) -> 1597
    
        /*
           @param FibonatchiNumber
                            int of desired fibonatchi number
           @return
                            int value of the fibonatchi number
        */
        
        static int getFibonatchiNumber(int FibonatchiNumber)
        {
        	if(FibonatchiNumber <= 1)//if the number reaches 1 or below
        		return 1;//return 1
        	else
        		return getFibonatchiNumber(FibonatchiNumber-1) + getFibonatchiNumber(FibonatchiNumber-2);
        	//else return the fibonatchi-1 + fibonatchi-2
        }
    
        
        //  Directions: Recursive function that returns the value of a desired factorial
      
        //FactorialNumber(1) -> 1
        //FactorialNumber(7) -> 5040
        //FactorialNumber(12) -> 479001600
    
        /*
           @param FactorialNumber
                            int of desired Factorial number
           @return
                            int value of the Factoiral number
        */
    
        static int getFactorialNumber(int FactorialNumber)
        {
        	if(FactorialNumber<2)//if the number reaches 1 or below
        		return FactorialNumber;//return the number
        	else
        		return FactorialNumber*getFactorialNumber(FactorialNumber-1);//else return the number times the number -1
        }
    
        /*  Directions: Recursive function that adds up the the squares of a range of two integer values
            Example: SumSquaresOfRange(5, 10);
            (5*5)+(6*6)+(7*7)+(8*8)+(9*9)+(10*10)=355
        */
      
        
        /*
           @param int x , int y
                            two integer values of the desired ranage
           @return
                            int value of the sum of all the numbers in the range squared
        */
        
        static int SumSquaresOfRange(int x , int y)
        {
        	if(x>=y)//if the first is as big or bigger than the second, return the second squared
        		return y*y;
        	else// else return the first squared
        		return (x*x)+SumSquaresOfRange(++x,y);
        }
	
        public static void main(String[] args)
		{       
        	long startTime = System.currentTimeMillis();
        	int x,y,z;
        	int a,b,c;
        	x = getFibonatchiNumber(30);
        	a = getFibonatchiNumber(3);
        	y = getFactorialNumber(10);
        	b = getFactorialNumber(3);
        	z = SumSquaresOfRange(1,100);
        	c = SumSquaresOfRange(5,10);
        	System.out.print("Fib test large: "+x+"\nFib test small: "+a+"\nFactorial test large: "+y+"\nFactorial test small: "+b+"\nSum of squares test large: "+z+"\nSum of squares test small: "+c);
        	long endTime = System.currentTimeMillis();
        	long elapsed = endTime - startTime;
        	System.out.print("\nTime of execution: "+elapsed+"Ms");
		}//End of main

}
