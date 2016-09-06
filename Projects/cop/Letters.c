#include <stdlib.h>
#include <stdio.h>

main(void)
{
	int i;
	for(i=0;i<20;i++)
	{
		int sample;
		sample = rand()%20+0;
		printf("%i\n",sample);
	}
	system("PAUSE");
}