package hotchemi.android.rate

import android.content.Context

class TextResource(
        var resourceId: Int,
        var text: String? = null
) {
    fun string(context: Context): String {
        return text ?: context.getString(resourceId)
    }
}
