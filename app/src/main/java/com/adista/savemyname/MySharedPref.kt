package com.adista.savemyname

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class MySharedPref(context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        PREF_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveName(value: String) {
        with(sharedPreferences.edit()) {
            putString(KEY_NAME, value)
            apply()
        }
    }

    fun getName(): String? {
        return sharedPreferences.getString(KEY_NAME, null)
    }

    companion object {
        const val PREF_NAME = "my_pref"
        const val KEY_NAME = "name"
    }
}