/*
 * main.c
 *
 *  Created on: May 21, 2014
 *      Author: lugoh008
 */

#include<stdio.h>
#include "DArray.h"

Word squareElement(Byte);
Word cubeElement(Byte);


int main(int argc, char** argv){
	struct DArray* array;
	array = allocDArray(10);
	//array = (struct DArray*)malloc(sizeof(struct DArray));
	printf("Usage of array is %d/%d\n", array->length, array->size);

	int i = 0;
	for(i = 0; i < 20; ++i){
		appendDArray(array, i);
		printf("Usage of array is %d/%d\n", array->length, array->size);
		if(array->length == 10){
			//see contents before array increase
			printDArray(array);
		}
	}
	//see contents at the end
	printDArray(array);

	printf("Processing square elements ...\n\n");
	Word* results = applyDArray(array, &squareElement);
	for(i = 0; i < array->length; ++i){
		printf("Value of function(%d) = %d\n", array->data[i], results[i]);
	}

	printf("Processing cube elements ...\n\n");
	Word* results2 = applyDArray(array, &cubeElement);
		for(i = 0; i < array->length; ++i){
			printf("Value of function2(%d) = %d\n", array->data[i], results2[i]);
		}

	//testing remove
	printf("Array contains 13? %s\n", containsDArray(array, 13) ? "YES" : "NO");
	Byte oldValue = removeDArray(array, 13);
	printf("Removed %d\n", oldValue);
	printf("Array contains 13? %s\n", containsDArray(array, 13) ? "YES" : "NO");
	printf("New Array is: ");
	printDArray(array);

	free(results);
	free(results2);
	releaseDArray(array);
	return 0;
}

Word squareElement(Byte element){
	return element * element;
}

Word cubeElement(Byte element){
	return element * element * element;
}
