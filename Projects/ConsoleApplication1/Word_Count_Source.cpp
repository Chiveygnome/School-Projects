#include <iostream>
#include <iomanip>
#include <cstring>
#include <string>
using namespace std;

void averageWords(float trueCharCount ,float wordCount)
{
	float charAverage = 0;
	charAverage = trueCharCount / wordCount;
	cout << "Your average character per word was : " << setprecision(3) << charAverage <<endl;
	system("PAUSE");
	return;
}
int CountWords(int length, char* charSent)
{
	float trueCharCount = 0;
	char charSent1 = ' ';
	int spaceCount = 0;
	int Count = 0;
	int charCount = 0;
	float wordCount = 0;
	
	

	
	while(Count < length)
	{
		if(charSent[Count] == ' ' && charSent[Count+1] != ' ')
		{
			wordCount++;
		}
		if(charSent[Count] == ' ')
		{
			spaceCount++;
		}
		if(charSent[Count] != ' ' || charSent[Count] != '!' || charSent[Count] != '.'|| charSent[Count] != '?' || charSent[Count] != ',' || charSent[Count] != ';' || charSent[Count] != ':')
		{
			charCount++;
		}
		Count++;
	}
	wordCount++; //To account for the fact that the first word has no spaces that it can properly count.
	trueCharCount = (charCount - spaceCount);
	cout << "Your character count is " << trueCharCount << endl;
	cout << "Your word count is " << wordCount <<endl;
	averageWords(trueCharCount,wordCount);
	return 0;
}

int main()
{
	string sentence = "";
	cout << "Please enter your sentence that you want counted." << endl;
	getline(cin, sentence);
	int length = sentence.length();

	char *charSent = new char[sentence.length() + 1];
	strncpy(charSent, sentence.c_str(), sentence.length());

	CountWords(length, charSent);
	
}