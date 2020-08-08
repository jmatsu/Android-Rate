package hotchemi.android.rate.internal

import android.os.Build
import hotchemi.android.rate.R

internal object Utils {
    val isLollipop: Boolean
        get() = Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP || Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1

    val dialogTheme: Int
        get() = if (isLollipop) R.style.CustomLollipopDialogStyle else 0
}