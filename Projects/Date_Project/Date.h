#ifndef DATE_H
#define DATE_H
#include <ostream>
#include <istream>
#include <iostream>
using namespace std;

class Date
{
private:
	int day,
		month,
		year;

public:
	Date()
		{day = 1;
		month = 1;
		year = 2000;}
	Date(int x, int y, int z)
		{day = y;
		month = x;
		year =z;}
	void getDay(int x);
	void getMonth(int y);
	void getYear(int z);
	int setDay(){return day;}
	int setMonth(){return month;}
	int setYear(){return year;}
	friend ostream &operator << (ostream &, const Date &);
	friend istream & operator >> (istream &, Date &);
	Date Tdate();
	Date FutureDate();
	Date temp(int day, int month, int year);
	Date operator++();
	Date operator++(int);
	Date operator--();
	Date operator--(int);
};

	int preIncrement(int month, int day, int year)
	{
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 && day == 32)
		{
			day = 1;
			++month;
			if(month = 12 && day == 32)
				++year;
		}
		else if(month == 4 || month == 6 || month == 9 || month == 11 && day == 31)
		{
			day = 1;
			++month;
		}
		else if(month == 2 && day == 29)
		{ 
			day = 1;
			++month;
		}
		return day, month, year;
	}
	int postIncrement(int month, int day, int year)
	{
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 && day == 32)
		{
			day = 1;
			month+=1;
			if(month = 12 && day == 32)
				{
					year+=1;
					month = 1;
				}
		}
		else if(month == 4 || month == 6 || month == 9 || month == 11 && day == 31)
		{
			day = 1;
			month+=1;
		}
		else(month == 2 && day == 29);
		{
			day = 1;
			month+=1;
		}
		return day, month, year;
	}
	int preDecrement(int month, int day, int year)
	{
		
		if(month == 1  || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 && day == 0)
		{
			day = 30;
			--month;
			if(month == 1 && day == 0)
				{
					--year;
					month = 12;
			}
		}
		else if(month == 4 || month == 6 || month == 9 || month == 11 && day == 0)
		{
			day = 31;
			--month;
		}
		else if(month == 3 && day == 0)
		{ 
			day = 1;
			--month;
		}
		return day, month, year;
		}
	int postDecrement(int month, int day, int year)
	{
		if(month == 1  || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 && day == 0)
		{
			day = 30;
			month--;
			if(month == 1 && day == 0)
				{
					year--;
					month = 12;
			}
		}
		else if(month == 4 || month == 6 || month == 9 || month == 11 && day == 0)
		{
			day = 31;
			month--;
		}
		else if(month == 3 && day == 0)
		{ 
			day = 1;
			month--;
		}
		return day, month, year;
		}
	Date Date::operator++()
	{
		++day;
		preIncrement(day,month,year);
		return *this;
	}
	Date Date::operator++(int)
	{
		Date temp(day, month, year);
		day++;
		postIncrement(day, month, year);
		return temp;
	}
	Date Date::operator--()
	{
		--day;
		preDecrement(day, month, year);
		return *this;
	}
	Date Date::operator--(int)
	{
		Date temp(day, month, year);
		day--;
		postDecrement(day, month, year);
		return temp;
	}
	void Date::getDay(int x)
	{day = x;}
	void Date::getMonth(int y)
	{month = y;}
	void Date::getYear(int z)
	{year = z;}
	ostream &operator << (ostream &strm, const Date &obj)
	{
		strm << obj.month << " - " << obj.day << " - " << obj.year;
		return strm;
	}
	istream &operator >> (istream &strm, Date &obj)
	{
		cout << "Month: ";
		strm >> obj.month;

		cout << "Day: ";
		strm >> obj.day;

		cout << "Year: ";
		strm >> obj.year;

		return strm;
}
#endif