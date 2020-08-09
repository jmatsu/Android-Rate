package hotchemi.android.rate

interface AppRateAction {
    fun navigateToReview()

    fun scheduleReminder()

    fun denyReview()
}