package org.decred.ticket.future.splash

import org.decred.ticket.DAO.UserPreference
import javax.inject.Inject


class SplashPresenter @Inject constructor(
        private val view: SplashContract.View,
        private val userPreference: UserPreference
) : SplashContract.Presenter {

    override fun onStart(){
        if(userPreference.isLogged()){
            view.isLogged()
        }
        else{
            view.newLogin()
        }
    }

}