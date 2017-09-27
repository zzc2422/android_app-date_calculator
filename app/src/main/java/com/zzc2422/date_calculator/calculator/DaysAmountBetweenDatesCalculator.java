package com.zzc2422.date_calculator.calculator;

import com.zzc2422.date_calculator.MonthDaysAmountCalculator;
import com.zzc2422.date_calculator.date.Date;
import com.zzc2422.date_calculator.exception.Date2BeforeDate1Exception;
import com.zzc2422.date_calculator.exception.DateIllegalException;

public class DaysAmountBetweenDatesCalculator{

	// 计算两给定日期之间的天数（需要判断日期2是否在日期1之前），返回天数。
	public static int getDaysAmountBetweenDates(Date date1,Date date2)
			throws Date2BeforeDate1Exception{
		if(date1.compareTo(date2)>0){
			throw new Date2BeforeDate1Exception();
		}
		return getDaysAmountBetweenDates
				(date1.YEAR,date1.MONTH,date1.DAY,
						date2.YEAR,date2.MONTH,date2.DAY);
	}

	// 计算两给定日期之间的天数（不做任何检查）
	private static int getDaysAmountBetweenDates
	(int year1,int month1,int day1,int year2,int month2,int day2){
		int tempYear,sameDateDaysAmount,oneYearDaysAmount;
		if(month1<=month2){
			tempYear=year2;
		}
		else{
			tempYear=year2-1;
		}
		sameDateDaysAmount=DaysAmountBetweenSameDateCalculator
				.getDaysBetweenSameDateInYears(year1,tempYear,month1);
		oneYearDaysAmount=getDaysAmountBetweenMonthsNoMoreThanOneYear
				(tempYear,month1,month2)+day2-day1;
		return sameDateDaysAmount+oneYearDaysAmount;
	}

	// 用逐月推算法计算给定两月份之间的天数（相差不超过一年，如果相同返回0）。
	// 由于结果与日无关，参数不包含日。
	private static int getDaysAmountBetweenMonthsNoMoreThanOneYear
	(int year,int month,int nextMonth){
		int daysAmount=0;
		while(month!=nextMonth){
			daysAmount+=MonthDaysAmountCalculator.getMonthDays(year,month);
			if(month==12){
				year++;
				month=1;
			}
			else{
				month++;
			}
		}
		return daysAmount;
	}
}