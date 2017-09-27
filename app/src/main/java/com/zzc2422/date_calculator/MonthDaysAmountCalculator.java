package com.zzc2422.date_calculator;

public class MonthDaysAmountCalculator{

	// 月份不合法错误码
	public final static int MONTH_ILLEGAL=-1;

	// 获取给定年月的天数。如果月份不合法，返回MONTH_ILLEGAL错误码。
	public static int getMonthDays(int year,int month){
		switch(month){
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				return getFebruaryDays(year);
			default:
				return MONTH_ILLEGAL;
		}
	}

	// 获取给定年份2月的天数。
	private static int getFebruaryDays(int year){
		if(isYearLeap(year)){
			return 29;
		}
		else{
			return 28;
		}
	}

	// 判断给定年份是否是闰年。
	private static boolean isYearLeap(int year){
		// 闰年的充要条件是：年能被400整除，或能被4整除但不能被100整除。
		return year%4==0&&(year%100!=0||year%400==0);
	}
}