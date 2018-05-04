package org.decred.ticket.future.association

import dagger.Module
import dagger.Provides
import org.decred.ticket.DAO.UserPreference
import org.decred.ticket.future.splash.SplashActivity
import org.decred.ticket.future.splash.SplashContract
import org.decred.ticket.future.splash.SplashPresenter

@Module
class AssociationModule {

    @Provides
    fun providesAssociationActivity(activity: AssociationActivity): AssociationContract.View = activity


    @Provides
    fun providesAssociationPresenter(activity: AssociationActivity,
                                     userPreference: UserPreference): AssociationContract.Presenter = AssociationPresenter(activity, userPreference)

}