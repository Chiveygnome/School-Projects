#include<iostream>
#include<fstream>
using namespace std;

struct Divisions{
	int east[4];
	int west[4];
	int south[4];
	int north[4];
	int EastTotal;
	int WestTotal;
	int SouthTotal;
	int NorthTotal;
	int Q1;
	int Q2;
	int Q3;
	int Q4;
	int EastAverage;
	int WestAverage;
	int SouthAverage;
	int NorthAverage;
	int All[20];
};

Divisions Division;
int Add(int x, int y, int z, int a)
{
	return x + y + z + a;
}
int Average(int x)
{
	return x/4;
}
void Display(Divisions Division)
{
Division.All[0] = Division.east[1];
Division.All[1] = Division.east[2];
Division.All[2] = Division.east[3];
Division.All[3] = Division.east[4];
Division.All[4] = Division.east[1];
Division.All[5] = Division.east[2];
Division.All[6] = Division.east[3];
Division.All[7] = Division.east[4];
Division.All[8] = Division.east[1];
Division.All[9] = Division.east[2];
Division.All[10] = Division.east[3];
Division.All[11] = Division.east[4];
Division.All[12] = Division.east[1];
Division.All[13] = Division.east[2];
Division.All[14] = Division.east[3];
Division.All[15] = Division.east[4];
	int Count0 = 1;
	int Count1 = 1;
	int Count2 = 1;
	int Count3 = 1;
	int i,j,temp;

	ifstream File;
	File.open("quarterly.dat", ios::in | ios::binary);

	
	for(Count0=1;Count0 < 5;Count0++)
	{
		File.read(reinterpret_cast<char *>(&Division.east[Count0]),sizeof(Division.east[Count0]));
		cout << "The sales for quarter: "<< Count0 << endl;
		cout << Division.east[Count0] << endl;
		system("PAUSE");
	}
	
		for(Count1=1;Count1 < 5;Count1++)
	{
		File >> Division.west[Count1];
		cout << "The sales for quarter: "<< Count1 << endl;
		cout << Division.west[Count1] << endl;
		system("PAUSE");
	}
		for(Count2=1;Count2 < 5;Count2++)
	{
		File >> Division.south[Count2];
		cout << "The sales for quarter: "<< Count2 << endl;
		cout << Division.south[Count2] << endl;
		system("PAUSE");
	}
		for(Count3=1;Count3 < 5;Count3++)
	{
		File >> Division.north[Count3];
		cout << "The sales for quarter: "<< Count3 << endl;
		cout << Division.north[Count3] << endl;
		system("PAUSE");
	}
		Division.EastTotal = Add(Division.east[1], Division.east[2], Division.east[3],Division.east[4]);
		Division.WestTotal = Add(Division.west[1], Division.west[2], Division.west[3], Division.west[4]);
		Division.SouthTotal = Add(Division.south[1], Division.south[2], Division.south[3], Division.south[4]);
		Division.NorthTotal = Add(Division.north[1], Division.north[2], Division.north[3], Division.north[4]);

		Division.Q1 = Add(Division.east[1], Division.west[1],Division.south[1],Division.north[1]);
		Division.Q2 = Add(Division.east[2],Division.west[2],Division.south[2],Division.north[2]);
		Division.Q3 = Add(Division.east[3],Division.west[3],Division.south[3],Division.north[3]);
		Division.Q4 = Add(Division.east[4],Division.west[4],Division.south[4],Division.north[4]);

		Division.EastAverage = Average(Division.EastTotal);
		Division.WestAverage = Average(Division.WestTotal);
		Division.SouthAverage = Average(Division.SouthTotal);
		Division.NorthAverage = Average(Division.NorthTotal);


		cout << "The Totals for each Division and Quarter is as follows:" << endl;
		cout << "Eastern: " << Division.EastTotal << endl;
		cout << "Western: " << Division.WestTotal << endl;
		cout << "Southern: " << Division.SouthTotal << endl;
		cout << "Northern: " << Division.NorthTotal << endl;
		cout << "Q1: " << Division.Q1 << endl;
		cout << "Q2: " << Division.Q2 << endl;
		cout << "Q3: " << Division.Q3 << endl;
		cout << "Q4: " << Division.Q4 << endl;
		cout << "The Averages for each of the quarters is as follows." << endl;
		cout << "Eastern: " << Division.EastAverage << endl;
		cout << "Western: " << Division.WestAverage << endl;
		cout << "Southern: " << Division.SouthAverage << endl;
		cout << "Northern: " << Division.NorthAverage << endl;
}
int main()
{
	Display(Division);
}