package com.zzc2422.date_calculator.ui;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zzc2422.date_calculator.R;
import com.zzc2422.date_calculator.calculator.DateDaysFromDateCalculator;
import com.zzc2422.date_calculator.calculator.DaysAmountBetweenDatesCalculator;
import com.zzc2422.date_calculator.date.Date;
import com.zzc2422.date_calculator.exception.Date2BeforeDate1Exception;
import com.zzc2422.date_calculator.exception.DateIllegalException;

import static android.graphics.Color.GREEN;

class Calculator implements View.OnClickListener{

	private final static String
			DATE2_BEFORE_DATE1="错误：日期2在日期1之前！",
			DAYS_AMOUNT_NOT_POSITIVE_INTEGER="错误：天数不是非负整数！";

	// 输入框
	private final EditTextGroup EDIT_TEXT_GROUP;

	// 结果显示区
	private final TextView TEXT_VIEW_RESULT;

	// 构造方法：构造显示在给定载体上的界面
	Calculator(DisplayableForCalculator d){
		EDIT_TEXT_GROUP=new EditTextGroup
				((EditText)(d.getView(R.id.editTextDate1)),
						(EditText)(d.getView(R.id.editTextDate2)),
						(EditText)(d.getView(R.id.editTextDaysAmount)));
		TEXT_VIEW_RESULT=(TextView)(d.getView(R.id.textViewResult));
		initButtonGroup(d);
	}

	// 相应按钮事件
	@Override
	public void onClick(View view){
		switch(view.getId()){
			case R.id.buttonCalculateDaysBetweenDates:
				calculateDaysAmountBetweenDates();
				break;
			case R.id.buttonCalculateDateDaysAfterDate1:
				calculateDateDaysAfterDate1();
				break;
			case R.id.buttonCalculateDateDaysBeforeDate2:
				calculateDateDaysBeforeDate2();
				break;
		}
	}

	// 初始化按钮
	private void initButtonGroup(DisplayableForCalculator d){
		d.getView(R.id.buttonCalculateDaysBetweenDates)
				.setOnClickListener(this);
		d.getView(R.id.buttonCalculateDateDaysAfterDate1)
				.setOnClickListener(this);
		d.getView(R.id.buttonCalculateDateDaysBeforeDate2)
				.setOnClickListener(this);
	}

	// 计算两给定日期之间的天数
	private void calculateDaysAmountBetweenDates(){
		Date date1,date2;
		int daysAmount;
		try{
			date1=EDIT_TEXT_GROUP.getDate1();
		}
		catch(DateIllegalException e){
			showDateIllegalExceptionString("日期1",e);
			return;
		}
		catch(Exception e){
			showDateIllegalExceptionString("日期1",null);
			return;
		}
		try{
			date2=EDIT_TEXT_GROUP.getDate2();
		}
		catch(DateIllegalException e){
			showDateIllegalExceptionString("日期2",e);
			return;
		}
		catch(Exception e){
			showDateIllegalExceptionString("日期2",null);
			return;
		}
		try{
			daysAmount=DaysAmountBetweenDatesCalculator
					.getDaysAmountBetweenDates(date1,date2);
		}
		catch(Date2BeforeDate1Exception e){
			showDate2BeforeDate1String();
			return;
		}
		showDaysAmountBetweenDatesString(date1,date2,daysAmount);
	}

	// 计算日期1之后若干天的日期
	private void calculateDateDaysAfterDate1(){
		Date date1,result;
		int daysAmount;
		try{
			date1=EDIT_TEXT_GROUP.getDate1();
		}
		catch(DateIllegalException e){
			showDateIllegalExceptionString("日期1",e);
			return;
		}
		catch(Exception e){
			showDateIllegalExceptionString("日期1",null);
			return;
		}
		try{
			daysAmount=EDIT_TEXT_GROUP.getDaysAmount();
			result=DateDaysFromDateCalculator
					.getDateDaysAfterDate(date1,daysAmount);
		}
		catch(DateIllegalException e){
			showDateIllegalExceptionString("计算结果",e);
			return;
		}
		catch(Exception e){
			showDaysAmountNotPositiveIntegerString();
			return;
		}
		showDateDaysAfterDate1String(date1,daysAmount,result);
	}

