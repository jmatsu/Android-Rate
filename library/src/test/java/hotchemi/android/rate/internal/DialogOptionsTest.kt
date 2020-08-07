package hotchemi.android.rate.internal

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import hotchemi.android.rate.StoreType
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(qualifiers = "en")
@RunWith(RobolectricTestRunner::class)
class DialogOptionsTest {
    internal lateinit var options: DialogOptions

    private val context: Context
        get() = ApplicationProvider.getApplicationContext()

    @Before
    fun before() {
        options = DialogOptions()
    }

    @Test
    fun `cancelable`() {
        "false by default".run {
            assertThat(options.cancelable).isFalse()
        }
    }

    @Test
    fun `storeType`() {
        "GooglePlay by default".run {
            assertThat(options.storeType).isEqualTo(StoreType.GooglePlay)
        }
    }

    @Test
    fun `shouldShowNeutralButton and setShowNeutralButton`() {
        "true by default".run {
            assertThat(options.shouldShowNeutralButton()).isTrue()
        }

        "change to false".run {
            options.setShowNeutralButton(false)
            assertThat(options.shouldShowNeutralButton()).isFalse()
        }

        "change to true".run {
            options.setShowNeutralButton(true)
            assertThat(options.shouldShowNeutralButton()).isTrue()
        }
    }

    @Test
    fun `shouldShowNegativeButton and setShowNegativeButton`() {
        "true by default".run {
            assertThat(options.shouldShowNegativeButton()).isTrue()
        }

        "change to false".run {
            options.setShowNegativeButton(false)
            assertThat(options.shouldShowNegativeButton()).isFalse()
        }

        "change to true".run {
            options.setShowNegativeButton(true)
            assertThat(options.shouldShowNegativeButton()).isTrue()
        }
    }

    @Test
    fun `shouldShowTitle and setShowTitle`() {
        "true by default".run {
            assertThat(options.shouldShowTitle()).isTrue()
        }

        "change to false".run {
            options.setShowTitle(false)
            assertThat(options.shouldShowTitle()).isFalse()
        }

        "change to true".run {
            options.setShowTitle(true)
            assertThat(options.shouldShowTitle()).isTrue()
        }
    }

    @Test
    fun `getTitleText and setTitleText`() {
        "default resource".run {
            assertThat(options.getTitleText(context = context)).isEqualTo("Rate this app")
        }

        "change to the given string".run {
            options.setTitleText("hello")
            assertThat(options.getTitleText(context = context)).isEqualTo("hello")
        }

        "back to the default".run {
            options.setTitleText(null)
            assertThat(options.getTitleText(context = context)).isEqualTo("Rate this app")
        }
    }

    @Test
    fun `getMessageText and setMessageText`() {
        "default resource".run {
            assertThat(options.getMessageText(context = context)).isEqualTo("If you enjoy playing this app, would you mind taking a moment to rate it? It won't take more than a minute. Thanks for your support!")
        }

        "change to the given string".run {
            options.setMessageText("hello")
            assertThat(options.getMessageText(context = context)).isEqualTo("hello")
        }

        "back to the default".run {
            options.setMessageText(null)
            assertThat(options.getMessageText(context = context)).isEqualTo("If you enjoy playing this app, would you mind taking a moment to rate it? It won't take more than a minute. Thanks for your support!")
        }
    }

    @Test
    fun `getPositiveText and setPositiveText`() {
        "default resource".run {
            assertThat(options.getPositiveText(context = context)).isEqualTo("Rate It Now")
        }

        "change to the given string".run {
            options.setPositiveText("hello")
            assertThat(options.getPositiveText(context = context)).isEqualTo("hello")
        }

        "back to the default".run {
            options.setPositiveText(null)
            assertThat(options.getPositiveText(context = context)).isEqualTo("Rate It Now")
        }
    }

    @Test
    fun `getNeutralText and setNeutralText`() {
        "default resource".run {
            assertThat(options.getNeutralText(context = context)).isEqualTo("Remind Me Later")
        }

        "change to the given string".run {
            options.setNeutralText("hello")
            assertThat(options.getNeutralText(context = context)).isEqualTo("hello")
        }

        "back to the default".run {
            options.setNeutralText(null)
            assertThat(options.getNeutralText(context = context)).isEqualTo("Remind Me Later")
        }
    }

    @Test
    fun `getNegativeText and setNegativeText`() {
        "default resource".run {
            assertThat(options.getNegativeText(context = context)).isEqualTo("No, Thanks")
        }

        "change to the given string".run {
            options.setNegativeText("hello")
            assertThat(options.getNegativeText(context = context)).isEqualTo("hello")
        }

        "back to the default".run {
            options.setNegativeText(null)
            assertThat(options.getNegativeText(context = context)).isEqualTo("No, Thanks")
        }
    }
}