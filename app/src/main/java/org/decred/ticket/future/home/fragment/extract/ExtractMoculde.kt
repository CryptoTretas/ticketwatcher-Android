package org.decred.ticket.future.home.fragment.extract

import dagger.Module
import dagger.Provides
import org.decred.ticket.DAO.UserPreference
import org.decred.ticket.future.home.fragment.balance.ExtractFragment
import org.decred.ticket.future.home.fragment.settings.SettingsContract
import org.decred.ticket.future.home.fragment.settings.SettingsFragment
import org.decred.ticket.future.home.fragment.settings.SettingsPresenter

@Module
class ExtractMoculde {

    @Provides
    fun providesSettingsFragment(fragment: ExtractFragment): ExtractContract.View = fragment

    @Provides
    fun provideExchangePresenter(
            exchangeFragment: ExtractFragment, userPreference: UserPreference): ExtractContract.Presenter = ExtractPresenter(exchangeFragment, userPreference)

}