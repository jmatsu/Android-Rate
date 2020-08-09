package hotchemi.android.rate.internal

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import hotchemi.android.rate.AppRateAction
import hotchemi.android.rate.DefaultDialogOption
import hotchemi.android.rate.PromotionDialogFactory

internal class BuiltinPromotionDialogFactory(
        private val option: DefaultDialogOption
) : PromotionDialogFactory {
    override fun create(activity: Activity, action: AppRateAction): Dialog {
        return AlertDialog.Builder(activity, Utils.dialogTheme).apply {
            setMessage(option.messageText.string(context))

            if (option.showTitle) {
                setTitle(option.titleText.string(context))
            }

            setCancelable(option.cancelable)

            option.view?.also { view ->
                setView(view)
            }

            val listener = option.listener?.get()

            setPositiveButton(option.positiveButtonText.string(context)) { dialog, which ->
                action.navigateToReview()
                listener?.onClick(dialog, which)
            }

            if (option.showNeutralButton) {
                setNeutralButton(option.neutralButtonText.string(context)) { dialog, which ->
                    action.scheduleReminder()
                    listener?.onClick(dialog, which)
                }
            }

            if (option.showNegativeButton) {
                setNegativeButton(option.negativeButtonText.string(context)) { dialog, which ->
                    action.denyReview()
                    listener?.onClick(dialog, which)
                }
            }
        }.create()
    }
}