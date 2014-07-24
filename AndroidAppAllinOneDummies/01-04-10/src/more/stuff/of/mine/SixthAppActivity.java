package more.stuff.of.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

public class SixthAppActivity extends Activity 
        implements OnClickListener {
    CheckBox pepBox, cheeseBox;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ((Button) findViewById(R.id.button1))
            .setOnClickListener(this);

        pepBox = 
            (CheckBox) findViewById(R.id.checkBox1);
        cheeseBox = 
            (CheckBox) findViewById(R.id.checkBox2);
        imageView = 
            (ImageView) findViewById(R.id.imageView1);
    }

    public void onClick(View view) {
        int level = 0;
        
        if (pepBox.isChecked()) {
            level += 1;
        }
        if (cheeseBox.isChecked()) {
            level += 2;
        }
        imageView.setImageLevel(level);
    }
}
