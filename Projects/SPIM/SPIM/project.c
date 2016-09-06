#include "spimcore.h"
/*
***Developer notes:
*** Aparently Visual studio breaks on Professor Angell's code at line 357: setvbuf()
*** I get an Assertion fail that states that the int in out of bounds.  When I
*** comment that line out, my code works just fine. I don't know if anyone else
*** has run into this problem.
*** Name: Ernie Wheaton
*** PID: e3388709
*** Class: CDA 3103
*** Date of last update: 11/16/14
*/

/* ALU */
/* 10 Points */
void ALU(unsigned A,unsigned B,char ALUControl,unsigned *ALUresult,char *Zero)
{
	if(ALUControl==0)//Add
	{
		*ALUresult = A+B;
	}
	else if(ALUControl == 1)//Sub
	{
		*ALUresult = A-B;
	}
	else if(ALUControl == 2)//slt signed
	{
		if((signed)A<(signed)B)
			*ALUresult = 1;
		else
			*ALUresult = 0;
	}
	else if(ALUControl == 3)//slt unsigned
	{
		if(A<B)
			*ALUresult = 1;
		else
			*ALUresult = 0;
	}
	else if(ALUControl == 4)//And
	{
		*ALUresult = A&B;
	}
	else if(ALUControl == 5)//Or
	{
		*ALUresult = A|B;
	}
	else if(ALUControl == 6)//sll by 16 bits
	{
		*ALUresult = B<<16;
	}
	else if(ALUControl == 7)//Not
	{
		*ALUresult = ~A;
	}
	if(*ALUresult == 0)//Zero
		*Zero = 1;
	else
		*Zero = 0;
	return;
}

/* instruction fetch */
/* 10 Points */
int instruction_fetch(unsigned PC,unsigned *Mem,unsigned *instruction)
{
	if(PC % 4 == 0 && PC <= 0xFFFF)//check if word aligned and in bounds
	{
		*instruction = Mem[PC>>2];//fetch the instruction
		return 0;// return go signal
	}
	else//if not return halt signal
	{
		//printf("here 0");
		return 1;
	}
}


/* instruction partition */
/* 10 Points */
void instruction_partition(unsigned instruction, unsigned *op, unsigned *r1,unsigned *r2, unsigned *r3, unsigned *funct, unsigned *offset, unsigned *jsec)
{
	*op = (instruction >> 26) & 0x3F; //get instructions[31-26]  We shift the instruction over by 26 bits to get the leading six and then use the & by 0011 1111 to save these 6 bits to the op variable
	*r1 = (instruction >> 21) & 0x1F; //get instructions[25-21]  ^ same execpt for 5 bits and 0001 1111
	*r2 = (instruction >> 16) & 0x1F; //get instructions[20-16]  ^ same
	*r3 = (instruction >> 11) & 0x1F; //get instructions[15-10]  ^ same
	*funct = instruction & 0x3F; //get instructions[5-0]  same as first instruction
	*offset = instruction & 0xFFFF; //get instructions[15-0] same but with 16 bits and with 1111 1111 1111 1111
	*jsec = instruction & 0x3FFFFFF; //get instructions[25-0] same, but with 26 bits and with 0011 1111 1111 1111 1111 1111 1111
}



/* instruction decode */
/* 15 Points */
int instruction_decode(unsigned op,struct_controls *controls)
{
	if(op == 0x0)//R-type
	{
		controls->RegDst = 1;//using registers
		controls->ALUSrc = 0;
		controls->MemtoReg = 0;
		controls->RegWrite = 1;//writing to a register
		controls->MemRead = 0;
		controls->MemWrite = 0;
		controls->Branch = 0;
		controls->Jump =0;
		controls->ALUOp = 7;//R-type instruction, to be decoded later
	}
	else if(op == 0x2)//jump
	{
		controls->RegDst = 0;
		controls->ALUSrc = 0;
		controls->MemtoReg = 0;
		controls->RegWrite = 0;
		controls->MemRead = 0;
		controls->MemWrite = 0;
		controls->Branch = 0;
		controls->Jump =1;//jumping
		controls->ALUOp = 0;
	}
	else if(op == 0x4)//beq
	{
		controls->RegDst = 0;
		controls->ALUSrc = 1;//using the extend_value
		controls->MemtoReg = 0;
		controls->RegWrite = 0;
		controls->MemRead = 0;
		controls->MemWrite = 0;
		controls->Branch = 1;//branching
		controls->Jump =0;
		controls->ALUOp = 1;//subtracting
	}
	else if(op == 0xF)//lui
	{
		controls->RegDst = 0;
		controls->ALUSrc = 1;//using the extend_value
		controls->MemtoReg = 0;
		controls->RegWrite = 1;//writing to register
		controls->MemRead = 0;
		controls->MemWrite = 0;
		controls->Branch = 0;
		controls->Jump =0;
		controls->ALUOp = 6;//shifting 
	}
	else if(op == 0x8)//addi
	{
		controls->RegDst = 0;
		controls->ALUSrc = 1;//I-type
		controls->MemtoReg = 0;
		controls->RegWrite = 1;//writing to a register
		controls->MemRead = 0;
		controls->MemWrite = 0;
		controls->Branch = 0;
		controls->Jump =0;
		controls->ALUOp = 0;//adding
	}
	else if(op == 0xA)//slti
	{
		controls->RegDst = 0;
		controls->ALUSrc = 1;//I-type
		controls->MemtoReg = 0;
		controls->RegWrite = 1;//writing to a register
		controls->MemRead = 0;
		controls->MemWrite = 0;
		controls->Branch = 0;
		controls->Jump =0;
		controls->ALUOp = 2;//signed set less than
	}
	else if(op == 0xB)//slti unsigned
	{
		controls->RegDst = 0;
		controls->ALUSrc = 1;//I-type
		controls->MemtoReg = 0;
		controls->RegWrite = 1;//writing to register
		controls->MemRead = 0;
		controls->MemWrite = 0;
		controls->Branch = 0;
		controls->Jump =0;
		controls->ALUOp = 3;//unsigned set less than
	}
	else if(op == 0x23)//lw
	{
		controls->RegDst = 0;
		controls->ALUSrc = 1;//using extended_value
		controls->MemtoReg = 1;//writing from memory
		controls->RegWrite = 1;//writing to a register
		controls->MemRead = 1;//Reading Memory
		controls->MemWrite = 0;
		controls->Branch = 0;
		controls->Jump =0;
		controls->ALUOp = 0;//don't care
	}
	else if(op == 0x2B)//sw
	{
		controls->RegDst = 2;//don't care
		controls->ALUSrc = 1;//using extended_value
		controls->MemtoReg = 2;//don't care
		controls->RegWrite = 0;
		controls->MemRead = 0;
		controls->MemWrite = 1;// writing to Memory
		controls->Branch = 0;
		controls->Jump =0;
		controls->ALUOp = 0;//don't care
	}
	else//bad instruction trips halt signal
	{
		//printf("here1");
		return 1;
	}
	return 0;
}

