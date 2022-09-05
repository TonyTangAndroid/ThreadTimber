package timber.log

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import java.util.*
import java.util.regex.Pattern

/**
 * A [Tree] for debug builds with thread info. Automatically infers the tag from the
 * calling class.
 */
class ThreadTree @JvmOverloads constructor(private val threadTagPrefix: String = "") :
  Timber.Tree() {
  /**
   * Extract the tag which should be used for the message from the `element`. By default this
   * will use the class name without any anonymous class suffixes (e.g., `Foo$1` becomes
   * `Foo`).
   *
   *
   * Note: This will not be called if a [manual tag][.tag] was specified.
   */
  protected fun createStackElementTag(element: StackTraceElement): String {
    var tag = element.className
    val m = ANONYMOUS_CLASS.matcher(tag)
    if (m.find()) {
      tag = m.replaceAll("")
    }
    tag = tag.substring(tag.lastIndexOf('.') + 1)
    // Tag length limit was removed in API 24.
    return if (tag.length <= MAX_TAG_LENGTH || Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      tag
    } else tag.substring(0, MAX_TAG_LENGTH)
  }

  public override fun getTag(): String {
    val tag = super.getTag()
    if (tag != null) {
      return tag
    }

    // DO NOT switch this to Thread.getCurrentThread().getStackTrace(). The test will pass
    // because Robolectric runs them on the JVM but on Android the elements are different.
    val stackTrace = Throwable().stackTrace
    check(stackTrace.size > CALL_STACK_INDEX) { "Synthetic stacktrace didn't have enough elements: are you using proguard?" }
    return createStackElementTag(stackTrace[CALL_STACK_INDEX])
  }

  /**
   * Break up `message` into maximum-length chunks (if needed) and send to either [ ][Log.println] or [Log.wtf()][Log.wtf]
   * for logging.
   *
   *
   * {@inheritDoc}
   */
  @SuppressLint("LogNotTimber")
  override fun log(priority: Int, tag: String?, rawMessage: String, t: Throwable?) {
    var message: String = rawMessage
    message = formatThreadTag() + ":" + message
    if (message.length < MAX_LOG_LENGTH) {
      if (priority == Log.ASSERT) {
        Log.wtf(tag, message)
      } else {
        Log.println(priority, tag, message)
      }
      return
    }

    // Split by line, then ensure each line can fit into Log's maximum length.
    var i = 0
    val length = message.length
    while (i < length) {
      var newline = message.indexOf('\n', i)
      newline = if (newline != -1) newline else length
      do {
        val end = Math.min(newline, i + MAX_LOG_LENGTH)
        val part = message.substring(i, end)
        if (priority == Log.ASSERT) {
          Log.wtf(tag, part)
        } else {
          Log.println(priority, tag, part)
        }
        i = end
      } while (i < newline)
      i++
    }
  }

  private fun formatThreadTag(): String {
    return String.format(
      Locale.getDefault(),
      "[%s#%s]",
      threadTagPrefix,
      Thread.currentThread().name
    )
  }

  companion object {
    private const val MAX_LOG_LENGTH = 4000
    private const val MAX_TAG_LENGTH = 23
    private const val CALL_STACK_INDEX = 5
    private val ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$")
  }
}