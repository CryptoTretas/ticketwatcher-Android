package org.decred.ticket.future.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import org.decred.ticket.R

class HomeActivity : DaggerAppCompatActivity(), HomeContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}