#include <fstream>
#include <iostream>
#include <istream>
using namespace std;

struct EastDivision{
	int east1;
	int east2;
	int east3;
	int east4;
};
struct WestDivision{
	int west1;
	int west2;
	int west3;
	int west4;
};
struct SouthDivision{
	int south1;
	int south2;
	int south3;
	int south4;
};
struct NorthDivision{
	int north1;
	int north2;
	int north3;
	int north4;
};
EastDivision EDiv;
WestDivision WDiv;
SouthDivision SDiv;
NorthDivision NDiv;

void getQuarterly(EastDivision EDiv,WestDivision WDiv, SouthDivision SDiv, NorthDivision NDiv)
{
	cout << "Please have all sales as positive." << endl;
	ofstream File;
	File.open("quarterly.dat", ios::out | ios::binary);
	
	cout << "Please input your the sales for the eastern division." << endl;
	while(EDiv.east1 >= 0)
	{
		cout << "1: ";
		cin >> EDiv.east1;
		if(EDiv.east1 >= 0)
		{
			File.write(reinterpret_cast<char *>(&EDiv.east1),sizeof(EDiv.east1));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
	while(EDiv.east2 >= 0)
	{
		cout << "2: ";
		cin >> EDiv.east2;
		if(EDiv.east2 >= 0)
		{
			File.write(reinterpret_cast<char *>(&EDiv.east2),sizeof(EDiv.east2));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
	while(EDiv.east3 >= 0)
	{
		cout << "3: ";
		cin >> EDiv.east3;
		if(EDiv.east3 >= 0)
		{
			File.write(reinterpret_cast<char *>(&EDiv.east3),sizeof(EDiv.east3));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
		while(EDiv.east4 >= 0)
	{
		cout << "4: ";
		cin >> EDiv.east4;
		if(EDiv.east4 >= 0)
		{
			File.write(reinterpret_cast<char *>(&EDiv.east4),sizeof(EDiv.east4));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
	cout << "Please input your the sales for the western division." << endl;
	while(WDiv.west1 >= 0)
	{
		cout << "1: ";
		cin >> WDiv.west1;
		if(WDiv.west1 >= 0)
		{
			File.write(reinterpret_cast<char *>(&WDiv.west1),sizeof(WDiv.west1));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
	while(WDiv.west2 >= 0)
	{
		cout << "2: ";
		cin >> WDiv.west2;
		if(WDiv.west2 >= 0)
		{
			File.write(reinterpret_cast<char *>(&WDiv.west2),sizeof(WDiv.west2));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
	while(WDiv.west3 >= 0)
	{
		cout << "3: ";
		cin >> WDiv.west3;
		if(WDiv.west3 >= 0)
		{
			File.write(reinterpret_cast<char *>(&WDiv.west3),sizeof(WDiv.west3));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
	while(WDiv.west4 >= 0)
	{
		cout << "4: ";
		cin >> WDiv.west4;
		if(WDiv.west4 >= 0)
		{
			File.write(reinterpret_cast<char *>(&WDiv.west4),sizeof(WDiv.west4));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
	cout << "Please input your the sales for the southern division." << endl;
	while(SDiv.south1 >= 0)
	{
		cout << "1: ";
		cin >> SDiv.south1;
		if(SDiv.south1 >= 0)
		{
			File.write(reinterpret_cast<char *>(&SDiv.south1),sizeof(SDiv.south1));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
	while(SDiv.south2 >= 0)
	{
		cout << "2: ";
		cin >> SDiv.south2;
		if(SDiv.south2 >= 0)
		{
			File.write(reinterpret_cast<char *>(&SDiv.south2),sizeof(SDiv.south2));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
	while(SDiv.south3 >= 0)
	{
		cout << "3: ";
		cin >> SDiv.south3;
		if(SDiv.south3 >= 0)
		{
			File.write(reinterpret_cast<char *>(&SDiv.south3),sizeof(SDiv.south3));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
	while(SDiv.south4 >= 0)
	{
		cout << "4: ";
		cin >> SDiv.south4;
		if(SDiv.south4 >= 0)
		{
			File.write(reinterpret_cast<char *>(&SDiv.south4),sizeof(SDiv.south4));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
		cout << "Please input your the sales for the northern division." << endl;
	while(NDiv.north1 >= 0)
	{
		cout << "1: ";
		cin >> NDiv.north1;
		if(NDiv.north1 >= 0)
		{
			File.write(reinterpret_cast<char *>(&NDiv.north1),sizeof(NDiv.north1));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
	while(NDiv.north2 >= 0)
	{
		cout << "2: ";
		cin >> NDiv.north2;
		if(NDiv.north2 >= 0)
		{
			File.write(reinterpret_cast<char *>(&NDiv.north2),sizeof(NDiv.north2));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
	while(NDiv.north3 >= 0)
	{
		cout << "3: ";
		cin >> NDiv.north3;
		if(NDiv.north3 >= 0)
		{
			File.write(reinterpret_cast<char *>(&NDiv.north3),sizeof(NDiv.north3));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
	while(NDiv.north4 >= 0)
	{
		cout << "4: ";
		cin >> NDiv.north4;
		if(NDiv.north4 >= 0)
		{
			File.write(reinterpret_cast<char *>(&NDiv.north4),sizeof(NDiv.north4));
			break;
		}
		else
			cout << "Please input a positive number." << endl;
	}
	File.close();
	return;
}

int main()
{


	getQuarterly(EDiv, WDiv, SDiv, NDiv);
}