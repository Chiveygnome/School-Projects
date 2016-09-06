#ifndef OBJECT_H_
#define OBJECT_H_

typedef struct _pos{
	int x;
	int y;
	int z;
}Pos
typedef struct _Object{
	Pos position; 
}Object

Pos allocPos();


#endif