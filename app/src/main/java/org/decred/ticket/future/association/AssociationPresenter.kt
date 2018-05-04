package org.decred.ticket.future.association

import org.decred.ticket.DAO.UserPreference
import org.decred.ticket.future.splash.SplashContract
import javax.inject.Inject

class AssociationPresenter @Inject constructor(
        private val view: AssociationContract.View,
        private val userPreference: UserPreference
) : AssociationContract.Presenter {

    override fun onStart(){

    }

}