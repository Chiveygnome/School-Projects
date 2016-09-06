#ifndef FUTUREDATE_H
#define FUTUREDATE_H
#include "Date.h"
#include <ctime>
using namespace std;

class FutureDate : public Date
{
private:
	int month,
		day,
		year;
	int fday,
		fmonth,
		fyear;
	int Tday,
		Tmonth,
		Tyear;
public:
	FutureDate(int x, int y, int z)
	{
	x = fmonth;
	y = fday;
	z = fyear;
	time_t t = time(0);   // get time now
    struct tm * now = localtime( & t );
    Tyear = (now->tm_year + 1900); 
	Tmonth = (now->tm_mon + 1); 
    Tday = (now->tm_mday);
	if(fmonth <= Tmonth && fday <= Tday && fyear <= Tyear)
	{
		fmonth = month;
		fday = day;
		fyear = year;
	}
		

	}

};


#endif