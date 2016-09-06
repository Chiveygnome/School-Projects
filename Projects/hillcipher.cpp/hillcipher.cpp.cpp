// hillcipher.cpp.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "stdio.h"
#include <iostream>
#include <fstream>
using namespace std;

int main(int argc, char *argv[])
{
	int i,j,m ,y,g=0,b=0,q=1,p=0,h;//initialize all counters
	int CNumText[10000];//initialize all the arrays that will handle the conversions
	int key[9][9]; 
	char tempch[10000] = {' '};
	char ch[10000];
	char CText[10000];
	int temp[10];
	char ckey[20];
	char *fkey =  argv[1];//set the key name as the first argument
	char *fname= argv[2];//set the file name as the second argument
	ifstream fileK;
	fileK.open(fkey,ios::in);//open key file
	if(fileK==0)//error
	{
		printf("Could not open Key");
		return 0;
	}
	else//file was opened
	{
		printf("Sucessfully opened Key.\n");
		while(!fileK.eof())
		{
			fileK >> ckey[b];
			b++;
		}
		cout << "\n";
		m = ckey[0]-'0';
		for(i=0;i<m;i++)
		{
			for(j=0;j<m;j++)
			{
				key[i][j] = ckey[q]-'0';
				cout<<key[i][j]<<' ';
				q++;
			}
		}
		cout << "\n";
	}
	ifstream file;
	file.open(fname,ios::in);//open text file
	if(file==0)//error
	{
		printf("Could not open file.");
		return 0;
	}
	else//file was opened
	{
		printf("Sucessfully opened the file.\n");
		while(!file.eof())
		{
			file >> tempch[g];
			//The block below tests what kind of symbol the file has just imported and converts to a lower alphabettical character or discards a non- alpha character.
			if(tempch[g] == '\0')
			{
				break;
			}
			else if(tempch[g] == ' ')
			{
				tempch[g] ='x';
			}
			else if(isalpha(tempch[g]))
			{
				if(isupper(tempch[g]))
				{
					ch[g]=tolower(tempch[g]);
					cout<<ch[g];
					g++;
				}
				else
				{
					ch[g] = tempch[g];
					cout<<ch[g];
					g++;// g is the character count, will be used later to figure out how many x's to pad with
				}
			}
		}
		h = g%m;
		cout << "\n";
		cout << "\n";
	}
	for(y=0;y<g;y)
	{
		for(int k = 0;k<m;k++)
		{
			temp[k]=0;
		}
		for(i=0;i<m;i++)
		{
			for(j=0;j<m;j++)
			{
				//the line below converts a character into ASCII and substracts 97 so the program is dealing with 0-25, just like a hill cipher on paper
				temp[j] += (key[j][i]*((static_cast<int>(ch[y]))-97));
			}
			y++;
		}
		for(j=0;j<m;j++)
		{
			//in the block below we do a modulus of 26 then add by 97 so that we have the proper values for ASCII conversion
			temp[j] = (temp[j]%26);
			temp[j] +=97;
		}
		y-=m;//this is to make sure we can place the characters in their correct place in the array
		for(i=0;i<m;i++)
		{
			CNumText[y] = temp[i];
			if(y>=g)
			{
				CNumText[y] = 120;
			}
			y++;
		}
	}
	cout<<"printing cipher text....\n";
	cout<<"\n";
	for(y=0;y<=g+h;y++)
	{
		//the block below casts the integers stored in CNumText as their character counterparts
		CText[y]=static_cast<char>(CNumText[y]);
		if((y%80) == 0)
			cout<<"\n";
		cout <<CText[y];
	}
	cout<<"\n";
}
