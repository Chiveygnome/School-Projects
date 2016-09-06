/*
* Trees.c
*
*  Created on: Jul 3, 2014
*      Original Author: lugoh008
*      Student Authors: Ernest Wheaton and Kyle Kortvely
*
*/

#include "Trees.h"

//auxiliary function prototypes for .h functions
void addBST_aux(BSTree*, Object, BTNode*);
bool removeBST_aux(BSTree*, Object, BTNode*);
void removeMinBST_aux(BSTree*, BTNode*);
void removeMaxBST_aux(BSTree*, BTNode*);
//min and max aux functions are not used by the tree function, but they help during remove
Object minBST_aux(BSTree*, BTNode*);
Object maxBST_aux(BSTree*, BTNode*);
uint heightBST_aux(BTNode*);
bool containsBST_aux(BSTree*, Object, BTNode*);
void printBST_aux(FILE*, BSTree*, BTNode*);
BSTree* allocBalanced(ArrayList*);

BSTree* allocBST(uint esize, void(*rel)(Object), int(*comp)(Object, Object)){
	//using calloc(1, sizeof) instead of malloc because calloc sets everything to 0
	//this causes root and its left and right to be NULL by default
	BSTree* tree = (BSTree*)calloc(1, sizeof(BSTree));
	tree->elementSize = esize;
	//when releasing call tree->releaser(node->data)
	tree->releaser = rel;
	//when comparing call tree->comparator(x, y)
	tree->comparator = comp;
	return tree;
}
/*void releaseBST(BSTree* tree){
//TODO: NEED DIFFERENT ITERATION TO RELEASE TREE
if(tree){
if(tree->root){

}
free(tree);
}
}*/
void setPrinterBST(BSTree* tree, void(*print)(FILE*, Object)){
	//later to call the print invoke as tree->printer(obj)
	//see printBST bellow
	tree->printer = print;
}
void addBST(BSTree* tree, Object obj){
	if (tree){
		//can only add if we have a non-NULL tree
		if (tree->root){
			//if I have a root then I need to traverse the tree to find the spot for new data to come in
			addBST_aux(tree, obj, tree->root);
			return;
		}
		//tree is empty
		//place data in tree->root
		tree->root = (BTNode*)calloc(1, sizeof(BTNode));
		tree->root->data = calloc(1, tree->elementSize);
		memcpy(tree->root->data, obj, tree->elementSize);
	}
}
void addBST_aux(BSTree* tree, Object obj, BTNode* node){
	if (tree->comparator(obj, node->data) < 0){
		//obj is smaller than node->data so it must go at the left of the node
		if (!node->left){
			//create new node here
			node->left = (BTNode*)calloc(1, sizeof(BTNode));
			node->left->data = calloc(1, tree->elementSize);
			memcpy(node->left->data, obj, tree->elementSize);
			return;
		}
		//else call recursion
		addBST_aux(tree, obj, node->left);
	}
	else{
		//obj must go at the right of node
		if (!node->right){
			//create new node here
			node->right = (BTNode*)calloc(1, sizeof(BTNode));
			node->right->data = calloc(1, tree->elementSize);
			memcpy(node->right->data, obj, tree->elementSize);
			return;
		}
		//else call recursion
		addBST_aux(tree, obj, node->right);
	}
}

bool removeBST(BSTree* tree, Object obj){
	if (!tree || !tree->root){
		return false;
	}
	return removeBST_aux(tree, obj, tree->root);
}

bool removeBST_aux(BSTree* tree, Object obj, BTNode* node){
	if (tree->comparator(obj, node->data) == 0){
		//element was found in the tree
		//but the question is how do we remove it?
		//leaves have no problem, because they are located at the end
		//and have no children

		//worst case scenario is what if want to remove the root of the tree?
		//we need to have an element to take its place
		//but this is only needed if the root has at least one child
		Object replacement = NULL;	//NULL is not memory allocation, therefore memory leak
		if (node->left){
			//we can grab the max element from the left (its a leaf)
			//remove it from the tree and place it on the position where the
			//element we want to remove is
			replacement = maxBST_aux(tree, node->left);
			//copy replacement to node
			memcpy(node->data, replacement, tree->elementSize);
			//remove duplicate
			removeMaxBST_aux(tree, node->left);
		}
		else if (node->right){
			//we can grab the min element from the right (its a leaf)
			//remove it from the tree and place it on the position where the
			//element we want to remove is
			replacement = minBST_aux(tree, node->right);
			//copy replacement to node
			memcpy(node->data, replacement, tree->elementSize);
			//remove duplicate
			removeMinBST_aux(tree, node->right);
		}
		return true;
	}
	//try to find it on left or right child
	bool found = false;
	if (tree->comparator(obj, node->data) < 0){
		//should search at the left
		if (node->left){
			//bitwise or |, performs the operation of aggregating booleans
			//as long as one remove returns true, the whole recursion returns true
			found = found | removeBST_aux(tree, obj, node->left);
		}
	}
	else{
		if (node->right){
			//bitwise or |, performs the operation of aggregating booleans
			//as long as one remove returns true, the whole recursion returns true
			found = found | removeBST_aux(tree, obj, node->right);
		}
		//should search at the right
	}
	return found;
}

