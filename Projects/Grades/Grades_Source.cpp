#include <iostream>
#include <string>
#include <cmath>
#include <cstdio>
#include <cstdlib>
using namespace std;

int checkAnswers(int Count, string answer[]);
void inputAnswers(string answer[], int Count);
void displayResults(string correctAnswer[20], string answer[], int correctCount, int wrongCount);

int wrongCount = 0;
int correctCount = 0;
int Count = 0;
string correctAnswer[20] = {"B","D","A","A",
							"C","A","B","A",
							"C","D","B","C",
							"D","A","D","C",
							"C","D","D","A"};
string answer[20]={""};

void inputAnswers(string answer[], int Count)
{
	while (Count < 20)
	{
		cout << "Please enter your answer." << endl;
		cout <<"(Please enter A, B, C or D.)" <<endl;
		cin >> answer[Count];
		if(answer[Count] == "A","a","B","b","C","c","D","d")
		{
			checkAnswers(Count,answer);
			Count++;
		}
		else
		{
			while (answer[Count] != "A","a","B","b","C","c","D","d")
			{
			cout << "Please enter a valid response." << endl;
			}
		}
	}
	return;
}
int checkAnswers(int Count,string answer[])
{
	if(answer[Count] == correctAnswer[Count])
	{
		correctCount++;
	}
	else if(answer[Count] == correctAnswer[Count])
	{
		wrongCount++;
	}
	return 0;
}
void displayResults(string correctAnswer[20], string answer[],int correctCount, int wrongCount)
{
	float correctPercentage = 0.0;
	cout << "The correct answers are below: " << endl;
	for(int i = 0; i < 19; i++)
	{
		cout << correctAnswer[i];
	}
	cout << endl;
	cout << "Your answers are below: " << endl;
	for(int j = 0; j < 19; j++)
	{
		cout << answer[j];
	}
	cout << endl;
	system("PAUSE");
	cout << "You got " << correctCount << " correct." << endl;
	cout << "You got " << wrongCount << " wrong." << endl;
	correctPercentage = (correctCount / 20) * 100;
	cout << "Your grade Percentage is : " << correctPercentage << endl;
	system("PAUSE");
	return;
}

int main()
{
	inputAnswers(answer,Count);
	displayResults(correctAnswer,answer,correctCount,wrongCount);
}