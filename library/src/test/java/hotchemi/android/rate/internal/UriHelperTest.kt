package hotchemi.android.rate.internal

import com.google.common.truth.Truth.assertThat
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

    @Test
    fun `getAmazonAppstore should append non-null package name to id param`() {
        val actual = UriHelper.getAmazonAppstore("com.example.app").toString()

        assertThat(actual).isEqualTo("amzn://apps/android?p=com.example.app")
    }
}