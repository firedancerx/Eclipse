package com.allmycode.intents4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyAlert extends Activity implements OnClickListener {
    Button yesButton, noButton;
    
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.alert);
        
        yesButton = (Button) findViewById(R.id.buttonYes);
        yesButton.setOnClickListener(this);
        noButton = (Button) findViewById(R.id.buttonNo);
        noButton.setOnClickListener(this);
        
    }
    
    @Override
    public void onClick(View view) {
      Intent intent = new Intent();

      intent.putExtra
        ("yesReally", (view == yesButton?true:false)); 
      setResult(RESULT_OK, intent);
      finish();
    }

}