	// 计算日期2之前若干天的日期
	private void calculateDateDaysBeforeDate2(){
		Date date2,result;
		int daysAmount;
		try{
			date2=EDIT_TEXT_GROUP.getDate2();
		}
		catch(DateIllegalException e){
			showDateIllegalExceptionString("日期2",e);
			return;
		}
		catch(Exception e){
			showDateIllegalExceptionString("日期2",null);
			return;
		}
		try{
			daysAmount=EDIT_TEXT_GROUP.getDaysAmount();
			result=DateDaysFromDateCalculator
					.getDateDaysBeforeDate(date2,daysAmount);
		}
		catch(DateIllegalException e){
			showDateIllegalExceptionString("计算结果",e);
			return;
		}
		catch(Exception e){
			showDaysAmountNotPositiveIntegerString();
			return;
		}
		showDateDaysBeforeDate2String(date2,daysAmount,result);
	}

	// 显示两日期之间天数结果
	private void showDaysAmountBetweenDatesString(Date date1,Date date2,
												 int daysAmount){
		String text=StringDealer
				.getDaysAmountBetweenDatesString(date1,date2,daysAmount);
		TEXT_VIEW_RESULT.setTextColor(GREEN);
		TEXT_VIEW_RESULT.setText(text);
	}

	// 显示日期1之后若干天的日期结果
	private void showDateDaysAfterDate1String(Date date1,int daysAmount,
											  Date result){
		String text=StringDealer.getDateDaysFromDate1String
						(date1,StringDealer.AFTER,daysAmount,result);
		TEXT_VIEW_RESULT.setTextColor(GREEN);
		TEXT_VIEW_RESULT.setText(text);
	}

	// 显示日期2之前若干天的日期结果
	private void showDateDaysBeforeDate2String(Date date2,int daysAmount,
											   Date result){
		String text=StringDealer.getDateDaysFromDate1String
				(date2,StringDealer.BEFORE,daysAmount,result);
		TEXT_VIEW_RESULT.setTextColor(GREEN);
		TEXT_VIEW_RESULT.setText(text);
	}

	// 显示日期输入错误信息（如果e为null，为日期发生其它错误，不再具体给出错误原因）
	private void showDateIllegalExceptionString(String date,
												DateIllegalException e){
		String text=StringDealer.getDateIllegalExceptionString(date,e);
		TEXT_VIEW_RESULT.setTextColor(Color.RED);
		TEXT_VIEW_RESULT.setText(text);
	}

	// 显示日期2在日期1之前错误信息
	private void showDate2BeforeDate1String(){
		TEXT_VIEW_RESULT.setTextColor(Color.RED);
		TEXT_VIEW_RESULT.setText(DATE2_BEFORE_DATE1);
	}

	// 显示天数不是数字错误信息
	private void showDaysAmountNotPositiveIntegerString(){
		TEXT_VIEW_RESULT.setTextColor(Color.RED);
		TEXT_VIEW_RESULT.setText(DAYS_AMOUNT_NOT_POSITIVE_INTEGER);
	}

	// 字符串处理
	private static class StringDealer{

		private final static boolean
				AFTER=false,	// 之后
				BEFORE=true;	// 之前

		// 获取两日期之间天数结果字符串
		private static String getDaysAmountBetweenDatesString
		(Date date1,Date date2,int daysAmount){
			return "从"+date1+"到"+date2+"共有"+daysAmount+"天。";
		}

		// 获取日期之前或之后（由daysAmount正负决定）若干天的日期结果字符串
		private static String getDateDaysFromDate1String
		(Date fromDate,boolean afterOrBefore,int daysAmount,Date result){
			String aob;
			if(afterOrBefore==AFTER){
				aob="后";
			}
			else{
				aob="前";
			}
			return fromDate+"之"+aob+"第"+daysAmount+"天是"+result+"。";
		}

		// 获取日期错误信息字符串（如果e为null，为日期发生其它错误，不再具体给出错误原因）
		private static String getDateIllegalExceptionString
		(String dateName,DateIllegalException e){
			return "错误："+dateName+getDateExceptionText(e)+"！";
		}

		// 根据日期错误类型返回错误说明
		// （如果e为null，为日期发生其它错误，不再具体给出错误原因）
		private static String getDateExceptionText(DateIllegalException e){
			if(e==null){
				return "不是合法日期";
			}
			switch(e.EXCEPTION_CODE){
				case DateIllegalException.TOO_EARLY:
					return "在1583年1月1日之前";
				case DateIllegalException.TOO_LATE:
					return "在3199年12月31日之后";
				case DateIllegalException.MONTH_ILLEGAL:
					return "月不合法";
				case DateIllegalException.DAY_ILLEGAL:
					return "日不合法";
				default:
					return null;
			}
		}
	}
}