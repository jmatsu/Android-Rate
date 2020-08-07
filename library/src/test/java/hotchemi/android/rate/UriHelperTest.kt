package hotchemi.android.rate

import com.google.common.truth.Truth.assertThat
import hotchemi.android.rate.internal.UriHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UriHelperTest {

    @Test
    fun `getGooglePlay should append non-null package name to id param`() {
        val actual = UriHelper.getGooglePlay("com.example.app").toString()

        assertThat(actual).isEqualTo("https://play.google.com/store/apps/details?id=com.example.app")
    }
}