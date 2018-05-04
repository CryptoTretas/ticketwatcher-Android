package org.decred.ticket.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import org.decred.ticket.DAO.UserPreference
import javax.inject.Singleton

@Module
@Singleton
class InfraModule {

    @Provides
    fun provideUserPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences("DECRED_TICKET_WALLET", Context.MODE_PRIVATE)
    }

    @Provides
    fun providesUserPreference(prefs: SharedPreferences): UserPreference = UserPreference(prefs)
}
