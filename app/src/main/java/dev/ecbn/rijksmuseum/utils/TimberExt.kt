package dev.ecbn.rijksmuseum.utils

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dev.ecbn.rijksmuseum.BuildConfig
import timber.log.Timber


/**
 * Created by Eka Cahya Nugraha on 27/10/21.
 * ecbn95@gmail.com
 */

fun timberInitialization() {
    if (BuildConfig.DEBUG) {
        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                Logger.log(priority, tag, message, t)
            }
        })
    }
    Logger.addLogAdapter(AndroidLogAdapter())
}

fun logInfo(s: String, vararg objects: Any) {
    Timber.i(s, *objects)
}

fun logInfo(throwable: Throwable, s: String, vararg objects: Any) {
    Timber.i(throwable, s, *objects)
}

fun logDebug(s: String, vararg objects: Any) {
    Timber.d(s, *objects)
}

fun logDebug(throwable: Throwable, s: String, vararg objects: Any) {
    Timber.d(throwable, s, *objects)
}

fun logWarning(s: String, vararg objects: Any) {
    Timber.d(s, *objects)
}

fun logWarning(throwable: Throwable, s: String, vararg objects: Any) {
    Timber.w(throwable, s, *objects)
}

fun logVerbose(s: String, vararg objects: Any) {
    Timber.v(s, *objects)
}

fun logVerbose(throwable: Throwable, s: String, vararg objects: Any) {
    Timber.v(throwable, s, *objects)
}

fun logError(s: String, vararg objects: Any) {
    Timber.e(s, *objects)
}

fun logError(throwable: Throwable, s: String, vararg objects: Any) {
    Timber.e(throwable, s, *objects)
}