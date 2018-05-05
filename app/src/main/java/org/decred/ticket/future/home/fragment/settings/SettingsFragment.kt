package org.decred.ticket.future.home.fragment.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import org.decred.ticket.R

class SettingsFragment : DaggerFragment(), SettingsContract.View {

    private lateinit var fragmentView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentView = inflater.inflate(R.layout.activity_home, container, false)

        return fragmentView
    }




}
