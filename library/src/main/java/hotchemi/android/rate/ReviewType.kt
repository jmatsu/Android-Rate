package hotchemi.android.rate

sealed class ReviewType {
    object GooglePlay : ReviewType()
    object Amazon : ReviewType()
    object NoOp: ReviewType()

    companion object {
        @JvmField
        val GOOGLEPLAY = GooglePlay

        @JvmField
        val AMAZON = Amazon
    }
}