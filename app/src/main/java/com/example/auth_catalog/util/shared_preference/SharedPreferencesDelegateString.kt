package com.example.auth_catalog.util.shared_preference

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Shared preferences [String] delegate to Kotlin property.
 */
fun SharedPreferences.string(
    defaultValue: String?,
    key: String
): ReadWriteProperty<Any, String?> = object : ReadWriteProperty<Any, String?> {

    /** Obtains the shared preferences value. */
    override fun getValue(thisRef: Any, property: KProperty<*>): String? =
        getString(key, defaultValue)

    /** Save the shared preferences value. */
    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        edit().putString(key, value).apply()
    }

}
