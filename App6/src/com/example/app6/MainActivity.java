package com.example.app6;

import com.example.app6.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends Activity implements android.widget.CompoundButton.OnCheckedChangeListener{
	
	TextView textView;
	CheckBox pepBox, cheeseBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		((CheckBox) findViewById(R.id.checkBox1)).setOnCheckedChangeListener(this);
		((CheckBox) findViewById(R.id.checkBox2)).setOnCheckedChangeListener(this);
		textView = (TextView) findViewById(R.id.textView2);
	}
	
	@Override
	public void onCheckedChanged(CompoundButton box, boolean isChecked) {
		
		StringBuilder str = new StringBuilder(textView.getText());
		CharSequence boxText = box.getText();
		if (isChecked) {
			str.append(" " + boxText);
		}
		else {
			int start = str.indexOf(boxText.toString());
			int length = boxText.length();
			str.replace(start, start + length, "");
		}
		textView.setText(str);
		
	}
}
