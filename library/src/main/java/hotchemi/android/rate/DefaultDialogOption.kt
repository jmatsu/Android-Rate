package hotchemi.android.rate

import android.content.DialogInterface
import android.view.View
import java.lang.ref.WeakReference

class DefaultDialogOption {
    var showNeutralButton: Boolean = true
    var showNegativeButton: Boolean = true
    var showTitle: Boolean = true

    var cancelable: Boolean = false

    var view: View? = null

    var titleText: TextResource = TextResource(resourceId = R.string.rate_dialog_title)
    var messageText: TextResource = TextResource(resourceId = R.string.rate_dialog_message)
    var positiveButtonText: TextResource = TextResource(resourceId = R.string.rate_dialog_ok)
    var neutralButtonText: TextResource = TextResource(resourceId = R.string.rate_dialog_cancel)
    var negativeButtonText: TextResource = TextResource(resourceId = R.string.rate_dialog_no)

    internal var listener: WeakReference<DialogInterface.OnClickListener?>? = null

    fun setListener(listener: DialogInterface.OnClickListener?) {
        this.listener = WeakReference(listener)
    }
}