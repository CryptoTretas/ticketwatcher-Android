package org.decred.ticket.future.splash

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import org.decred.ticket.DAO.UserPreference
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SplashPresenter @Inject constructor(
        private val view: SplashContract.View,
        private val userPreference: UserPreference
) : SplashContract.Presenter {

    override fun onStart() {
        Single.just(userPreference.isLogged()).delay(500, TimeUnit.MILLISECONDS)
                .subscribe { t1: Boolean, _ ->
                    if (t1) view.isLogged()
                    else view.newLogin()
                }
    }

}