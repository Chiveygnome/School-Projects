#ifndef DATASET_H_
#define DATASET_H_
#include <cstdio>
#include<cstdlib>
#include "DArray.h"
typedef struct DataSet
{
	struct DArray* array;
	Byte min;
	Byte max;
};
struct DataSet* allocDataSet()
{
	struct DataSet* dataSet = (DataSet*)malloc(sizeof(DataSet));
	dataSet->setData = allocDArray(10);
	dataSet->min = 255;
	dataSet->max = 0;
	return dataSet;
}
void releaseDataSet(DataSet* set)
{
	if(set)
	{
		if(set->max)
			free(set->max);
		if(set->min)
			free(set->min);
		if(set->array)
			releaseDArray(set->array);
		free(set);
	}
}
void appendDataSet(DataSet* dataSet, Byte NewValue)
{
	
}
DataSet* setUnionArray(DataSet* A,DataSet* B)
{
	DataSet* UnionSet = allocDataSet();
	for(int i = 0;i<A->array->length;i++)
	{
		appendDArray(UnionSet->array,A->array->data[i]);
	}
	for(int j = 0;j<(B->array->length);j++)
	{
		if(!containsDArray(UnionSet->array,B->array->data[j])
		{
			appendDArray(UnionSet->array,B->array->data[j]);
		}
	}
	return UnionSet;
}
struct DataSet* setIntersectDataSet(DataSet* A,DataSet* B)
{
	DataSet* IntersectSet = allocDataSet();
	for(int i = 0;i<(A->array->length);i++)
	{
		if(contiansDArray(A->array,B->array->data[i])
		{
			appendDArray(IntersectSet->array,B->array->data[i]);
		}
	}
	return IntersectSet;
}
DataSet* diffDataSet(DataSet* A, DataSet* B)
{
	DataSet* diffSet = allocDataSet();
	for(int i = 0; i<(A->array->length);i++)
	{
		if(!containsDArray(B->array,A->array->data[i])
			appendDArray(diffSet->array, A->array->data[i]);
	}
	return diffSet;
}



#endif DATASET_H_