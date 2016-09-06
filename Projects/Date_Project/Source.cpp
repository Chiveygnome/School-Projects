//Use this file in unmodified form with the following exceptions:
//If you used class names other than "Date" and "FutureDate", you may modify those
//  lines to reflect your class names
//If you are using an operating system that does not support the system("pause")
//  function, you may remove it or replace it with a cin statement.

//Please note: while testing, you will probably want to comment out portions of this
//  code to make sure your class is working.  However, the submitted classes should
//  run using the entire file (with possible modifications above).

#include <cstdlib>
#include <iostream>
#include"Date.h"
#include"FutureDate.h"
using namespace std;

int main()
{
    int menuChoice; //used for menu
    int TempMonth;
    int TempDay;
    int TempYear;
    cout<<"Creating Date object with default constructor..."<<endl;
    Date date1;
    cout<<"Stream insertion operator..."<<endl;
    cout<<date1<<endl;
    cout<<"\nStream extraction operator..."<<endl;
    cin>>date1;
    cout<<"Stream insertion operator..."<<endl;
    cout<<date1<<endl;
    
    //Ask user to enter data for next 2 objects
    cout<<"\n\nYou will be asked to enter a date to be used for the next 2 object."<<endl;
    cout<<"Try entering months and days outside the valid range."<<endl;
    cout<<"This program does not try to validate leap years."<<endl;
    cout<<"\n  Enter a month: ";
    cin>>TempMonth;
    cout<<"  Enter a day: ";
    cin>>TempDay;
    cout<<"  Enter a year:";
    cin>>TempYear;
    
    cout<<"\nCreating a Date object with the overloaded constructor..."<<endl;
    Date date2(TempMonth, TempDay, TempYear);
    cout<<"Stream insertion operator..."<<endl;
    cout<<date2<<endl;
    
    cout<<"\nCreating a FutureDate object using the overloaded constructor..."<<endl;    
    FutureDate aFutureDate(TempMonth, TempDay, TempYear);
    cout<<"Stream insertion operator..."<<endl;
    cout<<aFutureDate<<endl;
    
    cout<<"\n\nYou will now use the in/decrement operators on the FutureDate"<<endl;
    cout<<"Press (1) to use the increment operator"<<endl;
    cout<<"Press (2) to use the decrement operator"<<endl;
    cout<<"Press (0) to exit"<<endl;
    cin>>menuChoice;
    while(menuChoice)
    {
        switch(menuChoice)
        {
        case 1:
            aFutureDate++;
            cout<<aFutureDate<<endl;
            break;
        case 2: 
            aFutureDate--;
            cout<<aFutureDate<<endl;
        }
        cout<<"Press (1) to use the increment operator"<<endl;
        cout<<"Press (2) to use the decrement operator"<<endl;
        cout<<"Press (0) to exit"<<endl;
        cin>>menuChoice;
    }
    
    system("PAUSE");
    return EXIT_SUCCESS;
}
