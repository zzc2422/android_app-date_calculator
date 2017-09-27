package com.zzc2422.date_calculator.exception;

// 输入日期不合法异常
public class DateIllegalException extends Exception{

	// 全部正确、错误码
	public final static int
			LEGAL=0,				// 合法
			TOO_EARLY=1,			// 年超出范围（过早）
			TOO_LATE=2,				// 年超出范围（过晚）
			MONTH_ILLEGAL=3,		// 月错误
			DAY_ILLEGAL=4;			// 日错误

	// 本异常错误码
	public final int EXCEPTION_CODE;

	// 给定错误码构造方法
	public DateIllegalException(int code){
		EXCEPTION_CODE=code;
	}
}