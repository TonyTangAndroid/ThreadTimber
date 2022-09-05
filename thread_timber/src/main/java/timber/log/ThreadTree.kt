package timber.log

import java.util.Locale


/**
 * A [Tree] for debug builds with thread info. Automatically infers the tag from the
 * calling class.
 */
class ThreadTree @JvmOverloads constructor(private val threadTagPrefix: String = "") :
  Timber.DebugTree() {

  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    var assembledMessage: String = message
    assembledMessage = formatThreadTag() + ":" + assembledMessage
    super.log(priority, tag, assembledMessage, t)
  }

  private fun formatThreadTag(): String {
    return String.format(
      Locale.US,
      "[%s#%s]",
      threadTagPrefix,
      Thread.currentThread().name
    )
  }

}