

class HeartRate1
{
	//initialize the variables
	private String name1, name2; 
	private int Age;  
	private int HR =220;
	private int AHR;
	private int MinTargetHR,MaxTargetHR;
	//initialize the methods
	public void setName1(String name)
	{
		name1 = name;
	}
	public String getName1()
	{
		return name1;
	}
	public void setName2(String name)
	{
		name2 = name;
	}
	public String getName2()
	{
		return name2;
	}
	public void setAge(int currentYear, int Year)
	{
		Age = currentYear - Year;
	}
	public void setHR(int Age)
	{
		AHR = HR - Age;
		
	}
	public void setMaxHR(int AHR)
	{
		MaxTargetHR = (int) (AHR *.85);
	}
	public void setMinHR(int AHR)
	{
		MinTargetHR = (int) (AHR *.5);
	}
	public int getAHR()
	{
		return AHR;
	}
	public int getAge()
	{
		return Age;

	}
	public int getMaxHR()
	{
		return MaxTargetHR;
	}
	public int getMinHR()
	{
		return MinTargetHR;
	}
}
