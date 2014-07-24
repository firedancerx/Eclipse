package com.allmycode.samples;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MyActivity extends Activity {
    TextView textView1;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Account account1 = new Account(
                "Burd", "222 Cyberspace Lane", 24.02);
        
        textView1 = 
            ((TextView) findViewById(R.id.textView1));
        textView1.setText(account1.infoString());
    }
}
