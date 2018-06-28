package com.zhukovme.flickrclient.data

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Michael Zhukov on 28.06.2018.
 * email: zhukovme@gmail.com
 */
@SuppressLint("CommitPrefEdits")
class PrefHelper(context: Context) {

    companion object {
        private const val PREF_FILE_NAME = "filckr_client_prefs"
        private const val IS_BLOCKING_DEF_VALUE = false
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    fun putString(key: String, value: String) {
        putString(key, value, IS_BLOCKING_DEF_VALUE)
    }

    fun putString(key: String, value: String, isBlocking: Boolean) {
        commit(prefs.edit().putString(key, value), isBlocking)
    }

    fun putStringSet(key: String, value: Set<String>) {
        putStringSet(key, value, IS_BLOCKING_DEF_VALUE)
    }

    fun putStringSet(key: String, value: Set<String>, isBlocking: Boolean) {
        commit(prefs.edit().putStringSet(key, value), isBlocking)
    }

    fun putInt(key: String, value: Int) {
        putInt(key, value, IS_BLOCKING_DEF_VALUE)
    }

    fun putInt(key: String, value: Int, isBlocking: Boolean) {
        commit(prefs.edit().putInt(key, value), isBlocking)
    }

    fun putLong(key: String, value: Long) {
        putLong(key, value, IS_BLOCKING_DEF_VALUE)
    }

    fun putLong(key: String, value: Long, isBlocking: Boolean) {
        commit(prefs.edit().putLong(key, value), isBlocking)
    }

    fun putFloat(key: String, value: Float) {
        putFloat(key, value, IS_BLOCKING_DEF_VALUE)
    }

    fun putFloat(key: String, value: Float, isBlocking: Boolean) {
        commit(prefs.edit().putFloat(key, value), isBlocking)
    }

    fun putBoolean(key: String, value: Boolean) {
        putBoolean(key, value, IS_BLOCKING_DEF_VALUE)
    }

    fun putBoolean(key: String, value: Boolean, isBlocking: Boolean) {
        commit(prefs.edit().putBoolean(key, value), isBlocking)
    }

    fun getString(key: String, defValue: String): String = prefs.getString(key, defValue)

    fun getStringSet(key: String, defValue: Set<String>): Set<String> = prefs.getStringSet(key, defValue)

    fun getInt(key: String, defValue: Int): Int = prefs.getInt(key, defValue)

    fun getLong(key: String, defValue: Long): Long = prefs.getLong(key, defValue)

    fun getFloat(key: String, defValue: Float): Float = prefs.getFloat(key, defValue)

    fun getBoolean(key: String, defValue: Boolean): Boolean = prefs.getBoolean(key, defValue)

    fun remove(key: String) {
        remove(key, IS_BLOCKING_DEF_VALUE)
    }

    fun remove(key: String, isBlocking: Boolean) {
        commit(prefs.edit().remove(key), isBlocking)
    }

    fun clear() {
        clear(IS_BLOCKING_DEF_VALUE)
    }

    fun clear(isBlocking: Boolean) {
        commit(prefs.edit().clear(), isBlocking)
    }

    private fun commit(editor: SharedPreferences.Editor, isBlocking: Boolean) {
        if (isBlocking) {
            editor.commit()
        } else {
            editor.apply()
        }
    }
}
