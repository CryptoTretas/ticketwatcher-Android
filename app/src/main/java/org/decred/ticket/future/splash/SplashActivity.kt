package org.decred.ticket.future.splash

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import org.decred.ticket.R
import javax.inject.Inject


class SplashActivity: DaggerAppCompatActivity(), SplashContract.View{


    @Inject
    lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        presenter.onStart()
        super.onStart()
    }


    override fun isLogged() {
    }

    override fun newLogin() {
    }


}