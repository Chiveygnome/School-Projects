import java.util.Scanner;

class HeartRate
{

	public int getInfo(int Day,int Month,int Year,int currentYear)
	{
		System.out.println("Please enter the Day of Birth.\n");
		Scanner input = new Scanner(System.in);
		Day = input.nextInt();
		System.out.println("PLease enter the month of Birth.\n")
		Month = input.nextInt();
		System.out.println("Please enter the year of birth.\n")
		Year = input.nextInt();
		Age = currentYear - Year;
		return Age;                   
	}
	public void displayInfo(int Age, int HR, int MinTargetHR, int MaxTargetHR)
	{
		int AHR = HR - Age;
		MinTargetHR = AHR *.5;
		MaxTargetHR = AHR *.85;
		System.out.println("You are " + Age + " years old.  Your Adjusted Heart Rate is " + AHR + " BPM.\n Your minimum target heart rate is " + MinTargetHR +
				" and your max target heart rate is " + MaxTargetHR + ".");
	}
	public static void main()
	{
		int HR = 220;
		int Age = 0;
		int Day = 0, Month = 0, Year = 0;
		int currentYear = 2014;
		int MinTargetHR = 0, MaxTargetHR = 0;
		getInfo(Day,Month,Year,currentYear);
		displayInfo(Age,HR,MinTargetHR,MaxTargetHR);
	}
}