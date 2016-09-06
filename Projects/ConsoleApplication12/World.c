#include "World.h"
#include <stdlib.h>

World* allocWorld()
{
	World* world = (World*)malloc(sizeof(World));
	world->WORLD_WIDTH_MAX = 10000;
	world->WORLD_WIDTH_MIN = -10000;
	world->WORLD_LENGTH_MAX = 10000;
	world->WORLD_LENGTH_MIN = -10000;
	world->WORLD_HIEGHT_MAX = 10000;
	world->WORLD_HIEGHT_MIN = -10000;
	world->objCount = 0;
	return world;
}
void releaseWorld(World* world)
{
	if(world)
	{
		free(world);
	}
}
Object* addObject(World* world)
{
	Object* obj = allocObject();
	world->objCount++;
	return obj;
}
void keepInWorld(World* world,Object* obj)
{
	if(obj->position->x > world->WORLD_WIDTH_MAX)
	{
		obj->position->x--;
	}
	if(obj->position->x < world->WORLD_WIDTH_MIN)
	{
		obj->position->x++;
	}
	if(obj->position->y > world->WORLD_HIEGHT_MAX)
	{
		obj->position->y--;
	}
	if(obj->position->y < world->WORLD_HIEGHT_MIN)
	{
		obj->position->y++;
	}
	if(obj->position->z > world->WORLD_LENGTH_MAX)
	{
		obj->position->z--;
	}
	if(obj->position->z < world->WORLD_LENGTH_MIN)
	{
		obj->position->z++;
	}
}
void setWorldWidthMax(World* w,int i)
{
	w->WORLD_WIDTH_MAX = i;
	return;
}
void setWorldWidthMin(World* w,int i)
{
	w->WORLD_WIDTH_MIN = i;
	return;
}
void setWorldLengthMax(World* w,int i)
{
	w->WORLD_LENGTH_MAX = i;
	return;
}
void setWorldLengthMin(World* w,int i)
{
	w->WORLD_LENGTH_MIN = i;
	return;
}
void setWorldHieghtMax(World* w,int i)
{
	w->WORLD_HIEGHT_MAX = i;
	return;
}
void setWorldHieghtMin(World* w,int i)
{
	w->WORLD_HIEGHT_MIN = i;
	return;
}