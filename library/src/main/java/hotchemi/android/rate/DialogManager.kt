package hotchemi.android.rate

import android.app.Dialog
import android.content.Context

internal object DialogManager {
    fun create(context: Context, options: DialogOptions): Dialog {
        val builder = Utils.getDialogBuilder(context)
        builder.setMessage(options.getMessageText(context))
        if (options.shouldShowTitle()) builder.setTitle(options.getTitleText(context))
        builder.setCancelable(options.cancelable)
        val view = options.view
        if (view != null) builder.setView(view)
        val listener = options.getListener()
        builder.setPositiveButton(options.getPositiveText(context)) { dialog, which ->
            val intentToAppstore = if (options.storeType == StoreType.GOOGLEPLAY) IntentHelper.createIntentForGooglePlay(context) else IntentHelper.createIntentForAmazonAppstore(context)
            context.startActivity(intentToAppstore)
            PreferenceHelper.setAgreeShowDialog(context, false)
            listener?.onClickButton(which)
        }
        if (options.shouldShowNeutralButton()) {
            builder.setNeutralButton(options.getNeutralText(context)) { dialog, which ->
                PreferenceHelper.setRemindInterval(context)
                listener?.onClickButton(which)
            }
        }
        if (options.shouldShowNegativeButton()) {
            builder.setNegativeButton(options.getNegativeText(context)) { dialog, which ->
                PreferenceHelper.setAgreeShowDialog(context, false)
                listener?.onClickButton(which)
            }
        }
        return builder.create()
    }
}