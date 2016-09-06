#include "BSTree.h"
Node* MakeNode(int data)
{
	Node* newNode = (Node*)malloc(sizeof(Node*));
	newNode->data = data;
	newNode->Left = NULL;
	newNode->Right = NULL;
	return newNode;
}
int compare(Node* a, Node* b)
{
	if(a->data>b->data)
		return 1;
	else if(a->data<b->data)
		return 0;
	else
		return -1;
}
int compare(Node* a, int val)
{
	if(a->data>val)
		return 1;
	if(a->data<val)
		return 0;
	else
		return -1;
}
void Insert(Node* root,Node* a)
{
	if(compare(root,a) == 1)
	{
		if(root->Left == NULL)
		{
			root->Left = a;
		}
		else
		{
			Insert(root->Left,a);
		}
	}
	else if(compare(root,a) == 0)
	{
		if(root->Right == NULL)
		{
			root->Right = a;
		}
		else
		{
			Insert(root->Right,a);
		}
	}
	else
	{
		printf("Error -1: The value %d was already found in the tree.",a->data);
		return;
	}
}
int Find(Node* root, int val)
{
	if(val == root->data)
	{
		return root->data;
	}
	else if(val >root->data)
		Find(root->Right,val);
	else
		Find(root->Left,val);
}
int count(Node* root)
{
	if(root->Left == NULL && root->Right == NULL)
		return 1;
	else 
		return 1 + count(root->Left) + count(root->Right);
}
int countData(Node* root)
{
	if(root->Left == NULL && root->Right)
		return root->data;
	else
		return countData(root->Left) + countData(root->Right);
}
void deleteNode(int val,Node* node,Node* Root)
{
	Node* temp = (Node*)malloc(sizeof(Node*));
	/*
	First block here frees up leaves.  It checks the left->left and left->right to make sure that the node is a leaf
	if it's not, we will have to connect some branches.
	*/
	if(compare(node,val)==0)
	{
		if(node->Left!=NULL && node->Left->data == val)
		{
			if(node->Left->Left==NULL && node->Left->Right==NULL)//no leaf case
			{
				free(node->Left);
				node->Left = NULL;
			}
			else if(node->Left->Left!=NULL && node->Left->Right == NULL)//single leaf case
			{
				temp = node->Left;
				node->Left = NULL;
				Insert(Root,temp->Left);
				free(temp);
			}
			else if(node->Left->Left == NULL && node->Left->Right != NULL)//single leaf case
			{
				temp = node->Left;
				node->Left = NULL;
				Insert(Root,temp->Right);
				free(temp);
			}
			else if(node->Left->Left != NULL && node->Left->Right != NULL)
			{
				temp = node->Left;
				node->Left = NULL;
				Insert(Root,temp->Left);
				Insert(Root,temp->Right);
				free(temp);
			}
		}
		else
			deleteNode(val,node->Left,Root);
	}
	if(node->Right!=NULL && node->Right->data == val)
	{
		if(node->Right->Left==NULL && node->Right->Right==NULL)
		{
			node->Right = NULL;
			free(node->Right);		
		}
		else if(node->Right->Left != NULL && node->Right->Right == NULL)
		{
			temp = node->Right;
			node->Right = temp->Left;
			free(temp);
		}
		else if(node->Right->Left == NULL && node->Right->Right != NULL)
		{
			temp = node->Right;
			node->Right = temp->Right;
			free(temp);
		}
		else if(node->Right->Left != NULL && node->Right->Right != NULL)
		{
			temp = node->Right;
			node->Right = NULL;
			Insert(Root,temp->Left);
			Insert(Root,temp->Right);
			free(temp);
		}
	}
	else
		deleteNode(val,node->Right,Root);
	
}
Node* retrieveNode(int val, Node* root)
{
	if(root->data == val)
	{
		Node* temp = root;
		return temp;
	}
	if(root->data>val)
		return retrieveNode(val, root->Left);
	if(root->data<val)
		return retrieveNode(val,root->Right);
}
