package hotchemi.android.rate.internal

import android.util.Log
import hotchemi.android.rate.BuildConfig

internal inline fun debugLog(msg: () -> String) {
    if (BuildConfig.DEBUG) {
        Log.d("AppRate", msg())
    }
}