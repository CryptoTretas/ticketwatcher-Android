package org.decred.ticket.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.decred.ticket.future.home.fragment.balance.BalanceFragment
import org.decred.ticket.future.home.fragment.balance.BalanceModule
import org.decred.ticket.future.home.fragment.balance.ExtractFragment
import org.decred.ticket.future.home.fragment.extract.ExtractMoculde
import org.decred.ticket.future.home.fragment.settings.SettingsFragment
import org.decred.ticket.future.home.fragment.settings.SettingsModule


@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = arrayOf(ExtractMoculde::class))
    internal abstract fun ExtractFragment(): ExtractFragment

    @ContributesAndroidInjector(modules = arrayOf(BalanceModule::class))
    internal abstract fun BalanceFragment(): BalanceFragment

    @ContributesAndroidInjector(modules = arrayOf(SettingsModule::class))
    internal abstract fun SettingsFragment(): SettingsFragment

}