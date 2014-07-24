package com.allmycode.intents4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyMatchDialog extends Activity implements OnClickListener {
    Button dismissButton;
    TextView textView;
    
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.match);
        
        dismissButton = (Button) findViewById(R.id.buttonDismiss);
        dismissButton.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.textViewB);
        Intent caller = getIntent();
        textView.setText(caller.getStringExtra("anIntent") + "\n\n" +  caller.getStringExtra("aFilter"));
    }
    
    @Override
    public void onClick(View view) {
      finish();
    }

}
