package com.allmycode.samples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyActivity extends Activity 
                        implements OnClickListener {

    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        button = ((Button) findViewById(R.id.button1));

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        button.setBackgroundColor
                         (android.graphics.Color.GRAY);
    }
}
