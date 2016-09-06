#include "stdio.h"
#include "ctype.h"
#include <string.h>


int main(void)
{ 
char name[40];
int i;
printf("input your name\n");
gets(name);
printf("Your first name is : ");
for(i=0;i<strlen(name);i++)
{
	if(isalpha(name[i]))
		printf("%c",name[i]);
	else
	{
		printf("\n");
		printf("Your Last name is : ");
	}
}
printf("\n");
system("PAUSE");
return 0;

}

