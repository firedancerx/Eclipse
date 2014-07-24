package com.allmycode.samples;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MyActivity extends Activity implements
    OnClickListener {

  Button button;
  ImageView imageView;
  ProgressBar progressBar;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    button = ((Button) findViewById(R.id.button1));
    button.setOnClickListener(this);

    imageView = (ImageView) findViewById(R.id.imageView1);
    progressBar =
        (ProgressBar) findViewById(R.id.progressBar1);
    progressBar.setProgress(0);
  }

  public void onClick(View view) {
    new MyAsyncTask()
        .execute("http://allmycode.com/JavaForDummies/"
            + "JavaForDummies5thEdition.jpg");
  }

  // MyAsyncTask is in Listing BARRY

  class MyAsyncTask extends
      AsyncTask<String, Integer, Bitmap> {

    int progress;

    @Override
    protected void onPreExecute() {
      progress = 0;
      button.setClickable(false);
    }

    @Override
    protected Bitmap doInBackground(String... urlArray) {
      try {
        URL url = new URL(urlArray[0]);
        HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();

        progress += 50;
        publishProgress(progress);

        InputStream input = connection.getInputStream();
        Bitmap bitmap = BitmapFactory.decodeStream(input);

        progress += 50;
        publishProgress(progress);

        return bitmap;
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }

    @Override
    protected void onProgressUpdate(
        Integer... progressArray) {
      progressBar.setProgress(progressArray[0]);
    }

    @Override
    protected void onPostExecute(Bitmap result) {
      imageView.setImageBitmap(result);
      button.setClickable(true);
    }
  }

}