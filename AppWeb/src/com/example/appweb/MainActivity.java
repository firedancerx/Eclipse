package com.example.appweb;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.webkit.*;
import android.widget.*;
import com.example.appweb.R;

public class MainActivity extends Activity implements android.view.View.OnClickListener {
	
	CheckBox pepBox, cheeseBox;
	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		((Button) findViewById(R.id.button1)).setOnClickListener(this);
		pepBox = (CheckBox) findViewById(R.id.checkBox1);
		cheeseBox = (CheckBox) findViewById(R.id.checkBox2);
		webView = (WebView) findViewById(R.id.webView1);
	}
	
	public void onClick(View view) {
		StringBuilder str = new StringBuilder("");
		
		if (pepBox.isChecked()) {
			str.append(getString(R.string.web_pepperoni));			
		}
		
		if (cheeseBox.isChecked()) {
			str.append(getString(R.string.web_cheese));
		}
		
		if (str.length() == 0) {
			str.append(getString(R.string.web_plain));
		}
		
		webView.loadUrl(str.toString());
	}
}
