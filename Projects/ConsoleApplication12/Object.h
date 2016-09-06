#ifndef OBJECT_H_
#define OBJECT_H_
#include <stdio.h>
typedef struct _pos{
	int x;
	int y;
	int z;
}Pos;
typedef struct _rot{
	int x;
	int y;
	int z;
	int X_MAX;
	int Y_MAX;
	int Z_MAX;
	int X_MIN;
	int Y_MIN;
	int Z_MIN;
}Rot;
typedef struct _Object{
	Pos* position;
	Rot* rotation;
}Object;
typedef struct _Skin{
	unsigned int x;
	unsigned int y;
	FILE* skin;
}Skin;
Pos* allocPos();
void releasePos(Pos*);
Object* allocObject();
void releaseObject(Object*);
Rot* allocRot();
void releaseRot(Rot*);
void changePos(Object*,const char);
void SavePos(Object*);
Object* openSave(const char[]);
bool checkSave(const char[]);
#endif