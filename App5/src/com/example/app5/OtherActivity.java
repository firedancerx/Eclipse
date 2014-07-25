package com.example.app5;

import com.example.app5.R;

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
			str.append(getString(R.string.pepperoni) + " ");
		}
		
		if (intent.getBooleanExtra("Extra cheese", false)) {
			str.append(getString(R.string.cheese));
		}
		
		if (str.length() == 0) {
			str.append(getString(R.string.plain));
		}
		
		textView.setText(str);
	}

}
