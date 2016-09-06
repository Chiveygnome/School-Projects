/*
 * DoubleLinkedList.h
 *
 *  Created on: Jun 17, 2014
 *      Author: Ernest Wheaton and Kyle Kortvely
 */

#ifndef DOUBLELINKEDLIST_H_
#define DOUBLELINKEDLIST_H_
#include<stdlib.h>
#include"stdbool.h"
#include<stdio.h>

typedef unsigned int uint;
typedef unsigned long ulong;
typedef void* Object;

typedef struct _DNode{
 Object data;
 struct _DNode* prev;
 struct _DNode* next;
} DNode;


typedef struct _DoubleLinkedList{
 DNode* head;
 DNode* tail;
 uint length;
 uint elementSize;
} DoubleLinkedList;

DoubleLinkedList* allocDList(uint);
void releaseDList(DoubleLinkedList*);
void insertDListElementAt(DoubleLinkedList*, Object, uint);
void appendDList(DoubleLinkedList*, Object);
void insertDList(DoubleLinkedList*, Object);
DoubleLinkedList* reverseDList(DoubleLinkedList*);
DoubleLinkedList* halfList(DoubleLinkedList*);
Object removeDList(DoubleLinkedList*, uint);
void printDList(DoubleLinkedList*);
#endif /* DOUBLELINKEDLIST_H_ */
