package hotchemi.android.rate.internal

import android.app.Dialog
import android.content.Context
import hotchemi.android.rate.StoreType

internal object DialogManager {
    fun create(context: Context, options: DialogOptions): Dialog {
        return Utils.getDialogBuilder(context).apply {
            setMessage(options.getMessageText(context))

            if (options.shouldShowTitle()) {
                setTitle(options.getTitleText(context))
            }

            setCancelable(options.cancelable)

            options.view?.also { view ->
                setView(view)
            }

            val listener = options.getListener()

            setPositiveButton(options.getPositiveText(context)) { _, which ->
                val intentToAppstore = when (options.storeType) {
                    StoreType.GooglePlay -> IntentHelper.createIntentForGooglePlay(context)
                    StoreType.Amazon -> IntentHelper.createIntentForAmazonAppstore(context)
                }
                context.startActivity(intentToAppstore)
                PreferenceHelper.setAgreeShowDialog(context, false)
                listener?.onClickButton(which)
            }

            if (options.shouldShowNeutralButton()) {
                setNeutralButton(options.getNeutralText(context)) { _, which ->
                    PreferenceHelper.setRemindInterval(context)
                    listener?.onClickButton(which)
                }
            }

            if (options.shouldShowNegativeButton()) {
                setNegativeButton(options.getNegativeText(context)) { _, which ->
                    PreferenceHelper.setAgreeShowDialog(context, false)
                    listener?.onClickButton(which)
                }
            }
        }.create()
    }
}