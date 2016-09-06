#ifndef WORLD_H_
#define WORLD_H_
#include"Object.h"
typedef struct _world{
	int WORLD_WIDTH_MAX;
	int WORLD_WIDTH_MIN;
	int WORLD_LENGTH_MAX;
	int WORLD_LENGTH_MIN;
	int WORLD_HIEGHT_MAX;
	int WORLD_HIEGHT_MIN;
	int objCount;
}World;
World* allocWorld();
void releaseWorld(World*);
Object* addObject(World*);
void keepInWorld(World*,Object*);
void setWorldWidthMax(World*,int);
void setWorldWidthMin(World*,int);
void setWorldLengthMax(World*,int);
void setWorldLengthMin(World*,int);
void setWorldHieghtMax(World*,int);
void setWorldHieghtMin(World*,int);

#endif