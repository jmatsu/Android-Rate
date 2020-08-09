package hotchemi.android.rate.internal

import android.content.SharedPreferences
import java.util.*
import java.util.concurrent.ConcurrentHashMap

internal class MemorySharedPreferences : SharedPreferences {
    private val cache: MutableMap<String, Any?> = ConcurrentHashMap()

    override fun contains(key: String): Boolean = key in cache

    override fun getBoolean(key: String, defValue: Boolean): Boolean = cache.getOrElse(key, defValue)

    override fun getInt(key: String, defValue: Int): Int = cache.getOrElse(key, defValue)

    override fun getAll(): Map<String, *> = Collections.unmodifiableMap(cache)

    override fun edit(): SharedPreferences.Editor = EditorImpl()

    override fun getLong(key: String, defValue: Long): Long = cache.getOrElse(key, defValue)

    override fun getFloat(key: String, defValue: Float): Float = cache.getOrElse(key, defValue)

    override fun getString(key: String, defValue: String?): String? = cache.getOrElse(key, defValue)

    override fun getStringSet(key: String, defValues: MutableSet<String>?): MutableSet<String>? = cache.getOrElse(key, defValues)

    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        error("do not use")
    }

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        error("do not use")
    }

    @Suppress("UNCHECKED_CAST")
    @Throws(ClassCastException::class)
    private fun <T> Map<String, *>.getOrElse(key: String, elseValue: T): T {
        return this[key] as T? ?: elseValue
    }

    private inner class EditorImpl : SharedPreferences.Editor {
        private val store: MutableMap<String, Any?> = ConcurrentHashMap()

        private var shouldClear: Boolean = false

        override fun clear(): SharedPreferences.Editor = apply {
            shouldClear = true
        }

        override fun putLong(key: String, value: Long): SharedPreferences.Editor = apply {
            store[key] = value
        }

        override fun putInt(key: String, value: Int): SharedPreferences.Editor = apply {
            store[key] = value
        }

        override fun remove(key: String): SharedPreferences.Editor = apply {
            store.remove(key)
        }

        override fun putBoolean(key: String, value: Boolean): SharedPreferences.Editor = apply {
            store[key] = value
        }

        override fun putStringSet(key: String, values: MutableSet<String>?): SharedPreferences.Editor = apply {
            store[key] = values
        }

        override fun putFloat(key: String, value: Float): SharedPreferences.Editor = apply {
            store[key] = value
        }

        override fun putString(key: String, value: String?): SharedPreferences.Editor = apply {
            store[key] = value
        }

        override fun apply() {
            cache.putAll(store)
        }

        override fun commit(): Boolean {
            cache.putAll(store)
            return true
        }
    }
}