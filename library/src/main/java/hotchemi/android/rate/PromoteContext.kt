package hotchemi.android.rate

sealed class PromoteContext {

    object LaunchCount : PromoteContext()

    object ElapsedTimeAfterInstall : PromoteContext()

    object ScheduledReminder : PromoteContext()

    data class Custom(
            val type: String
    ) : PromoteContext()
}