void removeMinBST(BSTree* tree){
	//root has no parent
	BTNode* parent = NULL;
	BTNode* node = tree->root;
	while (node->left){
		parent = node;
		node = node->left;
	}
	//node is at the minimum, if this node is a leaf then everything is good to go
	//if it is not a leaf we need to reconnect the child to the parent of the node that is being removed
	//luckily, there is only one child at most (at the right)
	if (node->right){
		//we were coming from the left
		if (parent){
			parent->left = node->right;
		}
	}
	node->right = NULL;
	//node is disconnected, proceed to free data
	tree->releaser(node->data);
	//free the node
	free(node);
}
void removeMinBST_aux(BSTree* tree, BTNode* node){
	//to make this work as the non-aux functions we need to reach the node first
	//this is very easy because BST is ordered
	//this is only needed to get the parent of the node in case reconnection is needed

	BTNode* parent = ancestorBST(tree, node);

	//once iterator == null we have parent as the ancestor of node
	//proceed as removeMinBST

	while (node->left){
		parent = node;
		node = node->left;
	}
	//node is at the minimum, if this node is a leaf then everything is good to go
	//if it is not a leaf we need to reconnect the child to the parent of the node that is being removed
	//luckily, there is only one child at most (at the right)
	if (node->right){
		//we were coming from the left
		if (parent){
			parent->left = node->right;
		}
	}
	node->right = NULL;
	//node is disconnected, proceed to free data
	tree->releaser(node->data);
	//free the node
	free(node);
}
void removeMaxBST(BSTree* tree){
	//root has no parent
	BTNode* parent = NULL;
	BTNode* node = tree->root;
	while (node->right){
		parent = node;
		node = node->right;
	}
	//node is at the maximum, if this node is a leaf then everything is good to go
	//if it is not a leaf we need to reconnect the child to the parent of the node that is being removed
	//luckily, there is only one child at most (at the left)
	if (node->left){
		//we were coming from the right
		if (parent){
			parent->right = node->left;
		}
	}
	node->left = NULL;
	//node is disconnected, proceed to free data
	tree->releaser(node->data);
	//free the node
	free(node);
}
void removeMaxBST_aux(BSTree* tree, BTNode* node){
	//to make this work as the non-aux functions we need to reach the node first
	//this is very easy because BST is ordered
	//this is only needed to get the parent of the node in case reconnection is needed

	BTNode* parent = ancestorBST(tree, node);

	//once iterator == null we have parent as the ancestor of node
	//proceed as removeMinBST
	while (node->right){
		parent = node;
		node = node->right;
	}
	//node is at the maximum, if this node is a leaf then everything is good to go
	//if it is not a leaf we need to reconnect the child to the parent of the node that is being removed
	//luckily, there is only one child at most (at the left)
	if (node->left){
		//we were coming from the right
		if (parent){
			parent->right = node->left;
		}
	}
	node->left = NULL;
	//node is disconnected, proceed to free data
	tree->releaser(node->data);
	//free the node
	free(node);
}

//min and max functions dont need auxiliary functions because we can write them
//without recursion
Object minBST(BSTree* tree){
	if (!tree || !tree->root){
		return NULL;
	}
	BTNode* iterator = tree->root;
	while (iterator->left){
		iterator = iterator->left;
	}
	//once no more left children are found we are at the farthest left node (min)
	Object ret = calloc(1, tree->elementSize);
	memcpy(ret, iterator->data, tree->elementSize);
	return ret;
}

Object maxBST(BSTree* tree){
	if (!tree || !tree->root){
		return NULL;
	}
	BTNode* iterator = tree->root;
	while (iterator->right){
		iterator = iterator->right;
	}
	//once no more right children are found we are at the farthest right node (min)
	Object ret = calloc(1, tree->elementSize);
	memcpy(ret, iterator->data, tree->elementSize);
	return ret;
}

//helper for remove
Object minBST_aux(BSTree* tree, BTNode* node){
	if (!node){
		return NULL;
	}
	BTNode* iterator = node;
	while (iterator->left){
		iterator = iterator->left;
	}
	//once no more left children are found we are at the farthest left node (min)
	Object ret = calloc(1, tree->elementSize);
	memcpy(ret, iterator->data, tree->elementSize);
	return ret;
}

