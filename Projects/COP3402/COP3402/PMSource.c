#include "stdio.h"
#include "string.h"
#include "stdlib.h"
#define MAX_STACK_HEIGHT  2000
#define MAX_CODE_LENGTH  500
#define MAX_LEXI_LEVELS  3
typedef struct Code{
	int OP;
	int L;
	int M;
}Code;
void action(char*);
void printTrace(FILE*);
void toCode(char*);
void toOP(Code*,FILE*);
void OPR(int);
void Negate(void);
void Add(void);
void Sub(void);
void Multi(void);
void Div(void);
void Odd(void);
void Mod(void);
void Eql(void);
void Neq(void);
void Lss(void);
void Leq(void);
void Gtr(void);
void Geq(void);
void Lit(int);
void Return(void);
void Inc(int);
void load(int, int);
void store(int, int);
int base(int);
void cal(int, int);
void jmp(int);
void jpc(int);
void sio(int);
//This is the sole work of Ernest Wheaton for the class COP 3402 at the University of Central Florida.  There can be no copying of this file by any person
//unless consent by the above mentioned developer has been given or as the law allows.
static int SP = 0;
static int BP = 1;
static int PC = 0;
static Code* IR = { NULL };
static int halt = 0;// flag for the end of program
static Code CodeMemory[MAX_CODE_LENGTH];
static int Stack[MAX_STACK_HEIGHT] = { 0 };
int count = -1;
int arr[MAX_CODE_LENGTH];
int main(int argc, char** argv)
{
	int i;
	for( i = 0; i<MAX_CODE_LENGTH;i++)
	{
		CodeMemory[i].L = (int)malloc(sizeof(int));
		CodeMemory[i].OP = (int)malloc(sizeof(int));
		CodeMemory[i].M = (int)malloc(sizeof(int));
	}
	//CodeMemory[MAX_CODE_LENGTH] = (Code*)malloc(sizeof(*CodeMemory)*MAX_STACK_HEIGHT);
	//printf("malloced");
	action(argv[0]);
	return 0;
}


