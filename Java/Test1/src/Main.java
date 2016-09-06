import java.util.Scanner;


public class Main {
	public static void main(String[] args)
	{
		//initialize the variables
		String firstName,lastName;
		int Day = 0,Month = 0,Year = 0;
		int currentYear = 2014;
		//input the data
		System.out.println("Please enter the Day of Birth.\n");
		Scanner input = new Scanner(System.in);
		Day = input.nextInt();
		System.out.println("PLease enter the month of Birth.\n");
		Month = input.nextInt();
		System.out.println("Please enter the year of birth.\n");
		Year = input.nextInt();
		System.out.println("Please enter your first name.\n");
		firstName = input.nextLine();
		System.out.println("Please enter your last name.\n");
		lastName = input.nextLine();
		//create new HeartRate1 reference variable
		HeartRate1 myHeartRate = new HeartRate1();
		//crunch and store the data
		myHeartRate.setAge(currentYear, Year);
		int myAge = myHeartRate.getAge();
		myHeartRate.setHR(myAge);
		int myHR = myHeartRate.getAHR();
		myHeartRate.setMaxHR(myHR);
		int myMaxHR = myHeartRate.getMaxHR();
		myHeartRate.setMinHR(myHR);
		int myMinHR = myHeartRate.getMinHR();
		myHeartRate.setName1(firstName);
		myHeartRate.setName2(lastName);
		String myName1 = myHeartRate.getName1();
		String myName2 = myHeartRate.getName2();
		//print out the data in a readable form
		System.out.println("Hello, "+ myName1+ " " + myName2+".  You are " + myAge + " years old.  Your Adjusted Heart Rate is " + myHR + " BPM.\n Your minimum target heart rate is " + myMinHR +
				" and your max target heart rate is " + myMaxHR + ".");
	}
}
