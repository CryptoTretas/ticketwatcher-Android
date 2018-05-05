package org.decred.ticket.future.home.fragment.settings

import dagger.Module
import dagger.Provides
import org.decred.ticket.DAO.UserPreference

@Module
class SettingsModule {

    @Provides
    fun providesSettingsFragment(fragment: SettingsFragment): SettingsContract.View = fragment

    @Provides
    fun provideExchangePresenter(
            exchangeFragment: SettingsFragment, userPreference: UserPreference): SettingsContract.Presenter = SettingsPresenter(exchangeFragment, userPreference)

}