package more.stuff.of.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class SecondAppActivity extends Activity 
                        implements OnClickListener {
    TextView textView;
    CheckBox pepBox, cheeseBox;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        pepBox = 
            (CheckBox) findViewById(R.id.checkBox1);
        cheeseBox = 
            (CheckBox) findViewById(R.id.checkBox2);
        textView = 
            (TextView) findViewById(R.id.textView1);
        
        ((Button) findViewById(R.id.button1))
                          .setOnClickListener(this);
    }

    public void onClick(View view) {
        StringBuilder str = new StringBuilder("");
        if (pepBox.isChecked()) {
            str.append("Pepperoni" + " ");
        }
        if (cheeseBox.isChecked()) {
            str.append("Extra cheese");
        } 
        if (str.length() == 0) {
            str.append("Plain");
        }
        textView.setText(str);
    }
}
