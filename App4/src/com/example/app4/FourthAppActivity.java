package com.example.app4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class FourthAppActivity extends Activity {
	
	CheckBox pepBox, cheeseBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reusable_layout);
		
		pepBox = (CheckBox) findViewById(R.id.checkBox1);
		cheeseBox = (CheckBox) findViewById(R.id.checkBox2);
	}
	
	public void onButton1Click (View view) {
		Intent intent = new Intent(this, OtherActivity.class);
		intent.putExtra("Pepperoni", pepBox.isChecked());
		intent.putExtra("Extra cheese", cheeseBox.isChecked());
		startActivity(intent);
	}
}
