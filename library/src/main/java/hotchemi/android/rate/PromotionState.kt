package hotchemi.android.rate

enum class PromotionState(val kind: String) {
    INITIAL("initial"),
    ACCEPTED("accepted"),
    DENIED("denied"),
    UNKNOWN("unknown");

    companion object {
        @JvmStatic
        fun kindOf(kind: String): PromotionState {
            return values().firstOrNull { it.kind == kind } ?: UNKNOWN
        }
    }
}