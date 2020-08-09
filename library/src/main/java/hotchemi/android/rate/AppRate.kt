package hotchemi.android.rate

import android.app.Activity
import android.content.Context
import hotchemi.android.rate.internal.*
import java.util.*
import java.util.concurrent.TimeUnit

object AppRate {
    fun initialize(context: Context, reviewType: ReviewType, debug: Boolean) {
        if (_state != null) {
            debugLog { "AppRate has already been initialized" }
            return
        }

        val preferences = if (debug) {
            MemorySharedPreferences()
        } else {
            PreferenceHelper.getPreferences(context)
        }

        synchronized(AppRate) {
            if (_state != null) {
                return
            }

            _state = AppRateState(preferences)
            _option = AppRateOption(reviewType)
        }

        debugLog { "AppRate has been initialized (debug = ${debug})" }
    }

    private var _state: AppRateState? = null
    private var _option: AppRateOption? = null

    val isInitialized: Boolean
        get() = _state != null

    val option: AppRateOption
        get() = requireNotNull(_option) { "AppRate must be initialized first" }

    val state: AppRateState
        get() = requireNotNull(_state) { "AppRate must be initialized first" }

    val shouldShowRateDialog: Boolean
        get() = promoteContext != null

    private val promoteContext: PromoteContext?
        get() {
            if (state.hasShownPromotionDialog) {
                return null
            }

            val now = Date()

            if (!option.disableLaunchCountPromotion &&
                    state.launchTimes >= option.launchCountThreshold) {
                return PromoteContext.LaunchCount
            }

            if (!option.disableElapsedTimeAfterInstallPromotion &&
                    isOverDate(now, targetDate = state.installedDate, threshold = option.elapsedMillisAfterInstall, thresholdUnit = TimeUnit.MILLISECONDS)) {
                return PromoteContext.ElapsedTimeAfterInstall
            }

            if (!option.disableRemindIntervalPromotion &&
                    isOverDate(now, targetDate = state.remindSelectedDate, threshold = option.remindIntervalMillis, thresholdUnit = TimeUnit.MILLISECONDS)) {
                return PromoteContext.ScheduledReminder
            }

            return null
        }

    fun markAsLaunched() {
        if (state.installedDate == null) {
            state.installedDate = Date()
        }

        state.launchTimes = state.launchTimes + 1
    }

    fun showDialogIfMeetsConditions(activity: Activity): Boolean {
        if (!shouldShowRateDialog) {
            return false
        }

        if (!activity.isFinishing) {
            val factory = option.promotionDialogFactory
                    ?: BuiltinPromotionDialogFactory(option = option.builtinDialogOption)
            factory.create(activity, AppRateActionImpl(activity, state, option))
        }

        return true
    }

    @Suppress("SameParameterValue")
    private fun isOverDate(base: Date, targetDate: Date?, threshold: Long, thresholdUnit: TimeUnit): Boolean {
        if (targetDate == null) {
            return false
        }

        return base.time - targetDate.time >= thresholdUnit.toMillis(threshold)
    }
}