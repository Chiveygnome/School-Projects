#include <stdio.h>
#define Max 6
void main()
{
	int Array[7][7]={0};
	/*{{1,2,3,4,5,6,},  //These values are for program testing only.
	{7,8,9,10,11,12,},
	{13,14,15,16,17,18,},
	{19,20,21,22,23,24,},
	{25,26,27,28,29,30,},
	{31,32,33,34,35,36,}};*/
	int i,j,k=0,l=0;
	for(i =0;i!=6;++i)
	{
		for(j=0;j!=6;++j)
		{
			printf("Please input an integer number \n");
			scanf_s("%d",&Array[i][j]);
		}
	}

	for(i=0;i<=Max;i++)
	{
		for(j=0;j<Max;j++)
		{
			Array[i][6] += Array[i][j];
		}
	}
	for(j=0;j<=Max;j++)
	{
		for(i=0;i<Max;i++)
		{
			Array[6][j] += Array[i][j];
		}
	}
	for(i=0;i<=Max;i++)
	{
		for(j=0;j<=Max;j++)
		{
			printf("%5d",Array[i][j]);
		}
		printf("\n");
	}
	system("PAUSE");
	return;
}
