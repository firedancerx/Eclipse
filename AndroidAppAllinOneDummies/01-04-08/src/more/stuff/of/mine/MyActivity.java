package more.stuff.of.mine;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.
                             OnCheckedChangeListener;
import android.widget.TextView;

public class MyActivity extends Activity 
        implements OnCheckedChangeListener {

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ((CheckBox) findViewById(R.id.checkBox1))
                .setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.checkBox2))
                .setOnCheckedChangeListener(this);
        textView = 
                (TextView) findViewById(R.id.textView1);
    }

    @Override
    public void onCheckedChanged(CompoundButton box,
                                 boolean isChecked) {
        StringBuilder str = 
                new StringBuilder(textView.getText());
        CharSequence boxText = box.getText();
        if (isChecked) {
            str.append(" " + boxText);
        } else {
            int start = str.indexOf(boxText.toString());
            int length = boxText.length();
            str.replace(start, start + length, "");
        }        
        textView.setText(str.toString().trim());
    }
}
