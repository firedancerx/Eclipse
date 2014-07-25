package com.example.app4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OtherActivity extends Activity {
	
	TextView textView;
	
	@Override
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_layout);
		
		textView = (TextView) findViewById(R.id.textView1);
		
		Intent intent = getIntent();
		
		StringBuilder str = new StringBuilder("");
		
		if (intent.getBooleanExtra("Pepperoni", false)) {
			str.append("Pepperoni" + " ");
		}
		
		if (intent.getBooleanExtra("Extra cheese", false)) {
			str.append("Extra Cheese");
		}
		
		if (str.length() == 0) {
			str.append("Plain");
		}
		
		textView.setText(str);
	}

}
