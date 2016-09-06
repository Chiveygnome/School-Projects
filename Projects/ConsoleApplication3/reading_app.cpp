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
	int All[15];
	int Total;
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
	

	int i,j,temp;

	ifstream File;
	File.open("quarterly.dat", ios::in | ios::binary);

	
	for(int Count0 = 0;Count0 < 16;Count0++)
	{
		File.read(reinterpret_cast<char *>(&Division.All[Count0]),sizeof(Division.All[Count0]));
	}
		Division.EastTotal = Add(Division.All[0], Division.All[1], Division.All[2],Division.All[3]);//0,1,2,3 are the eastern division
		Division.WestTotal = Add(Division.All[4], Division.All[5], Division.All[6], Division.All[7]);//4,5,6,7 are the western division
		Division.SouthTotal = Add(Division.All[8], Division.All[9], Division.All[10], Division.All[11]);//8,9,10,11 are the southern division
		Division.NorthTotal = Add(Division.All[12], Division.All[13], Division.All[14], Division.All[15]);//12,13,14,15 are the northern division

		Division.Q1 = Add(Division.All[0], Division.All[4],Division.All[8],Division.All[12]);
		Division.Q2 = Add(Division.All[1],Division.All[5],Division.All[9],Division.All[13]);
		Division.Q3 = Add(Division.All[2],Division.All[6],Division.All[10],Division.All[14]);
		Division.Q4 = Add(Division.All[3],Division.All[7],Division.All[11],Division.All[15]);

		Division.EastAverage = Average(Division.EastTotal);
		Division.WestAverage = Average(Division.WestTotal);
		Division.SouthAverage = Average(Division.SouthTotal);
		Division.NorthAverage = Average(Division.NorthTotal);
		
		Division.Total = Add(Division.EastTotal,Division.WestTotal,Division.SouthTotal,Division.NorthTotal);


	for (i = 16; i >= 0; i--)
	{
		for (j = 1; j <= i; j++)
		 {
			 if (Division.All[j-1] > Division.All[j])
			 {
				 temp = Division.All[j-1];
				 Division.All[j-1] = Division.All[j];
				 Division.All[j] = temp;
			 }
		 }
	 }
 

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
		cout << "The Highest Quarter value was : "<< Division.All[0] << endl;
		cout << "The Lowest Quarter value was : " << Division.All[15] << endl;
		cout << "The Total sales was: " << Division.Total << endl;
		system("PAUSE");
		File.close();
}
//int main()
//{
	//Display(Division);
//}