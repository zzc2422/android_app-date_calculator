package com.zzc2422.date_calculator.calculator;

import com.zzc2422.date_calculator.MonthDaysAmountCalculator;
import com.zzc2422.date_calculator.date.Date;
import com.zzc2422.date_calculator.exception.DateIllegalException;
import com.zzc2422.date_calculator.exception.NegativeDaysAmountException;

public class DateDaysFromDateCalculator{

	// 计算并返回给定日期之后若干天的日期（需要检查天数是否为负）
	public static Date getDateDaysAfterDate(Date date,int daysAmount)
			throws NegativeDaysAmountException,DateIllegalException{
		if(daysAmount<0){
			throw new NegativeDaysAmountException();
		}
		else{
			return getNormalDate(date.YEAR,date.MONTH,date.DAY+daysAmount);
		}
	}

	// 计算并返回给定日期之前若干天的日期（需要检查天数是否为负）
	public static Date getDateDaysBeforeDate(Date date,int daysAmount)
			throws NegativeDaysAmountException,DateIllegalException{
		if(daysAmount<0){
			throw new NegativeDaysAmountException();
		}
		else{
			return getNormalDate(date.YEAR,date.MONTH,date.DAY-daysAmount);
		}
	}

	// 计算并返回给定的日超出范围日期的正确形式（允许给定日期超出范围，
	// 此时假设范围外日期规则与范围内相同，但如果结果超出范围则抛出异常）
	private static Date getNormalDate(int year,int month,int day)
			throws DateIllegalException{
		while(true){
			if(day>0){
				if(day<=197){
					return getNormalDateFromUpByMonth(year,month,day);
				}
			}
			else{
				if(day>=-168){
					return getNormalDateFromDownByMonth(year,month,day);
				}
			}
			// tempYear可能超出范围，但并非最终结果，可假定在范围外日期规则与范围内相同。
			int tempYear=(365+168+day)/365+year-1;
			// days为负数时，此语句本应为将+=改为-=并将year与tempYear交换，
			// 但函数设计使得语句等价。
			day-=DaysAmountBetweenSameDateCalculator
					.getDaysBetweenSameDateInYears(year,tempYear,month);
			year=tempYear;
		}
	}

	// 用逐月推算法计算并返回给定的日过大日期的正确形式（允许给定日期超出范围，
	// 此时假设范围外日期规则与范围内相同，但如果结果超出范围则抛出异常）
	private static Date getNormalDateFromUpByMonth(int year,int month,int day)
			throws DateIllegalException{
		while(true){
			int thisMonthDaysAmount=MonthDaysAmountCalculator
					.getMonthDays(year,month);
			if(day<=thisMonthDaysAmount){
				return new Date(year,month,day);
			}
			else if(month<12){
				month++;
			}
			else if(year==Date.MAX_YEAR){
				throw new DateIllegalException(DateIllegalException.TOO_LATE);
			}
			else{
				year++;
				month=1;
			}
			day-=thisMonthDaysAmount;
		}
	}

	// 用逐月推算法计算并返回给定的日小于0日期的正确形式（结果超出范围则抛出异常）
	private static Date getNormalDateFromDownByMonth(int year,int month,int day)
			throws DateIllegalException{
		while(day<=0){
			if(month>1){
				month--;
			}
			else if(year==Date.MIN_YEAR){
				throw new DateIllegalException(DateIllegalException.TOO_EARLY);
			}
			else{
				year--;
				month=12;
			}
			day+=MonthDaysAmountCalculator.getMonthDays(year,month);
		}
		return new Date(year,month,day);
	}

	/*
	public static class Test implements Runnable{

		private int currentDateNumber=0;

		private final static int
				DATE_AMOUNT=590597,	// 日期总数
				THREAD_AMOUNT=8,
				DATE_PER_TIME=100;

		// 日期表
		private final static Date[] DATE_LIST=new Date[DATE_AMOUNT];

		public static void main(String[] args){
			Date date;
			try{
				date=new Date(1583,1,1);
				Test test=new Test();
				DATE_LIST[0]=date;
				for(int i=1;i<DATE_AMOUNT;i++){
					date=date.getNextDate();
					DATE_LIST[i]=date;
				}
				for(int i=0;i<THREAD_AMOUNT;i++){
					(new Thread(test)).start();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

		@Override
		public void run(){
			int START,END;
			while(true){
				synchronized(this){
					START=currentDateNumber;
					if(START>=DATE_AMOUNT){
						System.out.println("结束！");
						return;
					}
					else{
						currentDateNumber=START+DATE_PER_TIME;
					}
				}
				END=START+DATE_PER_TIME;
				if(END>=DATE_AMOUNT){
					END=DATE_AMOUNT;
				}
				test(START,END);
				System.out.println(START+"\t"+"END");
			}
		}

		// 测试日期序号在start与end之间（包括前者不包括后者）
		private void test(final int START,final int END){
			try{
				Date startDate=DATE_LIST[START];
				for(int i=START;i<END;i++){
					for(int daysAmount=0;daysAmount+i<DATE_AMOUNT;daysAmount++){
						Date endDate=DATE_LIST[i+daysAmount];
						Date testEndDate=getDateDaysAfterDate
								(startDate,daysAmount);
						if(endDate.compareTo(testEndDate)!=0){
							System.out.println
									("出错！"+startDate+"之后"+daysAmount);
						}
						Date testStartDate=getDateDaysBeforeDate
								(endDate,daysAmount);
						if(startDate.compareTo(testStartDate)!=0){
							System.out.println
									("出错！"+endDate+"之前"+daysAmount);
						}
						int testDaysAmount=DaysAmountBetweenDatesCalculator
								.getDaysAmountBetweenDates(startDate,endDate);
						if(testDaysAmount!=daysAmount){
							System.out.println("出错！"+startDate+"与"+endDate);
						}
					}
					if(i+1<END){
						startDate=startDate.getNextDate();
					}
				}
			}
			catch(Exception e){}
		}
	}
	*/
}