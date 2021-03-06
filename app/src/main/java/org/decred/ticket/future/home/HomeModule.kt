package org.decred.ticket.future.home

import dagger.Module
import dagger.Provides
import org.decred.ticket.DAO.TicketInformation
import org.decred.ticket.DAO.UserPreference

@Module
class HomeModule {

    @Provides
    fun providesHomeActivity(activity: HomeActivity): HomeContract.View = activity


    @Provides
    fun providesHomePresenter(activity: HomeActivity,ticketInformation: TicketInformation): HomeContract.Presenter = HomePresenter(activity, ticketInformation)

}