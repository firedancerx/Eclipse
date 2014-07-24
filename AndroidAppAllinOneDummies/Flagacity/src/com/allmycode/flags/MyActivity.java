package com.allmycode.flags;

import java.lang.reflect.Field;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

public class MyActivity extends Activity {
    AutoCompleteTextView targetActivity;
    AutoCompleteTextView flags;
    EditText cheatSheet;
    static final String[] FLAGS_LIST = {
            "FLAG_ACTIVITY_BROUGHT_TO_FRONT 4194304 (0x00400000)",
            "FLAG_ACTIVITY_CLEAR_TOP 67108864 (0x04000000)",
            "FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS 8388608 (0x00800000)",
            "FLAG_ACTIVITY_FORWARD_RESULT 33554432 (0x02000000)",
            "FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY 1048576 (0x00100000)",
            "FLAG_ACTIVITY_MULTIPLE_TASK 134217728 (0x08000000)",
            "FLAG_ACTIVITY_NEW_TASK 268435456 (0x10000000)",
            "FLAG_ACTIVITY_NO_HISTORY 1073741824 (0x40000000)",
            "FLAG_ACTIVITY_PREVIOUS_IS_TOP 16777216 (0x01000000)",
            "FLAG_ACTIVITY_RESET_TASK_IF_NEEDED 2097152 (0x00200000)",
            "FLAG_ACTIVITY_SINGLE_TOP 536870912 (0x20000000)", "Since API 3:",
            "FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET 524288 (0x00080000)",
            "FLAG_ACTIVITY_NO_USER_ACTION 262144 (0x00040000)",
            "FLAG_ACTIVITY_REORDER_TO_FRONT 131072 (0x00020000)",
            "Since API 5:", "FLAG_ACTIVITY_NO_ANIMATION 65536 (0x00010000)",
            "Since API 11:", "FLAG_ACTIVITY_CLEAR_TASK 32768 (0x00008000)",
            "FLAG_ACTIVITY_TASK_ON_HOME 16384 (0x00004000)" };
    static final String[] FLAGS_LIST_CONCISE = {
      "FLAG_ACTIVITY_BROUGHT_TO_FRONT",
      "FLAG_ACTIVITY_CLEAR_TOP",
      "FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS",
      "FLAG_ACTIVITY_FORWARD_RESULT",
      "FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY",
      "FLAG_ACTIVITY_MULTIPLE_TASK",
      "FLAG_ACTIVITY_NEW_TASK",
      "FLAG_ACTIVITY_NO_HISTORY",
      "FLAG_ACTIVITY_PREVIOUS_IS_TOP",
      "FLAG_ACTIVITY_RESET_TASK_IF_NEEDED",
      "FLAG_ACTIVITY_SINGLE_TOP",
      "FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)",
      "FLAG_ACTIVITY_NO_USER_ACTION",
      "FLAG_ACTIVITY_REORDER_TO_FRONT",
      "FLAG_ACTIVITY_NO_ANIMATION",
      "FLAG_ACTIVITY_CLEAR_TASK)",
      "FLAG_ACTIVITY_TASK_ON_HOME" };
    static final String[] TARGETS_LIST = {
      "1", "1Other", "2", "2Other", "3", "3Other", "4", "4Other"
    };
    TextView helloView;
    static final String CLASSNAME = "MyActivity";
    ActivityManager actManager = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        targetActivity = (AutoCompleteTextView) findViewById(R.id.editText1);
        ArrayAdapter<String> targetsAdapter = new ArrayAdapter<String>(this, R.layout.list_item, TARGETS_LIST);
        targetActivity.setAdapter(targetsAdapter);
        flags = (AutoCompleteTextView) findViewById(R.id.editText2);
        ArrayAdapter<String> flagsAdapter = new ArrayAdapter<String>(this, R.layout.list_item, FLAGS_LIST_CONCISE);
        flags.setAdapter(flagsAdapter);
        cheatSheet = (EditText) findViewById(R.id.editText3);
        for (String item : FLAGS_LIST) {
            cheatSheet.append(item);
            cheatSheet.append("\n");
        }
        Intent whoCalled = getIntent();
        helloView = ((TextView) findViewById(R.id.textView3));
        helloView.setTextColor(android.graphics.Color.WHITE);
        helloView.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        helloView.setText("Flags: 0x"
                + Integer.toHexString(whoCalled.getFlags()));
        if (whoCalled.getExtras() != null) {
            boolean areErrors = whoCalled.getExtras().getBoolean("existErrors",
                    false);
            if (areErrors) {
                helloView.append(" There were errors!");
                helloView.setTextColor(android.graphics.Color.RED);
                helloView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            }
        }
        
//        actManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//        logSystemInfo();
        
