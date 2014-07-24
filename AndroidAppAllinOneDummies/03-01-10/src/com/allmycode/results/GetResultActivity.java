package com.allmycode.results;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GetResultActivity extends Activity 
                            implements OnClickListener {
  final int MY_REQUEST_CODE = 42;    
  TextView textView;
  Button button;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main); 
    textView = (TextView) findViewById(R.id.textView1);
    button = (Button) findViewById(R.id.button1);
    button.setOnClickListener(this);
  }
   
  @Override
  public void onClick(View v) {
    Intent intent = new Intent();
    intent.setClassName("com.allmycode.results", 
      "com.allmycode.results.GiveResultActivity");
    startActivityForResult(intent, MY_REQUEST_CODE);    
  }  
  
  @Override
  protected void onActivityResult(int requestCode, 
                                  int resultCode, 
                                  Intent intent) {
                                  
    if (requestCode == MY_REQUEST_CODE && 
                              resultCode == RESULT_OK) {
      textView.setText(intent.getStringExtra("text"));
    }
  }
}