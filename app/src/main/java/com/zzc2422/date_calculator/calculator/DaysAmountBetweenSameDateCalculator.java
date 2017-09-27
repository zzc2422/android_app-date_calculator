package com.zzc2422.date_calculator.calculator;

class DaysAmountBetweenSameDateCalculator{

	// 获取给定两个年份的同一给定日期之间的天数。由于结果与月有关而与日无关，
	// 因此参数只有月没有日。不接受2月29日的运算。
	static int getDaysBetweenSameDateInYears(int year1,int year2,int month){
		int baseAmount,leapAmount;
		baseAmount=365*(year2-year1);
		leapAmount=getLeapAmountBetweenSameDateInYears(year1,year2,month);
		return baseAmount+leapAmount;
	}

	// 获取给定两个年份的同一给定日期之间的2月29日天数。由于结果与月有关而与日无关，
	// 因此参数只有月没有日。不接受2月29日的运算。
	private static int getLeapAmountBetweenSameDateInYears(int year1,int year2,
														   int month){
		if(month<3){
			year1--;
			year2--;
		}
		return getLeapAmountBetweenMarchInYears(year1,year2);
	}

	// 获取给定两个年份的3月1日之间的2月29日的个数。
	private static int getLeapAmountBetweenMarchInYears(int year1,int year2){
		int mod400Amount,mod100Amount,mod4Amount,mod4NonleapAmount;
		mod4Amount=year2/4-year1/4;
		mod100Amount=year2/100-year1/100;
		mod400Amount=year2/400-year1/400;
		mod4NonleapAmount=mod100Amount-mod400Amount;
		return mod4Amount-mod4NonleapAmount;
	}
}