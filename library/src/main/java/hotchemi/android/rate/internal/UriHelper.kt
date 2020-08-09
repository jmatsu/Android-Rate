package hotchemi.android.rate.internal

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri

internal object UriHelper {
    private const val GOOGLE_PLAY = "https://play.google.com/store/apps/details"
    private const val AMAZON_APPSTORE = "amzn://apps/android"

    fun getGooglePlay(packageName: String): Uri {
        return Uri.parse(GOOGLE_PLAY).buildUpon().appendQueryParameter("id", packageName).build()
    }

    fun getAmazonAppstore(packageName: String): Uri {
        return Uri.parse(AMAZON_APPSTORE).buildUpon().appendQueryParameter("p", packageName).build()
    }

    fun isPackageExists(context: Context, targetPackage: String): Boolean {
        // FIXME add queries tag to support Android 11's package visibility changes
        return try {
            context.packageManager.getPackageInfo(targetPackage, 0) != null
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}