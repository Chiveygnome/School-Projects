#include "Object.h"
#include <stdlib.h>
#include <stdio.h>
Pos* allocPos()
{
	Pos* position = (Pos*)calloc(1,sizeof(Pos));
	return position;
}
void releasePos(Pos* pos)
{
	if(pos)
	{
		free(pos);
	}
}
Object* allocObject()
{
	Object* object = (Object*)calloc(1,sizeof(Object));
	object->position = allocPos();
	return object;
}
void releaseObject(Object* obj)
{
	if(obj)
	{
		if(obj->position)
		{
			releasePos(obj->position);
		}
		if(obj->rotation)
		{
			releaseRot(obj->rotation);
		}
		free(obj);
	}
}
Rot* allocRot()
{
	Rot* rot = (Rot*)calloc(1,sizeof(Rot));
	rot->X_MAX = 359;
	rot->Y_MAX = 359;
	rot->Z_MAX = 359;
	return rot;
}
void releaseRot(Rot* r)
{
	if(r)
	{
		free(r);
	}
}
void changePos(Object* obj,const  char d)
{
	switch(d)
	{
		case 'F' :
			obj->position->z+=1;
			break;
		case 'B' :
			obj->position->z-=1;
			break;
		case 'U' :
			obj->position->y+=1;
			break;
		case 'D' :
			obj->position->y-=1;
			break;
		case 'L' :
			obj->position->x+=1;
			break;
		case 'R' :
			obj->position->x-=1;
			break;
		default:
			break;
	}
}
void SavePos(Object* obj)
{
	FILE* file;
	char* fileName;
	printf("Please enter the name you want to save your file as (including extensions).");
	scanf("%c",fileName);
	file = fopen(fileName,"w");
	fprintf(file,"%d ",obj->position->x);
	fprintf(file,"%d ",obj->position->y);
	fprintf(file,"%d",obj->position->z);
	fclose(file);
}
Object* openSave(const char* fileName)
{
	Object* obj = allocObject();
	FILE* file;
	file = fopen(fileName,"r");
	fscanf(file,"%d",&obj->position->x);
	fscanf(file,"%d",&obj->position->y);
	fscanf(file,"%d",&obj->position->z);
	fclose(file);
	return obj;
}
bool checkSave(const char* fileName)
{
	FILE* file;
	if(file = fopen(fileName, "r"))
	{
		fclose(file);
		return true;
	}
	return false;
}