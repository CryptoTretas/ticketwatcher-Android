package org.decred.ticket.future.home.fragment.balance

import dagger.Module
import dagger.Provides
import org.decred.ticket.DAO.UserPreference
import org.decred.ticket.future.home.fragment.settings.SettingsContract
import org.decred.ticket.future.home.fragment.settings.SettingsFragment
import org.decred.ticket.future.home.fragment.settings.SettingsPresenter

@Module
class BalanceModule {

    @Provides
    fun providesSettingsFragment(fragment: BalanceFragment): BalanceContract.View = fragment

    @Provides
    fun provideExchangePresenter(
            exchangeFragment: BalanceFragment, userPreference: UserPreference): BalanceContract.Presenter = BalancePresenter(exchangeFragment, userPreference)

}