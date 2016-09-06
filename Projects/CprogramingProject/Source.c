#include <stdio.h>
#include <stdlib.h>
int computerGuess();
int userGuess();
int test(char);
int guess();

int main(void)
{
	int c = 0;
	printf("The purpose of the program is for you to chose whether you want to guess the \n computer's numbers or have the computer guess yours. Please enter 0 for the \n computer to guess your number or 1 to guess the computer's.");
	scanf("%i",&c);
	switch(c)
	{
	case 0:
		computerGuess();
	case 1:
		userGuess();
	}
}
int computerGuess()
{
	char ch[4],m[4] = {0};
	int i,j,k,l,n;
	
for(l=0;l<4;l++)
{
	printf("Enter a number between 1 and 4\n");
	ch[l] = getchar();
	i = test(ch[l]);
	while(!i)
	{
		ch[l] = getchar();
		i = test(ch[l]);
	}	
}
for(j=0;j<20;j++)
{
	for(k=0;k<4;k++)
	{
		m[k] = guess();
		printf("%c",m[k]);
	}
	if(ch[0] == m[0] && ch[1] == m[1] && ch[2] == m[2] && ch[3] == m[3])
	{
		printf(" - the computer has guessed your number! \n");
		break;
	}
	printf("\n");

}
	system("PAUSE");
	return 0;
}
int userGuess()
{
	char ch1[4],m1[4] = {0};
	int i1,j1,k1,l1,n1;
	for(k1=0;k1<4;k1++)
	{
		m1[k1] = guess();
	}

for(j1=0;j1<20;j1++)
{
	for(l1=0;l1<4;l1++)
	{
		printf("Enter a number between 1 and 4\n");
		ch1[l1] = getchar();
		i1 = test(ch1[l1]);
		while(!i1)
		{
			ch1[l1] = getchar();
			i1 = test(ch1[l1]);
		}	
	}
	if(ch1[0] == m1[0] && ch1[1] == m1[1] && ch1[2] == m1[2] && ch1[3] == m1[3])
	{
		printf(" - you have guessed the computer's number! \n");
		break;
	}
	else
	{
		printf("\n not the number, guess again.");
	}
	printf("\n");

}
	system("PAUSE");
	return 0;
}
int guess()
{
	int z = ((rand()%4+1) + (int)'0');
	char sample = (char)z;
	return sample;
}



int test(char ch)
{
	if(ch <= '4' && ch >= '1')
		return 1;
	else 
		return 0;
}