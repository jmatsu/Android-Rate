package hotchemi.android.rate

import android.app.Activity
import android.app.Dialog

interface PromotionDialogFactory {
    fun create(activity: Activity, action: AppRateAction): Dialog
}