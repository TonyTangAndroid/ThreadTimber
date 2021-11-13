package com.tonytangandroid.github.thread_timber;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import timber.log.ThreadTree;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Timber.plant(new ThreadTree("tony"));
    setContentView(R.layout.activity_main);
    Timber.i("Log message in Main Thread");
    new Thread(new Runnable() {
      @Override
      public void run() {
        Timber.v("Log message in Worker Thread");
      }
    }).start();
  }
}