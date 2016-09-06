// ConsoleApplication9.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "stdio.h"
#include <cstdlib>
#include <string.h>



int main()

{ 
char name[40];
char name1[40];
char name2[40];
int i = 0;
int x = 0;
int y = 0;

printf("input your name\n");

gets_s(name);

puts(name);
for(i=0; i<40; i++)
{
	printf("%c",name[i]);
}

for (i=0; i<40; i++)
{
	if(name[i] != ' ')
        name[i] = name1[i];
	if(name[i] == ' ')
		break;
}
for(x=(strlen(name1)); x<strlen(name - (strlen(name1))); x++)
{
		{
			if(name[x] != '\n')
			{
				name[x] = name2[y];
				++y;
			}
			else
				break;
		}
}
printf("Your first name is %s",name1);
printf("\n");
printf("Your last name is ");
puts(name2);
printf("\n");
system("PAUSE");
return 0;

}
