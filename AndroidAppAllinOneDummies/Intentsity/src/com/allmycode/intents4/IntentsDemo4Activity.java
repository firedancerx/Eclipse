package com.allmycode.intents4;

import java.util.Iterator;
import java.util.Scanner;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class IntentsDemo4Activity extends Activity implements OnClickListener {
    private static final String CLASSNAME = "IntentsDemo4Activity";

    SharedPreferences prefs = null;
    Intent savedIntent = null;
    IntentFilter filter = null;
    EditText actionText, uriText, 
            mimeTypeText, categoryText;
    Button addIntentCatButton, addFilterCatButton, addFilterActionButton,
            addFilterSchemeButton, addFilterAuthButton, addFilterPathButton,
            addFilterTypeButton, testButton, clearButton;
    TextView messagesView;
    LinearLayout intentCategoriesLayout, filterActionsLayout,
            filterCategoriesLayout, filterSchemeLayout, filterAuthLayout,
            filterPathLayout, filterTypeLayout;
    boolean isFirstTime = true, yesReally = false;
    boolean intentHasBothUriAndType = false;
    final int MY_REQUEST_CODE = 42;


    String[] pathPatterns = {"LITERAL", "PREFIX", "GLOB"};

    BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Intent matchDialogIntent = new Intent();
            matchDialogIntent.setClassName("com.allmycode.intents4",
                    "com.allmycode.intents4.MyMatchDialog");
            matchDialogIntent.putExtra("anIntent", intent.toString());
            matchDialogIntent.putExtra("aFilter", filterToString(filter));
            startActivity(matchDialogIntent);
            Log.i(CLASSNAME, "match with " + intent.toString());
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        actionText = (EditText) findViewById(R.id.editTextAction);
        uriText = (EditText) findViewById(R.id.editTextUri);

        mimeTypeText = (EditText) findViewById(R.id.editTextMimeType);
        addIntentCatButton = (Button) findViewById(R.id.addIntentCatButton);
        addIntentCatButton.setOnClickListener(this);
        intentCategoriesLayout = (LinearLayout) findViewById(R.id.intentCategoriesLayout);
        addRow(intentCategoriesLayout, "addCategory",
                "e.g., android.intent.category.SAMPLE_CODE", false);
        filterActionsLayout = (LinearLayout) findViewById(R.id.filterActionLayout);
        addFilterActionButton = (Button) findViewById(R.id.addFilterActionButton);
        addFilterActionButton.setOnClickListener(this);
        filterActionsLayout = (LinearLayout) findViewById(R.id.filterActionLayout);
        addRow(filterActionsLayout, "addAction",
                "e.g., android.intent.action.VIEW", false);

        addFilterSchemeButton = (Button) findViewById(R.id.addFilterSchemeButton);
        addFilterSchemeButton.setOnClickListener(this);
        filterSchemeLayout = (LinearLayout) findViewById(R.id.filterSchemeLayout);

        addFilterAuthButton = (Button) findViewById(R.id.addFilterAuthButton);
        addFilterAuthButton.setOnClickListener(this);
        filterAuthLayout = (LinearLayout) findViewById(R.id.filterAuthorityLayout);

        addFilterPathButton = (Button) findViewById(R.id.addFilterPathButton);
        addFilterPathButton.setOnClickListener(this);
        filterPathLayout = (LinearLayout) findViewById(R.id.filterPathLayout);

        addFilterTypeButton = (Button) findViewById(R.id.addFilterTypeButton);
        addFilterTypeButton.setOnClickListener(this);
        filterTypeLayout = (LinearLayout) findViewById(R.id.filterTypeLayout);

        addFilterCatButton = (Button) findViewById(R.id.addFilterCatButton);
        addFilterCatButton.setOnClickListener(this);
        filterCategoriesLayout = (LinearLayout) findViewById(R.id.filterCategoryLayout);
        addRow(filterCategoriesLayout, "addCategory",
                "e.g., android.intent.category.SAMPLE_CODE", false);

        isFirstTime = false;
        testButton = (Button) findViewById(R.id.buttonTest);
        testButton.setOnClickListener(this);
        clearButton = (Button) findViewById(R.id.buttonClear);
        clearButton.setOnClickListener(this);
        messagesView = (TextView) findViewById(R.id.textViewMessages);
        

    }

    void addRow(final LinearLayout layout, 
            String label, String hintStr,
            boolean addRadioGroup) {
        LinearLayout.LayoutParams rowLayoutParams = 
                new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams editTextLayoutParams =
                new LinearLayout.LayoutParams(
                180, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (addRadioGroup) {
            editTextLayoutParams = 
                    new LinearLayout.LayoutParams(180, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        LinearLayout.LayoutParams buttonLayoutParams = 
                new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);

        TextView textView = new TextView(this);
        textView.setText(label);
        row.addView(textView);
        EditText editText = new EditText(this);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        editText.setHint(hintStr);
        editText.setLayoutParams(editTextLayoutParams);
        if (!isFirstTime) {
            editText.requestFocus();
        }
        row.addView(editText);

        if (addRadioGroup) {
            LinearLayout groupLayout = new LinearLayout(this);
            groupLayout.setOrientation(LinearLayout.VERTICAL);
            groupLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            
            RadioGroup group = new RadioGroup(this);
            group.setLayoutParams(new RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT));

            

            final Button patternButton = new Button(this);
            patternButton.setText(pathPatterns[0]);
            patternButton.setTextSize(8);
            patternButton.setLayoutParams(buttonLayoutParams);
            patternButton.setOnClickListener(new OnClickListener() {              
              @Override
              public void onClick(View v) {
                String patternButtonText = patternButton.getText().toString().trim();
                if (patternButtonText.equals(pathPatterns[0])) {
                  patternButton.setText(pathPatterns[1]);
                } else if (patternButtonText.equals(pathPatterns[1])) {
                  patternButton.setText(pathPatterns[2]);
                } else if (patternButtonText.equals(pathPatterns[2])) {
                  patternButton.setText(pathPatterns[0]);
                }
              }
            });
            groupLayout.addView(patternButton);
            row.addView(groupLayout);
        }
        Button button = new Button(this);
        button.setTextSize(10);
        button.setTypeface(null, Typeface.BOLD);
        button.setText("X");
        button.setTypeface(
                Typeface.SANS_SERIF, Typeface.BOLD);
        button.setLayoutParams(buttonLayoutParams);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                layout.removeView(
                        (LinearLayout) view.getParent());
            }
        });
        row.addView(button);

        row.setLayoutParams(rowLayoutParams);
        layout.addView(row);
    }

    class RadioButtonPlusStuff extends RadioButton {

        EditText editText;
        TextView textView;
        String aHint;

        public EditText getEditText() {
            return editText;
        }

        public void setEditText(EditText editText) {
            this.editText = editText;
        }

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }

        public String getAHint() {
            return aHint;
        }

        public void setAHint(String aHint) {
            this.aHint = aHint;
        }

        public RadioButtonPlusStuff(Context context) {
            super(context);
        }

    }

    @Override
    public void onClick(View view) {
        if (view == addIntentCatButton) {
            addRow(intentCategoriesLayout, "addCategory",
                    "e.g., android.intent.category.SAMPLE_CODE", false);
        }
        if (view == addFilterCatButton) {
            addRow(filterCategoriesLayout, "addCategory",
                    "e.g., android.intent.category.SAMPLE_CODE", false);
            if (filterCategoriesLayout.getChildCount() == 1) {
                ((EditText) ((ViewGroup) filterCategoriesLayout.getChildAt(0))
                        .getChildAt(1))
                        .setText("android.intent.category.SAMPLE_CODE");
            }
        }
        if (view == addFilterActionButton) {
            addRow(filterActionsLayout, "addAction",
                    "e.g., android.intent.action.VIEW", false);
        }
        if (view == addFilterSchemeButton) {
            addRow(filterSchemeLayout, "addDataScheme",
                    "e.g., content (without a colon)", false);
        }
        if (view == addFilterAuthButton) {
            addRow(filterAuthLayout, "addDataAuthority",
                    "e.g., example.com:2000", false);
        }
        if (view == addFilterPathButton) {
            addRow(filterPathLayout, "addDataPath", "e.g., /folder/subfoler",
                    true);
        }
        if (view instanceof RadioButtonPlusStuff) {

            (((RadioButtonPlusStuff) view).getTextView())
                    .setText(((RadioButtonPlusStuff) view).getAHint());
        }
        if (view == addFilterTypeButton) {
            addRow(filterTypeLayout, "addDataType", "e.g., text/html", false);
        }
        if (view == testButton) {

            Intent intent = createIntentFromEditTextFields();

            if (intentHasBothUriAndType) {
                savedIntent = intent;

                Intent alertIntent = new Intent();
                alertIntent.setClassName("com.allmycode.intents4",
                        "com.allmycode.intents4.MyAlert");
                startActivityForResult(alertIntent, MY_REQUEST_CODE);
            } else {
                finishTest(intent);
            }
        } else if (view == clearButton) {
            clearEditTextFields();
        }
    }

    void finishTest(Intent intent) {
        Log.i(CLASSNAME, "---------");
        Log.i(CLASSNAME, intent.toString());
        filter = createFilterFromEditTextFields();
      
        filterToString(filter);

        try {
            unregisterReceiver(myReceiver);
        } catch (IllegalArgumentException e) {
            // Do nothing
        }
        registerReceiver(myReceiver, filter);

        messagesView.setText(intent.toString() + "\n\n"
                + filterToString(filter));
        sendBroadcast(intent);

        Log.i(CLASSNAME, "---------");
    }

    String filterToString(IntentFilter filter) {
        StringBuffer buffer = new StringBuffer("IntentFilter { ");

        Iterator<String> actionsIter = filter.actionsIterator();
        if (actionsIter != null) {
            buffer.append("act=[");
            while (actionsIter.hasNext()) {
                String temp = actionsIter.next().trim();
                buffer.append(temp + ",");
                Log.i(CLASSNAME, "Filter action: >" + temp + "<");
            }
            if (buffer.lastIndexOf(",") >= 0) {
                buffer.setCharAt(buffer.lastIndexOf(","), ']');
            } else {
                buffer.append(']');
            }
            buffer.append(" ");
        }

        Iterator<String> schemesIter = filter.schemesIterator();
        if (schemesIter != null) {
            buffer.append("sche=[");
            while (schemesIter.hasNext()) {
                String temp = schemesIter.next().trim();
                buffer.append(temp + ",");
                Log.i(CLASSNAME, "Filter scheme: >" + temp + "<");
            }
            buffer.setCharAt(buffer.lastIndexOf(","), ']');
            buffer.append(" ");
        }

        Iterator<IntentFilter.AuthorityEntry> authsIter = filter
                .authoritiesIterator();
        if (authsIter != null) {
            buffer.append("auth=[");
            while (authsIter.hasNext()) {
                IntentFilter.AuthorityEntry entry = authsIter.next();
                String temp = entry.getHost() + ":" + entry.getPort();
                buffer.append(temp + ",");
                Log.i(CLASSNAME, "Filter authority: >" + temp + "<");
            }
            buffer.setCharAt(buffer.lastIndexOf(","), ']');
            buffer.append(" ");
        }

        Iterator<PatternMatcher> pathsIter = filter.pathsIterator();
        if (pathsIter != null) {
            buffer.append("path=[");
            while (pathsIter.hasNext()) {
                PatternMatcher entry = pathsIter.next();
                String temp = entry.toString();
                buffer.append(temp + ",");
                Log.i(CLASSNAME, "Filter path: >" + temp + "<");
            }
            buffer.setCharAt(buffer.lastIndexOf(","), ']');
            buffer.append(" ");
        }

        Iterator<String> typesIter = filter.typesIterator();
        if (typesIter != null) {
            buffer.append("typ=[");
            while (typesIter.hasNext()) {
                String temp = typesIter.next().trim();
                buffer.append(temp + ",");
                Log.i(CLASSNAME, "Filter type: >" + temp + "<");
            }
            buffer.setCharAt(buffer.lastIndexOf(","), ']');
            buffer.append(" ");
        }

        Iterator<String> catsIter = filter.categoriesIterator();
        if (catsIter != null) {
            buffer.append("cat=[");
            while (catsIter.hasNext()) {
                String temp = catsIter.next().trim();
                buffer.append(temp + ",");
                Log.i(CLASSNAME, "Filter category: >" + temp + "<");
            }
            buffer.setCharAt(buffer.lastIndexOf(","), ']');
            buffer.append(" ");
        }

        buffer.append('}');
        return buffer.toString();
    }

    private Intent createIntentFromEditTextFields() {
        String theAction = 
                actionText.getText().toString().trim();
        String theUri = uriText.getText().toString().trim();
        String theMimeType = mimeTypeText.getText().toString().trim();

        Intent intent = new Intent();
        if (theAction.length() != 0) {
            intent.setAction(theAction);
        }
        intentHasBothUriAndType = false;
        if (theUri.length() != 0 && theMimeType.length() != 0) {
            intentHasBothUriAndType = true;
            intent.setDataAndType(Uri.parse(theUri), theMimeType);
        } else if (theUri.length() != 0) {
            intent.setData(Uri.parse(theUri));
        } else if (theMimeType.length() != 0) {
            intent.setType(theMimeType);
        }

        if (intentCategoriesLayout != null) {
            int count = 
                    intentCategoriesLayout.getChildCount();
            for (int i = 0; i < count; i++) {
                String cat = 
                        ((EditText) ((ViewGroup) intentCategoriesLayout
                        .getChildAt(i)).getChildAt(1)).getText().toString()
                        .trim();
                if (cat.length() != 0) {
                    intent.addCategory(cat);
                }
            }
        }
        Log.i(CLASSNAME, intent.toString());
        return intent;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            Intent intent) {
        if (requestCode == MY_REQUEST_CODE && resultCode == RESULT_OK) {
            yesReally = intent.getBooleanExtra("yesReally", false);
            if (!yesReally) {
                mimeTypeText.requestFocus();
            }
            if (yesReally) {
                finishTest(savedIntent);
            }
        }
    }

    private IntentFilter createFilterFromEditTextFields() {
        IntentFilter filter = new IntentFilter();

        if (filterActionsLayout != null) {
            int count = filterActionsLayout.getChildCount();
            for (int i = 0; i < count; i++) {
                String action = ((EditText) ((ViewGroup) filterActionsLayout
                        .getChildAt(i)).getChildAt(1)).getText().toString()
                        .trim();
                if (action.length() != 0) {
                    filter.addAction(action);
                }
            }
        }

        if (filterSchemeLayout != null) {
            int count = filterSchemeLayout.getChildCount();
            for (int i = 0; i < count; i++) {
                String scheme = ((EditText) ((ViewGroup) filterSchemeLayout
                        .getChildAt(i)).getChildAt(1)).getText().toString()
                        .trim();
                if (scheme.length() != 0) {
                    filter.addDataScheme(scheme);
                }
            }
        }

        if (filterAuthLayout != null) {
            int count = filterAuthLayout.getChildCount();
            for (int i = 0; i < count; i++) {
                String auth = ((EditText) ((ViewGroup) filterAuthLayout
                        .getChildAt(i)).getChildAt(1)).getText().toString()
                        .trim();
                if (auth.length() != 0) {
                    Scanner scanner = new Scanner(auth);
                    scanner.useDelimiter(":");
                    String host = null;
                    String port = null;
                    if (scanner.hasNext()) {
                        host = scanner.next();
                    }
                    if (scanner.hasNext()) {
                        port = scanner.next();
                    }
                    filter.addDataAuthority(host, port);
                }
            }
        }

        if (filterPathLayout != null) {
            int count = filterPathLayout.getChildCount();
            for (int i = 0; i < count; i++) {

                ViewGroup group = (ViewGroup) filterPathLayout.getChildAt(i);
                String path = ((EditText) group.getChildAt(1)).getText()
                        .toString().trim();
                String pattern = ((TextView) ((ViewGroup) group.getChildAt(2))
                        .getChildAt(0)).getText().toString().trim(); // ((TextView)

                int patternInt = 0;
                if (pattern.equals(pathPatterns[0])) {
                    patternInt = PatternMatcher.PATTERN_LITERAL;
                }
                if (pattern.equals(pathPatterns[1])) {
                    patternInt = PatternMatcher.PATTERN_PREFIX;
                }
                if (pattern.equals(pathPatterns[2])) {
                    patternInt = PatternMatcher.PATTERN_SIMPLE_GLOB;
                }
                if (path.length() != 0) {
                    filter.addDataPath(path, patternInt);
                }
            }
        }

        if (filterTypeLayout != null) {
            int count = filterTypeLayout.getChildCount();
            for (int i = 0; i < count; i++) {
                String aType = ((EditText) ((ViewGroup) filterTypeLayout
                        .getChildAt(i)).getChildAt(1)).getText().toString()
                        .trim();
                if (aType.length() != 0) {
                    try {
                        filter.addDataType(aType);
                    } catch (MalformedMimeTypeException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (filterCategoriesLayout != null) {
            int count = filterCategoriesLayout.getChildCount();
            for (int i = 0; i < count; i++) {
                String cat = ((EditText) ((ViewGroup) filterCategoriesLayout
                        .getChildAt(i)).getChildAt(1)).getText().toString()
                        .trim();
                if (cat.length() != 0) {
                    filter.addCategory(cat);
                }
            }
        }
        return filter;
    }

    void clearEditTextFields() {
        LinearLayout entireLayout = (LinearLayout) findViewById(R.id.entireLayout);
        clearFieldsWithin(entireLayout);
    }
    
    void clearFieldsWithin(View view) {
      if (view instanceof EditText) {
        ((EditText) view).setText("");
      } else if (view instanceof ViewGroup) {
        int childCount = ((ViewGroup) view).getChildCount();
        for (int i = 0; i < childCount; i++) {
          clearFieldsWithin(((ViewGroup) view).getChildAt(i));
        }
      }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("***", "Pausing");
        prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("***", "Resuming");

    }
}

