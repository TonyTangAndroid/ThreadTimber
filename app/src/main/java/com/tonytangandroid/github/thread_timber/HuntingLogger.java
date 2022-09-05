package com.tonytangandroid.github.thread_timber;

import androidx.annotation.Nullable;
import timber.log.Timber;

public class HuntingLogger {

  public static void log(String message, @Nullable Object... args) {
    Timber.d(message, args);
  }

}
