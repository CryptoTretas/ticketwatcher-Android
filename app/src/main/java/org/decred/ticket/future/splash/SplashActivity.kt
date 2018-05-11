package org.decred.ticket.future.splash

import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import org.decred.ticket.R
import org.decred.ticket.future.association.AssociationActivity
import org.decred.ticket.future.home.HomeActivity.Companion.getCallingIntent
import javax.inject.Inject


class SplashActivity : DaggerAppCompatActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    private fun clearNotification() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()
    }

    override fun onStart() {
        presenter.onStart()
        clearNotification()
        super.onStart()
    }


    override fun isLogged() {
        val intent = getCallingIntent(this)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun newLogin() {
        val intent = Intent(this, AssociationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }


}