#include <stdlib.h>
#include <stdio.h>

typedef struct{
	int data;
	Node* Left;
	Node* Right;
}Node;

Node* MakeNode(int);
int compare(Node*,Node*);
int compare(Node*, int);
void Insert(Node*, Node*);
int Find(Node*, int);
int count(Node*);
int countData(Node*);
void deleteNode(int, Node*, Node*);
Node* retrieveNode(int, Node*);