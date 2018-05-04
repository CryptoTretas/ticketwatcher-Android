package org.decred.ticket.future.association

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import org.decred.ticket.R
import org.decred.ticket.future.splash.SplashContract
import javax.inject.Inject

class AssociationActivity : DaggerAppCompatActivity(), AssociationContract.View {

    @Inject
    lateinit var presenter: AssociationContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_association)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }


    override fun isLogged() {
    }

    override fun newLogin() {
    }


}