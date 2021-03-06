package com.example.app2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;


public class MainActivity extends Activity {
	
	TextView textView;
	CheckBox pepBox, cheeseBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pepBox = (CheckBox) findViewById(R.id.checkBox1);
		cheeseBox = (CheckBox) findViewById(R.id.checkBox2);
		textView = (TextView) findViewById(R.id.textView2);
	}
	
	public void onButton1Click(View view) {
		StringBuilder str = new StringBuilder("");
		if (pepBox.isChecked()) {
			str.append("Pepperoni" + " ");
		}
		if (cheeseBox.isChecked()) {
			str.append("Extra cheese");
		}
		if (str.length() == 0) {
			str.append("Plain");
		}
		textView.setText(str);
	}
}
