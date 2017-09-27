package com.zzc2422.date_calculator.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.zzc2422.date_calculator.R;

public class MainActivity extends Activity implements DisplayableForCalculator{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Calculator(this);
	}

	// 根据ID获取控件
	@Override
	public View getView(int id){
		return findViewById(id);
	}
}