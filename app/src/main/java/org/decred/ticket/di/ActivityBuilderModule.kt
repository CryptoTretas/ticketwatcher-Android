package org.decred.ticket.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.decred.ticket.future.association.AssociationActivity
import org.decred.ticket.future.association.AssociationModule
import org.decred.ticket.future.splash.SplashActivity
import org.decred.ticket.future.splash.SplashModule


@Module
abstract class ActivityBuilderModule{

    @ContributesAndroidInjector(modules = arrayOf(SplashModule::class))
    internal abstract fun SplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = arrayOf(AssociationModule::class))
    internal abstract fun AssociationActivity(): AssociationActivity

}