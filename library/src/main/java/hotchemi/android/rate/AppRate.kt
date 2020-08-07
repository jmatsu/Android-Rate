package hotchemi.android.rate

import android.app.Activity
import android.content.Context
import android.view.View
import java.util.*

class AppRate private constructor(context: Context) {
    private val context: Context
    private val options = DialogOptions()
    private var installDate = 10
    private var launchTimes = 10
    private var remindInterval = 1
    var isDebug = false
        private set

    fun setLaunchTimes(launchTimes: Int): AppRate {
        this.launchTimes = launchTimes
        return this
    }

    fun setInstallDays(installDate: Int): AppRate {
        this.installDate = installDate
        return this
    }

    fun setRemindInterval(remindInterval: Int): AppRate {
        this.remindInterval = remindInterval
        return this
    }

    fun setShowLaterButton(isShowNeutralButton: Boolean): AppRate {
        options.setShowNeutralButton(isShowNeutralButton)
        return this
    }

    fun setShowNeverButton(isShowNeverButton: Boolean): AppRate {
        options.setShowNegativeButton(isShowNeverButton)
        return this
    }

    fun setShowTitle(isShowTitle: Boolean): AppRate {
        options.setShowTitle(isShowTitle)
        return this
    }

    fun clearAgreeShowDialog(): AppRate {
        PreferenceHelper.setAgreeShowDialog(context, true)
        return this
    }

    fun clearSettingsParam(): AppRate {
        PreferenceHelper.setAgreeShowDialog(context, true)
        PreferenceHelper.clearSharedPreferences(context)
        return this
    }

    fun setAgreeShowDialog(clear: Boolean): AppRate {
        PreferenceHelper.setAgreeShowDialog(context, clear)
        return this
    }

    fun setView(view: View?): AppRate {
        options.view = view
        return this
    }

    fun setOnClickButtonListener(listener: OnClickButtonListener?): AppRate {
        options.setListener(listener)
        return this
    }

    fun setTitle(resourceId: Int): AppRate {
        options.titleResId = resourceId
        return this
    }

    fun setTitle(title: String?): AppRate {
        options.setTitleText(title)
        return this
    }

    fun setMessage(resourceId: Int): AppRate {
        options.messageResId = resourceId
        return this
    }

    fun setMessage(message: String?): AppRate {
        options.setMessageText(message)
        return this
    }

    fun setTextRateNow(resourceId: Int): AppRate {
        options.textPositiveResId = resourceId
        return this
    }

    fun setTextRateNow(positiveText: String?): AppRate {
        options.setPositiveText(positiveText)
        return this
    }

    fun setTextLater(resourceId: Int): AppRate {
        options.textNeutralResId = resourceId
        return this
    }

    fun setTextLater(neutralText: String?): AppRate {
        options.setNeutralText(neutralText)
        return this
    }

    fun setTextNever(resourceId: Int): AppRate {
        options.textNegativeResId = resourceId
        return this
    }

    fun setTextNever(negativeText: String?): AppRate {
        options.setNegativeText(negativeText)
        return this
    }

    fun setCancelable(cancelable: Boolean): AppRate {
        options.cancelable = cancelable
        return this
    }

    fun setStoreType(appstore: StoreType): AppRate {
        options.storeType = appstore
        return this
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
        private get() = PreferenceHelper.getLaunchTimes(context) >= launchTimes

    private val isOverInstallDate: Boolean
        private get() = isOverDate(PreferenceHelper.getInstallDate(context), installDate)

    private val isOverRemindDate: Boolean
        private get() = isOverDate(PreferenceHelper.getRemindInterval(context), remindInterval)

    fun setDebug(isDebug: Boolean): AppRate {
        this.isDebug = isDebug
        return this
    }

    companion object {
        private var singleton: AppRate? = null
        @JvmStatic
        fun with(context: Context): AppRate? {
            if (singleton == null) {
                synchronized(AppRate::class.java) {
                    if (singleton == null) {
                        singleton = AppRate(context)
                    }
                }
            }
            return singleton
        }

        @JvmStatic
        fun showRateDialogIfMeetsConditions(activity: Activity): Boolean {
            val isMeetsConditions = singleton!!.isDebug || singleton!!.shouldShowRateDialog()
            if (isMeetsConditions) {
                singleton!!.showRateDialog(activity)
            }
            return isMeetsConditions
        }

        private fun isOverDate(targetDate: Long, threshold: Int): Boolean {
            return Date().time - targetDate >= threshold * 24 * 60 * 60 * 1000
        }
    }

    init {
        this.context = context.applicationContext
    }
}