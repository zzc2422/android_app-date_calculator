package com.zzc2422.date_calculator.date;

import com.zzc2422.date_calculator.MonthDaysAmountCalculator;
import com.zzc2422.date_calculator.exception.DateIllegalException;

public class Date implements Comparable<Date>{

	public final static int
			MAX_YEAR=3199,			// 最大允许年
			MIN_YEAR=1583;			// 最小允许年

	// 日期年月日
	public final int YEAR,MONTH,DAY;

	// 构造方法：用年月日连写组成的整数构造。
	public Date(int date) throws DateIllegalException{
		this(date/10000,date/100%100,date%100);
	}

	// 构造方法：分别设置年月日
	public Date(int year,int month,int day) throws DateIllegalException{
		int code=Checker.isDateLegal(year,month,day);
		if(code!=DateIllegalException.LEGAL){
			throw new DateIllegalException(code);
		}
		YEAR=year;
		MONTH=month;
		DAY=day;
	}

	// 测试：返回给定日期的下一日
	public Date getNextDate() throws DateIllegalException{
		if(DAY<MonthDaysAmountCalculator.getMonthDays(YEAR,MONTH)){
			return new Date(YEAR,MONTH,DAY+1);
		}
		else if(MONTH<12){
			return new Date(YEAR,MONTH+1,1);
		}
		else{
			return new Date(YEAR+1,1,1);
		}
	}

	// 将此日期与otherDate比较，如果此日期更早，返回-1，如果此日期更晚，返回1，
	// 如果两日期相同，返回0。
	@Override
	public int compareTo(Date otherDate){
		if(YEAR>otherDate.YEAR){
			return 1;
		}
		else if(YEAR<otherDate.YEAR){
			return -1;
		}
		else if(MONTH>otherDate.MONTH){
			return 1;
		}
		else if(MONTH<otherDate.MONTH){
			return -1;
		}
		else if(DAY>otherDate.DAY){
			return 1;
		}
		else if(DAY<otherDate.DAY){
			return -1;
		}
		else{
			return 0;
		}
	}

	// 将日期以年月日字符串形式返回
	@Override
	public String toString(){
		return ""+YEAR+"年"+MONTH+"月"+DAY+"日";
	}

	// 判断给定年份是否过早
	private static boolean isYearTooEarly(int year){
		return year<MIN_YEAR;
	}

	// 判断给定年份是否过晚
	private static boolean isYearTooLate(int year){
		return year>MAX_YEAR;
	}

	private static class Checker{

		// 判断给定年月日是否合法，返回DateIllegalException类正确或错误码。
		private static int isDateLegal(int year,int month,int day){
			if(isYearTooEarly(year)){
				return DateIllegalException.TOO_EARLY;
			}
			else if(isYearTooLate(year)){
				return DateIllegalException.TOO_LATE;
			}
			else{
				return isMonthDayLegal(year,month,day);
			}
		}

		// 判断月日是否合法，返回正确或错误码。
		private static int isMonthDayLegal(int year,int month,int day){
			int monthDaysAmount
					=MonthDaysAmountCalculator.getMonthDays(year,month);
			if(monthDaysAmount==MonthDaysAmountCalculator.MONTH_ILLEGAL){
				return DateIllegalException.MONTH_ILLEGAL;
			}
			else if(day<=monthDaysAmount&&day>0){
				return DateIllegalException.LEGAL;
			}
			else{
				return DateIllegalException.DAY_ILLEGAL;
			}
		}
	}
}