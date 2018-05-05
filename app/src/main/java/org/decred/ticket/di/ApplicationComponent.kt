package org.decred.ticket.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import org.decred.ticket.TicketApplication
import org.decred.ticket.data.DataModule
import org.decred.ticket.util.DeliveryModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        InfraModule::class,
        DeliveryModule::class,
        DataModule::class,
        ActivityBuilderModule::class))
interface ApplicationComponent : AndroidInjector<TicketApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}
