package com.tonytangandroid.github.thread_timber

import android.app.Application
import android.util.Log
import com.tonytangandroid.wood.WoodTree
import timber.log.ThreadTree
import timber.log.Timber

object TimberUtil {

  @JvmStatic
  fun initTimber(application: Application) {
    Timber.plant(ThreadTree("tttt"))
    Timber.plant(
      WoodTree(application,"tony_tracing")
        .retainDataFor(WoodTree.Period.FOREVER)
        .logLevel(Log.VERBOSE)
        .limitToTheseTaggerList(listOf("persistable","HuntingLogger"))
        .autoScroll(false)
        .maxLength(100000)
        .showNotification(true)
    )
  }
}
