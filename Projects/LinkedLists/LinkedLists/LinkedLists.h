#include "stdlib.h"
#include "stdio.h"
typedef struct {
	void* data;
	SLLNode* next;
}SLLNode;
typedef struct{
	void* data;
	DLLNode* next;
	DLLNode* last;
}DLLNode;
SLLNode* MakeSLLNode(void*);
DLLNode* MakeDLLNode(void*);
void insert(SLLNode*,SLLNode*);
void insert(DLLNode*,DLLNode*);
void Remove(SLLNode*,void*);
void RemoveDLL(DLLNode*,void*);
SLLNode* Find(SLLNode*, void*);
DLLNode* Find(DLLNode*, void*);
SLLNode* Sort(SLLNode*,int);
DLLNode* Sort(DLLNode*,int);
