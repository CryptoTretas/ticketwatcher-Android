package org.decred.ticket.future.splash

import dagger.Module
import dagger.Provides
import org.decred.ticket.DAO.UserPreference


@Module
class SplashModule {

    @Provides
    fun providesSplashActivity(activity: SplashActivity): SplashContract.View = activity


    @Provides
    fun providesSplashPresenter(activity: SplashActivity, userPreference: UserPreference): SplashContract.Presenter = SplashPresenter(activity, userPreference)


}