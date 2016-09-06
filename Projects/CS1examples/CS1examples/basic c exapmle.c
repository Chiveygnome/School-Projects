#include <stdio.h>
#include <string.h>
#include<stdbool.h>

typedef unsigned char Byte;
void toUpper(char*,int);
int main()//int argc,char** argv)
{
	short s=0;//2 bytes
	int i=0;//4 bytes
	long l=0;//8 bytes
	char c;//1 byte[-128,127]
	Byte ipFragment=65;//1 byte[0,255]
	double d=0;//8 bytes
	float f=0;//4 bytes
	bool b=false;//1 bit
	char* test = "hello world";
	toUpper(test);
	printf("%s\n",test);
	return 0;
}
//char* str is passed by reference
//int len is passed by value
void toUpper(char* str, int len)
{
	int i = 0;
	for(i;i<len;++i)		//i++ creates an extr copy of the variable i to increment, ++i does not.
	{
		if(str[i]>='a' && str[i]<='z')// remember, we are comparing the ASCII codes for these numers.  The capatilizations will not be included in this conditional.
		{
			str[i] = str[i]-32;
			//also, can be written as str[i] -= 32.  
		}
	}
}