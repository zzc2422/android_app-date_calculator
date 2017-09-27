package com.zzc2422.date_calculator.ui;

import android.widget.EditText;

import com.zzc2422.date_calculator.date.Date;
import com.zzc2422.date_calculator.exception.DateIllegalException;

class EditTextGroup{

	// 三个输入框
	private final EditText
			EDIT_TEXT_DATE1,EDIT_TEXT_DATE2,EDIT_TEXT_DAYS_AMOUNT;

	// 构造方法：给定三个输入框
	EditTextGroup(EditText editTextDate1,EditText editTextDate2,
				  EditText editTextDaysAmount){
		EDIT_TEXT_DATE1=editTextDate1;
		EDIT_TEXT_DATE2=editTextDate2;
		EDIT_TEXT_DAYS_AMOUNT=editTextDaysAmount;
	}

	// 获取日期1
	Date getDate1() throws DateIllegalException{
		return new Date(getInputInt(EDIT_TEXT_DATE1));
	}

	// 获取日期2
	Date getDate2() throws DateIllegalException{
		return new Date(getInputInt(EDIT_TEXT_DATE2));
	}

	// 获取天数
	int getDaysAmount(){
		return getInputInt(EDIT_TEXT_DAYS_AMOUNT);
	}

	// 获取输入框中输入的整数
	private static int getInputInt(EditText editText){
		String str=editText.getText().toString();
		return Integer.parseInt(str);
	}
}