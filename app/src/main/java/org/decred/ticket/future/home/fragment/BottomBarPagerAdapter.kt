package org.decred.ticket.future.home.fragment

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import org.decred.ticket.enum.EnumMenuBottomBar
import org.decred.ticket.future.home.fragment.settings.SettingsFragment

class BottomBarPagerAdapter(
        private val context: Context,
        fragment: FragmentManager
) : FragmentStatePagerAdapter(fragment) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SettingsFragment()
            1 -> SettingsFragment()
            else -> SettingsFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        var title = when (position) {
            0 -> EnumMenuBottomBar.EXTRACT.type
            1 -> EnumMenuBottomBar.BALANCE.type
            else -> EnumMenuBottomBar.SETTINGS.type
        }
        return context.getString(title)
    }

    override fun getCount(): Int = EnumMenuBottomBar.values().size //To change body of created functions use File | Settings | File Templates.

}