package org.decred.ticket.future.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.decred.ticket.R
import org.decred.ticket.future.home.fragment.BottomBarPagerAdapter

class HomeActivity : DaggerAppCompatActivity(), HomeContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setBottomBarNavigation()
        setPager()
    }

    private fun setPager() {
        view_pager.adapter = BottomBarPagerAdapter(this, supportFragmentManager)
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                bottom_navigation.menu.getItem(position).isChecked = true
                when (position) {
                    0 -> toolbar.title = getString(R.string.extract)
                    1 -> toolbar.title = getString(R.string.balance)
                    2 -> toolbar.title = getString(R.string.settings)
                }
            }

        })
    }

    private fun setBottomBarNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.menu_extract -> {
                        view_pager.currentItem = 0
                        toolbar.title = getString(R.string.extract)
                    }
                    R.id.menu_balance -> {
                        view_pager.currentItem = 1
                        toolbar.title = getString(R.string.balance)
                    }
                    R.id.menu_settings -> {
                        view_pager.currentItem = 2
                        toolbar.title = getString(R.string.settings)
                    }
                }
                return true;
            }
        })


    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}