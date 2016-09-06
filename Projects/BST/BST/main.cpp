#include"Trees.h"

void main(int argv,char** argc)
{
	srand(100);
	BSTree* Tree = allocBST(sizeof(int),&releasePrimitive,&compareInt);
	setPrinterBST(Tree,&printInt);
	//system("PAUSE");
//	BTNode* Root = (BTNode*)malloc(sizeof(BTNode));
	//Root->data = (Object)35;
	//Tree->root = Root;
	Int x = (Int)allocInt();
	int i;
	for(i = 0; i<16;i++)
	{
		*x = rand()%100;
		//BTNode* Node = (BTNode*)malloc(sizeof(BTNode));
		//Node->data = (Object)Rand;
		addBST(Tree,x);
		//free(Node);
	}
	//system("PAUSE");
	printBST(stdout,Tree);
	rebalance(Tree);
	printBST(stdout,Tree);
	releaseBST(Tree);
	//system("PAUSE");
	/*	srand(100);
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
	//still not releasing the tree, work pending*/
	return;
}