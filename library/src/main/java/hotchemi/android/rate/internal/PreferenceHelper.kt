package hotchemi.android.rate.internal

import android.content.Context
import android.content.SharedPreferences
import java.util.*

internal object PreferenceHelper {
    private const val PREF_FILE_NAME = "android_rate_pref_file"
    private const val PREF_KEY_INSTALL_DATE = "android_rate_install_date"
    private const val PREF_KEY_LAUNCH_TIMES = "android_rate_launch_times"
    private const val PREF_KEY_IS_AGREE_SHOW_DIALOG = "android_rate_is_agree_show_dialog"
    private const val PREF_KEY_REMIND_INTERVAL = "android_rate_remind_interval"

    fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Clear data in shared preferences.<br></br>
     *
     * @param context context
     */
    fun clearSharedPreferences(context: Context) {
        getPreferences(context).editAndApply {
            remove(PREF_KEY_INSTALL_DATE)
            remove(PREF_KEY_LAUNCH_TIMES)
        }
    }

    /**
     * Set agree flag about show dialog.<br></br>
     * If it is false, rate dialog will never shown unless data is cleared.
     *
     * @param context context
     * @param isAgree agree with showing rate dialog
     */
    fun setAgreeShowDialog(context: Context, isAgree: Boolean) {
        getPreferences(context).editAndApply {
            putBoolean(PREF_KEY_IS_AGREE_SHOW_DIALOG, isAgree)
        }
    }

    fun getIsAgreeShowDialog(context: Context): Boolean {
        return getPreferences(context).getBoolean(PREF_KEY_IS_AGREE_SHOW_DIALOG, true)
    }

    fun setRemindInterval(context: Context) {
        getPreferences(context).editAndApply {
            remove(PREF_KEY_REMIND_INTERVAL)
            putLong(PREF_KEY_REMIND_INTERVAL, Date().time)
        }
    }

    fun getRemindInterval(context: Context): Long {
        return getPreferences(context).getLong(PREF_KEY_REMIND_INTERVAL, 0)
    }

    fun setInstallDate(context: Context) {
        getPreferences(context).editAndApply {
            putLong(PREF_KEY_INSTALL_DATE, Date().time)
        }
    }

    fun getInstallDate(context: Context): Long {
        return getPreferences(context).getLong(PREF_KEY_INSTALL_DATE, 0)
    }

    fun setLaunchTimes(context: Context, launchTimes: Int) {
        getPreferences(context).editAndApply {
            putInt(PREF_KEY_LAUNCH_TIMES, launchTimes)
        }
    }

    fun getLaunchTimes(context: Context): Int {
        return getPreferences(context).getInt(PREF_KEY_LAUNCH_TIMES, 0)
    }

    fun isFirstLaunch(context: Context): Boolean {
        return getPreferences(context).getLong(PREF_KEY_INSTALL_DATE, 0) == 0L
    }

    private fun SharedPreferences.editAndApply(op: SharedPreferences.Editor.() -> Unit) {
        edit().also(op).apply()
    }
}