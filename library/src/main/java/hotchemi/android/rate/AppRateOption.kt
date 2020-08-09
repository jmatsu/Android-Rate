package hotchemi.android.rate

import java.util.concurrent.TimeUnit

class AppRateOption
internal constructor(
        internal val reviewType: ReviewType
) {
    companion object {
        const val SHOW_TODAY: Long = 0L
    }

    internal var elapsedMillisAfterInstall: Long = 10L
        private set
    internal var remindIntervalMillis: Long = 1L
        private set
    internal var launchCountThreshold: Int = 10
        private set
    internal var disableElapsedTimeAfterInstallPromotion: Boolean = false
        private set
    internal var disableRemindIntervalPromotion: Boolean = false
        private set
    internal var disableLaunchCountPromotion: Boolean = false
        private set

    var promotionDialogFactory: PromotionDialogFactory? = null

    var builtinDialogOption: DefaultDialogOption = DefaultDialogOption()

    fun setElapsedTimeAfterInstall(value: Long, unit: TimeUnit) {
        require(value > SHOW_TODAY)

        elapsedMillisAfterInstall = unit.toMillis(value)
    }

    fun setRemindInterval(value: Long, unit: TimeUnit) {
        remindIntervalMillis = unit.toMillis(value)
    }

    fun setLaunchCountThreshold(value: Int) {
        launchCountThreshold = value
    }
}