/* Read Register */
/* 5 Points */
void read_register(unsigned r1,unsigned r2,unsigned *Reg,unsigned *data1,unsigned *data2)
{
	*data1 = Reg[r1];//just copying data
	*data2 = Reg[r2];
}


/* Sign Extend */
/* 10 Points */
void sign_extend(unsigned offset,unsigned *extended_value)
{
	if (offset>>15 == 0)
		*extended_value = offset & 0x0000FFFF; //Extend by 16 0 bits
	else if(offset>>15 == 1)
		*extended_value = offset | 0xFFFF0000; //Extend by 16 1 bits

}

/* ALU operations */
/* 10 Points */
int ALU_operations(unsigned data1,unsigned data2,unsigned extended_value,unsigned funct,char ALUOp,char ALUSrc,unsigned *ALUresult,char *Zero)
{
	char decider = 0;//relates to the ALUOp for the ALU function.  This will decide how the ALU beaves.
	if(ALUSrc == 1)//if either I-type or branch, use extended value
		data2 = extended_value;
	if(ALUOp == 7)// This block takes the funct value and determines what ALUOp should be returned
	{
		//Some of these values i found on websites if they were not explicitly stated in instructions or in slides.
		if(funct == 0x20)//ADD
		{
			decider = 0;
		}
		else if(funct == 0x22)//SUB 
		{
			decider = 1;
		}
		else if(funct == 0x24)//AND
		{
			decider = 4;
		}
		else if(funct == 0x25)//OR
		{
			decider = 5;
		}
		else if(funct == 0x27)//NOT/NOR
		{
			decider = 7;
		}
		else if(funct == 0x2A)//STL SIGNED
		{
			decider = 2;
		}
		else if(funct == 0x2B)//STL UNSIGNED
		{
			decider =3;
		}
		else//Bad instruction
		{
			//printf("here");
			return 1;
		}
	}
	ALU(data1,data2,decider,ALUresult,Zero);
	return 0;
}

/* Read / Write Memory */
/* 10 Points */
int rw_memory(unsigned ALUresult,unsigned data2,char MemWrite,char MemRead,unsigned *memdata,unsigned *Mem)
{
	if((MemWrite == 1 || MemRead == 1) && ALUresult % 4 != 0 || ALUresult>0xFFFF || ALUresult<0)//Make sure that, if writing to memory, ALUresult is in bounds
	{
		//printf("here2");
		return 1;
	}
	if(MemWrite == 1 && (MemRead == 0 || MemRead == 2)) //if the memory write signal is asserted then write data2 to Mem
	{
		Mem[ALUresult>>2] = data2;//shift ALUresult and use this as reference to access in Mem
	}
	else if(MemRead == 1 && (MemWrite== 0 || MemWrite == 2)) //if the memory read signal is asserted then read the address of ALUresult to memdata
	{
		*memdata = Mem[ALUresult>>2];
	}
	return 0;
}


/* Write Register */
/* 10 Points */
void write_register(unsigned r2,unsigned r3,unsigned memdata,unsigned ALUresult,char RegWrite,char RegDst,char MemtoReg,unsigned *Reg)
{
	if(RegDst == 0 && MemtoReg == 1 && RegWrite == 1)
	{
		Reg[r3] = memdata;//copies memdata to Reg addressed by r3(this was unclear in directions)
	}
	else
		Reg[r2] = ALUresult;//copies ALUresult to Reg addressed by r2(this was unclear in directions)
}

/* PC update */
/* 10 Points */
void PC_update(unsigned jsec,unsigned extended_value,char Branch,char Jump,char Zero,unsigned *PC)
{
	*PC = *PC + 4;//update the PC
	if(Branch == 1 && Zero == 1)
		*PC += extended_value<<2;//shift and add extended_value to PC
	if(Jump == 1)
		*PC = (jsec<<2) | (*PC & 0xF0000000);//shift jsec over 2 then or it by the first 4 bits of the PC
}
