package hotchemi.android.rate.internal

import android.content.Context
import android.content.Intent

internal object IntentHelper {
    private const val GOOGLE_PLAY_PACKAGE_NAME = "com.android.vending"
    fun createIntentForGooglePlay(context: Context): Intent {
        val packageName = context.packageName
        val intent = Intent(Intent.ACTION_VIEW, UriHelper.getGooglePlay(packageName))
        if (UriHelper.isPackageExists(context, GOOGLE_PLAY_PACKAGE_NAME)) {
            intent.setPackage(GOOGLE_PLAY_PACKAGE_NAME)
        }
        return intent
    }

    fun createIntentForAmazonAppstore(context: Context): Intent {
        val packageName = context.packageName
        return Intent(Intent.ACTION_VIEW, UriHelper.getAmazonAppstore(packageName))
    }
}