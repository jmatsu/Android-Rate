package hotchemi.android.rate

sealed class StoreType {
    object GooglePlay : StoreType()
    object Amazon : StoreType()

    companion object {
        @Deprecated("Use StoreType#GooglePlay instead", level = DeprecationLevel.WARNING, replaceWith = ReplaceWith("GooglePlay"))
        @JvmField
        val GOOGLEPLAY = GooglePlay

        @Deprecated("Use StoreType#Amazon instead", level = DeprecationLevel.WARNING, replaceWith = ReplaceWith("Amazon"))
        @JvmField
        val AMAZON = Amazon
    }
}