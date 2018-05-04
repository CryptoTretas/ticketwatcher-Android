package org.decred.ticket

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import org.decred.ticket.di.ApplicationComponent

class TicketApplication : DaggerApplication() {


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