package com.tonytangandroid.github.thread_timber;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    TimberUtil.initTimber(getApplication());
    setContentView(R.layout.activity_main);
    Timber.i("log_message in Main Thread");
    Timber.tag("tag_1").i("log_message in Main Thread with tag");
    HuntingLogger.log("test hunting logger");
    new Thread(this::logWorkerThread).start();
  }

  private void logWorkerThread() {
    Timber.v("log_message in Worker Thread");
    Timber.tag("persistable").i("log_message in Worker Thread with tag");
  }
}