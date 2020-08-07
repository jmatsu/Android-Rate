package hotchemi.android.rate

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Build

internal object Utils {
    fun underHoneyComb(): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB
    }

    val isLollipop: Boolean
        get() = Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP || Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1

    val dialogTheme: Int
        get() = if (isLollipop) R.style.CustomLollipopDialogStyle else 0

    @SuppressLint("NewApi")
    fun getDialogBuilder(context: Context?): AlertDialog.Builder {
        return if (underHoneyComb()) {
            AlertDialog.Builder(context)
        } else {
            AlertDialog.Builder(context, dialogTheme)
        }
    }
}