Object maxBST_aux(BSTree* tree, BTNode* node){
	if (!node){
		return NULL;
	}
	BTNode* iterator = node;
	while (iterator->right){
		iterator = iterator->right;
	}
	//once no more left children are found we are at the farthest left node (min)
	Object ret = calloc(1, tree->elementSize);
	memcpy(ret, iterator->data, tree->elementSize);
	return ret;
}

BTNode* ancestorBST(BSTree* tree, BTNode* node){
	BTNode* parent = NULL;
	BTNode* iterator = tree->root;
	//this code assumes that node is in tree
	while (iterator != node){
		parent = iterator;
		if (tree->comparator(node->data, iterator->data) < 0){
			//node must be on the left side of iterator
			iterator = iterator->left;
		}
		else{
			//otherwise must be at the right because we assume node is in tree
			iterator = iterator->right;
		}
	}
	return parent;
}

uint heightBST(BSTree* tree){
	assert(tree && tree->root);
	//just call the auxiliary function as we know that the tree at least has a root
	return heightBST_aux(tree->root);
}

uint heightBST_aux(BTNode* node){
	if (!node->left && !node->right){
		//leaves have a height of 0
		return 0;
	}
	//height_BST_aux expects a non-NULL node, so only call it when node is not NULL
	//otherwise 0 is returned automatically
	uint lheight = node->left ? heightBST_aux(node->left) : 0;
	uint rheight = node->right ? heightBST_aux(node->right) : 0;
	//the height of the tree is its max height out of the left and right height
	uint height = lheight > rheight ? lheight : rheight;
	//1 + height causes the increase in height on every level
	return 1 + height;
}

bool containsBST(BSTree* tree, Object obj){
	//search counter is just for demo purposes
	//I want to demonstrate you how fast a BST can be during search
	searchCounter = 0;
	if (tree && tree->root){
		//an element can be within the tree only if the tree is not empty
		return containsBST_aux(tree, obj, tree->root);
	}
	return false;
}
bool containsBST_aux(BSTree* tree, Object obj, BTNode* node){
	//is call the counter is increased so that we can see how fast the search
	//was carried out
	++searchCounter;
	if (!node){
		//if I dont have a node then I dont have data
		return false;
	}
	if (tree->comparator(obj, node->data) == 0){
		//found data since comparator gave 0
		return true;
	}
	if (tree->comparator(obj, node->data) < 0){
		//data is smaller than the current node being evaluated
		//goto left to continue.
		return containsBST_aux(tree, obj, node->left);
	}
	//goto right as data should be there (obj > node->data)
	return containsBST_aux(tree, obj, node->right);
}

void printBST(FILE* out, BSTree* tree){
	fprintf(out, "{");
	if (tree && tree->root){
		printBST_aux(out, tree, tree->root);
	}
	fprintf(out, "}");
}
void printBST_aux(FILE* out, BSTree* tree, BTNode* node){
	if (!node){
		return;
	}
	printBST_aux(out, tree, node->left);
	tree->printer(out, node->data);
	//just a tab in between for now
	fprintf(out, "\t");
	printBST_aux(out, tree, node->right);
}

//////////////////////////////
//////////////////////////////
//	COMPLETED BY STUDENTS	//
//////////////////////////////
//////////////////////////////

/*
*In rebalance we are taking the original tree an determining if the tree is lopsided.
*If it is, then we put the root of the new tree a step lower on the lower side.
*Then we use the rebalance auxiliary function to add all the leaves to the new tree recursively.
*After this, we check if it is balanced, with the rebalance function.  If not then
*we repeat the process.  As of 7/16/14, this method can only handle perfect balanced
*trees, because i have no way to check it is going back and forth between one level.
*We will work on this, but this is how it stands. **Important fix, as of 7/19/14: 
*I have fixed the adding double values to the tree when re-evauluating the root.
*We also added a few of the functions you have requested, even though our function 
*doesnt use them, just in case we miss points for not including them.
*/
BSTree* rebalance(BSTree* T)
{
	BSTree* Tree = allocBST(sizeof(Object), &releasePrimitive, &compareInt);
	BSTree* M = allocBST(sizeof(Object),&releasePrimitive,&compareInt);
	BTNode* Node = (BTNode*)malloc(sizeof(BTNode));
	Node = T->root;
	int count = 0;
	while (Node->left)
	{
		Node = Node->left;
		count++;
	}
	BTNode* Node1 = (BTNode*)malloc(sizeof(BTNode));
	Node = T->root;
	int count1 = 0;
	while (Node->right)
	{
		Node = Node->right;
		count1++;
	}
	if (count == count1)
	{
		return T;
	}
	else if (count>count1)
	{
		Tree->root=T->root->left;
		Node = T->root;
		//removeBST(T,Node->data);
		rebalance_Aux(Node, Tree);
		//M = rebalance(Tree);
		//memcpy(Tree,M,sizeof(BSTree));
		return Tree;
	}
	else
	{
		Tree->root = T->root->right;
		Node = T->root;
		//removeBST(T,Node->data);
		rebalance_Aux(Node, Tree);
		//M = rebalance(Tree);
		//memcpy(Tree, M,sizeof(BSTree));
		return Tree;
	}
}
void rebalance_Aux(BTNode* N, BSTree* T)
{
	if (N->left != NULL)
	{
		addBST(T,N->left->data);//add a duplicate value at the re-evaluated position
		removeBST(T,N->left->data);//remove the original value
		rebalance_Aux(N->left, T);
		return;
	}
	if (N->right!=NULL)
	{
		addBST(T,N->left->data);
		removeBST(T,N->left->data);
		rebalance_Aux(N->right, T);
		return;
	}
}
BSTree* allocBalanced(ArrayList* sorted)
{
	int i;
	BSTree* T = allocBST(sizeof(Object), &releasePrimitive, &compareInt);
	memcpy(T->root,sorted[0].data,sizeof(Object*));
	for (i = 1; i<sorted->length; i++)
	{
		addBST(T, sorted[i].data);
	}
	return T;
}
void releaseBST(BSTree* T)
{
	if (T)
	{
		if (T->root)
		{
			BTNode* N = T->root;
			if (N->left)
			{
				releaseBST_Aux(N);
			}
			if (N->right)
			{
				releaseBST_Aux(N);
			}
			if (!N->left && !N->right)
			{
				free(N);
			}
		}
		free(T);
	}
	return;
}

