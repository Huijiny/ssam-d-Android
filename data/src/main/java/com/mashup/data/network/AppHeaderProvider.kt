package com.mashup.data.network

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Header와 관련된 로직을 관리합니다.
 */
class AppHeaderProvider @Inject constructor(
    private val context: Context
) : HttpHeaderProvider {
    private val preferences: SharedPreferences =
        context.getSharedPreferences(LOGIN_PREFERENCE, Context.MODE_PRIVATE)

    override fun getToken(): String {
        return preferences.getString(JWT, "") ?: ""
    }

    override fun saveToken(token: String?) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val LOGIN_PREFERENCE = "LOGIN_PREFERENCE"
        private const val JWT = "JWT"
    }
}