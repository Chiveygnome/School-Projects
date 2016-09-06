#include"LinkedLists.h"
SLLNode* MakeSLLNode(void* data)
{
	SLLNode* s = (SLLNode*)malloc(sizeof(SLLNode));
	s->data = data;
	return s;
}
DLLNode* MakeDLLNode(void* data)
{
	DLLNode* dn = (DLLNode*)malloc(sizeof(DLLNode));
	dn->data = data;
	return dn;
}
void insert(SLLNode* node,SLLNode* root)
{
	if(root->next == NULL)
	{
		root->next=node;
	}
	else
		return insert(node,root->next);
}
void insert(DLLNode* node, DLLNode* root)
{
	if(root->next == NULL)
	{
		root->next = node;
		node->last = root;
	}
	else
		return insert(node, root->next);
}
void Remove(SLLNode* root, void* data)
{
	if(root->next->data == data)
	{
		SLLNode* n = root->next;
		root->next = root->next->next;
		free(n);
	}
	else
		return Remove(root->next, data);
}
void RemoveDLL(DLLNode* root,void* data)
{
	if(root->next->data == data)
	{
		DLLNode* d = root->next;
		root->next = root->next->next;
		free(d);
	}
	else
		return RemoveDLL(root->next,data);
}
SLLNode* Find(SLLNode* root,void* data)
{
	if(root->next->data == NULL || root->next->data != data)
	{
		return root->next;
	}
	else
		return 
}