void releaseBST_Aux(BTNode* N)
{
	if (N->left!=NULL)
	{
		releaseBST_Aux(N->left);
	}
	if (N->right!=NULL)
	{
		releaseBST_Aux(N->right);
	}
	if (N->left == NULL && N->right == NULL)
	{
		free(N);
		return;
	}
}

//////////////////////////
//////////////////////////
//	TESTING EXAMPLES!!	//
//////////////////////////
//////////////////////////

int testBST(int argc, char** argv){
	srand(100);
	BSTree* tree = allocBST(sizeof(int), &releasePrimitive, &compareInt);
	setPrinterBST(tree, &printInt);
	printBST(stdout, tree);
	printf("\n");
	Int x = (Int)allocInt();
	int i = 0;
	for (i = 20; i >= 0; --i){
		*x = rand() % 100;
		addBST(tree, x);
	}
	printBST(stdout, tree);
	printf("\n");
	*x = 97;
	if (containsBST(tree, x)){
		printf("%d is contained in the tree\n", *x);
		printf("Found in %d steps\n", searchCounter);
		removeBST(tree, x);
		printBST(stdout, tree);
		printf("\n");
	}
	else{
		printf("%d is not contained in the tree\n", *x);
		printf("Not found in %d steps\n", searchCounter);
	}
	printf("Height of the tree %d\n", heightBST(tree));
	releasePrimitive(x);
	//still not releasing the tree, work pending
	return 0;
}

/*


int testSimpleBST(int argc, char** argv){
srand(100);
BSTree* tree = allocBST(sizeof(int), &releasePrimitive, &compareInt);
setPrinterBST(tree, &printInt);
printBST(stdout, tree);
printf("\n");
Int x = allocInt();
int i = 0;
for(i = 10; i >= 0; --i){
*x = rand() % 100;
addBST(tree, x);
}
printBST(stdout, tree);
printf("\n");
*x = 84;
if(containsBST(tree, x)){
printf("%d is contained in the tree\n", *x);
printf("Found in %d steps\n",searchCounter);
removeBST(tree, x);
printBST(stdout, tree);
printf("\n");
}
else{
printf("%d is not contained in the tree\n", *x);
printf("Not found in %d steps\n",searchCounter);
}
printf("Height of the tree %d\n", heightBST(tree));
if(isEmptyBST(tree)){
printf("The tree is empty\n");
}
//removeMaxBST(tree);
//removeMinBST(tree);
//printBST(stdout, tree);
//printf("\n");

releasePrimitive(x);
//still not releasing the tree, work pending
return 0;
}


int testLabBST(int argc, char** argv){
BSTree* tree = allocBST(sizeof(int), &releasePrimitive, &compareInt);
setPrinterBST(tree, &printInt);
printBST(stdout, tree);
printf("\n");
int i = 0;
int elements[] = {7, 5, 15, 5, 11, 83};
for(i = 0; i < sizeof(elements) / sizeof(int); ++i){
addBST(tree, &elements[i]);
}
printBST(stdout, tree);
printf("\nHEIGHT = %d\n", heightBST(tree));

ArrayList* array = BST2Array(tree);
setReleaseDArray(array, tree->releaser);
setPrinterDArray(array, tree->printer);
printDArray(stdout, array);
releaseDArray(array);

//still not releasing the tree, work pending
return 0;
}



*/