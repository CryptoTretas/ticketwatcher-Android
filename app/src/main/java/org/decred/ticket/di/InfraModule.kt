package org.decred.ticket.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import org.decred.ticket.DAO.TicketInformation
import org.decred.ticket.DAO.UserPreference
import org.decred.ticket.data.Api
import org.decred.ticket.util.DeliveryTicket
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

    @Provides
    @Singleton
    fun providesTicketInformation(api: Api, userPreference: UserPreference, publishSubject: PublishSubject<DeliveryTicket>): TicketInformation = TicketInformation(api, userPreference, publishSubject)
}
