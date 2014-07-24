package com.allmycode.examples.ndk;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MyActivity extends Activity {
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
   }
    
   public native String getString();

   static {
      System.loadLibrary("my-jni-app");
   }

   public void onButtonClick(View v) {

      Editable name = 
        ((EditText) findViewById(R.id.editText1)).getText();
      Toast.makeText(getApplication(), getString() + name,
         Toast.LENGTH_LONG).show();
   }
}
