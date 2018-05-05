package org.decred.ticket

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import org.decred.ticket.di.ApplicationComponent
import org.decred.ticket.di.DaggerApplicationComponent
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class TicketApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.asTree()
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Inconsolata-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        applicationComponent = DaggerApplicationComponent.builder()
                .application(this)
                .build()

        applicationComponent.inject(this)

        return applicationComponent
    }

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }
}
