#include<stdio.h>
int a,b,c = 0;
int main()
{
	for(a=0; a<7;a++)
	{
		for(b=0;b < c;b++)
		{
			printf("*");
		}
		printf("\n");
		++c;
	}
	system("PAUSE");
	return 0;
}