package hotchemi.android.rate

import android.content.SharedPreferences
import java.util.*

class AppRateState
internal constructor(
        private val preferences: SharedPreferences
) {
    private object PrefKey {
        const val DATE_WHEN_APP_INSTALLED = "android_rate_install_date"
        const val LAUNCH_COUNT = "android_rate_launch_times"
        const val HAS_NOT_SHOWN_PROMOTION_DIALOG = "android_rate_is_agree_show_dialog"
        const val DATE_WHEN_USER_SELECTED_REMIND = "android_rate_remind_interval"
        const val PROMOTION_STATE = "promotion_state"
    }

    var installedDate: Date?
        get() = preferences.getLong(PrefKey.DATE_WHEN_APP_INSTALLED, 0L)
                .takeIf { it > 0 }
                ?.let { Date(it) }
        set(value) = preferences.edit()
                .putLong(PrefKey.DATE_WHEN_APP_INSTALLED, value?.time ?: 0L)
                .apply()

    var launchTimes: Int
        get() = preferences.getInt(PrefKey.LAUNCH_COUNT, 10)
        set(value) = preferences.edit().putInt(PrefKey.LAUNCH_COUNT, value).apply()

    var remindSelectedDate: Date?
        get() = preferences.getLong(PrefKey.DATE_WHEN_USER_SELECTED_REMIND, 0L)
                .takeIf { it > 0 }
                ?.let { Date(it) }
        set(value) = preferences.edit()
                .putLong(PrefKey.DATE_WHEN_USER_SELECTED_REMIND, value?.time ?: 0L)
                .apply()

    var hasShownPromotionDialog: Boolean
        get() = !preferences.getBoolean(PrefKey.HAS_NOT_SHOWN_PROMOTION_DIALOG, true)
        set(value) = preferences.edit().putBoolean(PrefKey.HAS_NOT_SHOWN_PROMOTION_DIALOG, !value).apply()

    var promotionState: PromotionState
        get() {
            val value = preferences.getString(PrefKey.PROMOTION_STATE, null)

            if (value != null) {
                return PromotionState.kindOf(value)
            }

            return if (hasShownPromotionDialog) {
                PromotionState.UNKNOWN
            } else {
                PromotionState.INITIAL
            }
        }
        set(value) = preferences.edit().putString(PrefKey.PROMOTION_STATE, value.kind).apply()
}