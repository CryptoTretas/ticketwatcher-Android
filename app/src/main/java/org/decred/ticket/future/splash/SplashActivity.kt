package org.decred.ticket.future.splash

import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import org.decred.ticket.R
import org.decred.ticket.future.association.AssociationActivity
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
        val intent = Intent(this, AssociationActivity::class.java)
        startActivity(intent)
    }

    override fun newLogin() {
        val intent = Intent(this, AssociationActivity::class.java)
        startActivity(intent)
    }


}