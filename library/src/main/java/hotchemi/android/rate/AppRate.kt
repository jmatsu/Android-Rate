package hotchemi.android.rate

import android.app.Activity
import android.content.Context
import android.view.View
import hotchemi.android.rate.internal.DialogManager
import hotchemi.android.rate.internal.DialogOptions
import hotchemi.android.rate.internal.PreferenceHelper
import java.util.*

class AppRate private constructor(context: Context) {
    private val context: Context = context.applicationContext
    private val options = DialogOptions()
    private var installDate: Long = 10
    private var launchTimes: Int = 10
    private var remindInterval: Long = 1
    var isDebug = false
        private set

    fun setLaunchTimes(launchTimes: Int): AppRate = apply {
        this.launchTimes = launchTimes
    }

    fun setInstallDays(installDate: Long): AppRate = apply {
        this.installDate = installDate
    }

    fun setRemindInterval(remindInterval: Long): AppRate = apply {
        this.remindInterval = remindInterval
    }

    fun setShowLaterButton(isShowNeutralButton: Boolean): AppRate = apply {
        options.setShowNeutralButton(isShowNeutralButton)
    }

    fun setShowNeverButton(isShowNeverButton: Boolean): AppRate = apply {
        options.setShowNegativeButton(isShowNeverButton)
    }

    fun setShowTitle(isShowTitle: Boolean): AppRate = apply {
        options.setShowTitle(isShowTitle)
    }

    fun clearAgreeShowDialog(): AppRate = apply {
        PreferenceHelper.setAgreeShowDialog(context, true)
    }

    fun clearSettingsParam(): AppRate = apply {
        PreferenceHelper.setAgreeShowDialog(context, true)
        PreferenceHelper.clearSharedPreferences(context)
    }

    fun setAgreeShowDialog(clear: Boolean): AppRate = apply {
        PreferenceHelper.setAgreeShowDialog(context, clear)
    }

    fun setView(view: View?): AppRate = apply {
        options.view = view
    }

    fun setOnClickButtonListener(listener: OnClickButtonListener?): AppRate = apply {
        options.setListener(listener)
    }

    fun setTitle(resourceId: Int): AppRate = apply {
        options.titleResId = resourceId
    }

    fun setTitle(title: String?): AppRate = apply {
        options.setTitleText(title)
        return this
    }

    fun setMessage(resourceId: Int): AppRate = apply {
        options.messageResId = resourceId
    }

    fun setMessage(message: String?): AppRate = apply {
        options.setMessageText(message)
    }

    fun setTextRateNow(resourceId: Int): AppRate = apply {
        options.textPositiveResId = resourceId
    }

    fun setTextRateNow(positiveText: String?): AppRate = apply {
        options.setPositiveText(positiveText)
    }

    fun setTextLater(resourceId: Int): AppRate = apply {
        options.textNeutralResId = resourceId
    }

    fun setTextLater(neutralText: String?): AppRate = apply {
        options.setNeutralText(neutralText)
    }

    fun setTextNever(resourceId: Int): AppRate = apply {
        options.textNegativeResId = resourceId
    }

    fun setTextNever(negativeText: String?): AppRate = apply {
        options.setNegativeText(negativeText)
    }

    fun setCancelable(cancelable: Boolean): AppRate = apply {
        options.cancelable = cancelable
    }

    fun setStoreType(appstore: StoreType): AppRate = apply {
        options.storeType = appstore
    }

    fun monitor() {
        if (PreferenceHelper.isFirstLaunch(context)) {
            PreferenceHelper.setInstallDate(context)
        }
        PreferenceHelper.setLaunchTimes(context, PreferenceHelper.getLaunchTimes(context) + 1)
    }

    fun showRateDialog(activity: Activity) {
        if (!activity.isFinishing) {
            DialogManager.create(activity, options).show()
        }
    }

    fun shouldShowRateDialog(): Boolean {
        return PreferenceHelper.getIsAgreeShowDialog(context) &&
                isOverLaunchTimes &&
                isOverInstallDate &&
                isOverRemindDate
    }

    private val isOverLaunchTimes: Boolean
        get() = PreferenceHelper.getLaunchTimes(context) >= launchTimes

    private val isOverInstallDate: Boolean
        get() = isOverDate(PreferenceHelper.getInstallDate(context), installDate)

    private val isOverRemindDate: Boolean
        get() = isOverDate(PreferenceHelper.getRemindInterval(context), remindInterval)

    fun setDebug(isDebug: Boolean): AppRate = apply {
        this.isDebug = isDebug
    }

    companion object {
        private var singleton: AppRate? = null

        @JvmStatic
        fun with(context: Context): AppRate {
            if (singleton == null) {
                synchronized(AppRate::class.java) {
                    if (singleton == null) {
                        singleton = AppRate(context)
                    }
                }
            }

            return requireNotNull(singleton)
        }

        @JvmStatic
        fun showRateDialogIfMeetsConditions(activity: Activity): Boolean {
            val instance = requireNotNull(singleton) {
                return false
            }

            if (!instance.isDebug && !instance.shouldShowRateDialog()) {
                return false
            }

            instance.showRateDialog(activity)

            return true
        }

        private fun isOverDate(targetDate: Long, threshold: Long): Boolean {
            return Date().time - targetDate >= threshold * 24 * 60 * 60 * 1000
        }
    }

}