void action(char* file)
{
	FILE *ofp;
	toCode(file);
	ofp = fopen("stacktrace.txt","w");
	while (halt == 0)
	{
		IR = &CodeMemory[PC];//write to instruction register, i.e. fetch
		toOP(IR,ofp);// decode and execute
		/*
		The four if statements define the exceptions to the increment Program Counter
		
		if(IR->OP == 2)I have no idea whi this if statement was ever implemented, it only screws up my program
		{
			printTrace(ofp);
			continue;
		}*/
		if(IR->OP == 5)
		{
			printTrace(ofp);
			continue;
		}
		if(IR->OP == 7) 
		{
			printTrace(ofp);
			continue;
		}
		if(IR->OP == 8)
		{
			printTrace(ofp);
			continue;
		}
		PC++;//increment PC
		printTrace(ofp);//print the Trace
	}
	fclose(ofp);
	//printf("test done");
}
void printTrace(FILE *file)
{
	
	int i = BP;
	int j, k = count;
	if(SP<= 0)
	{
		fprintf(file, "\n");
		return;
	}
	for ( j = 1; j <= SP; j++)
	{
		//printf("%d ",Stack[j]);//print the vaues in the stack
		fprintf(file,"%d ",Stack[j]);
		if(k==-1)//if -1 then the array is empty
		{
			continue;
		}
		if (j == arr[k])//if the count is equal to one of the values in the array print out the dividers
		{
			//printf("| ");
			fprintf(file,"| ");
			k--;
		}
	}
	//printf("\n");
	fprintf(file,"\n");
	return;
}
void printInstr(Code* instr,char* name,FILE* fp)
{
	fprintf(fp,"%s\t%d\t%d\t",name,instr->L,instr->M);
}
void toCode(char* file)
{
	int i = 0;//initialize the counter
	FILE *fp;//initialize file pointer
	fp = fopen("mcode.txt","r");//hardcoded file
	//from here on in there is a lot of legacy code commented out. And leave the printing 
	//I have left it in for reference.  It probably, usually, definately won't work.
	if (fp == NULL)//file error catch
	{
		printf("Could not open file.");
		exit(0);
	}
	//printf("got to the while statement");
	while (!feof(fp))
	{
		//printf("It crashes at the read");
		//gcc/eustis has issue here
		fscanf(fp, "%d", (&CodeMemory[i].OP));//, (&CodeMemory[i].L), (&CodeMemory[i].M));
		fscanf(fp,"%d",(&CodeMemory[i].L));//take the three values from each line
		fscanf(fp,"%d",(&CodeMemory[i].M));
		//fscanf(fp,"%c %c %c");
		//printf("%d",i);
		i++;
	}
	//printf("Passed read");
	fclose(fp);
	return;
}
void toOP(Code* instr,FILE* fp)
{
	switch (instr->OP)
	{
	case 1:
		Lit(instr->M);
		printInstr(instr,"Lit",fp);
		break;
	case 2:
		OPR(instr->M);
		printInstr(instr,"Opr",fp);
		break;
	case 3:
		load(instr->L, instr->M);
		printInstr(instr,"Lod",fp);
		break;
	case 4:
		store(instr->L, instr->M);
		printInstr(instr,"Str",fp);
		break;
	case 5:
		cal(instr->L, instr->M);
		printInstr(instr,"Cal",fp);
		break;
	case 6:
		Inc(instr->M);
		printInstr(instr,"Inc",fp);
		break;
	case 7:
		jmp(instr->M);
		printInstr(instr,"Jmp",fp);
		break;
	case 8:
		jpc(instr->M);
		printInstr(instr,"Jpc",fp);
		break;
	case 9:
		sio(instr->M);
		printInstr(instr,"Sio",fp);
		break;
	case 10:
		sio(instr->M);
		printInstr(instr,"Sio",fp);
		break;
	case 11:
		sio(instr->M);
		printInstr(instr,"Sio",fp);
		break;
	default:
		printf("Not a valid code.  error:1");
		halt = 1; //crash if incorrect
		break;

	}
	return;
}
void OPR(int M)
{
	switch (M)
	{
	case 0:
		Return();
		break;
	case 1:
		Negate();
		break;
	case 2:
		Add();
		break;
	case 3:
		Sub();
		break;
	case 4:
		Multi();
		break;
	case 5:
		Div();
		break;
	case 6:
		Odd();
		break;
	case 7:
		Mod();
		break;
	case 8:
		Eql();
		break;
	case 9:
		Neq();
		break;
	case 10:
		Lss();
		break;
	case 11:
		Leq();
		break;
	case 12:
		Gtr();
		break;
	case 13:
		Geq();
		break;
	default:
		printf("Not a valid code.  error:2");
		halt = 1; //crash if incorrect
		break;
	}
	return;
}
	void Negate()
	{
		Stack[SP] = -Stack[SP];
	}
	void Add()
	{
		SP--;
		Stack[SP] = Stack[SP] + Stack[SP + 1];
	}
	void Sub()
	{
		SP--;
		Stack[SP] = Stack[SP] - Stack[SP + 1];
	}
	void Multi()
	{
		SP--;
		Stack[SP] = Stack[SP] * Stack[SP + 1];
	}
	void Div()
	{
		SP--;
		Stack[SP] = Stack[SP] / Stack[SP + 1];
	}
	void Odd()
	{
		if (Stack[SP] % 2 == 0) Stack[SP] = 0;
		else Stack[SP] = 1;
	}
	void Mod()
	{
		SP--;
		Stack[SP] = Stack[SP] % Stack[SP + 1];
	}
	void Eql()
	{
		SP--;
		if (Stack[SP] == Stack[SP + 1])Stack[SP] = 1;
		else Stack[SP] = 0;
	}
	void Neq()
	{
		SP--;
		if (Stack[SP] != Stack[SP + 1])Stack[SP] = 1;
		else Stack[SP] = 0;
	}
	void Lss()
	{
		SP--;
		if (Stack[SP] < Stack[SP + 1])Stack[SP] = 1;
		else Stack[SP] = 0;
	}
	void Leq()
	{
		SP--;
		if (Stack[SP] <= Stack[SP + 1])Stack[SP] = 1;
		else Stack[SP] = 0;
	}
	void Gtr()
	{
		SP--;
		if (Stack[SP] > Stack[SP + 1])Stack[SP] = 1;
		else Stack[SP] = 0;
	}
	void Geq()
	{
		SP--;
		if (Stack[SP] >= Stack[SP + 1])Stack[SP] = 1;
		else Stack[SP] = 0;
	}
	void Lit(int M)
	{
		SP = SP + 1;
		Stack[SP] = M;
	}
	void Return()
	{
		SP = BP - 1;
		PC = Stack[SP + 4];
		BP = Stack[SP + 3];
		count--;
	}
	void Inc(int M)
	{
		BP = SP+1;
		if (M >= 4)
		{
			SP += M;
		}
		else
		{
			SP += 3;
			printf("Number too low, automatically allocating 4 spaces");
		}
		count++;
		arr[count] = BP-1;
	}
	void load(int L, int M)
	{
		SP++;
		Stack[SP] = Stack[base(L) + M];
	}
	void store(int L, int M)
	{
		Stack[base(L) + M] = Stack[SP];
		SP--;
	}
	int base(int L)
	{
		int b1 = BP; // find base L levels down
        while (L > 0) {
            b1 = Stack[b1+1];
            L--;
        }
        return b1;
	}
	void cal(int L, int M)
	{
		Stack[SP + 1] = 0;
		Stack[SP + 2] = base(L);
		Stack[SP + 3] = BP;
		Stack[SP + 4] = PC+1;
		BP = SP + 1;
		PC = M;
	}
	void jmp(int M)
	{
		PC = M;
	}
	void jpc(int M)
	{
		if (Stack[SP] == 0) PC = M;
		else PC++;
		SP--;
	}
	void sio(int M)
	{
		int in = 0;
		switch (M)
		{
		case 1:
			printf("%d", Stack[SP]);
			Stack[SP] = 0;
			SP--;
			break;
		case 2:
			SP++;
			scanf("%d",&in);
			Stack[SP] = in;
			break;
		case 3:
			halt = 1;
			break;
		}
	}
