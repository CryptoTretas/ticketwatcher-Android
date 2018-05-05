package org.decred.ticket.future.home

import dagger.Module
import dagger.Provides
import org.decred.ticket.DAO.UserPreference

@Module
class HomeModule {

    @Provides
    fun providesHomeActivity(activity: HomeActivity):HomeContract.View = activity


    @Provides
    fun providesHomePresenter(activity: HomeActivity,userPreference: UserPreference):HomeContract.Presenter = HomePresenter(activity,userPreference)

}