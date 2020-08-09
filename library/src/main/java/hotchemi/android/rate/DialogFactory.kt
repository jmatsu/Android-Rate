package hotchemi.android.rate

import android.app.Dialog
import android.content.Context

interface DialogFactory {
    fun create(context: Context): Dialog
}