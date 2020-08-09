package hotchemi.android.rate.internal

import android.app.Activity
import hotchemi.android.rate.*
import java.lang.ref.WeakReference
import java.util.*

internal class AppRateActionImpl(
        activity: Activity,
        private val state: AppRateState,
        private val option: AppRateOption
) : AppRateAction {
    private val activityRef: WeakReference<Activity?> = WeakReference(activity)

    override fun navigateToReview() {
        val activity = requireNotNull(activityRef.get()) {
            debugLog { "activity reference has gone" }
            return
        }

        with(state) {
            promotionState = PromotionState.ACCEPTED
            hasShownPromotionDialog = true
        }

        when (option.reviewType) {
            ReviewType.GooglePlay -> {
                activity.startActivity(IntentHelper.createIntentForGooglePlay(activity))
            }
            ReviewType.Amazon -> {
                activity.startActivity(IntentHelper.createIntentForAmazonAppstore(activity))
            }
            else -> {
                debugLog { "do nothing because developers will handle this flow by themselves" }
            }
        }
    }

    override fun scheduleReminder() {
        with(state) {
            remindSelectedDate = Date()
        }
    }

    override fun denyReview() {
        with(state) {
            promotionState = PromotionState.DENIED
            hasShownPromotionDialog = true
        }
    }
}