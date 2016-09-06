#include <iostream>
#include <cstdlib>
#include <ctime>
#include <iomanip>
#include <cmath>
#include <Windows.h>
using namespace std;

int main()
{
	int howMany;
	int num1;
	int num2;
	int num;
	int num1Range;
	int num2Range;
	int range;
	int answer;
	int genAnswer;
	int count;

	num1Range = 300;
	num2Range = 300;
	range = 100;
	howMany = 0;

	cout << "Please enter the amount of math problems you wish generated." << endl;
	cin >> howMany;



	for (count = 0; count < howMany; count++ )
	{
		srand((unsigned)time(0));

		num1 = 1 + int(num1Range * rand()/RAND_MAX +1.0);
		num2 = 1 + int(num2Range * rand()/RAND_MAX +1.0);
		num = 1 + int(range * rand()/RAND_MAX +1.0);
		
		if (num % 2 == 0)
		{
			genAnswer = num1 - num2;
			cout << setw(5) << num1 << endl;
			cout << "-" << setw(4) << num2 << endl;
			cout << "-------" << endl;
			cin >> answer;
			if (answer = genAnswer)
			{
				cout << "Well done!  The answer is correct!" << endl;
				system("PAUSE");
			}
			else 
			{
				cout << "Good try!" << endl;
				cout << "The correct answer was: " << genAnswer << endl;
				cout << "Your answer was: " << answer << endl;
				system("PAUSE");
			}
		}
		else if (num = 2)
		{
			genAnswer = num1 + num2;
			cout << setw(5) << num1 << endl;
			cout << "+" << setw(4) << num2 << endl;
			cout << "-------" << endl;
			cin >> answer;
			if (answer = genAnswer)
			{
				cout << "Well done!  The answer is correct!" << endl;
				system("PAUSE");
			}
			else 
			{
				cout << "Good try!" << endl;
				cout << "The correct answer was: " << genAnswer << endl;
				cout << "Your answer was: " << answer << endl;
				system("PAUSE");
			}
		}
	}
	return 0;
}