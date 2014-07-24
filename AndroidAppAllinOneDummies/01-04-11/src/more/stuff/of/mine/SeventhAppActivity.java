package more.stuff.of.mine;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;

public class SeventhAppActivity extends Activity 
                        implements OnClickListener {
  CheckBox pepBox, cheeseBox;
  WebView webView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    ((Button) findViewById(R.id.button1))
      .setOnClickListener(this);

    pepBox = (CheckBox) findViewById(R.id.checkBox1);
    cheeseBox = (CheckBox) findViewById(R.id.checkBox2);
    webView = (WebView) findViewById(R.id.webView1);
  }

  public void onClick(View view) {
    StringBuilder str = new StringBuilder("");
    if (pepBox.isChecked()) {
      str.append("Pepperoni");
    }
    if (cheeseBox.isChecked()) {
      str.append("\"Extra cheese\"");
    }
    if (str.length() == 23) {
      str.insert(9, '+');
    }
    if (str.length() == 0) {
      str.append("Plain");
    }
    Log.i("SDKJF", "clicked");
    webView.loadUrl
      ("http://www.google.com/search?q="+str.toString());
  }
}
