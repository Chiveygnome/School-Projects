/*
* DoubleLinkedList.c
*
*  Created on: Jun 17, 2014
*      Author: Ernest Wheaton and Kyle Kortvely
*/

#include<stdlib.h>
#include"stdbool.h"
#include<stdio.h>
#include <string.h>
#include"DoubleLinkedList.h"

//STEP 1
DoubleLinkedList* allocDList(uint Size)
{
	DoubleLinkedList* DoubleLinkedListist = (DoubleLinkedList*)malloc(sizeof(DoubleLinkedList));
	DoubleLinkedListist->head = (DNode*)malloc(sizeof(DNode));
	DoubleLinkedListist->head->prev= NULL;
	DoubleLinkedListist->tail = DoubleLinkedListist->head;
	DoubleLinkedListist->length = 0;
	DoubleLinkedListist->elementSize = Size;
//	DoubleLinkedListist->head->next = DoubleLinkedListist->tail;
//	DoubleLinkedListist->tail->prev = DoubleLinkedListist->head;
	return DoubleLinkedListist;
}

//STEP 2
void releaseDList(DoubleLinkedList* list)
{
	DNode *helper = list->tail;
	while (helper != NULL)
	{
		free(helper->next);
		helper = helper->prev;
	};
	free(list->head);
}

//STEP 3
void insertDListElementAt(DoubleLinkedList* list, Object newElement, uint position)
{
	uint pos = 0;
	DNode* Node = (DNode*)malloc(sizeof(DNode*));
	DNode* iterator = (DNode*)malloc(sizeof(DNode*));
	iterator = list->head;
	while (pos <= position)
	{
		iterator->next;
		++pos;
	}
	Node->next = iterator->next;
	Node->prev = iterator;
	Node->prev->next = Node;
	Node->data = newElement;
}

//STEP 4
void appendDList(DoubleLinkedList* list, Object newElement)
{
	if (!list->head)
	{
		list->head = (DNode*)malloc(sizeof(DNode*));
		list->head->data = malloc(list->elementSize);
		memcpy(list->head->data, newElement, list->elementSize);
	}
	else if (list->head == list->tail)
	{
		list->tail = (DNode*)malloc(sizeof(DNode*));
		list->tail->data = malloc(list->elementSize);
		list->tail->data = newElement;
		list->tail->prev = list->head;
		list->tail->next = NULL;
		list->head->next = list->tail;
	}
	else
	{
		DNode* newTail = (DNode*)calloc(1, sizeof(DNode*));
		DNode* newNode = (DNode*)calloc(1, sizeof(DNode*));
		newNode = list->tail;
		list->tail = newTail;
		newTail->next = NULL;
		newNode->next = newTail;
		list->tail->prev = newNode;
		list->tail->data = newElement;
	}
}

//STEP 5
void insertDList(DoubleLinkedList* list, Object newElement)
{
	DNode* Node = (DNode*)malloc(sizeof(DNode*));
	Node->data = newElement;
	Node->prev = NULL;
	Node->next = list->head;
	list->head->prev = Node;
	list->head = Node;
}

//STEP 6
DoubleLinkedList* reverseDList(DoubleLinkedList* list)
{
	DoubleLinkedList* List = (DoubleLinkedList*)malloc(sizeof(DoubleLinkedList*));
	DNode* iterator = (DNode*)malloc(sizeof(DNode*));
	iterator = list->head->next;
	List->head = list->tail;
	List->tail = list->head;
	while (iterator->next)
	{
		appendDList(List, iterator->data);
		iterator = iterator->next;
	}
	return List;
}

//STEP 7
DoubleLinkedList* halfList(DoubleLinkedList* list)
{
	DNode* iterator1 = (DNode*)malloc(sizeof(DNode*));
	DNode* iterator2 = (DNode*)malloc(sizeof(DNode*));
	DoubleLinkedList* List = allocDList(sizeof(Object));
	iterator1 = list->head;
	iterator2 = list->tail;
	while (iterator1 != iterator2)
	{
		iterator1 = iterator1->next;
		iterator2 = iterator2->prev;
	}
	while (iterator1 != list->tail)
	{
		appendDList(List, iterator1->data);
		iterator1 = iterator1->next;
	}
	return List;
}

//STEP 8
Object removeDList(DoubleLinkedList* list, uint position)
{
	DNode* iterator = (DNode*)malloc(sizeof(DNode*));
	DNode* temp = (DNode*)malloc(sizeof(DNode*));
	Object Val;
	iterator = list->head;
	uint pos = 0;
	while (pos <= position)
	{
		iterator->next;
		++pos;
	}
	Val = iterator->data;
	temp = iterator->prev;
	temp->next = iterator->next;
	return Val;
}

//STEP 9
void printDList(DoubleLinkedList* list)
{
	//WORKING
	DNode* current = (DNode*)malloc(sizeof(DNode*));
	current = list->head;
	while (current != NULL){
		printf("%d\n", current->data);
		current = current->next;
	}
}
