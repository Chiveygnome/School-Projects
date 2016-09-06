#include "Object.h"
#include <stdio.h>

void main(int argc, char** argv)
{
	char* SFile;
	printf("Please enter the name of the SaveFile(if one has been created). If not enter 1.\n");
	scanf("%c",SFile);
	if(*SFile!='1')
	{
		if(!checkSave(SFile))
		{
			printf("Error: Save not found. File does not exist or name inputted was incorrect.\n");
			Object* character = allocObject();
		}
		else
		{
			Object* character = allocObject();
			character = openSave(SFile);
		}
	}
	Object* character = allocObject();
	changePos(character,'U');
	changePos(character,'L');
	changePos(character,'F');
	changePos(character,'D');
	changePos(character,'R');
	return;
}