        ActivityManager am = (ActivityManager) 
                getSystemService(Service.ACTIVITY_SERVICE); 
                List<ActivityManager.RunningAppProcessInfo> processes; 
                processes = am.getRunningAppProcesses(); 
                for(ActivityManager.RunningAppProcessInfo info: processes) { 
                    Log.i("Process:", info.processName); }
    }
    
    
    void logSystemInfo() {
       List<ActivityManager.RunningAppProcessInfo> procInfoList = actManager.getRunningAppProcesses();
       Log.i(CLASSNAME, "********");
       for (ActivityManager.RunningAppProcessInfo procInfo : procInfoList) {
           Log.i(CLASSNAME, "Running app: " + procInfo.getClass().getName());
       }
       
       List<ActivityManager.RunningTaskInfo> taskInfoList = actManager.getRunningTasks(30);
       for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
           Log.i(CLASSNAME, "Running task: " + taskInfo.getClass().getName());
       }
       Log.i(CLASSNAME, "********");
    }

    public void go(View view) {
        Intent intent = null;
        String targetActivityName = "com.allmycode.flags";
                
    //    try {
            //intent = new Intent(this, Class.forName(targetActivityName));
            intent = new Intent();
            String fromEditText = targetActivity.getText().toString().trim();
            String other = (fromEditText.contains("Other"))?".other":"";
//            if (!other.equals("")) {
//                targetActivityName += ".other";  
//            } 
            targetActivityName += other;
            targetActivityName += ".FlagsDemoActivity";
            targetActivityName += fromEditText;
            Log.i(CLASSNAME, "Target activity: >>" + targetActivityName + "<<");
          intent.setClassName("com.allmycode.flags" + other , targetActivityName);
  //      } catch (ClassNotFoundException e) {
  //          Log.i(CLASSNAME, e.getMessage());

            String allFlags = flags.getText().toString();
            int flagsValue = 0;
            if (allFlags != "" && allFlags != null) {

                TextUtils.SimpleStringSplitter splitter = new TextUtils.SimpleStringSplitter(
                        '|');
                splitter.setString(allFlags);
                boolean existErrors = false;
                for (String flagName : splitter) {

                    Log.i(CLASSNAME, ">>" + flagName + "<<");

                    flagName = flagName.trim();
                    if (!flagName.equals("") && flagName != null) { // BARRY
                                                                    // need
                                                                    // both?
                        if (isHex(flagName)) {
                            Log.i(CLASSNAME, flagName + " is hex");
                            flagsValue |= Integer.parseInt(
                                    flagName.substring(2), 16);
                        } else if (isDec(flagName)) {
                            Log.i(CLASSNAME, flagName + " is decimal");
                            flagsValue |= Integer.parseInt(flagName);
                        } else {
                            Field flagsField = null;
                            try {
                                Log.i(CLASSNAME, "About to do reflection>>"
                                        + flagName + "<<");
                                flagsField = android.content.Intent.class
                                        .getField(flagName);
                                Log.i(CLASSNAME, Integer.toString(flagsField
                                        .getInt(this)));
                                flagsValue |= flagsField.getInt(this);
                            } catch (SecurityException ex) {
                                existErrors = true;
                                ex.printStackTrace();
                            } catch (NoSuchFieldException ex) {
                                existErrors = true;
                                ex.printStackTrace();
                            } catch (IllegalAccessException ex) {
                                existErrors = true;
                                ex.printStackTrace();
                            }
                            try {
                                Log.i(CLASSNAME,
                                        Integer.toHexString(flagsValue));
                                if (flagsValue != 0) {
                                    intent.addFlags(flagsValue);
                                }
                            } catch (IllegalArgumentException e) {
                                existErrors = true;
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (flagsValue != 0) {
                    intent.addFlags(flagsValue);
                }
                intent.putExtra("existErrors", existErrors);

                Log.i(CLASSNAME, "About to start " + intent.toString());
                startActivity(intent);
            }
        //}
    }

    boolean isDec(String string) {
        boolean temp = true;
        for (char character : string.toCharArray()) {
            temp = temp && Character.isDigit(character);
        }
        return temp;
    }

    boolean isHex(String string) {
        boolean temp = (string.charAt(0) == '0');
        temp = temp && (string.charAt(1) == 'x');
        for (int i = 2; i < string.length(); i++) {
            temp = temp && isHexDigit(string.charAt(i));
        }
        return temp;
    }

    boolean isHexDigit(char ch) {
        Log.i(CLASSNAME, "testing " + Character.toString(ch));
        switch (ch) {
        case 'A':
            return true;
        case 'B':
            return true;
        case 'C':
            return true;
        case 'D':
            return true;
        case 'E':
            return true;
        default:
            return Character.isDigit(ch);
        }
    }
}
