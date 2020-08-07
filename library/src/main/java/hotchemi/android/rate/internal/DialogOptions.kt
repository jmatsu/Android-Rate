package hotchemi.android.rate.internal

import android.content.Context
import android.view.View
import hotchemi.android.rate.OnClickButtonListener
import hotchemi.android.rate.R
import hotchemi.android.rate.StoreType
import java.lang.ref.Reference
import java.lang.ref.WeakReference

internal class DialogOptions {
    private var showNeutralButton: Boolean = true
    private var showNegativeButton: Boolean = true
    private var showTitle: Boolean = true
    var cancelable: Boolean = false
    var storeType: StoreType = StoreType.GooglePlay

    // todo annotation
    var titleResId: Int = R.string.rate_dialog_title
    var messageResId: Int = R.string.rate_dialog_message
    var textPositiveResId: Int = R.string.rate_dialog_ok
    var textNeutralResId: Int = R.string.rate_dialog_cancel
    var textNegativeResId: Int = R.string.rate_dialog_no

    private var titleText: String? = null
    private var messageText: String? = null
    private var positiveText: String? = null
    private var neutralText: String? = null
    private var negativeText: String? = null

    var view: View? = null

    private var listener: Reference<OnClickButtonListener?>? = null

    fun shouldShowNeutralButton(): Boolean {
        return showNeutralButton
    }

    fun setShowNeutralButton(showNeutralButton: Boolean) {
        this.showNeutralButton = showNeutralButton
    }

    fun shouldShowNegativeButton(): Boolean {
        return showNegativeButton
    }

    fun setShowNegativeButton(showNegativeButton: Boolean) {
        this.showNegativeButton = showNegativeButton
    }

    fun shouldShowTitle(): Boolean {
        return showTitle
    }

    fun setShowTitle(showTitle: Boolean) {
        this.showTitle = showTitle
    }

    fun getListener(): OnClickButtonListener? = listener?.get()

    fun setListener(listener: OnClickButtonListener?) {
        this.listener = WeakReference(listener)
    }

    fun getTitleText(context: Context): String =
            titleText ?: context.getString(titleResId)

    fun setTitleText(titleText: String?) {
        this.titleText = titleText
    }

    fun getMessageText(context: Context): String =
            messageText ?: context.getString(messageResId)

    fun setMessageText(messageText: String?) {
        this.messageText = messageText
    }

    fun getPositiveText(context: Context): String =
            positiveText ?: context.getString(textPositiveResId)

    fun setPositiveText(positiveText: String?) {
        this.positiveText = positiveText
    }

    fun getNeutralText(context: Context): String =
            neutralText ?: context.getString(textNeutralResId)

    fun setNeutralText(neutralText: String?) {
        this.neutralText = neutralText
    }

    fun getNegativeText(context: Context): String =
            negativeText ?: context.getString(textNegativeResId)

    fun setNegativeText(negativeText: String?) {
        this.negativeText = negativeText
    }
}