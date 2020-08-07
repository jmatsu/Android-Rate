package hotchemi.android.rate;

import android.net.Uri;
import android.test.AndroidTestCase;

import hotchemi.android.rate.internal.UriHelper;

/**
 * Unit test for {@link UriHelper}
 */
public class UriHelperTest extends AndroidTestCase {

    public void testGetGooglePlayUri() {
        {
            Uri uri = UriHelper.INSTANCE.getGooglePlay("");
            assertEquals(uri.toString(), "https://play.google.com/store/apps/details?id=");
        }
        {
            final String packageName = "hotchemi.android.rate";
            Uri uri = UriHelper.INSTANCE.getGooglePlay(packageName);
            assertEquals(uri.toString(), "https://play.google.com/store/apps/details?id=" + packageName);
        }
    }
}