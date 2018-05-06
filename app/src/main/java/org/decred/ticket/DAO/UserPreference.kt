package org.decred.ticket.DAO

import android.content.SharedPreferences


class UserPreference constructor(private val preferences: SharedPreferences) {

    fun saveWalletId(id: String) {
        preferences.edit()
                .putString(WALLET_ID, id.trim())
                .apply()
    }

    fun getWalletId(): String = preferences.getString(WALLET_ID, "")

    fun isLogged(): Boolean = preferences.contains(WALLET_ID)

    fun clear() {
        preferences.edit()
                .clear()
                .apply()
    }

    companion object {
        private const val WALLET_ID = "WALLET_ID"

    }
}
