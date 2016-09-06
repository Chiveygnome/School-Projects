#include <string>
#include<iostream>
#include<fstream>
#include<cmath>
using namespace std;
void crcCalc(string,string);
string hexToBin(string);
string binXOR(char,char);
string binToHex(string);
bool hexValid(string);
void crcValid(string,string);
int main(int argv,char *argc[])
{
	const string binDivisor = "10000101010001101";
	bool isValid = true;
	int b = 0;
	ifstream fileH;
	char *fileHex = argc[1];
	char Hex[41] = {'\n'};
	fileH.open(fileHex,ios::in);
	if(fileHex == 0)
	{
		cout<<"Could not open file.\n";
		return 0;
	}
	else
	{
		cout<<"Sucessfully opened file.\n";
		while(!fileH.eof())
		{
			fileH >> Hex[b];
			cout<<Hex[b];
			b++;
		}
		Hex[b+1]='\n';
	}
	
	//cout <<"Please enter a hex to be validated :\n";
	//cin>>Hex;
	isValid = hexValid(Hex);
	if(!isValid)
	{
		cout<<"Sorry that is not a valid hex file.";
		return 0;
	}
	char answer;
	cout<<"Please enter c for crc Calcutlation or v for crc Validation.\n note: This is case sensitive, please only use lower case letters.\n";
	cin >> answer;
	if(answer == 'c')
		crcCalc(Hex,binDivisor);
	else if(answer == 'v')
		crcValid(Hex,binDivisor);
	else 
	{
		cout << "That is not valid input.\n";
		return 0;
	}
}
void crcCalc(string hex, string binDivisor)//function to calculate the crc
{
	int g = 0,a = 0,j=0,u=0;
	string Hexdividend = hex;
	string binDividend = hexToBin(Hexdividend);
	string hexCrc = "";
	string temp;
	string temp2 = "0000000000000000";
	char binCrc[40][4] ={""};
	if(binDividend.length() > 16)
		binDividend.append("0000000000000000");
	else
	{
		temp2.append(binDividend);
		binDividend = temp2;
	}
	int y = binDividend.length();
	int p = (y-16);
	cout << "The hex inputted is: " <<Hexdividend<<"\nThe binary form is: ";
	for(int ad =0;ad<y;ad++)
	{
		if(ad%4==0)
			cout<<" ";
		if(ad%80==0)
			cout<<"\n";
		cout<<binDividend[ad];
	}
	cout<<"\n"<<"The binary Divisor is " << binDivisor;
	cout << "\nPadded zeroes : 16";
	cout<<"\nlength is : "<< binDividend.length();
	while(a<(y-16))
	{
		for(a=j;a<y;a)
		{
			if(binDividend[a] == '0')
				a++;
			else if(binDividend[a] == '1')
			{
				break;
			}
		}
		temp = "";
		for(int i = 0;i<=16;i++)
		{
			if((i+a)>y)
				break;
			else
			{
				temp.append(binXOR(binDivisor[i],binDividend[i+a]));
			}
		}
		for(int z = 0;z<temp.length();z++)
		{
			binDividend[a+z] = temp[z];
		}
		for(int x = 0;x<y;x++)
		{
			if(x%4==0)
				cout<<" ";
			if(x%80==0)
				cout<<"\n";
			cout<<binDividend[x];
		}
		j++;
		for(a;a<y;a)
		{
			if(binDividend[a] == '0')
				a++;
			else if(binDividend[a] == '1')
			{
				break;
			}
		}
		//cout<<"\n"<< j<< " "<<a;//this was a debugging printout
		if(a+16>y)
			break;
	}
	cout<<"\n";
	for(int o =0;o<4;o++)
	{
		for(u = 0;u<4;u++)
		{
			binCrc[o][u]=binDividend[p];
			p++;
		}
		hexCrc.append(binToHex(binCrc[o]));
	}
	cout << "The crc of the polynomial " << binDivisor << "\n and the hex " << Hexdividend << " is(binary): ";
	for(int aa=(y-16);aa<binDividend.length();aa++)
	{
		if(aa%4==0)
			cout<<" ";
		if(aa%80==0)
			cout<<"\n";
		cout<<binDividend[aa];
	}
	cout<<"\n(hex): "<<hexCrc<<"\n";
	//system("PAUSE");
}
string hexToBin(string hex)//function to translate hexidecimal to binary
{
	int len = (int)hex.length();
	string cReturn ="";
	for(int i = 0; i<len;i++)
	{
		switch(hex[i])
		{
		case '0': cReturn.append("0000");
			break;
		case '1': cReturn.append("0001");
			break;
		case '2': cReturn.append("0010");
			break;
		case '3': cReturn.append("0011");
			break;
		case '4': cReturn.append("0100");
			break;
		case '5': cReturn.append("0101");
			break;
		case '6': cReturn.append("0110");
			break;
		case '7': cReturn.append("0111");
			break;
		case '8': cReturn.append("1000");
			break;
		case '9': cReturn.append("1001");
			break;
		case 'A': cReturn.append("1010");
			break;
		case 'B': cReturn.append("1011");
			break;
		case 'C': cReturn.append("1100");
			break;
		case 'D': cReturn.append("1101");
			break;
		case 'E': cReturn.append("1110");
			break;
		case 'F': cReturn.append("1111");
			break;
		case 'a': cReturn.append("1010");
			break;
		case 'b': cReturn.append("1011");
			break;
		case 'c': cReturn.append("1100");
			break;
		case 'd': cReturn.append("1101");
			break;
		case 'e': cReturn.append("1110");
			break;
		case 'f': cReturn.append("1111");
			break;
		}
	}
	return cReturn;
}
string binXOR(char divisor,char dividend)// function to XOR
{
	string sReturn;
	if(divisor == dividend)
		sReturn = "0";
	else if(divisor != dividend)
		sReturn = "1";
	return sReturn;
}
string binToHex(string bin)//function to translate binary to hexidecimal
{
	string Return = "";
	if(bin == "0000")
		Return.append("0");
	else if(bin == "0001")
		Return.append("1");
	else if(bin == "0010")
		Return.append("2");
	else if(bin == "0011")
		Return.append("3");
	else if(bin == "0100")
		Return.append("4");
	else if(bin == "0101")
		Return.append("5");
	else if(bin == "0110")
		Return.append("6");
	else if(bin == "0111")
		Return.append("7");
	else if(bin == "1000")
		Return.append("8");
	else if(bin == "1001")
		Return.append("9");
	else if(bin == "1010")
		Return.append("A");
	else if(bin == "1011")
		Return.append("B");
	else if(bin == "1100")
		Return.append("C");
	else if(bin == "1101")
		Return.append("D");
	else if(bin == "1110")
		Return.append("E");
	else if(bin == "1111")
		Return.append("F");
	return Return;

}
bool hexValid(string hex)//function to validate the input file
{
	int p =0;
	for(int i =0; i<(hex.length());i++)
	{
		if((hex[i]>='0' && hex[i]<='9') || (hex[i] >='a' && hex[i]<'g') || (hex[i]>='A' && hex[i]<'G'))
		{
			//cout<<p<<'\n';
			//p++;
			//cout<<hex[i];
		}
		else if(hex[i] == '\n')
			return true;
		if((hex[i] <'0' && hex[i] >'9')||(hex[i] <'a' && hex[i] >'g')||(hex[i] <'A' && hex[i] >'G'))
		{
			return false;
		}
	}
	return true;
}
void crcValid(string hex,string binDivisor)//function to validate the crc, very buggy
{
	int a=0,j=0,g=0;
	string crcToValidate;
	string crcBin;
	string temp,temp2;
	string endLoop;
	string hexBin;
	hexBin.append(hexToBin(hex));
	hexBin.append("0000000000000000");
	cout<<"The input "<<hex<<" is : ";
	for(int ad =0;ad<hexBin.length();ad++)
	{
		if(ad%4==0)
			cout<<" ";
		if(ad%80==0)
			cout<<"\n";
		cout<<hexBin[ad];
	}
	cout<<"\nPlease enter, in hexidecimal, the crc to validated:\n";
	cin >> crcToValidate;
	crcBin = hexToBin(crcToValidate);
	int y =crcBin.length()-binDivisor.length();
	while(j<(hexBin.length()-16))
	{
		for(a =j;a<hexBin.length();)
		{
			if(hexBin[a] == '0')
				a++;
			else if(hexBin[a] == '1')
				break;
		}
		temp = "";
		for(int i = 0;i<binDivisor.length();i++)
		{
			if((i+a)>hexBin.length())
				continue;
			else
			{
				temp.append(binXOR(binDivisor[i],hexBin[i+a]));
				g++;
			}
		}
		for(int z = 0;z<temp.length();z++)
		{
			hexBin[a+z] = temp[z];
		}
		for(int x = 0;x<hexBin.length();x++)
		{
			if(x%4==0)
				cout<<" ";
			if(x%80==0)
				cout<<"\n";
			cout<<hexBin[x];
		
		}
		j++;
		if(a+16>=hexBin.length())
			break;
	}
	for(int ac = 0; ac<16;ac++)
	{
		temp2.append(binXOR(crcBin[ac],hexBin[ac]));
	}
	if(temp2=="0000000000000000")
		cout<<"The Hexidecimal "<< crcToValidate <<" has been validated as the crc of the hex "<<hex;
	else
		cout<<"\nThe Hexidecimal " << crcToValidate <<" has not been validated as the crc of the hex.\n"<<hex;
	//system("PAUSE");
}